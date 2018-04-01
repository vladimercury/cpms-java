package com.mercury.dto;

import com.mercury.model.EmployeeInfo;
import com.mercury.model.EmployeePosition;
import org.hibernate.Hibernate;

public class EmployeeInfoDTO {
    private int id;
    private String description;
    private EmployeePositionDTO position;

    public EmployeeInfoDTO() {

    }

    public EmployeeInfoDTO(int id, String description, EmployeePositionDTO position) {
        this.id = id;
        this.description = description;
        this.position = position;
    }

    public EmployeeInfoDTO(EmployeeInfo entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.description = entity.getDescription();
            EmployeePosition position = entity.getPosition();
            if (position != null && Hibernate.isInitialized(position)) {
                this.position = new EmployeePositionDTO(position);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmployeePositionDTO getPosition() {
        return position;
    }

    public void setPosition(EmployeePositionDTO position) {
        this.position = position;
    }
}
