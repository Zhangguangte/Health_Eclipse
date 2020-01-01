package me.xiezefan.easyim.server.resource.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Ingredients;
import me.xiezefan.easyim.server.model.Recipes;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientResultVo {
	public String name;
	public String synopsis;
	public String nutritive;
	public String effect;
	public String url;
	public String calorie;
	public String components;

	public IngredientResultVo(Ingredients ingredients) {
		this.name = ingredients.getName();
		this.synopsis = ingredients.getSynopsis();
		this.nutritive = ingredients.getNutritive();
		this.effect = ingredients.getEffect();
		this.url = ingredients.getUrl();
		this.calorie = ingredients.getCalorie();
		this.components = ingredients.getComponents();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getNutritive() {
		return nutritive;
	}

	public void setNutritive(String nutritive) {
		this.nutritive = nutritive;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getComponents() {
		return components;
	}

	public void setComponents(String components) {
		this.components = components;
	}

}
