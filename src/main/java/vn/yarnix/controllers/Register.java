package vn.yarnix.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.yarnix.services.IUserService;
import vn.yarnix.services.impl.UserService;

import java.io.IOException;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user-name");
		String fullname = request.getParameter("user-fullname");
		String password = request.getParameter("user-pass");
		String email = request.getParameter("user-email");
		String phone = request.getParameter("user-phone");
		if(username.isBlank() || password.isBlank() || fullname.isBlank()) {
			request.setAttribute("alert", "Tài khoản hoặc mật khẩu hoặc họ tên không được rỗng");
			doGet(request, response);
			return;
		}
		
		IUserService service = new UserService();
		
		if (service.checkExistUsername(username)) {
			request.setAttribute("alert", "Tài khoản đã tồn tại!");
			doGet(request, response);
			return;
		}

		if (service.register(email, password, username, fullname, phone)) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			request.setAttribute("alert", "Lỗi! Đăng ký tài khoản không thành công!");
			doGet(request, response);
			return;
		}
	}

}
