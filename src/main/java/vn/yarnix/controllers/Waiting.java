package vn.yarnix.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.yarnix.models.UserModel;
import vn.yarnix.services.IUserService;
import vn.yarnix.services.impl.UserService;

import java.io.IOException;

/**
 * Servlet implementation class Waiting
 */
public class Waiting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Waiting() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || !(session.getAttribute("user") instanceof UserModel))
		{
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		UserModel user = (UserModel)session.getAttribute("user");
		IUserService usr_service = new UserService();
		if (usr_service.isRole(user.getId(), "ADMIN"))
			response.sendRedirect(request.getContextPath() + "/admin/category");
		else
			response.sendRedirect(request.getContextPath() + "/category");
	}

}
