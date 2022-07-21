package com.nelsonrueda.mercadolibreapp.Api;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nelsonrueda.mercadolibreapp.Entities.Enums.RequestType;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Category;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByCategoryResult;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByDataCustomResult;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Site;
import com.nelsonrueda.mercadolibreapp.Entities.Utils.Constants;
import com.nelsonrueda.mercadolibreapp.Entities.Utils.Utils;
import com.nelsonrueda.mercadolibreapp.Interface.ICategoriesListener;
import com.nelsonrueda.mercadolibreapp.Interface.IItemsListener;
import com.nelsonrueda.mercadolibreapp.Interface.IMercadoLibreListener;
import com.nelsonrueda.mercadolibreapp.Interface.ISitesListener;
import com.nelsonrueda.mercadolibreapp.R;

import java.util.ArrayList;


public class MercadoLibreAPIManager {

    final String TAG = "MercadoLibreAPIManager";
    private Context currentContext;
    private Object currentListener;
    private IMercadoLibreListener currentMercadoLibreListener;
    private String currentTAG;
    private RequestQueue requestQueue;


    public MercadoLibreAPIManager(Context context, String TAG, IMercadoLibreListener mlListener){
        this.currentContext = context;
        this.currentMercadoLibreListener = mlListener;
        this.currentTAG = TAG;
        this.requestQueue =  Volley.newRequestQueue(this.currentContext);
    }

    public MercadoLibreAPIManager(Context context, String TAG, Object objectListener, IMercadoLibreListener mlListener){
        this.currentContext = context;
        this.currentListener = objectListener;
        this.currentMercadoLibreListener = mlListener;
        this.currentTAG = TAG;
        this.requestQueue =  Volley.newRequestQueue(this.currentContext);
    }

    public void setObjectListener(ICategoriesListener objectListener){
        this.currentListener = objectListener;
    }

    public void setObjectListener(IItemsListener objectListener){
        this.currentListener = objectListener;
    }

    public IMercadoLibreListener GetMercadoLibreListener(){
        return this.currentMercadoLibreListener;
    }

