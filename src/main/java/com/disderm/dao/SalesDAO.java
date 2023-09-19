package com.disderm.dao;

import com.disderm.model.SalesModel;
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

public class SalesDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertSales(SalesModel _sales) {
        Long id = 0L;
        String sql = "INSERT INTO sales(pharmacy_id, description, product_id, upload_date, sale_date, units_number, units_price, import_source_id, payment_type_id, sale_type_id) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setInt(1, _sales.get_pharmacy_id());
                ps.setString(2, _sales.getDescription());
                ps.setInt(3, _sales.get_product_id());
                ps.setDate(4, FFecha.convertir(_sales.get_upload_date()));
                ps.setDate(5, FFecha.convertir(_sales.get_sale_date()));
                ps.setInt(6, _sales.get_units_number());
                ps.setBigDecimal(7, _sales.get_units_price());
                ps.setInt(8, _sales.get_import_source_id());
                ps.setInt(9, _sales.get_payment_type_id());
                ps.setInt(10, _sales.get_sale_type_id());
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

    public String getSalesByID(Long _id) {
        String reply = "";
        SalesModel _sales = new SalesModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _sales.set_id(rs.getLong("id"));
                        _sales.set_pharmacy_id(rs.getInt("pharmacy_id"));
                        _sales.setDescription(rs.getString("description"));
                        _sales.set_product_id(rs.getInt("product_id"));
                        _sales.set_upload_date(rs.getDate("upload_date"));
                        _sales.set_sale_date(rs.getDate("sale_date"));
                        _sales.set_units_number(rs.getInt("units_number"));
                        _sales.set_units_price(rs.getBigDecimal("units_price"));
                        _sales.set_import_source_id(rs.getInt("import_source_id"));
                        _sales.set_payment_type_id(rs.getInt("payment_type_id"));
                        _sales.set_sale_type_id(rs.getInt("sale_type_id"));

                        Gson gson = new Gson();
                        reply = gson.toJson(_sales);
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetSales() Exception: " + ex.getMessage());
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

    public SalesModel getSalesModelByID(Long _id) {
        SalesModel _sales = new SalesModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _sales.set_id(rs.getLong("id"));
                        _sales.set_pharmacy_id(rs.getInt("pharmacy_id"));
                        _sales.setDescription(rs.getString("description"));
                        _sales.set_product_id(rs.getInt("product_id"));
                        _sales.set_upload_date(rs.getDate("upload_date"));
                        _sales.set_sale_date(rs.getDate("sale_date"));
                        _sales.set_units_number(rs.getInt("units_number"));
                        _sales.set_units_price(rs.getBigDecimal("units_price"));
                        _sales.set_import_source_id(rs.getInt("import_source_id"));
                        _sales.set_payment_type_id(rs.getInt("payment_type_id"));
                        _sales.set_sale_type_id(rs.getInt("sale_type_id"));
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetSales() Exception: " + ex.getMessage());
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
            return _sales;
        }

    }

