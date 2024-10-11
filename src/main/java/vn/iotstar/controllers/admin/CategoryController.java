package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.implement.CategoryService;
import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("search")){ 
			String keyword = req.getParameter("keyword");
			List<Category> list = cateService.findByCategoryName(keyword);
			req.setAttribute("keyword", keyword);
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("admin/category/insert")) {
			Category category = new Category();
			String categoryName = req.getParameter("categoryName");
			String status = req.getParameter("status");
			int statusInt = Integer.parseInt(status);
			category.setCategoryName(categoryName);
			category.setStatus(statusInt);

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
					category.setImages(fname);
				} else {
					category.setImages("1234.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("/admin/category/update")) {
			Category category = new Category();
			String categoryName = req.getParameter("categoryName");
			String status = req.getParameter("status");
			int statusInt = Integer.parseInt(status);
			int categoryId = Integer.parseInt(req.getParameter("categoryId"));
			category.setCategoryId(categoryId);
			category.setCategoryName(categoryName);
			category.setStatus(statusInt);
			
			// Lưu hình ảnh cũ
			Category oldCate = cateService.findById(categoryId);
			String fileOld = oldCate.getImages();
			
			//Xử lý images
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
					category.setImages(fname);
				} else {
					category.setImages(fileOld);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
