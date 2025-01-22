package com.example.newdemo.ProjectMobile.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newdemo.ProjectMobile.Adapter.PopularAdapter;
import com.example.newdemo.ProjectMobile.domain.PopularDomain;
import com.example.newdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRycyclerView();
    }

    private void initRycyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T1 Worlds 2024 Jacket","jackett_t1_2024",3500000));
        items.add(new PopularDomain("T1 Worlds 2024 Jersey","jersey_t1_2024",2900000));
        items.add(new PopularDomain("T1 World Champions 2024 T-Shirt","world_champions",1000000));
        items.add(new PopularDomain("T1 Worlds 2024 Pants","t1_pants_2024",2500000));
        binding.popularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.popularView.setAdapter(new PopularAdapter(items));
    }
}
