package me.xiezefan.easyim.server.resource.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Lecture;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LectureVo {

	public String id;
	public String content;
	public String date;
	public String title;
	public String college;
	public String author;

	public LectureVo() {
	}

	public LectureVo(Lecture lecture, int type) {
		this.id = lecture.getId();
		this.title = lecture.getTitle();
		this.date = lecture.getDate();
		if (type == 1) {
			this.content = lecture.getContent();
			this.college = lecture.getCollege();
			this.author = lecture.getAuthor();
		}
	}

}
