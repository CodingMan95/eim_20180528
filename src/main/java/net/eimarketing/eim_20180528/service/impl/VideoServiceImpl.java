package net.eimarketing.eim_20180528.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.eimarketing.eim_20180528.dao.VideoDao;
import net.eimarketing.eim_20180528.entity.TopUser;
import net.eimarketing.eim_20180528.entity.Video;
import net.eimarketing.eim_20180528.entity.VideoTopX;
import net.eimarketing.eim_20180528.service.VideoService;

@Service
@Transactional
public class VideoServiceImpl implements VideoService{

	@Autowired
	private VideoDao videoDao;

	@Override
	public int addVideo(Video video) {
		return videoDao.addVideo(video);
	}

	@Override
	public List<Video> selectAllVideo() {
		return videoDao.selectAllVideo();
	}

	@Override
	public List<Video> selectVideoByUser(String employee_id) {
		return videoDao.selectVideoByUser(employee_id);
	}

	@Override
	public List<Video> selectVideoByArea(String employee_area) {
		return videoDao.selectVideoByArea(employee_area);
	}

	@Override
	public int deleteVideo(Integer video_id) {
		return videoDao.deleteVideo(video_id);
	}

	@Override
	public Video selectVideoById(Integer video_id) {
		return videoDao.selectVideoById(video_id);
	}

	@Override
	public int selectFabulous(Integer video_id) {
		return videoDao.selectFabulous(video_id);
	}

	@Override
	public int updateFabulous(Integer video_id) {
		return videoDao.updateFabulous(video_id);
	}

/*	@Override
	public List<Video> selectTop15Video() {
		// TODO Auto-generated method stub
		return videoDao.selectTop15Video();
	}
*/
	@Override
	public Video selectTopXVideo(int num) {
		return videoDao.selectTopXVideo(num);
	}

	@Override
	public Video selectTopXUrl(int num) {
		return videoDao.selectTopXUrl(num);
	}

	@Override
	public int updateCommentNum(Integer video_id) {
		return videoDao.updateCommentNum(video_id);
	}

	@Override
	public List<Video> selectVideosById(Integer video_id) {
		// TODO Auto-generated method stub
		return videoDao.selectVideosById(video_id);
	}

	@Override
	public Video selectVideoImgById(Integer video_id) {
		// TODO Auto-generated method stub
		return videoDao.selectVideoImgById(video_id);
	}

	@Override
	public Video selectUserById(Integer video_id) {
		// TODO Auto-generated method stub
		return videoDao.selectUserById(video_id);
	}

	@Override
	public int selectNumById(String employee_id) {
		// TODO Auto-generated method stub
		return videoDao.selectNumById(employee_id);
	}

	@Override
	public List<Video> selectVideoUrlByUser(String employee_id) {
		// TODO Auto-generated method stub
		return videoDao.selectVideoUrlByUser(employee_id);
	}

	@Override
	public Video getVideoById(int id) {
		// TODO Auto-generated method stub
		return videoDao.getVideoById(id);
	}


	@Override
	public List<Video> selectTopUser() {
		// TODO Auto-generated method stub
		return videoDao.selectTopUser();
	}

	@Override
	public List<Video> selectLimit(TopUser top) {
		// TODO Auto-generated method stub
		return videoDao.selectLimit(top);
	}

//	@Override
//	public List<Video> selectLimit(String employee_id, Integer begin) {
//		// TODO Auto-generated method stub
//		return videoDao.selectLimit(employee_id, begin);
//	}


	

}