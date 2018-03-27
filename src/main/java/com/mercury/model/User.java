package com.mercury.model;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "User", schema = "", catalog = "dream_team_cpms")
public class User implements Serializable {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private boolean isAdmin;
    private EmployeeInfo info;

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
    @Column(name = "Login", nullable = false, insertable = true, updatable = true, unique = true, length = 255)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Password", nullable = false, insertable = true, updatable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "FirstName", nullable = false, insertable = true, updatable = true, length = 32)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = false, insertable = true, updatable = true, length = 32)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "MiddleName", nullable = true, insertable = true, updatable = true, length = 32)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "IsAdmin", nullable = false, insertable = true, updatable = true, columnDefinition = "int")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @OneToOne
    @JoinColumn(name = "EmployeeInfoId")
    public EmployeeInfo getInfo() {
        return info;
    }

    public void setInfo(EmployeeInfo info) {
        this.info = info;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User that = (User) o;

        if (id != that.id)
            return false;
        if (!StringUtils.equals(login, that.login))
            return false;
        if (!StringUtils.equals(password, that.password))
            return false;
        if (!StringUtils.equals(firstName, that.firstName))
            return false;
        if (!StringUtils.equals(lastName, that.lastName))
            return false;
        if (!StringUtils.equals(middleName, that.middleName))
            return false;
        return isAdmin == that.isAdmin;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);

        return 31 * result;
    }
}
