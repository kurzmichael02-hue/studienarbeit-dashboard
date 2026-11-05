package de.dhbw.reisedashboard.model;


import java.time.LocalDateTime;
import java.math.BigDecimal;


import jakarta.persistence.*;

@Entity
@Table(name = "ausgabe")


public class Ausgabe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ausgabeID;

    //Fremdschlüssel von Reise ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReiseID", nullable = false)
    private Reise reise;

    @Column(nullable = false)
    private String kategorie;

    @Column(scale = 2, precision = 12, nullable = false)
    private BigDecimal betrag;

    @Column(nullable = false)
    private LocalDateTime datum;

    public Ausgabe(){

    }

    public Ausgabe(Reise reise, String kategorie, BigDecimal betrag, LocalDateTime datum){
        this.reise = reise;
        this.kategorie = kategorie;
        this.betrag = betrag;
        this.datum = datum;
    }

    public Integer getAusgabeID(){
        return ausgabeID;
    }

    public Reise getReise(){
        return reise;
    }
    public void setReise(Reise reise){
        this.reise = reise;
    }

    public String getKategorie(){
        return kategorie;
    }
    public void setKategorie(String kategorie){
        this.kategorie = kategorie;
    }

    public BigDecimal getBetrag(){
        return betrag;
    }
    public void setBetrag(BigDecimal betrag){
        this.betrag = betrag;
    }

    public LocalDateTime getDatum(){
        return datum;
    }
    public void setDatum(LocalDateTime datum){
        this.datum = datum;
    }
}

