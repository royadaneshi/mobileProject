package com.edufire.dic3;

import com.edufire.dic3.Models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserDataBase {
    private final DatabaseReference databaseReference;

    public UserDataBase() {
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
