package com.disderm.dao;
import com.disderm.model.MensajesincidenciaModel;
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

public class MensajesincidenciaDAO {
    private final static SSLog ssLog = new SSLog();
    public Long insertMensajesincidenciamensajes_incidencia (MensajesincidenciaModel _mensajes_incidenciamensajes_incidencia){
        Long id = 0L;
        String sql = " INSERT INTO mensajes_incidencia ( fecha, tipo_incidencia, " +
			 " mensaje, diagnostico_incidencia, facultativo, administrador) "+ 
			" VALUES ( ?, ?, ?, ?, ?, ?)";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
      PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setDate(1,FFecha.convertir(_mensajes_incidenciamensajes_incidencia.get_fecha()));
           ps.setString(2,_mensajes_incidenciamensajes_incidencia.get_tipo_incidencia());
           ps.setString(3,_mensajes_incidenciamensajes_incidencia.get_mensaje());
           ps.setLong(4,_mensajes_incidenciamensajes_incidencia.get_diagnostico_incidencia());
           ps.setLong(5,_mensajes_incidenciamensajes_incidencia.get_facultativo());
           ps.setLong(6,_mensajes_incidenciamensajes_incidencia.get_administrador());
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
    public Long updateMensajesincidenciamensajes_incidencia (MensajesincidenciaModel _mensajes_incidenciamensajes_incidencia){
        Long id = 0L;
        String sql = " UPDATE mensajes_incidencia SET  fecha = ?, tipo_incidencia = ?, " +
			 " mensaje = ?, diagnostico_incidencia = ?, facultativo = ?, administrador = ? WHERE id = ?";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
           PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setDate(1,FFecha.convertir(_mensajes_incidenciamensajes_incidencia.get_fecha()));
           ps.setString(2,_mensajes_incidenciamensajes_incidencia.get_tipo_incidencia());
           ps.setString(3,_mensajes_incidenciamensajes_incidencia.get_mensaje());
           ps.setLong(4,_mensajes_incidenciamensajes_incidencia.get_diagnostico_incidencia());
           ps.setLong(5,_mensajes_incidenciamensajes_incidencia.get_facultativo());
           ps.setLong(6,_mensajes_incidenciamensajes_incidencia.get_administrador());
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