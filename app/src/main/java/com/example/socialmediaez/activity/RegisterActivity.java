package com.example.socialmediaez.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialmediaez.R;
import com.example.socialmediaez.api.ApiClient;
import com.example.socialmediaez.api.MyApi;
import com.example.socialmediaez.model.User;
import com.example.socialmediaez.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    String name, email, password;
    EditText etNameRegister, etEmailRegister, etPasswordRegister;
    Button btnRegister, btnToLogin;
    MyApi myApi;
    ProgressDialog pd_loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNameRegister = findViewById(R.id.et_name_register);
        etEmailRegister = findViewById(R.id.et_email_register);
        etPasswordRegister = findViewById(R.id.et_password_register);
        btnRegister = findViewById(R.id.btn_register);
        btnToLogin = findViewById(R.id.btn_to_login);

        btnRegister.setOnClickListener(this);
        btnToLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                requestRegister();
                break;
            case R.id.btn_to_login:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    private void requestRegister() {
        pd_loading = new ProgressDialog(RegisterActivity.this);
        pd_loading.setMessage("Loading...");
        pd_loading.setCancelable(true);
        pd_loading.setCanceledOnTouchOutside(false);
        pd_loading.show();
        name = etNameRegister.getText().toString();
        email = etEmailRegister.getText().toString();
        password = etPasswordRegister.getText().toString();

        myApi = ApiClient.getClient().create(MyApi.class);

        User user = new User(name, email, password);


        Call<RegisterResponse> registerCall = myApi.register(user);

        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    pd_loading.dismiss();
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    pd_loading.dismiss();
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                pd_loading.dismiss();
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
