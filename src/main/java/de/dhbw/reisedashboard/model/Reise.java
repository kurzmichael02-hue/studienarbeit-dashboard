package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reise")
public class Reise {

    // Primärschlüssel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reiseID;

    // Fremdschlüssel laut ER-Modell. Solange Benutzer und Sprache noch keine
    // Entities sind, bleiben es einfache Spalten, danach @ManyToOne wie in Packliste.
    @Column(nullable = false)
    private Integer benutzerID;

    private Integer spracheID;

    private LocalDate startdatum;

    private LocalDate enddatum;

    @Column(nullable = false)
    private String ziel;

    private double budget;

    private String land;

    public Reise() {

    }

    public Reise(Integer benutzerID, String ziel, LocalDate startdatum, LocalDate enddatum) {
        this.benutzerID = benutzerID;
        this.ziel = ziel;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
    }

    public Integer getReiseID() {
        return reiseID;
    }
    public void setReiseID(Integer reiseID) {
        this.reiseID = reiseID;
    }

    public Integer getBenutzerID() {
        return benutzerID;
    }
    public void setBenutzerID(Integer benutzerID) {
        this.benutzerID = benutzerID;
    }

    public Integer getSpracheID() {
        return spracheID;
    }
    public void setSpracheID(Integer spracheID) {
        this.spracheID = spracheID;
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

    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getLand() {
        return land;
    }
    public void setLand(String land) {
        this.land = land;
    }
}
