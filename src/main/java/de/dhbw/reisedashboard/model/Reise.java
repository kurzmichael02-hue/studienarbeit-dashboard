package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "reise")
public class Reise {

    // Primärschlüssel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reiseID;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BenutzerID", nullable = false)
    private Benutzer benutzer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpracheID", nullable = false)
    private Sprache sprache;


    //Man könnte noch die umgekehrte Beziehung mit reinnehmen (OneToMany)

    @Column(nullable = false)
    private LocalDate startdatum;

    @Column(nullable = false)
    private LocalDate enddatum;

    @Column(nullable = false)
    private String ziel;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal budget;

    @Column(nullable = false)
    private String land;

    public Reise() {

    }

    public Reise(
        Benutzer benutzer, 
        Sprache sprache, 
        String ziel, 
        BigDecimal budget, 
        String land, 
        LocalDate startdatum, 
        LocalDate enddatum) 
        {
        this.benutzer = benutzer;
        this.sprache = sprache;
        this.ziel = ziel;
        this.budget = budget;
        this.land = land;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
    }

    public Integer getReiseID() {
        return reiseID;
    }
    // Setter brauchen wir nicht, wird automatisch generiert beim Primary Key

    public Benutzer getBenutzer() {
        return benutzer;
    }
    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Sprache getSprache() {
        return sprache;
    }
    public void setSprache(Sprache sprache) {
        this.sprache = sprache;
    }

    public LocalDate getStartdatum() {
        return startdatum;
    }
    public void setStartdatum(LocalDate startdatum) {
        this.startdatum = startdatum;
    }

    public LocalDate getEnddatum() {
        return enddatum;
    }
    public void setEnddatum(LocalDate enddatum) {
        this.enddatum = enddatum;
    }

    public String getZiel() {
        return ziel;
    }
    public void setZiel(String ziel) {
        this.ziel = ziel;
    }

    public BigDecimal getBudget() {
        return budget;
    }
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getLand() {
        return land;
    }
    public void setLand(String land) {
        this.land = land;
    }
}
