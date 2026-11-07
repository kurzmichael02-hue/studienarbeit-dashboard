package de.dhbw.reisedashboard.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@Table(name = "lernfortschritt", uniqueConstraints = {@UniqueConstraint(columnNames = {"BenutzerID", "VokabelID"})})

public class Lernfortschritt {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lernfortschrittID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BenutzerID", nullable = false)
    private Benutzer benutzer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VokabelID", nullable = false)
    private Vokabel vokabel;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer wiederholungen;

    @Column(nullable = false)
    private LocalDateTime letzteUebung;

    @PrePersist 
    public void vordemSpeichern(){
        letzteUebung = LocalDateTime.now();
    }
    @PreUpdate 
    public void vordemAktualisieren(){
        letzteUebung = LocalDateTime.now();
    }

    
    public Lernfortschritt(){

    }

    public Lernfortschritt(Benutzer benutzer, Vokabel vokabel, String status, Integer wiederholungen, LocalDateTime letzteUebung){
        this.benutzer = benutzer;
        this.vokabel = vokabel;
        this.status = status;
        this.wiederholungen = wiederholungen;
        this.letzteUebung = letzteUebung;
    }

    public Integer getLernfortschrittID(){
        return lernfortschrittID;
    }

    public Benutzer getBenutzer(){
        return benutzer;
    }
    public void setBenutzer(Benutzer benutzer){
        this.benutzer = benutzer;
    }

    public Vokabel getVokabel(){
        return vokabel;
    }
    public void setVokabel(Vokabel vokabel){
        this.vokabel = vokabel;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public Integer getWiederholungen(){
        return wiederholungen;
    }
    public void setWiederholungen(Integer wiederholungen){
        this.wiederholungen = wiederholungen;
    }

    public LocalDateTime getLetzteUebung(){
        return letzteUebung;
    }
    public void setLetzteUebung(LocalDateTime letzteUebung){
        this.letzteUebung = letzteUebung;
    }

}
