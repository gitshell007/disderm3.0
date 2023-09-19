<%@ page import="com.disderm.dao.PersonalDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" scope="session" class="com.disderm.bean.SessionBean"/>
<%

    HttpSession httpSession = request.getSession();
    String session_id = httpSession.getId();
    String hostname = request.getRemoteHost();

    PersonalDAO personalDao = new PersonalDAO();
    personalDao.logOut (session_id, hostname);

    // Se invalida la Sesion:
    httpSession.invalidate();

    sessionBean.close();

%>
<jsp:forward page="login.jsp?command=close"/>