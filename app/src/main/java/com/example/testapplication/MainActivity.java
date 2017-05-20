package com.example.testapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button myBtnRegister;
    Button myBtnSignUp;
    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBtnRegister = (Button) findViewById(R.id.register_id);
        myBtnSignUp = (Button) findViewById(R.id.signUp_Id);
        emailText = (EditText) findViewById(R.id.Email_id);
        passwordText = (EditText) findViewById(R.id.Password_id);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        myBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Second_activity.class);
                startActivity(intent);
            }
        });
        myBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = emailText.getText().toString();
                String strPassword = passwordText.getText().toString();
                if (SingletonSession.Instance().enterCheckPassword(strEmail, strPassword)) {
                    Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                    startActivity(intent);
                } else {
                    builder.setTitle("Alert")
                            .setMessage("Invalid user or password")
                            .setCancelable(false)
                            .setNegativeButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    System.out.println("False");
                    alert.show();

                }
            }
        });

    }


}
