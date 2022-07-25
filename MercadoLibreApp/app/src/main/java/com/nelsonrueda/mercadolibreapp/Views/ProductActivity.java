package com.nelsonrueda.mercadolibreapp.Views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nelsonrueda.mercadolibreapp.Api.MercadoLibreAPIManager;
import com.nelsonrueda.mercadolibreapp.Data.UserSettinsInMemory;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Category;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByCategoryResult;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByDataCustomResult;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Result;
import com.nelsonrueda.mercadolibreapp.Entities.Utils.Utils;
import com.nelsonrueda.mercadolibreapp.Interface.IAdapterListener;
import com.nelsonrueda.mercadolibreapp.Interface.ICategoriesListener;
import com.nelsonrueda.mercadolibreapp.Interface.IItemsListener;
import com.nelsonrueda.mercadolibreapp.Interface.IMercadoLibreListener;
import com.nelsonrueda.mercadolibreapp.R;
import com.nelsonrueda.mercadolibreapp.Views.Adapters.ItemsAdapter;

import java.util.ArrayList;

/**
 * Activity con la visualizacion de articulos y busqueda por categoria o una palabra especifica
 */
public class ProductActivity extends AppCompatActivity implements  IItemsListener, IMercadoLibreListener, IAdapterListener, ICategoriesListener {
    final private String TAG = "ProductActivity";

    private ProgressBar mProgressBar = null;
    private ConstraintLayout mParentProductLayout = null;
    private RecyclerView mItemListView = null;
    private Spinner mCategorySpinner = null;
    private TextView mTotalItemOfSearch = null;

    private AlertDialog mItemInformationDialog = null;

    private ItemsAdapter mItemAdapter = null;

    private MercadoLibreAPIManager mApiManagerByCustomItem = null;
    private MercadoLibreAPIManager mApiManagerByCategoryItem = null;
    private MercadoLibreAPIManager mApiManagerByCategories = null;

    private ArrayList<Category> mCategoryList = null;
    private ItemsByCategoryResult mItemsByCategory = null;
    private ItemsByDataCustomResult mItemsByCustomResult = null;
    private boolean IsItemsByCategory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        customActionBar();
        initView();
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
            Utils.ShowToast(this,Utils.GetResourceString(this,R.string.system_is_not_connected_internet));
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

