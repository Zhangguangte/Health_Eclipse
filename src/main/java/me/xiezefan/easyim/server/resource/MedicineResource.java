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
import me.xiezefan.easyim.server.resource.vo.MedicineDetailVo;
import me.xiezefan.easyim.server.resource.vo.MedicineListVo;
import me.xiezefan.easyim.server.resource.vo.MedicineVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.service.MedicineService;


@Path("/medicine")
public class MedicineResource {
    private static final Logger LOG = LoggerFactory.getLogger(MedicineResource.class);

    @Autowired
    public MedicineService medicineService;

   
    @POST
    @Path("/getClassify")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getClassify(@Context HttpHeaders headers) {
    	try {
    		List<MedicineVo> list = medicineService.getClassify();
    		return new ResponseBuilder(list).build();
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
    @Path("/getAllMedicine")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAllMedicine( RequestForm requestForm,@Context HttpHeaders headers) {
    	try {
    		List<MedicineListVo> list = medicineService.getAllMedicine(requestForm);
    		return new ResponseBuilder(list).build();
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
    @Path("/getMedicineDetail")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getMedicineDetail( RequestForm requestForm,@Context HttpHeaders headers) {
    	try {
    		MedicineDetailVo result = medicineService.getMedicineDetail(requestForm);
    		return new ResponseBuilder(result).build();
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
    @Path("/getAllMedicineByKey")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAllMedicineByKey( RequestForm requestForm,@Context HttpHeaders headers) {
    	try {
    		List<MedicineListVo> list= medicineService.getAllMedicineByKey(requestForm);
    		return new ResponseBuilder(list).build();
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
