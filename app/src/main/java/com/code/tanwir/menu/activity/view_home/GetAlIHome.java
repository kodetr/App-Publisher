package com.code.tanwir.menu.activity.view_home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.code.tanwir.menu.activity.network.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Tanwir on 06/02/2016.
 */
public class GetAlIHome {

    public static String[] name;
    public static String[] pb;
    public static Bitmap[] bitmaps;

    private String json;
    private JSONArray urls;

    public GetAlIHome(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(Config.TAG_IMAGE_URL));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }


    public void getAllImages() throws JSONException {
        bitmaps = new Bitmap[urls.length()];

        name = new String[urls.length()];
        pb = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            name[i] = urls.getJSONObject(i).getString(Config.TAG_NAME);
            pb[i] = urls.getJSONObject(i).getString(Config.TAG_PUBLISHER);

            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);

        }
    }
}
