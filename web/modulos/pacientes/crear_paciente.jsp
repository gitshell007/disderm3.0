<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 09/11/2021
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.disderm.view.MainView" %>
<%@ page import="com.disderm.auth.SessionBean" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.dao.*" %>
<%@ page import="com.disderm.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.disderm.utils.Settings" %>
<%@ page import="com.disderm.utils.FFecha" %>
<%@ page import="org.apache.http.impl.bootstrap.HttpServer" %>
<%@ page import="com.disderm.service.PacienteService" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    PacienteDAO pacienteDAO = new PacienteDAO();
    PacienteModel pacienteModel = new PacienteModel();
    String jsp_js_var = "";
    String usuario = "";
    String content = "";
    DisdermusersModel userModel = new DisdermusersModel();
    DisdermusersDAO userDAO = new DisdermusersDAO();
    PacienteService pacienteService = new PacienteService();
    HttpSession httpSession = request.getSession();
    SessionBean sBean = (SessionBean) httpSession.getAttribute("sessionBean");

    if (sBean != null) {
        if ((sBean.getUser() != null) && (sBean.getSession_id() != null)) {
            usuario = sBean.getUser().get_username();
            userModel = userDAO.getDisdermusersByUsername(usuario);


            if (request.getParameter("isSubmit") != null) {
                String prueba = request.getParameter("nombre");
                System.out.println(prueba);
                pacienteModel.set_identificador_pac(Long.valueOf(request.getParameter("identificador_pac")));
                pacienteModel.set_dni(request.getParameter("NIF"));
                pacienteModel.set_nombre(request.getParameter("nombre"));
                pacienteModel.set_apellidos(request.getParameter("apellidos"));
                pacienteModel.set_telefono(Long.valueOf(request.getParameter("telefono")));
                pacienteModel.set_correo(request.getParameter("correo"));



                userModel = userDAO.getDisdermusersByUsername(usuario);
                Long medico_id = userModel.get_id();
                Long insertID = pacienteDAO.insertPacientepaciente(pacienteModel);
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
            String host = Varios.getFullURL();
            String visible_name = sBean.getUser().get_visible_name();
            String username = usuario;
            String user_icon = "/backoffice/assets_intranet/images/avatars/doctora.png";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Disderm Backoffice System</title>
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="<c:url value='/assets_intranet/css/base.min.css' />">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.4/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
</head>
<body>
<div class="app-container app-theme-gray">
    <div class="app-main">

        <!-- MENU LATERAL -->
        <%=MainView.getMenu()%>
        <!-- FIN MENU LATERAL -->

        <div class="app-sidebar-overlay d-none animated fadeIn"></div>


        <div class="app-main__outer">
            <div class="app-main__inner">
                <div class="header-mobile-wrapper">
                    <div class="app-header__logo">
                        <a href="#" data-toggle="tooltip" data-placement="bottom" title="GineApp Admin"
                           class="logo-src"></a>
                        <button type="button" class="hamburger hamburger--elastic mobile-toggle-sidebar-nav">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                        </button>
                        <div class="app-header__menu">
                            <span>
                                <button type="button"
                                        class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                                    <span class="btn-icon-wrapper">
                                        <i class="fa fa-ellipsis-v fa-w-6"></i>
                                    </span>
                                </button>
                            </span>
                        </div>
                    </div>
                </div>
                <!-- HEADER TOP BAR -->

                <%=MainView.getTopBar("Crear Paciente", "Creación de paciente", "admin", "admin", user_icon)%>
                <!-- END HEADER TOP BAR -->

                <br>
                <div class="main-card mb-3 card" style="width: 100%; padding: 20px">
                    <div class="app-inner-layout app-inner-layout-page">
                        <div class="app-inner-layout__wrapper">
                            <div class="app-inner-layout__content pt-1">
                                <div class="tab-content">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="main-card mb-3 card">
                                                    <div class="card-body">
                                                        <form id="insertarPacienteForm" name="insertarPacienteForm" method="POST">
                                                            <h3 class="card-title" style="font-weight: bold;">CREAR PACIENTE</h3>
                                                            <div class="form-row">
                                                                <div class="col-md-8">
                                                                    <div class="position-relative form-group">
                                                                        <label for="apellidos" class="col-form-label"
                                                                               style="font-weight: bold;">Apellidos</label>
                                                                        <input name="apellidos" id="apellidos" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <div class="position-relative form-group">
                                                                        <label for="identificador_pac" class="col-form-label"
                                                                               style="font-weight: bold;">identificador_pac</label>
                                                                        <input name="identificador_pac" id="identificador_pac" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-row">
                                                                <div class="col-md-6">
                                                                    <div class="position-relative form-group">
                                                                        <label for="nombre" class="col-form-label"
                                                                               style="font-weight: bold;">Nombre</label>
                                                                        <input name="nombre" id="nombre" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="position-relative form-group">
                                                                        <label for="NIF" class="col-form-label"
                                                                               style="font-weight: bold;">NIF</label>
                                                                        <input name="NIF" id="NIF" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-row">
                                                                <div class="col-md-6">
                                                                    <div class="position-relative form-group">
                                                                        <label for="telefono" class="col-form-label"
                                                                               style="font-weight: bold;">Teléfono</label>
                                                                        <input name="telefono" id="telefono" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="position-relative form-group">
                                                                        <label for="telefono2" class="col-form-label"
                                                                               style="font-weight: bold; visibility: hidden">Teléfono2</label>
                                                                        <input name="telefono2" id="telefono2" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-row">
                                                                <div class="col-md-12">
                                                                    <div class="position-relative form-group">
                                                                        <label for="email" class="col-form-label"
                                                                               style="font-weight: bold;">Email</label>
                                                                        <input name="email" id="email" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-row">
                                                                <div class="col-md-8">
                                                                    <div class="position-relative form-group">
                                                                        <label for="observaciones" class="col-form-label"
                                                                               style="font-weight: bold;">Observaciones</label>
                                                                        <input name="observaciones" id="observaciones" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <div class="position-relative form-group">
                                                                        <label for="correo" class="col-form-label"
                                                                               style="font-weight: bold;">correo</label>
                                                                        <input name="correo" id="correo" type="text"
                                                                               class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="position-relative row">
                                                                <div class="col-sm-12">
                                                                    <button class="btn btn-primary form-group"
                                                                            onclick="actionCrearPaciente()"
                                                                            style="width: inherit; background-color: #b42d8f;">
                                                                        Guardar
                                                                    </button>
                                                                    <input type="hidden" name="citas_id" id="citas_id"
                                                                           value="">
                                                                    <input type="hidden" name="action" id="action"
                                                                           value="eliminar">
                                                                    <input type="hidden" name="isSubmit" value="true">

                                                                </div>
                                                            </div>
                                                            <div id="lock-modal"></div>
                                                            <div id="loading-circle"></div>
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
                <div class="app-wrapper-footer">
                    <%=MainView.getFooter()%>
                </div>
            </div>
            <!--THEME OPTIONS START-->
            <%=MainView.getThemeSettings()%>

            <!--THEME OPTIONS END-->
        </div>
    </div>

    <div class="app-drawer-overlay d-none animated fadeIn"></div>
    <div class="modal fade" id="avisoBorrarModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLongTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
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
</div>
<!-- START SCRIPTS IMPORTS -->
<!--CORE-->
<%=MainView.getImportsScripts(false,true,false,true,true)%>
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
    const form = $("#insertarPacienteForm");
    const lockModal = $("#lock-modal");
    const loadingCircle = $("#loading-circle");

    var actionCrearPaciente = function () {
        lockModal.css("display", "block");
        loadingCircle.css("display", "block");

        form.children("input").each(function () {
            $(this).attr("readonly", true);
        });
        form.validate();
        if (form.valid()) {
            form.submit();
        }
    };

    $(document).ready(() => {
        <%=jsp_js_var%>
        $("#insertarPacienteForm").validate({
            rules: {
                apellidos:
                    {
                        required:true,
                    },
                nombre:
                    {
                        required:true,
                    },
                NIF:
                    {
                        required:true,
                    },
                telefono:
                    {
                        digits: true,
                        required:true,
                    },

            }, messages: {
                /*nombre: {
                    required: 'Este campo es obligatorio'
                }*/
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `invalid-feedback` class to the error element
                //error.addClass("invalid-feedback");
                if (element.prop("type") === "checkbox") {
                    // $('#aceptarLOPDModal').modal('show');
                    error.appendTo(element.parent("div").next("div"));
                    //error.insertAfter(element.parent('.wrooong'));
                    // element.before('<div class="form-row">').append(error);

                } else {
                    error.addClass("invalid-feedback");
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass("is-invalid").removeClass("is-valid");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).addClass("is-valid").removeClass("is-invalid");
            }
        });
        jQuery.extend(jQuery.validator.messages, {
            required: "Este campo es obligatorio",
            remote: "Please fix this field.",
            email: "Introduzca una direccion de correo valida",
            url: "Please enter a valid URL.",
            date: "Please enter a valid date.",
            dateISO: "Please enter a valid date (ISO).",
            number: "Inserte un numero valido",
            digits: "Inserte solo digitos.",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            accept: "Please enter a value with a valid extension.",
            maxlength: jQuery.validator.format("No introduzca mas de {0} caracteres"),
            minlength: jQuery.validator.format("Introduca al menos {0} caracteres."),
            rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
            range: jQuery.validator.format("Please enter a value between {0} and {1}."),
            max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
            min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
        });

        $('#avisoBorrarModal').on('hidden.bs.modal', function () {
            console.log('x');
            window.location.replace("<%=Settings.contentRootModules%>" + "pacientes/tabla_pacientes.jsp");
        });
    });
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