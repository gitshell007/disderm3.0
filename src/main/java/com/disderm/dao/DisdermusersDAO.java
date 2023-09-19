package com.disderm.dao;

import com.disderm.model.DisdermusersModel;
import com.disderm.model.DropdownModel;
import com.disderm.conn.DBConnection;
import com.disderm.utils.SSLog;
import com.disderm.utils.FFecha;

import java.util.Date;
import java.sql.*;
import java.util.Map;

import com.google.gson.Gson;

import java.util.ArrayList;

import com.google.gson.*;

import java.util.List;
import java.lang.reflect.Method;

public class DisdermusersDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertDisdermusers(DisdermusersModel _disderm_users) {
        Long id = 0L;
        String sql = "INSERT INTO disderm_users(username, password, first_name, last_name, visible_name, user_type_id, profile_image, enabled, phone_mobile, localidad, provincia, cp, app_platform, app_device_model, app_device_token, fecha_alta_sistema, fecha_ultimo_acceso, message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, _disderm_users.get_username());
                ps.setString(2, _disderm_users.get_password());
                ps.setString(3, _disderm_users.get_first_name());
                ps.setString(4, _disderm_users.get_last_name());
                ps.setString(5, _disderm_users.get_visible_name());
                ps.setLong(6, _disderm_users.get_user_type_id());
                ps.setString(7, _disderm_users.get_profile_image());
                ps.setLong(8, _disderm_users.get_enabled());
                ps.setString(9, _disderm_users.get_phone_mobile());
                ps.setString(10, _disderm_users.get_localidad());
                ps.setString(11, _disderm_users.get_provincia());
                ps.setString(12, _disderm_users.get_cp());
                ps.setString(13, _disderm_users.get_app_platform());
                ps.setString(14, _disderm_users.get_app_device_model());
                ps.setString(15, _disderm_users.get_app_device_token());
                ps.setDate(16, FFecha.convertir(_disderm_users.get_fecha_alta_sistema()));
                ps.setDate(17, FFecha.convertir(_disderm_users.get_fecha_ultimo_acceso()));
                ps.setString(18, _disderm_users.get_message());
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

