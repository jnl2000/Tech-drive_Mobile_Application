package com.example.techdrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFuelDetails extends AppCompatActivity {

    EditText vehiclenb , vehicleFtype , fuelFtype , dateoffuel , miloffueltime , litergo , avgdis;
    Button btnFadd, btnFdelete, btnFView;
    DBVehicle DBVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel_details);

        vehiclenb = findViewById(R.id.txtFvehiclenumber);
        vehicleFtype = findViewById(R.id.txtFvehicletype);
        fuelFtype = findViewById(R.id.txtFFtype);
        dateoffuel = findViewById(R.id.txtFdate);
        miloffueltime = findViewById(R.id.txtFillingtime);
        litergo = findViewById(R.id.txtltergo);
        avgdis = findViewById(R.id.txtavgdis);

        btnFadd = findViewById(R.id.btnFadd);
        btnFdelete = findViewById(R.id.btnFdelete);
        btnFView = findViewById(R.id.btnFView);

        DBVehicle = new DBVehicle(this);

        btnFadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vnbTXT = vehiclenb.getText().toString();
                String vFtypeTXT = vehicleFtype.getText().toString();
                String FueltypeTXT = fuelFtype.getText().toString();
                String dofTXT = dateoffuel.getText().toString();
                String moftTXT = miloffueltime.getText().toString();
                String ltrgoTXT = litergo.getText().toString();
                String avegDisTXT = avgdis.getText().toString();

                Boolean fuelinsertdata = DBVehicle.insertfueldata(vnbTXT, vFtypeTXT, FueltypeTXT, dofTXT, moftTXT, ltrgoTXT, avegDisTXT);
                if(fuelinsertdata ==true) {
                    Toast.makeText(AddFuelDetails.this, " Entry not Inserted", Toast.LENGTH_SHORT);
                }
                else {
                    Toast.makeText(AddFuelDetails.this, " Entry  Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vehiclenbTXT = vehiclenb.getText().toString();
                Boolean fueldeletedata = DBVehicle.deletefueldata(vehiclenbTXT);
                if (fueldeletedata == true)
                    Toast.makeText(AddFuelDetails.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddFuelDetails.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnFView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DBVehicle.getfueldata();
                if (res.getCount()==0){
                    Toast.makeText(AddFuelDetails.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Vehicle Number : "+res.getString(0)+"\n");
                    buffer.append("Vehicle Type : "+res.getString(1)+"\n");
                    buffer.append("Fuel Type : "+res.getString(2)+"\n");
                    buffer.append("Date Of Fuel : "+res.getString(3)+"\n");
                    buffer.append("Milage Of Filling Time : "+res.getString(4)+"\n");
                    buffer.append("How far can a liter go : "+res.getString(5)+"\n");
                    buffer.append("Average Of Distance Travel : "+res.getString(6)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AddFuelDetails.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}