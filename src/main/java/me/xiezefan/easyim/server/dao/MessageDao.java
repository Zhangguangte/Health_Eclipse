package me.xiezefan.easyim.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.xiezefan.easyim.server.model.Message;
import me.xiezefan.easyim.server.model.MessageList;
import me.xiezefan.easyim.server.model.News;
import me.xiezefan.easyim.server.resource.form.ChatForm;

/**
 * Message Dao
 */
public interface MessageDao {
	@Insert("insert into tb_message(id, from_id, to_id, type, content, create_time) values(#{id}, #{fromId}, #{toId}, #{type}, #{contentStr}, #{createTime})")
	public void save(Message message);

	@Select("select * from tb_message where id=#{id}")
	@ResultMap("MessageResultMap")
	public Message findById(@Param("user_id") String id);

	///////////////////////////////////////////////////////////
	public List<Message> lastMessage(@Param("user_id") String user_id);

	public List<MessageList> getDoctorRoom(@Param("user_id") String user_id);

	@Update("update tb_message_list set status = 'DELETE' where room_id=#{room_id}")
	public void deleteRoomId(@Param("room_id") String room_id);
	
	@Select("select * from tb_message where room_id=#{room_id} ORDER BY create_time DESC limit #{start},15")
	@ResultMap("MessageResultMap")
	public List<Message> allChatByRoomId(@Param("room_id") String room_id,@Param("start") int start);
	
	public MessageList searchRoomId(@Param("user_id1") String user_id1,@Param("user_id2") String user_id2);

	@Insert("insert into tb_message(from_id, type, content, create_time,room_id,file_path,sentStatus) values(#{userId}, #{chatForm.type}, #{chatForm.content}, #{chatForm.create_time},#{chatForm.room_id},#{chatForm.file_path},'SENT')")
	@ResultMap("MessageResultMap")
	public void insertContent(@Param("chatForm") ChatForm chatForm, @Param("userId") String userId);

	@Insert("insert into tb_message(from_id, type, content, create_time,room_id,file_path,sentStatus) values(#{userId}, #{chatForm.type}, #{content}, #{chatForm.create_time},#{chatForm.room_id},#{chatForm.file_path},'SENT')")
	@ResultMap("MessageResultMap")
	public void insertCard(@Param("content") String content,@Param("chatForm") ChatForm chatForm, @Param("userId") String userId);

	
	@Insert("insert into tb_message(content, create_time,room_id) values('你们已经成功加为好友', #{time}, #{roomid})")
	@ResultMap("MessageResultMap")
	public void newfriend(@Param("time") String time, @Param("roomid") String roomid);

	@Insert("insert into tb_message_list(room_id, user_id, status,unread,another_name) values(#{roomid}, #{userId},'SHOW',1,#{nickname})")
	@ResultMap("MessageListResultMap")
	public void addMessageList(@Param("nickname") String nickname, @Param("roomid") String roomid,
			@Param("userId") String userId);

	
	@Insert("insert into tb_message_list(room_id, user_id, status,unread,another_name) values(#{roomid}, #{userId},'DOCTOR',0,#{nickname})")
	@ResultMap("MessageListResultMap")
	public void addDoctorMessageList(@Param("nickname") String nickname, @Param("roomid") String roomid,
			@Param("userId") String userId);

	
	
	@Update("update tb_message_list set unread=0 where room_id=#{roomid} and user_id=#{user_id}")
	@ResultMap("MessageListResultMap")
	public void lookmessage(@Param("roomid") String roomid, @Param("user_id") String id);
	
	@Update("update tb_message_list set unread=unread+1 where room_id=#{roomid} and user_id!=#{user_id}")
	@ResultMap("MessageListResultMap")
	public void addUnread(@Param("roomid") String roomid, @Param("user_id") String id);
	
	public List<News> getAllNotice(@Param("userId") String userId,@Param("time") String time);

	@Delete("delete from tb_news_user where user_id = #{userId}")
	public void clearNotice(@Param("userId") String userId);
	
	@Delete("delete from tb_news_user where user_id = #{userId} and news_id= #{newsId}")
	public void deleteNotice(@Param("newsId") String newsId,@Param("userId") String userId);
	
	@Update("update tb_news_user set read_time = #{time},status='READ' where user_id = #{userId} and news_id= #{id}")
	public void lookNotice(@Param("id") String id,@Param("time") String time,@Param("userId") String userId);
	
}
