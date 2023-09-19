package com.disderm.service;

import com.disderm.model.PharmacyModel;
import com.disderm.dao.PharmacyDAO;
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

@Path("pharmacy")
public class PharmacyService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public boolean serviceInsertPharmacy(
            @FormParam("description") String description) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        PharmacyDAO pharmacyDao = new PharmacyDAO();
        PharmacyModel _PharmacyModel = new PharmacyModel();
        _PharmacyModel.set_description(description);
        String reply = "";
        Long insert_id = 0L;
        boolean insert = true;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(pharmacyDao.insertPharmacy(_PharmacyModel)));
        if ((csam.equals("")) || (csam == null)) {
            insert = false;
        }
        reply = gson.toJson(csam);

        return insert;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllPharmacy(@PathParam("user_id") Long user_id) {
        PharmacyDAO pharmacyDao = new PharmacyDAO();
        String reply = "";
        reply = pharmacyDao.getAllPharmacyByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllPharmacyByUsername(@PathParam("username") String username) {
        PharmacyDAO pharmacyDao = new PharmacyDAO();
        String reply = "";
        reply = pharmacyDao.getAllPharmacyByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapPharmacy() {
        PharmacyDAO pharmacyDao = new PharmacyDAO();
        String reply = "";
        reply = pharmacyDao.getAllSelectPharmacy();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTablePharmacy(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        PharmacyDAO pharmacyDao = new PharmacyDAO();
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
        reply = pharmacyDao.getAllDataTablePharmacy(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String they = (String)it.next();
            //System.out.println(theKey);

        }*/
}