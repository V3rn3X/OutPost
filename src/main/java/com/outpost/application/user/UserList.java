package com.outpost.application.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserList  {

    private List<User> user;

    public UserList(){
        this.user = new ArrayList<>();
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
