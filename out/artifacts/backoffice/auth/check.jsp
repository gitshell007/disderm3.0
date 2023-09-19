<%@ page import="com.disderm.auth.SessionBean" %>
<%@ page import="com.disderm.auth.Heimdall" %>

<%@ page import="com.disderm.utils.Crypto" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.disderm.utils.FFecha" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.disderm.model.CustomAnswerModel" %>
<%@ page import="com.disderm.model.DisdermusersModel" %>
<%@ page import="com.disderm.utils.Settings" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.disderm.model.UsuariosModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css"/>
<style>
    .modal a.close-modal {
        display: none
    }
</style>

<script>


    function eventFire(el, etype) {
        if (el.fireEvent) {
            el.fireEvent('on' + etype);
        } else {
            var evObj = document.createEvent('Events');
            evObj.initEvent(etype, true, false);
            el.dispatchEvent(evObj);
        }
    }

</script>

<jsp:useBean id="sessionBean" scope="session" class="com.disderm.auth.SessionBean"/>
<div id="ex1" class="modal">
    <p>Usuario/contraseña incorrectos</p>
    <input type="button" onclick=" location.href='<%=Settings.contentRoot%>login.jsp' " value="Cerrar" name="boton"/>
    <!--<a href="/backoffice/login.jsp" >Cerrar</a>-->

</div>
<p><a id="modal" style="visibility: hidden" href="#ex1" rel="modal:open">Open Modal</a></p>
<div id="ex2" class="modal2">
    <p>Debe rellenar usuario y contraseña</p>
    <input type="button" onclick=" location.href='/backoffice/login.jsp' " value="Cerrar" name="boton2"/>
    <!--<a href="/backoffice/login.jsp" >Cerrar</a>-->

</div>
<p><a id="modal2" style="visibility: hidden" href="#ex2" rel="modal:open">Open Modal</a></p>


<%
    response.addHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    String username = null;
    if (request.getParameter("username") != null) {
        username = request.getParameter("username");
    }

    String password = null;
    if (request.getParameter("password") != null) {
        password = request.getParameter("password");
    }

    System.out.println("user: " + username + " pass: " + password);

    if ((username == "") || (password == "")) { %>
<script>
    eventFire(document.getElementById('modal2'), 'click');
</script>
<%
    }

    int code;

    Date date = new Date();
    String fecha_to_hash = FFecha.getString(date, FFecha.sdf_ddMMyyyyBarHHmm);
    String hash = Crypto.encrypt(fecha_to_hash + "elpoderdelperro", "SHA-512");

    if ((username != null) && (password != null) && (!username.isEmpty()) && (!password.isEmpty()) && username.length() < 40 && password.length() < 40) {

        String session_id = request.getSession().getId();
        String host = request.getRemoteHost();
        String agent = request.getHeader("user-agent");

        Heimdall heimdall = new Heimdall();
        Long idAuth = heimdall.insertLogSessions(host, agent, username, password, session_id);

        UsuariosModel user = heimdall.checkUserAppLogin(username, Crypto.encrypt(password, "SHA-512"), request.getRemoteAddr(), request.getHeader("user-agent"));


        if ((user != null) && (user.get_id() > 0)) {

            /*RoleManager roleManager = new RoleManager();
            user.setRole( roleManager.get(user.getRole_id()) );*/

            SessionBean sBean = new SessionBean();
            sBean.setUser(user);
            sBean.setSession_id(session_id);

            request.getSession().setAttribute("sessionBean", sBean); %>
echo "<script>
window.location.href="<%=Settings.contentRootModules%>"+"diagnosticos/tabla_diagnosticos_derma.jsp";
</script>";
<!--// response.sendRedirect("/backoffice/modulos/sales/tbl_custom_search_sales.jsp");
//heimdall.loginSuccessful(idAuth);

//auth.updateLastAccess (user.getId()); -->

<% code = 0;
    //response.sendRedirect("/backoffice/main.jsp");
} else { %>
<script>
    eventFire(document.getElementById('modal'), 'click');
</script>
<%
            code = 1;

        }

    } else {
        code = 2;
    }


%>
