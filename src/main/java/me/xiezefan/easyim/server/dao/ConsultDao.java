package me.xiezefan.easyim.server.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import me.xiezefan.easyim.server.model.Consult;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.ConsultPictureForm;
import me.xiezefan.easyim.server.resource.form.PatienInforBean;

/**
 * Consult Dao
 */
public interface ConsultDao {

	@Insert("insert into tb_consult(descr, prescription, history, images, user_id) values(#{describe}, #{prescription}, #{history}, #{images}, #{user_id})")
	public void insert(@Param("describe") String describe, @Param("prescription") String prescription,
			@Param("history") String history, @Param("images") String images, @Param("user_id") String user_id);

	@Select("select id from  tb_consult where user_id =#{user_id} order by id desc limit 1 ")
	@ResultMap("ConsultResultMap")
	public Consult selectByUserId(String user_id);

	@Insert("insert into tb_consult_user(consult_id, name, card_id, sex, birthday, weight, allergy, history, liver,kidney)"
			+ " values(#{consult_id}, #{bean.name}, #{bean.card_id}, #{bean.sex}, #{bean.birthday}, #{bean.weight}, #{bean.allergy}, #{bean.history}, #{bean.liver}, #{bean.kidney})")
	public void insertConsultUser(@Param("consult_id") String consult_id,@Param("bean") PatienInforBean bean);

}
