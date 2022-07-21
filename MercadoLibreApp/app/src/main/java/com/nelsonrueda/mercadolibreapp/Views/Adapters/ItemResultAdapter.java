package com.nelsonrueda.mercadolibreapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Result;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Site;
import com.nelsonrueda.mercadolibreapp.Interface.IAdapterListener;
import com.nelsonrueda.mercadolibreapp.R;

import java.util.ArrayList;

public class ItemResultAdapter extends ArrayAdapter<Result> implements View.OnClickListener{

    private ArrayList<Result> dataSet;
    Context mContext;
    IAdapterListener mListener;

    public ItemResultAdapter(@NonNull Context context, ArrayList<Result> data, IAdapterListener listener) {
        super(context, R.layout.item_previous_info_layout,data);
        this.dataSet = data;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Result itemInfo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item_previous_info_layout,parent,false);
        }
        ConstraintLayout ParentContent = (ConstraintLayout) convertView.findViewById(R.id.item_previous_content);
        ImageView imgThumbnail = (ImageView)convertView.findViewById(R.id.item_thumbnail_previuos_list);
        TextView itemTitle = (TextView)convertView.findViewById(R.id.item_title_description_list);
        TextView itemPrice = (TextView)convertView.findViewById(R.id.item_price_list);
        ParentContent.setTag(position);

        itemTitle.setText(itemInfo.getTitle());
        itemPrice.setText(itemInfo.getPrice());

        Glide.with(mContext).load(itemInfo.getThumbnail()).into(imgThumbnail);


        ParentContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=(Integer) view.getTag();
                Object object= getItem(position);
                Result itemSeletect =(Result)object;
                if(itemSeletect != null){
                    mListener.onClickItemSelect(itemSeletect);
                }
                else{
                    Toast.makeText(mContext,"No se pudo obtener información del item seleccionado",Toast.LENGTH_LONG);
                }
            }
        });

        return convertView;
    }



    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Result itemSeletect =(Result)object;
        if(itemSeletect != null){
            mListener.onClickItemSelect(itemSeletect);
        }
        else{
            Toast.makeText(mContext,"No se pudo obtener información del item seleccionado",Toast.LENGTH_LONG);
        }
    }
}
