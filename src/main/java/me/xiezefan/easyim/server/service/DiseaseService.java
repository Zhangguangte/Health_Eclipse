package me.xiezefan.easyim.server.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.DiseaseDao;
import me.xiezefan.easyim.server.model.Department;
import me.xiezefan.easyim.server.model.Disease;
import me.xiezefan.easyim.server.model.Part;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.DiseaseDetailVo;
import me.xiezefan.easyim.server.resource.vo.DiseaseSortListVo;
import me.xiezefan.easyim.server.resource.vo.DiseaseSortVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;

/**
 * Disease Service
 */
@Service
public class DiseaseService {

	@Resource
	private DiseaseDao diseaseDao;

	// 疾病分类
	public List<DiseaseSortListVo> getDiseaseSortList(RequestForm requestForm) throws ServiceException {
		List<DiseaseSortListVo> result = new LinkedList<DiseaseSortListVo>();
		if ("0".equals(requestForm.quest_id)) {
			List<Part> parts = diseaseDao.getDiseasePartList();
			for (Part part : parts) {
				result.add(new DiseaseSortListVo(part));
			}
		} else {
			List<Department> departments = diseaseDao.getDiseaseDepartmentList();
			for (Department department : departments) {
				result.add(new DiseaseSortListVo(department));
			}
		}

		return result;
	}

	// 分类疾病
	public List<DiseaseSortVo> getDiseaseSort(RequestForm requestForm) throws ServiceException {
		List<DiseaseSortVo> result = new LinkedList<DiseaseSortVo>();
		List<Disease> diseases;
		if ("0".equals(requestForm.quest_id)) {
			diseases = diseaseDao.getDiseasePart(requestForm.content, requestForm.row*20);
		} else if ("1".equals(requestForm.quest_id)){
			diseases = diseaseDao.getDiseaseDepartment(requestForm.content, requestForm.row*20);
		}else{
			diseases = diseaseDao.getDiseaseByKeyWord(requestForm.content, requestForm.row*15);
		}
		if (null == diseases) {
			throw new ServiceException(ResponseBuilder.ERROR_DISEASE_NOT_FOUND);
		}
		for (Disease disease : diseases) {
			result.add(new DiseaseSortVo(disease));
		}
		
		return result;
	}
	
	// 疾病详细
	public DiseaseDetailVo getDiseaseDetail(RequestForm requestForm) throws ServiceException {
		
		
		Disease disease =null;
		if ("ID".equals(requestForm.quest_id)) {
			disease = diseaseDao.getDiseaseDetailById(requestForm.content);
		} else if("NAME".equals(requestForm.quest_id)){
			disease = diseaseDao.getDiseaseDetailByName(requestForm.content);
		}
		
		if(null == disease)
		{
			throw new ServiceException(ResponseBuilder.ERROR_DISEASE_NOT_FOUND);
		}
		return new DiseaseDetailVo(disease);
	}

}
