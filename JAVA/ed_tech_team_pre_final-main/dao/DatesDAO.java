package com.example.edtech_team_new.dao;

import com.example.edtech_team_new.db.DbHandler;
import com.example.edtech_team_new.models.Dates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DatesDAO {
    DbHandler dbHandler = new DbHandler();
    public void update(Dates dates) throws SQLException {
        Connection conn = dbHandler.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO dates(temperature, humidity, x, y, z, lat, longg, internal_temp, pressure, distance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, dates.getTemperature());
            ps.setString(2, dates.getHumidity());
            ps.setString(3, dates.getX());
            ps.setString(4, dates.getY());
            ps.setString(5, dates.getZ());
            ps.setString(6, dates.getLat());
            ps.setString(7, dates.getLongg());
            ps.setString(8, dates.getInternalTemp());
            ps.setString(9, dates.getPressure());
            ps.setString(10, dates.getDistance());
            ps.executeUpdate();

            ps.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
