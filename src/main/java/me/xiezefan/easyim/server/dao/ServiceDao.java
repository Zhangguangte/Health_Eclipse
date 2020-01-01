package me.xiezefan.easyim.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import me.xiezefan.easyim.server.model.Consult;
import me.xiezefan.easyim.server.model.Feedback;
import me.xiezefan.easyim.server.model.Lecture;
import me.xiezefan.easyim.server.model.Library;
import me.xiezefan.easyim.server.model.Timetable;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.ConsultPictureForm;
import me.xiezefan.easyim.server.resource.form.PatienInforBean;
import me.xiezefan.easyim.server.resource.vo.LectureVo;

/**
 * Service Dao
 */
public interface ServiceDao {

	/******************* 图书 ****************************/
	@Select("select * from  tb_library where name like #{name}")
	public List<Library> searchBook(@Param("name")String name);

	@Select("select * from  tb_library where id =#{id}")
	public Library searchBookDetail(@Param("id")String id);

	/******************* 讲座 ****************************/
	@Select("select * from  tb_lecture where id =#{id}")
	public Lecture getLectureDetail(@Param("id")String id);
	
	@Select("select * from  tb_lecture where college =#{college} order by date desc limit #{row},15")
	public List<Lecture> getLectureList(@Param("college")String college,@Param("row")int row);
	
	/******************* 反馈 ****************************/
	@Insert("insert into tb_feedback(advice,contract) values(#{advice},#{contract})")
	public void sendFeed(@Param("advice")String advice,@Param("contract")String contract);
	
	/******************* 课表 ****************************/
	@Select("select * from  tb_timetable where major =#{major} and state ='USING' ")
	public List<Timetable> getTimeTable(@Param("major")String major);
	

}
