package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterFrom {
	  public String password;
	    public String phone;

    public boolean validate() {
        return !StringUtil.isEmpty(phone) && !StringUtil.isEmpty(password);
    }
}
