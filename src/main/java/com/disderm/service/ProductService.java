package com.disderm.service;

import com.disderm.model.ProductModel;
import com.disderm.dao.ProductDAO;
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

@Path("product")
public class ProductService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public boolean serviceInsertProduct(
            @FormParam("category_id") int category_id,
            @FormParam("description") String description,
            @FormParam("import_source_id") int import_source_id,
            @FormParam("cn") int cn) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        ProductDAO productDao = new ProductDAO();
        ProductModel _ProductModel = new ProductModel();
        _ProductModel.set_category_id(category_id);
        _ProductModel.set_description(description);
        _ProductModel.set_import_source_id(import_source_id);
        _ProductModel.set_cn(cn);
        String reply = "";
        Long insert_id = 0L;
        boolean insert = true;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(productDao.insertProduct(_ProductModel)));
        if ((csam.equals("")) || (csam == null)) {
            insert = false;
        }
        reply = gson.toJson(csam);
        return insert;
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
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllProductByUsername(@PathParam("username") String username) {
        ProductDAO productDao = new ProductDAO();
        String reply = "";
        reply = productDao.getAllProductByUsername(username);
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
    public String serviceGetAllDataTableProduct(
            @Context UriInfo uriInfo,
            @DefaultValue("20") @QueryParam("length") int length,
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        ProductDAO productDao = new ProductDAO();
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
        reply = productDao.getAllDataTableProduct(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String they = (String)it.next();
            //System.out.println(theKey);

        }*/
}