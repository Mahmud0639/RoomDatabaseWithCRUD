package com.manuni.roomdatabaseneatrootsprac1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.manuni.roomdatabaseneatrootsprac1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.idET.getText().toString();
                String firstName = binding.firstName.getText().toString();
                String lastName = binding.lastName.getText().toString();

                DatabaseHelper dbHelper = Room.databaseBuilder(getApplicationContext(),DatabaseHelper.class, DatabaseName.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
                    UserDao userDao =  dbHelper.userDao();
                    Boolean is_exists = userDao.isExists(Integer.parseInt(id));

                    if (is_exists){
                        binding.idET.setText("");
                        binding.firstName.setText("");
                        binding.lastName.setText("");
                        binding.isExists.setText("Already Exists!");
                    }else {
                        userDao.insetData(new User(Integer.parseInt(id),firstName,lastName));
                        binding.idET.setText("");
                        binding.firstName.setText("");
                        binding.lastName.setText("");
                        binding.isExists.setText("Inserted successfully!");
                    }

            }
        });

        binding.fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FetchActivity.class));
            }
        });
    }
}