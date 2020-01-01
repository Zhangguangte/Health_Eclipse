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
import me.xiezefan.easyim.server.resource.vo.DiseaseDetailVo;
import me.xiezefan.easyim.server.resource.vo.DiseaseSortListVo;
import me.xiezefan.easyim.server.resource.vo.DiseaseSortVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.service.DiseaseService;

@Path("/disease")
public class DiseaseResource {
    private static final Logger LOG = LoggerFactory.getLogger(DiseaseResource.class);

   
    
    @Autowired
    private DiseaseService diseaseService;
    
   
    
    @POST
    @Path("/getDiseaseSort")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getDiseaseSort(RequestForm requestForm,@Context HttpHeaders headers) {
        try {
        	List<DiseaseSortVo> list= diseaseService.getDiseaseSort(requestForm);
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
    @Path("/getDiseaseSortList")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getDiseaseSortList(RequestForm requestForm,@Context HttpHeaders headers) {
        try {
        	List<DiseaseSortListVo> list= diseaseService.getDiseaseSortList(requestForm);
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
    @Path("/getDiseaseDetail")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getDiseaseDetail(RequestForm requestForm,@Context HttpHeaders headers) {
        try {
        	DiseaseDetailVo list= diseaseService.getDiseaseDetail(requestForm);
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
