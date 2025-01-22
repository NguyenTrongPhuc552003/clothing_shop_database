package com.example.newdemo.ProjectMobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.newdemo.ProjectMobile.domain.PopularDomain;
import com.example.newdemo.databinding.ViewholderCartBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
//Source code lấy từ PopularAdapter qua, xóa bớt mấy cái thừa với lỗi,
// đã chỉnh sửa đuợc cho hiển thị (SetText) Tên sản phẩm và Giá tiền của 1 sản phẩm.
// Còn lại mấy cái như là Giá tiền 1 sản phẩm có n số lượng,
// nhấn dấu + hoặc -  thì thay đổi hiển thị số lượng
// (dấu + và - setting trong UI là dạng Text chứ k phải Button nhưng setting OnListener vẫn hoạt động bth được như button).
// Tổng giá tiền , Tổng số lượng sản phẩm, Phí phân phối; Thành tiền (Tổng tất cả).
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<PopularDomain> items;
    Context context;
    ViewholderCartBinding binding;

    public CartAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        binding.CartProduct.setText(items.get(position).getTitleProduct());
        binding.price1.setText("VNĐ " + formatPrice(items.get(position).getPrice()));
//Còn setText của priceMulti (setText cho giá tổng của 1 sản phẩm số lượng n
// và setting cho countQuantity thay đổi (setText đếm số lượng thay đổi khi nhấn + hoặc - )

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl()
                ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.CartPic);

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    private String formatPrice(double price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(price);
    }
    public static class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(ViewholderCartBinding binding) {
            super(binding.getRoot());
        }
    }
}
