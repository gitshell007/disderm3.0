package com.disderm.service;

import com.disderm.dao.ProductcategoryDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.ProductcategoryModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.Date;

;

@Path("product_categoryapp")
public class ProductcategoryAppService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertProductcategory(
            @FormParam("id") Long id,
            @FormParam("description") String description) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        ProductcategoryDAO product_categoryDao = new ProductcategoryDAO();
        ProductcategoryModel _ProductcategoryModel = new ProductcategoryModel();
        _ProductcategoryModel.set_id(id);
        _ProductcategoryModel.set_description(description);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(product_categoryDao.insertProductcategory(_ProductcategoryModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllProductcategory(@PathParam("user_id") Long user_id) {
        ProductcategoryDAO product_categoryDao = new ProductcategoryDAO();
        String reply = "";
        reply = product_categoryDao.getAllProductcategoryByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllProductcategoryByUsername(@PathParam("username") String username) {
        ProductcategoryDAO product_categoryDao = new ProductcategoryDAO();
        String reply = "";
        reply = product_categoryDao.getAllProductcategoryByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapProductcategory() {
        ProductcategoryDAO product_categoryDao = new ProductcategoryDAO();
        String reply = "";
        reply = product_categoryDao.getAllSelectProductcategory();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableProductcategory() {
        ProductcategoryDAO product_categoryDao = new ProductcategoryDAO();
        String reply = "";
        reply = product_categoryDao.getAllProductcategory();
        return reply;
    }
}