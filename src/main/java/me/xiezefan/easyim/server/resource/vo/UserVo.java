package me.xiezefan.easyim.server.resource.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo {
	public String id;
	public String account;
	public String nickname;
	public String avatar;
	public String description;
	public String sex;
	public String location;
	public String phone;
	public String createTime;
	public String lastmodifyTime;
	public String initials;
	public boolean isfriends;

	public UserVo() {
	}

	public UserVo(User user, boolean isfriends) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.nickname = user.getNickname();
		this.avatar = user.getAvatar();
		this.description = user.getDescription();
		this.location = user.getLocation();
		this.sex = user.getSex();
		this.phone = user.getPhone();
		this.initials = user.getInitials();
		this.createTime = user.getCreateTime();
		this.lastmodifyTime = user.getLastmodifyTime();
		this.isfriends = isfriends;
	}

	public UserVo(User user) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.nickname = user.getNickname();
		this.avatar = user.getAvatar();
		this.description = user.getDescription();
		this.location = user.getLocation();
		this.sex = user.getSex();
		this.phone = user.getPhone();
		this.initials = user.getInitials();
		this.createTime = user.getCreateTime();
		this.lastmodifyTime = user.getLastmodifyTime();
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", account=" + account + ", nickname=" + nickname + ", avatar=" + avatar
				+ ", description=" + description + ", sex=" + sex + ", location=" + location + ", phone=" + phone
				+ ", createTime=" + createTime + ", lastmodifyTime=" + lastmodifyTime + ", initials=" + initials + "]";
	}

}
