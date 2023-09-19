<%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 11/11/2021
  Time: 17:48
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
<%@ page import="com.disderm.utils.CreatePDF" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
    DiagnosticoModel diagnosticoModel = new DiagnosticoModel();
    PacienteModel pacienteModel = new PacienteModel();
    PacienteDAO pacienteDAO = new PacienteDAO();
    String jsp_js_var = "";
    String usuario = "";
    String content = "";
    UsuariosModel userModel = new UsuariosModel();
    UsuariosDAO userDAO = new UsuariosDAO();
    DiagnosticoService diagnosticoService = new DiagnosticoService();
    HttpSession httpSession = request.getSession();
    SessionBean sBean = (SessionBean) httpSession.getAttribute("sessionBean");
    long diagnosticoID = 0L;
    long pacienteID = 0L;

    if (sBean != null) {
        if ((sBean.getUser() != null) && (sBean.getSession_id() != null)) {
            usuario = sBean.getUser().get_nombre_usuario();
            userModel = userDAO.getUsuariosByNombre(usuario);
            if (request.getParameter("diagnosticoID") != null && request.getParameter("pacienteID") != null) {
                diagnosticoID = Long.parseLong(request.getParameter("diagnosticoID"));
                pacienteID = Long.parseLong(request.getParameter("pacienteID"));
                diagnosticoModel = diagnosticoDAO.getDiagnosticoModelByID(diagnosticoID);
                pacienteModel = pacienteDAO.getPacienteModelByID(pacienteID);
            }


            if (request.getParameter("crearPDF") != null) {
                System.out.println("Entramos al request");
                String html = request.getParameter("crearPDF");
                System.out.println("esto es el html"+html);
                //String documentoPDF = CreatePDF.getPDFLinkFromHTML(inicioHtml+html+finHTML);
                //CreatePDF.pdfEncryption(documentoPDF, diagnosticoModel.get_nombre());
                //CreatePDF.eliminarPDF(documentoPDF);
                //System.out.print(documentoPDF);
            } else {
                System.out.println("No se ha enviado el correo porque el informe está vacío.");
            }
            if (request.getParameter("isSubmit") != null) {
                diagnosticoModel.set_paciente_id(pacienteID);
                diagnosticoModel.set_id(Long.parseLong(request.getParameter("idDiagnostico")));
                diagnosticoModel.set_fecha(Date.valueOf(Settings.getFechaDDMMAAAA(Date.valueOf(request.getParameter("fecha")))));
                diagnosticoModel.set_urgente(request.getParameter("urgente"));
                diagnosticoModel.set_estado(request.getParameter("estado"));
                diagnosticoModel.set_user_facultativo_id(Long.valueOf(request.getParameter("user_facultativo_id")));

                pacienteModel.set_identificador_pac(Long.parseLong(request.getParameter("identificador_pac")));
                pacienteModel.set_dni(request.getParameter("dni"));
                pacienteModel.set_nombre(request.getParameter("nombre"));
                pacienteModel.set_apellidos(request.getParameter("apellidos"));
                pacienteModel.set_telefono(request.getParameter("telefono"));
                pacienteModel.set_correo(request.getParameter("correo"));
                pacienteModel.set_fecha_nacimiento(Date.valueOf(Settings.getFechaDDMMAAAA(Date.valueOf(request.getParameter("fecha-nacimiento")))));
                userModel = userDAO.getUsuariosByNombre(usuario);
                Long medico_id = userModel.get_id();
                Long insertID = diagnosticoDAO.updateDiagnosticodiagnostico(diagnosticoModel);
                if (insertID != 0L) {
                    content = "Item insertado correctamente";
                    jsp_js_var = "showMsg(\"Atenci&oacute;n\",\"" + content + "\",\" function () { console.log('Closing message from event', d, e);});\");";
                } else {
                    content = "Item no se inserto";
                    jsp_js_var = "showMsg(\"Atenci&oacute;n\",\"" + content + "\",\" function () { console.log('Closing message from event', d, e);});\");";
                }
            } else {
                //if (request.getParameter("id") != null) {


            }
            String urgencia = diagnosticoModel.get_urgente();
            urgencia = (urgencia.equalsIgnoreCase("1")) ? "Si" : "No";
            String host = Varios.getFullURL();
            String visible_name = sBean.getUser().get_nombre_mostrar();
            String username = usuario;
            String user_icon = "/backoffice/assets_intranet/images/avatars/doctora.png";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Disderm Backoffice System</title>
    <meta charset="ISO-8859-1"/>
    <meta http-equiv="Content-Type" content="text/html"; charset="utf-8"/>
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="<c:url value='/assets_intranet/css/base.min.css' />">

    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.4/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>


    <%=MainView.getImportsScripts(false, false, false, false, false)%>

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

    <style>
        .textoSuperior {
            font-weight: bold;
            color: #b42d8f;
        }

        .btnPrincipal {
            width: inherit;
            background-color: #b42d8f;
            height: 40px;
        }

        .title {
            border: #b42d8f;
            border-bottom: solid;
            font-weight: bold;
            color: #b42d8f;
        }

        .textareaCard {

        }

        .imgSelected {
            height: 25em;
            margin: auto;
            padding: 10px;
        }

        .imgSelected img {
            height: 100%;
            width: 100% !important;
        }

        .carousel-item {
            height: 200px;
        }

        .carousel-item img {
            width: 25%;
            height: 195px;
        !important;
            object-fit: scale-down;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .myImg {
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }

        .myImg:hover {
            opacity: 0.7;
        }


        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 70%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
        }

        /* Modal Content (image) */
        .modal-content {
            margin: auto;
            display: block;
            width: 100%;
            max-width: 1000px;
        }

        /* Caption of Modal Image */
        #caption {
            margin: auto;
            display: block;
            width: 80%;
            max-width: 700px;
            text-align: center;
            color: #ccc;
            padding: 10px 0;
            height: 150px;
        }

        /* Add Animation */
        .modal-content, #caption {
            -webkit-animation-name: zoom;
            -webkit-animation-duration: 0.6s;
            animation-name: zoom;
            animation-duration: 0.6s;
        }

        @-webkit-keyframes zoom {
            from {
                -webkit-transform: scale(0)
            }
            to {
                -webkit-transform: scale(1)
            }
        }

        @keyframes zoom {
            from {
                transform: scale(0)
            }
            to {
                transform: scale(1)
            }
        }

        /* The Close Button */
        .close {
            position: absolute;
            top: 15px;
            right: 35px;
            color: #f1f1f1;
            font-size: 40px;
            font-weight: bold;
            transition: 0.3s;
        }

        .close:hover,
        .close:focus {
            color: #bbb;
            text-decoration: none;
            cursor: pointer;
        }

        /* 100% Image Width on Smaller Screens */
        @media only screen and (max-width: 700px) {
            .modal-content {
                width: 100%;
            }
        }
    </style>


    <script>
        // Cargamos galeria
        new juicebox({
            containerId: 'juicebox-container',
            usePassword:"false",
            rememberPassword:"false",

        });
    </script>

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
                                        <%=MainView.getTopBar("", "", "admin", "admin", user_icon)%>
                                        <!-- END HEADER TOP BAR -->
                                        <div class="position-relative row">
                                            <div class="col-sm-12">
                                                <button id="paginaAnterior1"
                                                        type="button"
                                                        class="btn btn-primary form-group"
                                                        onclick="paginaAnterior()"
                                                        style="width: inherit;">
                                                    Volver
                                                </button>
                                                <input type="hidden" name="isSubmit" value="true">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="col-md-3 offset-md-1">
                                                <div class="position-relative form-group form-row">
                                                    <h4 class="textoSuperior">
                                                        Consulta: <%=diagnosticoID%>
                                                    </h4>
                                                </div>
                                            </div>
                                            <div class="col-md-3 offset-md-1">
                                                <div class="position-relative form-group form-row">
                                                    <h4 class="textoSuperior">Fecha Visita: <%=Settings.getFechaDDMMAAAA(diagnosticoModel.get_fecha())%>
                                                    </h4>
                                                </div>
                                            </div>
                                            <div class="col-md-3 offset-md-1">
                                                <div class="position-relative form-group form-row">
                                                    <h4 class="textoSuperior">Urgente: <%=urgencia%>
                                                    </h4>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="main-card mb-3 card">
                                            <div>
                                                <form id="crearInformeForm" name="crearInformeForm" method="POST">
                                                    <div class="card-body">
                                                        <h3 class="title">Paciente</h3>
                                                        <div class="form-row">
                                                            <div class="col-md-3">
                                                                <div class="position-relative form-group row">
                                                                    <div class="offset-1">
                                                                        <label for="identificador_pac"
                                                                               class="col-form-label"
                                                                               style="font-weight: bold;">Código
                                                                            paciente:</label>
                                                                    </div>
                                                                    <div>
                                                                        <input name="identificador_pac"
                                                                               id="identificador_pac"
                                                                               style="border:none; background-color: #FFFFFF"
                                                                               value="<%=pacienteID%>"
                                                                               type="text"
                                                                               class="form-control" readonly>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3 offset-md-1">
                                                                <div class="position-relative form-group row">
                                                                    <div>
                                                                        <label for="nombre" class="col-form-label"
                                                                               style="font-weight: bold;">Nombre</label>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <input name="nombre" id="nombre"
                                                                               style="border:none; background-color: #FFFFFF"
                                                                               value="<%=pacienteModel.get_nombre()%>"
                                                                               type="text"
                                                                               class="form-control" readonly>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <input type="hidden" id="idDiagnostico"
                                                                               name="idDiagnostico"
                                                                               value="<%=diagnosticoModel.get_id()%>"
                                                                               type="text" class="form-control">
                                                                        <input type="hidden" id="fecha" name="fecha"
                                                                               value="<%=Settings.getFechaDDMMAAAA(diagnosticoModel.get_fecha())%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="urgente" name="urgente"
                                                                               value="<%=diagnosticoModel.get_urgente()%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="dni" name="dni"
                                                                               value="<%=pacienteModel.get_dni()%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="apellidos"
                                                                               name="apellidos"
                                                                               value="<%=pacienteModel.get_apellidos()%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="telefono"
                                                                               name="telefono"
                                                                               value="<%=pacienteModel.get_telefono()%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="correo" name="correo"
                                                                               value="<%=pacienteModel.get_correo()%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="fecha_nacimiento"
                                                                               name="fecha_nacimiento"
                                                                               value="<%=Settings.getFechaDDMMAAAA(pacienteModel.get_fecha_nacimiento())%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="facultativo"
                                                                               name="facultativo"
                                                                               value="<%=diagnosticoModel.get_user_facultativo_id()%>"
                                                                               class="form-control">
                                                                        <input type="hidden" id="estado"
                                                                               name="estado"
                                                                               value="<%=diagnosticoModel.get_estado()%>"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3 offset-md-1">
                                                                <div class="position-relative form-group row">
                                                                    <div>
                                                                        <label for="fecha_nacimiento"
                                                                               class="col-form-label"
                                                                               style="font-weight: bold;">Fecha de nacimiento:</label>
                                                                    </div>
                                                                    <div>
                                                                        <input name="fecha_nacimiento_ver"
                                                                               id="fecha_nacimiento_ver"
                                                                               style="border:none; background-color: #FFFFFF"
                                                                               value="<%=Settings.getFechaDDMMAAAA(pacienteModel.get_fecha_nacimiento())%>"
                                                                               type="text"
                                                                               class="form-control" readonly>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="card-body">
                                                        <h3 class="title">Imágenes</h3>
                                                        <!-- The Modal -->
                                                        <div class="container ">
                                                            <div class="row">

                                                                <div id="juicebox-container"></div>
                                                                <div class="imgSelected">
                                                                    <img id="imgSelected" class="myImg2"
                                                                         src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/June_odd-eyed-cat.jpg/1200px-June_odd-eyed-cat.jpg">
                                                                </div>
                                                                <!-- The Modal -->
                                                                <div id="myModal" class="modal">
                                                                    <span class="close">&times;</span>
                                                                    <img class="modal-content" id="img01">
                                                                    <div id="caption"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="card-body textareaCard">
                                                        <div class="form-group w-100">
                                                            <label><h3 class="title">Incidencia que imposibilita generar
                                                                y enviar informe: </h3></label>
                                                            <input style="width: 20px; height: 20px;" type="checkbox"
                                                                   id="ocultarDiv" value="0"/>
                                                        </div>
                                                        <div class="form-group" id="incidencia" style="display: none">
                                                            <div class="form-group row">
                                                                <div class="dropdown col-3 offset-1">
                                                                    <button class="btn btn-primary form-group dropdown-toggle"
                                                                            type="button" id="dropdownMenu"
                                                                            data-toggle="dropdown" aria-haspopup="true"
                                                                            aria-expanded="false"
                                                                            style="background-color: #b42d8f; width: 75%;">
                                                                        Tipo de incidencia
                                                                    </button>
                                                                    <div class="dropdown-menu"
                                                                         aria-labelledby="dropdownMenu2">
                                                                        <button class="dropdown-item" type="button">
                                                                            Fotografías erroneas
                                                                        </button>
                                                                        <button class="dropdown-item" type="button">
                                                                            Faltan fotografías
                                                                        </button>
                                                                        <button class="dropdown-item" type="button">
                                                                            Falta de datos
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group col-6 card-body">
                                                                    <div style="border-bottom: groove; width: 100%">
                                                                        <label for="incidenciaText">Texto para
                                                                            administración:</label>
                                                                    </div>
                                                                    <textarea class="incidenciaText" id="incidenciaText"
                                                                              style="width: 100%;" rows="8"
                                                                              cols="100"></textarea>
                                                                </div>
                                                            </div>
                                                            <div class="position-relative row">
                                                                <div class="col-sm-12">
                                                                    <button class="btn btn-primary form-group btnPrincipal"
                                                                            onclick="">
                                                                        ENVIAR INCIDENCIA
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card-body textareaCard" id="valoracion">
                                                        <h3 class="title">Valoración</h3>
                                                        <button id="pruebaContent"
                                                                class="btn btn-primary btnPrincipal col-1"
                                                                type="button"
                                                                onclick="cargarPlantilla()">
                                                            Plantillas
                                                        </button>
                                                        <textarea id="informeSN" name="informeSN" class="form-control"></textarea>


                                                        <form id="enviarInformeForm" name="enviarInformeForm"
                                                              method="post">
                                                            <input type="hidden" name="enviarInforme"
                                                                   id="enviarInforme">
                                                        </form>
                                                        <form id="crearPDFForm" name="crearPDFFORM"
                                                              method="post">
                                                            <input type="hidden" name="crearPDF"
                                                                   id="crearPDF">
                                                        </form>
                                                        <div class="position-relative row">
                                                            <div class="col-sm-12">
                                                                <button id="paginaAnterior2"
                                                                        type="button"
                                                                        class="btn btn-primary form-group"
                                                                        onclick="paginaAnterior()"
                                                                        style="width: inherit;">
                                                                    Volver
                                                                </button>
                                                                <input type="hidden" name="isSubmit" value="true">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row">
                                                            <div class="col-sm-12">
                                                                <button id="generarInformeBtn"
                                                                        type="button"
                                                                        class="btn btn-info form-group"
                                                                        onclick="traerTexto()"
                                                                        style="width: inherit; color:white;">
                                                                    Generar Informe
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row">
                                                            <div class="col-sm-12">
                                                                <button id="vistaPraviaBtn"
                                                                        class="btn btn-info form-group"
                                                                        onclick="vistaPrevia()"
                                                                        style="width: inherit; color:white;">
                                                                    Vista Previa
                                                                </button>
                                                                <input type="hidden" name="isSubmit" value="true">
                                                            </div>
                                                        </div>
                                                        <div id="lock-modal"></div>
                                                        <div id="loading-circle"></div>
                                                    </div>
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
        <!--<div class="app-wrapper-footer">

        </div>-->
    </div>
