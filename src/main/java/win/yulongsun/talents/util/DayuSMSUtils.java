package win.yulongsun.talents.util;


import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

//阿里大鱼验证码工具类
public class DayuSMSUtils {

    private static final String HTTP_TEST  = "http://gw.api.tbsandbox.com/router/rest";//沙箱环境
    private static final String HTTP       = "http://gw.api.taobao.com/router/rest";//正式环境
    private static final String API_KEY    = "23568245";
    private static final String APP_SECRET = "672d1dfcec0f1d452c6ca1cc85e6ff48";

    /**
     * 发送短信验证码
     *
     * @param phone
     * @param code
     * @return
     */
    public static BizResult sendSms(String phone, int code) throws ApiException {
        TaobaoClient                     client = new DefaultTaobaoClient(HTTP, API_KEY, APP_SECRET);
        AlibabaAliqinFcSmsNumSendRequest req    = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        //  验证码${code}，您正在尝试修改${product}登录密码，请妥善保管账户信息。

        req.setSmsParamString("{\"code\":\"" + code + "\"}");
        req.setSmsFreeSignName("校推");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_34435111");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        rsp = client.execute(req);
        return rsp.getResult();
    }


    public static void main(String args[]) {
        //测试
        try {
            BizResult bizResult = sendSms("13750843926", 3333);
            System.out.println(bizResult.getMsg());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
