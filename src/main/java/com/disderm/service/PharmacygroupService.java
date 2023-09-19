package com.disderm.service;

import com.disderm.model.PharmacygroupModel;
import com.disderm.dao.PharmacygroupDAO;
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

@Path("pharmacy_group")
public class PharmacygroupService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertPharmacygroup(
            @FormParam("id") Long id,
            @FormParam("pharmacy_id") int pharmacy_id,
            @FormParam("pharmacist_id") int pharmacist_id) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        PharmacygroupDAO pharmacy_groupDao = new PharmacygroupDAO();
        PharmacygroupModel _PharmacygroupModel = new PharmacygroupModel();
        _PharmacygroupModel.set_id(id);
        _PharmacygroupModel.set_pharmacy_id(pharmacy_id);
        _PharmacygroupModel.set_pharmacist_id(pharmacist_id);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(pharmacy_groupDao.insertPharmacygroup(_PharmacygroupModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllPharmacygroup(@PathParam("user_id") Long user_id) {
        PharmacygroupDAO pharmacy_groupDao = new PharmacygroupDAO();
        String reply = "";
        reply = pharmacy_groupDao.getAllPharmacygroupByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllPharmacygroupByUsername(@PathParam("username") String username) {
        PharmacygroupDAO pharmacy_groupDao = new PharmacygroupDAO();
        String reply = "";
        reply = pharmacy_groupDao.getAllPharmacygroupByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapPharmacygroup() {
        PharmacygroupDAO pharmacy_groupDao = new PharmacygroupDAO();
        String reply = "";
        reply = pharmacy_groupDao.getAllSelectPharmacygroup();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTablePharmacygroup(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        PharmacygroupDAO pharmacy_groupDao = new PharmacygroupDAO();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();
        String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

        String query = uriInfo.getRequestUri().getQuery();
        String filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        String filter_sql = "";
        if (!filter_value.isEmpty()) {
            filter_sql = "WHERE CAST(pharmacy_group_id as CHAR) LIKE '%" + filter_value + "%' limit ?,?";
        }
        reply = pharmacy_groupDao.getAllDataTablePharmacygroup(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String theKey = (String)it.next();
            //System.out.println(theKey);

        }*/
}