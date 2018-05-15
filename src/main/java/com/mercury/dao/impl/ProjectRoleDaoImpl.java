package com.mercury.dao.impl;

import com.mercury.dao.ProjectRoleDAO;
import com.mercury.dao.util.HibernateUtil;
import com.mercury.exception.DataAccessException;
import com.mercury.model.ProjectRole;

import java.util.List;

@SuppressWarnings("unchecked")
public class ProjectRoleDaoImpl implements ProjectRoleDAO {

    @Override
    public ProjectRole get(int id) throws DataAccessException {
        return (ProjectRole) HibernateUtil.doGetById(ProjectRole.class, id);
    }

    @Override
    public List<ProjectRole> getAll() throws DataAccessException {
        return (List<ProjectRole>) HibernateUtil.doGetAll(ProjectRole.class);
    }

    @Override
    public ProjectRole update(ProjectRole updateEntity) throws DataAccessException {
        return (ProjectRole) HibernateUtil.doUpdate(updateEntity);
    }

    @Override
    public void delete(ProjectRole entity) throws DataAccessException {
        HibernateUtil.doDelete(entity);
    }

    @Override
    public void create(ProjectRole entity) throws DataAccessException {
        HibernateUtil.doCreate(entity);
    }

    @Override
    public void createOrUpdate(ProjectRole entity) throws DataAccessException {
        HibernateUtil.doCreateOrUpdate(entity);
    }
}
