package me.xiezefan.easyim.server.service;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.RecipesDao;
import me.xiezefan.easyim.server.model.Ingredients;
import me.xiezefan.easyim.server.model.Recipes;
import me.xiezefan.easyim.server.model.RecipesClassify;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.FoodMenuVo;
import me.xiezefan.easyim.server.resource.vo.FoodRecommendVo;
import me.xiezefan.easyim.server.resource.vo.FoodVo;
import me.xiezefan.easyim.server.resource.vo.IngredientResultVo;
import me.xiezefan.easyim.server.resource.vo.RecipesListVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.util.StringUtil;

/**
 * Medicine Service
 */
@Service
public class RecipesService {

	private final static String[] titles = { "早餐", "午餐", "晚餐" };
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");

	@Resource
	private RecipesDao recipesDao;

	// 获得所有的分类
	public List<FoodMenuVo> getRecipesByThreeMeals(RequestForm requestForm) throws ServiceException {
		float sum = 0;
		List<FoodMenuVo> result = new LinkedList<FoodMenuVo>();
		List<Recipes> list = recipesDao.getRecipesByThreeMeals(requestForm.quest_id,requestForm.content);
	
		FoodMenuVo foodMenuVo  = new FoodMenuVo();
		foodMenuVo.setDishName(titles[requestForm.row]);
		foodMenuVo.setMold(-1);
		for (Recipes recipes : list) {
			sum += Float.valueOf(recipes.getCalorie());
			result.add(new FoodMenuVo(recipes));
		}
		foodMenuVo.setCalorie(decimalFormat.format(sum));
		result.add(0, foodMenuVo);
		return result;
	}

	// 获得菜肴细节
	public FoodVo getRecipeDetail(RequestForm requestForm) throws ServiceException {
		Recipes recipes = null;
		if (0 == requestForm.type)
			recipes = recipesDao.getRecipeDetail(requestForm.quest_id);
		else
			recipes = recipesDao.getRecipeDetailByName(requestForm.quest_id);
		if (null == recipes)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return new FoodVo(recipes);
	}

	// 获得推荐菜肴细节
	public List<FoodRecommendVo> getRecommendRecipes() throws ServiceException {
		List<FoodRecommendVo> result = new LinkedList<FoodRecommendVo>();
		List<Recipes> list = recipesDao.getRecommendRecipes();
		for (Recipes recipes : list) {
			result.add(new FoodRecommendVo(recipes));
		}
		int i = 1;
		List<Ingredients> inList = recipesDao.getIngredientsTips();
		for (Ingredients ingredients : inList) {
			result.add(3 * i++ - 1, new FoodRecommendVo(ingredients));
		}
		return result;
	}

	// 获得材料细节
	public IngredientResultVo getIngredientResult(RequestForm requestForm) throws ServiceException {

		Ingredients ingredients = recipesDao.getIngredientResult(requestForm.content);

		if (null == ingredients)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);

		return new IngredientResultVo(ingredients);
	}

	public RecipesListVo getRecipesList(RequestForm requestForm) throws ServiceException {
		RecipesListVo reListVo = new RecipesListVo();
		RecipesClassify reClassify = recipesDao.getRecipesClassList(requestForm.type);
		if (null == reClassify)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		String[] clas = reClassify.getClassify().split(",");
	
		List<FoodMenuVo> result = getFoodList(clas[0],requestForm.row);
		reListVo.setFoodList(result);
		reListVo.setClassList(java.util.Arrays.asList(clas));
		
		return reListVo;
	}
	
	public List<FoodMenuVo> getFoodList(String name,int row) throws ServiceException {
		List<FoodMenuVo> result = new LinkedList<FoodMenuVo>();
		List<Recipes> recipes =	recipesDao.getFoodList("%(" + name + ")%",row*15);
		if (null == recipes)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		for(Recipes recipe:recipes)
			result.add(new FoodMenuVo(recipe));
		return result;
	
	}
	

}
