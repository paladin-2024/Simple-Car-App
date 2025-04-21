package com.example.task1_activitylifecyclemethods;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.task1_activitylifecyclemethods.adapters.CustomListAdapter;
import com.example.task1_activitylifecyclemethods.viewmodels.DataViewModel;
import com.google.android.material.tabs.TabLayout;

public class SettingsActivity extends AppCompatActivity {

    private CustomListAdapter adapter;
    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ListView listView = findViewById(R.id.settingsList);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Setup ListView Adapter
        adapter = new CustomListAdapter();
        listView.setAdapter((ListAdapter) adapter);

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
                            MainActivity.class : UserActivity.class;
                    startActivity(new Intent(SettingsActivity.this, target));
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
