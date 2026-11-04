package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;

@Entity 
@Table(name = "packliste")


public class Packliste {
    
    // Primärschlüssel
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packeintragID;

    // Fremdschlüssel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReiseID", nullable = false)
    private Reise reise;

    @Column(nullable = false)
    private String bezeichnung;

    @Column(nullable = false)
    private boolean erledigt;

    public Packliste(){

    }

    public Packliste(Reise reise, String bezeichnung, boolean erledigt){
        this.reise = reise;
        this.bezeichnung = bezeichnung;
        this.erledigt = erledigt;
    }

    public Integer getPackeintragID() {
        return packeintragID;
    }
    
    //Setter beim Primary Key nicht notwendig
    
    public Reise getReise() {
        return reise;
    }
    public void setReise(Reise reise){
        this.reise = reise;
    }

    public String getBezeichnung(){
        return bezeichnung;
    }
    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

    public boolean getErledigt(){
        return erledigt;
    }
    public void setErledigt(boolean erledigt){
        this.erledigt = erledigt;
    }


}
