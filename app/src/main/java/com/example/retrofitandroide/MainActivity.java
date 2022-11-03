package com.example.retrofitandroide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofitandroide.Interface.JsonPlaceHolderApi;
import com.example.retrofitandroide.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonTxtView = findViewById(R.id.jsonText);
        getPosts();
    }

    private void getPosts() {

        Retrofit retrofit = new Retrofit.Builder() //Creamos una instancia de Retrofit
                .baseUrl("https://jsonplaceholder.typicode.com/") //Añadimos la URL base
                .addConverterFactory(GsonConverterFactory.create()) //Añadimos un converter
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class); //Llamamos a la Interface

        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){ // Si no ha tenido exido
                    mJsonTxtView.setText("Código: " +response.code()); //Nos da un código de respuesta erronea
                    return;
                }
                // Si pasa el primer if es por que tenemos los datos

                List<Posts> postsList = response.body();

                for (Posts post: postsList){
                    String content = "";
                    content +="userId:"+post.getUserId() + "\n";
                    content +="id:"+post.getId() + "\n";
                    content +="title:"+post.getTitle() + "\n";
                    content +="body:"+post.getBody() + "\n\n";

                    mJsonTxtView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mJsonTxtView.setText("Error: " + t.getMessage());
            }
        });

    }
}