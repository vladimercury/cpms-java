package com.mercury.service;

import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dto.UserDTO;
import com.mercury.exception.DataAccessException;
import com.mercury.model.User;
import com.mercury.util.Routes;
import com.mercury.util.SessionWrapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginServlet extends HttpServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionWrapper sessionWrapper = new SessionWrapper(request.getSession());

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (StringUtils.isNoneEmpty(login, password)) {
            try {
                User user = userDao.authenticate(login, password);
                if (user != null) {
                    sessionWrapper.setLoggedUserId(user.getId());
                    UserDTO userDTO = new UserDTO(user);
                    userDTO.setOwnUser(true);
                    request.setAttribute("user", userDTO);
                    response.sendRedirect(Routes.STARTUP);
                    return;
                }
            } catch (DataAccessException | InvalidKeySpecException | NoSuchAlgorithmException e) {

            }
        }
        request.setAttribute("error", true);
        request.getRequestDispatcher(Routes.LOGIN_JSP).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Routes.LOGIN_JSP).forward(request, response);
    }
}
