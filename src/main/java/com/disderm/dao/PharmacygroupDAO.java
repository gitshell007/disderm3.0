package com.disderm.dao;

import com.disderm.model.PharmacygroupModel;
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

public class PharmacygroupDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertPharmacygroup(PharmacygroupModel _pharmacy_group) {
        Long id = 0L;
        String sql = "INSERT INTO pharmacy_group( id, pharmacy_id, pharmacist_id) VALUES ( ?, ?, ?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"pharmacy_group_id"});
                ps.setLong(1, _pharmacy_group.get_id());
                ps.setInt(2, _pharmacy_group.get_pharmacy_id());
                ps.setInt(3, _pharmacy_group.get_pharmacist_id());
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

    public String getPharmacygroupByID(Long _id) {
        String reply = "";
        PharmacygroupModel _pharmacy_group = new PharmacygroupModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group WHERE pharmacy_group_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _pharmacy_group.set_id(rs.getLong("id"));
                        _pharmacy_group.set_pharmacy_id(rs.getInt("pharmacy_id"));
                        _pharmacy_group.set_pharmacist_id(rs.getInt("pharmacist_id"));
                        Gson gson = new Gson();
                        reply = gson.toJson(_pharmacy_group);
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetPharmacygroup() Exception: " + ex.getMessage());
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

    public PharmacygroupModel getPharmacygroupModelByID(Long _id) {
        PharmacygroupModel _pharmacy_group = new PharmacygroupModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group WHERE pharmacy_group_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _pharmacy_group.set_id(rs.getLong("id"));
                        _pharmacy_group.set_pharmacy_id(rs.getInt("pharmacy_id"));
                        _pharmacy_group.set_pharmacist_id(rs.getInt("pharmacist_id"));
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetPharmacygroup() Exception: " + ex.getMessage());
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
            return _pharmacy_group;
        }

    }

