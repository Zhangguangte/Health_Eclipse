package me.xiezefan.easyim.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import me.xiezefan.easyim.server.model.MedicineClassify;

/**
 * MedicineClassify  Dao
 */
public interface MedicineClassifyDao {
   
    @Select("select * from tb_medicine_classify")
    public List<MedicineClassify> getClassify();
    
}

