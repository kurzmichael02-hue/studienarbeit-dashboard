package de.dhbw.reisedashboard.service;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Notizen;
import de.dhbw.reisedashboard.repository.NotizenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Geschaeftslogik rund um die Notizen. Notizen haengen am Benutzer, nicht an einer Reise,
 * damit man auch unabhaengig von einer Reise etwas festhalten kann.
 */
@Service
public class NotizenService {

    private final NotizenRepository notizenRepository;

    public NotizenService(NotizenRepository notizenRepository) {
        this.notizenRepository = notizenRepository;
    }

    // Alle Notizen eines Benutzers, die neueste zuerst.
    public List<Notizen> notizenVon(Benutzer benutzer) {
        return notizenRepository.findByBenutzerOrderByErstelltAmDesc(benutzer);
    }

    @Transactional
    public Notizen hinzufuegen(Benutzer benutzer, String inhalt) {
        if (inhalt == null || inhalt.isBlank()) {
            throw new IllegalArgumentException("Eine Notiz darf nicht leer sein.");
        }
        return notizenRepository.save(new Notizen(benutzer, inhalt));
    }

    // Loescht eine Notiz, aber nur wenn sie dem Benutzer gehoert.
    @Transactional
    public void loeschen(Integer notizID, Benutzer benutzer) {
        Notizen notiz = notizenRepository.findByNotizIDAndBenutzer(notizID, benutzer)
                .orElseThrow(() -> new IllegalArgumentException("Notiz " + notizID + " gehoert diesem Profil nicht."));
        notizenRepository.delete(notiz);
    }
}
