package com.example.socialmediaez.responses;

import com.example.socialmediaez.model.Comment;
import com.example.socialmediaez.model.Post;

import java.util.ArrayList;

public class CommentResponse {
    private boolean success;
    private String message;
    private ArrayList<Comment> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Comment> getData() {
        return data;
    }
}
