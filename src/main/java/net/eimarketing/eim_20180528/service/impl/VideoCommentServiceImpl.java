package net.eimarketing.eim_20180528.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.eimarketing.eim_20180528.dao.VideoCommentDao;
import net.eimarketing.eim_20180528.entity.VideoComment;
import net.eimarketing.eim_20180528.service.VideoCommentService;

@Service
@Transactional
public class VideoCommentServiceImpl implements VideoCommentService {

	@Autowired
	private VideoCommentDao videocommentDao;

	@Override
	public List<VideoComment> selectAllComment(Integer video_id) {
		return videocommentDao.selectAllComment(video_id);
	}

	@Override
	public int selectFabulousById(Integer comment_id) {
		return videocommentDao.selectFabulousById(comment_id);
	}

	@Override
	public int addComment(VideoComment comment) {
		// TODO Auto-generated method stub
		return videocommentDao.addComment(comment);
	}

	@Override
	public int updateCommentFabulous(Integer comment_id) {
		// TODO Auto-generated method stub
		return videocommentDao.updateCommentFabulous(comment_id);
	}

	@Override
	public int selectIdByUser(String content) {
		// TODO Auto-generated method stub
		return videocommentDao.selectIdByUser(content);
	}




}
