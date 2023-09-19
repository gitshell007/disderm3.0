package com.disderm.service;

import com.disderm.model.PacienteModel;
import com.disderm.dao.PacienteDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.google.gson.Gson;

import java.util.*;
import javax.ws.rs.*;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.sql.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.MySQLCodec;
import com.disderm.utils.FFecha;
import com.disderm.utils.FFecha.*;

@Path("paciente")
public class PacienteService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
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

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllPaciente(@PathParam("user_id") Long user_id) {
        PacienteDAO pacienteDao = new PacienteDAO();
        String reply = "";
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllPacienteByUsername(@PathParam("username") String username) {
        PacienteDAO pacienteDao = new PacienteDAO();
        String reply = "";
        return reply;
    }

    @POST
    @Path("/update")
    @Produces("application/json")
    public boolean serviceUpdatePaciente(
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
        boolean update = true;
        CustomServiceAnswerModel csam_paciente = new CustomServiceAnswerModel();
        Gson gson_paciente = new Gson();
        csam_paciente.setResult(String.valueOf(pacienteDao.updatePacientepaciente(_PacienteModel)));
        reply = gson_paciente.toJson(csam_paciente);
        if (((csam_paciente.getResult()).equals("")) || ((csam_paciente.getResult()) == null) || ((csam_paciente.getResult()).equals("0"))) {
            update = false;
        }
        return update;
    }
    @GET
    @Path("/getCustomDataTable_pacientes")
    @Produces("application/json")
    public String serviceCustomDataTable_pacientes(
            @Context UriInfo uriInfo,
            @DefaultValue("20") @QueryParam("length") int length,
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        PacienteDAO pacienteDao = new PacienteDAO();
        List<String> fields_to_get = new ArrayList<>();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();

        String query = uriInfo.getRequestUri().getQuery();
        System.out.println("query = " + query);
        String filter_value = "";
        try {
            filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        } catch (Exception e) {
            System.out.println("ereror al coger parametro " + e.getMessage());
        }
        System.out.println("filtro = " + filter_value);
        fields_to_get.add("id");
        fields_to_get.add("identificador_pac");
        fields_to_get.add("dni");
        fields_to_get.add("nombre");
        fields_to_get.add("apellidos");
        fields_to_get.add("telefono");
        fields_to_get.add("correo");
        fields_to_get.add("fecha_nacimiento");
        String draw = "10";
        String filter_sql = "";
        try {
            draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");
        } catch (Exception e) {
            System.out.println("error al coger parametro 2" + e.getMessage());
        }
        if (!filter_value.isEmpty()) {
            System.out.println("cogemos filtro");
            filter_sql = " WHERE " + divideFiltroPalabras(filter_value, "identificador_pac")
                    + " OR " + divideFiltroPalabras(filter_value, "dni")
                    + " OR " + divideFiltroPalabras(filter_value, "nombre")
                    + " OR " + divideFiltroPalabras(filter_value, "apellidos")
                    + " OR " + divideFiltroPalabras(filter_value, "telefono")
                    + " OR " + divideFiltroPalabras(filter_value, "correo")
                    + " OR " + divideFiltroPalabras(filter_value, "fecha_nacimiento");
        }

        System.out.println("antes de reply");
        reply =
                pacienteDao.getCustomDataTablePacientes(
                        start, length, Integer.parseInt(draw), filter_sql, fields_to_get);
        return reply;
    }

    public String divideFiltroPalabras(String filtro, String campo) {
        String finalW = "";

        if (filtro.length() > 0) {
            String[] auxW = filtro.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            finalW = "(" + campo + " LIKE '%" + auxW[0].replace("\"", "") + "%'";
            for (int i = 1; i < Arrays.stream(auxW).count(); i++) {
                String aux = auxW[i].replace("\"", "");
                if (aux.length() > 0) {
                    finalW = finalW + " OR " + campo + " LIKE '%" + aux + "%'";
                }
            }
            finalW = finalW + ")";
        }
        return finalW;
    }
}