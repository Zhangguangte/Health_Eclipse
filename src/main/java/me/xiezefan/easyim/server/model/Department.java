package me.xiezefan.easyim.server.model;

public class Department {
    private String id;
    private String departName;
    private String subName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departName=" + departName + ", subName=" + subName + "]";
	}
	

 
}
