package me.xiezefan.easyim.server.service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.ConsultDao;
import me.xiezefan.easyim.server.dao.FriendshipDao;
import me.xiezefan.easyim.server.dao.MessageDao;
import me.xiezefan.easyim.server.dao.RequestDao;
import me.xiezefan.easyim.server.dao.UserDao;
import me.xiezefan.easyim.server.model.Consult;
import me.xiezefan.easyim.server.model.Friendship;
import me.xiezefan.easyim.server.model.RequestFriend;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.ConsultPictureForm;
import me.xiezefan.easyim.server.resource.form.FriendshipAddForm;
import me.xiezefan.easyim.server.resource.form.PatienInforBean;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.AddressListVo;
import me.xiezefan.easyim.server.resource.vo.RequestFriendVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import me.xiezefan.easyim.server.util.DateUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Friendship Service
 */
@Service
public class ConsultService {

	@Resource
	public ConsultDao consultDao;

	public void saveConsultPicture(ConsultPictureForm form, String userid) throws ServiceException {
		System.out.println("form"+form);
		consultDao.insert(form.getDescribe(), form.isPrescription() ? "YES" : "NO", form.isHistory() ? "YES" : "NO",
				form.getImages(), userid);
		System.out.println("*********");
		Consult con = consultDao.selectByUserId(userid);
		System.out.println("++++++++++++");
		for (PatienInforBean bean : form.patienInforBeans) {
			consultDao.insertConsultUser(con.getId(),bean);
		}

		

	}

}
