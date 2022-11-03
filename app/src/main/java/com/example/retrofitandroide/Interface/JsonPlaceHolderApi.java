package com.example.retrofitandroide.Interface;

import com.example.retrofitandroide.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    // Esta es la parte de la URL donde busca la información que pusimos en el modelo (clase Posts)
    @GET("posts")
    Call<List<Posts>> getPosts();
}
