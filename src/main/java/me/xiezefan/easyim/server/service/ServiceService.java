package me.xiezefan.easyim.server.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.ServiceDao;
import me.xiezefan.easyim.server.model.Lecture;
import me.xiezefan.easyim.server.model.Library;
import me.xiezefan.easyim.server.model.Timetable;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.BookDetailVo;
import me.xiezefan.easyim.server.resource.vo.BookVo;
import me.xiezefan.easyim.server.resource.vo.CourseVo;
import me.xiezefan.easyim.server.resource.vo.LectureVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;

/**
 * Service Service
 */
@Service
public class ServiceService {

	@Resource
	public ServiceDao serviceDao;

	/******************* 图书 ****************************/
	public BookDetailVo searchBookDetail(RequestForm requestForm) throws ServiceException {
		Library library =serviceDao.searchBookDetail(requestForm.quest_id);
		if(null == library)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return new BookDetailVo(library);
	}

	public List<BookVo> searchBook(RequestForm requestForm) throws ServiceException {
		List<Library> list =serviceDao.searchBook(requestForm.content);
		if(null == list)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		List<BookVo> result = new LinkedList<BookVo>();
		for(Library library:list)
		{
			result.add(new BookVo(library));
		}
		return result;
	}
	/******************* 讲座 ****************************/
	public LectureVo getLectureDetail(RequestForm requestForm) throws ServiceException {
		Lecture list =serviceDao.getLectureDetail(requestForm.quest_id);
		if(null == list)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return new LectureVo(list,1);
	}
	
	public List<LectureVo> getLectureList(RequestForm requestForm) throws ServiceException {
		List<Lecture> list =serviceDao.getLectureList(requestForm.content,requestForm.row*15);
		if(null == list)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		List<LectureVo> result = new LinkedList<LectureVo>();
		for(Lecture lecture:list)
		{
			result.add(new LectureVo(lecture,0));
		}
		return result;
	}
	
	/******************* 反馈 ****************************/
	public void sendFeed(RequestForm requestForm) throws ServiceException {
		serviceDao.sendFeed(requestForm.content,requestForm.quest_id);
	}
	
	/******************* 课表 ****************************/
	public List<CourseVo> getTimeTable(RequestForm requestForm) throws ServiceException {
		List<Timetable> list =serviceDao.getTimeTable(requestForm.content);
		if(null == list)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		List<CourseVo> result = new LinkedList<CourseVo>();
		for(Timetable timetable:list)
		{
			result.add(new CourseVo(timetable));
		}
		return result;
	}
	
	
	
}
