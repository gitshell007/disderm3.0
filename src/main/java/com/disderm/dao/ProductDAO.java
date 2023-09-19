package com.disderm.dao;

import com.disderm.model.ProductModel;
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

public class ProductDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertProduct(ProductModel _product) {
        Long id = 0L;
        String sql = "INSERT INTO product(category_id, description, import_source_id, cn) VALUES (?, ?, ?, ?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setInt(1, _product.get_category_id());
                ps.setString(2, _product.get_description());
                ps.setInt(3, _product.get_import_source_id());
                ps.setInt(4, _product.get_cn());
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

    public String getProductByID(Long _id) {
        String reply = "";
        ProductModel _product = new ProductModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _product.set_id(rs.getLong("id"));
                        _product.set_category_id(rs.getInt("category_id"));
                        _product.set_description(rs.getString("description"));
                        _product.set_import_source_id(rs.getInt("import_source_id"));
                        _product.set_cn(rs.getInt("cn"));
                        Gson gson = new Gson();
                        reply = gson.toJson(_product);
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetProduct() Exception: " + ex.getMessage());
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

    public ProductModel getProductModelByID(Long _id) {
        ProductModel _product = new ProductModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _product.set_id(rs.getLong("id"));
                        _product.set_category_id(rs.getInt("category_id"));
                        _product.set_description(rs.getString("description"));
                        _product.set_import_source_id(rs.getInt("import_source_id"));
                        _product.set_cn(rs.getInt("cn"));
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetProduct() Exception: " + ex.getMessage());
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
            return _product;
        }

    }

