package com.example.testapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    Button returnBtn;
    TextView emailText;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        context=ThirdActivity.this;
        final SharedPreferencesTest shrPrf = new SharedPreferencesTest(context);
        emailText = (TextView) findViewById(R.id.text_id);
        emailText.setText(shrPrf.lastUser);
        returnBtn = (Button) findViewById(R.id.return_id);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnBtn = (Button) findViewById(R.id.signUp_Id);
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
