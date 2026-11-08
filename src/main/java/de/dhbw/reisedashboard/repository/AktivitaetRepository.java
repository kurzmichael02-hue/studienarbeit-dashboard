package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Aktivitaet;
import de.dhbw.reisedashboard.model.Reise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenzugriff auf die Aktivitaeten (Tagesplan) einer Reise.
 */
public interface AktivitaetRepository extends JpaRepository<Aktivitaet, Integer> {

    // Tagesplan einer Reise, chronologisch nach Datum und Uhrzeit.
    List<Aktivitaet> findByReiseOrderByDatumAscUhrzeitAsc(Reise reise);
}
