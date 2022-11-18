package com.example.mynews.ui.News;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mynews.Model_News;

import java.util.ArrayList;
import java.util.Objects;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> catagory;
    private MutableLiveData<ArrayList<Model_News>> newsList=new MutableLiveData<>();
    private ArrayList<Model_News> List=new ArrayList<>();
    private MutableLiveData<Context> context;

    public HomeViewModel() {
        catagory = new MutableLiveData<>();
        newsList = new MutableLiveData<>();
        context = new MutableLiveData<>();
        List =new ArrayList<Model_News>();


    }


    public void updateNewsArray(ArrayList<Model_News> jsonArray){

       this.newsList.setValue(jsonArray);

        Log.d("TEST", List.toString());

    }
    public void updateCatagory(String catgory){

        this.catagory.setValue(catgory);


    }
    public void setContext(Context context){
        this.context.setValue(context);
    }
    public LiveData<Context> getcontext(){
        return this.context;
    }


    public  LiveData<ArrayList<Model_News>> getNewsList(){
        Log.d("TEST",this.List.toString());
        return this.newsList;
    }


public LiveData<String> getCatagory() {
        return catagory;
    }


}