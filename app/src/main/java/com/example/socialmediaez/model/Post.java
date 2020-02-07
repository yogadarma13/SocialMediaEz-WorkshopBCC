package com.example.socialmediaez.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {

    private int id, id_user;
    private String content;
    private boolean isDeleted;
    private String name;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.id_user);
        dest.writeString(this.content);
        dest.writeByte(this.isDeleted ? (byte) 1 : (byte) 0);
    }

    protected Post(Parcel in) {
        this.id = in.readInt();
        this.id_user = in.readInt();
        this.content = in.readString();
        this.isDeleted = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
