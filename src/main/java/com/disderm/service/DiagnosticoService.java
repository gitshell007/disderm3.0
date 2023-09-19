package com.disderm.service;

import com.disderm.model.DiagnosticoModel;
import com.disderm.dao.DiagnosticoDAO;
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

@Path("diagnostico")
public class DiagnosticoService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
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

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllDiagnostico(@PathParam("user_id") Long user_id) {
        DiagnosticoDAO diagnosticoDao = new DiagnosticoDAO();
        String reply = "";
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllDiagnosticoByUsername(@PathParam("username") String username) {
        DiagnosticoDAO diagnosticoDao = new DiagnosticoDAO();
        String reply = "";
        return reply;
    }

    @POST
    @Path("/update")
    @Produces("application/json")
    public boolean serviceUpdateDiagnostico(
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
        boolean update = true;
        CustomServiceAnswerModel csam_diagnostico = new CustomServiceAnswerModel();
        Gson gson_diagnostico = new Gson();
        csam_diagnostico.setResult(String.valueOf(diagnosticoDao.updateDiagnosticodiagnostico(_DiagnosticoModel)));
        reply = gson_diagnostico.toJson(csam_diagnostico);
        if (((csam_diagnostico.getResult()).equals("")) || ((csam_diagnostico.getResult()) == null) || ((csam_diagnostico.getResult()).equals("0"))) {
            update = false;
        }
        return update;
    }

    @GET
    @Path("/getCustomDataTable_diagnosticos_pendientes")
    @Produces("application/json")
    public String serviceCustomDataTable_diagnosticos_pendientes(
            @Context UriInfo uriInfo,
            @DefaultValue("20") @QueryParam("length") int length,
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        DiagnosticoDAO diagnosticoDao = new DiagnosticoDAO();
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
        fields_to_get.add("id");
        fields_to_get.add("paciente_id");
        fields_to_get.add("fecha");
        fields_to_get.add("urgente");
        fields_to_get.add("dni");
        fields_to_get.add("nombre");
        fields_to_get.add("apellidos");
        fields_to_get.add("telefono");
        fields_to_get.add("correo");
        fields_to_get.add("fecha_nacimiento");
        fields_to_get.add("user_facultativo_id");
        fields_to_get.add("estado");
        String draw = "10";
        String filter_sql = "";
        try {
            draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");
        } catch (Exception e) {
            System.out.println("error al coger parametro 2" + e.getMessage());
        }
        if (!filter_value.isEmpty()) {
            System.out.println("cogemos filtro");
            filter_sql = " WHERE " + divideFiltroPalabras(filter_value, "id_paciente")
                    + " OR " + divideFiltroPalabras(filter_value, "fecha")
                    + " OR " + divideFiltroPalabras(filter_value, "urgente")
                    + " OR " + divideFiltroPalabras(filter_value, "dni")
                    + " OR " + divideFiltroPalabras(filter_value, "nombre")
                    + " OR " + divideFiltroPalabras(filter_value, "apellidos")
                    + " OR " + divideFiltroPalabras(filter_value, "telefono")
                    + " OR " + divideFiltroPalabras(filter_value, "correo")
                    + " OR " + divideFiltroPalabras(filter_value, "fecha_nacimiento")
                    + " OR " + divideFiltroPalabras(filter_value, "facultativo")
                    + " OR " + divideFiltroPalabras(filter_value, "estado");
        }


        reply =
                diagnosticoDao.getCustomDataTableDiagnosticoPendiente(
                        start, length, Integer.parseInt(draw), filter_sql, fields_to_get);
        return reply;
    }
    @GET
    @Path("/getCustomDataTable_diagnosticos_finalizados")
    @Produces("application/json")
    public String serviceCustomDataTable_diagnosticos_finalizados(
            @Context UriInfo uriInfo,
            @DefaultValue("20") @QueryParam("length") int length,
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        DiagnosticoDAO diagnosticoDao = new DiagnosticoDAO();
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
        fields_to_get.add("id");
        fields_to_get.add("paciente_id");
        fields_to_get.add("fecha");
        fields_to_get.add("urgente");
        fields_to_get.add("dni");
        fields_to_get.add("nombre");
        fields_to_get.add("apellidos");
        fields_to_get.add("telefono");
        fields_to_get.add("correo");
        fields_to_get.add("fecha_nacimiento");
        fields_to_get.add("facultativo");
        fields_to_get.add("estado");
        String draw = "10";
        String filter_sql = "";
        try {
            draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");
        } catch (Exception e) {
            System.out.println("error al coger parametro 2" + e.getMessage());
        }
        if (!filter_value.isEmpty()) {
            System.out.println("cogemos filtro");
            filter_sql = " WHERE " + divideFiltroPalabras(filter_value, "id_paciente")
                    + " OR " + divideFiltroPalabras(filter_value, "fecha")
                    + " OR " + divideFiltroPalabras(filter_value, "urgente")
                    + " OR " + divideFiltroPalabras(filter_value, "dni")
                    + " OR " + divideFiltroPalabras(filter_value, "nombre")
                    + " OR " + divideFiltroPalabras(filter_value, "apellidos")
                    + " OR " + divideFiltroPalabras(filter_value, "telefono")
                    + " OR " + divideFiltroPalabras(filter_value, "correo")
                    + " OR " + divideFiltroPalabras(filter_value, "fecha_nacimiento")
                    + " OR " + divideFiltroPalabras(filter_value, "facultativo")
                    + " OR " + divideFiltroPalabras(filter_value, "estado");
        }


        reply =
                diagnosticoDao.getCustomDataTableDiagnosticoFinalizado(
                        start, length, Integer.parseInt(draw), filter_sql, fields_to_get);
        return reply;
    }
    @GET
    @Path("/getCustomDataTable_diagnosticos_incidencias")
    @Produces("application/json")
    public String serviceCustomDataTable_diagnosticos_incidencias(
            @Context UriInfo uriInfo,
            @DefaultValue("20") @QueryParam("length") int length,
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        DiagnosticoDAO diagnosticoDao = new DiagnosticoDAO();
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
        fields_to_get.add("id");
        fields_to_get.add("paciente_id");
        fields_to_get.add("fecha");
        fields_to_get.add("urgente");
        fields_to_get.add("dni");
        fields_to_get.add("nombre");
        fields_to_get.add("apellidos");
        fields_to_get.add("telefono");
        fields_to_get.add("correo");
        fields_to_get.add("fecha_nacimiento");
        fields_to_get.add("user_facultativo_id");
        fields_to_get.add("estado");
        String draw = "10";
        String filter_sql = "";
        try {
            draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");
        } catch (Exception e) {
            System.out.println("error al coger parametro 2" + e.getMessage());
        }
        if (!filter_value.isEmpty()) {
            System.out.println("cogemos filtro");
            filter_sql = " WHERE " + divideFiltroPalabras(filter_value, "id_paciente")
                    + " OR " + divideFiltroPalabras(filter_value, "fecha")
                    + " OR " + divideFiltroPalabras(filter_value, "urgente")
                    + " OR " + divideFiltroPalabras(filter_value, "dni")
                    + " OR " + divideFiltroPalabras(filter_value, "nombre")
                    + " OR " + divideFiltroPalabras(filter_value, "apellidos")
                    + " OR " + divideFiltroPalabras(filter_value, "telefono")
                    + " OR " + divideFiltroPalabras(filter_value, "correo")
                    + " OR " + divideFiltroPalabras(filter_value, "fecha_nacimiento")
                    + " OR " + divideFiltroPalabras(filter_value, "facultativo")
                    + " OR " + divideFiltroPalabras(filter_value, "estado");
        }


        reply =
                diagnosticoDao.getCustomDataTableDiagnosticoIncidencia(
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