package com.nelsonrueda.mercadolibreapp.Views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nelsonrueda.mercadolibreapp.Api.MercadoLibreAPIManager;
import com.nelsonrueda.mercadolibreapp.Data.UserSettinsInMemory;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Category;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByCategoryResult;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByDataCustomResult;
import com.nelsonrueda.mercadolibreapp.Entities.Utils.Utils;
import com.nelsonrueda.mercadolibreapp.Interface.IAdapterListener;
import com.nelsonrueda.mercadolibreapp.Interface.ICategoriesListener;
import com.nelsonrueda.mercadolibreapp.Interface.IItemsListener;
import com.nelsonrueda.mercadolibreapp.Interface.IMercadoLibreListener;
import com.nelsonrueda.mercadolibreapp.R;
import com.nelsonrueda.mercadolibreapp.Views.Adapters.CountriesAdapter;
import com.nelsonrueda.mercadolibreapp.Views.Adapters.ItemResultAdapter;
import com.nelsonrueda.mercadolibreapp.Views.Adapters.ItemsAdapter;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements  IItemsListener, IMercadoLibreListener, IAdapterListener, ICategoriesListener {
    final private String TAG = "ProductActivity";

    ProgressBar mProgressBar;
    ConstraintLayout mParentProductLayout;
    RecyclerView mItemListView;
    Spinner mCategorySpinner;
    TextView mTotalItemOfSearch;

    ItemsAdapter mItemAdapter;

    MercadoLibreAPIManager mApiManagerByCustomItem;
    MercadoLibreAPIManager mApiManagerByCategoryItem;
    MercadoLibreAPIManager mApiManagerByCategories;

    ArrayList<Category> mCategoryList;
    ItemsByCategoryResult mItemsByCategory;
    ItemsByDataCustomResult mItemsByCustomResult;
    boolean IsItemsByCategory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        customActionBar();
        initView();
        ValidateConectionAndGetCategories();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ValidateConectionAndGetCategories();
    }

    private void ValidateConectionAndGetCategories(){
        ClearData();
        if(Utils.isNetworkAvailable(this)) {
            GetCategories();
        }else{
            Toast.makeText(this,R.string.system_is_not_connected_internet,Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
    }

    private void ClearData() {
        mItemListView.setAdapter(null);
        mItemsByCustomResult = null;
        mItemsByCategory = null;
        mTotalItemOfSearch.setText("0");
        IsItemsByCategory = false;
    }

    private void customActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_with_search_and_categories);
        View view =getSupportActionBar().getCustomView();
        SearchView searchView = view.findViewById(R.id.actionbar_with_search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                GetCustomItem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    private void initView(){
        mParentProductLayout = findViewById(R.id.item_parent_container);
        mItemListView = findViewById(R.id.items_list);


        mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mProgressBar.setVisibility(View.GONE);
        mParentProductLayout.addView(mProgressBar, params);
        mCategorySpinner= findViewById(R.id.items_category);
        mTotalItemOfSearch = findViewById(R.id.total_search_found_title);
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int
                    i, long l) {

                Category categorySelected = (Category) adapterView.getAdapter().getItem(i);
                if(!categorySelected.getId().equals("-1")){

                    sendRequestGetCategoryItems(categorySelected);
                }else{
                    mItemListView.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    private void GetCustomItem(String Data) {
        // if(!Utils.isNetworkAvailable(this))
        IsItemsByCategory = false;
        showProgress();
        sendRequestGetCustomItem(Data);
    }

    private void GetItemsByCategory(Category categorySelected) {
        // if(!Utils.isNetworkAvailable(this))
        IsItemsByCategory = true;
        showProgress();
        sendRequestGetCategoryItems(categorySelected);
    }

    private void GetCategories() {
        // if(!Utils.isNetworkAvailable(this))
        showProgress();
        sendRequestCategories();
    }

    private void sendRequestGetCustomItem(String Data){
        try{
            mApiManagerByCustomItem = new MercadoLibreAPIManager(this,TAG,this);
            mApiManagerByCustomItem.setObjectListener((IItemsListener) this);
            mApiManagerByCustomItem.GetItemByCustomResult(UserSettinsInMemory.GetCurrentSite().getId(),Data);
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

    private void hideProgress() {
        try{
            mProgressBar.setVisibility(View.GONE);
        }catch (Exception ex){
            Log.d(TAG, "showProgress: ex:"+ ex.getMessage());
        }

    }

    private void sendRequestGetCategoryItems(Category categorySelected){
        try{
            mApiManagerByCategoryItem = new MercadoLibreAPIManager(this,TAG,this);
            mApiManagerByCategoryItem.setObjectListener((IItemsListener) this);
            mApiManagerByCategoryItem.GetItemByCategory(UserSettinsInMemory.GetCurrentSite().getId(),categorySelected.getId());
        }catch (Exception ex){
            Log.d(TAG, "sendRequestGetSites: ex:"+ ex.getMessage());
        }
    }

    private void sendRequestCategories(){
        try{
            mApiManagerByCategories = new MercadoLibreAPIManager(this,TAG,this);
            mApiManagerByCategories.setObjectListener((ICategoriesListener) this);
            mApiManagerByCategories.GetCategories(UserSettinsInMemory.GetCurrentSite().getId());
        }catch (Exception ex){
            Log.d(TAG, "sendRequestGetSites: ex:"+ ex.getMessage());
        }
    }

    @Override
    public void onClickItemSelect(Object data) {
        if(data.getClass() == ItemsByCategoryResult.class)
        {

        }else if(data.getClass() == ItemsByDataCustomResult.class){

        }
    }

    @Override
    public void GetItemByCategory(ItemsByCategoryResult result) {
        hideProgress();
        if(result != null){
            mItemsByCategory = result;
            mTotalItemOfSearch.setText(" "+mItemsByCategory.getPaging().getTotal()+"");
            if(mItemsByCategory.getResults() != null && mItemsByCategory.getResults().size() > 0){
                mItemAdapter = new ItemsAdapter(mItemsByCategory.getResults());
                mItemListView.setHasFixedSize(true);
                mItemListView.setLayoutManager(new LinearLayoutManager(this));
                mItemListView.setAdapter(mItemAdapter);
            }
            else {
                mItemListView.setAdapter(null);
            }
        }else{
            mItemListView.setAdapter(null);
            Toast.makeText(this,R.string.product_not_get_results,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void GetItemByCustomResult(ItemsByDataCustomResult result) {
        hideProgress();
        if(result != null){
            mItemsByCustomResult = result;
            mTotalItemOfSearch.setText(" "+mItemsByCustomResult.getPaging().getTotal());
            if(mItemsByCustomResult.getResults() != null && mItemsByCustomResult.getResults().size() > 0){
                mItemAdapter = new ItemsAdapter(mItemsByCustomResult.getResults());
                mItemListView.setHasFixedSize(true);
                mItemListView.setLayoutManager(new LinearLayoutManager(this));
                mItemListView.setAdapter(mItemAdapter);
            }
            else {
                mItemListView.setAdapter(null);
            }
        }else{
            mItemListView.setAdapter(null);
            Toast.makeText(this,R.string.product_not_get_results,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void ReportError(String TAG, String Message) {
        if(this.TAG.equals(TAG)){
            Toast.makeText(this,Message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void GetCategories(ArrayList<Category> categoriesResult) {
        if(mCategoryList == null) mCategoryList = new ArrayList<Category>();
        mCategoryList.clear();


        Category generalCategory = new Category();
        generalCategory.setId("-1");
        generalCategory.setName("Todas las categorias");
        mCategoryList.add(generalCategory);

        if(categoriesResult != null && categoriesResult.size() > 0){
            mCategoryList.addAll(categoriesResult);
            ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,mCategoryList);
            categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCategorySpinner.setAdapter(categoryArrayAdapter);

        }else{
            Toast.makeText(this,R.string.categories_is_empty,Toast.LENGTH_LONG).show();
        }
        mProgressBar.setVisibility(View.GONE);
    }



}