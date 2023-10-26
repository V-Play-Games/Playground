package net.vpg;

import net.vpg.vjson.value.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class WebUtil {
    public static final OkHttpClient CLIENT = new OkHttpClient();

    public static JSONObject getData(String baseUrl, String location) {
        System.out.println("Calling /" + location);
        return getData0(baseUrl + location);
    }

    public static JSONObject getData(String url) {
        System.out.println("Calling " + url);
        return getData0(url);
    }

    private static JSONObject getData0(String url) {
        try {
            return JSONObject.parse(new URL(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(String baseUrl, String location) {
        System.out.println("Calling /" + location);
        return getString0(baseUrl + location);
    }

    @SuppressWarnings("unused")
    public static String getString(String url) {
        System.out.println("Calling " + url);
        return getString0(url);
    }

    private static String getString0(String url) {
        try (Response response = getResponse(url)) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Response getResponse(String url) {
        try {
            return CLIENT.newCall(new Request.Builder().url(url).build()).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