    public void GetCategories(String SiteID) {
        if(Utils.isNetworkAvailable(this.currentContext)) {
            String CATEGORIES_API_URL = String.format("%s%s", Constants.BASE_URL_MERCADOLIBRE, Constants.API_GET_CATEGORIES);
            CATEGORIES_API_URL = CATEGORIES_API_URL.replace("$SITE_ID", SiteID);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, CATEGORIES_API_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ProcessData(response, RequestType.REQUEST_CATEGORIES);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    GetMercadoLibreListener().ReportError(currentTAG, Resources.getSystem().getString(R.string.mercado_libre_api_manager_request_categories_error));
                    Log.e(TAG, String.format("%s - Error consultado la peticion: %s", "GetCategories", error.getMessage()));
                }
            });
            requestQueue.add(stringRequest);
        }else{
            GetMercadoLibreListener().ReportError(currentTAG, Resources.getSystem().getString(R.string.system_is_not_connected_internet));
        }
    }


    public void GetItemByCategory(String SiteID, String CategoryID) {
        if(Utils.isNetworkAvailable(this.currentContext)) {
            String ITEMS_BY_CATEGORIES_API_URL = String.format("%s%s", Constants.BASE_URL_MERCADOLIBRE,Constants.API_GET_ITEMS_BY_CATEGORY);
            ITEMS_BY_CATEGORIES_API_URL = ITEMS_BY_CATEGORIES_API_URL.replace("$SITE_ID",SiteID);
            ITEMS_BY_CATEGORIES_API_URL = ITEMS_BY_CATEGORIES_API_URL.replace("$CATEGORY_ID",CategoryID);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, ITEMS_BY_CATEGORIES_API_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ProcessData(response, RequestType.REQUEST_ITEMS_BY_CATEGORY);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    GetMercadoLibreListener().ReportError(currentTAG,Resources.getSystem().getString(R.string.mercado_libre_api_manager_request_items_by_categories_error));
                    Log.e(TAG,String.format("%s - Error consultado la peticion: %s","GetItemByCategory",error.getMessage()));
                }
            });
            requestQueue.add(stringRequest);
        }else{
            GetMercadoLibreListener().ReportError(currentTAG, Resources.getSystem().getString(R.string.system_is_not_connected_internet));
        }
    }

    public void GetItemByCustomResult(String SiteID, String Data) {
        if(Utils.isNetworkAvailable(this.currentContext)) {
            String CUSTOM_ITEMS_API_URL = String.format("%s%s", Constants.BASE_URL_MERCADOLIBRE,Constants.API_GET_INFO_BY_CUSTOM_SEARCH);
            CUSTOM_ITEMS_API_URL = CUSTOM_ITEMS_API_URL.replace("$SITE_ID",SiteID);
            CUSTOM_ITEMS_API_URL = CUSTOM_ITEMS_API_URL.replace("$DATA",Data.replace(" ","%"));
            StringRequest stringRequest = new StringRequest(Request.Method.GET, CUSTOM_ITEMS_API_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ProcessData(response, RequestType.REQUEST_ITEMS_BY_CUSTOM_DATA);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    GetMercadoLibreListener().ReportError(currentTAG,Resources.getSystem().getString(R.string.mercado_libre_api_manager_request_items_by_custom_data_error));
                    Log.e(TAG,String.format("%s - Error consultado la peticion: %s","GetItemByCustomResult",error.getMessage()));
                }
            });
            requestQueue.add(stringRequest);
        }else{
            GetMercadoLibreListener().ReportError(currentTAG, Resources.getSystem().getString(R.string.system_is_not_connected_internet));
        }
    }

    public void GetSites() {
        if(Utils.isNetworkAvailable(this.currentContext)) {
            String SITES_API_URL = String.format("%s%s", Constants.BASE_URL_MERCADOLIBRE,Constants.API_GET_SITES);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, SITES_API_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ProcessData(response, RequestType.REQUEST_SITES);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    GetMercadoLibreListener().ReportError(currentTAG,Resources.getSystem().getString(R.string.mercado_libre_api_manager_request_sites_error));
                    Log.e(TAG,String.format("%s - Error consultado la peticion: %s","GetSites",error.getMessage()));
                }
            });
            requestQueue.add(stringRequest);
        }else{
            GetMercadoLibreListener().ReportError(currentTAG, Resources.getSystem().getString(R.string.system_is_not_connected_internet));
        }
    }

    private void ProcessData(String response,RequestType requestType) {
        if(response != null && !response.isEmpty()){

            switch(requestType) {
                case REQUEST_SITES:
                    ArrayList<Site> siteList = Utils.jsonToArrayList(response, Site.class);
                    ((ISitesListener)currentListener).GetSites(siteList);
                    break;
                case REQUEST_CATEGORIES:
                    ArrayList<Category> categoriesList = Utils.jsonToArrayList(response, Category.class);
                    ((ICategoriesListener)currentListener).GetCategories(categoriesList);
                    break;

                case REQUEST_ITEMS_BY_CATEGORY:
                    ItemsByCategoryResult itemsByCategoryResult = Utils.jsonToObj(response, ItemsByCategoryResult.class);

                    ((IItemsListener)currentListener).GetItemByCategory(itemsByCategoryResult);
                    break;
                case REQUEST_ITEMS_BY_CUSTOM_DATA:
                    ItemsByDataCustomResult itemsByDataCustomResult = Utils.jsonToObj(response,ItemsByDataCustomResult.class);
                    ((IItemsListener)currentListener).GetItemByCustomResult(itemsByDataCustomResult);
                    break;
            }
        }else{
            GetMercadoLibreListener().ReportError(currentTAG, Resources.getSystem().getString(R.string.system_is_not_connected_internet));
        }
    }
}
