package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.model.Friendship;
import me.xiezefan.easyim.server.model.RequestFriend;
import me.xiezefan.easyim.server.resource.form.RequestForm;
import me.xiezefan.easyim.server.resource.vo.RequestFriendVo;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import javax.ws.rs.FormParam;

public interface RequestDao {

	///////////////////////////////////////////////
	public List<RequestFriend> requestFriends(@Param("user_id") String id);

	@Delete("delete from tb_request where (user_id=#{user_id} and friend_id=#{friend_id}) or (user_id=#{friend_id} and friend_id=#{user_id})")
	public void deleteBoth(@Param("user_id") String userId, @Param("friend_id") String friendId);

	@Update("update tb_request set status='CLEAR' where user_id = #{user_id} ")
	public void clearRequestFriends1(@Param("user_id") String userId);

	@Update("update tb_request set status1='CLEAR' where request_user_id =#{user_id} ")
	public void clearRequestFriends2(@Param("user_id") String userId);

	@Insert("insert into tb_request(user_id,request_user_id,content,create_time,status,status1) VALUES(#{user_id},#{quest_id},#{content},#{createTime},'REQUEST','F_REQUEST')")
	public void sendRequestFriend(@Param("quest_id") String quest_id,@Param("content") String content,@Param("createTime") String createTime ,@Param("user_id") String userId );

	@Select("select count(*) from tb_request where user_id=#{user_id} and request_user_id = #{quest_id}  and status1='F_REQUEST' and (status='REQUEST' or status='CLEAR') ")
	public int isExistRequst(@Param("user_id") String userId,@Param("quest_id") String quest_id);

	@Insert("update tb_request set create_time = #{createTime},content = #{content},status='REQUEST' where user_id = #{user_id} and request_user_id = #{quest_id} ")
	public void updateRequestTime(@Param("content") String content,@Param("quest_id") String quest_id,@Param("createTime") String createTime ,@Param("user_id") String userId );

	@Update("update tb_request set status='AGREE',status1='F_AGREE' where user_id=#{quest_id} and request_user_id = #{user_id} and status1='F_REQUEST'")
	public int saveRequestFriend(@Param("user_id") String userId,@Param("quest_id") String quest_id);
	
	@Update("update tb_request set status='REFUSE',status1='F_REFUSE' where user_id=#{quest_id} and request_user_id = #{user_id} and status1='F_REQUEST'")
	public int refuseRequestFriend(@Param("user_id") String userId,@Param("quest_id") String quest_id);




}
