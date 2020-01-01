package me.xiezefan.easyim.server.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.FoodMenuVo;
import me.xiezefan.easyim.server.resource.vo.FoodRecommendVo;
import me.xiezefan.easyim.server.resource.vo.FoodVo;
import me.xiezefan.easyim.server.resource.vo.IngredientResultVo;
import me.xiezefan.easyim.server.resource.vo.RecipesListVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.service.RecipesService;

@Path("/recipes")
public class RecipesResource {
    private static final Logger LOG = LoggerFactory.getLogger(RecipesResource.class);

   
    
    @Autowired
    private RecipesService recipesService;
    
    @POST
    @Path("/getRecipesByThreeMeals")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getRecipesByThreeMeals(RequestForm requestForm,@Context HttpHeaders headers) {
        try {
            List<FoodMenuVo> list= recipesService.getRecipesByThreeMeals(requestForm);
            return new ResponseBuilder(list).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (ServiceException e) {
            if (e.isLogException()) {
                LOG.error("Business Error", e);
            }
            return e.getResponseBuilder().build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }
    
    @POST
    @Path("/getRecipeDetail")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getRecipeDetail(RequestForm requestForm,@Context HttpHeaders headers) {
        try {
        	FoodVo foodVo= recipesService.getRecipeDetail(requestForm);
            return new ResponseBuilder(foodVo).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (ServiceException e) {
            if (e.isLogException()) {
                LOG.error("Business Error", e);
            }
            return e.getResponseBuilder().build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }
    
    @POST
    @Path("/getRecommendRecipes")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getRecommendRecipes(@Context HttpHeaders headers) {
    	try {
    		 List<FoodRecommendVo> list= recipesService.getRecommendRecipes();
    		return new ResponseBuilder(list).build();
    	} catch (IllegalArgumentException e) {
    		return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
    	} catch (ServiceException e) {
    		if (e.isLogException()) {
    			LOG.error("Business Error", e);
    		}
    		return e.getResponseBuilder().build();
    	} catch (Exception e) {
    		LOG.error("Unknown Error", e);
    		return ResponseBuilder.ERROR_BAD_SERVER.build();
    	}
    }
    
    @POST
    @Path("/getIngredientResult")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getIngredientResult(RequestForm requestForm,@Context HttpHeaders headers) {
    	try {
    		IngredientResultVo ingredientResultVo= recipesService.getIngredientResult(requestForm);
    		return new ResponseBuilder(ingredientResultVo).build();
    	} catch (IllegalArgumentException e) {
    		return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
    	} catch (ServiceException e) {
    		if (e.isLogException()) {
    			LOG.error("Business Error", e);
    		}
    		return e.getResponseBuilder().build();
    	} catch (Exception e) {
    		LOG.error("Unknown Error", e);
    		return ResponseBuilder.ERROR_BAD_SERVER.build();
    	}
    }
    @POST
    @Path("/getRecipesList")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getRecipesList(RequestForm requestForm,@Context HttpHeaders headers) {
    	try {
    		RecipesListVo recipesListVo= recipesService.getRecipesList(requestForm);
    		return new ResponseBuilder(recipesListVo).build();
    	} catch (IllegalArgumentException e) {
    		return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
    	} catch (ServiceException e) {
    		if (e.isLogException()) {
    			LOG.error("Business Error", e);
    		}
    		return e.getResponseBuilder().build();
    	} catch (Exception e) {
    		LOG.error("Unknown Error", e);
    		return ResponseBuilder.ERROR_BAD_SERVER.build();
    	}
    }
    
    @POST
    @Path("/getFoodList")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getFoodList(RequestForm requestForm,@Context HttpHeaders headers) {
    	try {
    		List<FoodMenuVo> list= recipesService.getFoodList(requestForm.content,requestForm.row);
    		return new ResponseBuilder(list).build();
    	} catch (IllegalArgumentException e) {
    		return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
    	} catch (ServiceException e) {
    		if (e.isLogException()) {
    			LOG.error("Business Error", e);
    		}
    		return e.getResponseBuilder().build();
    	} catch (Exception e) {
    		LOG.error("Unknown Error", e);
    		return ResponseBuilder.ERROR_BAD_SERVER.build();
    	}
    }
}
