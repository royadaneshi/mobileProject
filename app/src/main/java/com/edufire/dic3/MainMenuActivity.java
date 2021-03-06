package com.edufire.dic3;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.User;
import com.google.android.material.navigation.NavigationView;

public class MainMenuActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    User user;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        String username = getIntent().getStringExtra("userName");
        user = User.getAllUsers().get(username);
        TextView textView = findViewById(R.id.userNameTextView);
        textView.setText(username);

        Fragment frg = new FirstPageFragment(username);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        loadFragment(frg);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment fragment;
            Intent intent;
            switch (id){
                case R.id.home_menu:
                    fragment = new FirstPageFragment(username);
                    loadFragment(fragment);
                    break;
                case R.id.search:
                    intent = new Intent(MainMenuActivity.this, SearchActivity.class);
                    intent.putExtra("userName",username);
                    startActivity(intent);
                    break;
                case R.id.play_game:
                    intent = new Intent(MainMenuActivity.this, GameActivity.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                    break;
                case R.id.user_groups:
                    fragment = new GroupsFragment(username);
                    loadFragment(fragment);
                    break;
                case R.id.add_groups:
                    fragment = new GroupAddFragment(username);
                    loadFragment(fragment);
                    break;
                case R.id.request_groups:
                    fragment = new RequestGroupFragment(username);
                    loadFragment(fragment);
                    break;
                case R.id.word_search_in_database:
                    fragment = new WordsInDatabaseFragment(username);
                    loadFragment(fragment);
                    break;
                case R.id.setting:
                    fragment = new SettingFragment(username);
                    loadFragment(fragment);
                    break;
                case R.id.support:
                    if(!user.isPremium()) {
                        if (user.getScore() > 30) {
                            user.setUserPremium(true);
                            MainActivity.db.updateUserScore(username, user.getScore(), true);
                            Toast.makeText(MainMenuActivity.this, "now you have premium account", Toast.LENGTH_SHORT).show();
                            fragment = new FirstPageFragment(username);
                            loadFragment(fragment);
                        } else {
                            Toast.makeText(MainMenuActivity.this, "You need " + (30 - user.getScore()) + " score", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainMenuActivity.this, "your account already premium", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.logout:
                    intent = new Intent(MainMenuActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                default:
                    return true;
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
}