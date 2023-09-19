package com.disderm.dao;

import com.disderm.model.*;
import com.disderm.conn.DBConnection;
import com.disderm.utils.SSLog;
import com.disderm.utils.Settings;
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

public class DiagnosticoDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertDiagnosticodiagnostico(DiagnosticoModel _diagnosticodiagnostico) {
        Long id = 0L;
        String sql = " INSERT INTO diagnostico ( paciente_id, fecha, " +
                " patologia, urgente, observaciones, estado, " +
                " user_facultativo_id, user_admin_id, user_primaria_id, valoracion_dermatologo) " +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setLong(1, _diagnosticodiagnostico.get_paciente_id());
                ps.setDate(2, FFecha.convertir(_diagnosticodiagnostico.get_fecha()));
                ps.setString(3, _diagnosticodiagnostico.get_patologia());
                ps.setString(4, _diagnosticodiagnostico.get_urgente());
                ps.setString(5, _diagnosticodiagnostico.get_observaciones());
                ps.setString(6, _diagnosticodiagnostico.get_estado());
                ps.setLong(7, _diagnosticodiagnostico.get_user_facultativo_id());
                ps.setLong(8, _diagnosticodiagnostico.get_user_admin_id());
                ps.setLong(9, _diagnosticodiagnostico.get_user_primaria_id());
                ps.setString(10, _diagnosticodiagnostico.get_valoracion_dermatologo());
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

    public Long updateDiagnosticodiagnostico(DiagnosticoModel _diagnosticodiagnostico) {
        Long id = 0L;
        String sql = " UPDATE diagnostico SET  paciente_id = ?, fecha = ?, " +
                " patologia = ?, urgente = ?, observaciones = ?, estado = ?, " +
                " user_facultativo_id = ?, user_admin_id = ?, user_primaria_id = ?, valoracion_dermatologo = ? WHERE id = ?";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setLong(1, _diagnosticodiagnostico.get_paciente_id());
                ps.setDate(2, FFecha.convertir(_diagnosticodiagnostico.get_fecha()));
                ps.setString(3, _diagnosticodiagnostico.get_patologia());
                ps.setString(4, _diagnosticodiagnostico.get_urgente());
                ps.setString(5, _diagnosticodiagnostico.get_observaciones());
                ps.setString(6, _diagnosticodiagnostico.get_estado());
                ps.setLong(7, _diagnosticodiagnostico.get_user_facultativo_id());
                ps.setLong(8, _diagnosticodiagnostico.get_user_admin_id());
                ps.setLong(9, _diagnosticodiagnostico.get_user_primaria_id());
                ps.setString(10, _diagnosticodiagnostico.get_valoracion_dermatologo());
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

    public DiagnosticoModel getDiagnosticoModelByID(Long _id) {
        DiagnosticoModel diagnostico = new DiagnosticoModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM diagnostico WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        diagnostico.set_id(rs.getLong("id"));
                        diagnostico.set_paciente_id(rs.getLong("paciente_id"));
                        diagnostico.set_fecha(rs.getDate("fecha"));
                        diagnostico.set_patologia(rs.getString("patologia"));
                        diagnostico.set_urgente(rs.getString("urgente"));
                        diagnostico.set_observaciones(rs.getString("observaciones"));
                        diagnostico.set_estado(rs.getString("estado"));
                        diagnostico.set_user_facultativo_id(rs.getLong("user_facultativo_id"));
                        diagnostico.set_user_admin_id(rs.getLong("user_admin_id"));
                        diagnostico.set_user_primaria_id(rs.getLong("user_primaria_id"));
                        diagnostico.set_valoracion_dermatologo(rs.getString("valoracion_dermatologo"));

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
            return diagnostico;
        }

    }

