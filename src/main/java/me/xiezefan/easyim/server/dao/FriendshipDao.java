package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.model.Friendship;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Friend Ship Dao
 */
public interface FriendshipDao {
    @Insert("insert into tb_friendship(id, user_id, friend_id) values(#{id}, #{userId}, #{friend.id})")
    public void insert(Friendship friendship);


    @Delete("delete from tb_friendship where (user_id=#{user_id} and friend_id=#{friend_id}) or (user_id=#{friend_id} and friend_id=#{user_id})")
    public void deleteBoth(@Param("user_id")String userId, @Param("friend_id")String friendId);

    public List<Friendship> findByUserId(@Param("user_id")String userId);

    public Friendship findById(@Param("id")String id);

    @Select("select count(*) from tb_friendship where user_id=#{user_id} and friend_id=#{friend_id}")
    public Long validateExist(@Param("user_id")String userId, @Param("friend_id")String friendId);

    
    ////////////////////////////////////////////////////////////
    
    @Select("select count(*) from tb_friendship where (user_id=#{user_id} and user_id2=#{friend_id}) or (user_id=#{friend_id} and user_id2=#{user_id})")
    public int isFriendship(@Param("user_id")String userId, @Param("friend_id")String friendId);
    
    

}

