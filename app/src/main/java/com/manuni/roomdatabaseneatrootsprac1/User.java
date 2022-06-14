package com.manuni.roomdatabaseneatrootsprac1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = DatabaseName.TABLE_NAME)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "First_Name")
    private String firstName;

    @ColumnInfo(name = "Last_Name")
    private String lastName;

    public User(int uid, String firstName, String lastName){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUid(){
        return uid;
    }
    public void setUid(int uid){
        this.uid = uid;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


}
