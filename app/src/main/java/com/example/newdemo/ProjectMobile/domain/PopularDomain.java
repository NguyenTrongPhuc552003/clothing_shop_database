package com.example.newdemo.ProjectMobile.domain;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.newdemo.ProjectMobile.Adapter.CartAdapter;
import com.example.newdemo.databinding.ViewholderCartBinding;

public class PopularDomain {
    private String titleProduct;
    private String picUrl;
    private double price;

    public PopularDomain(String titleProduct, String picUrl, double price) {
        this.titleProduct = titleProduct;
        this.picUrl = picUrl;
        this.price = price;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    ViewholderCartBinding binding;
//    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
//        return new CartAdapter.Viewholder(binding);
//    }
//
//    public int getNumberInCart() {
//        return Integer.parseInt((String)binding.countQuantity.getText());
//    }
}
