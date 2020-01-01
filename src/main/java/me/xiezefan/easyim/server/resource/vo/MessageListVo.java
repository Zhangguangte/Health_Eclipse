package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Message;
import me.xiezefan.easyim.server.model.MessageList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageListVo {
	public int unread;
	public String room_id;
	public String type;
	public String content;
	public String create_time;
	public String another_name;
	public String belongId;
	public String file_path;
	public String sentStatus;
	
	public MessageListVo() {
	}

	public MessageListVo(Message message) {
		this.type = message.getType();
		this.content = message.getContent();
		this.room_id = message.getRoom_id();
		this.belongId = message.getFromId();
		this.file_path=message.getFile_path();
		this.create_time = message.getCreateTime();
		this.sentStatus = message.getSentStatus();
		if (null != message.getList()) {
			this.unread = message.getList().getUnread();
			this.another_name = message.getList().getAnotherName();
		}

	}

	public MessageListVo(MessageList message) {
		this.room_id =message.getRoomId() ;
		this.another_name =message.getAnotherName() ;
	}
	
	public MessageListVo(String roomId) {
		this.room_id =roomId;
	}
	
	
	public String getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(String sentStatus) {
		this.sentStatus = sentStatus;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public String getBelongId() {
		return belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getAnother_name() {
		return another_name;
	}

	public void setAnother_name(String another_name) {
		this.another_name = another_name;
	}

	@Override
	public String toString() {
		return "MessageListVo [type=" + type + ", content=" + content + ", create_time=" + create_time + ", room_id="
				+ room_id + ", another_name=" + another_name + "]";
	}

}
