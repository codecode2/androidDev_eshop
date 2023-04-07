package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private sharedPreferenceConfig sharedPreferenceConfig;

    EditText UserName, UserPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        UserName = findViewById(R.id.nameLogin);
        UserPassword = findViewById(R.id.loginPassword);
        if (sharedPreferenceConfig.readLoginStatus()){
            startActivity(new Intent(this, WelcomePage.class));
            finish();
        }
    }


    public void loginUser(View view) {
        String varusername = UserName.getText().toString();
        String varuserpassword = UserPassword.getText().toString();
        if (varusername.equals(getResources().getString(R.string.user_name))&& varuserpassword.equals((getResources().getString(R.string.user_password)))){
            startActivity(new Intent(this, WelcomePage.class));
            sharedPreferenceConfig.writeLoginStatus(true);
            finish();
        } else {
            Toast.makeText(this, "Login failed! Try again ...", Toast.LENGTH_LONG).show();
            UserName.setText("");
            UserPassword.setText("");
        }
    }
}



