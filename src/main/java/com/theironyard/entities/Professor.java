package com.theironyard.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jeff on 8/8/16.
 */
@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    public Professor(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
