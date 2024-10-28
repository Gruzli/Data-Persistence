package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.etBtnLogin);
        btnRegister = findViewById(R.id.etBtnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.etBtnRegister){
            String strUsername = etUsername.getText().toString();
            String strPassword = etPassword.getText().toString();

            User tempUserRegister = new User(strUsername,strPassword);
            UserDAO userDAO = new UserDAO();
            userDAO.addRowMsUser(this, tempUserRegister);

            Toast.makeText(this, "Register is clicked for"+strUsername, Toast.LENGTH_SHORT).show();
        }else if(v.getId()==R.id.etBtnLogin){
            //selalu select all
            UserDAO userDAO =new UserDAO();
            User firstUser = UserDAO.getFirstUser(this);

            Toast.makeText(this,
                    "Login is clicked, first user is: "
                    + firstUser.username+" "+firstUser.password,
                    Toast.LENGTH_SHORT).show();
        }
    }
}