package me.xiezefan.easyim.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.xiezefan.easyim.server.model.Department;
import me.xiezefan.easyim.server.model.Disease;
import me.xiezefan.easyim.server.model.Part;

/**
 * Disease Dao
 */
public interface DiseaseDao {
    
    //疾病部位
    public List<Part> getDiseasePartList();
    
    //疾病科室
    public List<Department> getDiseaseDepartmentList();
    
    
    //根据部位获得疾病
    public List<Disease> getDiseasePart(@Param("content") String content,@Param("row") int row);
    
    //根据科室获得疾病
    public List<Disease> getDiseaseDepartment(@Param("content") String content,@Param("row") int row);
    
    //根据关键字获得疾病
    public List<Disease> getDiseaseByKeyWord(@Param("content") String content,@Param("row") int row);
    
    
    //获得疾病详细
    public Disease getDiseaseDetailById(@Param("id") String id); //根据ID
    public Disease getDiseaseDetailByName(@Param("content") String content);  //根据名称
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

