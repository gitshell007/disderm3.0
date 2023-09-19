package com.disderm.dao;

import com.disderm.conn.DBConnection;
import com.disderm.model.DropdownModel;
import com.disderm.model.UploadModel;
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

public class UploadDAO {
  private static final SSLog ssLog = new SSLog();

  public Long insertUpload(UploadModel _upload) {
    Long id = 0L;
    String sql = "INSERT INTO upload( type, name, status, upload_date) VALUES ( ?, ?, ?, ?)";
    try {
      java.sql.Connection conn = null;
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        PreparedStatement ps = conn.prepareStatement(sql, new String[] {"upload_id"});
        ps.setString(1, _upload.get_type());
        ps.setString(2, _upload.get_name());
        ps.setString(3, _upload.get_status());
        ps.setDate(4, FFecha.convertir(_upload.get_upload_date()));
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

  public String getUploadByID(Long _id) {
    String reply = "";
    UploadModel _upload = new UploadModel();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM upload WHERE upload_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            _upload.set_type(rs.getString("type"));
            _upload.set_name(rs.getString("name"));
            _upload.set_status(rs.getString("status"));
            _upload.set_upload_date(rs.getDate("upload_date"));
            Gson gson = new Gson();
            reply = gson.toJson(_upload);
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java GetUpload() Exception: " + ex.getMessage());
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

  public UploadModel getUploadModelByID(Long _id) {
    UploadModel _upload = new UploadModel();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM upload WHERE upload_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            _upload.set_type(rs.getString("type"));
            _upload.set_name(rs.getString("name"));
            _upload.set_status(rs.getString("status"));
            _upload.set_upload_date(rs.getDate("upload_date"));
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java GetUpload() Exception: " + ex.getMessage());
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
      return _upload;
    }
  }

  public String getOneFieldFromUploadByID(Long _id, String _field) {
    String toReturn = null;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM upload WHERE upload_id = ?";
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
      ssLog.info("UploadDAO.java GetUpload() Exception: " + ex.getMessage());
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

  public Long getIDUploadFromOneStringField(String _field, String _value) {
    Long toReturn = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT upload_id FROM upload WHERE " + _field + " = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      Timestamp ts = new Timestamp(new Date().getTime());
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, _value);
        rs = ps.executeQuery();
        if (rs != null) {
          if (rs.next()) {
            toReturn = rs.getLong("upload_id");
          }
        }
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java FromOneStringFieldUpload() Exception: " + ex.getMessage());
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

  public String getAllUpload() {
    Long id = 0L;
    String reply = "";
    ArrayList<UploadModel> arrayDataModel = new ArrayList<UploadModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM upload";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
          UploadModel _upload = new UploadModel();
          _upload.set_type(rs.getString("type"));
          _upload.set_name(rs.getString("name"));
          _upload.set_status(rs.getString("status"));
          _upload.set_upload_date(rs.getDate("upload_date"));
          arrayDataModel.add(_upload);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java GetAll(Upload() Exception: " + ex.getMessage());
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

  public boolean updateUploadString(Map _map, Long _id) {
    boolean success = false;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "UPDATE upload SET ";
    for (Object o : _map.entrySet()) {
      Map.Entry pair = (Map.Entry) o;
      sql += pair.getKey() + " = ?,";
    }
    sql = sql.substring(0, sql.length() - 1);
    sql += " WHERE upload_id = ?";
    System.out.println("updateUploadString " + sql);
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
      ssLog.info("UploadDAO.java UpdateStringUpload() Exception: " + ex.getMessage());
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

  public boolean deleteUploadByID(Long _id) {
    boolean success = false;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "DELETE FROM upload WHERE upload_id = ?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, _id);
        success = ps.executeUpdate() > 0;
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java Delete(UploadByID() Exception: " + ex.getMessage());
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

  public String getAllUploadByUsername(String _username) {
    Long id = 0L;
    String reply = "";
    ArrayList<UploadModel> arrayDataModel = new ArrayList<UploadModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM upload where LOWER(username) = LOWER(?)";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, _username);
        rs = ps.executeQuery();
        while (rs.next()) {
          UploadModel _upload = new UploadModel();
          _upload.set_type(rs.getString("type"));
          _upload.set_name(rs.getString("name"));
          _upload.set_status(rs.getString("status"));
          _upload.set_upload_date(rs.getDate("upload_date"));
          _upload.set_upload_id(rs.getLong("upload_id"));
          arrayDataModel.add(_upload);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java GetAllUpload() Exception: " + ex.getMessage());
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

  public String getAllSelectUpload() {
    Long id = 0L;
    String reply = "";
    ArrayList<DropdownModel> arrayDataModel = new ArrayList<DropdownModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM upload";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
          DropdownModel _upload = new DropdownModel();
          _upload.set_des(rs.getString("upload_des"));
          _upload.set_code(rs.getString("upload_code"));
          _upload.set_dropdown_id(rs.getLong("upload_id"));
          arrayDataModel.add(_upload);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java GetAllDropDown(Upload() Exception: " + ex.getMessage());
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

  public String getCustomDataTableUpload(
      int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<UploadModel> arrayDataModel = new ArrayList<UploadModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_upload = getNumeroFilasUpload();
    long numero_filas_filtro_upload = 0L;
    if (!filter_sql.isEmpty()) {
      numero_filas_filtro_upload =
          getNumeroFilasFiltroUpload("SELECT count(upload_id) as total FROM upload " + filter_sql);
    } else {
      numero_filas_filtro_upload = numero_filas_total_upload;
    }
    String sql = "SELECT * FROM upload " + filter_sql + " limit ?,?";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setLong(1, start);
        ps.setLong(2, length);
        rs = ps.executeQuery();
        while (rs.next()) {
          row = new JsonArray();
          row.add(rs.getLong("upload_id"));
          List<String> fields_to_show_temp = new ArrayList<>(fields_to_show);
          int pos = 0;
          for (String field_name_raw : fields_to_show_temp) {
            String field_name = field_name_raw.split("@")[0];
            int action_type = Integer.parseInt(field_name_raw.split("@")[1]);
            if (field_name.equalsIgnoreCase("type")) {
              row.add(rs.getString("type"));
            }
            if (field_name.equalsIgnoreCase("name")) {
              row.add(rs.getString("name"));
            }
            if (field_name.equalsIgnoreCase("status")) {
              row.add(rs.getString("status"));
            }
            if (field_name.equalsIgnoreCase("upload_date")) {
              row.add(FFecha.getString(rs.getTimestamp("upload_date"), FFecha.sdf_ddMMyyyyBarHHmm));
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
        jMembers.addProperty("recordsTotal", numero_filas_total_upload);
        jMembers.addProperty("recordsFiltered", numero_filas_filtro_upload);

        reply = jMembers.toString();
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java getCustomDataTableUpload() Exception: " + ex.getMessage());
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

  public String getCustomSQLDataTableUpload(
      int start, int length, int draw, String sql, String sql_filtro) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<UploadModel> arrayDataModel = new ArrayList<UploadModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_upload = getNumeroFilasUpload();
    long numero_filas_filtro_upload = 0L;
    if (!sql_filtro.isEmpty()) {
      numero_filas_filtro_upload =
          getNumeroFilasFiltroUpload("SELECT count(upload_id) as total FROM upload " + sql_filtro);
    } else {
      numero_filas_filtro_upload = numero_filas_total_upload;
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
          jMembers.addProperty("recordsTotal", numero_filas_total_upload);
          jMembers.addProperty("recordsFiltered", numero_filas_filtro_upload);

          reply = jMembers.toString();
        }
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java getCustomSQLDataTableUpload() Exception: " + ex.getMessage());
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

  public long getNumeroFilasUploadByShowTableStatus() {
    long numero_filas_upload = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "show table status like 'upload'";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_upload = rs.getLong("Rows");
        }
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java getNumeroFilas(Upload() Exception: " + ex.getMessage());
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
    return numero_filas_upload;
  }

  public long getNumeroFilasUpload() {
    long numero_filas_upload = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT COUNT(upload_id) as total from upload";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_upload = rs.getLong("total");
        }
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java getNumeroFilas(Upload() Exception: " + ex.getMessage());
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
    return numero_filas_upload;
  }

  public long getNumeroFilasFiltroUpload(String _sql) {
    long numero_filas_upload = 0L;
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(_sql);
        rs = ps.executeQuery();
        if (rs.next()) {
          numero_filas_upload = rs.getLong("total");
        }
      }
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java getNumeroFilasFiltroUpload() Exception: " + ex.getMessage());
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
    return numero_filas_upload;
  }

  public String getAllDataTableUpload(int start, int length, int draw, String filter_sql) {
    Long id = 0L;
    String reply = "";
    JsonObject jMembers = new JsonObject();
    JsonArray data = new JsonArray();
    JsonArray row = new JsonArray();
    ArrayList<UploadModel> arrayDataModel = new ArrayList<UploadModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    long numero_filas_total_upload = getNumeroFilasUpload();
    long numero_filas_filtro_upload = 0L;
    if (!filter_sql.isEmpty()) {
      numero_filas_filtro_upload =
          getNumeroFilasFiltroUpload("SELECT count(upload_id) as total FROM upload " + filter_sql);
    } else {
      numero_filas_filtro_upload = numero_filas_total_upload;
    }
    String sql = "SELECT * FROM upload " + filter_sql + " limit ?,?";
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
          row.add(rs.getString("name"));
          row.add(rs.getString("status"));
          row.add(FFecha.getString(rs.getTimestamp("upload_date"), FFecha.sdf_ddMMyyyyBarHHmm));

          data.add(row);
        }
      }

      jMembers.add("data", data);
      jMembers.addProperty("draw", draw);
      jMembers.addProperty("recordsTotal", numero_filas_total_upload);
      jMembers.addProperty("recordsFiltered", numero_filas_filtro_upload);
      reply = jMembers.toString();
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java GetAllDataTableUpload() Exception: " + ex.getMessage());
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

  public String getAllUploadByUsernameString(String username) {
    Long id = 0L;
    String reply = "";
    ArrayList<UploadModel> arrayDataModel = new ArrayList<UploadModel>();
    ResultSet rs = null;
    java.sql.Connection conn = null;
    PreparedStatement ps = null;
    String sql = "SELECT * FROM upload where LOWER(username) = LOWER(?)";
    try {
      conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
      if (conn != null) {
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery();
        while (rs.next()) {
          UploadModel _upload = new UploadModel();
          _upload.set_type(rs.getString("type"));
          _upload.set_name(rs.getString("name"));
          _upload.set_status(rs.getString("status"));
          _upload.set_upload_date(rs.getDate("upload_date"));
          _upload.set_upload_id(rs.getLong("upload_id"));
          arrayDataModel.add(_upload);
        }
      }
      Gson gson = new Gson();
      reply = gson.toJson(arrayDataModel);
    } catch (Exception ex) {
      ssLog.info("UploadDAO.java GetAll(Upload() Exception: " + ex.getMessage());
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
