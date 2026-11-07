package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;

@Entity 
@Table(name = "lektion")

public class Lektion {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lektionID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpracheID", nullable = false)
    private Sprache sprache;

    @Column(nullable = false)
    private String name;

    public Lektion(){

    }

    public Lektion(Sprache sprache, String name){
        this.sprache = sprache;
        this.name = name;
    }

    public Integer getLektionID(){
        return lektionID;
    }

    public Sprache getSprache(){
        return sprache;
    }
    public void setSprache(Sprache sprache){
        this.sprache = sprache;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
