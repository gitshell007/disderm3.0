package com.disderm.service;

import com.disderm.model.PharmacistModel;
import com.disderm.dao.PharmacistDAO;
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

@Path("pharmacist")
public class PharmacistService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public boolean serviceInsertPharmacist(
            @FormParam("description") String description) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        PharmacistDAO pharmacistDao = new PharmacistDAO();
        PharmacistModel _PharmacistModel = new PharmacistModel();
        _PharmacistModel.set_description(description);
        String reply = "";
        Long insert_id = 0L;
        boolean insert = true;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(pharmacistDao.insertPharmacist(_PharmacistModel)));
        if ((csam.equals("")) || (csam == null)) {
            insert = false;
        }
        reply = gson.toJson(csam);
        return insert;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllPharmacist(@PathParam("user_id") Long user_id) {
        PharmacistDAO pharmacistDao = new PharmacistDAO();
        String reply = "";
        reply = pharmacistDao.getAllPharmacistByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllPharmacistByUsername(@PathParam("username") String username) {
        PharmacistDAO pharmacistDao = new PharmacistDAO();
        String reply = "";
        reply = pharmacistDao.getAllPharmacistByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapPharmacist() {
        PharmacistDAO pharmacistDao = new PharmacistDAO();
        String reply = "";
        reply = pharmacistDao.getAllSelectPharmacist();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTablePharmacist(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        PharmacistDAO pharmacistDao = new PharmacistDAO();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();
        String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

        String query = uriInfo.getRequestUri().getQuery();
        String filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        String filter_sql = "";
        if (!filter_value.isEmpty()) {
            filter_sql = "WHERE description LIKE '%" + filter_value + "%'";
        }
        reply = pharmacistDao.getAllDataTablePharmacist(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            StringeKey = (String)it.next();
            //System.out.println(theKey);

        }*/
}