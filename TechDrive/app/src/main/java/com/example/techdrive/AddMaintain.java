package com.example.techdrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMaintain extends AppCompatActivity {

    EditText vehiclenum , vehicleMtype , maintaindate , nextmaintaindate , whatismaintain , itemofmaintain , maintainedprice;
    Button btnMadd, btnMdelete, btnMView;
    DBVehicle DBVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_maintain);
        vehiclenum = findViewById(R.id.txtMvehiclenumber);
        vehicleMtype = findViewById(R.id.txtMvehicletype);
        maintaindate = findViewById(R.id.editMTextDate);
        nextmaintaindate = findViewById(R.id.txtnextmaintain);
        whatismaintain = findViewById(R.id.txtMwht);
        itemofmaintain = findViewById(R.id.txtMitem);
        maintainedprice = findViewById(R.id.txtMprice);

        btnMadd = findViewById(R.id.btnMadd);
        btnMdelete = findViewById(R.id.btnMdelete);
        btnMView = findViewById(R.id.btnMView);

        DBVehicle = new DBVehicle(this);



        btnMadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vnumTXT = vehiclenum.getText().toString();
                String vtypeTXT = vehicleMtype.getText().toString();
                String mdateTXT = maintaindate.getText().toString();
                String nxtmdateTXT = nextmaintaindate.getText().toString();
                String whtmaintainTXT = whatismaintain.getText().toString();
                String itmmaintainTXT = itemofmaintain.getText().toString();
                String mainpriceTXT = maintainedprice.getText().toString();

                Boolean maininsertdata = DBVehicle.insertMaintaindata(vnumTXT, vtypeTXT, mdateTXT, nxtmdateTXT, whtmaintainTXT, itmmaintainTXT, mainpriceTXT);
                if(maininsertdata ==true)
                    Toast.makeText(AddMaintain.this, " Entry Inserted", Toast.LENGTH_SHORT);
                else
                    Toast.makeText(AddMaintain.this, " Entry Inserted", Toast.LENGTH_SHORT).show();


            }
        });

        btnMdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vehiclenumTXT = vehiclenum.getText().toString();
                Boolean maindeletedata = DBVehicle.deletemaintaindata(vehiclenumTXT);
                if (maindeletedata == true)
                    Toast.makeText(AddMaintain.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddMaintain.this, "Vehicle Not Delete", Toast.LENGTH_SHORT).show();
            }
        });

        btnMView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DBVehicle.getmaintaindata();
                if (res.getCount()==0){
                    Toast.makeText(AddMaintain.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Vehicle Number : "+res.getString(0)+"\n");
                    buffer.append("Vehicle Type : "+res.getString(1)+"\n");
                    buffer.append("Maintaned Date : "+res.getString(2)+"\n");
                    buffer.append("Next Maintained Date : "+res.getString(3)+"\n");
                    buffer.append("What is Maintain : "+res.getString(4)+"\n");
                    buffer.append("Item of Maintain : "+res.getString(5)+"\n");
                    buffer.append("Maintaned Price : "+res.getString(6)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AddMaintain.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}