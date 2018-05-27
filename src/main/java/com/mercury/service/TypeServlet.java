package com.mercury.service;

import com.mercury.dao.impl.ProjectDaoImpl;
import com.mercury.dto.ProjectTypeDTO;
import com.mercury.exception.*;
import com.mercury.model.ProjectType;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TypeServlet extends GenericServlet {
    private static ProjectDaoImpl projectDao = new ProjectDaoImpl();

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException, NotImplementedException {
        Integer id = request.getParameterPositiveInteger("id");

        if (id != null) {
            ProjectType type = projectDao.getProjectType(id);
            if (type == null) {
                throw new NotFoundException("Project type not found");
            }
            response.writeJson(new ProjectTypeDTO(type));
        } else {
            List<ProjectType> typeList = projectDao.getAllTypes();
            response.writeJson(typeList.stream().map(ProjectTypeDTO::new).collect(Collectors.toList()));
        }
    }
}
