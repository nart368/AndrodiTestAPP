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

import com.nelsonrueda.mercadolibreapp.Entities.Models.Result;
import com.nelsonrueda.mercadolibreapp.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private ArrayList<Result> mItemsData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public ItemsAdapter(Context context,ArrayList<Result> data){
        this.mInflater = LayoutInflater.from(context);
        this.mItemsData = data != null?data:new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_previous_info_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder holder, int position) {
        Result item = mItemsData.get(position);
        holder.mTitleItem.setText(item.getTitle());
        holder.mPriceItem.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return mItemsData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mThumbnailItem;
        TextView mTitleItem;
        TextView mPriceItem;
        LinearLayout mParentContent;

        ItemViewHolder(View itemView) {
            super(itemView);
            mParentContent = itemView.findViewById(R.id.item_previous_content);
            mThumbnailItem = itemView.findViewById(R.id.item_thumbnail_previuos_list);
            mTitleItem = itemView.findViewById(R.id.item_title_description_list);
            mPriceItem = itemView.findViewById(R.id.item_price_list);
            mParentContent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    private Result getItem(int id) {
        return mItemsData.get(id);
    }

    public void AddItems(ArrayList<Result> items){
        if(mItemsData == null) mItemsData = new ArrayList<>();
        mItemsData.clear();
        mItemsData.addAll(items);
        notifyDataSetChanged();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
