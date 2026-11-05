package de.dhbw.reisedashboard.model;


import java.time.LocalDate;

import java.time.LocalTime;


import jakarta.persistence.*;

@Entity
@Table(name = "aktivität")


public class Aktivitaet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aktivitaetID;

    //Fremdschlüssel von Reise ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReiseID", nullable = false)
    private Reise reise;

    @Column(nullable = false)
    private LocalDate datum;

    @Column(nullable = false)
    private LocalTime uhrzeit;

    @Column(nullable = false)
    private String titel;

    public Aktivitaet(){

    }

    public Aktivitaet(Reise reise, LocalDate datum, LocalTime uhrzeit, String titel){
        this.reise = reise;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.titel = titel;
    }

    public Integer getAktivitaetID(){
        return aktivitaetID;
    }

    public Reise getReise(){
        return reise;
    }
    public void setReise(Reise reise){
        this.reise = reise;
    }

    public LocalDate getDatum(){
        return datum;
    }
    public void setDatum(LocalDate datum){
        this.datum = datum;
    }

    public LocalTime getUhrzeit(){
        return uhrzeit;
    }
    public void setUhrzeit(LocalTime uhrzeit){
        this.uhrzeit = uhrzeit;
    }

    public String getTitel(){
        return titel;
    }
    public void setTitel(String titel){
        this.titel = titel;
    }
}
