package com.tianao.module.net.http;

import com.facebook.stetho.common.LogUtil;
import com.tianao.module.net.BuildConfig;
import com.tianao.module.net.utils.DecodeHelper;
import com.tianao.module.net.utils.LogHelper;
import com.tianao.module.net.utils.MD5Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 请求日志
 */
class HttpLogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        long t1 = System.currentTimeMillis();

        Request original = chain.request();
        original = addParam(original);

        String body = bodyToString(original.body());

        Request request = original.newBuilder()
                .header("Accept", "application/json")
                .method(original.method(), original.body())
                .build();

        LogUtil.i(String.format("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                        "║ " + getClass().getName() + ".intercept(Chain chain) \n" +
                        "║ 请求发起 \n" +
                        "╟──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n" +
                        "║ request method  : %s\n" +
                        "║ request url     : %s\n" +
                        "║ request body    : %s\n" +
                        "║ request headers : %s\n" +
                        "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════"
                , request.method(),
                request.url().toString(),
                body,
                request.headers().toString().replaceAll("\\n", "\\\n║                   ")));

        Response response = chain.proceed(request);

        long t2 = System.currentTimeMillis();
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        String responseBodyStr = responseBody.string().replaceAll("\\n", "");
        LogHelper.showLargeLog(String.format("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                        "║ " + getClass().getName() + ".intercept(Chain chain) \n" +
                        "║ 请求响应 \n" +
                        "╟──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n" +
                        "║ 耗时 (不准)       : %s 毫秒\n" +
                        "║ response url     : %s\n" +
                        "║ response body    : %s\n" +
                        "║ response headers : %s\n" +
                        "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════",
                String.valueOf(t2 - t1),
                response.request().url(),
                unicodeToString(formatJson(BuildConfig.USE_DECODE ? DecodeHelper.decodeData(responseBodyStr) : responseBodyStr)),
                response.headers().toString().replaceAll("\\n", "\\\n║                   ")), 4000, "HTTP_RESPONSE");
/*
        LogUtil.i(String.format("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                        "║ " + getClass().getName() + ".intercept(Chain chain) \n" +
                        "║ 请求响应 \n" +
                        "╟──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n" +
                        "║ 耗时 (不准)       : %s 毫秒\n" +
                        "║ response url     : %s\n" +
                        "║ response body    : %s\n" +
                        "║ response headers : %s\n" +
                        "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════",
                String.valueOf(t2 - t1),
                response.request().url(),
                unicodeToString(formatJson(BuildConfig.USE_DECODE ? DecodeHelper.decodeData(responseBodyStr) : responseBodyStr)),
                response.headers().toString().replaceAll("\\n", "\\\n║                   ")));
*/
        return response;
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            if (copy == null) {
                return "";
            }
            final Buffer buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 格式化json
     */
    private String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last;
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            //遇到{ [换行，且下一行缩进
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;

                //遇到} ]换行，当前行缩进
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                //遇到,换行
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        return sb.toString();
    }

    /**
     * 添加space
     */
    private void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    /**
     * Unicode 转 中文
     *
     * @param dataStr unicode
     */
    private String unicodeToString(String dataStr) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(dataStr);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            dataStr = dataStr.replace(group1, ch + "");
        }
        return dataStr;
    }

    /**
     * Decode 转 中文
     *
     * @param dataStr decode
     */
    private String decodeToString(String dataStr) {
        String output = null;
        try {
            output = URLDecoder.decode(dataStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return output;
    }


    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addParam(Request oldRequest) {
        if (oldRequest.method().equals("POST")) {
            if (oldRequest.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) oldRequest.body();

                LinkedHashMap<String, String> keyList = new LinkedHashMap<>();
                //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    keyList.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
                String currentTime = String.valueOf(System.currentTimeMillis());
                keyList.put("timestamp", currentTime);
                String sign = createSign(keyList, currentTime);
                formBody = bodyBuilder
                        .addEncoded("timestamp", currentTime)
                        .addEncoded("sign", sign)
                        .build();

                oldRequest = oldRequest.newBuilder().post(formBody).build();
            }
        }
        return oldRequest;
    }

    private String createSign(LinkedHashMap<String, String> mapList, String currentTime) {
        String[] keyList = mapList.keySet().toArray(new String[0]);
        Arrays.sort(keyList);
        StringBuilder sb = new StringBuilder();
        for (String it : keyList) {
            sb.append(it).append("=").append(mapList.get(it)).append("&");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        sb.append("<!=kjhsdf234FDfd&9gsd==");
        String enCode = MD5Util.enCode(sb.toString());
        return enCode;
    }
}