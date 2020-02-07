package com.example.socialmediaez.api;

import com.example.socialmediaez.model.Login;
import com.example.socialmediaez.model.User;
import com.example.socialmediaez.responses.LoginResponse;
import com.example.socialmediaez.responses.RegisterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyApi {

    @POST("user/login")
    Call<LoginResponse> login (@Body Login login);

    @POST("user/register")
    Call<RegisterResponse> register (@Body User user);


}
