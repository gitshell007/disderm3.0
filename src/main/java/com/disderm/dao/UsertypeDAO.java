package com.disderm.dao;

import com.disderm.conn.DBConnection;
import com.disderm.model.DropdownModel;
import com.disderm.model.UsertypeModel;
import com.disderm.utils.FFecha;
import com.disderm.utils.SSLog;
import com.google.gson.*;
import com.google.gson.Gson;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UsertypeDAO {
  private static final SSLog ssLog = new SSLog();

  public Long insertUsertype(UsertypeModel _user_type) {
    Long id = 0L;
    String sql = "INSERT INTO user_type( user_type_des, user_type_code) VALUES ( ?, ?)";
    try {
      java.sql.Connection conn = null;
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        PreparedStatement ps = conn.prepareStatement(sql, new String[] {"user_type_id"});
        ps.setString(1, _user_type.get_user_type_des());
        ps.setString(2, _user_type.get_user_type_code());
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

  public String getUsertypeByID(Long _id) {
    String reply = "";
    UsertypeModel _user_type = new UsertypeModel();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM user_type WHERE user_type_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            _user_type.set_user_type_des(rs.getString("user_type_des"));
            _user_type.set_user_type_code(rs.getString("user_type_code"));
            Gson gson = new Gson();
            reply = gson.toJson(_user_type);
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java GetUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
      return reply;
    }
  }

  public UsertypeModel getUsertypeModelByID(Long _id) {
    UsertypeModel _user_type = new UsertypeModel();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM user_type WHERE user_type_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            _user_type.set_user_type_des(rs.getString("user_type_des"));
            _user_type.set_user_type_code(rs.getString("user_type_code"));
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java GetUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
      return _user_type;
    }
  }

  public String getOneFieldFromUsertypeByID(Long _id, String _field) {
    String toReturn = null;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM user_type WHERE user_type_id = ?";
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
      ssLog.info("UsertypeDAO.java GetUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
      return toReturn;
    }
  }

  public Long getIDUsertypeFromOneStringField(String _field, String _value) {
    Long toReturn = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT user_type_id FROM user_type WHERE " + _field + " = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, _value);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            toReturn = rs.getLong("user_type_id");
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java FromOneStringFieldUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
      return toReturn;
    }
  }

  public String getAllUsertype() {
    Long id = 0L;
    String reply = "";
    ArrayList<UsertypeModel> arrayDataModel = new ArrayList<UsertypeModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM user_type";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
          UsertypeModel _user_type = new UsertypeModel();
          _user_type.set_user_type_des(rs.getString("user_type_des"));
          _user_type.set_user_type_code(rs.getString("user_type_code"));
          arrayDataModel.add(_user_type);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java GetAll(Usertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
      return reply;
    }
  }

  public boolean updateUsertypeString(Map _map, Long _id) {
    boolean success = false;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "UPDATE user_type SET ";
    for (Object o : _map.entrySet()) {
      Map.Entry pair = (Map.Entry) o;
      sql += pair.getKey() + " = ?,";
    }
    sql = sql.substring(0, sql.length() - 1);
    sql += " WHERE user_type_id = ?";
    System.out.println("updateUsertypeString " + sql);
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
      ssLog.info("UsertypeDAO.java UpdateStringUsertype() Exception: " + ex.getMessage());
    } finally {
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
          System.out.println(logOrIgnore);
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
          System.out.println(logOrIgnore);
        }
    }
    return success;
  }

  public boolean deleteUsertypeByID(Long _id) {
    boolean success = false;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "DELETE FROM user_type WHERE user_type_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        success = ps.executeUpdate() > 0;
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java Delete(UsertypeByID() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return success;
  }

  public String getAllUsertypeByUsername(String _username) {
    Long id = 0L;
    String reply = "";
    ArrayList<UsertypeModel> arrayDataModel = new ArrayList<UsertypeModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM user_type where LOWER(username) = LOWER(?)";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, _username);
        rs = ps.executeQuery();
        while (rs.next()) {
          UsertypeModel _user_type = new UsertypeModel();
          _user_type.set_user_type_des(rs.getString("user_type_des"));
          _user_type.set_user_type_code(rs.getString("user_type_code"));
          _user_type.set_user_type_id(rs.getLong("user_type_id"));
          arrayDataModel.add(_user_type);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java GetAllUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return reply;
  }

  public String getAllSelectUsertype() {
    Long id = 0L;
    String reply = "";
    ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM user_type";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
          DropdownModel _user_type = new DropdownModel();
          _user_type.set_des(rs.getString("user_type_des"));
          _user_type.set_code(rs.getString("user_type_code"));
          _user_type.set_dropdown_id(rs.getLong("user_type_id"));
          arrayDataModel.add(_user_type);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java GetAllDropDown(Usertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return reply;
  }

  public String getCustomDataTableUsertype(
      int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<UsertypeModel> arrayDataModel = new ArrayList<UsertypeModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_user_type = getNumeroFilasUsertype();
    long numero_filas_filtro_user_type = 0L;
    if (!filter_sql.isEmpty()) {
      numero_filas_filtro_user_type =
          getNumeroFilasFiltroUsertype(
              "SELECT count(user_type_id) as total FROM user_type " + filter_sql);
    } else {
      numero_filas_filtro_user_type = numero_filas_total_user_type;
    }
    String sql = "SELECT * FROM user_type " + filter_sql + " limit ?,?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, start);
        ps.setLong(2, length);
        rs = ps.executeQuery();
        while (rs.next()) {
          row = new JsonArray();
          row.add(rs.getLong("user_type_id"));
          List<String> fields_to_show_temp = new ArrayList<>(fields_to_show);
          int pos = 0;
          for (String field_name_raw : fields_to_show_temp) {
            String field_name = field_name_raw.split("@")[0];
            int action_type = Integer.parseInt(field_name_raw.split("@")[1]);
            if (field_name.equalsIgnoreCase("user_type_des")) {
              row.add(rs.getString("user_type_des"));
            }
            if (field_name.equalsIgnoreCase("user_type_code")) {
              row.add(rs.getString("user_type_code"));
            }
            if (field_name.startsWith("extID")) {
              String className = field_name.split(":")[1];
              String methodName = field_name.split(":")[2];
              String parameterToPass = field_name.split(":")[3];
              String typeValueToReturn = field_name.split(":")[4];
              Class class_ext = Class.forName(className);

              Method metodo_ext = class_ext.getDeclaredMethod(methodName, Long.class);
              metodo_ext.setAccessible(true);

              row.add(
                  (int)
                      metodo_ext.invoke(
                          class_ext.getDeclaredConstructor().newInstance(),
                          rs.getLong(parameterToPass)));
            }
          }

          data.add(row);
        }

        jMembers.add("data", data);
        jMembers.addProperty("draw", draw);
        jMembers.addProperty("recordsTotal", numero_filas_total_user_type);
        jMembers.addProperty("recordsFiltered", numero_filas_filtro_user_type);

        reply = jMembers.toString();
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java getCustomDataTableUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return reply;
  }

  public String getCustomSQLDataTableUsertype(
      int start, int length, int draw, String sql, String sql_filtro) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<UsertypeModel> arrayDataModel = new ArrayList<UsertypeModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_user_type = getNumeroFilasUsertype();
    long numero_filas_filtro_user_type = 0L;
    if (!sql_filtro.isEmpty()) {
      numero_filas_filtro_user_type =
          getNumeroFilasFiltroUsertype(
              "SELECT count(user_type_id) as total FROM user_type " + sql_filtro);
    } else {
      numero_filas_filtro_user_type = numero_filas_total_user_type;
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
          jMembers.addProperty("recordsTotal", numero_filas_total_user_type);
          jMembers.addProperty("recordsFiltered", numero_filas_filtro_user_type);

          reply = jMembers.toString();
        }
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java getCustomSQLDataTableUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }

      return reply;
    }
  }

  public long getNumeroFilasUsertypeByShowTableStatus() {
    long numero_filas_user_type = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "show table status like 'user_type'";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_user_type = rs.getLong("Rows");
        }
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java getNumeroFilas(Usertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return numero_filas_user_type;
  }

  public long getNumeroFilasUsertype() {
    long numero_filas_user_type = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT COUNT(user_type_id) as total from user_type";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_user_type = rs.getLong("total");
        }
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java getNumeroFilas(Usertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return numero_filas_user_type;
  }

  public long getNumeroFilasFiltroUsertype(String _sql) {
    long numero_filas_user_type = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(_sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_user_type = rs.getLong("total");
        }
      }
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java getNumeroFilasFiltroUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return numero_filas_user_type;
  }

  public String getAllDataTableUsertype(int start, int length, int draw, String filter_sql) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<UsertypeModel> arrayDataModel = new ArrayList<UsertypeModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_user_type = getNumeroFilasUsertype();
    long numero_filas_filtro_user_type = 0L;
    if (!filter_sql.isEmpty()) {
      numero_filas_filtro_user_type =
          getNumeroFilasFiltroUsertype(
              "SELECT count(user_type_id) as total FROM user_type " + filter_sql);
    } else {
      numero_filas_filtro_user_type = numero_filas_total_user_type;
    }
    String sql = "SELECT * FROM user_type " + filter_sql + " limit ?,?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, start);
        ps.setLong(2, length);
        rs = ps.executeQuery();
        while (rs.next()) {
          row = new JsonArray();
          row.add(rs.getString("user_type_des"));
          row.add(rs.getString("user_type_code"));

          data.add(row);
        }
      }

      jMembers.add("data", data);
      jMembers.addProperty("draw", draw);
      jMembers.addProperty("recordsTotal", numero_filas_total_user_type);
      jMembers.addProperty("recordsFiltered", numero_filas_filtro_user_type);
      reply = jMembers.toString();
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java GetAllDataTableUsertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return reply;
  }

  public String getAllUsertypeByUsernameString(String username) {
    Long id = 0L;
    String reply = "";
    ArrayList<UsertypeModel> arrayDataModel = new ArrayList<UsertypeModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM user_type where LOWER(username) = LOWER(?)";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery();
        while (rs.next()) {
          UsertypeModel _user_type = new UsertypeModel();
          _user_type.set_user_type_des(rs.getString("user_type_des"));
          _user_type.set_user_type_code(rs.getString("user_type_code"));
          _user_type.set_user_type_id(rs.getLong("user_type_id"));
          arrayDataModel.add(_user_type);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UsertypeDAO.java GetAll(Usertype() Exception: " + ex.getMessage());
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException logOrIgnore) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException logOrIgnore) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
    return reply;
  }
}
