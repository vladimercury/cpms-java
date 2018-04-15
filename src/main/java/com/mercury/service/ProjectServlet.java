package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dto.MessageDTO;
import com.mercury.dto.ProjectDTO;
import com.mercury.dto.UserDTO;
import com.mercury.exception.DataAccessException;
import com.mercury.model.Message;
import com.mercury.model.Project;
import com.mercury.model.User;
import com.mercury.model.UserToProject;
import com.mercury.util.Routes;
import com.mercury.util.SessionWrapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectServlet extends HttpServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = new SessionWrapper(req.getSession()).getLoggedUserId();
        User user = null;
        try {
            user = userDao.get(id);
        } catch (DataAccessException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for (UserToProject userToProject : user.getUserToProjects()) {
            Project project = userToProject.getProject();
            ProjectDTO projectDTO = new ProjectDTO(project);
            projectDTOList.add(projectDTO);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("my", projectDTOList);
        data.put("all", new ArrayList<>());
        resp.getWriter().write(new Gson().toJson(data));
    }
}
