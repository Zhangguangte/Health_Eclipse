package me.xiezefan.easyim.server.resource.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Disease;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiseaseSortVo {
	public String id;
	public String title;
	public String introduction;
	public String url;
	
	public DiseaseSortVo(Disease disease) {
		this.id = disease.getId();
		this.title = disease.getDiseaseName();
		this.introduction = disease.getIntroduce();
		this.url = disease.getUrl();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
