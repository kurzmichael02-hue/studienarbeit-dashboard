package de.dhbw.reisedashboard.controller;

import de.dhbw.reisedashboard.config.LoginInterceptor;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Die Benutzerliste fuer die Profil-Auswahl kommt mit der Benutzer-Entity dazu.
    // Bis dahin reicht die Id, damit die Session-Logik schon getestet werden kann.
    @PostMapping("/login")
    public String anmelden(@RequestParam Long benutzerId, HttpSession session) {
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, benutzerId);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String abmelden(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
