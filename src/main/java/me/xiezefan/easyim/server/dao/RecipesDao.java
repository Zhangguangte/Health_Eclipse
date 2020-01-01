package me.xiezefan.easyim.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import me.xiezefan.easyim.server.model.Ingredients;
import me.xiezefan.easyim.server.model.Recipes;
import me.xiezefan.easyim.server.model.RecipesClassify;

/**
 * Recipes Dao
 */
public interface RecipesDao {

	// 三餐
	@Select("SELECT * from tb_recipes where type=#{type} and week like #{week}")
	@ResultMap("RecipesResultMap")
	public List<Recipes> getRecipesByThreeMeals(@Param("type") String type,@Param("week") String week);

	// 菜肴详细
	@Select("select * from tb_recipes where id=#{id}")
	@ResultMap("RecipesResultMap")
	public Recipes getRecipeDetail(@Param("id") String id);

	// 菜肴详细
	@Select("select * from tb_recipes where dish_name like #{name} limit 1")
	@ResultMap("RecipesResultMap")
	public Recipes getRecipeDetailByName(@Param("name") String name);

	// 推荐食谱
	@Select("select id,picture_url,dish_name from tb_recipes ORDER BY RAND() LIMIT 10")
	@ResultMap("RecipesResultMap")
	public List<Recipes> getRecommendRecipes();

	// 食材小知识
	@Select("select * from tb_ingredients ORDER BY RAND() LIMIT 5")
	public List<Ingredients> getIngredientsTips();

	// 食材信息
	@Select("select * from tb_ingredients where name like #{name} limit 1")
	public Ingredients getIngredientResult(@Param("name") String name);

	@Select("SELECT * from tb_recipes_classify where id = #{id} ")
	public RecipesClassify getRecipesClassList(@Param("id") int id);

	@Select("select * from tb_recipes where classification like #{name} LIMIT #{row},15")
	@ResultMap("RecipesResultMap")
	public List<Recipes> getFoodList(@Param("name") String name,@Param("row") int row);
}
