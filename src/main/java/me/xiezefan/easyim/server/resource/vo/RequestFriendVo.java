package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.RequestFriend;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestFriendVo {

	public String id;
	public String user_id;
	public String request_user_id;
	public String nickname;
	public String content;
	public String status;
	public String headImg;

	public RequestFriendVo(RequestFriend requestFriend) {
		this.id = requestFriend.getId();
		this.user_id = requestFriend.getUserid();
		this.request_user_id = requestFriend.getFriend().getId();
		this.nickname = requestFriend.getFriend().getNickname();
		this.content = requestFriend.getContent();
		this.status = requestFriend.getStatus();
		// this.headImg = requestFriend.getDescription();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequest_user_id() {
		return request_user_id;
	}

	public void setRequest_user_id(String request_user_id) {
		this.request_user_id = request_user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	@Override
	public String toString() {
		return "RequestFriendVo [id=" + id + ", request_user_id=" + request_user_id + ", nickname=" + nickname
				+ ", content=" + content + ", status=" + status + ", headImg=" + headImg + "]";
	}

}
