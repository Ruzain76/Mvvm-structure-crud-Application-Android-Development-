package com.example.crudapplication;

import com.example.crudapplication.Room.Users;

public interface AdapterListner {

    void Onupdate(Users users);
    void Ondelete(int id, int pos);
}
