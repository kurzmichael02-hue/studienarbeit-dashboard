package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Lektion;
import de.dhbw.reisedashboard.model.Sprache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenzugriff auf die Lektionen einer Sprache.
 */
public interface LektionRepository extends JpaRepository<Lektion, Integer> {

    List<Lektion> findBySprache(Sprache sprache);
}