</div>
</div>
<div class="app-drawer-overlay d-none animated fadeIn"></div>
<div class="modal fade" id="avisoBorrarModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div>
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">ATENCION</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" name="modal1-body-text" id="modal1-body-text">
                La subida de archivos finalizo correctamente
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>
<script>
    var ocultarCheck = document.getElementById('ocultarDiv');
    var ocultarDiv = function myFunction() {
        var x = document.getElementById("incidencia");
        var y = document.getElementById("valoracion");
        if (x.style.display === "none") {
            x.style.display = "block";
            y.style.display = "none";
        } else {
            x.style.display = "none";
            y.style.display = "block";
        }
    }

    ocultarCheck.addEventListener("click", ocultarDiv)

    var paginaAnterior = function () {
        window.location = "<%=Settings.contentRootModules%>" + 'diagnosticos/tabla_diagnosticos_derma.jsp';
    }

    var pruebasubmit = function (){
        form_main.submit();
    }
    var vistaPrevia = function () {
        var seldata = "<%=diagnosticoModel.get_id()%>"
        var form_main = $("#crearInformeForm")
        var input = $("#enviarInforme")

        input.val(seldata)
        form_main.attr('action', "<%=Settings.contentRootModules%>" + 'informes/vista_previa.jsp');
        form_main.submit();
    }

    //MODAL PARA LAS FOTOS
    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the image and insert it inside the modal - use its "alt" text as a caption
    var img = document.getElementsByClassName('myImg');
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");
    var selectedImg = document.getElementById("imgSelected");

    var showModal = function () {
        modal.style.display = "block";
        modalImg.src = this.src;
        captionText.innerHTML = this.alt;
    }


    var changeModal = function () {
        selectedImg.src = this.src;
    }

    for (var i = 0; i < img.length; i++) {
        img[i].addEventListener('click', changeModal);
    }
    selectedImg.addEventListener('click', showModal);
    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }
