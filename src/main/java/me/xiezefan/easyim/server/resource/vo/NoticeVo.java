package me.xiezefan.easyim.server.resource.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.News;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticeVo {
	public String id;
    public String create_time;
    public String content;
    public String status;
    public String noticeType;

	public NoticeVo() {
	}

	public NoticeVo(News news) {
		this.id = news.getId();
		this.create_time = news.getCreateTime();
		this.content = news.getContent();
		this.status = news.getNewsUser().getStatus();
		this.noticeType = news.getUserType();
	}

	@Override
	public String toString() {
		return "NoticeVo [id=" + id + ", create_time=" + create_time + ", content=" + content + ", status=" + status
				+ ", noticeType=" + noticeType + "]";
	}

	

}
