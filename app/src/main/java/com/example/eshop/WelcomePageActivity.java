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
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;


import com.google.android.material.navigation.NavigationView;

public class WelcomePageActivity extends AppCompatActivity  {

    public static FragmentManager fragmentManager;
    public static eshopDatabase myAppDatabase;

    public WelcomePageActivity(){}


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private sharedPreferenceConfig sharedPreferenceConfig;


    @SuppressLint("MissingInflatedId")




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_fragments);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),eshopDatabase.class,"reservesBD").allowMainThreadQueries().build();
        welcomeFragment welcomefragmentIntro = new welcomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, welcomefragmentIntro).commit();
        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        toolbar = findViewById(R.id.firsttoolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        MenuUploadFragment fragmentUpload = new MenuUploadFragment();

        SearchingFragment search = new SearchingFragment();
        SupportFragment support = new SupportFragment();
        ProductsFragment product = new ProductsFragment();
        SuppliersFragment supplier =new SuppliersFragment();


        CategoryFragment categories =new CategoryFragment();
        SuppliesFragment supplies =new SuppliesFragment();




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.myhome:
                        //   menuItem.setChecked(true);

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, welcomefragmentIntro).commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.products:
                        //  menuItem.setChecked(true);

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, product).commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.suppliers:
                        //  menuItem.setChecked(true);

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, supplier).commit();
                        drawerLayout.closeDrawers();
                        return true;


                    case R.id.find:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, search).commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.aboutFragment:
                        //  menuItem.setChecked(true);

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, support).commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.categories:
                        //  menuItem.setChecked(true);

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, categories).commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.supplies:
                        //  menuItem.setChecked(true);

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, supplies).commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.logout:
                        //  menuItem.setChecked(true);

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
        startActivity(new Intent(this, IntroPageLoginActivity.class));
        finish();
        }



    void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



}


