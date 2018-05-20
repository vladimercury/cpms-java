package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.Project;
import com.mercury.model.ProjectRole;
import com.mercury.model.ProjectType;

import java.util.List;

public interface ProjectDAO extends GenericDAO<Project, Integer> {
    List<Project> getProjectsForUser(int userId) throws DataAccessException;

    Project getProjectWithMembers(int projectId) throws DataAccessException;

    ProjectRole getMemberRole(int userId, int projectId) throws DataAccessException;

    ProjectType getProjectType(int projectTypeId) throws DataAccessException;

    boolean isMember(int userId, int projectId) throws DataAccessException;
}
