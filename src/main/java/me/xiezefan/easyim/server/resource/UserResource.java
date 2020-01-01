package me.xiezefan.easyim.server.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.LoginForm;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import me.xiezefan.easyim.server.service.UserService;


@Path("/users")
public class UserResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    public UserService userService;

    
    /////////////////////////////////////////
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginForm dataForm) {
        try {
            User user = userService.login(dataForm);
            UserVo uservo = new UserVo(user);
            System.out.println(uservo);
            return new ResponseBuilder(uservo).build();
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
    
    @GET
   	@Path("/information/{account}")
   	@Produces("application/json")
   	public Response getUserInformation(@PathParam("account") String account, @Context HttpHeaders headers) {
   		try {
               UserVo user = userService.getUserInformation(account);
               System.out.println("UserVo"+user);
               return new ResponseBuilder(user).build();
           } catch (ServiceException e) {
               if (e.isLogException()) {
                   LOG.error("Business Error", e);
               }
               return e.getResponseBuilder().build();
           }  catch (Exception e) {
               LOG.error("Unknown Error", e);
               return ResponseBuilder.ERROR_BAD_SERVER.build();
           }
   	}
    
    
    @POST
   	@Path("/searchUser/{searchWords}")
   	@Produces("application/json")
   	public Response searchUser(@PathParam("searchWords") String searchWords, @Context HttpHeaders headers) {
   		try {
               UserVo user = userService.searchUser(searchWords,headers.getHeaderString("User"));
               return new ResponseBuilder(user).build();
           } catch (ServiceException e) {
               if (e.isLogException()) {
                   LOG.error("Business Error", e);
               }
               return e.getResponseBuilder().build();
           }  catch (Exception e) {
               LOG.error("Unknown Error", e);
               return ResponseBuilder.ERROR_BAD_SERVER.build();
           }
   	}
    @POST
    @Path("/searchUser")
    @Produces("application/json")
    @Consumes("application/json")
    public Response searchUser(RequestForm requestForm, @Context HttpHeaders headers) {
    	try {
    		UserVo user = userService.searchUser(requestForm);
    		return new ResponseBuilder(user).build();
    	} catch (ServiceException e) {
    		if (e.isLogException()) {
    			LOG.error("Business Error", e);
    		}
    		return e.getResponseBuilder().build();
    	}  catch (Exception e) {
    		LOG.error("Unknown Error", e);
    		return ResponseBuilder.ERROR_BAD_SERVER.build();
    	}
    }
    
    

}
