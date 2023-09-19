package com.disderm.service;

import com.disderm.model.SalesModel;
import com.disderm.dao.SalesDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.google.gson.Gson;

import java.math.BigDecimal;
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

@Path("sales")
public class SalesService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public boolean serviceInsertSales(
            @FormParam("pharmacy_id") int pharmacy_id,
            @FormParam("description") String description,
            @FormParam("product_id") int product_id,
            @FormParam("upload_date") Date upload_date,
            @FormParam("sale_date") Date sale_date,
            @FormParam("units_number") int units_number,
            @FormParam("units_price") BigDecimal units_price,
            @FormParam("import_source_id") int import_source_id,
            @FormParam("payment_type_id") int payment_type_id,
            @FormParam("sale_type_id") int sale_type_id) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        SalesDAO salesDao = new SalesDAO();
        SalesModel _SalesModel = new SalesModel();
        _SalesModel.set_pharmacy_id(pharmacy_id);
        _SalesModel.setDescription(description);
        _SalesModel.set_product_id(product_id);
        _SalesModel.set_upload_date(upload_date);
        _SalesModel.set_sale_date(sale_date);
        _SalesModel.set_units_number(units_number);
        _SalesModel.set_units_price(units_price);
        _SalesModel.set_import_source_id(import_source_id);
        _SalesModel.set_payment_type_id(payment_type_id);
        _SalesModel.set_sale_type_id(sale_type_id);
        String reply = "";
        Long insert_id = 0L;
        boolean insert = true;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(salesDao.insertSales(_SalesModel)));
        if ((csam.equals("")) || (csam == null)) {
            insert = false;
        }
        reply = gson.toJson(csam);
        return insert;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllSales(@PathParam("user_id") Long user_id) {
        SalesDAO salesDao = new SalesDAO();
        String reply = "";
        reply = salesDao.getAllSalesByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllSalesByUsername(@PathParam("username") String username) {
        SalesDAO salesDao = new SalesDAO();
        String reply = "";
        reply = salesDao.getAllSalesByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapSales() {
        SalesDAO salesDao = new SalesDAO();
        String reply = "";
        reply = salesDao.getAllSelectSales();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableSales(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        SalesDAO salesDao = new SalesDAO();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();
        String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

        String query = uriInfo.getRequestUri().getQuery();
        String filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        String filter_sql = "";
        //TODO FILTRO
        if (!filter_value.isEmpty()) {
            filter_sql = "WHERE units_number LIKE '%" + filter_value + "%'";
        }
        reply = salesDao.getAllDataTableSales(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String theKey = (String)it.next();
            //System.out.println(theKey);

        }*/
}