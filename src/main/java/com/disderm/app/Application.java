package com.disderm.app;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("services")
public class Application  extends ResourceConfig {
    public Application() {
        packages("com.disderm.auth;com.disderm.service");
    }
}
