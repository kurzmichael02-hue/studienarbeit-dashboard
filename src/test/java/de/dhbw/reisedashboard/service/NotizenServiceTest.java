package de.dhbw.reisedashboard.service;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Notizen;
import de.dhbw.reisedashboard.repository.BenutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Prueft den NotizenService: anlegen, auflisten, leere Notiz abweisen, und dass ein Profil
 * nur seine eigenen Notizen loeschen kann.
 */
@SpringBootTest
@Transactional
class NotizenServiceTest {

    @Autowired
    private NotizenService notizenService;
    @Autowired
    private BenutzerRepository benutzerRepository;

    private Benutzer michael;
    private Benutzer schayan;

    @BeforeEach
    void anlegen() {
        michael = benutzerRepository.save(new Benutzer("Michael", "Kurz", 0));
        schayan = benutzerRepository.save(new Benutzer("Schayan", "Goudarzi", 0));
    }

    @Test
    void notizWirdAngelegtUndGelistet() {
        notizenService.hinzufuegen(michael, "Reisepass verlaengern");

        List<Notizen> notizen = notizenService.notizenVon(michael);
        assertEquals(1, notizen.size());
        assertEquals("Reisepass verlaengern", notizen.get(0).getInhalt());
    }

    @Test
    void leereNotizWirdAbgewiesen() {
        assertThrows(IllegalArgumentException.class, () -> notizenService.hinzufuegen(michael, "   "));
    }

    @Test
    void fremdeNotizKannNichtGeloeschtWerden() {
        Notizen notiz = notizenService.hinzufuegen(michael, "privat");

        // Schayan versucht, Michaels Notiz zu loeschen.
        assertThrows(IllegalArgumentException.class, () -> notizenService.loeschen(notiz.getNotizID(), schayan));
        assertEquals(1, notizenService.notizenVon(michael).size());
    }

    @Test
    void eigeneNotizWirdGeloescht() {
        Notizen notiz = notizenService.hinzufuegen(michael, "weg damit");
        notizenService.loeschen(notiz.getNotizID(), michael);
        assertTrue(notizenService.notizenVon(michael).isEmpty());
    }
}
