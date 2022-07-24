package com.nelsonrueda.mercadolibreapp.Entities.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Utils {

    public static <T>ArrayList<T> jsonToArrayList(String json, Class<T> classInfo){
        if(TextUtils.isEmpty(json)){
            return null;
        }
        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();

        Gson gson = new GsonBuilder().setLenient().create();
        ArrayList<JsonObject> jsonObjects = gson.fromJson(json,type);

        ArrayList<T> arrayList = new ArrayList<>();
        for(JsonObject jsonObject:jsonObjects){
            arrayList.add(gson.fromJson(jsonObject,classInfo));
        }
        return arrayList;
    }

    public static <T> T jsonToObj(String json, Class<T> classInfo){
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,classInfo);
    }


    public static Boolean isNetworkAvailable(Context context){
        boolean connected = false;
        try {
            final ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                connected = true;
            } else if (netInfo != null && netInfo.isConnected()
                    && cm.getActiveNetworkInfo().isAvailable()) {
                connected = true;
            } else if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url
                            .openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        connected = true;
                    }
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (cm != null) {
                final NetworkInfo[] netInfoAll = cm.getAllNetworkInfo();
                for (NetworkInfo ni : netInfoAll) {
                    System.out.println("get network type :::" + ni.getTypeName());
                    if ((ni.getTypeName().equalsIgnoreCase("WIFI") || ni
                            .getTypeName().equalsIgnoreCase("MOBILE"))
                            && ni.isConnected() && ni.isAvailable()) {
                        connected = true;
                        if (connected) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connected;
    }

    public static String FormatCurrency(int data){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(data);
    }

    public static String FormatNumberInstance(int data){
        NumberFormat format =  NumberFormat.getNumberInstance();
        return format.format(data);
    }
}
