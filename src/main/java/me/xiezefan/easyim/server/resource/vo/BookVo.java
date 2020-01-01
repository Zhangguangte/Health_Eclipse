package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Ingredients;
import me.xiezefan.easyim.server.model.Library;
import me.xiezefan.easyim.server.model.Recipes;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookVo {
	public String id;
	public String bookName;
	public String author;
	public String publish;
	public int rest;
	public String classify;

	public BookVo(Library library) {
		this.id = library.getId();
		this.bookName = library.getName();
		this.author = library.getAuthor();
		this.publish = library.getPublish();
		this.classify = library.getClassify();
		this.rest = library.getRest();
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}


}
