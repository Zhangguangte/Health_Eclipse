package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.User;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressListVo {
	public String nickname;
	public String account;
	public String image;
	public String sortLetters; // 显示数据拼音的首字母

	public AddressListVo() {
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	 public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public AddressListVo(User user) {
	        this.nickname = user.getNickname();
	        this.account = user.getAccount();
	        this.sortLetters = user.getInitials();
	    }

	@Override
	public String toString() {
		return "AddressListVo [nickname=" + nickname + ", account=" + account + ", image=" + image + ", sortLetters="
				+ sortLetters + "]";
	}
	
	

}
