package com.mustofakamal.jfood_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mustofakamal.jfood_android.R;
import com.mustofakamal.jfood_android.object.Food;

import java.util.List;

/**
 * Class ChartAdapter adalah class yang berfungsi untuk memproses
 * daftar food pada chart dalam recyclerview.
 *
 * @author Mustofa Kamal
 * @version 07-06-2020
 */
public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {

    private List<Food> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ChartAdapter(Context context, List<Food> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.chart_view_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String animal = mData.get(position).getName();
        holder.myTextView.setText(String.valueOf(position+1));
        holder.tvFoodName.setText(mData.get(position).getName());
        holder.tvFoodPrice.setText(String.valueOf(mData.get(position).getPrice()));
        holder.tvFoodCategory.setText(mData.get(position).getCategory().toString());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView tvCode;
        TextView tvFoodName;
        TextView tvFoodCategory;
        TextView tvFoodPrice;
        TextView tvTotalPrice;
        ImageButton btnRemove;
        TextView tvPesanan;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.food_id);
            tvFoodName = itemView.findViewById(R.id.food_name);
            tvFoodPrice = itemView.findViewById(R.id.food_price);
            tvFoodCategory = itemView.findViewById(R.id.food_category);
            tvTotalPrice = itemView.findViewById(R.id.total_price);
            tvCode = itemView.findViewById(R.id.promo_code);
            tvFoodName = itemView.findViewById(R.id.food_name);
            btnRemove = itemView.findViewById(R.id.btn_remove);
            btnRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getName();
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
