package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.microsoft.sqlserver.jdbc.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IRoleService;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.implement.CategoryService;
import vn.iotstar.services.implement.RoleService;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.services.implement.VideoService;

import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/users", "/admin/user/add", "/admin/user/insert", "/admin/user/edit",
		"/admin/user/update", "/admin/user/delete", "/admin/user/search" })
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("users")) {
			List<User> list = userService.findAll();
			req.setAttribute("listuser", list);
			req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
		} else if (url.contains("search")) {
			String keyword = req.getParameter("keyword");
			List<User> list = userService.findByUsername(keyword);
			req.setAttribute("keyword", keyword);
			req.setAttribute("listuser", list);
			req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/user-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User user = userService.findById(id);
			req.setAttribute("user", user);
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				userService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("admin/user/insert")) {
			User user = new User();
			IRoleService roleService = new RoleService();

			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			int roleId = Integer.parseInt(req.getParameter("roleId"));

			user.setUsername(username);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setEmail(email);
			user.setPhone(phone);
			user.setRole(roleService.findByRoleId(roleId));
			user.setCreateDate(LocalDateTime.now());

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload File
					part.write(uploadPath + File.separator + fname);
					// Ghi tên file vào data
					user.setImages(fname);
				} else {
					user.setImages("1234.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			userService.insert(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		} else if (url.contains("/admin/user/update")) {
			User user = new User();
			IRoleService roleService = new RoleService();

			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			int roleId = Integer.parseInt(req.getParameter("roleId"));
			int userId = Integer.parseInt(req.getParameter("userId"));

			user.setUsername(username);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setEmail(email);
			user.setPhone(phone);
			user.setRole(roleService.findByRoleId(roleId));
			user.setUserId(userId);

			// Lưu hình ảnh cũ
			User oldUser = userService.findById(userId);
			String fileOld = oldUser.getImages();
			user.setCreateDate(oldUser.getCreateDate());

			// Xử lý images
			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload File
					part.write(uploadPath + File.separator + fname);
					// Ghi tên file vào data
					user.setImages(fname);
				} else {
					user.setImages(fileOld);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			userService.update(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}
}
