package com.mercury.service;

import com.mercury.dao.impl.ProjectRoleDaoImpl;
import com.mercury.dto.ProjectRoleDTO;
import com.mercury.exception.BadRequestException;
import com.mercury.exception.DataAccessException;
import com.mercury.exception.ForbiddenException;
import com.mercury.exception.NotFoundException;
import com.mercury.model.ProjectRole;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RoleServlet extends GenericServlet {
    private static ProjectRoleDaoImpl roleDao = new ProjectRoleDaoImpl();

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        Integer id =  request.getParameterPositiveInteger("id");
        if (id == null) {
            List<ProjectRoleDTO> list = roleDao.getAll().stream().map(ProjectRoleDTO::new).collect(Collectors.toList());
            response.writeJson(list);
        } else {
            ProjectRole role = roleDao.get(id);
            if (role == null) {
                throw new NotFoundException("Role with id " + id + " not found");
            }
            response.writeJson(new ProjectRoleDTO(role));
        }
    }
}
