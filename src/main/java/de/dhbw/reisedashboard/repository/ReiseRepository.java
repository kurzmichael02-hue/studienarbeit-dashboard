package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Reise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenzugriff auf Reisen. Spring Data leitet die Abfragen aus den Methodennamen ab,
 * eine eigene Implementierung ist nicht noetig.
 */
public interface ReiseRepository extends JpaRepository<Reise, Integer> {

    // Die Reisen eines Benutzers, die naechste zuerst.
    List<Reise> findByBenutzerOrderByStartdatumAsc(Benutzer benutzer);
}
