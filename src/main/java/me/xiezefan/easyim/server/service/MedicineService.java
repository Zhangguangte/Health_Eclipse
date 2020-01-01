package me.xiezefan.easyim.server.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.MedicineClassifyDao;
import me.xiezefan.easyim.server.dao.MedicineDao;
import me.xiezefan.easyim.server.model.Medicine;
import me.xiezefan.easyim.server.model.MedicineClassify;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.MedicineDetailVo;
import me.xiezefan.easyim.server.resource.vo.MedicineListVo;
import me.xiezefan.easyim.server.resource.vo.MedicineVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;

/**
 * Medicine Service
 */
@Service
public class MedicineService {

	@Resource
	private MedicineDao medicineDao;

	@Resource
	private MedicineClassifyDao medicineClassifyDao;

	
	// 获得所有的分类
	public List<MedicineVo> getClassify() throws ServiceException {
		List<MedicineVo> result = new LinkedList<MedicineVo>();
		List<MedicineClassify> list = medicineClassifyDao.getClassify();
		if (list == null || list.size() == 0) {
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		}
		for (MedicineClassify mClassify : list) {
			result.add(new MedicineVo(mClassify));
		}
		return result;
	}

	// 获得所有的药品根据分类
	public List<MedicineListVo> getAllMedicine(RequestForm requestForm) throws ServiceException {
		List<MedicineListVo> result = new LinkedList<MedicineListVo>();
		List<Medicine> list = medicineDao.getAllMedicine(requestForm.content, requestForm.row * 20);
		if (list == null || list.size() == 0) {
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		}
		for (Medicine medicine : list) {
			result.add(new MedicineListVo(medicine));
		}
		return result;
	}

	// 获得所有的药品根据关键字和范围
	public List<MedicineListVo> getAllMedicineByKey(RequestForm requestForm) throws ServiceException {
		List<MedicineListVo> result = new LinkedList<MedicineListVo>();
		List<Medicine> list;
		if ("1".equals(requestForm.quest_id)) {
			list = medicineDao.getAllMedicineByKey(requestForm.quest_id, requestForm.content, requestForm.row * 20);
		} else if ("0".equals(requestForm.quest_id)) {
			list = medicineDao.getAllMedicineByKey(requestForm.quest_id, requestForm.content, requestForm.row * 20);
		} else {
			list = medicineDao.getAllMedicineByKey(requestForm.quest_id, requestForm.content, requestForm.row * 20);
		}
		if (list == null || list.size() == 0) {
			throw new ServiceException(ResponseBuilder.ERROR_MEDICINE_NOT_FOUND);
		}
		for (Medicine medicine : list) {
			result.add(new MedicineListVo(medicine));
		}
		return result;
	}

	// 获得药品详细信息
	public MedicineDetailVo getMedicineDetail(RequestForm requestForm) throws ServiceException {
		Medicine medicine = null;
		if ("ID".equals(requestForm.quest_id)) {
			medicine = medicineDao.getMedicineDetailById(requestForm.content);
		} else if ("NAME".equals(requestForm.quest_id)) {
			medicine = medicineDao.getMedicineDetailByName(requestForm.content);
		}else if ("CODE".equals(requestForm.quest_id)) {
			medicine = medicineDao.getMedicineDetailByCode(requestForm.content);
		}
		if (medicine == null) {
			throw new ServiceException(ResponseBuilder.ERROR_MEDICINE_NOT_FOUND);
		}
		return new MedicineDetailVo(medicine);
	}

}
