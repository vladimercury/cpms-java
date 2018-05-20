package com.mercury.dao.impl;

import com.mercury.dao.ProjectStageTemplateDAO;
import com.mercury.dao.util.HibernateUtil;
import com.mercury.exception.DataAccessException;
import com.mercury.model.Project;
import com.mercury.model.ProjectStageTemplate;

import java.util.List;

@SuppressWarnings("unchecked")
public class ProjectStageTemplateDaoImpl implements ProjectStageTemplateDAO {
    @Override
    public ProjectStageTemplate get(int id) throws DataAccessException {
        return (ProjectStageTemplate) HibernateUtil.doGetById(ProjectStageTemplate.class, id);
    }

    @Override
    public List<ProjectStageTemplate> getAll() throws DataAccessException {
        return (List<ProjectStageTemplate>) HibernateUtil.doGetAll(ProjectStageTemplate.class);
    }

    @Override
    public ProjectStageTemplate update(ProjectStageTemplate updateEntity) throws DataAccessException {
        return (ProjectStageTemplate) HibernateUtil.doUpdate(updateEntity);
    }

    @Override
    public void delete(ProjectStageTemplate entity) throws DataAccessException {
        HibernateUtil.doDelete(entity);
    }

    @Override
    public void create(ProjectStageTemplate entity) throws DataAccessException {
        HibernateUtil.doCreate(entity);
    }

    @Override
    public void createOrUpdate(ProjectStageTemplate entity) throws DataAccessException {
        HibernateUtil.doCreateOrUpdate(entity);
    }
}
