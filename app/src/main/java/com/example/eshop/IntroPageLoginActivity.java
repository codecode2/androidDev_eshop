package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class IntroPageLoginActivity extends AppCompatActivity {

    private sharedPreferenceConfig sharedPreferenceConfig;

    EditText UserName, UserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intropagelogin);
        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        UserName = findViewById(R.id.nameLogin);
        UserPassword = findViewById(R.id.loginPassword);
        if (sharedPreferenceConfig.readLoginStatus()){
            startActivity(new Intent(this, WelcomePageActivity.class));
            finish();
        }
    }
    public void loginUser(View view) {
        String varusername = UserName.getText().toString();
        String varuserpassword = UserPassword.getText().toString();
        boolean  flag=false;
        Resources res = getResources();
        String[] stringUsernames= res.getStringArray(R.array.all_strings_username);
        Resources res2 = getResources();
        String[] stringPasswords = res2.getStringArray(R.array.all_strings_passwords);

for(int i=0; i<stringUsernames.length; i++) {

    if (varusername.equals(stringUsernames[i].toString()) && varuserpassword.equals(stringPasswords[i].toString())) {
        flag=true;
        startActivity(new Intent(this, WelcomePageActivity.class));
        sharedPreferenceConfig.writeLoginStatus(true);
        finish();
             }
        }
        if(flag==false)
        {
            Toast.makeText(this, "Login failed! Try again ...", Toast.LENGTH_LONG).show();
            UserName.setText("");
            UserPassword.setText("");
        }

    }
}



