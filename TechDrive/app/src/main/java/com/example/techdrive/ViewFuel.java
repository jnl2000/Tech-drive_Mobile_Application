package com.example.techdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewFuel extends AppCompatActivity {

    private Button btnAddFuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fuel);

        btnAddFuel = (Button) findViewById(R.id.btnAddFuel);

        btnAddFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewFuel.this, AddFuelDetails.class);
                startActivity(intent);
            }
        });
    }
}