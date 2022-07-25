package com.nelsonrueda.mercadolibreapp.Entities.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.nelsonrueda.mercadolibreapp.R;

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
    final static String TAG = "Util";
    /**
     * Metodo que se usa para transforma la data Json a un array de una estructura de datos
     * con ayuda de la libreria de Gson
     * @param json
     * @param classInfo
     * @param <T>
     * @return
     */
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

    /**
     * Metodo que se usa para transforma la data Json a un objecto de una estructura de datos
     * con ayuda de la libreria de Gson
     * @param json
     * @param classInfo
     * @param <T>
     * @return
     */
    public static <T> T jsonToObj(String json, Class<T> classInfo){
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,classInfo);
    }

    /**
     * Metodo que se usa para validar que estan activo las configuracion
     * de conectividad de datos o WIFi y validar si tiene conexion a internet
     * realizando una pequeña conexion a una sitio publico para ver si hay
     * respuesta
     * @param context
     * @return
     */
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

    /**
     * Metodo que nos transforma una objecto a formato de Moneda
     * @param data
     * @return
     */
    public static String FormatCurrency(Object data){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(data);
    }

    /**
     * Metodo que nos transforma una objecto a formato de miles
     * @param data
     * @return
     */
    public static String FormatNumberInstance(Object data){
        NumberFormat format =  NumberFormat.getNumberInstance();
        return format.format(data);
    }

    /**
     * Metodo para visualizar Toast
     * @param context
     * @param message
     */
    public static void ShowToast(Context context, String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Log.e(TAG, String.format("%s - Excepcion: %s", "ShowToast", ex.getMessage()));
        }
    }

    /**
     * Metodo para obtener el string de un recurso
     * @param context
     * @param ResourceId
     * @return
     */
    public static String GetResourceString(Context context, int ResourceId){
        String message = "";
        try{
            message = context.getString(ResourceId);
        }catch (Exception ex){
            Log.e(TAG,String.format("%s - Excepcion: %s","GetResourceString",ex.getMessage()));
            message = "Resourse Not Found";
        }
        return message;
    }
}
