package me.xiezefan.easyim.server.model;

import java.util.Date;
import java.util.Map;

public class MessageList {

	private int id;
	private int roomId;
	private String userId;
	private String status;
	private int unread;
	private String anotherName;
	private User friend;
	private int is_read;
	
	

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
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

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public int getIs_read() {
		return is_read;
	}

	public void setIs_read(int is_read) {
		this.is_read = is_read;
	}

	@Override
	public String toString() {
		return "MessageList [id=" + id + ", roomId=" + roomId + ", userId=" + userId + ", status=" + status
				+ ", unread=" + unread + ", anotherName=" + anotherName + ", friend=" + friend + ", is_read="
				+ is_read + "]";
	}

	
	
}
