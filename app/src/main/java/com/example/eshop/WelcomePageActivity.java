package com.example.eshop;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;


import com.google.firebase.messaging.FirebaseMessaging;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class WelcomePageActivity extends AppCompatActivity {


    private static final String CHANNEL_ID = "My Channel";
    private static final int notification_id = 100;
    public static FragmentManager fragmentManager;
    public static eshopDatabase myAppDatabase;
    public static eshopDatabase eshopDb;

    public static FirebaseFirestore db_firestore;

    public WelcomePageActivity() {
    }

    public String token;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    public static eshopDatabase database;

    private sharedPreferenceConfig sharedPreferenceConfig;


    @SuppressLint({"MissingInflatedId", "MissingPermission"})


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_fragments);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), eshopDatabase.class, "reservesBD").allowMainThreadQueries().build();
        welcomeFragment welcomefragmentIntro = new welcomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, welcomefragmentIntro).commit();
        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        toolbar = findViewById(R.id.firsttoolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        db_firestore = FirebaseFirestore.getInstance();


        SearchingFragment search = new SearchingFragment();
        SupportFragment support = new SupportFragment();
        ProductsFragment product = new ProductsFragment();
        SuppliersFragment supplier = new SuppliersFragment();


        CategoryFragment categories = new CategoryFragment();
        SuppliesFragment supplies = new SuppliesFragment();



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

    public static eshopDatabase getDatabase() {
        return database;
    }


    void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    public void createNotifications(String messageWindow,String insideMessage)
    {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.app_icon,null);
        BitmapDrawable bitmapDrawable =(BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();


        NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.baseline_doorbell_24)
                .setLargeIcon(largeIcon)
                .setContentText(messageWindow)
                .setSubText(insideMessage)
                .setChannelId(CHANNEL_ID)
                .build();
        nm.createNotificationChannel((new NotificationChannel(CHANNEL_ID,"new channel",NotificationManager.IMPORTANCE_HIGH)));
        nm.notify(notification_id,notification);




    }




}

