package de.dhbw.reisedashboard.controller;

import de.dhbw.reisedashboard.config.LoginInterceptor;
import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.service.BenutzerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final BenutzerService benutzerService;

    public LoginController(BenutzerService benutzerService) {
        this.benutzerService = benutzerService;
    }

    // Zeigt die Profil-Auswahl. Die Liste der Benutzer kommt aus der Datenbank in die Oberflaeche.
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("benutzer", benutzerService.alle());
        return "login";
    }

    // Meldet den gewaehlten Benutzer an. Nur wenn es das Profil wirklich gibt, sonst zurueck
    // zur Auswahl (kein Geist-Profil in die Session).
    @PostMapping("/login")
    public String anmelden(@RequestParam Integer benutzerId, HttpSession session) {
        if (benutzerService.finden(benutzerId).isEmpty()) {
            return "redirect:/login";
        }
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, benutzerId);
        return "redirect:/";
    }

    // Legt ein neues Profil an (Rang 0) und meldet es gleich an.
    @PostMapping("/benutzer/neu")
    public String neuesProfil(@RequestParam String vorname, @RequestParam String nachname, HttpSession session) {
        Benutzer benutzer = benutzerService.anlegen(vorname, nachname);
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, benutzer.getBenutzerID());
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String abmelden(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
