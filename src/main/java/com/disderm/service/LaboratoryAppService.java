package com.disderm.service;

import com.disderm.dao.LaboratoryDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.LaboratoryModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.Date;

;

@Path("laboratoryapp")
public class LaboratoryAppService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertLaboratory(
            @FormParam("id") Long id,
            @FormParam("description") String description)
        {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        LaboratoryDAO laboratoryDao = new LaboratoryDAO();
        LaboratoryModel _LaboratoryModel = new LaboratoryModel();
        _LaboratoryModel.set_id(id);
        _LaboratoryModel.set_description(description);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(laboratoryDao.insertLaboratory(_LaboratoryModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllLaboratory(@PathParam("user_id") Long user_id) {
        LaboratoryDAO laboratoryDao = new LaboratoryDAO();
        String reply = "";
        reply = laboratoryDao.getAllLaboratoryByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllLaboratoryByUsername(@PathParam("username") String username) {
        LaboratoryDAO laboratoryDao = new LaboratoryDAO();
        String reply = "";
        reply = laboratoryDao.getAllLaboratoryByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapLaboratory() {
        LaboratoryDAO laboratoryDao = new LaboratoryDAO();
        String reply = "";
        reply = laboratoryDao.getAllSelectLaboratory();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableLaboratory(){
        LaboratoryDAO laboratoryDao = new LaboratoryDAO();
        String reply = "";
        reply = laboratoryDao.getAllLaboratory();
        return reply;
    }
}