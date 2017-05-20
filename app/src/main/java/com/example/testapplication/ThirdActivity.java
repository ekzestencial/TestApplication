package com.example.testapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.testapplication.R.id.text_id;

public class ThirdActivity extends AppCompatActivity {
    Button returnBtn;
    TextView emailText;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    final String NO_TEXT = "no_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        if (!existsSession().equals(NO_TEXT)) {
            loadText();
        }
        emailText = (TextView) findViewById(R.id.text_id);
        emailText.setText(SingletonSession.lastUser);
        returnBtn = (Button) findViewById(R.id.return_id);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                //saveText();
                returnBtn = (Button) findViewById(R.id.signUp_Id);
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void saveText() {
        sPref = getSharedPreferences("SaveSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SAVED_TEXT, SingletonSession.lastUser);
        editor.apply();
    }

    private void loadText() {
        sPref = getSharedPreferences("SaveSession", MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        emailText.setText(savedText);

    }

    private void clearText() {
        sPref = getSharedPreferences("SaveSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.clear().apply();
    }

    private String existsSession()

    {
        sPref = getSharedPreferences("SaveSession", MODE_PRIVATE);
        return sPref.getString(SAVED_TEXT, NO_TEXT);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }


}
