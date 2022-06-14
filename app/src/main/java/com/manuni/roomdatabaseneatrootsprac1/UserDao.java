package com.manuni.roomdatabaseneatrootsprac1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    //here we used the query to match all the data in the table with the user given uid
    @Insert
    void insetData(User user);
    @Query("SELECT EXISTS(SELECT * FROM "+DatabaseName.TABLE_NAME+" WHERE uid = :uid)")
    Boolean isExists(int uid);

    //query for getting all the data from the table
    @Query("SELECT * FROM "+DatabaseName.TABLE_NAME+"")
    List<User> getAllData();

    //query for deleting all the data from the table based on user defined id
    @Query("DELETE FROM "+DatabaseName.TABLE_NAME+" WHERE uid = :uid")
    void deleteAt(int uid);

    //query for updating the field in the table columns based on user defined id
    @Query("UPDATE "+DatabaseName.TABLE_NAME+" SET First_Name = :first_name, Last_Name = :last_name WHERE uid = :uid")
    void updateData(int uid,String first_name,String last_name);
}
