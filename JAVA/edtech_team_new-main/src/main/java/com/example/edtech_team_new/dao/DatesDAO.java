package com.example.edtech_team_new.dao;

import com.example.edtech_team_new.db.DbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatesDAO {
    public void update(String datesUpdate){
        Connection conn = DbHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE dates SET temperature = ?, humidity = ?, x = ?, y = ?, z = ?, lat = ?, longg = ? WHERE id = ?");
            ps.setInt(1, datesUpdate.getTemprature());
            ps.setInt(2, datesUpdate.getHumidity());
            ps.setInt(3, datesUpdate.getX());
            ps.setInt(4, datesUpdate.getY());
            ps.setInt(5, datesUpdate.getZ());
            ps.setInt(6, datesUpdate.getLat());
            ps.setInt(7, datesUpdate.getLongg());
            ps.executeUpdate();

            ps.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
