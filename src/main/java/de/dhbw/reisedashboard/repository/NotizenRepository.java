package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Notizen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Datenzugriff auf die Notizen eines Benutzers.
 */
public interface NotizenRepository extends JpaRepository<Notizen, Integer> {

    // Die Notizen eines Benutzers, die neueste zuerst.
    List<Notizen> findByBenutzerOrderByErstelltAmDesc(Benutzer benutzer);

    // Eine Notiz, aber nur wenn sie dem Benutzer gehoert. So kann ueber die URL kein fremdes
    // Profil die Notiz eines anderen loeschen.
    Optional<Notizen> findByNotizIDAndBenutzer(Integer notizID, Benutzer benutzer);
}
