package com.tianao.module.net.utils;

import com.tianao.module.net.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DecodeHelper {

    public static String decodeData(String rootText) {
        try {
            JSONObject jsonObject = new JSONObject(rootText);
            String enIv = jsonObject.getString("data");
            return decodeDataText(enIv);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decodeDataText(String dataText) {
        String firstJson = decodeByBase64(dataText);
        //Log.e("解密 -- 初始数据解码：", firstJson);

        try {
            JSONObject jsonObject = new JSONObject(firstJson);
            String enIv = jsonObject.getString("iv");
            String enValue = jsonObject.getString("value");

            //固定key
            String key = BuildConfig.API_KEY;
            //Log.e("解密 -- key：", key);

            //解密后的iv
            String iv = decodeByBase64(enIv);
            //Log.e("解密 -- iv：", iv);

            //解密后的value
            String resultJson = decodeByAES(enValue, key, iv);
            //Log.e("解密 -- value：", resultJson);

            return resultJson;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decodeByBase64(String enData) {
        byte[] deByte = Base64.decode(enData);
        return new String(deByte);
    }

    private static String decodeByAES(String enData, String key, String iv) {
        String deText = AesUtil.aesDecrypt(enData, key, iv);
        return deText;
    }
}
