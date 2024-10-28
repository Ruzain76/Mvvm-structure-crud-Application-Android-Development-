package com.example.crudapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crudapplication.Room.UserDao;
import com.example.crudapplication.Room.UserDatabase;
import com.example.crudapplication.Room.Users;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameEd,emailEd;
    private Button updateButton;
    private Users users;

    private UserDatabase userDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        userDatabase=UserDatabase.getInstance(this);
        userDao=userDatabase.getDao();


        nameEd = findViewById(R.id.name);
        emailEd = findViewById(R.id.email);
        updateButton = findViewById(R.id.update);

        Users users = (Users) getIntent().getSerializableExtra("model");

        nameEd.setText(users.getName());
        emailEd.setText(users.getEmail());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users userModel = new Users(users.getId(),nameEd.getText().toString(),emailEd.getText().toString());
                userDao.update(userModel);
                finish();

            }
        });
    }
}