package com.example.task1_activitylifecyclemethods.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task1_activitylifecyclemethods.R;
import com.example.task1_activitylifecyclemethods.models.Data;

import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends ViewModel {
    private final MutableLiveData<List<Data>> listData = new MutableLiveData<>();

    public LiveData<List<Data>> getListData() {
        return listData;
    }

    public void loadData() {
        List<Data> dummyData = new ArrayList<>();
        dummyData.add(new Data(R.drawable.profile, "Item 1", "Description 1"));
        dummyData.add(new Data(R.drawable.notification, "Item 2", "Description 2"));
        dummyData.add(new Data(R.drawable.wallet, "Item 3", "Description 3"));
        dummyData.add(new Data(R.drawable.settings, "Item 3", "Description 3"));;
        listData.setValue(dummyData);
    }
}