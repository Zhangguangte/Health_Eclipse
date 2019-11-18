package me.xiezefan.easyim.server.resource;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.resource.form.ChatForm;
import me.xiezefan.easyim.server.resource.form.MessageSendForm;
import me.xiezefan.easyim.server.resource.form.MessageStatusUpdateForm;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.MessageListVo;
import me.xiezefan.easyim.server.resource.vo.MessageVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import me.xiezefan.easyim.server.service.MessageService;
import me.xiezefan.easyim.server.util.FileUtil;
import me.xiezefan.easyim.server.util.JsonUtil;
import okhttp3.RequestBody;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Path("/messages")
public class MessageResource {
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(MessageResource.class);

	@Autowired
	private MessageService messageService;

	@POST
	@Path("/send")
	@Consumes("application/json")
	@Produces("application/json")
	public Response send(MessageSendForm dataForm, @Context HttpHeaders headers) {
		try {
			MessageVo msg = messageService.send(headers.getHeaderString("User"), dataForm);
			return new ResponseBuilder(msg).build();
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
	@Path("/offline")
	@Produces("application/json")
	public Response offline(@Context HttpHeaders headers) {
		try {
			List<MessageVo> result = messageService.getOfflineMessage(headers.getHeaderString("User"));
			return new ResponseBuilder(result).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}

	@PUT
	@Path("/status")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateStatusBatch(MessageStatusUpdateForm[] dataForms, @Context HttpHeaders headers) {
		try {
			messageService.updateStatus(headers.getHeaderString("User"), dataForms);
			return ResponseBuilder.SUCCESS.build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}

	@PUT
	@Path("/status/{mid}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateStatus(MessageStatusUpdateForm dataForm, @PathParam("mid") String mid,
			@Context HttpHeaders headers) {
		try {
			messageService.updateStatus(headers.getHeaderString("User"), mid, dataForm);
			return ResponseBuilder.SUCCESS.build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}

	///////////////////////////////////////////////////
	@POST
	@Path("/lastMessage")
	@Produces("application/json")
	public Response lastMessage(RequestBody vv,@Context HttpHeaders headers) {
		try {
			List<MessageListVo> list = messageService.lastMessage(headers.getHeaderString("User"));
			return new ResponseBuilder(list).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}

	@POST
	@Path("/allChatByRoomId")
	@Consumes("application/json")
	@Produces("application/json")
	public Response allChatByRoomId(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			List<MessageListVo> list = messageService.allChatByRoomId(requestForm, headers.getHeaderString("User"));
			return new ResponseBuilder(list).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}
	
	@POST
	@Path("/allChatByUid")
	@Consumes("application/json")
	@Produces("application/json")
	public Response allChatByUid(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			List<MessageListVo> list = messageService.allChatByUid(requestForm, headers.getHeaderString("User"));
			return new ResponseBuilder(list).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}

	@POST
	@Path("/insertContent")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertContent(ChatForm chatForm, @Context HttpHeaders headers) {
		try {
			messageService.insertContent(chatForm, headers.getHeaderString("User"));
			return ResponseBuilder.SUCCESS.build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}
	
	@POST
	@Path("/insertCard")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertCard(ChatForm chatForm, @Context HttpHeaders headers) {
		try {
			UserVo uservo = messageService.insertCard(chatForm, headers.getHeaderString("User"));
			return new ResponseBuilder(uservo).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}
	
//	@POST
//	@Path("/upPicture")
//	public Response upPicture(@RequestParam  Map<String, Object> params) {
//		try {
//			System.out.println("1");
//			return ResponseBuilder.SUCCESS.build();
//		} catch (IllegalArgumentException e) {
//			System.out.println("2");
//			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
//		} catch (Exception e) {
//			LOG.error("Unknown Error", e);
//			System.out.println("3");
//			return ResponseBuilder.ERROR_BAD_SERVER.build();
//		}
//		finally {
//			System.out.println("4");
//		}
//	}
	
//	@POST
//	@Path("/upload2IM")
//	public Response upload2IM(@RequestParam Map<String, RequestBody> params,HttpServletRequest request) {
//		try {
//			System.out.println("1");
//			return ResponseBuilder.SUCCESS.build();
//		} catch (IllegalArgumentException e) {
//			System.out.println("2");
//			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
//		} catch (Exception e) {
//			LOG.error("Unknown Error", e);
//			System.out.println("3");
//			return ResponseBuilder.ERROR_BAD_SERVER.build();
//		}
//		finally {
//			System.out.println("4");
//		}
//	}
	
	

//	@POST
//	@Path("/upPicture")
//	public Response upPicture(@RequestParam(value = "pictures", required = false) CommonsMultipartFile[] pictures) {
//		try {
//			System.out.println("1");
////			System.out.println("request"+request);
////			request.setCharacterEncoding("utf-8");
////			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
////			System.out.println("isMultipart"+isMultipart);
////			if (isMultipart) {
////				FileItemFactory factory = new DiskFileItemFactory();
////				ServletFileUpload upload = new ServletFileUpload(factory);
////				List<FileItem> items = upload.parseRequest(request);
////				Iterator<FileItem> iter = items.iterator();
////				while (iter.hasNext()) {
////					FileItem item = (FileItem) iter.next();
////					if (item.isFormField()) {
////						String fieldName = item.getFieldName();
////						String imageAddress = "H:\\MyImage\\" + fieldName;
////						FileUtil.string2image(item.getString(), imageAddress);
////					}
////				}
////			}
////			
//			return ResponseBuilder.SUCCESS.build();
//		} catch (IllegalArgumentException e) {
//			System.out.println("2");
//			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
//		} catch (Exception e) {
//			LOG.error("Unknown Error", e);
//			System.out.println("3");
//			return ResponseBuilder.ERROR_BAD_SERVER.build();
//		}
//		finally {
//			System.out.println("4");
//		}
//	}
	
	@POST
	@Path("/searchRoomid")
	@Consumes("application/json")
	@Produces("application/json")
	public Response searchRoomid(RequestForm requestForm, @Context HttpHeaders headers) {
		try {
			MessageListVo msListVo=messageService.searchRoomid(requestForm, headers.getHeaderString("User"));
			return new ResponseBuilder(msListVo).build();
		} catch (IllegalArgumentException e) {
			return ResponseBuilder.ERROR_INVALID_PARAMETER.build();
		} catch (Exception e) {
			LOG.error("Unknown Error", e);
			return ResponseBuilder.ERROR_BAD_SERVER.build();
		}
	}
	

}
