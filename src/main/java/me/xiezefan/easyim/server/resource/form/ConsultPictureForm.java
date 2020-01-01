package me.xiezefan.easyim.server.resource.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import me.xiezefan.easyim.server.util.StringUtil;

/**
 * Message Send Form
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultPictureForm {
	public String describe;
	public List<String> images;
	public List<PatienInforBean> patienInforBeans;
	public boolean prescription;
	public boolean history;

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getImages() {
		StringBuffer sBuffer = new StringBuffer();
		for (String str : images) {
			sBuffer.append(str + ",");
		}
		if (sBuffer.length() > 0)
			sBuffer.setLength(sBuffer.length() - 1);
		return sBuffer.toString();
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<PatienInforBean> getPatienInforBeans() {
		return patienInforBeans;
	}

	public void setPatienInforBeans(List<PatienInforBean> patienInforBeans) {
		this.patienInforBeans = patienInforBeans;
	}

	public boolean isPrescription() {
		return prescription;
	}

	public void setPrescription(boolean prescription) {
		this.prescription = prescription;
	}

	public boolean isHistory() {
		return history;
	}

	public void setHistory(boolean history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "ConsultPictureForm [describe=" + describe + ", images=" + images + ", patienInforBeans="
				+ patienInforBeans + ", prescription=" + prescription + ", history=" + history + "]";
	}

}
