package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Role;
import vn.iotstar.services.IRoleService;
import vn.iotstar.services.implement.RoleService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/roles", "/admin/role/add", "/admin/role/insert",
		"/admin/role/edit", "/admin/role/update", "/admin/role/delete", "/admin/role/search" })
public class RoleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IRoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("roles")) {
			List<Role> list = roleService.findAll();
			req.setAttribute("listrole", list);
			req.getRequestDispatcher("/views/admin/role-list.jsp").forward(req, resp);
		} else if (url.contains("search")){ 
			String keyword = req.getParameter("keyword");
			List<Role> list = roleService.findByRoleName(keyword);
			req.setAttribute("keyword", keyword);
			req.setAttribute("listrole", list);
			req.getRequestDispatcher("/views/admin/role-list.jsp").forward(req, resp);
		}else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/role-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Role role = roleService.findByRoleId(id);
			req.setAttribute("role", role);
			req.getRequestDispatcher("/views/admin/role-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				roleService.delete(id);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/roles");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("admin/role/insert")) {
			Role role = new Role();
			String roleName = req.getParameter("roleName");
			role.setRoleName(roleName);
			roleService.insert(role);
			resp.sendRedirect(req.getContextPath() + "/admin/roles");
		} else if (url.contains("/admin/role/update")) {
			Role role = new Role();
			String roleName = req.getParameter("roleName");
			int roleId = Integer.parseInt(req.getParameter("roleId"));
			role.setRoleId(roleId);
			role.setRoleName(roleName);
			roleService.update(role);
			resp.sendRedirect(req.getContextPath() + "/admin/roles");
		}
	}
}
