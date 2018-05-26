package com.musearcher.g_brain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.musearcher.g_brain.common.Constants;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    // OKHttp json 参数 post 请求
//    @Test
//    public void run() throws Exception {
//        final MediaType MEDIA_TYPE_JSON
//                = MediaType.parse("application/json; charset=utf-8");
//        Map<String, String> params = new HashMap<>();
//        params.put("RISK_HINTS_UPDATE_TIMESTAMP", "2017-11-28 13:57:00.883871");
//        params.put("EXAM_BASIS_INDEX_CODE", "1");
//        params.put("SQL_CODE", "0");
//        params.put("USER-AGENT", "IPHONE");
//        params.put("VER_CODE", "33");
//        params.put("MAJOR_CODE", "3");
//        params.put("TEMPLATE_CODE", "9");
//        params.put("APP_DEADLINE_CODE", "3");
//        params.put("INSPECTION_CODE", "1");
//        params.put("DICT_CODE", "1");
//        params.put("AREA_CODE","2");
//        Gson gson = new Gson();
//        String paramsStr = gson.toJson(params);
//        final OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(Constants.GET_BASIC_DATA)
//                .post(RequestBody.create(MEDIA_TYPE_JSON, paramsStr))
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            System.out.println(response.body().string());
//        }
//    }

    // fromParameters
//    @Test
//    public void run() throws Exception {
//
//        final OkHttpClient client = new OkHttpClient();
//
//        RequestBody formBody = new FormBody.Builder()
//                .add("search", "Jurassic Park")
//                .build();
//        Request request = new Request.Builder()
//                .url(Constants.CONNECT_TEST)
//                .post(formBody)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            System.out.println(response.body().string());
//        }
//    }

//    @Test
//    public void run() throws Exception {
//        String IMGUR_CLIENT_ID = "...";
//        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
//
//        OkHttpClient client = new OkHttpClient();
//        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("title", "Square Logo")
//                .addFormDataPart("image", "index.jpg",
//                        RequestBody.create(MEDIA_TYPE_PNG, new File("E:\\AndroidStudioProjects\\g_brain\\app\\src\\test\\java\\com\\musearcher\\g_brain\\index.jpg")))
//                .build();
//
//        Request request = new Request.Builder()
//                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
//                .url(Constants.CONNECT_TEST)
//                .post(requestBody)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            System.out.println(response.body().string());
//        }
//    }

    @Test
    public void getPic() throws Exception {
        //获取okHttp对象get请求
        OkHttpClient client = new OkHttpClient();
        //获取请求对象
        Request request = new Request.Builder().url("http://localhost:8080/gbrain/images/44.jpg").build();
        //获取响应体
        ResponseBody body = client.newCall(request).execute().body();
        //获取流
        InputStream in = body.byteStream();
        //转化为bitmap

//        Bitmap bitmap = BitmapFactory.decodeStream(in);
    }
}