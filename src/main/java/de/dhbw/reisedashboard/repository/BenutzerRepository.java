package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Datenzugriff auf die Benutzer. Fuer die Profilauswahl beim Login reicht findAll aus JpaRepository.
 */
public interface BenutzerRepository extends JpaRepository<Benutzer, Integer> {
}