    public String getOneFieldFromSalesByID(Long _id, String _field) {
        String toReturn = null;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales WHERE id = ?";
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
            ssLog.info("SalesDAO.java GetSales() Exception: " + ex.getMessage());
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

    public Long getIDSalesFromOneStringField(String _field, String _value) {
        Long toReturn = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT id FROM sales WHERE " + _field + " = ?";
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
            ssLog.info("SalesDAO.java FromOneStringFieldSales() Exception: " + ex.getMessage());
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

    public String getAllSales() {
        Long id = 0L;
        String reply = "";
        ArrayList<SalesModel> arrayDataModel = new ArrayList<SalesModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    SalesModel _sales = new SalesModel();
                    _sales.set_id(rs.getLong("id"));
                    _sales.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _sales.setDescription(rs.getString("description"));
                    _sales.set_product_id(rs.getInt("product_id"));
                    _sales.set_upload_date(rs.getDate("upload_date"));
                    _sales.set_sale_date(rs.getDate("sale_date"));
                    _sales.set_units_number(rs.getInt("units_number"));
                    _sales.set_units_price(rs.getBigDecimal("units_price"));
                    _sales.set_import_source_id(rs.getInt("import_source_id"));
                    _sales.set_payment_type_id(rs.getInt("payment_type_id"));
                    _sales.set_sale_type_id(rs.getInt("sale_type_id"));
                    arrayDataModel.add(_sales);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetAll(Sales() Exception: " + ex.getMessage());
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

    public boolean updateSalesString(Map _map, Long _id) {
        boolean success = false;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE sales SET ";
        for (Object o : _map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            sql += pair.getKey() + " = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE id = ?";
        System.out.println("updateSalesString " + sql);
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
            ssLog.info("SalesDAO.java UpdateStringSales() Exception: " + ex.getMessage());
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

    public boolean deleteSalesByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM sales WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java Delete(SalesByID() Exception: " + ex.getMessage());
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

    public String getAllSalesByUsername(String _username) {
        Long id = 0L;
        String reply = "";
        ArrayList<SalesModel> arrayDataModel = new ArrayList<SalesModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales as t1, pacientes as t2 WHERE t1.pacientes_id = t2.pacientes_id AND t2.app_username = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    SalesModel _sales = new SalesModel();
                    _sales.set_id(rs.getLong("id"));
                    _sales.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _sales.setDescription(rs.getString("description"));
                    _sales.set_product_id(rs.getInt("product_id"));
                    _sales.set_upload_date(rs.getDate("upload_date"));
                    _sales.set_sale_date(rs.getDate("sale_date"));
                    _sales.set_units_number(rs.getInt("units_number"));
                    _sales.set_units_price(rs.getBigDecimal("units_price"));
                    _sales.set_import_source_id(rs.getInt("import_source_id"));
                    _sales.set_payment_type_id(rs.getInt("payment_type_id"));
                    _sales.set_sale_type_id(rs.getInt("sale_type_id"));
                    arrayDataModel.add(_sales);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetAllSales() Exception: " + ex.getMessage());
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

    public String getAllSelectSales() {
        Long id = 0L;
        String reply = "";
        ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DropdownModel _sales = new DropdownModel();
                    _sales.set_des(rs.getString("upload_date"));
                    _sales.set_code(rs.getString("id"));
                    _sales.set_dropdown_id(rs.getLong("id"));
                    arrayDataModel.add(_sales);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetAllDropDown(Sales() Exception: " + ex.getMessage());
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

    public boolean getIfExistSalesByFieldAndStringValue(String _field, String _value) {
        boolean toReturn = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales WHERE ? = ?";
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
            ssLog.info("SalesDAO.java getIfExistSalesByFieldAndStringValue() Exception: " + ex.getMessage());
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

    public String getCustomDataTableSales(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<SalesModel> arrayDataModel = new ArrayList<SalesModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_sales = getNumeroFilasSales();
        long numero_filas_filtro_sales = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_sales = getNumeroFilasFiltroSales("SELECT count(id) as total FROM sales " + filter_sql);
        } else {
            numero_filas_filtro_sales = numero_filas_total_sales;
        }
        String sql = "SELECT * FROM sales " + filter_sql + " limit ?,?";
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
                        if (field_name.equalsIgnoreCase("pharmacy_id")) {
                            row.add(rs.getInt("pharmacy_id"));
                        }
                        if (field_name.equalsIgnoreCase("description")) {
                            row.add(rs.getString("description"));
                        }
                        if (field_name.equalsIgnoreCase("product_id")) {
                            row.add(rs.getInt("product_id"));
                        }
                        if (field_name.equalsIgnoreCase("upload_date")) {
                            row.add(FFecha.getString(rs.getTimestamp("upload_date"), FFecha.sdf_ddMMyyyyBarHHmm));
                        }
                        if (field_name.equalsIgnoreCase("sale_date")) {
                            row.add(FFecha.getString(rs.getTimestamp("sale_date"), FFecha.sdf_ddMMyyyyBarHHmm));
                        }
                        if (field_name.equalsIgnoreCase("units_number")) {
                            row.add(rs.getInt("units_number"));
                        }
                        if (field_name.equalsIgnoreCase("units_price")) {
                            row.add(rs.getBigDecimal("units_price"));
                        }
                        if (field_name.equalsIgnoreCase("import_source_id")) {
                            row.add(rs.getInt("import_source_id"));
                        }
                        if (field_name.equalsIgnoreCase("payment_type_id")) {
                            row.add(rs.getInt("payment_type_id"));
                        }
                        if (field_name.equalsIgnoreCase("sale_type_id")) {
                            row.add(rs.getInt("sale_type_id"));
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
                jMembers.addProperty("recordsTotal", numero_filas_total_sales);
                jMembers.addProperty("recordsFiltered", numero_filas_filtro_sales);

                reply = jMembers.toString();
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java getCustomDataTableSales() Exception: " + ex.getMessage());
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

    public String getCustomSQLDataTableSales(int start, int length, int draw, String sql, String sql_filtro) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<SalesModel> arrayDataModel = new ArrayList<SalesModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_sales = getNumeroFilasSales();
        long numero_filas_filtro_sales = 0L;
        if (!sql_filtro.isEmpty()) {
            numero_filas_filtro_sales = getNumeroFilasFiltroSales("SELECT count(id) as total FROM sales " + sql_filtro);
        } else {
            numero_filas_filtro_sales = numero_filas_total_sales;
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
                    jMembers.addProperty("recordsTotal", numero_filas_total_sales);
                    jMembers.addProperty("recordsFiltered", numero_filas_filtro_sales);

                    reply = jMembers.toString();
                }
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java getCustomSQLDataTableSales() Exception: " + ex.getMessage());
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

    public long getNumeroFilasSalesByShowTableStatus() {
        long numero_filas_sales = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "show table status like 'sales'";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_sales = rs.getLong("Rows");
                }
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java getNumeroFilas(Sales() Exception: " + ex.getMessage());
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
        return numero_filas_sales;

    }

    public long getNumeroFilasSales() {
        long numero_filas_sales = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(id) as total from sales";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_sales = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java getNumeroFilas(Sales() Exception: " + ex.getMessage());
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
        return numero_filas_sales;

    }

    public long getNumeroFilasFiltroSales(String _sql) {
        long numero_filas_sales = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_sales = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java getNumeroFilasFiltroSales() Exception: " + ex.getMessage());
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
        return numero_filas_sales;

    }

    public String getAllDataTableSales(int start, int length, int draw, String filter_sql) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<SalesModel> arrayDataModel = new ArrayList<SalesModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_sales = getNumeroFilasSales();
        long numero_filas_filtro_sales = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_sales = getNumeroFilasFiltroSales("SELECT count(id) as total FROM sales " + filter_sql);
        } else {
            numero_filas_filtro_sales = numero_filas_total_sales;
        }
        String sql = "SELECT * FROM sales " + filter_sql + " limit ?,?";
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
                    ps = conn.prepareStatement("SELECT description FROM pharmacy WHERE id = "+rs.getInt("pharmacy_id"));
                    ResultSet pharmacy = ps.executeQuery();
                    while (pharmacy.next()) {
                        row.add(pharmacy.getString("description"));
                    }

                    ps = conn.prepareStatement("SELECT description FROM product WHERE id = "+rs.getInt("product_id"));
                    ResultSet product = ps.executeQuery();
                    while (product.next()) {
                        row.add(product.getString("description"));
                    }


                    row.add(FFecha.getString(rs.getTimestamp("upload_date"), FFecha.sdf_ddMMyyyyBarHHmm));
                    row.add(FFecha.getString(rs.getTimestamp("sale_date"), FFecha.sdf_ddMMyyyyBarHHmm));
                    row.add(rs.getInt("units_number"));
                    row.add(rs.getBigDecimal("units_price"));


                    ps = conn.prepareStatement("SELECT description FROM import_source WHERE id = "+rs.getInt("import_source_id"));
                    ResultSet importSource = ps.executeQuery();
                    while (importSource.next()) {
                        row.add(importSource.getString("description"));
                    }

                    ps = conn.prepareStatement("SELECT description FROM payment_type WHERE id = "+rs.getInt("payment_type_id"));
                    ResultSet paymentType = ps.executeQuery();
                    while (paymentType.next()) {
                        row.add(paymentType.getString("description"));
                    }

                    ps = conn.prepareStatement("SELECT description FROM sale_type WHERE id = "+rs.getInt("sale_type_id"));
                    ResultSet saleType = ps.executeQuery();
                    while (saleType.next()) {
                        row.add(saleType.getString("description"));
                    }

                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_sales);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_sales);
            reply = jMembers.toString();
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetAllDataTableSales() Exception: " + ex.getMessage());
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

    public String getAllSalesByUsernameString(String username) {
        Long id = 0L;
        String reply = "";
        ArrayList<SalesModel> arrayDataModel = new ArrayList<SalesModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales where LOWER(username) = LOWER(?)";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    SalesModel _sales = new SalesModel();
                    _sales.set_id(rs.getLong("id"));
                    _sales.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _sales.setDescription(rs.getString("description"));
                    _sales.set_product_id(rs.getInt("product_id"));
                    _sales.set_upload_date(rs.getDate("upload_date"));
                    _sales.set_sale_date(rs.getDate("sale_date"));
                    _sales.set_units_number(rs.getInt("units_number"));
                    _sales.set_units_price(rs.getBigDecimal("units_price"));
                    _sales.set_import_source_id(rs.getInt("import_source_id"));
                    _sales.set_payment_type_id(rs.getInt("payment_type_id"));
                    _sales.set_sale_type_id(rs.getInt("sale_type_id"));
                    arrayDataModel.add(_sales);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetAll(Sales() Exception: " + ex.getMessage());
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

    public String getAllSalesByPacienteID(Long user_id) {
        Long id = 0L;
        String reply = "";
        ArrayList<SalesModel> arrayDataModel = new ArrayList<SalesModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM sales where pacientes_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, user_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    SalesModel _sales = new SalesModel();
                    _sales.set_id(rs.getLong("id"));
                    _sales.set_pharmacy_id(rs.getInt("pharmacy_id"));
                    _sales.setDescription(rs.getString("description"));
                    _sales.set_product_id(rs.getInt("product_id"));
                    _sales.set_upload_date(rs.getDate("upload_date"));
                    _sales.set_sale_date(rs.getDate("sale_date"));
                    _sales.set_units_number(rs.getInt("units_number"));
                    _sales.set_units_price(rs.getBigDecimal("units_price"));
                    _sales.set_import_source_id(rs.getInt("import_source_id"));
                    _sales.set_payment_type_id(rs.getInt("payment_type_id"));
                    _sales.set_sale_type_id(rs.getInt("sale_type_id"));
                    arrayDataModel.add(_sales);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("SalesDAO.java GetAll(Sales() Exception: " + ex.getMessage());
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