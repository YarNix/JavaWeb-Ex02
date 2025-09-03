package vn.yarnix.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.yarnix.services.IUserService;
import vn.yarnix.services.impl.UserService;

import java.io.IOException;

/**
 * Servlet implementation class ResetPassword
 */
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resetToken = request.getParameter("token");
		if (resetToken != null && !resetToken.isBlank())
			request.setAttribute("token", resetToken);
		request.getRequestDispatcher("views/reset-password.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resetToken = request.getParameter("reset-token");
		String password = request.getParameter("user-pass");
		
		IUserService usr_service = new UserService();
		usr_service.resetPassword(resetToken, password);
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
