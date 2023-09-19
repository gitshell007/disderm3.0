package com.disderm.service;

import com.disderm.dao.LogsDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.LogsModel;
import com.google.gson.Gson;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.ws.rs.*;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("logs")
public class LogsService {
  private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  @POST
  @Path("/insert")
  @Produces("application/json")
  public String serviceInsertLogs(
      @FormParam("type") String type,
      @FormParam("date") String date,
      @FormParam("user") String user,
      @FormParam("des") String des) {
    Timestamp ts_now = new Timestamp(new Date().getTime());
    LogsDAO logsDao = new LogsDAO();
    LogsModel _LogsModel = new LogsModel();
    _LogsModel.set_type(type);
    _LogsModel.set_date(date);
    _LogsModel.set_user(user);
    _LogsModel.set_des(des);
    String reply = "";
    Long insert_id = 0L;
    CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
    csam.setSuccess(1);
    Gson gson = new Gson();
    csam.setResult(String.valueOf(logsDao.insertLogs(_LogsModel)));
    reply = gson.toJson(csam);
    return reply;
  }

  @GET
  @Path("/getAll/{username}")
  @Produces("application/json")
  public String serviceGetAllLogs(@PathParam("username") String username) {
    LogsDAO logsDao = new LogsDAO();
    String reply = "";
    reply = logsDao.getAllLogsByUsername(username);
    return reply;
  }

  @GET
  @Path("/getAll")
  @Produces("application/json")
  public String serviceGetAllLogs() {
    LogsDAO logsDao = new LogsDAO();
    String reply = "";
    reply = logsDao.getAllLogs();
    return reply;
  }

  @GET
  @Path("/getAll/username/{username}")
  @Produces("application/json")
  public String serviceGetAllLogsByUsername(@PathParam("username") String username) {
    LogsDAO logsDao = new LogsDAO();
    String reply = "";
    reply = logsDao.getAllLogsByUsername(username);
    return reply;
  }

  @GET
  @Path("/getSelectBootstrap")
  @Produces("application/json")
  public String serviceGetAllSelectBootstrapLogs() {
    LogsDAO logsDao = new LogsDAO();
    String reply = "";
    reply = logsDao.getAllSelectLogs();
    return reply;
  }

  @GET
  @Path("/getAllDataTable")
  @Produces("application/json")
  public String serviceGetAllDataTableLogs(
      @Context UriInfo uriInfo,
      @DefaultValue("20") @QueryParam("length") int length,
      @DefaultValue("0") @QueryParam("start") int start,
      @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
    LogsDAO logsDao = new LogsDAO();
    String reply = "";
    MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    Iterator<String> it = queryParams.keySet().iterator();
    String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

    String query = uriInfo.getRequestUri().getQuery();
    String filter_value =
        queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
    String filter_sql = "";
    if (!filter_value.isEmpty()) {
      filter_sql = "WHERE CAST(logs_id as CHAR) LIKE '%" + filter_value + "%' limit ?,?";
    }
    reply = logsDao.getAllDataTableLogs(start, length, Integer.parseInt(draw), filter_sql);
    return reply;
  } /*ONLY TIP for (String theKey : queryParams.keySet()) {
        String theKey = (String)it.next();
        //System.out.println(theKey);

    }*/
}
