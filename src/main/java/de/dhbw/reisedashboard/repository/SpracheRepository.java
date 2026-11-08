package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Sprache;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Datenzugriff auf die Sprachen (Russisch, Japanisch, Koreanisch).
 */
public interface SpracheRepository extends JpaRepository<Sprache, Integer> {
}
