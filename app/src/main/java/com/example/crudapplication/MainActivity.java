package com.example.crudapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudapplication.Room.UserDao;
import com.example.crudapplication.Room.UserDatabase;
import com.example.crudapplication.Room.Users;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nameEd, emailEd;
    Button insertButton;
    RecyclerView myrecycler;

    private UserDatabase userDatabase;
    private UserDao userDao;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameEd = findViewById(R.id.name);
        emailEd = findViewById(R.id.email);
        insertButton = findViewById(R.id.btnInsert);
        myrecycler = findViewById(R.id.userRecycler);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        userAdapter =new UserAdapter(this);
        myrecycler.setAdapter(userAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEd.getText().toString();
                String email = emailEd.getText().toString();
                Users users = new Users(0, name, email);
                userAdapter.addUser(users);
                userDao.insert(users);

                nameEd.setText("");
                emailEd.setText("");

                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();


            }
        });
    }
    private void fetchData(){
        List<Users> usersList = userDao.getAllUsers();
        for (int i=0; i< usersList.size();i++){
            Users users = usersList.get(i);
            userAdapter.addUser(users);
        }
    }
}