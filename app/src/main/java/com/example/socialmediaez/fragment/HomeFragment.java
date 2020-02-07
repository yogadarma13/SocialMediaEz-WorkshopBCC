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
import android.widget.LinearLayout;

import com.example.socialmediaez.R;
import com.example.socialmediaez.activity.CreatePostActivity;
import com.example.socialmediaez.adapter.PostAdapter;
import com.example.socialmediaez.model.Post;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private Toolbar toolbar;
    private Button btn_create_post;
    private RecyclerView rv_post;

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

        PostAdapter postAdapter = new PostAdapter(getContext());
        postAdapter.setData(getAllPost());
        rv_post.setAdapter(postAdapter);
        rv_post.setLayoutManager(new LinearLayoutManager(getContext()));

        btn_create_post = view.findViewById(R.id.btn_create);
        btn_create_post.setOnClickListener(this);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_create:
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                startActivity(intent);
                break;
        }
    }


    private ArrayList<Post> getAllPost(){
        Post post1 = new Post();
        post1.setId(1);
        post1.setId_user(0);
        post1.setContent("Satu dua tiga empat lima enam");
        post1.setDeleted(false);
        post1.setCreatedAt("1231232");
        post1.setUpdatedAt("12312313");

        ArrayList<Post> postModelArrayList = new ArrayList<>();
        postModelArrayList.add(post1);

        return postModelArrayList;
    }

}
