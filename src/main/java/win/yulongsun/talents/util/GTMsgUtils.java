package win.yulongsun.talents.util;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style1;
import win.yulongsun.talents.model.Msg;
import win.yulongsun.talents.model.User;

/**
 * Created by sunyulong on 2016/12/20.
 */
public class GTMsgUtils {
    static String appId        = "F9PVBw1Oz29uS98WReT6F8";
    static String appKey       = "zvJbaPnYNJ6KmKfYGGuAi6";
    static String masterSecret = "NGI8nPHBK2AKOTHVfv5gU2";
    static String host         = "http://sdk.open.api.igexin.com/apiex.htm";


    private GTMsgUtils() {
    }

    /**
     * 推送通知栏消息
     */
    public static PushResult pushMsgToSingle(Msg msg) {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        //tmp
//        TransmissionTemplate tmp = new TransmissionTemplate();
//        tmp.setAppId(appId);
//        tmp.setAppkey(appKey);
//        tmp.setTransmissionType(2);
//        tmp.setTransmissionContent(msg.getMsgContent());

        NotificationTemplate tmp = new NotificationTemplate();
        tmp.setTransmissionContent(msg.getMsgContent());
        tmp.setTransmissionType(2);
        tmp.setAppId(appId);
        tmp.setAppkey(appKey);
        Style1 style1 = new Style1();
        style1.setTitle(msg.getMsgTitle());
        style1.setText(msg.getMsgType());
        tmp.setStyle(style1);

        //singleMessage
        SingleMessage singleMessage = new SingleMessage();
        singleMessage.setOffline(true);// 离线有效时间，单位为毫秒，可选
        singleMessage.setOfflineExpireTime(24 * 3600 * 1000);
        singleMessage.setData(tmp);
        singleMessage.setPushNetWorkType(0);// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        //target
        Target target = new Target();
        target.setAppId(appId);
        target.setAlias(String.valueOf(msg.getMsgToId()));
        //push
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(singleMessage, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(singleMessage, target, e.getRequestId());
        }
        //result
        PushResult pushResult = new PushResult();
        if (ret != null) {
            String result = (String) ret.getResponse().get("result");
            System.out.println(ret.getResponse().toString());
            if ("ok".equals(result)) {
                pushResult.success = true;
                pushResult.message = "推送成功";
            } else {
                pushResult.success = false;
                pushResult.message = "推送失败";
            }
        } else {
            pushResult.success = false;
            pushResult.message = "服务器异常";
        }
        return pushResult;
    }


    public static class PushResult {
        public boolean success;
        public String  message;
    }


    public static void main(String[] args) {
        User user = new User();
        user.setUserName("zhansgan222");
        user.setUserToken("2222");
//        GTMsgUtils.pushMsgToSingle("2", JSON.toJSONString(user));
    }
}
