package com.disderm.dao;
import com.disderm.model.VisitaModel;
import com.disderm.model.DropdownModel;
import com.disderm.conn.DBConnection;
import com.disderm.utils.SSLog;
import com.disderm.utils.Varios;
import com.disderm.utils.FFecha;
import com.disderm.utils.FFecha.*;
import static com.disderm.utils.FFecha.sdf_ddMMyyyyBarHHmmss;
import static com.disderm.utils.FFecha.sdf_ddMMyyyy;
import com.google.gson.Gson;
import java.util.*;
import javax.ws.rs.*;
import java.util.Date;
import java.sql.*;
import java.util.Date;
import java.util.Map;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.*;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Method;

public class VisitaDAO {
    private final static SSLog ssLog = new SSLog();
    public Long insertVisitavisita (VisitaModel _visitavisita){
        Long id = 0L;
        String sql = " INSERT INTO visita ( fecha_visita, urgencia, " +
			 " sintomatologia) "+ 
			" VALUES ( ?, ?, ?)";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
      PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setDate(1,FFecha.convertir(_visitavisita.get_fecha_visita()));
           ps.setString(2,_visitavisita.get_urgencia());
           ps.setString(3,_visitavisita.get_sintomatologia());
           ps.execute();
           ResultSet rs = ps.getGeneratedKeys();
           if (rs.next()) { 
               id = rs.getLong(1);
           }
           ps.close();
           conn.close();
       }

   } catch (Exception e) {
        e.printStackTrace();
   }
   return id;
}
    public Long updateVisitavisita (VisitaModel _visitavisita){
        Long id = 0L;
        String sql = " UPDATE visita SET  fecha_visita = ?, urgencia = ?, " +
			 " sintomatologia = ? WHERE id = ?";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
           PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setDate(1,FFecha.convertir(_visitavisita.get_fecha_visita()));
           ps.setString(2,_visitavisita.get_urgencia());
           ps.setString(3,_visitavisita.get_sintomatologia());
           ps.executeUpdate();
           ResultSet rs = ps.getGeneratedKeys();
           if (rs.next()) { 
               id = rs.getLong(1);
           }
           ps.close();
           conn.close();
       }

   } catch (Exception e) {
        e.printStackTrace();
   }
   return id;
}
}