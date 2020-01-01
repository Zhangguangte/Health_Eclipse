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
import me.xiezefan.easyim.server.dao.ServiceDao;
import me.xiezefan.easyim.server.resource.form.ConsultPictureForm;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.BookDetailVo;
import me.xiezefan.easyim.server.resource.vo.BookVo;
import me.xiezefan.easyim.server.resource.vo.CourseVo;
import me.xiezefan.easyim.server.resource.vo.LectureVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.service.ConsultService;
import me.xiezefan.easyim.server.service.ServiceService;

@Path("/service")
public class ServiceResource {
	private static final Logger LOG = LoggerFactory.getLogger(ServiceResource.class);

	@Autowired
	private ServiceService service;

	/******************* 图书 ****************************/
	@POST
	@Path("/library/searchBookDetail")
	@Consumes("application/json")
	@Produces("application/json")
	public Response searchBookDetail(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			BookDetailVo result = service.searchBookDetail(requestForm);
			return new ResponseBuilder(result).build();
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
	@Path("/library/searchBook")
	@Consumes("application/json")
	@Produces("application/json")
	public Response searchBook(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			List<BookVo> result = service.searchBook(requestForm);
			return new ResponseBuilder(result).build();
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

	/******************* 讲座 ****************************/
	@POST
	@Path("/lecture/getLectureDetail")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getLectureDetail(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			LectureVo lectureVo = service.getLectureDetail(requestForm);
			return new ResponseBuilder(lectureVo).build();
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
	@Path("/lecture/getLectureList")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getLectureList(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			List<LectureVo> list = service.getLectureList(requestForm);
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
	
	/******************* 反馈 ****************************/
	@POST
	@Path("/feedback/sendFeed")
	@Consumes("application/json")
	@Produces("application/json")
	public Response sendFeed(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			service.sendFeed(requestForm);
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
	
	
	/******************* 课表 ****************************/
	@POST
	@Path("/course/getTimeTable")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getTimeTable(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			List<CourseVo>  list = service.getTimeTable(requestForm);
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
