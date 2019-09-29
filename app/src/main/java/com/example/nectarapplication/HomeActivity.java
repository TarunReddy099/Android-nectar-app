package com.example.nectarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {
 ImageButton buy,donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         buy.findViewById(R.id.buy_home);
        donate.findViewById(R.id.donate_home);
       buy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent buy =new Intent(HomeActivity.this,BuyActivity.class);
               startActivity(buy);
           }
       });

       donate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent donate =new Intent(HomeActivity.this,DonateActivity.class);
               startActivity(donate);
           }
       });

    }
}
