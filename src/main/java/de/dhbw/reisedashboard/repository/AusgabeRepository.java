package de.dhbw.reisedashboard.repository;

import de.dhbw.reisedashboard.model.Ausgabe;
import de.dhbw.reisedashboard.model.Reise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Datenzugriff auf die Ausgaben einer Reise.
 */
public interface AusgabeRepository extends JpaRepository<Ausgabe, Integer> {

    // Ausgaben einer Reise, die neueste zuerst.
    List<Ausgabe> findByReiseOrderByDatumDesc(Reise reise);

    // Summe aller Ausgaben einer Reise. coalesce liefert 0 statt null, wenn es noch keine gibt,
    // damit die Budget-Rechnung im Service nicht auf null pruefen muss.
    @Query("select coalesce(sum(a.betrag), 0) from Ausgabe a where a.reise = :reise")
    BigDecimal summeBetrag(@Param("reise") Reise reise);
}
