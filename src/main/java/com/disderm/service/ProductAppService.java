package com.disderm.service;

import com.disderm.dao.ProductDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.ProductModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.Date;

@Path("productapp")
public class ProductAppService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertProduct(
            @FormParam("id") Long id,
            @FormParam("category_id") int category_id,
            @FormParam("description") String description,
            @FormParam("import_source_id") int import_source_id,
            @FormParam("cn") int cn) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        ProductDAO productDao = new ProductDAO();
        ProductModel _ProductModel = new ProductModel();
        _ProductModel.set_id(id);
        _ProductModel.set_category_id(category_id);
        _ProductModel.set_description(description);
        _ProductModel.set_import_source_id(import_source_id);
        _ProductModel.set_cn(cn);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(productDao.insertProduct(_ProductModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllProduct(@PathParam("user_id") Long user_id) {
        ProductDAO productDao = new ProductDAO();
        String reply = "";
        reply = productDao.getAllProductByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/username/{username}")
    @Produces("application/json")
    public String serviceGetAllProductByUsername(@PathParam("username") String _username) {
        ProductDAO productDao = new ProductDAO();
        String reply = "";
        reply = productDao.getAllProductByUsernameString(_username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapProduct() {
        ProductDAO productDao = new ProductDAO();
        String reply = "";
        reply = productDao.getAllSelectProduct();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableProduct(){
        ProductDAO productDao = new ProductDAO();
        String reply = "";
        reply = productDao.getAllProduct();
        return reply;
    }
}