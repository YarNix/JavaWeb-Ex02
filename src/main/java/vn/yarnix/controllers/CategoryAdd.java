package vn.yarnix.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.yarnix.models.CategoryModel;
import vn.yarnix.models.UserModel;
import vn.yarnix.services.ICategoryService;
import vn.yarnix.services.IUserService;
import vn.yarnix.services.impl.CategoryService;
import vn.yarnix.services.impl.UserService;

import java.io.IOException;

/**
 * Servlet implementation class CategoryAdd
 */
public class CategoryAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryAdd() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if (session == null || !(session.getAttribute("user") instanceof UserModel))
		{
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.getRequestDispatcher("/views/category_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("user") instanceof UserModel)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserModel user = (UserModel) session.getAttribute("user");
        UserService usrService = new UserService();

        // Check if user is admin
        if (!usrService.isRole(user.getId(), "Admin")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, ".");
            return;
        }

        // Read form data
        String categoryName = request.getParameter("categoryName");
        String icon = request.getParameter("icon");
        String statusStr = request.getParameter("status");

        int status = 1; // default active
        if (statusStr != null) {
            try {
                status = Integer.parseInt(statusStr);
            } catch (NumberFormatException e) {
                status = 1; // fallback
            }
        }

        CategoryModel category = new CategoryModel();
        category.setCategoryName(categoryName);
        category.setIcon(icon);
        category.setStatus(status);

        CategoryService catService = new CategoryService();
        catService.insert(category);

        response.sendRedirect(request.getContextPath() + "/category");
	}
}
