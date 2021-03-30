package com.example.spacex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("crew")
    Call<List<Crew>> getCrewMembers();
}
