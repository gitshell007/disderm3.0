package com.disderm.dao;

import com.disderm.model.DiagnosticoPacienteModel;
import com.disderm.model.PacienteModel;
import com.disderm.model.DropdownModel;
import com.disderm.conn.DBConnection;
import com.disderm.utils.SSLog;
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

public class PacienteDAO {
    private final static SSLog ssLog = new SSLog();

    public Long insertPacientepaciente(PacienteModel _pacientepaciente) {
        Long id = 0L;
        String sql = " INSERT INTO paciente ( identificador_pac, dni, " +
                " nombre, apellidos, telefono, correo, " +
                " fecha_nacimiento) " +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setLong(1, _pacientepaciente.get_identificador_pac());
                ps.setString(2, _pacientepaciente.get_dni());
                ps.setString(3, _pacientepaciente.get_nombre());
                ps.setString(4, _pacientepaciente.get_apellidos());
                ps.setString(5, _pacientepaciente.get_telefono());
                ps.setString(6, _pacientepaciente.get_correo());
                ps.setDate(7, FFecha.convertir(_pacientepaciente.get_fecha_nacimiento()));
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

    public Long updatePacientepaciente(PacienteModel _pacientepaciente) {
        Long id = 0L;
        String sql = " UPDATE paciente SET  identificador_pac = ?, dni = ?, " +
                " nombre = ?, apellidos = ?, telefono = ?, correo = ?, " +
                " fecha_nacimiento = ? WHERE id = ?";
        try {
            java.sql.Connection conn = null;
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                ps.setLong(1, _pacientepaciente.get_identificador_pac());
                ps.setString(2, _pacientepaciente.get_dni());
                ps.setString(3, _pacientepaciente.get_nombre());
                ps.setString(4, _pacientepaciente.get_apellidos());
                ps.setString(5, _pacientepaciente.get_telefono());
                ps.setString(6, _pacientepaciente.get_correo());
                ps.setDate(7, FFecha.convertir(_pacientepaciente.get_fecha_nacimiento()));
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

    public String getAllPacientes() {
        Long id = 0L;
        String reply = "";
        ArrayList<PacienteModel> arrayDataModel = new ArrayList<PacienteModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM paciente;";

        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PacienteModel _paciente = new PacienteModel();
                    _paciente.set_id(rs.getLong("id"));
                    _paciente.set_identificador_pac(rs.getLong("identificador_pac"));
                    _paciente.set_dni(rs.getString("dni"));
                    _paciente.set_nombre(rs.getString("nombre"));
                    _paciente.set_apellidos(rs.getString("apellidos"));
                    _paciente.set_telefono(rs.getString("telefono"));
                    _paciente.set_correo(rs.getString("correo"));
                    _paciente.set_fecha_nacimiento(rs.getDate("fecha_nacimiento"));
                    arrayDataModel.add(_paciente);
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

    public String getCustomDataTablePacientes(int start, int length, int draw, String filter_sql, List<String> fields_to_show) {
        Long id = 0L;
        String reply = "";
        JsonObject jMembers = new JsonObject();
        JsonArray data = new JsonArray();
        JsonArray row = new JsonArray();
        ArrayList<PacienteModel> arrayDataModel = new ArrayList<PacienteModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        System.out.println("Entramos en getcustomdatatablepacientes");
        long numero_filas_total_pacientes = getNumeroFilasPacientes();
        long numero_filas_filtro_pacientes = 0L;
        if (!filter_sql.isEmpty()) {
            numero_filas_filtro_pacientes =
                    getNumeroFilasFiltroPacientes(
                            "SELECT count(id) as total FROM paciente " + filter_sql);
        } else {
            numero_filas_filtro_pacientes = numero_filas_total_pacientes;
        }
        String sql = "SELECT * FROM paciente " + filter_sql + " limit ?,?";
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
                    row.add(rs.getLong("identificador_pac"));
                    row.add(rs.getString("dni"));
                    row.add(rs.getString("nombre"));
                    row.add(rs.getString("apellidos"));
                    row.add(rs.getLong("telefono"));
                    row.add(rs.getString("correo"));
                    row.add(String.valueOf(rs.getDate("fecha_nacimiento")));
                    data.add(row);
                }
            }

            jMembers.add("data", data);
            jMembers.addProperty("draw", draw);
            jMembers.addProperty("recordsTotal", numero_filas_total_pacientes);
            jMembers.addProperty("recordsFiltered", numero_filas_filtro_pacientes);

            reply = jMembers.toString();

        } catch (Exception ex) {
            ssLog.info("PacientesDAO.java getCustomDataTablePacientes() Exception: " + ex.getMessage());
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

    public PacienteModel getPacienteModelByID(Long _id) {
        PacienteModel _paciente = new PacienteModel();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            Timestamp ts = new Timestamp(new Date().getTime());
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        _paciente.set_id(rs.getLong("id"));
                        _paciente.set_identificador_pac(rs.getLong("identificador_pac"));
                        _paciente.set_dni(rs.getString("dni"));
                        _paciente.set_nombre(rs.getString("nombre"));
                        _paciente.set_apellidos(rs.getString("apellidos"));
                        _paciente.set_telefono(rs.getString("telefono"));
                        _paciente.set_correo(rs.getString("correo"));
                        _paciente.set_fecha_nacimiento(rs.getDate("fecha_nacimiento"));
                    }
                }
            }
        } catch (Exception ex) {
            ssLog.info("PacienteDAO.java GetPaciente() Exception: " + ex.getMessage());
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
            return _paciente;
        }

    }

    public long getNumeroFilasFiltroPacientes(String _sql) {
        long numero_filas_pacientes = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(_sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_pacientes = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PacientesDAO.java getNumeroFilasFiltroPacientes() Exception:  AQUI" + ex.getMessage());
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
        return numero_filas_pacientes;
    }

    public long getNumeroFilasPacientes() {
        long numero_filas_pacientes = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(id) as total from paciente";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numero_filas_pacientes = rs.getLong("total");
                }
            }
        } catch (Exception ex) {
            ssLog.info("PacientesDAO.java getNumeroFilas(Pacientes() Exception: " + ex.getMessage());
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
        return numero_filas_pacientes;
    }

    public boolean deletePacientesByID(Long _id) {
        boolean success = false;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM paciente WHERE id = ?";
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setLong(1, _id);
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ssLog.info("PacienteDAO.java Delete(PacientesByID() Exception: " + ex.getMessage());
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

    public String searchDNI(String DNI) {
        Long id = 0L;
        String reply = "";
        ArrayList<PacienteModel> arrayDataModel = new ArrayList<PacienteModel>();
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM paciente where dni='"+DNI+"';";

        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PacienteModel _paciente = new PacienteModel();
                    _paciente.set_id(rs.getLong("id"));
                    _paciente.set_identificador_pac(rs.getLong("identificador_pac"));
                    _paciente.set_dni(rs.getString("dni"));
                    _paciente.set_nombre(rs.getString("nombre"));
                    _paciente.set_apellidos(rs.getString("apellidos"));
                    _paciente.set_telefono(rs.getString("telefono"));
                    _paciente.set_correo(rs.getString("correo"));
                    _paciente.set_fecha_nacimiento(rs.getDate("fecha_nacimiento"));
                    arrayDataModel.add(_paciente);
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