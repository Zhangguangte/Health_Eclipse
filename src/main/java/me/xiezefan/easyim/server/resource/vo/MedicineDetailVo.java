package me.xiezefan.easyim.server.resource.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Medicine;
import me.xiezefan.easyim.server.model.News;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineDetailVo {

	public String id;
	public String image;
	public String detail;
	public String name;
	public String price;
	public String spec;
	public String unit;
	public String zhuzhi;
	public MedicineDetailVo() {
	}

	public MedicineDetailVo(Medicine medicine) {
		this.id = medicine.getGoodsId();
		this.price = medicine.getPrice();
		this.detail = medicine.getExplainBook();
		this.name = medicine.getGoodsName();
		this.spec = medicine.getSpec();
		this.unit = medicine.getUnit();
		this.zhuzhi = medicine.getZhuzhi();
		this.image = medicine.getLogo();
	}

}
