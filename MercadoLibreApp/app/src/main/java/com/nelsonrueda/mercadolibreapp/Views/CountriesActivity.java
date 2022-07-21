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
        ValidaConnectionAndGetCountries();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ValidaConnectionAndGetCountries();
    }

    private void ValidaConnectionAndGetCountries(){
        clearData();
        if(Utils.isNetworkAvailable(this)) {
            GetCountriesBySites();
        }else{
            Toast.makeText(this,R.string.system_is_not_connected_internet,Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
    }

    private void clearData(){
        mCountriesListView.setAdapter(null);
        mSiteList = null;

    }

    private void customActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_without_search);
        //View view =getSupportActionBar().getCustomView();
    }

    private void initView(){
        mParentContriesLayout = findViewById(R.id.country_parent_layout);
        mCountriesListView = findViewById(R.id.countries_list);
        mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mProgressBar.setVisibility(View.GONE);
        mParentContriesLayout.addView(mProgressBar, params);

    }

    private void GetCountriesBySites() {
        // if(!Utils.isNetworkAvailable(this))
        showProgress();
        sendRequestGetSites();
    }

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

    @Override
    public void ReportError(String TAG, String Message) {
        if(this.TAG.equals(TAG)){
            Toast.makeText(this,Message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void GetSites(ArrayList<Site> sites) {
        if(mSiteList == null) mSiteList = new ArrayList<>();
            mSiteList.clear();

        if(sites != null && sites.size() > 0){
            mSiteList.addAll(sites);
            mCountriesAdapter = new CountriesAdapter(this,mSiteList,this);
            mCountriesListView.setAdapter(mCountriesAdapter);
        }else{
            Toast.makeText(this,R.string.sites_is_empty,Toast.LENGTH_LONG).show();
        }
        mProgressBar.setVisibility(View.GONE);
    }
}