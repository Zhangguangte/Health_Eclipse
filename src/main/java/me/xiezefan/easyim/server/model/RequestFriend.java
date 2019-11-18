package me.xiezefan.easyim.server.model;

import java.util.Date;

public class RequestFriend {
	private String id;
	private String userid;
	private String requestUserid;
	private String content;
	private String createTime;
	private String status;
	private User friend;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRequestUserid() {
		return requestUserid;
	}
	public void setRequestUserid(String requestUserid) {
		this.requestUserid = requestUserid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
	@Override
	public String toString() {
		return "Quest [id=" + id + ", userid=" + userid + ", requestUserid=" + requestUserid + ", content=" + content
				+ ", createTime=" + createTime + ", status=" + status + "]";
	}

	

}
