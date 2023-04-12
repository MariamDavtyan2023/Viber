package com.demo.viber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextRegEmail;
    private EditText editTextRegPassword;
    private EditText editTextRegName;
    private EditText editTextRegLastName;
    private EditText editTextRegAge;
    private Button buttonSignUp;
    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        observeViewModel();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getTrimmedValue(editTextRegEmail);
                String password = getTrimmedValue(editTextRegPassword);
                String name = getTrimmedValue(editTextRegName);
                String lastName = getTrimmedValue(editTextRegLastName);
                int age;
                if (!getTrimmedValue(editTextRegAge).isEmpty()) {
                    age = Integer.parseInt(getTrimmedValue(editTextRegAge));
                } else {
                    age = -1;
                }
                if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !lastName.isEmpty() && age > 0) {
                    viewModel.signUp(email, password, name, lastName, age);
                } else {
                    Toast.makeText(RegistrationActivity.this, getString(R.string.toast), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    Toast.makeText(RegistrationActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = UsersActivity.newIntent(RegistrationActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initViews() {
        editTextRegEmail = findViewById(R.id.editTextRegEmail);
        editTextRegPassword = findViewById(R.id.editTextRegPassword);
        editTextRegName = findViewById(R.id.editTextRegName);
        editTextRegLastName = findViewById(R.id.editTextRegLastName);
        editTextRegAge = findViewById(R.id.editTextRegAge);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }
}