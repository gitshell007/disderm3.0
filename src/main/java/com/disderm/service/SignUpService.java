package com.disderm.service;

import com.disderm.auth.Heimdall;
import com.disderm.dao.DisdermusersDAO;
import com.disderm.model.CustomAppUserModel;
import com.disderm.model.DisdermusersModel;
import com.disderm.utils.Crypto;
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
import java.util.Date;

@Path("signupapp")
public class SignUpService {


    @POST
    @Consumes(MediaType.WILDCARD)
    @Produces("text/plain")
    public String serviceActualizarTratamientos(
            String json
    ) throws IOException {
        Date fecha_actual = new Date();
        CustomAppUserModel caum = new CustomAppUserModel();
        System.out.println("signup  " + json);
        DisdermusersModel userModelReceived = new DisdermusersModel();
        Gson gson = new Gson();
        Heimdall heimdall = new Heimdall();
        DisdermusersDAO userDAO = new DisdermusersDAO();
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);

        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {

                String fieldName = parser.getCurrentName();
                jsonToken = parser.nextToken();
                if("username".equals(fieldName)){
                    userModelReceived.set_username(parser.getValueAsString());
                } else if ("password".equals(fieldName)){
                    userModelReceived.set_password(Crypto.encrypt(parser.getValueAsString(), "SHA-512"));
                }
                else if ("first_name".equals(fieldName)){
                    userModelReceived.set_first_name(parser.getValueAsString());
                }
                else if ("last_name".equals(fieldName)){
                    userModelReceived.set_first_name(parser.getValueAsString());
                }

            }
        }


        if (heimdall.checkExistUser(userModelReceived.get_username()) == 0L) {
           //TODO DA FALLO userModelReceived.set_fecha_alta_sistema(fecha_actual);
            userModelReceived.set_enabled(1);
            System.out.println(userModelReceived);
            Long insertID = userDAO.insertDisdermusers(userModelReceived);
            if (insertID != 0L) {
                caum.set_success(true);
                caum.setMensaje("Registro correcto!");
                //sms.sendAltaAppdEmail(pacientesModel.get_app_email(), request.getParameter("password"));
                //content = "  <h4>Operaci&oacute;n completada correctamente</h4><span>Su proceso de alta ha sido realizado correctamente, vuelva a la aplicación para acceder y revise su buzon de correo electr&oacute;nico</span>\n\n<div class=\" mt-4 d-flex align-items-center\"><div class=\"ml-auto\"><button class=\"mb-2 mr-2 btn-icon btn-pill btn btn-primary\" onclick=\"goHome()\"><i class=\"lnr-chevron-left-circle btn-icon-wrapper\"> </i>Volver</button></div></div>";
            }
            else
            {
                caum.set_success(false);
                caum.setMensaje("Error al insertar usuario");
            }
        } else {
            caum.set_success(false);
            caum.setMensaje("El usuario ya existe");
            //content = "  <h4>No se pudo completar la operaci&oacute;n</h4><span>El usuario ya existe, solicite recordar su clave si la ha olvidado: <a href=\"https://intranet.gineapp.com:10000/backoffice/recuperar-clave\">Recuperar clave.</a></span>\n\n<div class=\" mt-4 d-flex align-items-center\"><div class=\"ml-auto\"><button class=\"mb-2 mr-2 btn-icon btn-pill btn btn-primary\" onclick=\"goHome()\"><i class=\"lnr-chevron-left-circle btn-icon-wrapper\"> </i>Volver</button></div></div>";
        }


        String reply = gson.toJson(caum);
        System.out.println(reply);
        return reply;
    }
}