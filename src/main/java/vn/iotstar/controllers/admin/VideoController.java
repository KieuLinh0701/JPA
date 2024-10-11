package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.implement.CategoryService;
import vn.iotstar.services.implement.VideoService;

import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert",
		"/admin/video/edit", "/admin/video/update", "/admin/video/delete", "/admin/video/search" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IVideoService videoService = new VideoService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Video video = videoService.findById(id);
			req.setAttribute("vid", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				videoService.delete(id);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("admin/video/insert")) {
			Video video = new Video();
			ICategoryService cateService = new CategoryService();
			String title = req.getParameter("title");
			int active = Integer.parseInt(req.getParameter("active"));
			String description = req.getParameter("description");
			int categoryId = Integer.parseInt(req.getParameter("categoryId"));
			video.setTitle(title);
			video.setActive(active);
			video.setDescription(description);
			video.setViews(0);
			video.setCategory(cateService.findById(categoryId));

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload File
					part.write(uploadPath + File.separator + fname);
					// Ghi tên file vào data
					video.setPoster(fname);
				} else {
					video.setPoster("1234.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("/admin/video/update")) {
			Video video = new Video();
			String title = req.getParameter("title");
			int active = Integer.parseInt(req.getParameter("active"));
			String description = req.getParameter("description");
			int categoryId = Integer.parseInt(req.getParameter("categoryId"));
			int videoId = Integer.parseInt(req.getParameter("videoId"));
			video.setVideoId(videoId);
			video.setTitle(title);
			ICategoryService cateService = new CategoryService();
			
			video.setCategory(cateService.findById(categoryId));
			
			video.setActive(active);
			video.setDescription(description);
			
			// Lưu hình ảnh cũ
			Video oldVideo = videoService.findById(videoId);
			String fileOld = oldVideo.getPoster();
			video.setViews(oldVideo.getViews());
			
			//Xử lý images
			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload File
					part.write(uploadPath + File.separator + fname);
					// Ghi tên file vào data
					video.setPoster(fname);
				} else {
					video.setPoster(fileOld);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}
}
