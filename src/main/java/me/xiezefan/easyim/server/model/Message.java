package me.xiezefan.easyim.server.model;

import me.xiezefan.easyim.server.util.JsonUtil;

import java.util.Date;
import java.util.Map;

/**
 * 消息对象
 * id 的构建方法为, s_ 代表单推消息, g_代表群推消息
 * content 字段为消息的Json字符串
 */
public class Message {
    private String id;
    private String fromId;
    private String file_path;
    private String type;
    private String room_id;
    private String contentStr;
    private String content;
    private Map<String, Object> contentmap ;
    private String createTime;
    private String sentStatus;
    private User friend;
    private MessageList list;


	public String getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(String sentStatus) {
		this.sentStatus = sentStatus;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public MessageList getList() {
		return list;
	}

	public void setList(MessageList list) {
		this.list = list;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Map<String, Object> getContentMap() {
        return contentmap;
    }

    public void setContentMap(Map<String, Object> contentmap) {
        this.contentmap = contentmap;
        this.contentStr = JsonUtil.toJson(contentmap);
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentmap = JsonUtil.formatToMap(contentStr);
        this.contentStr = contentStr;
    }

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", fromId=" + fromId +  ", type=" + type + ", contentStr="
				+ contentStr + ", content=" + content + ", contentmap=" + contentmap + ", createTime=" + createTime
				+", friend=" + friend + ", list=" + list + "]";
	}

	
	
    
}
