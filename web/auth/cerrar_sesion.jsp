<%@ page import="com.disderm.auth.Heimdall" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" scope="session" class="com.disderm.auth.SessionBean"/>
<%

    HttpSession httpSession = request.getSession();
    String session_id = httpSession.getId();
    String hostname = request.getRemoteHost();

    Heimdall heimdall = new Heimdall();
    heimdall.logOut (session_id, hostname);

    // Se invalida la Sesion:
    httpSession.invalidate();

    sessionBean.close();

%>
<jsp:forward page="logout.jsp"/>