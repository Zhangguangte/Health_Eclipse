package me.xiezefan.easyim.server.resource.form;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestForm {
	public String quest_id;
	public String content;
	public Integer row;
	public Map<String, String> map;
	public boolean validate() {
		return !StringUtil.isEmpty(quest_id) && !StringUtil.isEmpty(content);
	}
}
