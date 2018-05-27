package com.mercury.service;

import com.mercury.dao.impl.ProjectDaoImpl;
import com.mercury.dao.impl.ProjectLogDaoImpl;
import com.mercury.dto.LogDTO;
import com.mercury.exception.BadRequestException;
import com.mercury.exception.DataAccessException;
import com.mercury.exception.ForbiddenException;
import com.mercury.exception.NotFoundException;
import com.mercury.model.ProjectLog;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LogServlet extends GenericServlet{
    private static ProjectDaoImpl projectDao = new ProjectDaoImpl();
    private static ProjectLogDaoImpl logDao = new ProjectLogDaoImpl();

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        Integer projectId = request.getParameterPositiveInteger("project");
        Integer userId = request.getCurrentUserId();
        List<ProjectLog> log;

        if (projectId != null) {
            if (!request.isUserAdmin() && !projectDao.isMember(userId, projectId)) {
                throw new ForbiddenException("Not a project member or admin");
            }
            log = logDao.getForProject(projectId);
        } else {
            if (!request.isUserAdmin()) {
                throw new ForbiddenException("Not an admin");
            }
            log = logDao.getAll();
        }
        response.writeJson(log.stream().map(LogDTO::new).collect(Collectors.toList()));
    }

    @Override
    protected void handlePost(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        Boolean clear = request.getParameterBoolean("clear");

        if (!request.isUserAdmin()) {
            throw new ForbiddenException("Not an admin");
        }
        if (clear) {
            logDao.clear();
        }
        response.setNoContentStatus();
    }
}
