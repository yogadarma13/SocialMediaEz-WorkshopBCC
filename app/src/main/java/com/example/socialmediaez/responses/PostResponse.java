package com.example.socialmediaez.responses;

import com.example.socialmediaez.model.Post;

import java.util.ArrayList;

public class PostResponse {
    private boolean success;
    private String message;
    private ArrayList<Post> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Post> getData() {
        return data;
    }
}
