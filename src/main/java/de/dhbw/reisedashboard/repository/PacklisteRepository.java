package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Packliste;
import de.dhbw.reisedashboard.model.Reise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Datenzugriff auf die Packliste einer Reise.
 */
public interface PacklisteRepository extends JpaRepository<Packliste, Integer> {

    // Packliste einer Reise in der Reihenfolge, in der die Eintraege angelegt wurden.
    List<Packliste> findByReiseOrderByPackeintragIDAsc(Reise reise);

    // Ein Packeintrag, aber nur innerhalb der angegebenen Reise. Verhindert, dass ueber die URL
    // der Eintrag einer fremden Reise abgehakt wird.
    Optional<Packliste> findByPackeintragIDAndReise(Integer packeintragID, Reise reise);
}
