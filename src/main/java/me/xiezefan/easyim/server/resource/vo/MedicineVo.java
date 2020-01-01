package me.xiezefan.easyim.server.resource.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.MedicineClassify;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineVo {
	public String typeName;
    public String classifyName;

	public MedicineVo() {
	}

	public MedicineVo(MedicineClassify mClassify ) {
		this.typeName=mClassify.getType();
		this.classifyName=mClassify.getClassify();
	}

	

	

}
