<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 18/11/2021
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.disderm.view.MainView" %>
<%@ page import="com.disderm.auth.SessionBean" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.dao.*" %>
<%@ page import="com.disderm.model.*" %>
<%@ page import="com.disderm.utils.Settings" %>
<%@ page import="com.disderm.service.DiagnosticoService" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
    DiagnosticoModel diagnosticoModel = new DiagnosticoModel();
    String jsp_js_var = "";
    String usuario = "";
    String content = "";
    String urgencia = "";
    UsuariosModel userModel = new UsuariosModel();
    UsuariosDAO userDAO = new UsuariosDAO();
    DiagnosticoService diagnosticoService = new DiagnosticoService();
    HttpSession httpSession = request.getSession();
    SessionBean sBean = (SessionBean) httpSession.getAttribute("sessionBean");
    long diagnosticoID = 0L;


    if (sBean != null) {
        if ((sBean.getUser() != null) && (sBean.getSession_id() != null)) {
            usuario = sBean.getUser().get_nombre_usuario();
            userModel = userDAO.getUsuariosByNombre(usuario);
            if (request.getParameter("enviarInforme") != null) {
                diagnosticoID = Long.parseLong(request.getParameter("enviarInforme"));
            }
            diagnosticoModel = diagnosticoDAO.getDiagnosticoModelByID(diagnosticoID);
            System.out.println(diagnosticoModel.toString());


            //urgencia = (diagnosticoModel.get_urgente().equalsIgnoreCase("1")) ? "Si" : "No";
            String host = Varios.getFullURL();
            String visible_name = sBean.getUser().get_nombre_mostrar();
            String username = usuario;
            String user_icon = "/backoffice/assets_intranet/images/avatars/doctora.png";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Disderm Backoffice System</title>
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="<c:url value='/assets_intranet/css/base.min.css' />">

    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.4/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">

    <%=MainView.getImportsScripts(false, false, false, false, false)%>
    <style>
        .btnVista {
            background-color: #b42d8f;
        }

        .textoSuperior {
            color: #b42d8f;
        }
        .btnPrincipal {
            width: inherit;
            background-color: #b42d8f;
            height: 50px;
        }
    </style>
</head>
<body>
<div class="app-container app-theme-gray">
    <div class="app-main">
        <div class="main-card  card" style="width: 100%; padding: 20px">
            <div class="app-inner-layout app-inner-layout-page">
                <div class="app-inner-layout__wrapper">
                    <div class="app-inner-layout__content pt-1">
                        <div class="tab-content">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-12">
                                        <!-- HEADER TOP BAR -->
                                        <%=MainView.getTopBar("Vista Previa del Informe", "Vista previa del pdf", "admin", "admin", user_icon)%>
                                        <!-- END HEADER TOP BAR -->
                                        <button id="paginaAnterior"
                                                class="btn btn-primary btnPrincipal col-1"
                                                onclick="paginaAnterior()">
                                            Volver
                                        </button>
                                        <div class="card-header form-group">
                                            <div class="col-md-3 offset-md-1">
                                                <div class="position-relative form-group form-row">
                                                    <h4 class="textoSuperior">
                                                        VISTA PREVIA INFORME DIAGNÓSTICO: <%=diagnosticoModel.get_id()%>
                                                    </h4>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="main-card card">
                                            <div>
                                                <div class="position-relative row">
                                                    <div class="col-sm-12 form-group">
                                                        <embed class="col-10 offset-1"
                                                               src="sample.pdf#toolbar=1" type="application/pdf"
                                                               width="100%" height="750px">
                                                    </div>
                                                    <div class="col-sm-12 form-group row">
                                                        <button id="cancelar"
                                                                class="btn btn-primary btnPrincipal col-2 offset-1 btnVista"
                                                                onclick="paginaAnterior()">
                                                            CANCELAR
                                                        </button>
                                                        <button id="finalizarEnviar"
                                                                class="btn btn-primary btnPrincipal col-2 offset-6 btnVista">
                                                            GENERAR INFORME
                                                        </button>
                                                    </div>
                                                </div>
                                                <form id="volverInformeForm" name="volverInformeForm" method="post">
                                                    <input type="hidden" name="diagnosticoID"
                                                           id="diagnosticoID">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var paginaAnterior = function (){
        var seldata = "<%=diagnosticoModel.get_id()%>"
        var form_main = $("#volverInformeForm")
        var input = $("#diagnosticoID")

        input.val(seldata)
        form_main.attr('action', "<%=Settings.contentRootModules%>" + 'informes/crear_informe.jsp');
        form_main.submit();
    }
    var crearPdf = function myFunction() {
        $.post('https://http://localhost:8080/backoffice/generatePDF', {
            gatito1: "hello", html: variable_editor,

        }, function(result) {

        });
    }
</script>
</body>
</html>
<%
        } else {
            response.sendRedirect(Settings.contentRoot + "login.jsp");
        }

    } else {
        response.sendRedirect(Settings.contentRoot + "login.jsp");
    }
%>
