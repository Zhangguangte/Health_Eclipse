package me.xiezefan.easyim.server.common;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

import java.util.Map;

/**
 * Message Push Common Helper
 */
public class JPushHelper {

    private static JPushClient client = new JPushClient("24a3428a64e8f64a99c57ad3", "df16f03f5b10c237dd606e45");


    public static void sendMsg(String targetAlias, String targetId, me.xiezefan.easyim.server.model.Message msg) {

        Message.Builder msgBuilder = Message.newBuilder()
                .setMsgContent(msg.getId())
                .setTitle(targetId)
                .setContentType(msg.getType());

        Map<String, Object> extras = msg.getContentMap();
        for (String key : extras.keySet()) {
            Object value = extras.get(key);
            if (value instanceof String) {
                msgBuilder.addExtra(key, (String) value);
            } else if (value instanceof Number) {
                msgBuilder.addExtra(key, (Number) value);
            } else if (value instanceof Boolean) {
                msgBuilder.addExtra(key, (Boolean) value);
            }
        }


        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(targetAlias))
                .setMessage(msgBuilder.build())
                .build();

        try {
            client.sendPush(payload);
        } catch (Exception e) {
            // TODO retry
            e.printStackTrace();
        }


    }




}