    public DisdermusersModel getDisdermusersByUsername(String username) {
        DisdermusersModel _user = new DisdermusersModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users WHERE username = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _user.set_id(rs.getLong("disderm_users_id"));
                        _user.set_username(rs.getString("username"));
                        _user.set_password(rs.getString("password"));
                        _user.set_first_name(rs.getString("first_name"));
                        _user.set_last_name(rs.getString("last_name"));
                        _user.set_visible_name(rs.getString("visible_name"));
                        _user.set_user_type_id(rs.getInt("user_type_id"));
                        _user.set_profile_image(rs.getString("profile_image"));
                        _user.set_enabled(rs.getInt("enabled"));
                        _user.set_phone_mobile(rs.getString("phone_mobile"));
                        _user.set_localidad(rs.getString("localidad"));
                        _user.set_provincia(rs.getString("provincia"));
                        _user.set_cp(rs.getString("cp"));
                        _user.set_app_platform(rs.getString("app_platform"));
                        _user.set_app_device_model(rs.getString("app_device_model"));
                        _user.set_app_device_token(rs.getString("app_device_token"));
                        _user.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                        _user.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                        _user.set_message(rs.getString("message"));
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

    public Long getIDDisdermusersFromOneStringField(String _field, String _value) {
        Long toReturn = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT id FROM disderm_users WHERE " + _field + " = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _value);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        toReturn = rs.getLong("id");
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java FromOneStringFieldDisdermusers() Exception: " + ex.getMessage());
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
        }
        return toReturn;

    }

    public String getAllDisdermusers() {
        Long id = 0L;
        String reply = "";
        ArrayList<DisdermusersModel> arrayDataModel = new ArrayList<DisdermusersModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DisdermusersModel _disderm_users = new DisdermusersModel();
                    _disderm_users.set_id(rs.getLong("id"));
                    _disderm_users.set_username(rs.getString("username"));
                    _disderm_users.set_password(rs.getString("password"));
                    _disderm_users.set_first_name(rs.getString("first_name"));
                    _disderm_users.set_last_name(rs.getString("last_name"));
                    _disderm_users.set_visible_name(rs.getString("visible_name"));
                    _disderm_users.set_user_type_id(rs.getInt("user_type_id"));
                    _disderm_users.set_profile_image(rs.getString("profile_image"));
                    _disderm_users.set_enabled(rs.getInt("enabled"));
                    _disderm_users.set_phone_mobile(rs.getString("phone_mobile"));
                    _disderm_users.set_localidad(rs.getString("localidad"));
                    _disderm_users.set_provincia(rs.getString("provincia"));
                    _disderm_users.set_cp(rs.getString("cp"));
                    _disderm_users.set_app_platform(rs.getString("app_platform"));
                    _disderm_users.set_app_device_model(rs.getString("app_device_model"));
                    _disderm_users.set_app_device_token(rs.getString("app_device_token"));
                    _disderm_users.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                    _disderm_users.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                    _disderm_users.set_message(rs.getString("message"));
                    arrayDataModel.add(_disderm_users);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetAll(Disdermusers() Exception: " + ex.getMessage());
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
            return reply;
        }

    }

    public boolean updateDisdermusersString(Map _map, Long _id) {
        boolean success = false;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE disderm_users SET ";
        for (Object o : _map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            sql += pair.getKey() + " = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE id = ?";
        System.out.println("updateDisdermusersString " + sql);
        try {

            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            int i = 1;
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                for (Object o : _map.entrySet()) {
                    Map.Entry pair = (Map.Entry) o;
                    ps.setString(i, pair.getValue().toString());
                    i++;
                }
                ps.setLong(i, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java UpdateStringDisdermusers() Exception: " + ex.getMessage());
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException logOrIgnore) {
                System.out.println(logOrIgnore);
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException logOrIgnore) {
                System.out.println(logOrIgnore);
            }
        }
        return success;
    }

    public String getAllDisdermusersByUsername(String _username) {
        Long id = 0L;
        String reply = "";
        ArrayList<DisdermusersModel> arrayDataModel = new ArrayList<DisdermusersModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users as t1, pacientes as t2 WHERE t1.pacientes_id = t2.pacientes_id AND t2.app_username = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DisdermusersModel _disderm_users = new DisdermusersModel();
                    _disderm_users.set_id(rs.getLong("id"));
                    _disderm_users.set_username(rs.getString("username"));
                    _disderm_users.set_password(rs.getString("password"));
                    _disderm_users.set_first_name(rs.getString("first_name"));
                    _disderm_users.set_last_name(rs.getString("last_name"));
                    _disderm_users.set_visible_name(rs.getString("visible_name"));
                    _disderm_users.set_user_type_id(rs.getInt("user_type_id"));
                    _disderm_users.set_profile_image(rs.getString("profile_image"));
                    _disderm_users.set_enabled(rs.getInt("enabled"));
                    _disderm_users.set_phone_mobile(rs.getString("phone_mobile"));
                    _disderm_users.set_localidad(rs.getString("localidad"));
                    _disderm_users.set_provincia(rs.getString("provincia"));
                    _disderm_users.set_cp(rs.getString("cp"));
                    _disderm_users.set_app_platform(rs.getString("app_platform"));
                    _disderm_users.set_app_device_model(rs.getString("app_device_model"));
                    _disderm_users.set_app_device_token(rs.getString("app_device_token"));
                    _disderm_users.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                    _disderm_users.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                    _disderm_users.set_message(rs.getString("message"));
                    _disderm_users.set_id(rs.getLong("id"));
                    arrayDataModel.add(_disderm_users);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetAllDisdermusers() Exception: " + ex.getMessage());
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
        }
        return reply;

    }

    public String getAllSelectDisdermusers() {
        Long id = 0L;
        String reply = "";
        ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DropdownModel _disderm_users = new DropdownModel();
                    _disderm_users.set_des(rs.getString("disderm_users_des"));
                    _disderm_users.set_code(rs.getString("disderm_users_code"));
                    _disderm_users.set_dropdown_id(rs.getLong("id"));
                    arrayDataModel.add(_disderm_users);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetAllDropDown(Disdermusers() Exception: " + ex.getMessage());
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

        }
        return reply;

    }

    public long getNumeroFilasDisdermusers() {
        long numero_filas_disderm_users = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(id) as total from disderm_users";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_disderm_users = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java getNumeroFilas(Disdermusers() Exception: " + ex.getMessage());
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
        }
        return numero_filas_disderm_users;

    }

    public long getNumeroFilasFiltroDisdermusers(String _sql) {
        long numero_filas_disderm_users = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_disderm_users = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java getNumeroFilasFiltroDisdermusers() Exception: " + ex.getMessage());
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
        }
        return numero_filas_disderm_users;

    }

    public String getAllDataTableDisdermusers(int start, int length, int draw, String filter_sql) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<DisdermusersModel> arrayDataModel = new ArrayList<DisdermusersModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_disderm_users = getNumeroFilasDisdermusers();
        long numero_filas_filtro_disderm_users = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_disderm_users = getNumeroFilasFiltroDisdermusers("SELECT count(id) as total FROM disderm_users " + filter_sql);
        } else {
            numero_filas_filtro_disderm_users = numero_filas_total_disderm_users;
        }
        String sql = "SELECT * FROM disderm_users " + filter_sql + " limit ?,?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, start);
                ps.setLong(2, length);
                rs = ps.executeQuery();
                while (rs.next()) {
                    row = new JsonArray();
                    row.add(rs.getLong("id"));
                    row.add(rs.getString("username"));
                    row.add(rs.getString("password"));
                    row.add(rs.getString("first_name"));
                    row.add(rs.getString("last_name"));
                    row.add(rs.getString("visible_name"));
                    row.add(rs.getLong("user_type_id"));
                    row.add(rs.getString("profile_image"));
                    row.add(rs.getLong("enabled"));
                    row.add(rs.getString("phone_mobile"));
                    row.add(rs.getString("localidad"));
                    row.add(rs.getString("provincia"));
                    row.add(rs.getString("cp"));
                    row.add(rs.getString("app_platform"));
                    row.add(rs.getString("app_device_model"));
                    row.add(rs.getString("app_device_token"));
                    row.add(FFecha.getString(rs.getTimestamp("fecha_alta_sistema"), FFecha.sdf_ddMMyyyyBarHHmm));
                    row.add(FFecha.getString(rs.getTimestamp("fecha_ultimo_acceso"), FFecha.sdf_ddMMyyyyBarHHmm));
                    row.add(rs.getString("message"));

                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_disderm_users);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_disderm_users);
            reply = jMembers.toString();
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetAllDataTableDisdermusers() Exception: " + ex.getMessage());
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
        }
        return reply;

    }

    public String getAllDisdermusersByPacienteID(Long user_id) {
        Long id = 0L;
        String reply = "";
        ArrayList<DisdermusersModel> arrayDataModel = new ArrayList<DisdermusersModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users where pacientes_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, user_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DisdermusersModel _disderm_users = new DisdermusersModel();
                    _disderm_users.set_id(rs.getLong("id"));
                    _disderm_users.set_username(rs.getString("username"));
                    _disderm_users.set_password(rs.getString("password"));
                    _disderm_users.set_first_name(rs.getString("first_name"));
                    _disderm_users.set_last_name(rs.getString("last_name"));
                    _disderm_users.set_visible_name(rs.getString("visible_name"));
                    _disderm_users.set_user_type_id(rs.getInt("user_type_id"));
                    _disderm_users.set_profile_image(rs.getString("profile_image"));
                    _disderm_users.set_enabled(rs.getInt("enabled"));
                    _disderm_users.set_phone_mobile(rs.getString("phone_mobile"));
                    _disderm_users.set_localidad(rs.getString("localidad"));
                    _disderm_users.set_provincia(rs.getString("provincia"));
                    _disderm_users.set_cp(rs.getString("cp"));
                    _disderm_users.set_app_platform(rs.getString("app_platform"));
                    _disderm_users.set_app_device_model(rs.getString("app_device_model"));
                    _disderm_users.set_app_device_token(rs.getString("app_device_token"));
                    _disderm_users.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                    _disderm_users.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                    _disderm_users.set_message(rs.getString("message"));
                    arrayDataModel.add(_disderm_users);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetAll(Disdermusers() Exception: " + ex.getMessage());
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
            return reply;
        }

    }

    public String getDisdermusersByID(Long _id) {
        String reply = "";
        DisdermusersModel _disderm_users = new DisdermusersModel();
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
                        _disderm_users.set_id(rs.getLong("disderm_user_id"));
                        _disderm_users.set_username(rs.getString("username"));
                        _disderm_users.set_password(rs.getString("password"));
                        _disderm_users.set_first_name(rs.getString("first_name"));
                        _disderm_users.set_last_name(rs.getString("last_name"));
                        _disderm_users.set_visible_name(rs.getString("visible_name"));
                        _disderm_users.set_user_type_id(rs.getInt("user_type_id"));
                        _disderm_users.set_profile_image(rs.getString("profile_image"));
                        _disderm_users.set_enabled(rs.getInt("enabled"));
                        _disderm_users.set_phone_mobile(rs.getString("phone_mobile"));
                        _disderm_users.set_localidad(rs.getString("localidad"));
                        _disderm_users.set_provincia(rs.getString("provincia"));
                        _disderm_users.set_cp(rs.getString("cp"));
                        _disderm_users.set_app_platform(rs.getString("app_platform"));
                        _disderm_users.set_app_device_model(rs.getString("app_device_model"));
                        _disderm_users.set_app_device_token(rs.getString("app_device_token"));
                        _disderm_users.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                        _disderm_users.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                        _disderm_users.set_message(rs.getString("message"));
                        Gson gson = new Gson();
                        reply = gson.toJson(_disderm_users);
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetDisdermfarmausers() Exception: " + ex.getMessage());
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
            return reply;
        }

    }


    public DisdermusersModel getDisdermusersModelByID(Long _id) {
        DisdermusersModel _disderm_users = new DisdermusersModel();
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
                        _disderm_users.set_id(rs.getLong("id"));
                        _disderm_users.set_username(rs.getString("username"));
                        _disderm_users.set_password(rs.getString("password"));
                        _disderm_users.set_first_name(rs.getString("first_name"));
                        _disderm_users.set_last_name(rs.getString("last_name"));
                        _disderm_users.set_visible_name(rs.getString("visible_name"));
                        _disderm_users.set_user_type_id(rs.getInt("user_type_id"));
                        _disderm_users.set_profile_image(rs.getString("profile_image"));
                        _disderm_users.set_enabled(rs.getInt("enabled"));
                        _disderm_users.set_phone_mobile(rs.getString("phone_mobile"));
                        _disderm_users.set_localidad(rs.getString("localidad"));
                        _disderm_users.set_provincia(rs.getString("provincia"));
                        _disderm_users.set_cp(rs.getString("cp"));
                        _disderm_users.set_app_platform(rs.getString("app_platform"));
                        _disderm_users.set_app_device_model(rs.getString("app_device_model"));
                        _disderm_users.set_app_device_token(rs.getString("app_device_token"));
                        _disderm_users.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                        _disderm_users.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                        _disderm_users.set_message(rs.getString("message"));
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
            return _disderm_users;
        }

    }

    public String getOneFieldFromDisdermusersByID(Long _id, String _field) {
        String toReturn = null;
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
                        toReturn = rs.getString(_field);
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
        }
        return toReturn;

    }


    public boolean deleteDisdermusersByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM disderm_users WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java Delete(DisdermusersByID() Exception: " + ex.getMessage());
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

        }
        return success;

    }


    public boolean getIfExistDisdermusersByFieldAndStringValue(String _field, String _value) {
        boolean toReturn = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users WHERE ? = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _field);
                ps.setString(2, _value);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        toReturn = true;
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java getIfExistDisdermusersByFieldAndStringValue() Exception: " + ex.getMessage());
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

        }
        return toReturn;

    }

    public String getCustomDataTableDisdermusers(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<DisdermusersModel> arrayDataModel = new ArrayList<DisdermusersModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_disderm_users = getNumeroFilasDisdermusers();
        long numero_filas_filtro_disderm_users = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_disderm_users = getNumeroFilasFiltroDisdermusers("SELECT count(id) as total FROM disderm_users " + filter_sql);
        } else {
            numero_filas_filtro_disderm_users = numero_filas_total_disderm_users;
        }
        String sql = "SELECT * FROM disderm_users " + filter_sql + " limit ?,?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, start);
                ps.setLong(2, length);
                rs = ps.executeQuery();
                while (rs.next()) {
                    row = new JsonArray();
                    row.add(rs.getLong("id"));
                    List<String> fields_to_show_temp = new ArrayList<>(fields_to_show);
                    int pos = 0;
                    for (String field_name_raw : fields_to_show_temp) {
                        String field_name = field_name_raw.split("%")[0];
                        int action_type = Integer.parseInt(field_name_raw.split("%")[1]);
                        if (field_name.equalsIgnoreCase("id")) {
                            row.add(rs.getLong("id"));
                        }
                        if (field_name.equalsIgnoreCase("username")) {
                            row.add(rs.getString("username"));
                        }
                        if (field_name.equalsIgnoreCase("password")) {
                            row.add(rs.getString("password"));
                        }
                        if (field_name.equalsIgnoreCase("first_name")) {
                            row.add(rs.getString("first_name"));
                        }
                        if (field_name.equalsIgnoreCase("last_name")) {
                            row.add(rs.getString("last_name"));
                        }
                        if (field_name.equalsIgnoreCase("visible_name")) {
                            row.add(rs.getString("visible_name"));
                        }
                        if (field_name.equalsIgnoreCase("user_type_id")) {
                            row.add(rs.getLong("user_type_id"));
                        }
                        if (field_name.equalsIgnoreCase("profile_image")) {
                            row.add(rs.getString("profile_image"));
                        }
                        if (field_name.equalsIgnoreCase("enabled")) {
                            row.add(rs.getLong("enabled"));
                        }
                        if (field_name.equalsIgnoreCase("phone_mobile")) {
                            row.add(rs.getString("phone_mobile"));
                        }
                        if (field_name.equalsIgnoreCase("localidad")) {
                            row.add(rs.getString("localidad"));
                        }
                        if (field_name.equalsIgnoreCase("provincia")) {
                            row.add(rs.getString("provincia"));
                        }
                        if (field_name.equalsIgnoreCase("cp")) {
                            row.add(rs.getString("cp"));
                        }
                        if (field_name.equalsIgnoreCase("app_platform")) {
                            row.add(rs.getString("app_platform"));
                        }
                        if (field_name.equalsIgnoreCase("app_device_model")) {
                            row.add(rs.getString("app_device_model"));
                        }
                        if (field_name.equalsIgnoreCase("app_device_token")) {
                            row.add(rs.getString("app_device_token"));
                        }
                        if (field_name.equalsIgnoreCase("fecha_alta_sistema")) {
                            row.add(FFecha.getString(rs.getTimestamp("fecha_alta_sistema"), FFecha.sdf_ddMMyyyyBarHHmm));
                        }
                        if (field_name.equalsIgnoreCase("fecha_ultimo_acceso")) {
                            row.add(FFecha.getString(rs.getTimestamp("fecha_ultimo_acceso"), FFecha.sdf_ddMMyyyyBarHHmm));
                        }
                        if (field_name.equalsIgnoreCase("message")) {
                            row.add(rs.getString("message"));
                        }
                        if (field_name.startsWith("extID")) {
                            String className = field_name.split(":")[1];
                            String methodName = field_name.split(":")[2];
                            String parameterToPass = field_name.split(":")[3];
                            String typeValueToReturn = field_name.split(":")[4];
                            Class class_ext = Class.forName(className);

                            Method metodo_ext = class_ext.getDeclaredMethod(methodName, Long.class);
                            metodo_ext.setAccessible(true);
                            if (typeValueToReturn.equalsIgnoreCase("int")) {
                                row.add((int) metodo_ext.invoke(class_ext.getDeclaredConstructor().newInstance(), rs.getLong(parameterToPass)));
                            } else if (typeValueToReturn.equalsIgnoreCase("String")) {
                                row.add((String) metodo_ext.invoke(class_ext.getDeclaredConstructor().newInstance(), rs.getLong(parameterToPass)));
                            }
                        }
                    }


                    data.add(row);
                }

                jMembers.add("data", data);
                jMembers.addProperty("draw", draw);
                jMembers.addProperty("recordsTotal", numero_filas_total_disderm_users);
                jMembers.addProperty("recordsFiltered", numero_filas_filtro_disderm_users);

                reply = jMembers.toString();
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java getCustomDataTableDisdermusers() Exception: " + ex.getMessage());
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

        }
        return reply;

    }

    public String getCustomSQLDataTableDisdermusers(int start, int length, int draw, String sql, String sql_filtro) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<DisdermusersModel> arrayDataModel = new ArrayList<DisdermusersModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_disderm_users = getNumeroFilasDisdermusers();
        long numero_filas_filtro_disderm_users = 0L;
        if (!sql_filtro.isEmpty()) {
            numero_filas_filtro_disderm_users = getNumeroFilasFiltroDisdermusers("SELECT count(id) as total FROM disderm_users " + sql_filtro);
        } else {
            numero_filas_filtro_disderm_users = numero_filas_total_disderm_users;
        }
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, start);
                ps.setLong(2, length);
                rs = ps.executeQuery();
                while (rs.next()) {
                    row = new JsonArray();
                    if (conn != null) {
                        ps = conn.prepareStatement(sql);
                        rs = ps.executeQuery();
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        while (rs.next()) {
                            row = new JsonArray();
                            for (int j = 1; j <= columnsNumber; j++) {

                                int sqlTypes = rsmd.getColumnType(j);

                                switch (sqlTypes) {
                                    case Types.VARCHAR:
                                        row.add(rs.getString(j));
                                        break;
                                    case Types.NULL:
                                        break;
                                    case Types.CHAR:
                                        break;
                                    case Types.TIMESTAMP:
                                        row.add(FFecha.getString(rs.getTimestamp(j), FFecha.sdf_ddMMyyyyBarHHmm));
                                        break;
                                    case Types.DOUBLE:
                                        row.add(rs.getDouble(j));
                                        break;
                                    case Types.INTEGER:
                                        row.add(rs.getInt(j));
                                        break;
                                    case Types.SMALLINT:
                                        row.add(rs.getInt(j));
                                        break;
                                    case Types.DECIMAL:
                                        row.add(rs.getBigDecimal(j));
                                        break;
                                }
                                row.add(rs.getString(j));
                            }
                            data.add(row);
                        }
                    }


                    jMembers.add("data", data);
                    jMembers.addProperty("draw", draw);
                    jMembers.addProperty("recordsTotal", numero_filas_total_disderm_users);
                    jMembers.addProperty("recordsFiltered", numero_filas_filtro_disderm_users);

                    reply = jMembers.toString();
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java getCustomSQLDataTableDisdermusers() Exception: " + ex.getMessage());
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

            return reply;
        }

    }

    public long getNumeroFilasDisdermusersByShowTableStatus() {
        long numero_filas_disderm_users = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "show table status like 'disderm_users'";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_disderm_users = rs.getLong("Rows");
                }
            }
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java getNumeroFilas(Disdermusers() Exception: " + ex.getMessage());
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

        }
        return numero_filas_disderm_users;

    }


    public String getAllDisdermusersByUsernameString(String username) {
        Long id = 0L;
        String reply = "";
        ArrayList<DisdermusersModel> arrayDataModel = new ArrayList<DisdermusersModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM disderm_users where LOWER(username) = LOWER(?)";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DisdermusersModel _disderm_users = new DisdermusersModel();
                    _disderm_users.set_id(rs.getLong("id"));
                    _disderm_users.set_username(rs.getString("username"));
                    _disderm_users.set_password(rs.getString("password"));
                    _disderm_users.set_first_name(rs.getString("first_name"));
                    _disderm_users.set_last_name(rs.getString("last_name"));
                    _disderm_users.set_visible_name(rs.getString("visible_name"));
                    _disderm_users.set_user_type_id(rs.getInt("user_type_id"));
                    _disderm_users.set_profile_image(rs.getString("profile_image"));
                    _disderm_users.set_enabled(rs.getInt("enabled"));
                    _disderm_users.set_phone_mobile(rs.getString("phone_mobile"));
                    _disderm_users.set_localidad(rs.getString("localidad"));
                    _disderm_users.set_provincia(rs.getString("provincia"));
                    _disderm_users.set_cp(rs.getString("cp"));
                    _disderm_users.set_app_platform(rs.getString("app_platform"));
                    _disderm_users.set_app_device_model(rs.getString("app_device_model"));
                    _disderm_users.set_app_device_token(rs.getString("app_device_token"));
                    _disderm_users.set_fecha_alta_sistema(rs.getDate("fecha_alta_sistema"));
                    _disderm_users.set_fecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso"));
                    _disderm_users.set_message(rs.getString("message"));
                    arrayDataModel.add(_disderm_users);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("DisdermusersDAO.java GetAll(Disdermusers() Exception: " + ex.getMessage());
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

        }
        return reply;

    }


}