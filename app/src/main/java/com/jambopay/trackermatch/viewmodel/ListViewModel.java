package com.jambopay.trackermatch.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jambopay.trackermatch.model.Model;

import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<Model>> matches = new MutableLiveData<List<Model>>();
    public MutableLiveData<Boolean> matchError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh() {

    }

    private void fetchMatches() {

    }


}
