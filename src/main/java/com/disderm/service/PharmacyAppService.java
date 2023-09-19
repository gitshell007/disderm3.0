package com.disderm.service;

import com.disderm.dao.PharmacyDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.PharmacyModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.Date;

@Path("pharmacyapp")
public class PharmacyAppService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertPharmacy(
            @FormParam("id") Long id,
            @FormParam("description") String description) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        PharmacyDAO pharmacyDao = new PharmacyDAO();
        PharmacyModel _PharmacyModel = new PharmacyModel();
        _PharmacyModel.set_id(id);
        _PharmacyModel.set_description(description);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(pharmacyDao.insertPharmacy(_PharmacyModel)));
        reply = gson.toJson(csam);
        return reply;
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
    public String serviceGetAllDataTablePharmacy() {
        PharmacyDAO pharmacyDao = new PharmacyDAO();
        String reply = "";
        reply = pharmacyDao.getAllPharmacy();
        return reply;
    }
}