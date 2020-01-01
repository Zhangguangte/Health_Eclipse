package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginForm {
    public String device_id;
    public String account;
    public String password;

    public boolean validate() {
        return !StringUtil.isEmpty(account)
                && !StringUtil.isEmpty(password);
    }
}
