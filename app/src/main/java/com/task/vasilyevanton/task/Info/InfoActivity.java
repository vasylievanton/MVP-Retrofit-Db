package com.task.vasilyevanton.task.Info;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.task.vasilyevanton.task.R;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        Intent intent = getIntent();

        String fName = intent.getStringExtra("fname");
        String lName = intent.getStringExtra("lname");

        TextView sex = (TextView) findViewById(R.id.sex);
        TextView deathDate = (TextView) findViewById(R.id.death);
        TextView deathCity = (TextView) findViewById(R.id.death_city);
        TextView bornCoord = (TextView) findViewById(R.id.born_coord);
        TextView deathCoord = (TextView) findViewById(R.id.death_coord);

        sex.setText(intent.getStringExtra("sex"));
        deathDate.setText(intent.getStringExtra("death_date"));
        deathCity.setText(intent.getStringExtra("death_city"));


    }
}
