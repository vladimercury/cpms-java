package com.mercury.service;

import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.util.Routes;
import com.mercury.util.SessionWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionWrapper sessionWrapper = new SessionWrapper(request.getSession(false));

        String page = request.getParameter("page");

        if (sessionWrapper.isValid()) {
            if (sessionWrapper.getLoggedUserId() != null) {
                String route = Routes.PROJECTS_JSP;
                if (page != null) {
                    switch (page.toLowerCase()) {
                        case "projects":
                            route = Routes.PROJECTS_JSP;
                            break;
                    }
                }
                request.getRequestDispatcher(route).forward(request, response);
                return;
            }
        }

        response.sendRedirect(Routes.LOGIN_SERVLET);
    }
}
