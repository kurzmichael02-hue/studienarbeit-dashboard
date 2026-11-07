package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;

@Entity 
@Table(name = "vokabel")

public class Vokabel {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vokabelID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LektionID", nullable = false)
    private Lektion lektion;

    @Column(nullable = false)
    private String deutsch;

    @Column(nullable = false)
    private String uebersetzung;

    @Column(nullable = false)
    private String transkription;

    public Vokabel(){

    }

    public Vokabel(Lektion lektion, String deutsch, String uebersetzung, String transkription){
        this.lektion = lektion;
        this.deutsch = deutsch;
        this.uebersetzung = uebersetzung;
        this.transkription = transkription;
    }

    public Integer getVokabelID(){
        return vokabelID;
    }

    public Lektion getLektion(){
        return lektion;
    }
    public void setLektion(Lektion lektion){
        this.lektion = lektion;
    }

    public String getDeutsch(){
        return deutsch;
    }
    public void setDeutsch(String deutsch){
        this.deutsch = deutsch;
    }

    public String getUebersetzung(){
        return uebersetzung;
    }
    public void setUebersetzung(String uebersetzung){
        this.uebersetzung = uebersetzung;
    }

    public String getTranskription(){
        return transkription;
    }
    public void setTranskription(String transkription){
        this.transkription = transkription;
    }
}
