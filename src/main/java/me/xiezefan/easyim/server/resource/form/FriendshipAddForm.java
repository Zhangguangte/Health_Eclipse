package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendshipAddForm {
    public String friend_id;

    public boolean validate() {
        return !StringUtil.isEmpty(friend_id);
    }
}
