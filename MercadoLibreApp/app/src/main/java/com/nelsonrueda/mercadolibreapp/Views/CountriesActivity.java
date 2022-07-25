package com.nelsonrueda.mercadolibreapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.provider.Settings;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.nelsonrueda.mercadolibreapp.Api.MercadoLibreAPIManager;
import com.nelsonrueda.mercadolibreapp.Data.UserSettinsInMemory;

import com.nelsonrueda.mercadolibreapp.Entities.Models.Site;
import com.nelsonrueda.mercadolibreapp.Entities.Utils.Utils;
import com.nelsonrueda.mercadolibreapp.Interface.IAdapterListener;
import com.nelsonrueda.mercadolibreapp.Interface.IMercadoLibreListener;
import com.nelsonrueda.mercadolibreapp.Interface.ISitesListener;
import com.nelsonrueda.mercadolibreapp.R;
import com.nelsonrueda.mercadolibreapp.Views.Adapters.CountriesAdapter;

import java.util.ArrayList;

/**
 * Activity para permitir al Usuario obtener y seleccionar el Site de su pais
 */
public class CountriesActivity  extends AppCompatActivity implements ISitesListener, IMercadoLibreListener, IAdapterListener {

    final String TAG = "CountriesActivity";
    ProgressBar mProgressBar;
    ConstraintLayout mParentContriesLayout;
    ListView mCountriesListView;
    MercadoLibreAPIManager mApiManager;
    ArrayList<Site> mSiteList;
    CountriesAdapter mCountriesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        customActionBar();
        initView();
        //ValidaConnectionAndGetCountries();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ValidaConnectionAndGetCountries();
    }

    /**
     * metodo para validar conectividad y obtener inforamcion de los sites
     */
    private void ValidaConnectionAndGetCountries(){
        clearData();
        if(Utils.isNetworkAvailable(this)) {
            GetCountriesBySites();
        }else{
            Utils.ShowToast(this,Utils.GetResourceString(this,R.string.system_is_not_connected_internet));
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
    }

    private void clearData(){
        mCountriesListView.setAdapter(null);
        mSiteList = null;

    }

    /**
     * Metodo para personalizar la ActionBar
     */
    private void customActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_without_search);
    }

    /**
     * Metodo para inicializar los diferentes View del Layout
     */
    private void initView(){
        mParentContriesLayout = findViewById(R.id.country_parent_layout);
        mCountriesListView = findViewById(R.id.countries_list);
        mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mProgressBar.setVisibility(View.GONE);
        mParentContriesLayout.addView(mProgressBar, params);

    }

    /**
     * Metodo realizar la peticion de los SITE
     */
    private void GetCountriesBySites() {
        showProgress();
        sendRequestGetSites();
    }

    /**
     * Metodo que inicializa un instancia de ApiManager que por medio de el se realizar la peticion para
     * obtener los Sites
     */
    private void sendRequestGetSites(){
        try{
            mApiManager = new MercadoLibreAPIManager(this,TAG,this,this);
            mApiManager.GetSites();
        }catch (Exception ex){
            Log.d(TAG, "sendRequestGetSites: ex:"+ ex.getMessage());
        }
    }

    private void showProgress() {
        try{
            mProgressBar.setVisibility(View.VISIBLE);
        }catch (Exception ex){
            Log.d(TAG, "showProgress: ex:"+ ex.getMessage());
        }

    }

    /**
     * Metodo que nos devuelve el evento de selecion de elemento de la lista de Site y nos
     * permite guardar la informacion y pasar al siguitente Activity
     * @param data
     */
    @Override
    public void onClickItemSelect(Object data) {
        if(data != null && data.getClass() == Site.class){
            UserSettinsInMemory.SetCurrentSite((Site)data);
            Intent ProductIntent = new Intent(CountriesActivity.this,ProductActivity.class);
            startActivity(ProductIntent);
            overridePendingTransition(R.anim.zoom_forward_in,R.anim.zoom_forward_out);
            finish();
        }
    }

    /**
     * Metodo que escucha los reporte de errores que son Notificado por el APIManager
     * @param TAG
     * @param Message
     */
    @Override
    public void ReportError(String TAG, String Message) {
        if(this.TAG.equals(TAG)){
            mProgressBar.setVisibility(View.GONE);
            Utils.ShowToast(this,Message);
        }
    }

    /**
     * Metodo que escucha y recibe la data de los Site obtenido de la peticion realizada en linea
     * @param sites
     */
    @Override
    public void GetSites(ArrayList<Site> sites) {
        if(mSiteList == null) mSiteList = new ArrayList<>();
            mSiteList.clear();

        if(sites != null && sites.size() > 0){
            mSiteList.addAll(sites);
            mCountriesAdapter = new CountriesAdapter(this,mSiteList,this);
            mCountriesListView.setAdapter(mCountriesAdapter);
        }else{
            Utils.ShowToast(this,Utils.GetResourceString(this,R.string.sites_is_empty));
        }
        mProgressBar.setVisibility(View.GONE);
    }
}