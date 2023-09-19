package com.disderm.dao;

import com.disderm.model.PaymenttypeModel;
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

public class PaymenttypeDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertPaymenttype(PaymenttypeModel _payment_type) {
        Long id = 0L;
        String sql = "INSERT INTO payment_type( id, description) VALUES ( ?, ?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setLong(1, _payment_type.get_id());
                ps.setString(2, _payment_type.get_description());
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

    public String getPaymenttypeByID(Long _id) {
        String reply = "";
        PaymenttypeModel _payment_type = new PaymenttypeModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _payment_type.set_id(rs.getLong("id"));
                        _payment_type.set_description(rs.getString("description"));
                        Gson gson = new Gson();
                        reply = gson.toJson(_payment_type);
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetPaymenttype() Exception: " + ex.getMessage());
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

    public PaymenttypeModel getPaymenttypeModelByID(Long _id) {
        PaymenttypeModel _payment_type = new PaymenttypeModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _payment_type.set_id(rs.getLong("id"));
                        _payment_type.set_description(rs.getString("description"));
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetPaymenttype() Exception: " + ex.getMessage());
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
            return _payment_type;
        }

    }

    public String getOneFieldFromPaymenttypeByID(Long _id, String _field) {
        String toReturn = null;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type WHERE id = ?";
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
            ssLog.info("PaymenttypeDAO.java GetPaymenttype() Exception: " + ex.getMessage());
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

    public Long getIDPaymenttypeFromOneStringField(String _field, String _value) {
        Long toReturn = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT id FROM payment_type WHERE " + _field + " = ?";
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
            ssLog.info("PaymenttypeDAO.java FromOneStringFieldPaymenttype() Exception: " + ex.getMessage());
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

    public String getAllPaymenttype() {
        Long id = 0L;
        String reply = "";
        ArrayList<PaymenttypeModel> arrayDataModel = new ArrayList<PaymenttypeModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PaymenttypeModel _payment_type = new PaymenttypeModel();
                    _payment_type.set_id(rs.getLong("id"));
                    _payment_type.set_description(rs.getString("description"));
                    arrayDataModel.add(_payment_type);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetAll(Paymenttype() Exception: " + ex.getMessage());
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

    public boolean updatePaymenttypeString(Map _map, Long _id) {
        boolean success = false;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE payment_type SET ";
        for (Object o : _map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            sql += pair.getKey() + " = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE id = ?";
        System.out.println("updatePaymenttypeString " + sql);
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
            ssLog.info("PaymenttypeDAO.java UpdateStringPaymenttype() Exception: " + ex.getMessage());
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

    public boolean deletePaymenttypeByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM payment_type WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java Delete(PaymenttypeByID() Exception: " + ex.getMessage());
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

    public String getAllPaymenttypeByUsername(String _username) {
        Long id = 0L;
        String reply = "";
        ArrayList<PaymenttypeModel> arrayDataModel = new ArrayList<PaymenttypeModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type as t1, pacientes as t2 WHERE t1.pacientes_id = t2.pacientes_id AND t2.app_username = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PaymenttypeModel _payment_type = new PaymenttypeModel();
                    _payment_type.set_id(rs.getLong("id"));
                    _payment_type.set_description(rs.getString("description"));
                    arrayDataModel.add(_payment_type);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetAllPaymenttype() Exception: " + ex.getMessage());
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

    public String getAllSelectPaymenttype() {
        Long id = 0L;
        String reply = "";
        ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DropdownModel _payment_type = new DropdownModel();
                    _payment_type.set_des(rs.getString("description"));
                    _payment_type.set_code(rs.getString("id"));
                    _payment_type.set_dropdown_id(rs.getLong("id"));
                    arrayDataModel.add(_payment_type);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetAllDropDown(Paymenttype() Exception: " + ex.getMessage());
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

    public boolean getIfExistPaymenttypeByFieldAndStringValue(String _field, String _value) {
        boolean toReturn = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type WHERE ? = ?";
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
            ssLog.info("PaymenttypeDAO.java getIfExistPaymenttypeByFieldAndStringValue() Exception: " + ex.getMessage());
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

    public String getCustomDataTablePaymenttype(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<PaymenttypeModel> arrayDataModel = new ArrayList<PaymenttypeModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_payment_type = getNumeroFilasPaymenttype();
        long numero_filas_filtro_payment_type = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_payment_type = getNumeroFilasFiltroPaymenttype("SELECT count(id) as total FROM payment_type " + filter_sql);
        } else {
            numero_filas_filtro_payment_type = numero_filas_total_payment_type;
        }
        String sql = "SELECT * FROM payment_type " + filter_sql + " limit ?,?";
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
                jMembers.addProperty("recordsTotal", numero_filas_total_payment_type);
                jMembers.addProperty("recordsFiltered", numero_filas_filtro_payment_type);

                reply = jMembers.toString();
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java getCustomDataTablePaymenttype() Exception: " + ex.getMessage());
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

    public String getCustomSQLDataTablePaymenttype(int start, int length, int draw, String sql, String sql_filtro) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<PaymenttypeModel> arrayDataModel = new ArrayList<PaymenttypeModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_payment_type = getNumeroFilasPaymenttype();
        long numero_filas_filtro_payment_type = 0L;
        if (!sql_filtro.isEmpty()) {
            numero_filas_filtro_payment_type = getNumeroFilasFiltroPaymenttype("SELECT count(id) as total FROM payment_type " + sql_filtro);
        } else {
            numero_filas_filtro_payment_type = numero_filas_total_payment_type;
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
                    jMembers.addProperty("recordsTotal", numero_filas_total_payment_type);
                    jMembers.addProperty("recordsFiltered", numero_filas_filtro_payment_type);

                    reply = jMembers.toString();
                }
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java getCustomSQLDataTablePaymenttype() Exception: " + ex.getMessage());
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

    public long getNumeroFilasPaymenttypeByShowTableStatus() {
        long numero_filas_payment_type = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "show table status like 'payment_type'";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_payment_type = rs.getLong("Rows");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java getNumeroFilas(Paymenttype() Exception: " + ex.getMessage());
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
        return numero_filas_payment_type;

    }

    public long getNumeroFilasPaymenttype() {
        long numero_filas_payment_type = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(id) as total from payment_type";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_payment_type = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java getNumeroFilas(Paymenttype() Exception: " + ex.getMessage());
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
        return numero_filas_payment_type;

    }

    public long getNumeroFilasFiltroPaymenttype(String _sql) {
        long numero_filas_payment_type = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_payment_type = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java getNumeroFilasFiltroPaymenttype() Exception: " + ex.getMessage());
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
        return numero_filas_payment_type;

    }

    public String getAllDataTablePaymenttype(int start, int length, int draw, String filter_sql) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<PaymenttypeModel> arrayDataModel = new ArrayList<PaymenttypeModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_payment_type = getNumeroFilasPaymenttype();
        long numero_filas_filtro_payment_type = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_payment_type = getNumeroFilasFiltroPaymenttype("SELECT count(id) as total FROM payment_type " + filter_sql);
        } else {
            numero_filas_filtro_payment_type = numero_filas_total_payment_type;
        }
        String sql = "SELECT * FROM payment_type " + filter_sql + " limit ?,?";
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
            jMembers.addProperty("recordsTotal", numero_filas_total_payment_type);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_payment_type);
            reply = jMembers.toString();
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetAllDataTablePaymenttype() Exception: " + ex.getMessage());
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

    public String getAllPaymenttypeByUsernameString(String username) {
        Long id = 0L;
        String reply = "";
        ArrayList<PaymenttypeModel> arrayDataModel = new ArrayList<PaymenttypeModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type where LOWER(username) = LOWER(?)";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PaymenttypeModel _payment_type = new PaymenttypeModel();
                    _payment_type.set_id(rs.getLong("id"));
                    _payment_type.set_description(rs.getString("description"));
                    arrayDataModel.add(_payment_type);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetAll(Paymenttype() Exception: " + ex.getMessage());
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

    public String getAllPaymenttypeByPacienteID(Long user_id) {
        Long id = 0L;
        String reply = "";
        ArrayList<PaymenttypeModel> arrayDataModel = new ArrayList<PaymenttypeModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM payment_type where pacientes_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, user_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PaymenttypeModel _payment_type = new PaymenttypeModel();
                    _payment_type.set_id(rs.getLong("id"));
                    _payment_type.set_description(rs.getString("description"));
                    arrayDataModel.add(_payment_type);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("PaymenttypeDAO.java GetAll(Paymenttype() Exception: " + ex.getMessage());
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