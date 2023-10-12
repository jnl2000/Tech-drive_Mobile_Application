package com.example.techdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText email, password;
    Button btnlogin, btnreg;
    DBLogin DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email =(EditText) findViewById(R.id.email1);
        password =(EditText) findViewById(R.id.password1);

        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnreg = (Button) findViewById(R.id.btnreg);

        DB= new DBLogin(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = email.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(LoginPage.this, "Please Enter All the Fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkemailpassword(user, pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginPage.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginPage.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }    }
            }
        });


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });
    }
}