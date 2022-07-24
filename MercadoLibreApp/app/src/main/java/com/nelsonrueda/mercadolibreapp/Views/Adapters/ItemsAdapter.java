package com.nelsonrueda.mercadolibreapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Result;
import com.nelsonrueda.mercadolibreapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    ArrayList<Result> mItemList;

    public ItemsAdapter(ArrayList<Result> itemList){
        this.mItemList = itemList;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_previous_info_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        final Result itemSelected = mItemList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(itemSelected.getThumbnail())
                .into(holder.imageView);
        holder.titleView.setText(itemSelected.getTitle());
        holder.priceView.setText(""+itemSelected.getPrice());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView titleView;
        public TextView priceView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.item_thumbnail_previuos_list);
            this.titleView = itemView.findViewById(R.id.item_title_description_list);
            this.priceView = itemView.findViewById(R.id.item_price_list);
        }
    }
}
