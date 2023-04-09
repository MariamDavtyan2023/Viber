package com.demo.viber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextRegEmail;
    private EditText editTextRegPassword;
    private EditText editTextRegName;
    private EditText editTextRegLastName;
    private EditText editTextRegAge;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getTrimmedValue(editTextRegEmail);
                String password = getTrimmedValue(editTextRegPassword);
                String name = getTrimmedValue(editTextRegName);
                String lastName = getTrimmedValue(editTextRegLastName);
                int age = Integer.parseInt(getTrimmedValue(editTextRegAge));
                // sign up
            }
        });
    }

    private void initViews(){
        editTextRegEmail = findViewById(R.id.editTextRegEmail);
        editTextRegPassword = findViewById(R.id.editTextRegPassword);
        editTextRegName = findViewById(R.id.editTextRegName);
        editTextRegLastName = findViewById(R.id.editTextRegLastName);
        editTextRegAge = findViewById(R.id.editTextRegAge);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }

    private String getTrimmedValue(EditText editText){
        return editText.getText().toString().trim();
    }

    public static Intent newIntent(Context context){
        return new Intent(context, RegistrationActivity.class);
    }
}