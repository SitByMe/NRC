package com.tianao.module.net.convert;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.tianao.module.net.utils.DecodeHelper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private TypeAdapter<T> adapter;
    private Gson gson;

    public DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public DecodeResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String firstJson = responseBody.string();
        return adapter.fromJson(DecodeHelper.decodeData(firstJson));
    }
}