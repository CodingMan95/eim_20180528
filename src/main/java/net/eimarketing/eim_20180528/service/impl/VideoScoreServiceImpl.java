package net.eimarketing.eim_20180528.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.eimarketing.eim_20180528.dao.VideoScoreDao;
import net.eimarketing.eim_20180528.entity.Number;
import net.eimarketing.eim_20180528.entity.VideoScore;
import net.eimarketing.eim_20180528.service.VideoScoreService;

@Service
@Transactional
public class VideoScoreServiceImpl implements VideoScoreService{

	@Autowired
	private VideoScoreDao videoscoreDao;

	@Override
	public VideoScore selectScoreByUser(String employee_id) {
		return videoscoreDao.selectScoreByUser(employee_id);
	}

	@Override
	public List<VideoScore> selectByRater(String employee_id) {
		return videoscoreDao.selectByRater(employee_id);
	}

	@Override
	public List<VideoScore> selectTopScore(String employee_area) {
		return videoscoreDao.selectTopScore(employee_area);
	}

	@Override
	public int updateVideoScore(VideoScore videoScore) {
		return videoscoreDao.updateVideoScore(videoScore);
	}

	@Override
	public int addVideoScore(VideoScore videoScore) {
		// TODO Auto-generated method stub
		return videoscoreDao.addVideoScore(videoScore);
	}

	@Override
	public int selectNumByRater(String employee_id) {
		// TODO Auto-generated method stub
		return videoscoreDao.selectNumByRater(employee_id);
	}

	@Override
	public int selectTotalByRater(String employee_id) {
		// TODO Auto-generated method stub
		return videoscoreDao.selectTotalByRater(employee_id);
	}

	@Override
	public void updateRaterId(VideoScore videoScore) {
		// TODO Auto-generated method stub
		videoscoreDao.updateRaterId(videoScore);
	}

	@Override
	public List<VideoScore> getSocrelist(String employee_area) {
		return videoscoreDao.getSocrelist(employee_area);
	}

}
