package me.xiezefan.easyim.server.model;

import java.util.Date;

public class User {
	private String id;
	private String account;
	private String nickname;
	private String password;
	private String avatar;
	private String description;
	private String sex;
	private String location;
	private String phone;
	private String createTime;
	private String lastmodifyTime;
	private String initials;
	private String deviceId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastmodifyTime() {
		return lastmodifyTime;
	}

	public void setLastmodifyTime(String lastmodifyTime) {
		this.lastmodifyTime = lastmodifyTime;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", nickname=" + nickname + ", avatar=" + avatar
				+ ", description=" + description + ", sex=" + sex + ", location=" + location + ", phone=" + phone
				+ ", createTime=" + createTime + ", lastmodifyTime=" + lastmodifyTime + ", initials=" + initials + "]";
	}

}
