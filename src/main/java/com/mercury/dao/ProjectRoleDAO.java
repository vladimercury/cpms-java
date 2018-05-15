package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.ProjectRole;

public interface ProjectRoleDAO extends GenericDAO<ProjectRole, Integer>{
    ProjectRole get(int id) throws DataAccessException;
}
