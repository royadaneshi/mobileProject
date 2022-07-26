package com.edufire.dic3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.edufire.dic3.Models.Word;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {
    private Context context;
    private boolean isAccept;
    private boolean isWordSearch;
    private String username;
    private List<String> users = new ArrayList<>();

    public GroupAdapter(Context context, boolean isAccept, String username, boolean isWordSearch) {
        this.context = context;
        this.isAccept = isAccept;
        this.username = username;
        this.isWordSearch = isWordSearch;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setGroups(List<String> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.group_cardview, parent, false);
        return new GroupAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.userName.setText(users.get(position));
        holder.cardView.setOnClickListener(view -> {
            if (isAccept) {
                if (!MainActivity.db.IsInSameGroup(username, users.get(position))) {
                    MainActivity.db.insertGroups(username, users.get(position));
                    if (MainActivity.db.isTeammateRequestExistsInDatabase(users.get(position), username) ||
                            MainActivity.db.isTeammateRequestExistsInDatabase(username, users.get(position)))
                        MainActivity.db.deleteTeammateRequest(username, users.get(position));
                    Toast.makeText(context, "now you are in a group", Toast.LENGTH_SHORT).show();
                    setGroups(MainActivity.db.getInvitation(username));

                } else {
                    Toast.makeText(context, "you already in a group", Toast.LENGTH_SHORT).show();
                }
            } else if (isWordSearch) {
                Intent intent = new Intent(context, WordActivity.class);
                intent.putExtra("userName",username);
                intent.putExtra("word", users.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username_group);
            cardView = itemView.findViewById(R.id.cardView_group);
        }
    }
}
