package net.eimarketing.eim_20180528.entity;

import java.util.ArrayList;
import java.util.List;

public class TopUser {
	
	public List<Video> vi =new ArrayList<Video>();
	public Integer begin;
	
	public List<Video> getVi() {
		return vi;
	}
	public void setVi(List<Video> vi) {
		this.vi = vi;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	
}
