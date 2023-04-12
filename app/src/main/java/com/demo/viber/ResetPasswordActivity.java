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

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";

    private EditText editTextForgotEmail;
    private Button buttonResetPassword;
    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initViews();
        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        observeViewModel();
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        if (!email.isEmpty()) {
            editTextForgotEmail.setText(email);
        }


        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextForgotEmail.getText().toString().trim();
                if (!email.isEmpty()) {
                    viewModel.resetPassword(email);
                }else {
                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.toast), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void observeViewModel(){
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    Toast.makeText(ResetPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success){
                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.reset_link_sent), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        editTextForgotEmail = findViewById(R.id.editTextForgotEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
    }

    public static Intent newIntent(Context context, String email) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}