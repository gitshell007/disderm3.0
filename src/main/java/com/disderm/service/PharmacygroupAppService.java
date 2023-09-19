package com.disderm.service;

import com.disderm.dao.PharmacygroupDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.PharmacygroupModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.Date;

;

@Path("pharmacy_groupapp")
public class PharmacygroupAppService {
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
    public String serviceGetAllDataTablePharmacygroup() {
        PharmacygroupDAO pharmacy_groupDao = new PharmacygroupDAO();
        String reply = "";
        reply = pharmacy_groupDao.getAllPharmacygroup();
        return reply;
    }
}