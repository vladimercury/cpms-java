package com.mercury.dto;

import com.mercury.model.Deployment;

public class DeploymentDTO {
    private int id;
    private String name;
    private String version;
    private String url;
    private String description;
    private boolean removed;

    public DeploymentDTO() {

    }

    public DeploymentDTO(Deployment deployment) {
        if (deployment != null) {
            this.id = deployment.getId();
            this.name = deployment.getName();
            this.version = deployment.getVersion();
            this.url = deployment.getUrl();
            this.description = deployment.getDescription();
            this.removed = deployment.isRemoved();
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
