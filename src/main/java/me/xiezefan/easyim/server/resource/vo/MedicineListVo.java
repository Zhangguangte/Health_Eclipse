package me.xiezefan.easyim.server.resource.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Medicine;
import me.xiezefan.easyim.server.model.News;
import me.xiezefan.easyim.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineListVo {
	
	public String id;
	public String price;
	public String goodName;
	public String description;
	public String isOct; // 非处方药
	public String image;

	public MedicineListVo() {
	}

	public MedicineListVo(Medicine medicine) {
		this.id = medicine.getGoodsId();
		this.price = medicine.getPrice();
		this.goodName = medicine.getGoodsName();
		this.description = medicine.getManufacturer();
		this.isOct = "1".equals(medicine.getIsOtc())?"处方药":"非处方药";
		this.image = medicine.getLogo();
	}

}