    /**
     * Metodo donde se personaliza el ActionBar, dentro de este se configura
     * la barra de Busqueda y su eventos
     */
    private void customActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_with_search_and_categories);
        View view =getSupportActionBar().getCustomView();
        SearchView searchView = view.findViewById(R.id.actionbar_with_search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * En este evento se captura la accion de submit para tomar los escrito por el usuario
             * y consultar con la API la peticion
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                mCategorySpinner.setSelection(0);

                GetCustomItem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    /**
     * Se inicializan todos los Views del Layout y tambien se declara el listener de Spinner que contienes la
     * informacion de la categorias
     */
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
            /**
             * se captura el elementos del Spinner para identificar las categoria y enviar a realizar la peticion
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int
                    i, long l) {

                Category categorySelected = (Category) adapterView.getAdapter().getItem(i);
                if(!categorySelected.getId().equals("-1")){

                    GetItemsByCategory(categorySelected);
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

    /**
     * Metodo para realizar peticion de la busqueda de Item por una palabra
     * @param Data
     */
    private void GetCustomItem(String Data) {
        // if(!Utils.isNetworkAvailable(this))
        IsItemsByCategory = false;
        showProgress();
        sendRequestGetCustomItem(Data);
    }

    /**
     * Metodo para realizar peticion de la busqueda de items por una categoria
     * @param categorySelected
     */
    private void GetItemsByCategory(Category categorySelected) {
        // if(!Utils.isNetworkAvailable(this))
        IsItemsByCategory = true;
        showProgress();
        sendRequestGetCategoryItems(categorySelected);
    }

    /**
     * Metodo para realizar peticion para obtener las categorias
     */
    private void GetCategories() {
        // if(!Utils.isNetworkAvailable(this))
        showProgress();
        sendRequestCategories();
    }

    /**
     * Metodo que inicializa una nueva instancia del ApiManager para poder realizar una peticion de
     * busqueda de item por una palabra especifica
     * @param Data
     */
    private void sendRequestGetCustomItem(String Data){
        try{
            mApiManagerByCustomItem = new MercadoLibreAPIManager(this,TAG,this);
            mApiManagerByCustomItem.setObjectListener((IItemsListener) this);
            mApiManagerByCustomItem.GetItemByCustomResult(UserSettinsInMemory.GetCurrentSite().getId(),Data);
        }catch (Exception ex){
            Log.d(TAG, "sendRequestGetSites: ex:"+ ex.getMessage());
            Utils.ShowToast(getApplicationContext(),Utils.GetResourceString(getApplicationContext(),R.string.request_failed));
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

    /**
     * Metodo que inicializa una nueva instancia del ApiManager para poder realizar una peticion de
     * busqueda de item por una categoria
     * @param categorySelected
     */
    private void sendRequestGetCategoryItems(Category categorySelected){
        try{
            mApiManagerByCategoryItem = new MercadoLibreAPIManager(this,TAG, this);
            mApiManagerByCategoryItem.setObjectListener((IItemsListener) this);
            mApiManagerByCategoryItem.GetItemByCategory(UserSettinsInMemory.GetCurrentSite().getId(),categorySelected.getId());
        }catch (Exception ex){
            Log.d(TAG, "sendRequestGetSites: ex:"+ ex.getMessage());
            Utils.ShowToast(getApplicationContext(),Utils.GetResourceString(getApplicationContext(),R.string.request_failed));
        }
    }

    /**
     * Metodo que inicializa una nueva instancia del ApiManager para poder realizar una peticion para
     * obtener las categorias
     */
    private void sendRequestCategories(){
        try{
            mApiManagerByCategories = new MercadoLibreAPIManager(this,TAG,this);
            mApiManagerByCategories.setObjectListener((ICategoriesListener) this);
            mApiManagerByCategories.GetCategories(UserSettinsInMemory.GetCurrentSite().getId());
        }catch (Exception ex){
            Log.d(TAG, "sendRequestGetSites: ex:"+ ex.getMessage());
            Utils.ShowToast(getApplicationContext(),Utils.GetResourceString(getApplicationContext(),R.string.request_failed));
        }
    }

    /**
     * Metodo que escucha la seleccion de una elemento de la lista y visualiza una ventanda
     * de dialogo con su informacion
     * @param data
     */
    @Override
    public void onClickItemSelect(Object data) {
        if(data.getClass() == Result.class)
        {
            ShowDialogItemInformation((Result)data);
        }
    }

    /**
     * Metodo que inicializa un AlertDialo para visualizar la informacion del item seleccionado
     * @param data
     */
    private void ShowDialogItemInformation(Result data) {
        try{
            try{
                if(mItemInformationDialog != null && mItemInformationDialog.isShowing()){
                    mItemInformationDialog.dismiss();
                    mItemInformationDialog.cancel();
                }
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this,  AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater mInflater = LayoutInflater.from(this);
                View mDialogView = mInflater.inflate(R.layout.item_description_layout,null);
                ImageView miniatura = mDialogView.findViewById(R.id.item_thumbnail_detail_view);
                TextView itemTitleView = mDialogView.findViewById(R.id.item_title_description_detail_view);
                TextView itemPriceView  = mDialogView.findViewById(R.id.item_price_detail_view);
                TextView itemFullDescritionView  = mDialogView.findViewById(R.id.item_full_description);
                Button itemBuyButton = mDialogView.findViewById(R.id.item_button_buy);
                Button itemCloseButton= mDialogView.findViewById(R.id.item_button_back);

                Glide.with(mDialogView)
                        .load(data.getThumbnail())
                        .into(miniatura);
                miniatura.setContentDescription(data.getThumbnail_id());
                itemTitleView.setText(data.getTitle());
                itemPriceView.setText(Utils.FormatCurrency(data.getPrice()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    itemFullDescritionView.setText(Html.fromHtml(data.toString(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    itemFullDescritionView.setText(Html.fromHtml(data.toString()));
                }

                itemBuyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.ShowToast(getApplicationContext(),Utils.GetResourceString(getApplicationContext(),R.string.item_selected));
                    }
                });

                itemCloseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemInformationDialog.cancel();
                    }
                });


                alertDialog.setView(mDialogView);
                alertDialog.setCancelable(true);

                mItemInformationDialog = alertDialog.create();
                mItemInformationDialog.setCanceledOnTouchOutside(false);
                mItemInformationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mItemInformationDialog.show();
            }
            catch (Exception ex){
                Log.e(TAG,String.format("%s - Excepcion: %s","ShowDialogItemInformation",ex.getMessage()));
            }
        }
        catch (Exception ex){
            Log.e(TAG,String.format("%s - Excepcion: %s","ShowDialogItemInformation",ex.getMessage()));
            Utils.ShowToast(this,Utils.GetResourceString(this,R.string.item_not_load_dialog));
        }
    }

    /**
     * Metodo que escucha y recibe la data de obenido de la peticion de busqueda  de item por categoria
     * @param result
     */
    @Override
    public void GetItemByCategory(ItemsByCategoryResult result) {
        hideProgress();
        if(result != null){
            mItemsByCategory = result;
            mTotalItemOfSearch.setText(Utils.FormatNumberInstance(mItemsByCategory.getPaging().getTotal()));
            if(mItemsByCategory.getResults() != null && mItemsByCategory.getResults().size() > 0){
                mItemAdapter = new ItemsAdapter(mItemsByCategory.getResults(), (IAdapterListener) this);
                mItemListView.setHasFixedSize(true);
                mItemListView.setLayoutManager(new LinearLayoutManager(this));
                mItemListView.setAdapter(mItemAdapter);
            }
            else {
                mItemListView.setAdapter(null);
            }
        }else{
            mItemListView.setAdapter(null);
            Utils.ShowToast(this,Utils.GetResourceString(this,R.string.product_not_get_results));
        }
    }

    /**
     *  Metodo que escucha y recibe la data de obenido de la peticion de busqueda  de item por una palabra especifica
     * @param result
     */
    @Override
    public void GetItemByCustomResult(ItemsByDataCustomResult result) {
        hideProgress();
        if(result != null){
            mItemsByCustomResult = result;
            mTotalItemOfSearch.setText(Utils.FormatNumberInstance(mItemsByCustomResult.getPaging().getTotal()));
            if(mItemsByCustomResult.getResults() != null && mItemsByCustomResult.getResults().size() > 0){
                mItemAdapter = new ItemsAdapter(mItemsByCustomResult.getResults(),(IAdapterListener) this);
                mItemListView.setHasFixedSize(true);
                mItemListView.setLayoutManager(new LinearLayoutManager(this));
                mItemListView.setAdapter(mItemAdapter);
            }
            else {
                mItemListView.setAdapter(null);
            }
        }else{
            mItemListView.setAdapter(null);
            Utils.ShowToast(this,Utils.GetResourceString(this,R.string.product_not_get_results));
        }
    }

    /**
     *  Metodo que escucha los reporte de errores que son Notificado por el APIManager
     * @param TAG
     * @param Message
     */
    @Override
    public void ReportError(String TAG, String Message) {
        if(this.TAG.equals(TAG)){
            mProgressBar.setVisibility(View.GONE);
            Utils.ShowToast(this,Message);
            if(Message.equals(Utils.GetResourceString(this,R.string.system_is_not_connected_internet)))
            {
                Utils.ShowToast(this,Utils.GetResourceString(this,R.string.system_is_not_connected_internet));
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }
    }

    /**
     *  Metodo que escucha y recibe la data de obenida de la peticion de categoria
     * @param categoriesResult
     */
    @Override
    public void GetCategories(ArrayList<Category> categoriesResult) {
        if(mCategoryList == null) mCategoryList = new ArrayList<>();
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
            Utils.ShowToast(this,Utils.GetResourceString(this,R.string.categories_is_empty));
        }
        mProgressBar.setVisibility(View.GONE);
    }



}