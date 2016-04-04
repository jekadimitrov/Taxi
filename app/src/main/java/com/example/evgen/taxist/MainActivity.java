package com.example.evgen.taxist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
    }
     public void onClick(View v) {
         switch (v.getId()) {
             case R.id.bt1:
                 Intent intent = new Intent(this, sto.class);
                 startActivity(intent);
                 break;
             default:
                 break;
         }

            }

    }


