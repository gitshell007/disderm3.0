package com.disderm.auth;

import com.disderm.model.UsuariosModel;

public class SessionBean {

    private UsuariosModel user;
    private String session_id;

    public UsuariosModel getUser() {
        return user;
    }

    public void setUser(UsuariosModel user) {
        this.user = user;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void close() {
        this.user = null;
        this.session_id = null;
    }

}
