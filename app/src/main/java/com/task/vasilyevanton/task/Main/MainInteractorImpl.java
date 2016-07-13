package com.task.vasilyevanton.task.Main;


import android.app.Activity;
import android.util.Log;

import com.task.vasilyevanton.task.API;
import com.task.vasilyevanton.task.DataBaseHandler;
import com.task.vasilyevanton.task.pojos.PeopleData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainInteractorImpl implements MainInteractor {
    private API service;
    private String BASE_URL = "https://api.github.com";

    //DataBase


    @Override
    public void getDatabaseData(OnDataBaseObtainListener listener, Activity activity) {
        DataBaseHandler db = new DataBaseHandler(activity);
        if (db.getPeopleCount() == 0) {
            listener.onEmptyDatabase();
        } else {
            listener.onSuccessDatabase(db.getPeoples());
        }

    }

    @Override
    public void setDatabaseData(ArrayList<PeopleData> data, Activity activity) {
        DataBaseHandler db = new DataBaseHandler(activity);
        db.addPeoples(data);
    }


    //Api
    @Override
    public void getApiData(OnDataObtainListener listener) {
        createAPIService();
        obtainOrdersData(listener);
    }


    private void obtainOrdersData(final OnDataObtainListener listener) {
        service.getPeople("vasylievanton").enqueue(new Callback<PeopleData>() {
            @Override
            public void onResponse(Call<PeopleData> call, Response<PeopleData> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    Log.w("ID", response.body().getAvatarUrl() + "");
                    ArrayList<PeopleData> data = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        data.add(response.body());
                    }
                    listener.onSuccessApi(data);
                } else {
                    listener.onErrorApi();
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                }
            }

            @Override
            public void onFailure(Call<PeopleData> call, Throwable t) {
                listener.onErrorApi();
            }
        });
    }

    private void createAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API.class);
    }
}
