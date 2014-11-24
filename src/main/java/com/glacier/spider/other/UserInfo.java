package com.glacier.spider.other;

import com.glacier.spider.login.AccessTokenJSON;
import com.glacier.spider.weibo4j.org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by glacier on 14-11-24.
 */
public class UserInfo {
    private static String json = null;
    private static String showURL = "https://api.weibo.com/2/users/show.json?"
            + "source=462118737&access_token=" + AccessTokenJSON.getAccessToken() +"&uid="+ AccessTokenJSON.getUid();
    private static JSONObject jsonObject = null;

    private static String getJson() {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(showURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while( (line = reader.readLine()) != null ) {
                buffer.append(line + "\n");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void init() {
        try {
            json = getJson();
            jsonObject = new JSONObject(json);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        try {
            return jsonObject.getString(key)+"";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
