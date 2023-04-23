package com.entity;

import com.db.init.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private Integer total_spent;

    public Client(String name, Integer total_spent) {
        this.name = name;
        this.total_spent = total_spent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal_spent() {
        return total_spent;
    }

    public void setTotal_spent(Integer total_spent) {
        this.total_spent = total_spent;
    }
}
