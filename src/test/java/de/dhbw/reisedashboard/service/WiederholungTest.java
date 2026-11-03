package de.dhbw.reisedashboard.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WiederholungTest {

    private final LocalDate heute = LocalDate.of(2026, 11, 3);

    @Test
    void richtigRueckEinFachHoch() {
        assertEquals(2, Wiederholung.naechstesFach(1, true));
        assertEquals(5, Wiederholung.naechstesFach(4, true));
    }

    @Test
    void imLetztenFachBleibtManStehen() {
        assertEquals(5, Wiederholung.naechstesFach(5, true));
    }

    @Test
    void falschWirftZurueckInsErsteFach() {
        assertEquals(1, Wiederholung.naechstesFach(4, false));
        assertEquals(1, Wiederholung.naechstesFach(1, false));
    }

    @Test
    void pausenWerdenLaenger() {
        assertEquals(heute.plusDays(3), Wiederholung.naechsterTermin(1, true, heute));
        assertEquals(heute.plusDays(7), Wiederholung.naechsterTermin(2, true, heute));
        assertEquals(heute.plusDays(30), Wiederholung.naechsterTermin(5, true, heute));
    }

    @Test
    void nachFehlerMorgenWieder() {
        assertEquals(heute.plusDays(1), Wiederholung.naechsterTermin(4, false, heute));
    }

    @Test
    void faelligHeuteOderFrueherOderNieGelernt() {
        assertTrue(Wiederholung.istFaellig(heute, heute));
        assertTrue(Wiederholung.istFaellig(heute.minusDays(2), heute));
        assertTrue(Wiederholung.istFaellig(null, heute));
        assertFalse(Wiederholung.istFaellig(heute.plusDays(1), heute));
    }

    @Test
    void ungueltigesFachKnallt() {
        assertThrows(IllegalArgumentException.class, () -> Wiederholung.naechstesFach(0, true));
        assertThrows(IllegalArgumentException.class, () -> Wiederholung.naechstesFach(6, false));
    }
}
