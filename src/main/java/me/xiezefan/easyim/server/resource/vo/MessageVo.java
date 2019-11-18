package me.xiezefan.easyim.server.resource.vo;

import me.xiezefan.easyim.server.model.Message;
import me.xiezefan.easyim.server.model.OfflineMessage;

import java.util.Map;

public class MessageVo {
    public String id;
    public String fromId;
    public String type;
    public Map<String, Object> content;
    public String create_time;

    public MessageVo() {
    }


    public MessageVo(OfflineMessage msg) {
        this.id = msg.getId();
        Message _msg = msg.getMessage();
        this.fromId = _msg.getFromId();
        this.type = _msg.getType();
        this.content = _msg.getContentMap();
        this.create_time = _msg.getCreateTime();
    }

}
