package com.manuni.roomdatabaseneatrootsprac1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},exportSchema = false,version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
   public abstract UserDao userDao();

}
