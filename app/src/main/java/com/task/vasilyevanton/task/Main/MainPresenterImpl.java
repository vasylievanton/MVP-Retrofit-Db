package com.task.vasilyevanton.task.Main;


import android.app.Activity;
import android.util.Log;

import com.task.vasilyevanton.task.pojos.PeopleData;

import java.util.ArrayList;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnDataObtainListener, MainInteractor.OnDataBaseObtainListener {

    private MainView mainView;
    private MainInteractor mainInteractor;
    private Activity mActivity;

    public MainPresenterImpl(MainView mainView, Activity activity) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
        this.mActivity=activity;
    }

    //from VIEW
    @Override
    public void getPeopleInfo() {
        if (mainView != null) {
            Log.w("getPeopleInfo", "Get db data");
            mainInteractor.getDatabaseData(this, mActivity);
        }

    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
        }
    }

    @Override
    public void getApiData() {
        Log.w("getPeopleInfo", "Get api data");
        mainInteractor.getApiData(this);
    }


    //to VIEW
    @Override
    public void onErrorApi() {
        Log.w("error:","Check Internet Connection");
        //error
    }

    @Override
    public void onSuccessApi(ArrayList<PeopleData> data) {
        if (mainView != null) {
            Log.w("fromApi", "set db data");

            mainView.setPeopleInfoData(data);
            mainInteractor.setDatabaseData(data, mActivity);
        }
    }

    @Override
    public void onEmptyDatabase() {
        Log.w("Empty db", "Get Api data");
        mainInteractor.getApiData(this);
    }


    @Override
    public void onSuccessDatabase(ArrayList<PeopleData> data) {
        if (mainView != null) {
            Log.w("Load db", "data");
            mainView.setPeopleInfoData(data);
        }
    }

}
