package com.geekbang.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * description: OkHttp样例代码
 *
 * @author 张天乐
 * @since 2020-11-01
 */
public class OkHttpDemo {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8088/api/hello/").build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

}
