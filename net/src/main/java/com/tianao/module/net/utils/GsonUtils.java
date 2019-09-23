package com.tianao.module.net.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tianao.module.net.entity.httpResult.AppResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zohar on 2017/9/6.
 * desc:
 */
public class GsonUtils {

    /**
     * json 转 实体
     *
     * @param json     json
     * @param classOfT 类型
     * @param <T>      泛型
     * @return 实体
     */
    public static <T> T jsonToClass(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    /**
     * json 转 list
     *
     * @param json     json
     * @param classOfT list元素类型
     * @param <T>      泛型
     * @return list
     */
    public static <T> List<T> jsonToList(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        ArrayList<T> retList = new ArrayList<>();
        if (!TextUtils.isEmpty(json)) {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                retList.add(gson.fromJson(elem, classOfT));
            }
        }
        return retList;
    }

    /**
     * json 转 AppResult
     *
     * @param json json
     * @param <T>  泛型
     */
    public static <T> AppResult<T> jsonToAppResult(String json) {
        TypeToken re = new TypeToken<AppResult<T>>() {
        };
        return new Gson().fromJson(json, re.getType());
    }

    public static <T> AppResult<T> jsonToAppResult1(String json) {
        Type type = new TypeToken<AppResult<T>>() {
        }.getType();
        AppResult<T> serviceResult = new Gson().fromJson(json, type);
        T _t = serviceResult.data;
        return serviceResult;
    }

    /**
     * json 转 实体
     *
     * @param js     json
     * @param classOfT 类型
     * @param <T>      泛型
     * @return 实体
     */
    public static <T> T jsonToClass(Object js, Class<T> classOfT) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = gson.toJson(js);
        return gson.fromJson(jsonString, classOfT);
    }

    public static <T> List<T> jsonToList(Object js, Class<T> classOfT) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = gson.toJson(js);
        return jsonToList(jsonString, classOfT);
    }
}
