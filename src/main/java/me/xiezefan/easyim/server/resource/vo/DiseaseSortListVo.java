package me.xiezefan.easyim.server.resource.vo;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Department;
import me.xiezefan.easyim.server.model.Part;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiseaseSortListVo {
	
    public String title;
	public List<String> subName;
	
	
	public DiseaseSortListVo(Part part)
	{
		this.title = part.getPartName();
		subName = new LinkedList<String>();
		String[] sub = part.getRelatedOrgans().split(",");
		for(String str :sub)
		{
			subName.add(str);
		}
	}
	
	public DiseaseSortListVo(Department department)
	{
		this.title = department.getDepartName();
		subName = new LinkedList<String>();
		String[] sub = department.getSubName().split(",");
		for(String str :sub)
		{
			subName.add(str);
		}
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getSubName() {
		return subName;
	}
	public void setSubName(List<String> subName) {
		this.subName = subName;
	}

	

	

}
