package com.mercury.dto.extra;

import com.mercury.dto.ProjectDTO;
import com.mercury.model.Project;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectListDTO {
    private List<ProjectDTO> my;
    private List<ProjectDTO> all;

    public ProjectListDTO() {

    }

    public ProjectListDTO(List<ProjectDTO> my, List<ProjectDTO> all) {
        setMy(my);
        setAll(all);
    }

    public List<ProjectDTO> getMy() {
        return my;
    }

    public void setMy(List<ProjectDTO> my) {
        this.my = my;
    }

    public List<ProjectDTO> getAll() {
        return all;
    }

    public void setAll(List<ProjectDTO> all) {
        this.all = all;
    }
}
