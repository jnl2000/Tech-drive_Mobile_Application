package com.example.techdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewRepaire extends AppCompatActivity {

    private Button btnAddRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_repaire);


        btnAddRepair = (Button) findViewById(R.id.btnAddRepair);


        btnAddRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewRepaire.this, AddRepaire.class);
                startActivity(intent);
            }
        });
    }
}