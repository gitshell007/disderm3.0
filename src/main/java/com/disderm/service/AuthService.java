package com.disderm.service;

import com.disderm.auth.Heimdall;
import com.disderm.model.CustomAppUserModel;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("auth")
public class AuthService {


    @POST
    @Path("/auth")
    @Consumes(MediaType.WILDCARD)
    @Produces("text/plain")
    public String serviceActualizarTratamientos(
            String json
    ) throws IOException {
        System.out.println("cadena recibida para autenticar:  " + json);

        Gson gson = new Gson();
        Heimdall heimdall = new Heimdall();

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);

        String username = "";
        String password = "";


        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {

                String fieldName = parser.getCurrentName();
                jsonToken = parser.nextToken();
                if("username".equals(fieldName)){
                    username = parser.getValueAsString();
                } else if ("password".equals(fieldName)){
                    System.out.println("el valor es " + parser.getValueAsString());
                    password = parser.getValueAsString();
                }
            }
        }
        CustomAppUserModel caum;
        caum = heimdall.checkUserFromApp(username,password);
        String reply = gson.toJson(caum);
        return reply;
    }
}