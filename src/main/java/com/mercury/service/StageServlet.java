package com.mercury.service;

import com.mercury.dao.impl.ProjectDaoImpl;
import com.mercury.dao.impl.ProjectStageDaoImpl;
import com.mercury.dao.impl.ProjectStageTemplateDaoImpl;
import com.mercury.dto.ProjectStageDTO;
import com.mercury.exception.BadRequestException;
import com.mercury.exception.DataAccessException;
import com.mercury.exception.ForbiddenException;
import com.mercury.exception.NotFoundException;
import com.mercury.model.ProjectStage;
import com.mercury.util.DataUtils;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StageServlet extends GenericServlet{
    private static ProjectDaoImpl projectDao = new ProjectDaoImpl();
    private static ProjectStageDaoImpl stageDao = new ProjectStageDaoImpl();
    private static ProjectStageTemplateDaoImpl templateDao = new ProjectStageTemplateDaoImpl();

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        Integer id = request.getParameterPositiveInteger("id");
        Integer projectId = request.getParameterPositiveInteger("project");

        Integer userId = request.getCurrentUserId();

        if (id != null) {
            ProjectStage stage = stageDao.get(id);
            if (stage == null) {
                throw new NotFoundException("Stage not found");
            }
            if (!projectDao.isMember(userId, stage.getProjectId())) {
                throw new ForbiddenException("Not a project member");
            }
            response.writeJson(stage);
        } else if (projectId != null) {
            if (!projectDao.isMember(request.getCurrentUserId(), projectId)) {
                throw new ForbiddenException("Not a project member");
            }
            List<ProjectStage> list = stageDao.getForProject(projectId);
            List<ProjectStageDTO> dtos = list.stream().map(ProjectStageDTO::new).collect(Collectors.toList());
            response.writeJson(dtos);
        } else {
            throw new BadRequestException("No ids provided");
        }
    }

    @Override
    protected void handlePost(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        Integer order = request.getParameterNonNegativeInteger("order");
        String start = request.getParameterTrimmedString("start");
        String end = request.getParameterTrimmedString("end");

        if (request.isPutMethod()) {
            Integer id = request.requirePositiveParameterInteger("id");
            ProjectStage stage = stageDao.get(id);
            if (stage == null) {
                throw new NotFoundException("Stage not found");
            }
            if (order != null) {
                stage.setOrder(order);
            }
            if (start != null) {
                stage.setStartDate(DataUtils.parseDate(start));
            }
            if (end != null) {
                stage.setEndDate(DataUtils.parseDate(end));
            }

            stageDao.update(stage);
            response.writeJson(stage);
        } else if (request.isDeleteMethod()) {
            //todo
        } else {
            //todo
        }
    }
}
