package de.dhbw.reisedashboard.controller;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Lektion;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.model.Vokabel;
import de.dhbw.reisedashboard.repository.LektionRepository;
import de.dhbw.reisedashboard.repository.SpracheRepository;
import de.dhbw.reisedashboard.repository.VokabelRepository;
import de.dhbw.reisedashboard.service.BenutzerService;
import de.dhbw.reisedashboard.service.VokabelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

/**
 * Steuert den Sprachlern-Teil: Sprache waehlen, Lektion waehlen, Vokabeltrainer. Der Trainer
 * zeigt die heute faelligen Vokabeln; eine Antwort (richtig/falsch) geht an den VokabelService,
 * der das Leitner-Fach anpasst. Welche Vokabel wann faellig ist, haengt am angemeldeten
 * Benutzer aus der Session.
 */
@Controller
public class VokabelController {

    private final VokabelService vokabelService;
    private final BenutzerService benutzerService;
    private final SpracheRepository spracheRepository;
    private final LektionRepository lektionRepository;
    private final VokabelRepository vokabelRepository;

    public VokabelController(VokabelService vokabelService, BenutzerService benutzerService,
                             SpracheRepository spracheRepository, LektionRepository lektionRepository,
                             VokabelRepository vokabelRepository) {
        this.vokabelService = vokabelService;
        this.benutzerService = benutzerService;
        this.spracheRepository = spracheRepository;
        this.lektionRepository = lektionRepository;
        this.vokabelRepository = vokabelRepository;
    }

    private Benutzer angemeldeter(HttpSession session) {
        return benutzerService.ausSession(session)
                .orElseThrow(() -> new IllegalStateException("Kein angemeldeter Benutzer in der Session."));
    }

    // Sprachauswahl.
    @GetMapping("/lernen")
    public String sprachen(Model model) {
        model.addAttribute("sprachen", spracheRepository.findAll());
        return "sprachen";
    }

    // Lektionen einer Sprache.
    @GetMapping("/lernen/sprache/{spracheId}")
    public String lektionen(@PathVariable Integer spracheId, Model model) {
        Sprache sprache = spracheRepository.findById(spracheId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("sprache", sprache);
        model.addAttribute("lektionen", lektionRepository.findBySprache(sprache));
        return "lektionen";
    }

    // Vokabeltrainer: die heute faelligen Vokabeln der Lektion fuer den angemeldeten Benutzer.
    @GetMapping("/lernen/lektion/{lektionId}")
    public String trainer(@PathVariable Integer lektionId, HttpSession session, Model model) {
        Lektion lektion = lektion(lektionId);
        model.addAttribute("lektion", lektion);
        model.addAttribute("faellige", vokabelService.faelligeVokabeln(angemeldeter(session), lektion));
        return "vokabeltrainer";
    }

    // Eine beantwortete Vokabel verbuchen und zurueck zum Trainer.
    @PostMapping("/lernen/lektion/{lektionId}/vokabel/{vokabelId}")
    public String beantworten(@PathVariable Integer lektionId, @PathVariable Integer vokabelId,
                              @RequestParam boolean richtig, HttpSession session) {
        Vokabel vokabel = vokabelRepository.findById(vokabelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        vokabelService.beantworten(angemeldeter(session), vokabel, richtig);
        return "redirect:/lernen/lektion/" + lektionId;
    }

    private Lektion lektion(Integer lektionId) {
        return lektionRepository.findById(lektionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
