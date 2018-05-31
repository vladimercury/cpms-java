package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.ProjectRole;
import com.mercury.model.UserToProject;

import java.util.List;

public interface UserToProjectDAO extends GenericDAO<UserToProject, Integer>{
    List<UserToProject> doFilter(Integer userId, Integer projectId, Integer roleId) throws DataAccessException;

    UserToProject get(Integer userId, Integer projectId) throws DataAccessException;
}
