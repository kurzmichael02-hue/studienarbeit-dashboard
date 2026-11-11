package de.dhbw.reisedashboard.service;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Lektion;
import de.dhbw.reisedashboard.model.Lernfortschritt;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.model.Vokabel;
import de.dhbw.reisedashboard.repository.BenutzerRepository;
import de.dhbw.reisedashboard.repository.LektionRepository;
import de.dhbw.reisedashboard.repository.LernfortschrittRepository;
import de.dhbw.reisedashboard.repository.SpracheRepository;
import de.dhbw.reisedashboard.repository.VokabelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Prueft die Vokabel-Logik: neue Vokabeln sind faellig, eine richtige Antwort rueckt die Karte
 * ein Fach hoeher (dann heute nicht mehr faellig), eine falsche haelt sie in Fach 1.
 */
@SpringBootTest
@Transactional
class VokabelServiceTest {

    @Autowired
    private VokabelService vokabelService;
    @Autowired
    private LernfortschrittRepository lernfortschrittRepository;
    @Autowired
    private BenutzerRepository benutzerRepository;
    @Autowired
    private SpracheRepository spracheRepository;
    @Autowired
    private LektionRepository lektionRepository;
    @Autowired
    private VokabelRepository vokabelRepository;

    private Benutzer benutzer;
    private Lektion lektion;
    private Vokabel haus;
    private Vokabel baum;

    @BeforeEach
    void anlegen() {
        benutzer = benutzerRepository.save(new Benutzer("Michael", "Kurz", 0));
        Sprache sprache = spracheRepository.save(new Sprache("Russisch"));
        lektion = lektionRepository.save(new Lektion(sprache, "Erste Woerter"));
        haus = vokabelRepository.save(new Vokabel(lektion, "Haus", "dom", "dom"));
        baum = vokabelRepository.save(new Vokabel(lektion, "Baum", "derevo", "derevo"));
    }

    @Test
    void neueVokabelnSindAlleFaellig() {
        List<Vokabel> faellig = vokabelService.faelligeVokabeln(benutzer, lektion);
        assertEquals(2, faellig.size());
    }

    @Test
    void richtigBeantwortetRuecktEinFachHochUndIstHeuteNichtMehrFaellig() {
        vokabelService.beantworten(benutzer, haus, true);

        // "haus" liegt jetzt in Fach 2 (Pause 3 Tage), also heute nicht mehr faellig; "baum" schon.
        List<Vokabel> faellig = vokabelService.faelligeVokabeln(benutzer, lektion);
        assertEquals(1, faellig.size());
        assertEquals("Baum", faellig.get(0).getDeutsch());

        Lernfortschritt fortschritt = lernfortschrittRepository.findByBenutzerAndVokabel(benutzer, haus).orElseThrow();
        assertEquals("2", fortschritt.getStatus());
    }

    @Test
    void falschBeantwortetHaeltInFachEinsUndKommtErstMorgenWieder() {
        vokabelService.beantworten(benutzer, haus, false);

        Lernfortschritt fortschritt = lernfortschrittRepository.findByBenutzerAndVokabel(benutzer, haus).orElseThrow();
        assertEquals("1", fortschritt.getStatus());

        // Nach dem Ueben ist die Karte heute nicht noch einmal faellig (Fach 1 = 1 Tag Pause),
        // heute bleibt nur die ungeuebte "baum".
        List<Vokabel> faellig = vokabelService.faelligeVokabeln(benutzer, lektion);
        assertEquals(1, faellig.size());
        assertEquals("Baum", faellig.get(0).getDeutsch());
    }

    @Test
    void wiederholungenWerdenGezaehlt() {
        vokabelService.beantworten(benutzer, haus, true);
        vokabelService.beantworten(benutzer, haus, true);

        Lernfortschritt fortschritt = lernfortschrittRepository.findByBenutzerAndVokabel(benutzer, haus).orElseThrow();
        assertEquals(2, fortschritt.getWiederholungen());
        assertEquals("3", fortschritt.getStatus());
    }

    @Test
    void falschWirftAusHoeheremFachZurueckInFachEins() {
        vokabelService.beantworten(benutzer, haus, true);   // Fach 2
        vokabelService.beantworten(benutzer, haus, true);   // Fach 3
        vokabelService.beantworten(benutzer, haus, false);  // zurueck in Fach 1

        Lernfortschritt fortschritt = lernfortschrittRepository.findByBenutzerAndVokabel(benutzer, haus).orElseThrow();
        assertEquals("1", fortschritt.getStatus());
    }
}
