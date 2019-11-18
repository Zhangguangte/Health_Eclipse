package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

import java.util.Map;

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
	public int room_id;

	public boolean validate() {
		return !StringUtil.isEmpty(type) && !StringUtil.isEmpty(create_time);
	}

	@Override
	public String toString() {
		return "ChatForm [user_id=" + user_id + ", type=" + type + ", create_time=" + create_time + ", content="
				+ content + ", file_path=" + file_path + ", room_id=" + room_id + "]";
	}
	
	
}
