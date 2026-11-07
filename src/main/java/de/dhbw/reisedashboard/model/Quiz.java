package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity 
@Table(name = "quiz")

public class Quiz {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BenutzerID", nullable = false)
    private Benutzer benutzer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LektionID", nullable = false)
    private Lektion lektion;

    @Column(nullable = false, updatable = false)
    private LocalDate datum;

    @PrePersist
    public void vorDemSpeichern() {
        if(datum == null) {
            datum = LocalDate.now();
        }
    }

    @Column(nullable = false)
    private Integer punkte;

    @Column(nullable = false)
    private Integer anzahlFragen;

    public Quiz(){

    }

    public Quiz(Benutzer benutzer, Lektion lektion, Integer punkte, Integer anzahlFragen){
        this.benutzer = benutzer;
        this.lektion = lektion;
        this.punkte = punkte;
        this.anzahlFragen = anzahlFragen;
    }

    public Integer getQuizID(){
        return quizID;
    }

    public Benutzer getBenutzer(){
        return benutzer;
    }
    public void setBenutzer(Benutzer benutzer){
        this.benutzer = benutzer;
    }

    public Lektion getLektion(){
        return lektion;
    }
    public void setLektion(Lektion lektion){
        this.lektion = lektion;
    }

    public LocalDate getDatum(){
        return datum;
    }
    /* siehe Notizen.java
    public void setDatum(LocalDate datum){
        this.datum = datum;
    }
    */
    public Integer getPunkte(){
        return punkte;
    }
    public void setPunkte(Integer punkte){
        this.punkte = punkte;
    }

    public Integer getAnzahlFragen(){
        return anzahlFragen;
    }
    public void setAnzahlFragen(Integer anzahlFragen){
        this.anzahlFragen = anzahlFragen;
    }
    
}
