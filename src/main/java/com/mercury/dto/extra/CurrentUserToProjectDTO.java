package com.mercury.dto.extra;

import com.mercury.dto.ProjectRoleDTO;
import com.mercury.model.ProjectRole;
import org.apache.commons.lang3.StringUtils;

public class CurrentUserToProjectDTO extends ProjectRoleDTO {
    private boolean member;
    private boolean manager;

    public CurrentUserToProjectDTO() {
        super();
    }

    public CurrentUserToProjectDTO(ProjectRole role) {
        super(role);
        if (role != null) {
            this.member = true;
            this.manager = StringUtils.equalsIgnoreCase(role.getSlug(), ProjectRole.MANAGER);
        }
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
