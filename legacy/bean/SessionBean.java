package com.disderm.bean;

import com.disderm.model.DisdermusersModel;

public class SessionBean {

    private DisdermusersModel personal;
    private String session_id;

    public DisdermusersModel getUser() {
        return personal;
    }

    public void setUser(DisdermusersModel _personal) {
        this.personal = _personal;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void close() {
        this.personal = null;
        this.session_id = null;
    }

}
