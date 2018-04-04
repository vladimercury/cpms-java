package com.mercury.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserToProject", schema = "", catalog = "dream_team_cpms")
public class UserToProject implements Serializable {
    private User user;
    private Project project;
    private ProjectRole role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProjectId")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RoleId")
    public ProjectRole getRole() {
        return role;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }
}
