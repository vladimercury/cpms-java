package com.mercury.service;

import com.mercury.dao.impl.UserToProjectDaoImpl;
import com.mercury.dto.UserToProjectDTO;
import com.mercury.exception.BadRequestException;
import com.mercury.exception.DataAccessException;
import com.mercury.exception.ForbiddenException;
import com.mercury.exception.NotFoundException;
import com.mercury.model.UserToProject;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MemberServlet extends GenericServlet {
    private static UserToProjectDaoImpl userProjectDao = new UserToProjectDaoImpl();

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        Integer projectId = request.getParameterPositiveInteger("project");
        Integer userId = request.getParameterPositiveInteger("user");
        Integer roleId = request.getParameterPositiveInteger("role");

        if (projectId == null && userId == null && roleId == null) {
            throw new BadRequestException("No filter specified");
        }
        List<UserToProject> list = userProjectDao.doFilter(userId, projectId, roleId);
        response.writeJson(list.stream().map(UserToProjectDTO::new).collect(Collectors.toList()));
    }
}
