package de.dhbw.reisedashboard.service;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Reise;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.repository.BenutzerRepository;
import de.dhbw.reisedashboard.repository.SpracheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Prueft den ReiseService gegen die echte H2-Datenbank: anlegen, Budget rechnen,
 * und dass unsinnige Daten abgewiesen werden. @Transactional macht jeden Test nach
 * dem Durchlauf rueckgaengig, damit die Tests sich nicht gegenseitig beeinflussen.
 */
@SpringBootTest
@Transactional
class ReiseServiceTest {

    @Autowired
    private ReiseService reiseService;
    @Autowired
    private BenutzerRepository benutzerRepository;
    @Autowired
    private SpracheRepository spracheRepository;

    private Benutzer benutzer;
    private Sprache sprache;

    @BeforeEach
    void anlegen() {
        benutzer = benutzerRepository.save(new Benutzer("Michael", "Kurz", 0));
        sprache = spracheRepository.save(new Sprache("Russisch"));
    }

    @Test
    void reiseWirdGespeichert() {
        Reise reise = reiseService.reiseAnlegen(benutzer, sprache, "Samara", "Russland",
                new BigDecimal("1000.00"), LocalDate.of(2027, 3, 1), LocalDate.of(2027, 3, 14));

        assertEquals("Samara", reiseService.reiseFinden(reise.getReiseID()).getZiel());
        assertEquals(1, reiseService.reisenVonBenutzer(benutzer).size());
    }

    @Test
    void budgetStandZiehtAusgabenAb() {
        Reise reise = reiseService.reiseAnlegen(benutzer, sprache, "Samara", "Russland",
                new BigDecimal("1000.00"), LocalDate.of(2027, 3, 1), LocalDate.of(2027, 3, 14));
        reiseService.ausgabeHinzufuegen(reise, "Flug", new BigDecimal("300.00"));
        reiseService.ausgabeHinzufuegen(reise, "Hotel", new BigDecimal("250.00"));

        BudgetStand stand = reiseService.budgetStand(reise);

        assertEquals(0, new BigDecimal("550.00").compareTo(stand.ausgegeben()));
        assertEquals(0, new BigDecimal("450.00").compareTo(stand.verbleibend()));
    }

    @Test
    void ohneAusgabenIstDasGanzeBudgetUebrig() {
        Reise reise = reiseService.reiseAnlegen(benutzer, sprache, "Tokio", "Japan",
                new BigDecimal("2000.00"), LocalDate.of(2027, 4, 1), LocalDate.of(2027, 4, 20));

        BudgetStand stand = reiseService.budgetStand(reise);

        assertEquals(0, BigDecimal.ZERO.compareTo(stand.ausgegeben()));
        assertEquals(0, new BigDecimal("2000.00").compareTo(stand.verbleibend()));
    }

    @Test
    void enddatumVorStartdatumWirdAbgewiesen() {
        assertThrows(IllegalArgumentException.class, () ->
                reiseService.reiseAnlegen(benutzer, sprache, "Seoul", "Korea",
                        new BigDecimal("1500.00"), LocalDate.of(2027, 5, 10), LocalDate.of(2027, 5, 1)));
    }

    @Test
    void negativesBudgetWirdAbgewiesen() {
        assertThrows(IllegalArgumentException.class, () ->
                reiseService.reiseAnlegen(benutzer, sprache, "Seoul", "Korea",
                        new BigDecimal("-10.00"), LocalDate.of(2027, 5, 1), LocalDate.of(2027, 5, 10)));
    }
}
