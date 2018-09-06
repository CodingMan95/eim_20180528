package net.eimarketing.eim_20180528.dao;

import java.util.List;

import net.eimarketing.eim_20180528.entity.VideoComment;

public interface VideoCommentDao {

	public List<VideoComment> selectAllComment(Integer video_id);// 查看某一视频的全部评论

	public int selectFabulousById(Integer comment_id);// 某一评论点赞数
	
	public int addComment(VideoComment comment);//添加评论
	
	public int updateCommentFabulous(Integer comment_id);// 为某一评论点赞

	public int selectIdByUser(String content);
}
