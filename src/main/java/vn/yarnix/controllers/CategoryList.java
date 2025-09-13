package vn.yarnix.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.yarnix.models.UserModel;
import vn.yarnix.services.ICategoryService;
import vn.yarnix.services.IUserService;
import vn.yarnix.services.impl.CategoryService;
import vn.yarnix.services.impl.UserService;

import java.io.IOException;

/**
 * Servlet implementation class CategoryList
 */
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryList() {
        super();
        // TODO Auto-generated constructor stub
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
		ICategoryService cat_service = new CategoryService();
		request.setAttribute("user", user);
		request.setAttribute("categories", cat_service.getAll());
		request.setAttribute("isUserAdmin", usr_service.isRole(user.getId(), "ADMIN"));
		request.getRequestDispatcher("/views/category_list.jsp").forward(request, response);
	}

}
