package com.disderm.dao;
import com.disderm.model.InformeModel;
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

public class InformeDAO {
    private final static SSLog ssLog = new SSLog();
    public Long insertInformeinforme (InformeModel _informeinforme){
        Long id = 0L;
        String sql = " INSERT INTO informe ( situacion_lesion, informe, " +
			 " tipo_incidencia) "+ 
			" VALUES ( ?, ?, ?)";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
      PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setString(1,_informeinforme.get_situacion_lesion());
           ps.setString(2,_informeinforme.get_informe());
           ps.setString(3,_informeinforme.get_tipo_incidencia());
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
    public Long updateInformeinforme (InformeModel _informeinforme){
        Long id = 0L;
        String sql = " UPDATE informe SET  situacion_lesion = ?, informe = ?, " +
			 " tipo_incidencia = ? WHERE id = ?";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
           PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setString(1,_informeinforme.get_situacion_lesion());
           ps.setString(2,_informeinforme.get_informe());
           ps.setString(3,_informeinforme.get_tipo_incidencia());
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