    public String getCustomDataTableDiagnosticoPendiente(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<DiagnosticoModel> arrayDataModel = new ArrayList<DiagnosticoModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        System.out.println("Entramos en getcustomdatatablediagnosticos");
        long numero_filas_total_diagnosticos = getNumeroFilasDiagnosticos();
        long numero_filas_filtro_diagnosticos = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_diagnosticos =
                    getNumeroFilasFiltroDiagnosticos(
                            "SELECT count(id) as total FROM diagnostico " + filter_sql);
        } else {
            numero_filas_filtro_diagnosticos = numero_filas_total_diagnosticos;
        }
        String sql = "SELECT * FROM diagnostico d INNER JOIN paciente p ON d.paciente_id = p.id " + filter_sql + "WHERE d.estado='pendiente' ORDER BY d.urgente DESC, d.fecha ASC " + " limit ?,?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, start);
                ps.setLong(2, length);
                rs = ps.executeQuery();
                while (rs.next()) {
                    row = new JsonArray();
                    row.add(rs.getLong("d.id"));
                    row.add(rs.getLong("d.paciente_id"));
                    row.add(Settings.getFechaDDMMAAAA((rs.getDate("d.fecha"))));
                    row.add(rs.getString("d.urgente"));
                    row.add(rs.getString("p.dni"));
                    row.add(rs.getString("p.nombre"));
                    row.add(rs.getString("p.apellidos"));
                    row.add(rs.getLong("p.telefono"));
                    row.add(rs.getString("p.correo"));
                    row.add(Settings.getFechaDDMMAAAA(rs.getDate("p.fecha_nacimiento")));
                    row.add(rs.getLong("d.user_facultativo_id"));
                    row.add(rs.getString("d.estado"));
                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_diagnosticos);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_diagnosticos);

            reply = jMembers.toString();

        } catch (Exception ex) {
            ssLog.info("DiasnogticoDAO.java getCustomDataTableDiagnostico() Exception: " + ex.getMessage());
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

    public String getCustomDataTableDiagnosticoFinalizado(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<DiagnosticoModel> arrayDataModel = new ArrayList<DiagnosticoModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        System.out.println("Entramos en getcustomdatatablediagnosticos");
        long numero_filas_total_diagnosticos = getNumeroFilasDiagnosticos();
        long numero_filas_filtro_diagnosticos = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_diagnosticos =
                    getNumeroFilasFiltroDiagnosticos(
                            "SELECT count(id) as total FROM diagnostico " + filter_sql);
        } else {
            numero_filas_filtro_diagnosticos = numero_filas_total_diagnosticos;
        }
        String sql = "SELECT * FROM diagnostico d INNER JOIN paciente p ON d.paciente_id = p.id " + filter_sql + "WHERE d.estado='finalizado' ORDER BY d.urgente DESC, d.fecha ASC " + " limit ?,?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, start);
                ps.setLong(2, length);
                rs = ps.executeQuery();
                while (rs.next()) {
                    row = new JsonArray();
                    row.add(rs.getLong("d.id"));
                    row.add(rs.getLong("d.paciente_id"));
                    row.add(Settings.getFechaDDMMAAAA((rs.getDate("d.fecha"))));
                    row.add(rs.getString("d.urgente"));
                    row.add(rs.getString("p.dni"));
                    row.add(rs.getString("p.nombre"));
                    row.add(rs.getString("p.apellidos"));
                    row.add(rs.getLong("p.telefono"));
                    row.add(rs.getString("p.correo"));
                    row.add(Settings.getFechaDDMMAAAA(rs.getDate("p.fecha_nacimiento")));
                    row.add(rs.getLong("d.user_facultativo_id"));
                    row.add(rs.getString("d.estado"));
                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_diagnosticos);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_diagnosticos);

            reply = jMembers.toString();

        } catch (Exception ex) {
            ssLog.info("DiasnogticoDAO.java getCustomDataTableDiagnostico() Exception: " + ex.getMessage());
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

    public String getCustomDataTableDiagnosticoIncidencia(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<DiagnosticoModel> arrayDataModel = new ArrayList<DiagnosticoModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        System.out.println("Entramos en getcustomdatatablediagnosticos");
        long numero_filas_total_diagnosticos = getNumeroFilasDiagnosticos();
        long numero_filas_filtro_diagnosticos = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_diagnosticos =
                    getNumeroFilasFiltroDiagnosticos(
                            "SELECT count(id) as total FROM diagnostico " + filter_sql);
        } else {
            numero_filas_filtro_diagnosticos = numero_filas_total_diagnosticos;
        }
        String sql = "SELECT * FROM diagnostico d INNER JOIN paciente p ON d.paciente_id = p.id " + filter_sql + "WHERE d.estado='incidencia' ORDER BY d.urgente DESC, d.fecha ASC " + " limit ?,?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, start);
                ps.setLong(2, length);
                rs = ps.executeQuery();
                while (rs.next()) {
                    row = new JsonArray();
                    row.add(rs.getLong("d.id"));
                    row.add(rs.getLong("d.paciente_id"));
                    row.add(Settings.getFechaDDMMAAAA((rs.getDate("d.fecha"))));
                    row.add(rs.getString("d.urgente"));
                    row.add(rs.getString("p.dni"));
                    row.add(rs.getString("p.nombre"));
                    row.add(rs.getString("p.apellidos"));
                    row.add(rs.getLong("p.telefono"));
                    row.add(rs.getString("p.correo"));
                    row.add(Settings.getFechaDDMMAAAA(rs.getDate("p.fecha_nacimiento")));
                    row.add(rs.getLong("d.user_facultativo_id"));
                    row.add(rs.getString("d.estado"));
                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_diagnosticos);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_diagnosticos);

            reply = jMembers.toString();

        } catch (Exception ex) {
            ssLog.info("DiasnogticoDAO.java getCustomDataTableDiagnostico() Exception: " + ex.getMessage());
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

    public long getNumeroFilasDiagnosticos() {
        long numero_filas_diagnosticos = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(id) as total from diagnostico ";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_diagnosticos = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("DiagnosticoDAO.java getNumeroFilas(Diagnostico() Exception: " + ex.getMessage());
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
        return numero_filas_diagnosticos;
    }

    public long getNumeroFilasFiltroDiagnosticos(String _sql) {
        long numero_filas_diagnosticos = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_diagnosticos = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("DiagnosticosDAO.java getNumeroFilasFiltroDiagnosticos() Exception:  AQUI" + ex.getMessage());
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
        return numero_filas_diagnosticos;
    }

    public boolean deleteDiagnosticosByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM diagnostico WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("DiagnosticoDAO.java Delete(Diagnostico ByID() Exception: " + ex.getMessage());
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

    public String getAllDiagnosticoIncidencia() {
        Long id = 0L;
        String reply = "";
        ArrayList<DiagnosticoPacienteModel> arrayDataModel = new ArrayList<DiagnosticoPacienteModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM diagnostico d INNER JOIN paciente p ON d.paciente_id = p.id WHERE d.estado = 'pendiente';";

        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DiagnosticoPacienteModel _diagnostico = new DiagnosticoPacienteModel();
                    _diagnostico.setId(rs.getLong("d.id"));
                    _diagnostico.setPaciente_id(rs.getLong("d.paciente_id"));
                    _diagnostico.setFecha(rs.getDate("d.fecha"));
                    _diagnostico.setUrgente(rs.getString("d.urgente"));
                    _diagnostico.setUser_facultativo_id(rs.getLong("d.user_facultativo_id"));
                    _diagnostico.setEstado(rs.getString("d.estado"));
                    _diagnostico.setUser_admin_id(rs.getLong("d.user_admin_id"));
                    _diagnostico.setDni(rs.getString("p.dni"));
                    _diagnostico.setNombre(rs.getString("p.nombre"));
                    _diagnostico.setApellidos(rs.getString("p.apellidos"));
                    _diagnostico.setTelefono(rs.getLong("p.telefono"));
                    _diagnostico.setCorreo(rs.getString("p.correo"));
                    _diagnostico.setFecha_nacimiento(rs.getDate("p.fecha_nacimiento"));
                    arrayDataModel.add(_diagnostico);
                }
            }
            Gson gson = new Gson();
            reply = gson.toJson(arrayDataModel);
        } catch (Exception ex) {
            ssLog.info("DiagnosticoDAO.java GetAll(Diagnostico() Exception: " + ex.getMessage());
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