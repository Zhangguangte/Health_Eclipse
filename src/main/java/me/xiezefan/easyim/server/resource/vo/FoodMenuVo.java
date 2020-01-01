package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Recipes;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodMenuVo {
	public String id;
	public String pictureUrl;
	public String dishName;
	public String calorie;
	public String type;
	public int mold; 
	public FoodMenuVo() {}
	
	public FoodMenuVo(Recipes recipes) {
		this.id = recipes.getId();
		this.pictureUrl = recipes.getPictureUrl();
		this.dishName = recipes.getDishName();
		this.calorie = recipes.getCalorie();
		this.type = recipes.getType();
		this.mold = -2;
	}
	
	
	public int getMold() {
		return mold;
	}

	public void setMold(int mold) {
		this.mold = mold;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
