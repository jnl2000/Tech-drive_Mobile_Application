package com.example.techdrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVehicle<DBAddVehicle> extends AppCompatActivity{

    EditText vehiclenumber, vehicletype, vehiclemodel, chassisnumber, enginenumber, fueltype;
    Button btnadd, btndelete, btnView;
    DBVehicle DBVehicle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        vehiclenumber = findViewById(R.id.txtvehiclenumber);
        vehicletype = findViewById(R.id.txtvehicletype);
        vehiclemodel = findViewById(R.id.txtvehiclemodel);
        chassisnumber = findViewById(R.id.txtchassisnumber);
        enginenumber = findViewById(R.id.txtenginenumber);
        fueltype = findViewById(R.id.txtfueltype);
        btnadd = findViewById(R.id.btnadd);
        btndelete = findViewById(R.id.btndelete);
        btnView = findViewById(R.id.btnView);
        DBVehicle = new DBVehicle(this);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vehiclenumberTXT = vehiclenumber.getText().toString();
                String vehicletypeTXT = vehicletype.getText().toString();
                String vehiclemodelTXT = vehiclemodel.getText().toString();
                String chassisnumberTXT = chassisnumber.getText().toString();
                String enginenumberTXT = enginenumber.getText().toString();
                String fueltypeTXT = fueltype.getText().toString();
                Boolean checkvehicledata = DBVehicle.insertvehicledata(vehiclenumberTXT,vehicletypeTXT, vehiclemodelTXT,  chassisnumberTXT, enginenumberTXT, fueltypeTXT);
                if (checkvehicledata == true)
                    Toast.makeText(AddVehicle.this, "Successfully Added Your Vehicle", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddVehicle.this, "Vehicle Not Added", Toast.LENGTH_SHORT).show();
            }
        });


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vehiclenumberTXT = vehiclenumber.getText().toString();
                Boolean checkdeletedata = DBVehicle.deletevehicledata(vehiclenumberTXT);
                if (checkdeletedata == true)
                    Toast.makeText(AddVehicle.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddVehicle.this, "Vehicle Not Delete", Toast.LENGTH_SHORT).show();
                }
            });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DBVehicle.getdata();
                if (res.getCount()==0){
                    Toast.makeText(AddVehicle.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Vehicle Number : "+res.getString(0)+"\n");
                    buffer.append("Vehicle Type : "+res.getString(1)+"\n");
                    buffer.append("Vehicle Model : "+res.getString(2)+"\n");
                    buffer.append("Chassis Number : "+res.getString(3)+"\n");
                    buffer.append("Engine Number : "+res.getString(4)+"\n");
                    buffer.append("Fuel type : "+res.getString(5)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AddVehicle.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


        }
    }
