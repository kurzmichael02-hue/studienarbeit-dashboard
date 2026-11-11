package de.dhbw.reisedashboard.service;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Lektion;
import de.dhbw.reisedashboard.model.Lernfortschritt;
import de.dhbw.reisedashboard.model.Vokabel;
import de.dhbw.reisedashboard.repository.LernfortschrittRepository;
import de.dhbw.reisedashboard.repository.VokabelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Das Herzstueck des Sprachlernteils: welche Vokabeln sind heute faellig, und was passiert,
 * wenn der Nutzer eine Karte richtig oder falsch beantwortet. Die reine Leitner-Regel steht in
 * {@link Wiederholung}; dieser Service verbindet sie mit dem gespeicherten Lernfortschritt.
 *
 * In welchem der fuenf Faecher eine Vokabel fuer einen Benutzer liegt, steht im Feld "status"
 * des Lernfortschritts (als Zahl 1 bis 5). Eine Vokabel ohne Eintrag wurde noch nie geuebt und
 * startet in Fach 1.
 */
@Service
public class VokabelService {

    private final VokabelRepository vokabelRepository;
    private final LernfortschrittRepository lernfortschrittRepository;

    public VokabelService(VokabelRepository vokabelRepository,
                          LernfortschrittRepository lernfortschrittRepository) {
        this.vokabelRepository = vokabelRepository;
        this.lernfortschrittRepository = lernfortschrittRepository;
    }

    // Alle Vokabeln einer Lektion.
    public List<Vokabel> vokabelnVon(Lektion lektion) {
        return vokabelRepository.findByLektion(lektion);
    }

    /**
     * Die heute faelligen Vokabeln einer Lektion fuer einen Benutzer: alle, die er noch nie geuebt
     * hat, plus die, deren Wiederholungsabstand abgelaufen ist.
     */
    public List<Vokabel> faelligeVokabeln(Benutzer benutzer, Lektion lektion) {
        LocalDate heute = LocalDate.now();
        List<Vokabel> faellig = new ArrayList<>();
        for (Vokabel vokabel : vokabelRepository.findByLektion(lektion)) {
            Lernfortschritt fortschritt = lernfortschrittRepository
                    .findByBenutzerAndVokabel(benutzer, vokabel).orElse(null);
            if (fortschritt == null) {
                faellig.add(vokabel);
                continue;
            }
            int fach = fachAus(fortschritt);
            LocalDate termin = Wiederholung.faelligAb(fach, fortschritt.getLetzteUebung().toLocalDate());
            if (Wiederholung.istFaellig(termin, heute)) {
                faellig.add(vokabel);
            }
        }
        return faellig;
    }

    /**
     * Verarbeitet die Antwort auf eine Vokabel. Richtig = ein Fach hoeher (laengere Pause),
     * falsch = zurueck in Fach 1. Legt den Lernfortschritt beim ersten Mal an.
     */
    @Transactional
    public void beantworten(Benutzer benutzer, Vokabel vokabel, boolean richtig) {
        Lernfortschritt fortschritt = lernfortschrittRepository
                .findByBenutzerAndVokabel(benutzer, vokabel).orElse(null);
        int aktuellesFach = fortschritt == null ? Wiederholung.ERSTES_FACH : fachAus(fortschritt);
        int neuesFach = Wiederholung.naechstesFach(aktuellesFach, richtig);

        if (fortschritt == null) {
            fortschritt = new Lernfortschritt(benutzer, vokabel, String.valueOf(neuesFach), 1, LocalDateTime.now());
        } else {
            fortschritt.setStatus(String.valueOf(neuesFach));
            fortschritt.setWiederholungen(fortschritt.getWiederholungen() + 1);
            fortschritt.setLetzteUebung(LocalDateTime.now());
        }
        lernfortschrittRepository.save(fortschritt);
    }

    // Liest das Fach aus dem status-Feld. Faellt auf Fach 1 zurueck, falls dort mal etwas
    // Unerwartetes steht, damit die Abfrage nie wegen eines kaputten Werts abbricht.
    private int fachAus(Lernfortschritt fortschritt) {
        try {
            int fach = Integer.parseInt(fortschritt.getStatus().trim());
            if (fach < Wiederholung.ERSTES_FACH || fach > Wiederholung.LETZTES_FACH) {
                return Wiederholung.ERSTES_FACH;
            }
            return fach;
        } catch (NumberFormatException e) {
            return Wiederholung.ERSTES_FACH;
        }
    }
}
