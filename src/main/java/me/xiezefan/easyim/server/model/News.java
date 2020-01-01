package me.xiezefan.easyim.server.model;


public class News {
	private String id;
	private String createTime;
	private String content;
	private String userType;
	private NewsUser newsUser;
	
	public NewsUser getNewsUser() {
		return newsUser;
	}
	public void setNewsUser(NewsUser newsUser) {
		this.newsUser = newsUser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", createTime=" + createTime + ", content=" + content + ", userType=" + userType
				+ "]";
	}

	

}
