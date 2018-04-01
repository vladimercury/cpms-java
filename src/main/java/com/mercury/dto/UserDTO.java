package com.mercury.dto;

import com.mercury.model.EmployeeInfo;
import com.mercury.model.Message;
import com.mercury.model.User;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private boolean isAdmin;

    private EmployeeInfoDTO info;
    private List<MessageDTO> receivedMessages;

    private boolean ownUser;

    public UserDTO() {

    }

    public UserDTO(int id, String login, String firstName, String lastName, String middleName, boolean isAdmin,
                   EmployeeInfoDTO info, List<MessageDTO> receivedMessages) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.isAdmin = isAdmin;
        this.info = info;
        this.receivedMessages = receivedMessages;
    }

    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.login = user.getLogin();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.middleName = user.getMiddleName();
            this.isAdmin = user.isAdmin();
            EmployeeInfo info = user.getInfo();
            if (info != null && Hibernate.isInitialized(info)) {
                this.info = new EmployeeInfoDTO(info);
            }
            Set<Message> receivedMessages = user.getReceivedMessages();
            if (receivedMessages != null && Hibernate.isInitialized(receivedMessages)) {
                this.receivedMessages = new ArrayList<>();
                for (Message message : receivedMessages) {
                    this.receivedMessages.add(new MessageDTO(message));
                }
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

    public List<MessageDTO> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<MessageDTO> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public boolean isOwnUser() {
        return ownUser;
    }

    public void setOwnUser(boolean ownUser) {
        this.ownUser = ownUser;
    }
}
