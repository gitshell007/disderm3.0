package com.disderm.service;

import com.disderm.dao.UsertypeDAO;
import com.disderm.model.CustomServiceAnswerModel;
import com.disderm.model.UsertypeModel;
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

@Path("user_type")
public class UsertypeService {
  private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

  @POST
  @Path("/insert")
  @Produces("application/json")
  public String serviceInsertUsertype(
      @FormParam("user_type_des") String user_type_des,
      @FormParam("user_type_code") String user_type_code) {
    Timestamp ts_now = new Timestamp(new Date().getTime());
    UsertypeDAO user_typeDao = new UsertypeDAO();
    UsertypeModel _UsertypeModel = new UsertypeModel();
    _UsertypeModel.set_user_type_des(user_type_des);
    _UsertypeModel.set_user_type_code(user_type_code);
    String reply = "";
    Long insert_id = 0L;
    CustomServiceAnswerModel csam = new CustomServiceAnswerModel();
    csam.setSuccess(1);
    Gson gson = new Gson();
    csam.setResult(String.valueOf(user_typeDao.insertUsertype(_UsertypeModel)));
    reply = gson.toJson(csam);
    return reply;
  }

  @GET
  @Path("/getAll/{username}")
  @Produces("application/json")
  public String serviceGetAllUsertype(@PathParam("username") String username) {
    UsertypeDAO user_typeDao = new UsertypeDAO();
    String reply = "";
    reply = user_typeDao.getAllUsertypeByUsername(username);
    return reply;
  }

  @GET
  @Path("/getAll")
  @Produces("application/json")
  public String serviceGetAllUsertype() {
    UsertypeDAO user_typeDao = new UsertypeDAO();
    String reply = "";
    reply = user_typeDao.getAllUsertype();
    return reply;
  }

  @GET
  @Path("/getAll/username/{username}")
  @Produces("application/json")
  public String serviceGetAllUsertypeByUsername(@PathParam("username") String username) {
    UsertypeDAO user_typeDao = new UsertypeDAO();
    String reply = "";
    reply = user_typeDao.getAllUsertypeByUsername(username);
    return reply;
  }

  @GET
  @Path("/getSelectBootstrap")
  @Produces("application/json")
  public String serviceGetAllSelectBootstrapUsertype() {
    UsertypeDAO user_typeDao = new UsertypeDAO();
    String reply = "";
    reply = user_typeDao.getAllSelectUsertype();
    return reply;
  }

  @GET
  @Path("/getAllDataTable")
  @Produces("application/json")
  public String serviceGetAllDataTableUsertype(
      @Context UriInfo uriInfo,
      @DefaultValue("20") @QueryParam("length") int length,
      @DefaultValue("0") @QueryParam("start") int start,
      @DefaultValue("0") @QueryParam("filter_field") String filter_field) {
    UsertypeDAO user_typeDao = new UsertypeDAO();
    String reply = "";
    MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    Iterator<String> it = queryParams.keySet().iterator();
    String draw = queryParams.get("draw").toString().replace("]", "").replace("[", "");

    String query = uriInfo.getRequestUri().getQuery();
    String filter_value =
        queryParams.get("search[value]").toString().replace("]", "").replace("[", "");
    String filter_sql = "";
    if (!filter_value.isEmpty()) {
      filter_sql = "WHERE CAST(user_type_id as CHAR) LIKE '%" + filter_value + "%' limit ?,?";
    }
    reply = user_typeDao.getAllDataTableUsertype(start, length, Integer.parseInt(draw), filter_sql);
    return reply;
  } /*ONLY TIP for (String theKey : queryParams.keySet()) {
        String theKey = (String)it.next();
        //System.out.println(theKey);

    }*/
}
