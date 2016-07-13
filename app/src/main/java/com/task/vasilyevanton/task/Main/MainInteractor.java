package com.task.vasilyevanton.task.Main;


import android.app.Activity;

import com.task.vasilyevanton.task.pojos.PeopleData;

import java.util.ArrayList;

public interface MainInteractor {


    void getDatabaseData(OnDataBaseObtainListener listener, Activity activity);
    void setDatabaseData(ArrayList<PeopleData> data, Activity activity);

    void getApiData(OnDataObtainListener listener);

    interface OnDataObtainListener {
        void onErrorApi();
        void onSuccessApi(ArrayList<PeopleData> data);
    }

    interface OnDataBaseObtainListener {
        void onEmptyDatabase();
        void onSuccessDatabase(ArrayList<PeopleData> data);
    }
}
