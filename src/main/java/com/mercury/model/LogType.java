package com.mercury.model;

import javax.persistence.*;

@Entity
@Table(name = "LogType", schema = "", catalog = "dream_team_cpms")
public class LogType {
    private int id;
    private String slug;
    private String name;

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Slug", nullable = false, length = 64, unique = true)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Basic
    @Column(name = "Name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
