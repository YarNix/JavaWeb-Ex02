package vn.yarnix.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import vn.yarnix.services.*;
import vn.yarnix.services.impl.*;

/**
 * Servlet implementation class ForgotPassword
 */
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/forgot-password.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if (email.isBlank())
		{
			request.setAttribute("alert", "Email không được để trống!");
			doGet(request, response);
			return;
		}
		
		IUserService usr_service = new UserService();
		String resetToken = usr_service.generatePasswordResetToken(email);
		if (!resetToken.isBlank())
		{
			String resetLink = request.getRequestURL().toString().replace("forgot-password", "reset-password") + "?token=" + resetToken;
			IMailService mail_service = new MailService();
			mail_service.send(email, "Password Reset", "Click here to reset: " + resetLink);			
		}
		request.setAttribute("info", "Link để reset mật khẩu đã được gửi vào email của bạn.");
		doGet(request, response);
	}

}
