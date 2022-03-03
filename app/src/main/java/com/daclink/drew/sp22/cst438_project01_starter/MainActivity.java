package com.daclink.drew.sp22.cst438_project01_starter;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "com.daclink.drew.sp22.cst438_project01_starter.EXTRA_USER_ID";

    TextView userTextView;


    String userName = null;
    private Button logoutButton;
    private Button searchBooksButton;
    private Button userMenuBtn;
    private Button wishlistBtn;
    private Button bookLogBtn;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutButton = (Button) findViewById(R.id.logoutButton);
        //viewBooksButton = (Button) findViewById(R.id.viewBooksButton);
        userMenuBtn = (Button) findViewById(R.id.editUsersBtn);

        searchBooksButton = (Button) findViewById(R.id.searchBooksButton);
        userMenuBtn = (Button) findViewById(R.id.editUsersBtn);
        wishlistBtn = findViewById(R.id.wishlistButton);
        bookLogBtn = findViewById(R.id.bookLogButton);

        Intent i = getIntent();
        Boolean isAdmin = i.getBooleanExtra(LoginActivity.EXTRA_IS_ADMIN, false);
        userID = i.getIntExtra(LoginActivity.EXTRA_USER_ID, -10);



        if (isAdmin) {
            userMenuBtn.setVisibility(View.VISIBLE);
        } else {
            userMenuBtn.setVisibility(View.INVISIBLE);
        }



        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserSettingsActivity.class);
                startActivity(intent);
//                finish();
            }
        });



        searchBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewBooksActivity.class);
                startActivity(intent);
            }
        });

        wishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WishlistActivity.class);
                intent.putExtra(EXTRA_USER_ID, userID);
                startActivity(intent);
            }
        });

        bookLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookLogActivity.class);
                intent.putExtra(EXTRA_USER_ID, userID);
                startActivity(intent);
            }
        });

        userMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

    }
}
