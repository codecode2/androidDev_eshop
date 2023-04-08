package com.example.eshop;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;

public class WelcomePage extends AppCompatActivity  {

    public  WelcomePage(){}


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private sharedPreferenceConfig sharedPreferenceConfig;

    @SuppressLint("MissingInflatedId")




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_fragments);

        welcomeFragment welcomefragmentIntro = new welcomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, welcomefragmentIntro).commit();


        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());

        toolbar = findViewById(R.id.firsttoolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        menuUploadFragment fragmentUpload = new menuUploadFragment();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.myhome:
                        //   menuItem.setChecked(true);
                        displayMessage("Home");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, welcomefragmentIntro).commit();
                        drawerLayout.closeDrawers();




                        return true;
                    case R.id.upload:
                        //   menuItem.setChecked(true);
                        displayMessage("upload");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentUpload).commit();
                        drawerLayout.closeDrawers();

                          return true;
                    case R.id.find:
                        displayMessage("find something");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.about:
                        //  menuItem.setChecked(true);
                        displayMessage("Info ...");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.logout:
                        //  menuItem.setChecked(true);
                        displayMessage("Logout");
                        drawerLayout.closeDrawers();
                        logoutUser(null);
                        return true;
                }
                return false;
            }
        });



     }

    public void logoutUser(View view) {
        sharedPreferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, IntroPage.class));
        finish();
        }



    void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



}


