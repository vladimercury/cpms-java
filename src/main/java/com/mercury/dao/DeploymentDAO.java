package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.Deployment;

import java.util.List;

public interface DeploymentDAO extends GenericDAO<Deployment, Integer> {
    Deployment get(int id) throws DataAccessException;

    List<Deployment> getForProjectStage(int stageId) throws DataAccessException;
}
