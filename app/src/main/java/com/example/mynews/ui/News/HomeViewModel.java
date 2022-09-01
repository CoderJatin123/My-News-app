package com.example.mynews.ui.News;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(1);
    }

    void updateText(String s){
        mText.setValue(1 + mText.getValue());
    }
public LiveData<Integer> getText() {
        return mText;
    }
}