package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.implement.VideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;

public class VideoService implements IVideoService{

	IVideoDao videoDao = new VideoDao();
	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videoDao.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByVideoTitle(String title) {
		return videoDao.findByVideoTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public Video findById(int videoId) {
		return videoDao.findById(videoId);
	}

	@Override
	public void delete(int videoId) throws Exception {
		videoDao.delete(videoId);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);
	}

}
