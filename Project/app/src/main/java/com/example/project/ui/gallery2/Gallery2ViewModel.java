package com.example.project.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Gallery2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Gallery2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery2 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}