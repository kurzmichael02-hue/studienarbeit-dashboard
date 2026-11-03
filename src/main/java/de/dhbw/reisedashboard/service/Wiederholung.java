package de.dhbw.reisedashboard.service;

import java.time.LocalDate;

/**
 * Rechnet aus, wann eine Vokabel wieder abgefragt wird (Spaced Repetition nach Leitner).
 * Eine Vokabel sitzt in einem von fuenf Faechern. Richtig = ein Fach hoeher, laengere Pause.
 * Falsch = zurueck in Fach 1, morgen wieder. Bewusst ohne Datenbank, damit die Regel
 * fuer sich getestet werden kann; welche Vokabel in welchem Fach liegt, speichert spaeter
 * der Lernfortschritt des Benutzers.
 */
public final class Wiederholung {

    public static final int ERSTES_FACH = 1;
    public static final int LETZTES_FACH = 5;

    // Pause in Tagen je Fach: 1, 3, 7, 14, 30
    private static final int[] PAUSE_TAGE = {1, 3, 7, 14, 30};

    private Wiederholung() {
    }

    public static int naechstesFach(int fach, boolean richtig) {
        pruefeFach(fach);
        if (!richtig) {
            return ERSTES_FACH;
        }
        return Math.min(fach + 1, LETZTES_FACH);
    }

    public static LocalDate naechsterTermin(int fach, boolean richtig, LocalDate heute) {
        int neuesFach = naechstesFach(fach, richtig);
        return heute.plusDays(PAUSE_TAGE[neuesFach - 1]);
    }

    public static boolean istFaellig(LocalDate termin, LocalDate heute) {
        // Vokabeln ohne Termin wurden noch nie gelernt, die sind immer dran.
        return termin == null || !termin.isAfter(heute);
    }

    private static void pruefeFach(int fach) {
        if (fach < ERSTES_FACH || fach > LETZTES_FACH) {
            throw new IllegalArgumentException("Fach muss zwischen 1 und 5 liegen, war " + fach);
        }
    }
}
