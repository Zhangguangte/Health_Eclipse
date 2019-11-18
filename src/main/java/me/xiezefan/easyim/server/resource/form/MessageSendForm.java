package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

import java.util.Map;

/**
 * Message Send Form
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageSendForm {
    public String to;
    public String type;
    public Map<String, Object> content;

    public boolean validate() {
        return !StringUtil.isEmpty(to)
                && !StringUtil.isEmpty(type)
                && content != null
                && content.size() > 0;
    }
}
