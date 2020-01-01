package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Ingredients;
import me.xiezefan.easyim.server.model.Library;
import me.xiezefan.easyim.server.model.Recipes;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDetailVo {
	public String classify;
    public String name;
    public String author;
    public String price;
    public String publish;
    public String introduced;
    public String url;
    public int sum;
    public int rest;
    public String place;
    public String index;
	
	
	public BookDetailVo(Library library) {
		this.classify = library.getClassify();
	    this.name = library.getName();
	    this.author= library.getAuthor();
	    this.price= library.getPrice();
	    this.publish= library.getPublish();
	    this.introduced= library.getIntroduced();
	    this.url= library.getUrl();
	    this.sum= library.getSum();
	    this.rest= library.getRest();
	    this.place= library.getPlace();
	    this.index= library.getIndex();
	}


	public String getClassify() {
		return classify;
	}


	public void setClassify(String classify) {
		this.classify = classify;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getPublish() {
		return publish;
	}


	public void setPublish(String publish) {
		this.publish = publish;
	}


	public String getIntroduced() {
		return introduced;
	}


	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public int getRest() {
		return rest;
	}


	public void setRest(int rest) {
		this.rest = rest;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}

	
	
	
}
