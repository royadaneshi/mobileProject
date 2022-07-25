package com.edufire.dic3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edufire.dic3.Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> users = new ArrayList<>();

    public UserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getUsername());
        holder.password.setText(users.get(position).getPassword());
        holder.limitRequestCounter.setText(String.valueOf(users.get(position).getLimitRequestCounter()));
        holder.score.setText(String.valueOf(0));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName, password, limitRequestCounter, score;
        ImageView userImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.UserName_adminPage);
            password = itemView.findViewById(R.id.Password_adminPage);
            userImage = itemView.findViewById(R.id.UserImageView);
            limitRequestCounter = itemView.findViewById(R.id.limitRequestCounter_adminPage);
            score = itemView.findViewById(R.id.score_adminPage);
        }
    }
}
