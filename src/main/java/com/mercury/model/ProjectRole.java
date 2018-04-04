package com.mercury.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ProjectRole", schema = "", catalog = "dream_team_cpms")
public class ProjectRole implements Serializable {
    private int id;
    private String slug;
    private String name;

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false, insertable = true, updatable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Login", nullable = false, unique = true, length = 64)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Basic
    @Column(name = "Login", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
