package com.shine.dto;

import java.util.Date;

public class FollowContent {
	private int id;
	private int oppId;
	private Date time;
	private String content;
	private String follower;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOppId() {
		return oppId;
	}
	public void setOppId(int oppId) {
		this.oppId = oppId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
}
