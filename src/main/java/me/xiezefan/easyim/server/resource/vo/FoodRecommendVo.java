package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Ingredients;
import me.xiezefan.easyim.server.model.Recipes;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodRecommendVo {
	public String id;
	public String picture;
	public String title;
	public String description;
	public int mold;

	public FoodRecommendVo(Recipes recipes) {
		this.id = recipes.getId();
		this.picture = recipes.getPictureUrl();
		this.title = recipes.getDishName();
		this.mold = 0;
	}

	public FoodRecommendVo(Ingredients ingredients) {
		this.id = ingredients.getId();
		this.picture = ingredients.getUrl();
		this.title = ingredients.getName();
		int ra = (int) (Math.random() * 9) % 3;
		switch (ra) {
		case 0:
			this.description = ingredients.getSynopsis();
			break;
		case 1:
			this.description = ingredients.getNutritive();
			break;
		case 2:
			this.description = ingredients.getEffect();
			break;

		}

		this.mold = 1;
	}

	public FoodRecommendVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMold() {
		return mold;
	}

	public void setMold(int mold) {
		this.mold = mold;
	}

}
