package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Lektion;
import de.dhbw.reisedashboard.model.Vokabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenzugriff auf die Vokabeln einer Lektion.
 */
public interface VokabelRepository extends JpaRepository<Vokabel, Integer> {

    List<Vokabel> findByLektion(Lektion lektion);
}
