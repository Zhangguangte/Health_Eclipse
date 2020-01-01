package me.xiezefan.easyim.server.resource;

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
import me.xiezefan.easyim.server.resource.form.ConsultPictureForm;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.service.ConsultService;

@Path("/consult")
public class ConsultResource {
    private static final Logger LOG = LoggerFactory.getLogger(ConsultResource.class);

   
    
    @Autowired
    private ConsultService consultService;
    
   
    
    @POST
    @Path("/saveConsultPicture")
    @Consumes("application/json")
    @Produces("application/json")
    public Response saveConsultPicture(ConsultPictureForm consultPictureForm,@Context HttpHeaders headers) {
        try {
        	 consultService.saveConsultPicture(consultPictureForm,headers.getHeaderString("User"));
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
    
    
    
}
