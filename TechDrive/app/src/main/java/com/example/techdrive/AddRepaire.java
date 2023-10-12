package com.example.techdrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRepaire extends AppCompatActivity {

    EditText vehiclenumb , vehicleRtype , repairedate , nextrepairedate , whatisrepaire , itemofrepair , repaireprice;
    Button btnRadd, btnRdelete, btnRView;
    DBVehicle DBVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repaire);

        setContentView(R.layout.activity_add_repaire);
        vehiclenumb = findViewById(R.id.txtRvehiclenumber);
        vehicleRtype = findViewById(R.id.txtRvehicletype);
        repairedate = findViewById(R.id.editRTextDate);
        nextrepairedate = findViewById(R.id.txtnextrepair);
        whatisrepaire = findViewById(R.id.txtRwht);
        itemofrepair = findViewById(R.id.txtRitem);
        repaireprice = findViewById(R.id.txtRprice);

        btnRadd = findViewById(R.id.btnRadd);
        btnRdelete = findViewById(R.id.btnRdelete);
        btnRView = findViewById(R.id.btnRView);

        DBVehicle = new DBVehicle(this);

        btnRadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vnumbTXT = vehiclenumb.getText().toString();
                String vrtypeTXT = vehicleRtype.getText().toString();
                String RdateTXT = repairedate.getText().toString();
                String nxtRdateTXT = nextrepairedate.getText().toString();
                String whtrepaireTXT = whatisrepaire.getText().toString();
                String itmrepaireTXT = itemofrepair.getText().toString();
                String repairepriceTXT = repaireprice.getText().toString();

                Boolean repaireinsertdata = DBVehicle.insertrepairedata(vnumbTXT, vrtypeTXT, RdateTXT, nxtRdateTXT, whtrepaireTXT, itmrepaireTXT, repairepriceTXT);
                if(repaireinsertdata ==true)
                    Toast.makeText(AddRepaire.this, " Entry Inserted", Toast.LENGTH_SHORT);
                else
                    Toast.makeText(AddRepaire.this, " Entry Inserted", Toast.LENGTH_SHORT).show();


            }
        });

        btnRdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vehiclenumbTXT = vehiclenumb.getText().toString();
                Boolean repeiredeletedata = DBVehicle.deleterepairedata(vehiclenumbTXT);
                if (repeiredeletedata == true)
                    Toast.makeText(AddRepaire.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddRepaire.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnRView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DBVehicle.getrepairedata();
                if (res.getCount()==0){
                    Toast.makeText(AddRepaire.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Vehicle Number : "+res.getString(0)+"\n");
                    buffer.append("Vehicle Type : "+res.getString(1)+"\n");
                    buffer.append("Repaire Date : "+res.getString(2)+"\n");
                    buffer.append("Next Check Repaire Date : "+res.getString(3)+"\n");
                    buffer.append("What is Repaire : "+res.getString(4)+"\n");
                    buffer.append("Item of Repaire : "+res.getString(5)+"\n");
                    buffer.append("Repaire Price : "+res.getString(6)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AddRepaire.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}