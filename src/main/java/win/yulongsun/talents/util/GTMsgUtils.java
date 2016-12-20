package win.yulongsun.talents.util;

import com.gexin.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import win.yulongsun.talents.model.User;

/**
 * Created by sunyulong on 2016/12/20.
 */
public class GTMsgUtils {
    static String appId        = "F9PVBw1Oz29uS98WReT6F8";
    static String appKey       = "zvJbaPnYNJ6KmKfYGGuAi6";
    static String masterSecret = "NGI8nPHBK2AKOTHVfv5gU2";
    static String host         = "http://sdk.open.api.igexin.com/apiex.htm";
    private static IGtPush push;


    private GTMsgUtils() {
    }

    /**
     * 推送通知栏消息
     *
     * @param alias
     * @param content
     */
    public static PushResult pushMsgToSingle(String alias, String content) {
        push = new IGtPush(host, appKey, masterSecret);
        //tmp
        TransmissionTemplate tmp = new TransmissionTemplate();
        tmp.setAppId(appId);
        tmp.setAppkey(appKey);
        tmp.setTransmissionType(2);
        tmp.setTransmissionContent(content);
        //msg
        SingleMessage msg = new SingleMessage();
        msg.setOffline(true);// 离线有效时间，单位为毫秒，可选
        msg.setOfflineExpireTime(24 * 3600 * 1000);
        msg.setData(tmp);
        msg.setPushNetWorkType(0);// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        //target
        Target target = new Target();
        target.setAppId(appId);
        target.setAlias(alias);
        //push
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(msg, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(msg, target, e.getRequestId());
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


    static class PushResult {
        public boolean success;
        public String  message;
    }


    public static void main(String[] args) {
        User user = new User();
        user.setUserName("zhansgan");
        user.setUserToken("123");
        GTMsgUtils.pushMsgToSingle("1", JSON.toJSONString(user));
    }
}
