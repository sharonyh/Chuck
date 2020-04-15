package com.example.chuck;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FactService {
    @GET("/jokes/random?category=dev")
    Call<Fact> getFacts();
}
