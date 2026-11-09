package de.dhbw.reisedashboard.controller;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Reise;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.repository.SpracheRepository;
import de.dhbw.reisedashboard.service.BenutzerService;
import de.dhbw.reisedashboard.service.ReiseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Steuert die Reise-Seiten: Liste, neue Reise, Detailansicht mit Tagesplan, Budget und Packliste.
 * Der Controller holt den angemeldeten Benutzer aus der Session und laesst den ReiseService die
 * eigentliche Arbeit machen; hier wird nur zwischen Browser und Service vermittelt.
 */
@Controller
public class ReiseController {

    private final ReiseService reiseService;
    private final BenutzerService benutzerService;
    private final SpracheRepository spracheRepository;

    public ReiseController(ReiseService reiseService, BenutzerService benutzerService,
                           SpracheRepository spracheRepository) {
        this.reiseService = reiseService;
        this.benutzerService = benutzerService;
        this.spracheRepository = spracheRepository;
    }

    // Der angemeldete Benutzer. Die Login-Pruefung macht schon der Interceptor, hier kann die
    // Session also nicht leer sein; der Fehler faellt nur, wenn das Profil zwischendurch geloescht wurde.
    private Benutzer angemeldeter(HttpSession session) {
        return benutzerService.ausSession(session)
                .orElseThrow(() -> new IllegalStateException("Kein angemeldeter Benutzer in der Session."));
    }

    // Laedt eine Reise nur dann, wenn sie dem angemeldeten Benutzer gehoert. Sonst 404, damit kein
    // Profil ueber die URL (z.B. /reisen/5) an die Reise eines anderen Profils kommt.
    private Reise eigeneReise(Integer reiseId, HttpSession session) {
        return reiseService.reiseFuerBenutzer(reiseId, angemeldeter(session))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Liste der eigenen Reisen.
    @GetMapping("/reisen")
    public String liste(HttpSession session, Model model) {
        model.addAttribute("reisen", reiseService.reisenVonBenutzer(angemeldeter(session)));
        return "reisen";
    }

    // Formular fuer eine neue Reise. Die Sprachauswahl kommt aus der Datenbank.
    @GetMapping("/reisen/neu")
    public String neuForm(Model model) {
        model.addAttribute("sprachen", spracheRepository.findAll());
        return "reise-neu";
    }

    @PostMapping("/reisen")
    public String anlegen(@RequestParam Integer spracheId,
                          @RequestParam String ziel,
                          @RequestParam String land,
                          @RequestParam BigDecimal budget,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdatum,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddatum,
                          HttpSession session) {
        Sprache sprache = spracheRepository.findById(spracheId)
                .orElseThrow(() -> new IllegalArgumentException("Sprache " + spracheId + " gibt es nicht."));
        Reise reise = reiseService.reiseAnlegen(angemeldeter(session), sprache, ziel, land, budget, startdatum, enddatum);
        return "redirect:/reisen/" + reise.getReiseID();
    }

    // Detailansicht mit Tagesplan, Budget und Packliste.
    @GetMapping("/reisen/{reiseId}")
    public String detail(@PathVariable Integer reiseId, HttpSession session, Model model) {
        Reise reise = eigeneReise(reiseId, session);
        model.addAttribute("reise", reise);
        model.addAttribute("tagesplan", reiseService.tagesplan(reise));
        model.addAttribute("ausgaben", reiseService.ausgaben(reise));
        model.addAttribute("budget", reiseService.budgetStand(reise));
        model.addAttribute("packliste", reiseService.packliste(reise));
        return "reise-detail";
    }

    @PostMapping("/reisen/{reiseId}/aktivitaet")
    public String aktivitaet(@PathVariable Integer reiseId, HttpSession session,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datum,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime uhrzeit,
                             @RequestParam String titel) {
        reiseService.aktivitaetHinzufuegen(eigeneReise(reiseId, session), datum, uhrzeit, titel);
        return "redirect:/reisen/" + reiseId;
    }

    @PostMapping("/reisen/{reiseId}/ausgabe")
    public String ausgabe(@PathVariable Integer reiseId, HttpSession session,
                          @RequestParam String kategorie,
                          @RequestParam BigDecimal betrag) {
        reiseService.ausgabeHinzufuegen(eigeneReise(reiseId, session), kategorie, betrag);
        return "redirect:/reisen/" + reiseId;
    }

    @PostMapping("/reisen/{reiseId}/packliste")
    public String packeintrag(@PathVariable Integer reiseId, HttpSession session, @RequestParam String bezeichnung) {
        reiseService.packeintragHinzufuegen(eigeneReise(reiseId, session), bezeichnung);
        return "redirect:/reisen/" + reiseId;
    }

    @PostMapping("/reisen/{reiseId}/packliste/{packeintragId}/umschalten")
    public String packeintragUmschalten(@PathVariable Integer reiseId, @PathVariable Integer packeintragId, HttpSession session) {
        reiseService.packeintragUmschalten(eigeneReise(reiseId, session), packeintragId);
        return "redirect:/reisen/" + reiseId;
    }
}
