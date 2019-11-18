package me.xiezefan.easyim.server.service;

import me.xiezefan.easyim.server.common.JPushHelper;
import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.MessageDao;
import me.xiezefan.easyim.server.dao.OfflineMessageDao;
import me.xiezefan.easyim.server.dao.UserDao;
import me.xiezefan.easyim.server.model.Message;
import me.xiezefan.easyim.server.model.MessageList;
import me.xiezefan.easyim.server.model.MessageStatus;
import me.xiezefan.easyim.server.model.OfflineMessage;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.ChatForm;
import me.xiezefan.easyim.server.resource.form.MessageSendForm;
import me.xiezefan.easyim.server.resource.form.MessageStatusUpdateForm;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.MessageListVo;
import me.xiezefan.easyim.server.resource.vo.MessageVo;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import me.xiezefan.easyim.server.util.DateUtil;
import me.xiezefan.easyim.server.util.JsonUtil;
import me.xiezefan.easyim.server.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

	@Resource
	private UserDao userDao;
	@Resource
	private MessageDao messageDao;
	@Resource
	private OfflineMessageDao offlineMessageDao;

	public MessageVo send(String userId, MessageSendForm dataForm) throws ServiceException {
		if (!dataForm.validate()) {
			throw new IllegalArgumentException("Invalid Parameter");
		}

		User user = findAndValidateUser(userId);

		char targetType = dataForm.to.charAt(0);

		Message msg = new Message();
		msg.setId(UUID.randomUUID().toString());
		msg.setFromId(user.getId());
		msg.setType(dataForm.type);
		msg.setContentMap(dataForm.content);
		msg.setCreateTime(DateUtil.getStringDate());
		messageDao.save(msg);

		if (targetType == 's') {
			return sendMsgToSingle(msg);
		} else if (targetType == 'g') {
			// un support , ignore
		} else {
			// ignore
		}

		return null;
	}

	private MessageVo sendMsgToSingle(Message msg) {

		OfflineMessage offlineMsg = new OfflineMessage();
		offlineMsg.setId(UUID.randomUUID().toString());
		offlineMsg.setMessage(msg);
		offlineMsg.setStatus(MessageStatus.SEND);
		offlineMsg.setCreateTime(new Date());
		offlineMessageDao.save(offlineMsg);
		// JPushHelper.sendMsg(targetId, msg.getToId(), msg);
		return new MessageVo(offlineMsg);
	}

	public List<MessageVo> getOfflineMessage(String userId) {
		List<OfflineMessage> messages = offlineMessageDao.findOffline(userId);
		List<MessageVo> result = new ArrayList<MessageVo>();
		for (OfflineMessage msg : messages) {
			result.add(new MessageVo(msg));
		}
		return result;
	}

	public void updateStatus(String userId, MessageStatusUpdateForm... dataForms) {
		for (MessageStatusUpdateForm dataForm : dataForms) {
			OfflineMessage msg = offlineMessageDao.findById(dataForm.id);
			MessageStatus status = MessageStatus.format(dataForm.status);
			if (msg == null || status == null || !msg.getUserId().equals(userId)
					|| msg.getStatus().ordinal() >= status.ordinal()) {
				continue;
			}
			msg.setStatus(status);
			offlineMessageDao.updateStatus(msg);
		}
	}

	public void updateStatus(String userId, String mid, MessageStatusUpdateForm dataForm) throws ServiceException {
		OfflineMessage msg = offlineMessageDao.findById(mid);
		MessageStatus status = MessageStatus.format(dataForm.status);
		if (msg == null) {
			throw new ServiceException(ResponseBuilder.ERROR_MESSAGE_NOT_FOUND);
		}
		if (status == null || msg.getStatus().ordinal() >= status.ordinal()) {
			throw new IllegalArgumentException("Invalid Parameter");
		}
		if (!msg.getUserId().equals(userId)) {
			throw new ServiceException(ResponseBuilder.ERROR_FORBIDDEN_FAIL);
		}
		msg.setStatus(status);
		offlineMessageDao.updateStatus(msg);
	}

	private User findAndValidateUser(String userId) throws ServiceException {
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
		}
		return user;
	}

	/////////////////////////////////////////////
	// 现存最后一条消息
	public List<MessageListVo> lastMessage(String userId) {
		List<MessageListVo> result = new ArrayList<MessageListVo>();
		List<Message> messages = messageDao.lastMessage(userId);
		for (Message m : messages) {
			result.add(new MessageListVo(m));
		}
		return result;
	}

	// 所有消息根据房间号
	public List<MessageListVo> allChatByRoomId(RequestForm requestForm, String userId) throws ServiceException {
		List<MessageListVo> result = new ArrayList<MessageListVo>();
		List<Message> messages = messageDao.allChatByRoomId(requestForm.quest_id,15*requestForm.row,15*requestForm.row+15);
		for (Message m : messages) {
			result.add(new MessageListVo(m));
		}
		if (result.size() == 0) {
			throw new ServiceException(ResponseBuilder.ERROR_MESSAGE_NOT_FOUND);
		}
		messageDao.lookmessage(requestForm.quest_id, userId);
		return result;
	}

	// 所有消息根据两个用户ID
	public List<MessageListVo> allChatByUid(RequestForm requestForm, String userId) throws ServiceException {
		MessageList messageList = messageDao.searchRoomId(requestForm.quest_id, userId);
		requestForm.quest_id = String.valueOf(messageList.getRoomId());
		return allChatByRoomId(requestForm, userId);
	}

	// 添加消息（文字、录音、图片）
	public void insertContent(ChatForm chatForm, String userId) throws ServiceException {
		if (!chatForm.validate()) {
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		}
		messageDao.insertContent(chatForm, userId);
		messageDao.addUnread(String.valueOf(chatForm.room_id), userId);
	}
	
	// 添加消息（名片）
	public UserVo insertCard(ChatForm chatForm, String userId) throws ServiceException {
		User user = userDao.findByAccount(chatForm.user_id);
		messageDao.insertCard(user.getAvatar()+","+user.getNickname()+","+user.getAccount(),chatForm, userId);
		messageDao.addUnread(String.valueOf(chatForm.room_id), userId);
		return new UserVo(user);
	}

	//房间号
	public MessageListVo searchRoomid(RequestForm requestForm, String userId) throws ServiceException {
		return new MessageListVo(messageDao.searchRoomId(requestForm.quest_id, userId));
	}

}