    public String getOneFieldFromPharmacygroupByID(Long _id, String _field) {
        String toReturn = null;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group WHERE pharmacy_group_id = ?";
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
            ssLog.info("PharmacygroupDAO.java GetPharmacygroup() Exception: " + ex.getMessage());
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

    public Long getIDPharmacygroupFromOneStringField(String _field, String _value) {
        Long toReturn = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT pharmacy_group_id FROM pharmacy_group WHERE " + _field + " = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _value);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        toReturn = rs.getLong("pharmacy_group_id");
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java FromOneStringFieldPharmacygroup() Exception: " + ex.getMessage());
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

    public String getAllPharmacygroup() {
        Long id = 0L;
        String reply = "";
        ArrayList<PharmacygroupModel> arrayDataModel = new ArrayList<PharmacygroupModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PharmacygroupModel _pharmacy_group = new PharmacygroupModel();
                    _pharmacy_group.set_id(rs.getLong("id"));
                    _pharmacy_group.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _pharmacy_group.set_pharmacist_id(rs.getInt("pharmacist_id"));
                    arrayDataModel.add(_pharmacy_group);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetAll(Pharmacygroup() Exception: " + ex.getMessage());
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

    public boolean updatePharmacygroupString(Map _map, Long _id) {
        boolean success = false;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE pharmacy_group SET ";
        for (Object o : _map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            sql += pair.getKey() + " = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE pharmacy_group_id = ?";
        System.out.println("updatePharmacygroupString " + sql);
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
            ssLog.info("PharmacygroupDAO.java UpdateStringPharmacygroup() Exception: " + ex.getMessage());
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

    public boolean deletePharmacygroupByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM pharmacy_group WHERE pharmacy_group_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java Delete(PharmacygroupByID() Exception: " + ex.getMessage());
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

    public String getAllPharmacygroupByUsername(String _username) {
        Long id = 0L;
        String reply = "";
        ArrayList<PharmacygroupModel> arrayDataModel = new ArrayList<PharmacygroupModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group as t1, pacientes as t2 WHERE t1.pacientes_id = t2.pacientes_id AND t2.app_username = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PharmacygroupModel _pharmacy_group = new PharmacygroupModel();
                    _pharmacy_group.set_id(rs.getLong("id"));
                    _pharmacy_group.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _pharmacy_group.set_pharmacist_id(rs.getInt("pharmacist_id"));
                    arrayDataModel.add(_pharmacy_group);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetAllPharmacygroup() Exception: " + ex.getMessage());
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

    public String getAllSelectPharmacygroup() {
        Long id = 0L;
        String reply = "";
        ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DropdownModel _pharmacy_group = new DropdownModel();
                    _pharmacy_group.set_des(rs.getString("pharmacy_group_des"));
                    _pharmacy_group.set_code(rs.getString("pharmacy_group_code"));
                    _pharmacy_group.set_dropdown_id(rs.getLong("pharmacy_group_id"));
                    arrayDataModel.add(_pharmacy_group);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetAllDropDown(Pharmacygroup() Exception: " + ex.getMessage());
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

    public boolean getIfExistPharmacygroupByFieldAndStringValue(String _field, String _value) {
        boolean toReturn = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group WHERE ? = ?";
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
            ssLog.info("PharmacygroupDAO.java getIfExistPharmacygroupByFieldAndStringValue() Exception: " + ex.getMessage());
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

    public String getCustomDataTablePharmacygroup(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<PharmacygroupModel> arrayDataModel = new ArrayList<PharmacygroupModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_pharmacy_group = getNumeroFilasPharmacygroup();
        long numero_filas_filtro_pharmacy_group = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_pharmacy_group = getNumeroFilasFiltroPharmacygroup("SELECT count(pharmacy_group_id) as total FROM pharmacy_group " + filter_sql);
        } else {
            numero_filas_filtro_pharmacy_group = numero_filas_total_pharmacy_group;
        }
        String sql = "SELECT * FROM pharmacy_group " + filter_sql + " limit ?,?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, start);
                ps.setLong(2, length);
                rs = ps.executeQuery();
                while (rs.next()) {
                    row = new JsonArray();
                    row.add(rs.getLong("pharmacy_group_id"));
                    List<String> fields_to_show_temp = new ArrayList<>(fields_to_show);
                    int pos = 0;
                    for (String field_name_raw : fields_to_show_temp) {
                        String field_name = field_name_raw.split("%")[0];
                        int action_type = Integer.parseInt(field_name_raw.split("%")[1]);
                        if (field_name.equalsIgnoreCase("id")) {
                            row.add(rs.getLong("id"));
                        }
                        if (field_name.equalsIgnoreCase("pharmacy_id")) {
                            row.add(rs.getInt("pharmacy_id"));
                        }
                        if (field_name.equalsIgnoreCase("pharmacist_id")) {
                            row.add(rs.getInt("pharmacist_id"));
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
                jMembers.addProperty("recordsTotal", numero_filas_total_pharmacy_group);
                jMembers.addProperty("recordsFiltered", numero_filas_filtro_pharmacy_group);

                reply = jMembers.toString();
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java getCustomDataTablePharmacygroup() Exception: " + ex.getMessage());
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

    public String getCustomSQLDataTablePharmacygroup(int start, int length, int draw, String sql, String sql_filtro) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<PharmacygroupModel> arrayDataModel = new ArrayList<PharmacygroupModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_pharmacy_group = getNumeroFilasPharmacygroup();
        long numero_filas_filtro_pharmacy_group = 0L;
        if (!sql_filtro.isEmpty()) {
            numero_filas_filtro_pharmacy_group = getNumeroFilasFiltroPharmacygroup("SELECT count(pharmacy_group_id) as total FROM pharmacy_group " + sql_filtro);
        } else {
            numero_filas_filtro_pharmacy_group = numero_filas_total_pharmacy_group;
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
                    jMembers.addProperty("recordsTotal", numero_filas_total_pharmacy_group);
                    jMembers.addProperty("recordsFiltered", numero_filas_filtro_pharmacy_group);

                    reply = jMembers.toString();
                }
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java getCustomSQLDataTablePharmacygroup() Exception: " + ex.getMessage());
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

    public long getNumeroFilasPharmacygroupByShowTableStatus() {
        long numero_filas_pharmacy_group = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "show table status like 'pharmacy_group'";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_pharmacy_group = rs.getLong("Rows");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java getNumeroFilas(Pharmacygroup() Exception: " + ex.getMessage());
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
        return numero_filas_pharmacy_group;

    }

    public long getNumeroFilasPharmacygroup() {
        long numero_filas_pharmacy_group = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(pharmacy_group_id) as total from pharmacy_group";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_pharmacy_group = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java getNumeroFilas(Pharmacygroup() Exception: " + ex.getMessage());
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
        return numero_filas_pharmacy_group;

    }

    public long getNumeroFilasFiltroPharmacygroup(String _sql) {
        long numero_filas_pharmacy_group = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_pharmacy_group = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java getNumeroFilasFiltroPharmacygroup() Exception: " + ex.getMessage());
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
        return numero_filas_pharmacy_group;

    }

    public String getAllDataTablePharmacygroup(int start, int length, int draw, String filter_sql) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<PharmacygroupModel> arrayDataModel = new ArrayList<PharmacygroupModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_pharmacy_group = getNumeroFilasPharmacygroup();
        long numero_filas_filtro_pharmacy_group = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_pharmacy_group = getNumeroFilasFiltroPharmacygroup("SELECT count(pharmacy_group_id) as total FROM pharmacy_group " + filter_sql);
        } else {
            numero_filas_filtro_pharmacy_group = numero_filas_total_pharmacy_group;
        }
        String sql = "SELECT * FROM pharmacy_group " + filter_sql + " limit ?,?";
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
                    row.add(rs.getInt("pharmacy_id"));
                    row.add(rs.getInt("pharmacist_id"));

                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_pharmacy_group);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_pharmacy_group);
            reply = jMembers.toString();
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetAllDataTablePharmacygroup() Exception: " + ex.getMessage());
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

    public String getAllPharmacygroupByUsernameString(String username) {
        Long id = 0L;
        String reply = "";
        ArrayList<PharmacygroupModel> arrayDataModel = new ArrayList<PharmacygroupModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group where LOWER(username) = LOWER(?)";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PharmacygroupModel _pharmacy_group = new PharmacygroupModel();
                    _pharmacy_group.set_id(rs.getLong("id"));
                    _pharmacy_group.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _pharmacy_group.set_pharmacist_id(rs.getInt("pharmacist_id"));
                    arrayDataModel.add(_pharmacy_group);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetAll(Pharmacygroup() Exception: " + ex.getMessage());
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

    public String getAllPharmacygroupByPacienteID(Long user_id) {
        Long id = 0L;
        String reply = "";
        ArrayList<PharmacygroupModel> arrayDataModel = new ArrayList<PharmacygroupModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM pharmacy_group where pacientes_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, user_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PharmacygroupModel _pharmacy_group = new PharmacygroupModel();
                    _pharmacy_group.set_id(rs.getLong("id"));
                    _pharmacy_group.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _pharmacy_group.set_pharmacist_id(rs.getInt("pharmacist_id"));
                    arrayDataModel.add(_pharmacy_group);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PharmacygroupDAO.java GetAll(Pharmacygroup() Exception: " + ex.getMessage());
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
}