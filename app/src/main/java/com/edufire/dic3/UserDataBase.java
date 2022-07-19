package com.edufire.dic3;

import android.content.Context;

import androidx.annotation.NonNull;

import com.edufire.dic3.Models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataBase {
    private final DatabaseReference databaseReference;
    FirebaseDatabase db;
    Context context;

    public UserDataBase(Context context) {
        db = FirebaseDatabase.getInstance("https://dic3-fd99b-default-rtdb.firebaseio.com");
        databaseReference = db.getReference("User Information");
        this.context = context;
    }

    public void add(User user){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isExist = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String name = String.valueOf(dataSnapshot.child("Name").getValue());
                    if(name.equals(user.getUsername()))
                        isExist = true;
                }
                if(!isExist){
                    databaseReference.push().setValue(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void countUser(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int counter = 0;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    counter += 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }
}