    public String getOneFieldFromProductByID(Long _id, String _field) {
        String toReturn = null;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product WHERE id = ?";
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
            ssLog.info("ProductDAO.java GetProduct() Exception: " + ex.getMessage());
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

    public Long getIDProductFromOneStringField(String _field, String _value) {
        Long toReturn = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT id FROM product WHERE " + _field + " = ?";
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
            ssLog.info("ProductDAO.java FromOneStringFieldProduct() Exception: " + ex.getMessage());
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

    public String getAllProduct() {
        Long id = 0L;
        String reply = "";
        ArrayList<ProductModel> arrayDataModel = new ArrayList<ProductModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ProductModel _product = new ProductModel();
                    _product.set_id(rs.getLong("id"));
                    _product.set_category_id(rs.getInt("category_id"));
                    _product.set_description(rs.getString("description"));
                    _product.set_import_source_id(rs.getInt("import_source_id"));
                    _product.set_cn(rs.getInt("cn"));
                    arrayDataModel.add(_product);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetAll(Product() Exception: " + ex.getMessage());
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

    public boolean updateProductString(Map _map, Long _id) {
        boolean success = false;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE product SET ";
        for (Object o : _map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            sql += pair.getKey() + " = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE id = ?";
        System.out.println("updateProductString " + sql);
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
            ssLog.info("ProductDAO.java UpdateStringProduct() Exception: " + ex.getMessage());
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

    public boolean deleteProductByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM product WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java Delete(ProductByID() Exception: " + ex.getMessage());
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

    public String getAllProductByUsername(String _username) {
        Long id = 0L;
        String reply = "";
        ArrayList<ProductModel> arrayDataModel = new ArrayList<ProductModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product as t1, pacientes as t2 WHERE t1.pacientes_id = t2.pacientes_id AND t2.app_username = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, _username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ProductModel _product = new ProductModel();
                    _product.set_id(rs.getLong("id"));
                    _product.set_category_id(rs.getInt("category_id"));
                    _product.set_description(rs.getString("description"));
                    _product.set_import_source_id(rs.getInt("import_source_id"));
                    _product.set_cn(rs.getInt("cn"));
                    arrayDataModel.add(_product);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetAllProduct() Exception: " + ex.getMessage());
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

    public String getAllSelectProduct() {
        Long id = 0L;
        String reply = "";
        ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DropdownModel _product = new DropdownModel();
                    _product.set_des(rs.getString("description"));
                    _product.set_code(rs.getString("id"));
                    _product.set_dropdown_id(rs.getLong("id"));
                    arrayDataModel.add(_product);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetAllDropDown(Product() Exception: " + ex.getMessage());
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

    public boolean getIfExistProductByFieldAndStringValue(String _field, String _value) {
        boolean toReturn = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product WHERE ? = ?";
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
            ssLog.info("ProductDAO.java getIfExistProductByFieldAndStringValue() Exception: " + ex.getMessage());
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

    public String getCustomDataTableProduct(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<ProductModel> arrayDataModel = new ArrayList<ProductModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_product = getNumeroFilasProduct();
        long numero_filas_filtro_product = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_product = getNumeroFilasFiltroProduct("SELECT count(id) as total FROM product " + filter_sql);
        } else {
            numero_filas_filtro_product = numero_filas_total_product;
        }
        String sql = "SELECT * FROM product " + filter_sql + " limit ?,?";
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
                        if (field_name.equalsIgnoreCase("category_id")) {
                            row.add(rs.getInt("category_id"));
                        }
                        if (field_name.equalsIgnoreCase("description")) {
                            row.add(rs.getString("description"));
                        }
                        if (field_name.equalsIgnoreCase("import_source_id")) {
                            row.add(rs.getInt("import_source_id"));
                        }
                        if (field_name.equalsIgnoreCase("cn")) {
                            row.add(rs.getInt("cn"));
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
                jMembers.addProperty("recordsTotal", numero_filas_total_product);
                jMembers.addProperty("recordsFiltered", numero_filas_filtro_product);

                reply = jMembers.toString();
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java getCustomDataTableProduct() Exception: " + ex.getMessage());
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

    public String getCustomSQLDataTableProduct(int start, int length, int draw, String sql, String sql_filtro) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<ProductModel> arrayDataModel = new ArrayList<ProductModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_product = getNumeroFilasProduct();
        long numero_filas_filtro_product = 0L;
        if (!sql_filtro.isEmpty()) {
            numero_filas_filtro_product = getNumeroFilasFiltroProduct("SELECT count(id) as total FROM product " + sql_filtro);
        } else {
            numero_filas_filtro_product = numero_filas_total_product;
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
                    jMembers.addProperty("recordsTotal", numero_filas_total_product);
                    jMembers.addProperty("recordsFiltered", numero_filas_filtro_product);

                    reply = jMembers.toString();
                }
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java getCustomSQLDataTableProduct() Exception: " + ex.getMessage());
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

    public long getNumeroFilasProductByShowTableStatus() {
        long numero_filas_product = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "show table status like 'product'";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_product = rs.getLong("Rows");
                }
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java getNumeroFilas(Product() Exception: " + ex.getMessage());
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
        return numero_filas_product;

    }

    public long getNumeroFilasProduct() {
        long numero_filas_product = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(id) as total from product";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_product = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java getNumeroFilas(Product() Exception: " + ex.getMessage());
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
        return numero_filas_product;

    }

    public long getNumeroFilasFiltroProduct(String _sql) {
        long numero_filas_product = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_product = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java getNumeroFilasFiltroProduct() Exception: " + ex.getMessage());
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
        return numero_filas_product;

    }

    public String getAllDataTableProduct(int start, int length, int draw, String filter_sql) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<ProductModel> arrayDataModel = new ArrayList<ProductModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        long numero_filas_total_product = getNumeroFilasProduct();
        long numero_filas_filtro_product = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_product = getNumeroFilasFiltroProduct("SELECT count(id) as total FROM product " + filter_sql);
        } else {
            numero_filas_filtro_product = numero_filas_total_product;
        }
        String sql = "SELECT * FROM product " + filter_sql + " limit ?,?";
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
                    ps = conn.prepareStatement("SELECT description FROM product_category WHERE id = "+rs.getInt("category_id"));
                    ResultSet pharmacy = ps.executeQuery();
                    while (pharmacy.next()) {
                        row.add(pharmacy.getString("description"));
                    }
                    row.add(rs.getString("description"));
                    ps = conn.prepareStatement("SELECT description FROM import_source WHERE id = "+rs.getInt("import_source_id"));
                    ResultSet importSource = ps.executeQuery();
                    while (importSource.next()) {
                        row.add(importSource.getString("description"));
                    }
                    row.add(rs.getInt("cn"));

                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_product);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_product);
            reply = jMembers.toString();
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetAllDataTableProduct() Exception: " + ex.getMessage());
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

    public String getAllProductByUsernameString(String username) {
        Long id = 0L;
        String reply = "";
        ArrayList<ProductModel> arrayDataModel = new ArrayList<ProductModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product where LOWER(username) = LOWER(?)";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ProductModel _product = new ProductModel();
                    _product.set_id(rs.getLong("id"));
                    _product.set_category_id(rs.getInt("category_id"));
                    _product.set_description(rs.getString("description"));
                    _product.set_import_source_id(rs.getInt("import_source_id"));
                    _product.set_cn(rs.getInt("cn"));
                    arrayDataModel.add(_product);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetAll(Product() Exception: " + ex.getMessage());
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

    public String getAllProductByPacienteID(Long user_id) {
        Long id = 0L;
        String reply = "";
        ArrayList<ProductModel> arrayDataModel = new ArrayList<ProductModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product where pacientes_id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, user_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ProductModel _product = new ProductModel();
                    _product.set_id(rs.getLong("id"));
                    _product.set_category_id(rs.getInt("category_id"));
                    _product.set_description(rs.getString("description"));
                    _product.set_import_source_id(rs.getInt("import_source_id"));
                    _product.set_cn(rs.getInt("cn"));
                    arrayDataModel.add(_product);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("ProductDAO.java GetAll(Product() Exception: " + ex.getMessage());
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