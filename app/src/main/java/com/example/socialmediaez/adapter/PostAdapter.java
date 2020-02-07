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
import com.example.socialmediaez.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Post> list_post = new ArrayList<>();

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Post> items) {
        list_post.clear();
        list_post.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        final ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(v1 -> {
            Intent intent = new Intent(context, PostActivity.class);
            context.startActivity(intent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(list_post.get(position));

    }

    @Override
    public int getItemCount() {
        return list_post.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_person_name;
        private final TextView tv_content;
        private final Button btn_expand;

        ViewHolder(View itemView) {
            super(itemView);
            tv_person_name = itemView.findViewById(R.id.tv_person_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            btn_expand = itemView.findViewById(R.id.btn_expand);
        }

        void bind(Post post) {
            tv_person_name.setText(String.valueOf(post.getId()));
            tv_content.setText(post.getContent());
        }
    }
}