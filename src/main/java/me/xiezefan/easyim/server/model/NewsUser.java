package me.xiezefan.easyim.server.model;


public class NewsUser {
	private String id;
	private String newsId;
	private String userId;
	private String status;
	private String readTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	@Override
	public String toString() {
		return "NewsUser [id=" + id + ", newsId=" + newsId + ", userId=" + userId + ", status=" + status + ", readTime="
				+ readTime + "]";
	}
	

	

}
