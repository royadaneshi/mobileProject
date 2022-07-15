package com.edufire.dic3;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.edufire.dic3.Models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserDataBase {
    private final DatabaseReference databaseReference;

    public UserDataBase(Context context) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://dic3-fd99b-default-rtdb.firebaseio.com");
        databaseReference = db.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user){
        return databaseReference.push().setValue(user);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> update(String key){
        return databaseReference.child(key).removeValue();
    }
}
