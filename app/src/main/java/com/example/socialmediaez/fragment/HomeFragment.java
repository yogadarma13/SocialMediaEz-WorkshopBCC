package com.example.socialmediaez.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.socialmediaez.R;
import com.example.socialmediaez.activity.CreatePostActivity;
import com.example.socialmediaez.adapter.PostAdapter;
import com.example.socialmediaez.api.ApiClient;
import com.example.socialmediaez.api.MyApi;
import com.example.socialmediaez.model.Post;
import com.example.socialmediaez.responses.PostResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private Toolbar toolbar;
    private Button btn_create_post;
    private RecyclerView rv_post;
    MyApi myApi;

    public HomeFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.toolbar_home);
        toolbar.setTitle("Twettir");

        rv_post = view.findViewById(R.id.rv_post);

        getAllPost();

        btn_create_post = view.findViewById(R.id.btn_create);
        btn_create_post.setOnClickListener(this);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getAllPost() {
        myApi = ApiClient.getClient().create(MyApi.class);
        Call<PostResponse> postCall = myApi.getAllPost();
        postCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<Post> posts = response.body().getData();
                    rv_post.setAdapter(new PostAdapter(getContext(), posts));
                    rv_post.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });
    }

}
