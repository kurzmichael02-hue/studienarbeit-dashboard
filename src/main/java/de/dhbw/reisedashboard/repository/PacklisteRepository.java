package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Packliste;
import de.dhbw.reisedashboard.model.Reise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenzugriff auf die Packliste einer Reise.
 */
public interface PacklisteRepository extends JpaRepository<Packliste, Integer> {

    // Packliste einer Reise in der Reihenfolge, in der die Eintraege angelegt wurden.
    List<Packliste> findByReiseOrderByPackeintragIDAsc(Reise reise);
}
