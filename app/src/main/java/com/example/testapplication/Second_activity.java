package com.example.testapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Second_activity extends AppCompatActivity {
    Button myBtnRegistration;
    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
        myBtnRegistration = (Button) findViewById(R.id.registration2_id);
        emailText = (EditText) findViewById(R.id.emailReg_id);
        passwordText = (EditText) findViewById(R.id.passwordReg_id);

        myBtnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = emailText.getText().toString();
                String strPassword = passwordText.getText().toString();


                AlertDialog.Builder builder = new AlertDialog.Builder(Second_activity.this);
                if (strEmail.length() <= 6 && strPassword.length() <= 4) {
                    builder.setTitle("Alert")
                            .setMessage("The length of the email or password is not appropriate, please change the length")
                            .setCancelable(false)
                            .setNegativeButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();


                } else if (!SingletonSession.Instance().addUsername(strEmail, strPassword)) {
                    builder.setTitle("Alert")
                            .setMessage("This user exists, please register an other one")
                            .setCancelable(false)
                            .setNegativeButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    Intent intent = new Intent(Second_activity.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });


    }

}
