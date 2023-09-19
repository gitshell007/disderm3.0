package com.disderm.service;

import com.disderm.dao.DiagnosticoDAO;
import com.disderm.dao.PacienteDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.DiagnosticoModel;
import com.disderm.model.PacienteModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.Date;

@Path("diagnosticoapp")
public class DiagnosticoAppService {

    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insertPaciente")
    @Produces("application/json")
    public boolean serviceInsertPaciente(
            @FormParam("id") Long id,
            @FormParam("identificador_pac") Long identificador_pac,
            @FormParam("dni") String dni,
            @FormParam("nombre") String nombre,
            @FormParam("apellidos") String apellidos,
            @FormParam("telefono") String telefono,
            @FormParam("correo") String correo,
            @FormParam("fecha_nacimiento") Date fecha_nacimiento) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        PacienteDAO pacienteDao = new PacienteDAO();
        PacienteModel _PacienteModel = new PacienteModel();
        _PacienteModel.set_id(id);
        _PacienteModel.set_identificador_pac(identificador_pac);
        _PacienteModel.set_dni(dni);
        _PacienteModel.set_nombre(nombre);
        _PacienteModel.set_apellidos(apellidos);
        _PacienteModel.set_telefono(telefono);
        _PacienteModel.set_correo(correo);
        _PacienteModel.set_fecha_nacimiento(fecha_nacimiento);
        String reply = "";
        Long insert_id = 0L;
        boolean insert = true;
        CustomServiceAnswerModel csam_paciente = new CustomServiceAnswerModel();
        Gson gson_paciente = new Gson();
        csam_paciente.setResult(String.valueOf(pacienteDao.insertPacientepaciente(_PacienteModel)));
        reply = gson_paciente.toJson(csam_paciente);
        if (((csam_paciente.getResult()).equals("")) || ((csam_paciente.getResult()) == null) || ((csam_paciente.getResult()).equals("0"))) {
            insert = false;
        }
        return insert;
    }

    @POST
    @Path("/insertDiagnostico")
    @Produces("application/json")
    public boolean serviceInsertDiagnostico(
            @FormParam("id") Long id,
            @FormParam("paciente_id") Long paciente_id,
            @FormParam("fecha") Date fecha,
            @FormParam("patologia") String patologia,
            @FormParam("urgente") String urgente,
            @FormParam("observaciones") String observaciones,
            @FormParam("estado") String estado,
            @FormParam("user_facultativo_id") Long user_facultativo_id,
            @FormParam("user_admin_id") Long user_admin_id,
            @FormParam("user_primaria_id") Long user_primaria_id,
            @FormParam("valoracion_dermatologo") String valoracion_dermatologo) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        DiagnosticoDAO diagnosticoDao = new DiagnosticoDAO();
        DiagnosticoModel _DiagnosticoModel = new DiagnosticoModel();
        _DiagnosticoModel.set_id(id);
        _DiagnosticoModel.set_paciente_id(paciente_id);
        _DiagnosticoModel.set_fecha(fecha);
        _DiagnosticoModel.set_patologia(patologia);
        _DiagnosticoModel.set_urgente(urgente);
        _DiagnosticoModel.set_observaciones(observaciones);
        _DiagnosticoModel.set_estado(estado);
        _DiagnosticoModel.set_user_facultativo_id(user_facultativo_id);
        _DiagnosticoModel.set_user_admin_id(user_admin_id);
        _DiagnosticoModel.set_user_primaria_id(user_primaria_id);
        _DiagnosticoModel.set_valoracion_dermatologo(valoracion_dermatologo);
        String reply = "";
        Long insert_id = 0L;
        boolean insert = true;
        CustomServiceAnswerModel csam_diagnostico = new CustomServiceAnswerModel();
        Gson gson_diagnostico = new Gson();
        csam_diagnostico.setResult(String.valueOf(diagnosticoDao.insertDiagnosticodiagnostico(_DiagnosticoModel)));
        reply = gson_diagnostico.toJson(csam_diagnostico);
        if (((csam_diagnostico.getResult()).equals("")) || ((csam_diagnostico.getResult()) == null) || ((csam_diagnostico.getResult()).equals("0"))) {
            insert = false;
        }
        return insert;
    }

    @POST
    @Path("/searchDNI")
    public boolean  searchDNI(String dni){
        PacienteDAO pacienteDAO = new PacienteDAO();
        dni = dni.replace("\"","");
        System.out.println(dni);
        String resultado = String.valueOf(pacienteDAO.searchDNI(dni));
        return !resultado.equals("[]");
    }

    @GET
    @Path("/getAllDataTableDiagnosticoIncidencia")
    @Produces("application/json")
    public String serviceGetAllDataTableDiagnosticoIncidencia(){
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
        String reply = "";
        reply = diagnosticoDAO.getAllDiagnosticoIncidencia();
        return reply;
    }
    @GET
    @Path("/getAllDataTablePaciente")
    @Produces("application/json")
    public String serviceGetAllDataTablePacientes(){
        PacienteDAO pacienteDAO = new PacienteDAO();
        String reply = "";
        reply = pacienteDAO.getAllPacientes();
        return reply;
    }

}