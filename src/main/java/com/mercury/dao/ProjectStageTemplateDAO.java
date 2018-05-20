package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.ProjectStageTemplate;

public interface ProjectStageTemplateDAO extends GenericDAO<ProjectStageTemplate, Integer>{
    public ProjectStageTemplate get(int id) throws DataAccessException;
}
