package com.disderm.service;

import com.disderm.dao.DisdermusersDAO;
import com.disderm.model.DisdermusersModel;
import com.disderm.model.CustomServiceAnswerModel;
import com.google.gson.Gson;

import java.util.*;;import javax.ws.rs.*;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.sql.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("disderm_users")
public class DisdermusersService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertDisdermusers(
            @FormParam("id") Long id,
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("first_name") String first_name,
            @FormParam("last_name") String last_name,
            @FormParam("visible_name") String visible_name,
            @FormParam("user_type_id") int user_type_id,
            @FormParam("profile_image") String profile_image,
            @FormParam("enabled") int enabled,
            @FormParam("phone_mobile") String phone_mobile,
            @FormParam("localidad") String localidad,
            @FormParam("provincia") String provincia,
            @FormParam("cp") String cp,
            @FormParam("app_platform") String app_platform,
            @FormParam("app_device_model") String app_device_model,
            @FormParam("app_device_token") String app_device_token,
            @FormParam("fecha_alta_sistema") Date fecha_alta_sistema,
            @FormParam("fecha_ultimo_acceso") Date fecha_ultimo_acceso,
            @FormParam("message") String message) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        DisdermusersDAO disderm_usersDao = new DisdermusersDAO();
        DisdermusersModel _DisdermusersModel = new DisdermusersModel();
        _DisdermusersModel.set_id(id);
        _DisdermusersModel.set_username(username);
        _DisdermusersModel.set_password(password);
        _DisdermusersModel.set_first_name(first_name);
        _DisdermusersModel.set_last_name(last_name);
        _DisdermusersModel.set_visible_name(visible_name);
        _DisdermusersModel.set_user_type_id(user_type_id);
        _DisdermusersModel.set_profile_image(profile_image);
        _DisdermusersModel.set_enabled(enabled);
        _DisdermusersModel.set_phone_mobile(phone_mobile);
        _DisdermusersModel.set_localidad(localidad);
        _DisdermusersModel.set_provincia(provincia);
        _DisdermusersModel.set_cp(cp);
        _DisdermusersModel.set_app_platform(app_platform);
        _DisdermusersModel.set_app_device_model(app_device_model);
        _DisdermusersModel.set_app_device_token(app_device_token);
        _DisdermusersModel.set_fecha_alta_sistema(fecha_alta_sistema);
        _DisdermusersModel.set_fecha_ultimo_acceso(fecha_ultimo_acceso);
        _DisdermusersModel.set_message(message);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(disderm_usersDao.insertDisdermusers(_DisdermusersModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllDisdermusers(@PathParam("user_id") Long user_id) {
        DisdermusersDAO disderm_usersDao = new DisdermusersDAO();
        String reply = "";
        reply = disderm_usersDao.getAllDisdermusersByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllDisdermusersByUsername(@PathParam("username") String username) {
        DisdermusersDAO disderm_usersDao = new DisdermusersDAO();
        String reply = "";
        reply = disderm_usersDao.getAllDisdermusersByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapDisdermusers() {
        DisdermusersDAO disderm_usersDao = new DisdermusersDAO();
        String reply = "";
        reply = disderm_usersDao.getAllSelectDisdermusers();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableDisdermusers(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        DisdermusersDAO disderm_usersDao = new DisdermusersDAO();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();
        String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

        String query = uriInfo.getRequestUri().getQuery();
        String filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        String filter_sql = "";
        if (!filter_value.isEmpty()) {
            filter_sql = "WHERE CAST(disderm_users_id as CHAR) LIKE '%" + filter_value + "%' limit ?,?";
        }
        reply = disderm_usersDao.getAllDataTableDisdermusers(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String theKey = (String)it.next();
            //System.out.println(theKey);

        }*/
}