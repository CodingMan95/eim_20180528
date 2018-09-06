package net.eimarketing.eim_20180528.service;

import java.util.List;

import net.eimarketing.eim_20180528.entity.VideoComment;

public interface VideoCommentService {

	public List<VideoComment> selectAllComment(Integer video_id);// 查看某一视频的全部评论

	public int selectFabulousById(Integer comment_id);// 查看某一评论点赞数
	
	public int updateCommentFabulous(Integer comment_id);// 为某一评论点赞
	
	public int addComment(VideoComment comment);//添加评论
	
	public int selectIdByUser(String content);

}
