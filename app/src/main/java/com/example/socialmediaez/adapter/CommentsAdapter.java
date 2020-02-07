package com.example.socialmediaez.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaez.R;
import com.example.socialmediaez.activity.PostActivity;
import com.example.socialmediaez.model.Comment;
import com.example.socialmediaez.model.Post;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<Comment> list_comments;

    public CommentsAdapter(Context context, ArrayList<Comment> list_comments) {
        this.context = context;
        this.list_comments = list_comments;
    }

    public void setData(ArrayList<Comment> items) {
        list_comments.clear();
        list_comments.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        final ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(v1 -> {
//            Intent intent = new Intent(context, PostActivity.class);
//            intent.putExtra("post", list_comments.get(holder.getAdapterPosition()));
//            context.startActivity(intent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(list_comments.get(position));

    }

    @Override
    public int getItemCount() {
        return list_comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_person_name;
        private final TextView tv_content;

        ViewHolder(View itemView) {
            super(itemView);
            tv_person_name = itemView.findViewById(R.id.tv_person_name);
            tv_content = itemView.findViewById(R.id.tv_content);
        }

        void bind(Comment comment) {
            tv_person_name.setText(String.valueOf(comment.getId()));
            tv_content.setText(comment.getContent());
        }
    }
}