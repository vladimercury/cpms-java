package com.mercury.dto;

import com.mercury.model.User;

public class UserDTO {
    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private boolean isAdmin;
    private EmployeeInfoDTO info;

    public UserDTO() {

    }

    public UserDTO(int id, String login, String firstName, String lastName, String middleName, boolean isAdmin,
                   EmployeeInfoDTO info) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.isAdmin = isAdmin;
        this.info = info;
    }

    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.login = user.getLogin();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.middleName = user.getMiddleName();
            this.isAdmin = user.isAdmin();
            if (user.getInfo() != null) {
                this.info = new EmployeeInfoDTO(user.getInfo());
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public EmployeeInfoDTO getInfo() {
        return info;
    }

    public void setInfo(EmployeeInfoDTO info) {
        this.info = info;
    }
}
