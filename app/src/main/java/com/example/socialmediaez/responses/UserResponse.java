package com.example.socialmediaez.responses;

import com.example.socialmediaez.model.Comment;
import com.example.socialmediaez.model.User;

import java.util.ArrayList;

public class UserResponse {
    private boolean success;
    private String message;
    private ArrayList<User> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<User> getData() {
        return data;
    }
}
