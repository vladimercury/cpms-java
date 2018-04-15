package com.mercury.dto;

import com.mercury.model.Project;

public class ProjectDTO {
    private int id;
    private String name;
    private String description;
    private boolean active;
    private int priority;
    private ProjectTypeDTO projectType;

    public ProjectDTO() {

    }

    public ProjectDTO(int id, String name, String description, boolean active, int priority, ProjectTypeDTO type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.priority = priority;
        if (type != null) {
            this.projectType = type;
        }
    }

    public ProjectDTO(Project project) {
        if (project != null) {
            this.id = project.getId();
            this.name = project.getName();
            this.description = project.getDescription();
            this.active = project.isActive();
            this.priority = project.getPriority();
            if (project.getProjectType() != null) {
                ProjectTypeDTO projectTypeDTO = new ProjectTypeDTO(project.getProjectType());
                this.projectType = projectTypeDTO;
            }
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ProjectTypeDTO getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectTypeDTO projectType) {
        this.projectType = projectType;
    }
}
