package me.xiezefan.easyim.server.resource;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.resource.form.FriendshipAddForm;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.form.UserUpdateForm;
import me.xiezefan.easyim.server.resource.vo.AddressListVo;
import me.xiezefan.easyim.server.resource.vo.RequestFriendVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import me.xiezefan.easyim.server.service.FriendshipService;
import me.xiezefan.easyim.server.service.MessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/friends")
public class FriendshipResource {
    private static final Logger LOG = LoggerFactory.getLogger(FriendshipResource.class);

    @Autowired
    private FriendshipService friendshipService;
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(FriendshipAddForm dataForm, @Context HttpHeaders headers) {
        try {
            friendshipService.save(dataForm, headers.getHeaderString("User"));
            return ResponseBuilder.SUCCESS.build();
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

    @DELETE
    @Path("/{friend_id}")
    @Produces("application/json")
    public Response delete(@PathParam("friend_id")String friend_id, @Context HttpHeaders headers) {
        try {
            friendshipService.delete(headers.getHeaderString("User"), friend_id);
            return ResponseBuilder.SUCCESS.build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }

    @GET
    @Produces("application/json")
    public Response list(@Context HttpHeaders headers) {
        try {
            List<UserVo> friends = friendshipService.findByUserId(headers.getHeaderString("User"));
            return new ResponseBuilder(friends).build();
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
        } catch (Exception e) {
            LOG.error("Unknown Error", e);
            return ResponseBuilder.ERROR_BAD_SERVER.build();
        }
    }

    /////////////////////////////////////////////////////////
    @POST
	@Path("/allFriend")
	@Produces("application/json")
	public Response allFriend(@Context HttpHeaders headers) {
		try {
			List<AddressListVo> list = friendshipService.allFriend(headers.getHeaderString("User"));
			return new ResponseBuilder(list).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}

    @POST
	@Path("/requestFriends")
	@Produces("application/json")
	public Response requestFriends(@Context HttpHeaders headers) {
		try {
			List<RequestFriendVo> list = friendshipService.requestFriends(headers.getHeaderString("User"));
			return new ResponseBuilder(list).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}
    
    @POST
	@Path("/clearRequestFriends")
	@Produces("application/json")
	public Response clearRequestFriends(@Context HttpHeaders headers) {
		try {
			friendshipService.clearRequestFriends(headers.getHeaderString("User"));
			return ResponseBuilder.SUCCESS.build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}
    
    @POST
   	@Path("/sendRequestFriend")
    @Consumes("application/json")
   	@Produces("application/json")
   	public Response sendRequestFriend(RequestForm requestForm,@Context HttpHeaders headers) {
   		try {
   			friendshipService.sendRequestFriend(requestForm,headers.getHeaderString("User"));
   			return ResponseBuilder.SUCCESS.build();
   		} catch (IllegalArgumentException e) {
   			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
   		} catch (Exception e) {
   			LOG.error("Unknown Error", e);
   			return ResponseBuilder.ERROR_BAD_SERVER.build();
   		}
   	}
       
    @POST
   	@Path("/saveRequestFriend")
    @Consumes("application/json")
   	@Produces("application/json")
   	public Response saveRequestFriend(RequestForm requestForm,@Context HttpHeaders headers) {
   		try {
   			friendshipService.saveRequestFriend(requestForm,headers.getHeaderString("User"));	
   			return ResponseBuilder.SUCCESS.build();
   		} catch (IllegalArgumentException e) {
   			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
   		} catch (Exception e) {
   			LOG.error("Unknown Error", e);
   			return ResponseBuilder.ERROR_BAD_SERVER.build();
   		}
   	}
    @POST
    @Path("/refuseRequestFriend")
    @Consumes("application/json")
    @Produces("application/json")
    public Response refuseRequestFriend(RequestForm requestForm,@Context HttpHeaders headers) {
    	try {
    		friendshipService.refuseRequestFriend(requestForm,headers.getHeaderString("User"));
    		return ResponseBuilder.SUCCESS.build();
    	} catch (IllegalArgumentException e) {
    		return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
    	} catch (Exception e) {
    		LOG.error("Unknown Error", e);
    		return ResponseBuilder.ERROR_BAD_SERVER.build();
    	}
    }
    
}
