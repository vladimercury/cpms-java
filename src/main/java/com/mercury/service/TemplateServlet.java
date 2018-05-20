package com.mercury.service;

import com.mercury.dao.impl.ProjectStageTemplateDaoImpl;
import com.mercury.dto.ProjectStageTemplateDTO;
import com.mercury.exception.BadRequestException;
import com.mercury.exception.DataAccessException;
import com.mercury.exception.ForbiddenException;
import com.mercury.exception.NotFoundException;
import com.mercury.model.ProjectStageTemplate;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TemplateServlet extends GenericServlet{
    private static ProjectStageTemplateDaoImpl templateDao = new ProjectStageTemplateDaoImpl();

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        List<ProjectStageTemplate> list = templateDao.getAll();
        List<ProjectStageTemplateDTO> dtos = list.stream().map(ProjectStageTemplateDTO::new).collect(Collectors.toList());
        response.writeJson(dtos);
    }

    @Override
    protected void handlePost(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        if (!request.isUserAdmin()) {
            throw new ForbiddenException("Not an admin");
        }
        if (request.isPutMethod()) {
            Integer id = request.requirePositiveParameterInteger("id");
            String name = request.getParameterTrimmedString("name");
            String description = request.getParameterTrimmedString("description");

            ProjectStageTemplate template = templateDao.get(id);
            if (template == null) {
                throw new NotFoundException("Template not found");
            }
            if (name != null) {
                template.setName(name);
            }
            if (description != null) {
                template.setDescription(description);
            }

            templateDao.update(template);
            response.writeJson(template);
        } else {
            String name = request.requireNotBlankParameterString("name");
            String description = request.getParameterTrimmedString("description");

            ProjectStageTemplate template = new ProjectStageTemplate();
            template.setName(name);
            if (description != null) {
                template.setDescription(description);
            }
            templateDao.create(template);
            response.writeJson(template);
        }
    }
}
