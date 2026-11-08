package de.dhbw.reisedashboard.service;

import de.dhbw.reisedashboard.config.LoginInterceptor;
import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.repository.BenutzerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Geschaeftslogik rund um die Benutzer-Profile. Beim Login gibt es kein Passwort,
 * man waehlt ein Profil aus der Liste (wie am Fernseher). Dieser Service liefert die
 * Liste, legt neue Profile an und loest den in der Session gewaehlten Benutzer auf.
 */
@Service
public class BenutzerService {

    private final BenutzerRepository benutzerRepository;

    public BenutzerService(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    // Alle Profile fuer die Auswahl auf der Login-Seite.
    public List<Benutzer> alle() {
        return benutzerRepository.findAll();
    }

    public Optional<Benutzer> finden(Integer benutzerID) {
        if (benutzerID == null) {
            return Optional.empty();
        }
        return benutzerRepository.findById(benutzerID);
    }

    /**
     * Legt ein neues Profil an. Neue Profile starten mit Rang 0; der Rang waechst
     * erst ueber das Lernen (Highscore), deshalb wird er hier nicht abgefragt.
     */
    @Transactional
    public Benutzer anlegen(String vorname, String nachname) {
        return benutzerRepository.save(new Benutzer(vorname, nachname, 0));
    }

    // Der aktuell in der Session gewaehlte Benutzer, oder leer wenn keiner gewaehlt ist.
    public Optional<Benutzer> ausSession(HttpSession session) {
        Object id = session.getAttribute(LoginInterceptor.SESSION_BENUTZER);
        if (id instanceof Integer benutzerID) {
            return finden(benutzerID);
        }
        return Optional.empty();
    }
}
