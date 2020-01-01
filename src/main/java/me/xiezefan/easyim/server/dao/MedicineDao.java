package me.xiezefan.easyim.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import me.xiezefan.easyim.server.model.Medicine;

/**
 * Medicine  Dao
 */
public interface MedicineDao {
   
    
    @Select("select * from tb_medicine where c2=#{classifyName} ORDER BY goods_id LIMIT #{start},20")
    @ResultMap("MedicineResultMap")
    public List<Medicine> getAllMedicine(@Param("classifyName") String classifyName,@Param("start") int start);
    
    @Select("select * from tb_medicine where ${scope} like #{content} ORDER BY goods_id LIMIT #{start},20")
    @ResultMap("MedicineResultMap")
    public List<Medicine> getAllMedicineByKey(@Param("scope") String scope,@Param("content") String content,@Param("start") int start);
    
    @Select("select * from tb_medicine where goods_id=#{id}")
    @ResultMap("MedicineResultMap")
    public Medicine getMedicineDetailById(@Param("id") String id);
    
    
    @Select("select * from tb_medicine where goods_name=#{content} limit 1")
    @ResultMap("MedicineResultMap")
    public Medicine getMedicineDetailByName(@Param("content") String content);
    
    
    @Select("select * from tb_medicine where bar_code=#{bar_code} limit 1")
    @ResultMap("MedicineResultMap")
    public Medicine getMedicineDetailByCode(@Param("bar_code") String bar_code);
    
    

}

