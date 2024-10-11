package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoService {
	
		int count();

		List<Video> findAll(int page, int pagesize);
		
		List<Video> findByVideoTitle(String title);

		List<Video> findAll();

		Video findById(int videoId);

		void delete(int videoId) throws Exception;

		void update(Video video);

		void insert(Video video);
}
