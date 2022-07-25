package com.edufire.dic3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class GroupAdapter  extends RecyclerView.Adapter<GroupAdapter.MyViewHolder>{
    private Context context;
    private boolean isAccept;
    private List<String> users = new ArrayList<>();
    private String username;

    public GroupAdapter(Context context, boolean isAccept, String username) {
        this.context = context;
        this.isAccept = isAccept;
        this.username = username;
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
        if(isAccept) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!MainActivity.db.IsInSameGroup(username, users.get(position)))
                        MainActivity.db.insertGroups(username, users.get(position));
                    if(MainActivity.db.isTeammateRequestExistsInDatabase(username, users.get(position)))
                        MainActivity.db.deleteTeammateRequest(username, users.get(position));
                    setGroups(MainActivity.db.getInvitation(username));
                    Toast.makeText(context, "now you are in a group", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username_group);
            cardView = itemView.findViewById(R.id.cardView_group);
        }
    }
}
