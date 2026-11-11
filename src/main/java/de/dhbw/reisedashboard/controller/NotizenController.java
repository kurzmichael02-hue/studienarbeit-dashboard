package de.dhbw.reisedashboard.controller;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.service.BenutzerService;
import de.dhbw.reisedashboard.service.NotizenService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Steuert die Notizen-Seite: eigene Notizen anzeigen, anlegen und loeschen. Jedes Profil
 * sieht und aendert nur seine eigenen Notizen (der angemeldete Benutzer kommt aus der Session).
 */
@Controller
public class NotizenController {

    private final NotizenService notizenService;
    private final BenutzerService benutzerService;

    public NotizenController(NotizenService notizenService, BenutzerService benutzerService) {
        this.notizenService = notizenService;
        this.benutzerService = benutzerService;
    }

    private Benutzer angemeldeter(HttpSession session) {
        return benutzerService.ausSession(session)
                .orElseThrow(() -> new IllegalStateException("Kein angemeldeter Benutzer in der Session."));
    }

    @GetMapping("/notizen")
    public String liste(HttpSession session, Model model) {
        model.addAttribute("notizen", notizenService.notizenVon(angemeldeter(session)));
        return "notizen";
    }

    @PostMapping("/notizen")
    public String anlegen(@RequestParam String inhalt, HttpSession session) {
        notizenService.hinzufuegen(angemeldeter(session), inhalt);
        return "redirect:/notizen";
    }

    @PostMapping("/notizen/{notizId}/loeschen")
    public String loeschen(@PathVariable Integer notizId, HttpSession session) {
        notizenService.loeschen(notizId, angemeldeter(session));
        return "redirect:/notizen";
    }
}
