package com.manuni.roomdatabaseneatrootsprac1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.manuni.roomdatabaseneatrootsprac1.databinding.ItemRowBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.EmployeeViewHolder> {
    List<User> data;
    Context context;

    public MyAdapter(List<User> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        return new  EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User userData = data.get(position);
        holder.binding.id.setText(String.valueOf(userData.getUid()));
        holder.binding.firstName.setText(userData.getFirstName());
        holder.binding.lastName.setText(userData.getLastName());

        holder.binding.menuClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), holder.binding.menuClick);
                popupMenu.inflate(R.menu.item_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.update:
                                Intent intent = new Intent(context,UpdateActivity.class);
                                intent.putExtra("uid",userData.getUid());
                                intent.putExtra("first_name",userData.getFirstName());
                                intent.putExtra("last_name",userData.getLastName());
                                context.startActivity(intent);
                                break;
                            case R.id.delete:
                                DatabaseHelper databaseHelper = Room.databaseBuilder(view.getContext(),DatabaseHelper.class,DatabaseName.DATABASE_NAME)
                                        .fallbackToDestructiveMigration()
                                        .allowMainThreadQueries()
                                        .build();
                                UserDao userDao = databaseHelper.userDao();
                                userDao.deleteAt(userData.getUid());
                                data.remove(position);
                                notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        ItemRowBinding binding;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemRowBinding.bind(itemView);
        }
    }
}
