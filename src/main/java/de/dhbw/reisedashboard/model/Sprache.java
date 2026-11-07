package de.dhbw.reisedashboard.model;

import jakarta.persistence.*;

@Entity 
@Table (name = "sprache")

public class Sprache {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spracheID;

    @Column(nullable = false)
    private String name;

    public Sprache(){

    }

    public Sprache(String name){
        this.name = name;
    }

    public Integer getSpracheID(){
        return spracheID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
