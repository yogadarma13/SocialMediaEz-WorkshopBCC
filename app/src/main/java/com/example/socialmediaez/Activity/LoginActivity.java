package com.example.socialmediaez.Activity;

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
import com.example.socialmediaez.model.Login;
import com.example.socialmediaez.responses.LoginResponse;
//import com.example.socialmediaez.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    String email, password;
    EditText etEmailLogin, etPasswordLogin;
    Button btnLogin, btnToRegister;
    MyApi myApi;
    ProgressDialog pd_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLogin = findViewById(R.id.et_email_login);
        etPasswordLogin = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnToRegister = findViewById(R.id.btn_to_signup);

        btnLogin.setOnClickListener(this);
        btnToRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                requestLogin();
                break;
            case R.id.btn_to_signup:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void requestLogin() {
        pd_loading = new ProgressDialog(LoginActivity.this);
        pd_loading.setMessage("Loading...");
        pd_loading.setCancelable(true);
        pd_loading.setCanceledOnTouchOutside(false);
        pd_loading.show();
        email = etEmailLogin.getText().toString();
        password = etPasswordLogin.getText().toString();

        Login login = new Login(email, password);

        myApi = ApiClient.getClient().create(MyApi.class);

        Call<LoginResponse> loginCall = myApi.login(login);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    pd_loading.dismiss();
                    Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    pd_loading.dismiss();
                    Toast.makeText(LoginActivity.this, "Email and password invalid", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                pd_loading.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
