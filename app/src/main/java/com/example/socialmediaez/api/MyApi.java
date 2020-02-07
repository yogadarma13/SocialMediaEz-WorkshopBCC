package com.example.socialmediaez.api;

import com.example.socialmediaez.model.Login;
import com.example.socialmediaez.model.User;
import com.example.socialmediaez.responses.CommentResponse;
import com.example.socialmediaez.responses.LoginResponse;
import com.example.socialmediaez.responses.PostResponse;
import com.example.socialmediaez.responses.RegisterResponse;
import com.example.socialmediaez.responses.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApi {

    @POST("user/login")
    Call<LoginResponse> login (@Body Login login);

    @POST("user/register")
    Call<RegisterResponse> register (@Body User user);

    @GET("user/{id}")
    Call<UserResponse> getUserById(@Path("id") int id);

    @GET("post")
    Call<PostResponse> getAllPost ();

    @GET("comment/{id}")
    Call<CommentResponse> getAllCommentOnSpecificPost(@Path("id") int id);

}
