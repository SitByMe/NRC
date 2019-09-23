package com.tianao.module.net.utils;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class HttpUtils {

    static Context contextMethod(String methodName) {
        Context context = null;
        try {
            Class<?> c = Class.forName("com.tianao.aossaharavideo.App");
            Object obj1 = c.newInstance();
            //Object[] obj2 = new Object[1];
            Method getContextMethod = c.getMethod(methodName);
            context = (Context) getContextMethod.invoke(obj1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return context;
    }

    static void showToastMethod(String toast) {
        try {
            Class<?> c = Class.forName("com.tianao.aossaharavideo.utils.ToastHelper");
            Object obj1 = c.newInstance();
            //Object[] obj2 = new Object[1];
            Method showToastMethod = c.getMethod("showToast", String.class);
            showToastMethod.invoke(obj1, toast);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
