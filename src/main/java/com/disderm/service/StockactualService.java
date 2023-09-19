package com.disderm.service;

import com.disderm.model.StockactualModel;
import com.disderm.dao.StockactualDAO;
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

@Path("stock_actual")
public class StockactualService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertStockactual(
            @FormParam("id") Long id,
            @FormParam("units") int units,
            @FormParam("pharmacy_id") int pharmacy_id) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        StockactualDAO stock_actualDao = new StockactualDAO();
        StockactualModel _StockactualModel = new StockactualModel();
        _StockactualModel.set_id(id);
        _StockactualModel.set_units(units);
        _StockactualModel.set_pharmacy_id(pharmacy_id);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(stock_actualDao.insertStockactual(_StockactualModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllStockactual(@PathParam("user_id") Long user_id) {
        StockactualDAO stock_actualDao = new StockactualDAO();
        String reply = "";
        reply = stock_actualDao.getAllStockactualByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllStockactualByUsername(@PathParam("username") String username) {
        StockactualDAO stock_actualDao = new StockactualDAO();
        String reply = "";
        reply = stock_actualDao.getAllStockactualByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapStockactual() {
        StockactualDAO stock_actualDao = new StockactualDAO();
        String reply = "";
        reply = stock_actualDao.getAllSelectStockactual();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableStockactual(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        StockactualDAO stock_actualDao = new StockactualDAO();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();
        String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

        String query = uriInfo.getRequestUri().getQuery();
        String filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        String filter_sql = "";
        if (!filter_value.isEmpty()) {
            filter_sql = "WHERE CAST(stock_actual_id as CHAR) LIKE '%" + filter_value + "%' limit ?,?";
        }
        reply = stock_actualDao.getAllDataTableStockactual(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String theKey = (String)it.next();
            //System.out.println(theKey);

        }*/
}