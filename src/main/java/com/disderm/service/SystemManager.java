package com.disderm.service;

import com.disderm.conn.DBConnection;
import com.disderm.utils.Crypto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * User: epajaron
 * Date: 25/02/14
 * Time: 7:14
 */
public class SystemManager {

    public static String AES_KEY = "5dWUj2sQo0mKrxhG";

    public String getServiceValueDec(String service, String field){
        String value ="";

        String sql = "select value from system where service = ? and field = ?";
        try
        {
            java.sql.Connection conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, service);
                ps.setString(2, field);
                ResultSet rs = ps.executeQuery();
                Crypto libcrip = new Crypto();
                if (rs != null ){
                    if(rs.next()) {

                        value = libcrip.decriptAES(AES_KEY,rs.getString("value"));
                    }
                    rs.close();
                }

                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public Map<String, Map<String, String>> getDataService(String service){
        Map<String, Map<String, String>> map = null;

        String sql = "select * from system where service = ? and field not like 'Password' ORDER BY field";
        try
        {
            java.sql.Connection conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, service);
                ResultSet rs = ps.executeQuery();
                Crypto c = new Crypto();
                if (rs != null ){
                    map = new HashMap<String, Map<String, String>>();
                    while(rs.next()) {
                        Map<String, String> aux = new HashMap<String, String>();
                        aux.put("field",rs.getString("field"));
                        aux.put("value",c.decriptAES(AES_KEY,rs.getString("value")));

                        map.put(rs.getString("id_system"),aux);
                    }
                    rs.close();
                }

                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public boolean saveValue(com.disderm.model.SystemModel service){
        boolean updated = false;
        String sql = "update system set value = ? where id_system = ? " ;
        try
        {
            java.sql.Connection conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, service.getValue());
                ps.setLong(2, service.getId_system());

                updated = ps.executeUpdate()>0;

                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

}
