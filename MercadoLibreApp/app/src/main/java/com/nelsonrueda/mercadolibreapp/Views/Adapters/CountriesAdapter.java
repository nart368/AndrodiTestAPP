package com.nelsonrueda.mercadolibreapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.nelsonrueda.mercadolibreapp.Entities.Models.Site;
import com.nelsonrueda.mercadolibreapp.Interface.IAdapterListener;
import com.nelsonrueda.mercadolibreapp.R;

import java.util.ArrayList;

public class CountriesAdapter extends ArrayAdapter<Site> implements View.OnClickListener {

    private ArrayList<Site> dataSet;
    Context mContext;
    IAdapterListener mListener;

    public CountriesAdapter(@NonNull Context context, ArrayList<Site> data, IAdapterListener listener) {
        super(context, R.layout.country_item_layout,data);
        this.dataSet = data;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Site siteItem = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.country_item_layout,parent,false);
        }
        ConstraintLayout ParentContent = (ConstraintLayout) convertView.findViewById(R.id.countries_item_parent_content);
        TextView prefixSite = (TextView)convertView.findViewById(R.id.country_item_prefix);
        TextView captionSite = (TextView)convertView.findViewById(R.id.country_item_caption);
        ParentContent.setTag(position);
        prefixSite.setText(siteItem.getId());
        prefixSite.setVisibility(View.GONE);
        captionSite.setText(siteItem.getName());

        ParentContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=(Integer) view.getTag();
                Object object= getItem(position);
                Site siteModel =(Site)object;
                if(siteModel != null){
                    mListener.onClickItemSelect(siteModel);
                }
                else{
                    Toast.makeText(mContext,"No se pudo obtener información del Pais seleccionado",Toast.LENGTH_LONG);
                }
            }
        });

        return convertView;
    }


    @Override
    public void onClick(View view) {
        int position=(Integer) view.getTag();
        Object object= getItem(position);
        Site siteModel =(Site)object;
        if(siteModel != null){
            mListener.onClickItemSelect(siteModel);
        }
        else{
            Toast.makeText(mContext,"No se pudo obtener información del Pais seleccionado",Toast.LENGTH_LONG);
        }
    }
}
