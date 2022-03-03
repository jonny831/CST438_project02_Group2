package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText userNameText, passwordText;
    private Button registerButton, toLoginButton;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userNameText = findViewById(R.id.editTextUsername);
        passwordText = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.buttonCreateAccount);
        toLoginButton = findViewById(R.id.buttonLoginShortcut);

        userDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getDao();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        toLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        User user = new User(userNameText.getText().toString(), passwordText.getText().toString());

        if (userNameText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()) {
            Toast.makeText(CreateAccountActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else if (userNameText.getText().toString().equals("admin")) {
            Toast.makeText(CreateAccountActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
        } else {
            userDAO.insert(user);
            Toast.makeText(CreateAccountActivity.this, "Account successfully created", Toast.LENGTH_SHORT).show();
        }
    }
}