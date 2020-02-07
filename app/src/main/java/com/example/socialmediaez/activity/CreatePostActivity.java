package com.example.socialmediaez.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.socialmediaez.R;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edt_content;
    private Toolbar toolbar;
    private Button btn_submit_post;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Create Post");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_content = findViewById(R.id.edt_content);
        edt_content.requestFocus();

        btn_submit_post = findViewById(R.id.btn_submit_post);
        btn_submit_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_post:
                finish();
                break;
        }
    }
}
