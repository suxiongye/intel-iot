package cn.edu.bjut.util;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * Created by ray on 2016/9/14.
 */
public class SMSUtil {

    private static String url = "http://gw.api.taobao.com/router/rest";

    private static String appkey = "23454304";

    private static String secret = "5cc5310992ed831327228a1521d68137";

    public static  String template_press = "SMS_14920400";
    public static  String template_fire = "SMS_14880306";


    public  static void sendSms(String phone, String template, String sms) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");
        req.setSmsFreeSignName("Nest应用");
        req.setSmsParamString(sms);
        req.setRecNum(phone);
        req.setSmsTemplateCode(template);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }

}
