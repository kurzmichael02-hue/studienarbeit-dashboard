package de.dhbw.reisedashboard.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity 
@Table(name = "notizen")

public class Notizen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notizID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BenutzerID", nullable = false)
    private Benutzer benutzer;

    @Column(nullable = false)
    private String inhalt;

    @Column(name = "erstellt_am", nullable = false, updatable = false)
    private LocalDateTime erstelltAm;

    @PrePersist 
    public void vordemSpeichern() {
        if(erstelltAm == null) {
            erstelltAm = LocalDateTime.now();
        }
    }

    public Notizen(){

    }

    public Notizen(Benutzer benutzer, String inhalt){
        this.benutzer = benutzer;
        this.inhalt = inhalt;
    }

    public Integer getNotizID(){
        return notizID;
    }

    public Benutzer getBenutzer(){
        return benutzer;
    }
    public void setBenutzer(Benutzer benutzer){
        this.benutzer = benutzer;
    }

    public String getInhalt(){
        return inhalt;
    }
    public void setInhalt(String inhalt){
        this.inhalt = inhalt;
    }

    public LocalDateTime getErstelltAm(){
        return erstelltAm;
    }
    /* Unnötig, da Notiz automatisch erstellt
    public void setErstelltAm(LocalDateTime erstelltAm){
        this.erstelltAm = erstelltAm;
    }
    */

}
