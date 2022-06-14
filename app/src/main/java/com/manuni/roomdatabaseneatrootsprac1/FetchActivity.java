package com.manuni.roomdatabaseneatrootsprac1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.os.Bundle;

import com.manuni.roomdatabaseneatrootsprac1.databinding.ActivityFetchBinding;

import java.util.List;

public class FetchActivity extends AppCompatActivity {
    ActivityFetchBinding binding;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFetchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseHelper helper = Room.databaseBuilder(getApplicationContext(),DatabaseHelper.class,DatabaseName.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        UserDao dao = helper.userDao();
        List<User> myData = dao.getAllData();


        adapter = new MyAdapter(myData,FetchActivity.this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        binding.recyclerView.setAdapter(adapter);
    }
}