</script>

<script>
    var traerTexto = function (){
        var texto= document.getElementById('informeSN').value;
        console.log(texto)
        var form_main = $("#crearPDFForm")
        var input = $("#crearPDF")

        input.val(texto)
        form_main.submit();
    }


    var cargarPlantilla = function () {
        var markupStr =
            '<h1>Mi primer PDF</h1>'+
            '<p>Hello there.</p>'
        $('#informeSN').summernote('code', markupStr);
    }
    $(document).ready(function() {



        $('#informeSN').summernote({
            height: 300,
            toolbar: [
                ['style', ['bold', 'italic', 'underline', 'clear']],
                ['font', ['strikethrough', 'superscript', 'subscript']],
                ['fontsize', ['fontsize']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['view', ['codeview']],
            ]
        });
    });
</script>
<!-- START SCRIPTS IMPORTS -->
<!--CORE-->

<!-- END SCRIPTS -->

<!--SCRIPTS INCLUDES-->
<script>
    var $msgModal = $('#avisoBorrarModal').modal({backdrop: false, show: false, keyboard: false}),
        showMsg = function (header, text, callback) {
            $msgModal
                .find('.modal-header > h3').text(header).end()
                .find('.modal-body').text(text).end()
                .find('.callback-btn').off('click.callback')
                .on('click.callback', callback).end()
                .modal('show');
        };
    const form = $("#crearInformeForm");
    const lockModal = $("#lock-modal");
    const loadingCircle = $("#loading-circle");

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