package com.disderm.service;

import com.disderm.model.ImportsourceModel;
import com.disderm.dao.ImportsourceDAO;
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

@Path("import_source")
public class ImportsourceService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertImportsource(
            @FormParam("id") Long id,
            @FormParam("description") String description) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        ImportsourceDAO import_sourceDao = new ImportsourceDAO();
        ImportsourceModel _ImportsourceModel = new ImportsourceModel();
        _ImportsourceModel.set_id(id);
        _ImportsourceModel.set_description(description);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(import_sourceDao.insertImportsource(_ImportsourceModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllImportsource(@PathParam("user_id") Long user_id) {
        ImportsourceDAO import_sourceDao = new ImportsourceDAO();
        String reply = "";
        reply = import_sourceDao.getAllImportsourceByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllImportsourceByUsername(@PathParam("username") String username) {
        ImportsourceDAO import_sourceDao = new ImportsourceDAO();
        String reply = "";
        reply = import_sourceDao.getAllImportsourceByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapImportsource() {
        ImportsourceDAO import_sourceDao = new ImportsourceDAO();
        String reply = "";
        reply = import_sourceDao.getAllSelectImportsource();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableImportsource(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        ImportsourceDAO import_sourceDao = new ImportsourceDAO();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();
        String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

        String query = uriInfo.getRequestUri().getQuery();
        String filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        String filter_sql = "";
        if (!filter_value.isEmpty()) {
            filter_sql = "WHERE CAST(import_source_id as CHAR) LIKE '%" + filter_value + "%' limit ?,?";
        }
        reply = import_sourceDao.getAllDataTableImportsource(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String theKey = (String)it.next();
            //System.out.println(theKey);

        }*/
}