package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;

@Entity 
@Table(name = "benutzer")

public class Benutzer {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer benutzerID;

    // Viele OneToMany Beziehungen, vll später implementieren

    @Column(nullable = false)
    private String vorname;

    @Column(nullable = false)
    private String nachname;

    @Column(nullable = false)
    private Integer rang;

    public Benutzer(){

    }

    public Benutzer(String vorname, String nachname, Integer rang){
        this.vorname = vorname;
        this.nachname = nachname;
        this.rang = rang;
    }

    public Integer getBenutzerID(){
        return benutzerID;
    }

    public String getVorname(){
        return vorname;
    }
    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    public String getNachname(){
        return nachname;
    }
    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    public Integer getRang(){
        return rang;
    }
    public void setRang(Integer rang){
        this.rang = rang;
    }


}
