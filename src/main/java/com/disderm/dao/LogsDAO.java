package com.disderm.dao;

import com.disderm.conn.DBConnection;
import com.disderm.model.DropdownModel;
import com.disderm.model.LogsModel;
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

public class LogsDAO {
  private static final SSLog ssLog = new SSLog();

  public Long insertLogs(LogsModel _logs) {
    Long id = 0L;
    String sql = "INSERT INTO logs( type, date, user, des) VALUES ( ?, ?, ?, ?)";
    try {
      java.sql.Connection conn = null;
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        PreparedStatement ps = conn.prepareStatement(sql, new String[] {"logs_id"});
        ps.setString(1, _logs.get_type());
        ps.setString(2, _logs.get_date());
        ps.setString(3, _logs.get_user());
        ps.setString(4, _logs.get_des());
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

  public String getLogsByID(Long _id) {
    String reply = "";
    LogsModel _logs = new LogsModel();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM logs WHERE logs_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            _logs.set_type(rs.getString("type"));
            _logs.set_date(rs.getString("date"));
            _logs.set_user(rs.getString("user"));
            _logs.set_des(rs.getString("des"));
            Gson gson = new Gson();
            reply = gson.toJson(_logs);
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java GetLogs() Exception: " + ex.getMessage());
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

  public LogsModel getLogsModelByID(Long _id) {
    LogsModel _logs = new LogsModel();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM logs WHERE logs_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            _logs.set_type(rs.getString("type"));
            _logs.set_date(rs.getString("date"));
            _logs.set_user(rs.getString("user"));
            _logs.set_des(rs.getString("des"));
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java GetLogs() Exception: " + ex.getMessage());
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
      return _logs;
    }
  }

  public String getOneFieldFromLogsByID(Long _id, String _field) {
    String toReturn = null;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM logs WHERE logs_id = ?";
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
      ssLog.info("LogsDAO.java GetLogs() Exception: " + ex.getMessage());
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

  public Long getIDLogsFromOneStringField(String _field, String _value) {
    Long toReturn = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT logs_id FROM logs WHERE " + _field + " = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, _value);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            toReturn = rs.getLong("logs_id");
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java FromOneStringFieldLogs() Exception: " + ex.getMessage());
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

  public String getAllLogs() {
    Long id = 0L;
    String reply = "";
    ArrayList<LogsModel> arrayDataModel = new ArrayList<LogsModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM logs";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
          LogsModel _logs = new LogsModel();
          _logs.set_type(rs.getString("type"));
          _logs.set_date(rs.getString("date"));
          _logs.set_user(rs.getString("user"));
          _logs.set_des(rs.getString("des"));
          arrayDataModel.add(_logs);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java GetAll(Logs() Exception: " + ex.getMessage());
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

  public boolean updateLogsString(Map _map, Long _id) {
    boolean success = false;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "UPDATE logs SET ";
    for (Object o : _map.entrySet()) {
      Map.Entry pair = (Map.Entry) o;
      sql += pair.getKey() + " = ?,";
    }
    sql = sql.substring(0, sql.length() - 1);
    sql += " WHERE logs_id = ?";
    System.out.println("updateLogsString " + sql);
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
      ssLog.info("LogsDAO.java UpdateStringLogs() Exception: " + ex.getMessage());
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

  public boolean deleteLogsByID(Long _id) {
    boolean success = false;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "DELETE FROM logs WHERE logs_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        success = ps.executeUpdate() > 0;
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java Delete(LogsByID() Exception: " + ex.getMessage());
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

  public String getAllLogsByUsername(String _username) {
    Long id = 0L;
    String reply = "";
    ArrayList<LogsModel> arrayDataModel = new ArrayList<LogsModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM logs where LOWER(username) = LOWER(?)";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, _username);
        rs = ps.executeQuery();
        while (rs.next()) {
          LogsModel _logs = new LogsModel();
          _logs.set_type(rs.getString("type"));
          _logs.set_date(rs.getString("date"));
          _logs.set_user(rs.getString("user"));
          _logs.set_des(rs.getString("des"));
          _logs.set_logs_id(rs.getLong("logs_id"));
          arrayDataModel.add(_logs);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java GetAllLogs() Exception: " + ex.getMessage());
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

  public String getAllSelectLogs() {
    Long id = 0L;
    String reply = "";
    ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM logs";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
          DropdownModel _logs = new DropdownModel();
          _logs.set_des(rs.getString("logs_des"));
          _logs.set_code(rs.getString("logs_code"));
          _logs.set_dropdown_id(rs.getLong("logs_id"));
          arrayDataModel.add(_logs);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java GetAllDropDown(Logs() Exception: " + ex.getMessage());
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

  public String getCustomDataTableLogs(
      int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<LogsModel> arrayDataModel = new ArrayList<LogsModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_logs = getNumeroFilasLogs();
    long numero_filas_filtro_logs = 0L;
    if (!filter_sql.isEmpty()) {
      numero_filas_filtro_logs =
          getNumeroFilasFiltroLogs("SELECT count(logs_id) as total FROM logs " + filter_sql);
    } else {
      numero_filas_filtro_logs = numero_filas_total_logs;
    }
    String sql = "SELECT * FROM logs " + filter_sql + " limit ?,?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, start);
        ps.setLong(2, length);
        rs = ps.executeQuery();
        while (rs.next()) {
          row = new JsonArray();
          row.add(rs.getLong("logs_id"));
          List<String> fields_to_show_temp = new ArrayList<>(fields_to_show);
          int pos = 0;
          for (String field_name_raw : fields_to_show_temp) {
            String field_name = field_name_raw.split("@")[0];
            int action_type = Integer.parseInt(field_name_raw.split("@")[1]);
            if (field_name.equalsIgnoreCase("type")) {
              row.add(rs.getString("type"));
            }
            if (field_name.equalsIgnoreCase("date")) {
              row.add(rs.getString("date"));
            }
            if (field_name.equalsIgnoreCase("user")) {
              row.add(rs.getString("user"));
            }
            if (field_name.equalsIgnoreCase("des")) {
              row.add(rs.getString("des"));
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
        jMembers.addProperty("recordsTotal", numero_filas_total_logs);
        jMembers.addProperty("recordsFiltered", numero_filas_filtro_logs);

        reply = jMembers.toString();
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java getCustomDataTableLogs() Exception: " + ex.getMessage());
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

  public String getCustomSQLDataTableLogs(
      int start, int length, int draw, String sql, String sql_filtro) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<LogsModel> arrayDataModel = new ArrayList<LogsModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_logs = getNumeroFilasLogs();
    long numero_filas_filtro_logs = 0L;
    if (!sql_filtro.isEmpty()) {
      numero_filas_filtro_logs =
          getNumeroFilasFiltroLogs("SELECT count(logs_id) as total FROM logs " + sql_filtro);
    } else {
      numero_filas_filtro_logs = numero_filas_total_logs;
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
          jMembers.addProperty("recordsTotal", numero_filas_total_logs);
          jMembers.addProperty("recordsFiltered", numero_filas_filtro_logs);

          reply = jMembers.toString();
        }
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java getCustomSQLDataTableLogs() Exception: " + ex.getMessage());
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

  public long getNumeroFilasLogsByShowTableStatus() {
    long numero_filas_logs = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "show table status like 'logs'";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_logs = rs.getLong("Rows");
        }
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java getNumeroFilas(Logs() Exception: " + ex.getMessage());
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
    return numero_filas_logs;
  }

  public long getNumeroFilasLogs() {
    long numero_filas_logs = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT COUNT(logs_id) as total from logs";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_logs = rs.getLong("total");
        }
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java getNumeroFilas(Logs() Exception: " + ex.getMessage());
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
    return numero_filas_logs;
  }

  public long getNumeroFilasFiltroLogs(String _sql) {
    long numero_filas_logs = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(_sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_logs = rs.getLong("total");
        }
      }
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java getNumeroFilasFiltroLogs() Exception: " + ex.getMessage());
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
    return numero_filas_logs;
  }

  public String getAllDataTableLogs(int start, int length, int draw, String filter_sql) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<LogsModel> arrayDataModel = new ArrayList<LogsModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_logs = getNumeroFilasLogs();
    long numero_filas_filtro_logs = 0L;
    if (!filter_sql.isEmpty()) {
      numero_filas_filtro_logs =
          getNumeroFilasFiltroLogs("SELECT count(logs_id) as total FROM logs " + filter_sql);
    } else {
      numero_filas_filtro_logs = numero_filas_total_logs;
    }
    String sql = "SELECT * FROM logs " + filter_sql + " limit ?,?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, start);
        ps.setLong(2, length);
        rs = ps.executeQuery();
        while (rs.next()) {
          row = new JsonArray();
          row.add(rs.getString("type"));
          row.add(rs.getString("date"));
          row.add(rs.getString("user"));
          row.add(rs.getString("des"));

          data.add(row);
        }
      }

      jMembers.add("data", data);
      jMembers.addProperty("draw", draw);
      jMembers.addProperty("recordsTotal", numero_filas_total_logs);
      jMembers.addProperty("recordsFiltered", numero_filas_filtro_logs);
      reply = jMembers.toString();
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java GetAllDataTableLogs() Exception: " + ex.getMessage());
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

  public String getAllLogsByUsernameString(String username) {
    Long id = 0L;
    String reply = "";
    ArrayList<LogsModel> arrayDataModel = new ArrayList<LogsModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM logs where LOWER(username) = LOWER(?)";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery();
        while (rs.next()) {
          LogsModel _logs = new LogsModel();
          _logs.set_type(rs.getString("type"));
          _logs.set_date(rs.getString("date"));
          _logs.set_user(rs.getString("user"));
          _logs.set_des(rs.getString("des"));
          _logs.set_logs_id(rs.getLong("logs_id"));
          arrayDataModel.add(_logs);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("LogsDAO.java GetAll(Logs() Exception: " + ex.getMessage());
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
