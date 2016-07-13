package com.task.vasilyevanton.task;

import com.task.vasilyevanton.task.pojos.PeopleData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("/users/{username}")
    Call<PeopleData> getPeople(@Path("username") String username);
}
