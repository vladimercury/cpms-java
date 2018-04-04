package com.mercury.dto;

import com.mercury.model.Message;
import com.mercury.model.User;
import com.mercury.util.DateUtil;
import org.hibernate.Hibernate;

import java.util.Date;

public class MessageDTO {
    private int id;
    private String content;
    private String creationDate;
    private boolean unread;

    private UserDTO author;

    public MessageDTO() {

    }

    public MessageDTO(int id, String content, String creationDate, boolean unread) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.unread = unread;
    }

    public MessageDTO(Message entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.creationDate = DateUtil.format(entity.getCreationDate());
        this.unread = entity.isUnread();
        User author = entity.getAuthor();
        if (author != null && Hibernate.isInitialized(author)) {
            this.author = new UserDTO(author);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
