package com.disderm.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.disderm.dao.SalesDAO;
import com.disderm.model.CustomAnswerModel;
import com.disderm.model.SalesModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

;

@Path("salesapp")
public class SalesAppService {
    private org.owasp.esapi.codecs.MySQLCodec MySQLCodecInstance;

    @POST
    @Path("/create")
    @Consumes(MediaType.WILDCARD)
    @Produces("application/json")
    public String serviceCreateSales(InputStream incomingData) throws IOException {
        Gson gson = new Gson();

        StringBuilder crunchifyBuilder = new StringBuilder();
        SalesDAO salesDAO = new SalesDAO();
        //ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            System.out.println(in.readLine());
            String line = null;
            while ((line = in.readLine()) != null) {
                crunchifyBuilder.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
        String json = crunchifyBuilder.toString();
        System.out.println(json);
        // Convert JSON File to Java Object
        SalesModel _SalesModel = gson.fromJson(json, SalesModel.class);
        System.out.println("----");
        System.out.println(_SalesModel.get_id());
        System.out.println(_SalesModel.toString());

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(crunchifyBuilder.toString());

        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                jsonToken = parser.nextToken();
                if ("id".equals(fieldName)) {
                    System.out.print("EL ID ES " + parser.getValueAsString());
                }
            }
        }
        CustomAnswerModel csam = new CustomAnswerModel();
        csam.setSuccess(1);
        csam.setResult(String.valueOf(salesDAO.insertSales(_SalesModel)));
        String reply = gson.toJson(csam);

        return reply;
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
    public String serviceGetAllDataTableSales() {
        SalesDAO salesDao = new SalesDAO();
        String reply = "";
        reply = salesDao.getAllSales();
        return reply;
    }
}