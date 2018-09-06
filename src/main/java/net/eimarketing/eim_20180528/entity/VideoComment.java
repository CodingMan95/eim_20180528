package net.eimarketing.eim_20180528.entity;

import java.util.Date;

public class VideoComment {
	
	private Integer comment_id;// 评论id
	private String commentator_id;// 评论者id
	private String commentator_name;// 评论者name
	private String content;// 评论内容
	private Integer video_id;// 被评论视频id
	private String employee_id;// 被评论者id
	private String employee_name;// 被评论者name
	private Integer fabulous;// 评论点赞数
	private Date time;// 评论时间
	private String openId;
	
	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	
	public String getCommentator_id() {
		return commentator_id;
	}

	public void setCommentator_id(String commentator_id) {
		this.commentator_id = commentator_id;
	}

	public String getCommentator_name() {
		return commentator_name;
	}

	public void setCommentator_name(String commentator_name) {
		this.commentator_name = commentator_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVideo_id() {
		return video_id;
	}

	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public Integer getFabulous() {
		return fabulous;
	}

	public void setFabulous(Integer fabulous) {
		this.fabulous = fabulous;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
