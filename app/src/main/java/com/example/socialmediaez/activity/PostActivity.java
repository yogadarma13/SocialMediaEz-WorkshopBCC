package com.example.socialmediaez.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.socialmediaez.R;
import com.example.socialmediaez.adapter.CommentsAdapter;
import com.example.socialmediaez.adapter.PostAdapter;
import com.example.socialmediaez.api.ApiClient;
import com.example.socialmediaez.api.MyApi;
import com.example.socialmediaez.model.Comment;
import com.example.socialmediaez.model.Post;
import com.example.socialmediaez.responses.CommentResponse;
import com.example.socialmediaez.responses.PostResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    Post post;
    TextView tv_person_name;
    TextView tv_content;
    Toolbar toolbar;
    MyApi myApi;
    RecyclerView rv_comment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Post");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        post = getIntent().getParcelableExtra("post");

        tv_person_name = findViewById(R.id.tv_person_name);
        tv_content = findViewById(R.id.tv_content);

        tv_person_name.setText(String.valueOf(post.getId()));
        tv_content.setText(post.getContent());

        rv_comment = findViewById(R.id.rv_comment);
        getAllComment(post.getId());
    }


    private void getAllComment(int id) {
        myApi = ApiClient.getClient().create(MyApi.class);
        Call<CommentResponse> commentCall = myApi.getAllCommentOnSpecificPost(id);
        commentCall.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<Comment> comments = response.body().getData();
                    rv_comment.setAdapter(new CommentsAdapter(getApplicationContext(), comments));
                    rv_comment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {

            }
        });
    }
}
