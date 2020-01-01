package me.xiezefan.easyim.server.resource.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Disease;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipesListVo {
	public List<String> classList;
	public List<FoodMenuVo> foodList;
	public List<String> getClassList() {
		return classList;
	}
	public void setClassList(List<String> classList) {
		this.classList = classList;
	}
	public List<FoodMenuVo> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<FoodMenuVo> foodList) {
		this.foodList = foodList;
	}
	

}
