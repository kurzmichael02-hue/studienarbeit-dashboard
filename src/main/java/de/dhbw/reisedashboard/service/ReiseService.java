package de.dhbw.reisedashboard.service;

import de.dhbw.reisedashboard.model.Aktivitaet;
import de.dhbw.reisedashboard.model.Ausgabe;
import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Reise;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.repository.AktivitaetRepository;
import de.dhbw.reisedashboard.repository.AusgabeRepository;
import de.dhbw.reisedashboard.repository.ReiseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Geschaeftslogik rund um eine Reise: anlegen, den Tagesplan liefern und den Budgetstand
 * ausrechnen. Die Logik liegt bewusst hier und nicht im Controller, damit sie ohne Browser
 * getestet werden kann und die Oberflaeche austauschbar bleibt (siehe Schichtenarchitektur).
 */
@Service
public class ReiseService {

    private final ReiseRepository reiseRepository;
    private final AktivitaetRepository aktivitaetRepository;
    private final AusgabeRepository ausgabeRepository;

    public ReiseService(ReiseRepository reiseRepository,
                        AktivitaetRepository aktivitaetRepository,
                        AusgabeRepository ausgabeRepository) {
        this.reiseRepository = reiseRepository;
        this.aktivitaetRepository = aktivitaetRepository;
        this.ausgabeRepository = ausgabeRepository;
    }

    /**
     * Legt eine neue Reise an. Prueft vorher, dass das Enddatum nicht vor dem Startdatum
     * liegt und das Budget nicht negativ ist, sonst speichert die Datenbank Unsinn.
     */
    @Transactional
    public Reise reiseAnlegen(Benutzer benutzer, Sprache sprache, String ziel, String land,
                              BigDecimal budget, LocalDate startdatum, LocalDate enddatum) {
        if (enddatum.isBefore(startdatum)) {
            throw new IllegalArgumentException("Das Enddatum liegt vor dem Startdatum.");
        }
        if (budget.signum() < 0) {
            throw new IllegalArgumentException("Das Budget darf nicht negativ sein.");
        }
        Reise reise = new Reise(benutzer, sprache, ziel, budget, land, startdatum, enddatum);
        return reiseRepository.save(reise);
    }

    // Alle Reisen eines Benutzers, die naechste zuerst.
    public List<Reise> reisenVonBenutzer(Benutzer benutzer) {
        return reiseRepository.findByBenutzerOrderByStartdatumAsc(benutzer);
    }

    // Eine Reise anhand ihrer ID, oder Fehler wenn es sie nicht gibt.
    public Reise reiseFinden(Integer reiseID) {
        return reiseRepository.findById(reiseID)
                .orElseThrow(() -> new IllegalArgumentException("Reise " + reiseID + " gibt es nicht."));
    }

    // Tagesplan einer Reise, chronologisch sortiert.
    public List<Aktivitaet> tagesplan(Reise reise) {
        return aktivitaetRepository.findByReiseOrderByDatumAscUhrzeitAsc(reise);
    }

    @Transactional
    public Aktivitaet aktivitaetHinzufuegen(Reise reise, LocalDate datum, LocalTime uhrzeit, String titel) {
        return aktivitaetRepository.save(new Aktivitaet(reise, datum, uhrzeit, titel));
    }

    // Alle Ausgaben einer Reise, die neueste zuerst.
    public List<Ausgabe> ausgaben(Reise reise) {
        return ausgabeRepository.findByReiseOrderByDatumDesc(reise);
    }

    @Transactional
    public Ausgabe ausgabeHinzufuegen(Reise reise, String kategorie, BigDecimal betrag) {
        if (betrag.signum() < 0) {
            throw new IllegalArgumentException("Ein Ausgabenbetrag darf nicht negativ sein.");
        }
        return ausgabeRepository.save(new Ausgabe(reise, kategorie, betrag, LocalDateTime.now()));
    }

    /**
     * Rechnet den Budgetstand aus: geplantes Budget minus Summe der Ausgaben.
     * Das ist der Wert, den die Reise-Detailansicht anzeigt.
     */
    public BudgetStand budgetStand(Reise reise) {
        BigDecimal ausgegeben = ausgabeRepository.summeBetrag(reise);
        BigDecimal verbleibend = reise.getBudget().subtract(ausgegeben);
        return new BudgetStand(reise.getBudget(), ausgegeben, verbleibend);
    }
}
