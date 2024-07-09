package com.hyf.test.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkActivityInfoGetRequest;
import com.taobao.api.response.TbkActivityInfoGetResponse;

/**
 * @author baB_hyf
 * @date 2022/08/15
 */
public class ElemeApi {

    // eleme   minigrogram id : wxece3a9a4c82f58c9
    // meituan minigrogram id : wxde8ac0a21135c07d

    // mm_xxx_xxx_xxx 仅三方分成场景使用
    public static final String MID = "mm_3181275383_2698550269_114416350153";
    // mm_xxx_xxx_xxx的第三位
    public static final long AdzoneId = 114416350153L;
    public static final String url    = "https://eco.taobao.com/router/rest";
    public static final String appkey = "33964887";
    public static final String secret = "e3d7aafa411afdc8a67cac1de4af6e8e";

    // 	官方活动会场ID，从淘宝客后台“我要推广-活动推广”中获取
    public static final String MaterialId = "1571715733668"; // 直接领取红包 - 每日领
    public static final String MaterialId2 = "1585018034441"; // 每日生鲜红包 - 零售 百亿补贴天天抢

    public static void main(String[] args) throws ApiException {

        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);

        TbkActivityInfoGetRequest request = null;
        TbkActivityInfoGetResponse response = null;
        JSONObject rtn = null;

        // 1

        // https://open.taobao.com/api.htm?docId=48340&docType=2&scopeId=18294
        request = new TbkActivityInfoGetRequest();
        request.setActivityMaterialId(MaterialId);
        request.setAdzoneId(AdzoneId);
        request.setSubPid(MID);
        // req.setRelationId(123L);
        request.setUnionId("eleme"); // custom

        response = client.execute(request);

        rtn = JSONObject.parseObject(response.getBody());
        System.out.println(rtn.toString(SerializerFeature.PrettyFormat));

        // 2

        request = new TbkActivityInfoGetRequest();
        request.setActivityMaterialId(MaterialId2);
        request.setAdzoneId(AdzoneId);
        request.setSubPid(MID);
        // req.setRelationId(123L);
        request.setUnionId("eleme"); // custom

        response = client.execute(request);

        rtn = JSONObject.parseObject(response.getBody());
        System.out.println(rtn.toString(SerializerFeature.PrettyFormat));
    }
}
