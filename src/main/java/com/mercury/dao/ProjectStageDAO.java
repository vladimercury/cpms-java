package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.ProjectStage;

import java.util.List;

public interface ProjectStageDAO extends GenericDAO<ProjectStage, Integer> {
    ProjectStage get(int id) throws DataAccessException;

    List<ProjectStage> getForProject(int projectId) throws DataAccessException;
}
