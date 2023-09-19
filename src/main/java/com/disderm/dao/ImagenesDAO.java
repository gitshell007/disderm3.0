package com.disderm.dao;
import com.disderm.model.ImagenesModel;
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

public class ImagenesDAO {
    private final static SSLog ssLog = new SSLog();
    public Long insertImagenesimagenes (ImagenesModel _imagenesimagenes){
        Long id = 0L;
        String sql = " INSERT INTO imagenes ( diagnostico_id, imagen_nombre, " +
			 " imagen_datetime, imagen, localizacion) "+ 
			" VALUES ( ?, ?, ?, ?, ?)";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
      PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setLong(1,_imagenesimagenes.get_diagnostico_id());
           ps.setString(2,_imagenesimagenes.get_imagen_nombre());
           ps.setDate(3,FFecha.convertir(_imagenesimagenes.get_imagen_datetime()));
           ps.setString(4,_imagenesimagenes.get_imagen());
           ps.setString(5,_imagenesimagenes.get_localizacion());
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
    public Long updateImagenesimagenes (ImagenesModel _imagenesimagenes){
        Long id = 0L;
        String sql = " UPDATE imagenes SET  diagnostico_id = ?, imagen_nombre = ?, " +
			 " imagen_datetime = ?, imagen = ?, localizacion = ? WHERE id = ?";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
           PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setLong(1,_imagenesimagenes.get_diagnostico_id());
           ps.setString(2,_imagenesimagenes.get_imagen_nombre());
           ps.setDate(3,FFecha.convertir(_imagenesimagenes.get_imagen_datetime()));
           ps.setString(4,_imagenesimagenes.get_imagen());
           ps.setString(5,_imagenesimagenes.get_localizacion());
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