package com.example.project.ui.Menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Gallery1ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Gallery1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery1 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}