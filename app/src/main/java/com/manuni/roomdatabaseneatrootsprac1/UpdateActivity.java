package com.manuni.roomdatabaseneatrootsprac1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.manuni.roomdatabaseneatrootsprac1.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    private int id;
    private String f_name;
    private String l_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getIntExtra("uid",0);
        f_name = getIntent().getStringExtra("first_name");
        l_name = getIntent().getStringExtra("last_name");

        binding.editfname.setText(f_name);
        binding.editlname.setText(l_name);

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper db = Room.databaseBuilder(UpdateActivity.this,DatabaseHelper.class,DatabaseName.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                UserDao dao = db.userDao();
                dao.updateData(id,binding.editfname.getText().toString(),binding.editlname.getText().toString());
                startActivity(new Intent(UpdateActivity.this,FetchActivity.class));
                finish();
            }
        });




    }
}