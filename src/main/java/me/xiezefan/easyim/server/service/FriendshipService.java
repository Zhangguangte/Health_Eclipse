package me.xiezefan.easyim.server.service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.FriendshipDao;
import me.xiezefan.easyim.server.dao.MessageDao;
import me.xiezefan.easyim.server.dao.RequestDao;
import me.xiezefan.easyim.server.dao.UserDao;
import me.xiezefan.easyim.server.model.Friendship;
import me.xiezefan.easyim.server.model.RequestFriend;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.FriendshipAddForm;
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
 * Created by xiezefan-pc on 2015/4/1 0001.
 */
@Service
public class FriendshipService {

    @Resource
    public FriendshipDao friendshipDao;
    @Resource
    public UserDao userDao;
    @Resource
    public RequestDao requestDao;
    @Resource
	private MessageDao messageDao;
    
    @Transactional
    public void save(FriendshipAddForm dataForm, String userId) throws ServiceException {
        if (!dataForm.validate()) {
            throw new IllegalArgumentException("Invalid Parameter");
        }

        User user = findAndValidateUser(userId);
        User friend = userDao.findById(dataForm.friend_id);
        if (friend == null) {
            throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
        }

        // validate user's friendship is exist
        Long count = friendshipDao.validateExist(userId, dataForm.friend_id);
        if (count > 0) {
            return;
        }

        Friendship friendship = new Friendship();
        friendship.setId(UUID.randomUUID().toString());
        friendship.setUserId(userId);
        friendship.setFriend(friend);
        friendshipDao.insert(friendship);

        // validate friend's friendship is exist
        count = friendshipDao.validateExist(dataForm.friend_id, userId);
        if (count > 0) {
            return;
        }
        Friendship anotherFriendship = new Friendship();
        anotherFriendship.setId(UUID.randomUUID().toString());
        anotherFriendship.setUserId(friend.getId());
        anotherFriendship.setFriend(user);
        friendshipDao.insert(anotherFriendship);
    }

    public void delete(String userId, String friendId) {
        friendshipDao.deleteBoth(userId, friendId);
    }

    public List<UserVo> findByUserId(String userId) {
        List<UserVo> result = new ArrayList<UserVo>();
        result = null;
        List<Friendship> friendshipList = friendshipDao.findByUserId(userId);
        for (Friendship friendship : friendshipList) {
            if (friendship.getFriend() == null) {
                continue;
            }
            result.add(new UserVo(friendship.getFriend()));
        }
        System.out.println("result:"+result);
        return result;
    }



    private User findAndValidateUser(String userId) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
        }
        return user;
    }

    ///////////////////////////////////////////////////
    
    //获得所有好友，通讯录
    public List<AddressListVo> allFriend(String userId) {
		List<AddressListVo> result = new ArrayList<AddressListVo>();
		List<User> users = userDao.allFriend(userId);
		for (User user : users) {
			result.add(new AddressListVo(user));
		}
		return result;
	}
  
    //获得所有好友请求，好友通知
    public List<RequestFriendVo> requestFriends(String userId) {
		List<RequestFriendVo> result = new ArrayList<RequestFriendVo>();
		List<RequestFriend> requestFriends = requestDao.requestFriends(userId);
		for (RequestFriend requestFriend : requestFriends) {
			result.add(new RequestFriendVo(requestFriend));
		}
		return result;
	}

    //清空好友通知
    public void clearRequestFriends(String userId) {
    	requestDao.clearRequestFriends1(userId);
    	requestDao.clearRequestFriends2(userId);
  	}

    //发送好友请求，添加好友消息
    public void sendRequestFriend(RequestForm requestForm,String userid) throws ServiceException{
    	if(!requestForm.validate())
            throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
    	int count;
    	count = requestDao.isExistRequst(userid,requestForm.quest_id);
    	if(count >0)
    		requestDao.updateRequestTime(requestForm.content,requestForm.quest_id,DateUtil.getStringDate(),userid);
    	else
    		requestDao.sendRequestFriend(requestForm.quest_id,requestForm.content,DateUtil.getStringDate(),userid);
    }
    
    
    //同意好友请求，新好友
    public void saveRequestFriend(RequestForm requestForm,String userid) throws ServiceException{
    	requestDao.saveRequestFriend(userid,requestForm.quest_id);
    	String roomid=UUID.randomUUID().toString().replace("-", "0").substring(0,8);
    	messageDao.addMessageList(requestForm.map.get("s_nickname"),roomid,userid);
    	messageDao.addMessageList(requestForm.map.get("f_nickname"),roomid,requestForm.quest_id);
    	messageDao.newfriend(DateUtil.getStringDate(), roomid);    
    }
    
    //拒绝好友请求，新好友
    public void refuseRequestFriend(RequestForm requestForm,String userid) throws ServiceException{
    	requestDao.refuseRequestFriend(userid,requestForm.quest_id);
    }

}
