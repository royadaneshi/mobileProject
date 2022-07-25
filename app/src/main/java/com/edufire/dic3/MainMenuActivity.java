package com.edufire.dic3;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.User;
import com.google.android.material.navigation.NavigationView;

public class MainMenuActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView txtSearch,txtGroups,txtPremium, txtPlayGame;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        String username = getIntent().getStringExtra("userName");
        TextView textView = findViewById(R.id.userNameTextView);
        textView.setText(username);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                Intent intent;
                switch (id){
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
                    case R.id.rq_groups:
                        fragment = new RequestGroupFragment(username);
                        loadFragment(fragment);
                        break;
                    case R.id.setting:
                        fragment = new SettingFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.support:
                        break;
                    case R.id.logout:
                        intent = new Intent(MainMenuActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

//        txtSearch = findViewById(R.id.txt_menu_search);
//        txtGroups = findViewById(R.id.txt_menu_groups);
//        txtPremium = findViewById(R.id.txt_menu_premium);
//        txtPlayGame = findViewById(R.id.playGame);

//
//        user = User.getAllUsers().get(username);
//
//        txtSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainMenuActivity.this, SearchActivity.class);
//                intent.putExtra("username",username);
//                startActivity(intent);
//            }
//        });
//
//        txtPremium.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainMenuActivity.this, PremiumActivity.class);
//                intent.putExtra("username", username);
//                startActivity(intent);
//            }
//        });
//
//        txtPlayGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainMenuActivity.this, GameActivity.class);
//                intent.putExtra("username", username);
//                startActivity(intent);
//            }
//        });
//
//        txtGroups.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainMenuActivity.this, GroupActivity.class);
//                intent.putExtra("username", username);
//                startActivity(intent);
//            }
//        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
}