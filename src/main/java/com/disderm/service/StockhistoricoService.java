package com.disderm.service;

import com.disderm.model.StockhistoricoModel;
import com.disderm.dao.StockhistoricoDAO;
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

@Path("stock_historico")
public class StockhistoricoService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/insert")
    @Produces("application/json")
    public String serviceInsertStockhistorico(
            @FormParam("id") Long id,
            @FormParam("units") int units,
            @FormParam("date") Date date,
            @FormParam("pharmacy_id") int pharmacy_id) {
        Timestamp ts_now = new Timestamp(new Date().getTime());
        StockhistoricoDAO stock_historicoDao = new StockhistoricoDAO();
        StockhistoricoModel _StockhistoricoModel = new StockhistoricoModel();
        _StockhistoricoModel.set_id(id);
        _StockhistoricoModel.set_units(units);
        _StockhistoricoModel.set_date(date);
        _StockhistoricoModel.set_pharmacy_id(pharmacy_id);
        String reply = "";
        Long insert_id = 0L;
        CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
        csam.setSuccess(1);
        Gson gson = new Gson();
        csam.setResult(String.valueOf(stock_historicoDao.insertStockhistorico(_StockhistoricoModel)));
        reply = gson.toJson(csam);
        return reply;
    }

    @GET
    @Path("/getAll/{user_id}")
    @Produces("application/json")
    public String serviceGetAllStockhistorico(@PathParam("user_id") Long user_id) {
        StockhistoricoDAO stock_historicoDao = new StockhistoricoDAO();
        String reply = "";
        reply = stock_historicoDao.getAllStockhistoricoByPacienteID(user_id);
        return reply;
    }

    @GET
    @Path("/getAll/username/{username}")
    @Produces("application/json")
    public String serviceGetAllStockhistoricoByUsername(@PathParam("username") String username) {
        StockhistoricoDAO stock_historicoDao = new StockhistoricoDAO();
        String reply = "";
        reply = stock_historicoDao.getAllStockhistoricoByUsername(username);
        return reply;
    }

    @GET
    @Path("/getSelectBootstrap")
    @Produces("application/json")
    public String serviceGetAllSelectBootstrapStockhistorico() {
        StockhistoricoDAO stock_historicoDao = new StockhistoricoDAO();
        String reply = "";
        reply = stock_historicoDao.getAllSelectStockhistorico();
        return reply;
    }

    @GET
    @Path("/getAllDataTable")
    @Produces("application/json")
    public String serviceGetAllDataTableStockhistorico(@Context UriInfo uriInfo, @DefaultValue("20") @QueryParam("length") int length, @DefaultValue("0") @QueryParam("start") int start, @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
        StockhistoricoDAO stock_historicoDao = new StockhistoricoDAO();
        String reply = "";
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        Iterator<String> it = queryParams.keySet().iterator();
        String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

        String query = uriInfo.getRequestUri().getQuery();
        String filter_value = queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
        String filter_sql = "";
        if (!filter_value.isEmpty()) {
            filter_sql = "WHERE CAST(stock_historico_id as CHAR) LIKE '%" + filter_value + "%' limit ?,?";
        }
        reply = stock_historicoDao.getAllDataTableStockhistorico(start, length, Integer.parseInt(draw), filter_sql);
        return reply;
    }/*ONLY TIP for (String theKey : queryParams.keySet()) {
            String theKey = (String)it.next();
            //System.out.println(theKey);

        }*/
}