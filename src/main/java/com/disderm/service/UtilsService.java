package com.disderm.service;

import com.disderm.utils.Varios;

import javax.ws.rs.*;

@Path("utils")
public class UtilsService {
    @GET
    @Path("/app-about")
    @Produces("text/html")
    public String get_web_about() {
        return Varios.getHTMLTerminosCondiciones();
    }

}

