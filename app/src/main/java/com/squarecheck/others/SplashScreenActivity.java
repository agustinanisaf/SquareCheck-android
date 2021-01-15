package com.squarecheck.others;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squarecheck.R;
import com.squarecheck.about.AboutUsActivity;
import com.squarecheck.login.view.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        findViewById(R.id.splash_screen).setOnClickListener(view -> redirectToLogin());
        findViewById(R.id.btn_about_us).setOnClickListener(view -> redirectToAboutUs());
    }

    private void redirectToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToAboutUs(){
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
        finish();
    }
}
