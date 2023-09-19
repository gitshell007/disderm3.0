package com.disderm.dao;

import com.disderm.model.LaboratoryModel;
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

public class LaboratoryDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertLaboratory(LaboratoryModel _laboratory) {
        Long id = 0L;
        String sql = "INSERT INTO laboratory(description) VALUES (?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, _laboratory.get_description());
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

    public String getLaboratoryByID(Long _id) {
        String reply = "";
        LaboratoryModel _laboratory = new LaboratoryModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _laboratory.set_id(rs.getLong("id"));
                        _laboratory.set_description(rs.getString("description"));
                        Gson gson = new Gson();
                        reply = gson.toJson(_laboratory);
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetLaboratory() Exception: " + ex.getMessage());
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

    public LaboratoryModel getLaboratoryModelByID(Long _id) {
        LaboratoryModel _laboratory = new LaboratoryModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _laboratory.set_id(rs.getLong("id"));
                        _laboratory.set_description(rs.getString("description"));
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetLaboratory() Exception: " + ex.getMessage());
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
            return _laboratory;
        }

    }

    public String getOneFieldFromLaboratoryByID(Long _id, String _field) {
        String toReturn = null;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory WHERE id = ?";
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
            ssLog.info("LaboratoryDAO.java GetLaboratory() Exception: " + ex.getMessage());
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

    public Long getIDLaboratoryFromOneStringField(String _field, String _value) {
        Long toReturn = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT id FROM laboratory WHERE " + _field + " = ?";
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
            ssLog.info("LaboratoryDAO.java FromOneStringFieldLaboratory() Exception: " + ex.getMessage());
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

    public String getAllLaboratory() {
        Long id = 0L;
        String reply = "";
        ArrayList<LaboratoryModel> arrayDataModel = new ArrayList<LaboratoryModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    LaboratoryModel _laboratory = new LaboratoryModel();
                    _laboratory.set_id(rs.getLong("id"));
                    _laboratory.set_description(rs.getString("description"));
                    arrayDataModel.add(_laboratory);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetAll(Laboratory() Exception: " + ex.getMessage());
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

    public boolean updateLaboratoryString(Map _map, Long _id) {
        boolean success = false;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE laboratory SET ";
        for (Object o : _map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            sql += pair.getKey() + " = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE id = ?";
        System.out.println("updateLaboratoryString " + sql);
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
            ssLog.info("LaboratoryDAO.java UpdateStringLaboratory() Exception: " + ex.getMessage());
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

    public boolean deleteLaboratoryByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM laboratory WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java Delete(LaboratoryByID() Exception: " + ex.getMessage());
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

    public String getAllLaboratoryByUsername(String _username) {
        Long id = 0L;
        String reply = "";
        ArrayList<LaboratoryModel> arrayDataModel = new ArrayList<LaboratoryModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory as t1, pacientes as t2 WHERE t1.pacientes_id = t2.pacientes_id AND t2.app_username = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    LaboratoryModel _laboratory = new LaboratoryModel();
                    _laboratory.set_id(rs.getLong("id"));
                    _laboratory.set_description(rs.getString("description"));
                    arrayDataModel.add(_laboratory);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetAllLaboratory() Exception: " + ex.getMessage());
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

    public String getAllSelectLaboratory() {
        Long id = 0L;
        String reply = "";
        ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DropdownModel _laboratory = new DropdownModel();
                    _laboratory.set_des(rs.getString("laboratory_des"));
                    _laboratory.set_code(rs.getString("laboratory_code"));
                    _laboratory.set_dropdown_id(rs.getLong("id"));
                    arrayDataModel.add(_laboratory);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetAllDropDown(Laboratory() Exception: " + ex.getMessage());
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

    public boolean getIfExistLaboratoryByFieldAndStringValue(String _field, String _value) {
        boolean toReturn = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory WHERE ? = ?";
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
            ssLog.info("LaboratoryDAO.java getIfExistLaboratoryByFieldAndStringValue() Exception: " + ex.getMessage());
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

    public String getCustomDataTableLaboratory(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<LaboratoryModel> arrayDataModel = new ArrayList<LaboratoryModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_laboratory = getNumeroFilasLaboratory();
        long numero_filas_filtro_laboratory = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_laboratory = getNumeroFilasFiltroLaboratory("SELECT count(id) as total FROM laboratory " + filter_sql);
        } else {
            numero_filas_filtro_laboratory = numero_filas_total_laboratory;
        }
        String sql = "SELECT * FROM laboratory " + filter_sql + " limit ?,?";
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
                        if (field_name.equalsIgnoreCase("description")) {
                            row.add(rs.getString("description"));
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
                jMembers.addProperty("recordsTotal", numero_filas_total_laboratory);
                jMembers.addProperty("recordsFiltered", numero_filas_filtro_laboratory);

                reply = jMembers.toString();
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java getCustomDataTableLaboratory() Exception: " + ex.getMessage());
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

    public String getCustomSQLDataTableLaboratory(int start, int length, int draw, String sql, String sql_filtro) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<LaboratoryModel> arrayDataModel = new ArrayList<LaboratoryModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_laboratory = getNumeroFilasLaboratory();
        long numero_filas_filtro_laboratory = 0L;
        if (!sql_filtro.isEmpty()) {
            numero_filas_filtro_laboratory = getNumeroFilasFiltroLaboratory("SELECT count(id) as total FROM laboratory " + sql_filtro);
        } else {
            numero_filas_filtro_laboratory = numero_filas_total_laboratory;
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
                    jMembers.addProperty("recordsTotal", numero_filas_total_laboratory);
                    jMembers.addProperty("recordsFiltered", numero_filas_filtro_laboratory);

                    reply = jMembers.toString();
                }
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java getCustomSQLDataTableLaboratory() Exception: " + ex.getMessage());
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

    public long getNumeroFilasLaboratoryByShowTableStatus() {
        long numero_filas_laboratory = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "show table status like 'laboratory'";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_laboratory = rs.getLong("Rows");
                }
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java getNumeroFilas(Laboratory() Exception: " + ex.getMessage());
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
        return numero_filas_laboratory;

    }

    public long getNumeroFilasLaboratory() {
        long numero_filas_laboratory = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(id) as total from laboratory";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_laboratory = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java getNumeroFilas(Laboratory() Exception: " + ex.getMessage());
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
        return numero_filas_laboratory;

    }

    public long getNumeroFilasFiltroLaboratory(String _sql) {
        long numero_filas_laboratory = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_laboratory = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java getNumeroFilasFiltroLaboratory() Exception: " + ex.getMessage());
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
        return numero_filas_laboratory;

    }

    public String getAllDataTableLaboratory(int start, int length, int draw, String filter_sql) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<LaboratoryModel> arrayDataModel = new ArrayList<LaboratoryModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_laboratory = getNumeroFilasLaboratory();
        long numero_filas_filtro_laboratory = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_laboratory = getNumeroFilasFiltroLaboratory("SELECT count(id) as total FROM laboratory " + filter_sql);
        } else {
            numero_filas_filtro_laboratory = numero_filas_total_laboratory;
        }
        String sql = "SELECT * FROM laboratory " + filter_sql + " limit ?,?";
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
                    row.add(rs.getString("description"));

                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_laboratory);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_laboratory);
            reply = jMembers.toString();
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetAllDataTableLaboratory() Exception: " + ex.getMessage());
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

    public String getAllLaboratoryByUsernameString(String username) {
        Long id = 0L;
        String reply = "";
        ArrayList<LaboratoryModel> arrayDataModel = new ArrayList<LaboratoryModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory where LOWER(username) = LOWER(?)";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    LaboratoryModel _laboratory = new LaboratoryModel();
                    _laboratory.set_id(rs.getLong("id"));
                    _laboratory.set_description(rs.getString("description"));
                    arrayDataModel.add(_laboratory);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetAll(Laboratory() Exception: " + ex.getMessage());
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

    public String getAllLaboratoryByPacienteID(Long user_id) {
        Long id = 0L;
        String reply = "";
        ArrayList<LaboratoryModel> arrayDataModel = new ArrayList<LaboratoryModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM laboratory where pacientes_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, user_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    LaboratoryModel _laboratory = new LaboratoryModel();
                    _laboratory.set_id(rs.getLong("id"));
                    _laboratory.set_description(rs.getString("description"));
                    arrayDataModel.add(_laboratory);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("LaboratoryDAO.java GetAll(Laboratory() Exception: " + ex.getMessage());
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