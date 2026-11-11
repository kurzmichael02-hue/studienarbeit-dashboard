package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Lernfortschritt;
import de.dhbw.reisedashboard.model.Vokabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Datenzugriff auf den Lernfortschritt. Pro Benutzer und Vokabel gibt es hoechstens einen
 * Eintrag (Unique-Constraint in der Entity), der festhaelt, in welchem Leitner-Fach die
 * Vokabel gerade liegt und wann sie zuletzt geuebt wurde.
 */
public interface LernfortschrittRepository extends JpaRepository<Lernfortschritt, Integer> {

    Optional<Lernfortschritt> findByBenutzerAndVokabel(Benutzer benutzer, Vokabel vokabel);
}
