package com.mercury.model;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@Table(name = "EmployeePosition", schema = "", catalog = "dream_team_cpms")
public class EmployeePosition implements Serializable {
    public static Comparator<EmployeePosition> positionComparator = new Comparator<EmployeePosition>() {
        @Override
        public int compare(EmployeePosition o1, EmployeePosition o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    private int id;
    private String name;
    private String description;

    public EmployeePosition() {
    }

    public EmployeePosition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, insertable = true, updatable = true, unique = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Description", nullable = true, insertable = true, updatable = true)
    @Type(type = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EmployeePosition that = (EmployeePosition) o;

        if (id != that.id)
            return false;
        if (!StringUtils.equals(name, that.name))
            return false;
        if (!StringUtils.equals(description, that.description))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);

        return result;
    }

}
