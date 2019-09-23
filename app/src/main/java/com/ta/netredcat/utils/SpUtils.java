package com.ta.netredcat.utils;

import android.text.TextUtils;
import android.util.Base64;

import com.ta.netredcat.App;

import net.grandcentrix.tray.AppPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 跨进程存储工具类
 */
public class SpUtils {
    private static AppPreferences appPreferences;

    public static String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public static void putString(String key, String value) {
        getSharedPreferences().put(key, value);
    }

    public static boolean getBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        getSharedPreferences().put(key, value);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int value) {
        return getSharedPreferences().getInt(key, value);
    }

    public static void putInt(String key, int value) {
        getSharedPreferences().put(key, value);
    }

    public static long getLong(String key) {
        return getSharedPreferences().getLong(key, 0);
    }

    public static void putLong(String key, long value) {
        getSharedPreferences().put(key, value);
    }

    /**
     * 存储List集合
     *
     * @param key  存储的键
     * @param list 存储的集合
     */
    public static void putList(String key, List<? extends Serializable> list) {
        try {
            putObject(key, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取List集合
     *
     * @param key 键
     * @param <E> 指定泛型
     * @return List集合
     */
    public static <E extends Serializable> List<E> getList(String key) {
        try {
            return (List<E>) getObject(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void remove(String key) {
        getSharedPreferences().remove(key);
    }

    private static AppPreferences getSharedPreferences() {
        if (appPreferences == null) {
            appPreferences = new AppPreferences(App.getContext());
        }
        return appPreferences;
    }

    /**
     * 存储对象
     *
     * @param key 键
     * @param obj 对象
     */
    private static void putObject(String key, Object obj) throws IOException {
        if (obj == null) {
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        baos.close();
        oos.close();

        putString(key, objectStr);
    }

    /**
     * 获取对象
     *
     * @param key 键
     * @return 对象
     */
    private static Object getObject(String key) throws IOException, ClassNotFoundException {
        String wordBase64 = getString(key);
        // 将base64格式字符串还原成byte数组
        if (TextUtils.isEmpty(wordBase64)) { //不可少，否则在下面会报java.io.StreamCorruptedException异常
            return null;
        }
        byte[] objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        // 将byte数组转换成product对象
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        return obj;
    }
}
