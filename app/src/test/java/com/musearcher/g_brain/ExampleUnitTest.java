package com.musearcher.g_brain;

import com.google.gson.Gson;
import com.musearcher.g_brain.common.Constants;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    // OKHttp json 参数 post 请求
    @Test
    public void run() throws Exception {
        final MediaType MEDIA_TYPE_JSON
                = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<>();
        params.put("RISK_HINTS_UPDATE_TIMESTAMP", "2017-11-28 13:57:00.883871");
        params.put("EXAM_BASIS_INDEX_CODE", "1");
        params.put("SQL_CODE", "0");
        params.put("USER-AGENT", "IPHONE");
        params.put("VER_CODE", "33");
        params.put("MAJOR_CODE", "3");
        params.put("TEMPLATE_CODE", "9");
        params.put("APP_DEADLINE_CODE", "3");
        params.put("INSPECTION_CODE", "1");
        params.put("DICT_CODE", "1");
        params.put("AREA_CODE","2");
        Gson gson = new Gson();
        String paramsStr = gson.toJson(params);
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Constants.GET_BASIC_DATA)
                .post(RequestBody.create(MEDIA_TYPE_JSON, paramsStr))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }
}