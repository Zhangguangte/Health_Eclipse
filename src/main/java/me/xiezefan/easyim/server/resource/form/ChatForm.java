package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import me.xiezefan.easyim.server.util.StringUtil;

/**
 * Message Send Form
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatForm {
	public String user_id;
	public String type;
	public String create_time;
	public String content;
	public String file_path;
	public String room_id;

	public boolean validate() {
		return !StringUtil.isEmpty(type) && !StringUtil.isEmpty(create_time);
	}

	@Override
	public String toString() {
		return "ChatForm [user_id=" + user_id + ", type=" + type + ", create_time=" + create_time + ", content="
				+ content + ", file_path=" + file_path + ", room_id=" + room_id + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	
	
}
