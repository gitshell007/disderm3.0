package com.disderm.dao;
import com.disderm.model.DisdermusersModel;
import com.disderm.model.UsuariosModel;
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

public class UsuariosDAO {
    private final static SSLog ssLog = new SSLog();
    public Long insertUsuariosusuarios (UsuariosModel _usuariosusuarios){
        Long id = 0L;
        String sql = " INSERT INTO usuarios ( nombre_usuario, password, " +
			 " nombre, apellido, nombre_mostrar, perfil, " +
			 " imagen_perfil, habilitado, telefono, localidad, " +
			 " provincia, cp, app_platform, app_device_model, " +
			 " app_device_token, fecha_alta_sistema, fecha_ultimo_acceso, mensaje) "+ 
			" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
      PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setString(1,_usuariosusuarios.get_nombre_usuario());
           ps.setString(2,_usuariosusuarios.get_password());
           ps.setString(3,_usuariosusuarios.get_nombre());
           ps.setString(4,_usuariosusuarios.get_apellido());
           ps.setString(5,_usuariosusuarios.get_nombre_mostrar());
           ps.setLong(6,_usuariosusuarios.get_perfil());
           ps.setString(7,_usuariosusuarios.get_imagen_perfil());
           ps.setLong(8,_usuariosusuarios.get_habilitado());
           ps.setString(9,_usuariosusuarios.get_telefono());
           ps.setString(10,_usuariosusuarios.get_localidad());
           ps.setString(11,_usuariosusuarios.get_provincia());
           ps.setString(12,_usuariosusuarios.get_cp());
           ps.setString(13,_usuariosusuarios.get_app_platform());
           ps.setString(14,_usuariosusuarios.get_app_device_model());
           ps.setString(15,_usuariosusuarios.get_app_device_token());
           ps.setDate(16,FFecha.convertir(_usuariosusuarios.get_fecha_alta_sistema()));
           ps.setDate(17,FFecha.convertir(_usuariosusuarios.get_fecha_ultimo_acceso()));
           ps.setString(18,_usuariosusuarios.get_mensaje());
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
    public Long updateUsuariosusuarios (UsuariosModel _usuariosusuarios){
        Long id = 0L;
        String sql = " UPDATE usuarios SET  nombre_usuario = ?, password = ?, " +
			 " nombre = ?, apellido = ?, nombre_mostrar = ?, perfil = ?, " +
			 " imagen_perfil = ?, habilitado = ?, telefono = ?, localidad = ?, " +
			 " provincia = ?, cp = ?, app_platform = ?, app_device_model = ?, " +
			 " app_device_token = ?, fecha_alta_sistema = ?, fecha_ultimo_acceso = ?, mensaje = ? WHERE id = ?";
   try
   {
       java.sql.Connection conn = null;
       conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
       Timestamp ts = new Timestamp(new Date().getTime());
       if(conn != null){
           PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"});
           ps.setString(1,_usuariosusuarios.get_nombre_usuario());
           ps.setString(2,_usuariosusuarios.get_password());
           ps.setString(3,_usuariosusuarios.get_nombre());
           ps.setString(4,_usuariosusuarios.get_apellido());
           ps.setString(5,_usuariosusuarios.get_nombre_mostrar());
           ps.setLong(6,_usuariosusuarios.get_perfil());
           ps.setString(7,_usuariosusuarios.get_imagen_perfil());
           ps.setLong(8,_usuariosusuarios.get_habilitado());
           ps.setString(9,_usuariosusuarios.get_telefono());
           ps.setString(10,_usuariosusuarios.get_localidad());
           ps.setString(11,_usuariosusuarios.get_provincia());
           ps.setString(12,_usuariosusuarios.get_cp());
           ps.setString(13,_usuariosusuarios.get_app_platform());
           ps.setString(14,_usuariosusuarios.get_app_device_model());
           ps.setString(15,_usuariosusuarios.get_app_device_token());
           ps.setDate(16,FFecha.convertir(_usuariosusuarios.get_fecha_alta_sistema()));
           ps.setDate(17,FFecha.convertir(_usuariosusuarios.get_fecha_ultimo_acceso()));
           ps.setString(18,_usuariosusuarios.get_mensaje());
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

    public UsuariosModel getUsuariosByNombre(String nombre_usuario) {
        UsuariosModel _user = new UsuariosModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, nombre_usuario);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _user.set_id(rs.getLong("id"));
                        _user.set_nombre_usuario(rs.getString("nombre_usuario"));
                        _user.set_password(rs.getString("password"));
                        _user.set_nombre(rs.getString("nombre"));
                        _user.set_apellido(rs.getString("apellido"));
                        _user.set_nombre_mostrar(rs.getString("nombre_mostrar"));
                        _user.set_perfil(Long.valueOf(rs.getInt("perfil")));
                        _user.set_imagen_perfil(rs.getString("imagen_perfil"));
                        _user.set_habilitado(Long.valueOf(rs.getInt("habilitado")));
                        _user.set_telefono(rs.getString("telefono"));
                        _user.set_localidad(rs.getString("localidad"));
                        _user.set_provincia(rs.getString("provincia"));
                        _user.set_cp(rs.getString("cp"));
                        _user.set_app_platform(rs.getString("app_platform"));
                        _user.set_app_device_model(rs.getString("app_device_model"));
                        _user.set_app_device_token(rs.getString("app_device_token"));
                        _user.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                        _user.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                        _user.set_mensaje(rs.getString("mensaje"));
                        Gson gson = new Gson();
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetDisdermusers() Exception: " + ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException logOrIgnore) {
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException logOrIgnore) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException logOrIgnore) {
            }
            return _user;
        }

    }

    public UsuariosModel getUsuarioModelById(Long _id) {
        UsuariosModel usuarios = new UsuariosModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        usuarios.set_id(rs.getLong("id"));
                        usuarios.set_nombre_usuario(rs.getString("nombre_usuario"));
                        usuarios.set_password(rs.getString("password"));
                        usuarios.set_nombre(rs.getString("nombre"));
                        usuarios.set_apellido(rs.getString("apellido"));
                        usuarios.set_nombre_mostrar(rs.getString("nombre_mostrar"));
                        usuarios.set_perfil(Long.valueOf(rs.getInt("perfil")));
                        usuarios.set_imagen_perfil(rs.getString("imagen_perfil"));
                        usuarios.set_habilitado(Long.valueOf(rs.getInt("habilitado")));
                        usuarios.set_telefono(rs.getString("telefono"));
                        usuarios.set_localidad(rs.getString("localidad"));
                        usuarios.set_provincia(rs.getString("provincia"));
                        usuarios.set_cp(rs.getString("cp"));
                        usuarios.set_app_platform(rs.getString("app_platform"));
                        usuarios.set_app_device_model(rs.getString("app_device_model"));
                        usuarios.set_app_device_token(rs.getString("app_device_token"));
                        usuarios.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                        usuarios.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                        usuarios.set_mensaje(rs.getString("mensaje"));
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetDisdermusers() Exception: " + ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException logOrIgnore) {
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException logOrIgnore) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException logOrIgnore) {
            }
            return usuarios;
        }

    }
}