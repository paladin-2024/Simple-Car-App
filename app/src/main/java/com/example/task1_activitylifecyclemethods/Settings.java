package com.example.task1_activitylifecyclemethods;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.task1_activitylifecyclemethods.adapters.CustomAdapter;
import com.example.task1_activitylifecyclemethods.viewmodels.DataViewModel;
import com.google.android.material.tabs.TabLayout;

public class Settings extends AppCompatActivity {

    private CustomAdapter adapter;
    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        // Setup ViewModel
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        viewModel.getListData().observe(this, dataList -> {
            adapter.setListData(dataList);
        });
        viewModel.loadData();

        // Setup Tab Navigation
        tabLayout.selectTab(tabLayout.getTabAt(2));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != 2) {
                    Class<?> target = tab.getPosition() == 0 ?
                            MainActivity.class : User.class;
                    startActivity(new Intent(Settings.this, target));
                    finish();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}