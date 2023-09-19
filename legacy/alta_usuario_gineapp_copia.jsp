<%@ page import="java.util.Date" %>
<%@ page import="com.disderm.bean.SessionBean" %>
<%@ page import="com.disderm.dao.PacientesDAO" %>
<%@ page import="com.disderm.model.PacientesModel" %>
<%@ page import="com.disderm.utils.FFecha" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.utils.Crypto" %>
<%@ page import="com.disderm.auth.Heimdall" %>
<%@ page import="com.disderm.service.SystemMailService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sessionBean" scope="session" class="com.disderm.bean.SessionBean"/>
<%
    /* HttpSession httpSession = request.getSession();

     SessionBean sBean = (SessionBean)httpSession.getAttribute("sessionBean");
     if ((sBean == null) || (sBean.getUser() == null) || (sBean.getSession_id() == null)) {
     <script type="text/javascript">jQuery(location).attr('href','auth/cerrar_sesion.jsp');</script>
     */
    //} else {

    /*
sBean.getUser().get_first_name()
     */

    response.addHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    String content = "";
    Date fecha_actual = new Date();
    Heimdall heimdall = new Heimdall();
    boolean is_processing = false;
    SystemMailService sms = new SystemMailService();
    boolean isIE11 = false;
    String userAgent = request.getHeader("user-agent");
    String modalAction1 = "";
    String modalAction2 = "";
    String botonModalTerminos = "";
    String botonModalPrivacidad = "";
    if (userAgent.indexOf("MSIE") > -1 || userAgent.equalsIgnoreCase("Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")) {
        isIE11 = true;
    }
    if (isIE11) {
        modalAction1 = "<label class=\"form-check-label\" for=\"chkAceptarTerminos\"> Acepto\n" +
                "                                        <a class=\"app-gineapp-link\"  href=\"javascript:void(0);\" style=\"horiz-align:right\"\n" +
                "                                              data-target=\"#aceptarTerminosModal\" data-toggle=\"modal\"\n" +
                " >los Términos y Condiciones.</a>\n" +
                " </label>";
        modalAction2 = "<label class=\"form-check-label\" for=\"chkAceptarPrivacy\"> Acepto\n" +
                "                                        <a class=\"app-gineapp-link\"  href=\"javascript:void(0);\" style=\"horiz-align:right\"\n" +
                "                                              data-target=\"#aceptarPrivacidadModal\" data-toggle=\"modal\"\n" +
                " >la Política de Privacidad.</a>\n" +
                " </label>";
        botonModalTerminos = "onclick=\"IEmodalAcceptTermsUse()\">";
        botonModalPrivacidad = "onclick=\"IEmodalAcceptPrivacyPolicy()\">";


    } else {
        modalAction1 = "<label class=\"form-check-label\" for=\"chkAceptarTerminos\"> Acepto\n" +
                "                                        <a class=\"app-gineapp-link\"  href=\"javascript:void(0);\" style=\"horiz-align:right\"\n" +
                "                                              onclick=\"openTermsUseModal()\"\n" +
                " >los Términos y Condiciones.</a>\n" +
                " </label>";
        modalAction2 = "<label class=\"form-check-label\" for=\"chkAceptarPrivacy\"> Acepto\n" +
                "                                        <a class=\"app-gineapp-link\"  href=\"javascript:void(0);\" style=\"horiz-align:right\"\n" +
                "                                              onclick=\"openPrivacyUseModal()\"\n" +
                " >la Política de Privacidad.</a>\n" +
                " </label>";

        botonModalTerminos = "onclick=\"modalAcceptTermsUse()\">";
        botonModalPrivacidad = "onclick=\"modalAcceptPrivacyPolicy()\">";


    }

    if ((request.getParameter("isSubmit") == null) ? false : true) {
        is_processing = true;
        if (!heimdall.existAppUser(request.getParameter("app_email"))) {
            PacientesDAO pacientesDAO = new PacientesDAO();
            PacientesModel pacientesModel = new PacientesModel();
            pacientesModel.set_first_name(request.getParameter("first_name"));
            pacientesModel.set_last_name(request.getParameter("last_name"));
            Date date_fecha_nac = FFecha.getDate(request.getParameter("fecha_nac"), FFecha.sdf_ddMMyyyy);
            if (date_fecha_nac == null) {
                date_fecha_nac = FFecha.getDate("01/01/2020", FFecha.sdf_ddMMyyyy);
            }
            pacientesModel.set_birthday(date_fecha_nac);

            pacientesModel.set_nif(request.getParameter("nif"));
            pacientesModel.set_historia_id(request.getParameter("historia_id"));
            pacientesModel.set_codpac_id(request.getParameter("codpac_id"));
            pacientesModel.set_app_fecha_parto(request.getParameter("app_fecha_parto"));
            pacientesModel.set_app_telefono_movil(request.getParameter("app_telefono_movil"));
            pacientesModel.set_app_email(request.getParameter("app_email"));
            pacientesModel.set_app_username(request.getParameter("app_email"));
            pacientesModel.set_app_password(Crypto.encrypt(request.getParameter("password"), "SHA-512"));
            pacientesModel.set_app_enabled(1);
            pacientesModel.set_app_fecha_alta_sistema(fecha_actual);
            pacientesModel.set_app_fecha_alta_sistema_string(FFecha.getString(fecha_actual, FFecha.sdf_ddMMyyyyBarHHmm));
            Long insertID = pacientesDAO.insertPacientes(pacientesModel);
            if (insertID != 0L) {
                sms.sendAltaAppdEmail(pacientesModel.get_app_email(), request.getParameter("password"));
                content = "  <h4>Operaci&oacute;n completada correctamente</h4><span>Su proceso de alta ha sido realizado correctamente, vuelva a la aplicación para acceder y revise su buzon de correo electr&oacute;nico</span>\n\n<div class=\" mt-4 d-flex align-items-center\"><div class=\"ml-auto\"><button class=\"mb-2 mr-2 btn-icon btn-pill btn btn-primary\" onclick=\"goHome()\"><i class=\"lnr-chevron-left-circle btn-icon-wrapper\"> </i>Volver</button></div></div>";
            }
        } else {
            content = "  <h4>No se pudo completar la operaci&oacute;n</h4><span>El usuario ya existe, solicite recordar su clave si la ha olvidado: <a href=\"https://intranet.gineapp.com:10000/backoffice/recuperar-clave\">Recuperar clave.</a></span>\n\n<div class=\" mt-4 d-flex align-items-center\"><div class=\"ml-auto\"><button class=\"mb-2 mr-2 btn-icon btn-pill btn btn-primary\" onclick=\"goHome()\"><i class=\"lnr-chevron-left-circle btn-icon-wrapper\"> </i>Volver</button></div></div>";
        }

    }
%>

<!DOCTYPE html>
<html lang="es">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="es" http-equiv="Content-Language">

    <title>.:GINEAPP:. Formulario de alta de usuario</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no"
          name="viewport">
    <meta content="GINEAPP ALTA USUARIO" name="description">

    <!-- Disable tap highlight on IE -->
    <meta content="no" name="msapplication-tap-highlight">
    <meta http-equiv="x-ua-compatible" content="IE=edge; charset=UTF-8">
    <link href="css_framework.css" rel="stylesheet">
    <!--<link href="assets/css/bootstrap-datepicker3.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/pikaday/css/pikaday.css">
    <link rel="icon" href="assets/images/gineapp/favicon.ico" type="image/ico"/>
    <script type="text/javascript" src="assets/js/jquery.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <!--<script type="text/javascript" src="js_framework.js?version=4"></script>-->
    <script type="text/javascript" src="assets/js/moment.js?version=4"></script>
    <script type="text/javascript" src="assets/js/validation/jquery.validate.js"></script>

    <!--
    <script type="text/javascript" src="assets/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap-datepicker.es.min.js"></script> -->

    <style>
        .app-logo-gineapp {
            align-content: center;
            display: flex;
            padding-bottom: 30px;
            justify-content: center;
        }

        .terminos {
            align-content: center;
        }

        .app-legal-gineapp {
            align-content: center;
            display: flex;
            margin: 0 auto;
            float: none;
            padding-bottom: 30px;
            justify-content: center;
        }

        /*
        Centra los elementos
        */
        .app-centerblock-gineapp {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .app-centerblock-gineapp label, input {
            display: inline-block !important;
            vertical-align: middle !important;
            margin-top: 0 !important;
            position: relative !important;
        }

        .app-centerblock-align-left {
            height: 100%;
            vertical-align: middle !important;
            /*display: inline-block !important;*/
            align-items: center;
            padding-left: 30px;
            /*justify-content: center;*/
        }

        .app-centerblock-gineapp-align-left input {
            display: inline-block !important;
            vertical-align: middle !important;
            margin-top: 0 !important;
            margin-left: -30px;
            position: relative !important;
        }

        .app-centerblock-gineapp-align-left label {
            display: inline-block !important;
            vertical-align: middle !important;
            margin-top: 0 !important;
            margin-left: 50px;
            position: relative !important;
        }

        .app-footer-gineapp {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: "Roboto", sans-serif;
            font-size: 12px;
            font-style: italic;
        }

        a {
            color: #da35ca !important;
        }

        .modal-body {
            height: 250px;
            width: 100%;
            overflow-y: auto;
        }

        @media screen and (max-width: 1024px) {
            .bg-premium-dark, .slide-img-bg, .slider-light {
                display: none;
            }
        }

    </style>
</head>


<body>

<div class="app-container app-theme-white body-tabs-shadow">
    <div class="app-container">
        <div class="h-100">
            <div class="h-100 no-gutters row">
                <div class="h-100 d-md-flex d-sm-block bg-white justify-content-center align-items-center col-md-12 col-lg-7">
                    <div class="mx-auto app-login-box col-sm-12 col-md-10 col-lg-9">
                        <div class=""></div>

                        <div class="app-logo-gineapp">
                            <img alt="Gineapp" height="82" longdesc="https://www.gineapp.com"
                                 src="assets/images/gineapp/logo_gineapp_medium.png" width="340"/>
                            <div></div>
                        </div>
                        <%
                            if (!is_processing) {
                        %>
                        <h4>
                            <div>Bienvenido</div>
                            <span>Solo le tomar&aacute; <span
                                    class="text-success">unos segundos</span> en crear su cuenta.</span></h4>
                        <div>
                            <form name="appAltaUsuario" id="appAltaUsuario" class=""
                                  action="<%=request.getRequestURI()%>" method="post">
                                <div class="form-row">
                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class="" for="first_name">
                                            Nombre</label><input class="form-control" id="first_name" name="first_name"
                                                                 placeholder="Nombre" type="text">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class="" for="last_name">
                                            Apellidos</label><input class="form-control" id="last_name" name="last_name"
                                                                    placeholder="Apellidos" type="text" required></div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class=""
                                                                                         for="fecha_nac"><span
                                                class="text-danger">*</span> Fecha de nacimiento</label>
                                            <!-- <input
                                                    class="form-control" id="fecha_nac"
                                                    name="fecha_nac" type="date" placeholder="dd/mm/aaaa"
                                                    pattern="(^(((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d$)|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)"
                                                    autocomplete="on" required> -->

                                            <%
                                                if (isIE11)
                                                {
                                            %>
                                            <input type="text" class="form-control" id="fecha_nac"
                                                   placeholder="dd/mm/aaaa" required>
                                            <%
                                                }
                                                else
                                                {
                                            %>
                                            <input
                                                    class="form-control" id="fecha_nac"
                                                    name="fecha_nac" type="date" placeholder="dd/mm/aaaa"
                                                    pattern="(^(((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d$)|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)"
                                                    autocomplete="on" required>
                                            <%
                                                }
                                            %>
                                        </div>
                                    </div>
                                    <!-- pattern="(^(((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d$)|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)" -->


                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class=""
                                                                                         for="app_fecha_parto"> Fecha
                                            prevista de parto</label><input
                                                placeholder="Fecha prevista" class="form-control" id="app_fecha_parto"
                                                name="app_fecha_parto" type="date"></div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class=""
                                                                                         for="app_telefono_movil"><span
                                                class="text-danger">*</span> Tel&eacute;fono</label><input
                                                class="form-control" id="app_telefono_movil" name="app_telefono_movil"
                                                placeholder="Teléfono movil"
                                                type="number"></div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class=""
                                                                                         for="nif"><span
                                                class="text-danger">*</span> DNI</label><input
                                                class="form-control" id="nif" name="nif"
                                                placeholder="DNI"
                                                type="text" required></div>
                                    </div>
                                </div>
                                <div class="form-row">

                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class="" for="app_email"><span
                                                class="text-danger">*</span> Introduzca su correo
                                            electronico</label><input
                                                class="form-control" id="app_email" name="app_email"
                                                placeholder="Escriba su correo" type="email">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="position-relative form-group"><label class=""
                                                                                         for="confirm_app_email"><span
                                                class="text-danger">*</span> Vuelva a escribir su direccion de
                                            correo</label><input
                                                class="form-control" id="confirm_app_email" name="confirm_app_email"
                                                placeholder="Vuelva a escribir su correo" type="text"></div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-md-6">
                                        <div class="position-relative form-group">
                                            <label class="" for="password"><span
                                                    class="text-danger">*</span> Clave de acceso</label>
                                            <input id="password" placeholder="Introduzca nueva clave" type="password"
                                                   class="form-control" required name=password>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="position-relative form-group">
                                            <label class="" for="confirm_password"><span
                                                    class="text-danger">*</span> Repita clave</label>
                                            <input id="confirm_password" placeholder="Repita nueva clave"
                                                   type="password"
                                                   class="form-control" name=confirm_password>
                                        </div>
                                    </div>
                                </div>
                                <div class="app-centerblock-align-left">
                                    <input style="horiz-align:right" class="form-check-input" id="chkAceptarTerminos"
                                           name="chkAceptarTerminos"
                                           type="checkbox">
                                    <%=modalAction1%>
                                </div>
                                <div class="app-centerblock-align-left">
                                </div>
                                <div class="app-centerblock-align-left">
                                    <input style="horiz-align:right" class="form-check-input" id="chkAceptarPrivacidad"
                                           name="chkAceptarPrivacidad"
                                           type="checkbox">
                                    <%=modalAction2%>
                                </div>
                                <div class="app-centerblock-align-left">
                                </div>
                                <div class="mt-4 d-flex align-items-center">
                                    <div class="ml-auto">
                                        <button id="enviar" class="mb-2 mr-2 btn-icon btn-pill btn btn-primary"
                                                onClick="go()">
                                            <i class="lnr-enter btn-icon-wrapper"> </i>
                                            Procesar alta
                                        </button>
                                    </div>
                                </div>
                                <input type="hidden" name="isSubmit" value="true">
                            </form>
                        </div>
                        <%
                            } else {
                                out.print(content);
                            }
                        %>
                        <div class="app-footer-gineapp">
                            <footer>
                                <small>&copy; Copyright 2020, GineApp <%=Varios.getVersion()%>
                                </small>
                            </footer>
                        </div>
                    </div>
                </div>
                <div class="d-lg-flex d-xs-none col-lg-5">
                    <div class="slider-light">
                        <div class="slick-slider">
                            <div class="h-100 d-flex justify-content-center align-items-center bg-premium-dark"
                                 tabindex="-1">
                                <div class="slide-img-bg"
                                     style="background-image: url('assets/images/gineapp/register_background.jpg');"></div>
                                <div class="slider-content"><h3>Gineapp su Ecografía en su Móvil toda una
                                    revolución</h3>
                                    <p>Calidad HD, comparta con su familiares a través de nuestra App </p></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="aceptarTerminosModal" name="aceptarTerminosModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLongTitle"
             aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">T&eacute;rminos y Condiciones de Uso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <%=Varios.getHTMLTerminosCondiciones()%>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" <%=botonModalTerminos%>Acepto
                        </button>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="aceptarPrivacidadModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLongTitle2"
             aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="privacidadModalLongTitle">Politica de Privacidad</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p style="line-height: 1;"><span
                                style="font-family: Tahoma, Geneva, sans-serif; font-size: 16px;">En Europa y en Espa&ntilde;a existen normas de protecci&oacute;n de datos pensadas para proteger su informaci&oacute;n personal de obligado cumplimiento para nuestra entidad.</span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Por ello, es muy importante para nosotros que entienda perfectamente qu&eacute; vamos a hacer con los datos personales que le pedimos.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">As&iacute;, seremos transparentes y le daremos el control de sus datos, con un lenguaje sencillo y opciones claras que le permitir&aacute;n decidir qu&eacute; haremos con su informaci&oacute;n personal.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Por favor, si una vez le&iacute;da la presente informaci&oacute;n le queda alguna duda, no dude en preguntarnos.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Muchas gracias por su colaboraci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&nbsp;</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">1. &nbsp; &nbsp; &iquest;Qui&eacute;nes somos?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Nuestra denominaci&oacute;n: Centro de Ginecolog&iacute;a y Diagnostico Prenatal Doctor Chac&oacute;n S.L.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Nuestro CIF / NIF: B91559724</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Nuestra actividad principal: ginecolog&iacute;a y obstetricia</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Nuestra direcci&oacute;n: C/ Asunci&oacute;n N&ordm; 84, 2&ordm;C, CP 41011, Sevilla (Sevilla)</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Nuestro tel&eacute;fono de contacto: 954032050</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Nuestra direcci&oacute;n de correo electr&oacute;nico de contacto:&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">soporte@gineapp.com</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Nuestra p&aacute;gina web: www.ginecologiaprenal.com</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&nbsp;</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Para su confianza y seguridad, le informamos que somos una entidad inscrita en el siguiente Registro Mercantil /Registro P&uacute;blico: &nbsp;REGISTRO MERCANTIL DE SEVILLA , AL TOMO 4469,FOLIO 130 ,HOJA69293.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&nbsp;</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Estamos a su disposici&oacute;n, no dude en contactar con nosotros.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&nbsp;</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Legitimaci&oacute;n para el tratamiento de datos</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">La base legal para el tratamiento de sus datos es el registro de los usuarios en la Aplicaci&oacute;n y la relaci&oacute;n contractual existente.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">El consentimiento de los usuarios es la base leg&iacute;tima del tratamiento de los datos que hacemos para las finalidades explicadas concretamente en esta Pol&iacute;tica de Privacidad.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Finalidades del tratamiento de los datos personales</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&nbsp;</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">La finalidad del tratamiento de los datos es la transmisi&oacute;n de informaci&oacute;n de sobre Im&aacute;genes, Historial M&eacute;dico, Datos biom&eacute;tricos, y aquellos de categor&iacute;a de salud y especiales derivados de las consultas m&eacute;dicas. Comprobar &nbsp;que es correcto y no hay que a&ntilde;adir nada m&aacute;s.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&nbsp;</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;&iquest;Para qu&eacute; vamos a usar sus datos?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&nbsp;</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Con car&aacute;cter general, sus datos personales ser&aacute;n usados para poder relacionarnos con usted y poder prestarle nuestros servicios.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Asimismo, tambi&eacute;n pueden ser usados para otras actividades, como enviarle publicidad o promocionar nuestras actividades.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">5. &nbsp; &nbsp; &iquest;Por qu&eacute; necesitamos usar sus datos?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Sus datos personales son necesarios para poder relacionarnos con usted y poder prestarle nuestros servicios. En este sentido, pondremos a su disposici&oacute;n una serie de casillas que le permitir&aacute;n decidir de manera clara y sencilla sobre el uso de su informaci&oacute;n personal. &nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">6. &nbsp; &nbsp; &iquest;Qui&eacute;n va a conocer la informaci&oacute;n que le pedimos?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Los datos de los usuarios de la Web no se transmitir&aacute;n a ning&uacute;n tercero a excepci&oacute;n de que:</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;sea estrictamente necesario para la prestaci&oacute;n de los servicios solicitados, en caso que GINEAPP colabore con terceros</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;cuando GINEAPP tenga autorizaci&oacute;n expresa e inequ&iacute;voca por parte del usuario</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;cuando se solicite por parte de autoridad competente en el ejercicio de sus funciones (para investigar, prevenir o tomar acciones relativas a acciones ilegales)</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;si lo requiere la ley. GINEAPP mantendr&aacute; un registro de transmisi&oacute;n de datos incluyendo&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">o &nbsp; &nbsp;fecha de transmisi&oacute;n de datos,&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">o &nbsp; &nbsp;una entidad a la que se transmiten los datos,&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">o &nbsp; &nbsp;la base legal para la transmisi&oacute;n de datos,&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">o &nbsp; &nbsp;la finalidad de la transmisi&oacute;n de datos y&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">o &nbsp; &nbsp;el alcance de la transmisi&oacute;n de datos.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">7. &nbsp; &nbsp; &iquest;C&oacute;mo vamos a proteger sus datos?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Protegeremos sus datos con medidas de seguridad eficaces en funci&oacute;n de los riesgos que conlleve el uso de su informaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Adoptando las medidas de &iacute;ndole t&eacute;cnica y organizativas reglamentariamente establecidas que garanticen la seguridad de los datos de car&aacute;cter personal de los usuarios y eviten su alteraci&oacute;n, p&eacute;rdida, tratamiento o acceso no autorizado, habida cuenta del estado de la tecnolog&iacute;a, la naturaleza de los datos almacenados y los riesgos a que est&eacute;n expuestos, todo ello de conformidad con la legislaci&oacute;n aplicable en materia de protecci&oacute;n de datos.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Para ello, nuestra entidad ha aprobado una Pol&iacute;tica de Protecci&oacute;n de Datos y se realizan controles y auditor&iacute;as anuales para verificar que sus datos personales est&aacute;n seguros en todo momento.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Sin perjuicio de lo anterior, es responsabilidad de los usuarios custodiar debidamente las claves y contrase&ntilde;as para su acceso como usuarios registrados en la App, impidiendo el uso indebido de las mismas por parte de terceros. Sin embargo no nos hacemos responsable del mal uso de las claves y contrase&ntilde;as de acceso que lleven a cabo los usuarios.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">8. &nbsp; &nbsp; &iquest;Durante cu&aacute;nto tiempo vamos a conservar sus datos?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Conservaremos sus datos durante nuestra relaci&oacute;n y mientras nos obliguen las leyes. Una vez finalizados los plazos legales aplicables, procederemos a eliminarlos de forma segura y respetuosa con el medio ambiente.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">9. &nbsp; &nbsp; &iquest;Cu&aacute;les son sus derechos de protecci&oacute;n de datos?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">En cualquier momento puede dirigirse a nosotros para saber qu&eacute; informaci&oacute;n tenemos sobre usted, rectificarla si fuese incorrecta y eliminarla una vez finalizada nuestra relaci&oacute;n, en el caso de que ello sea legalmente posible.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Tambi&eacute;n tiene derecho a solicitar el traspaso de su informaci&oacute;n a otra entidad. Este derecho se llama &ldquo;portabilidad&rdquo; y puede ser &uacute;til en determinadas situaciones.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Para solicitar alguno de estos derechos, deber&aacute; realizar una solicitud escrita a nuestra direcci&oacute;n, junto con una fotocopia de su DNI, para poder identificarle.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">En las oficinas de nuestra entidad disponemos de formularios espec&iacute;ficos para solicitar dichos derechos y le ofrecemos nuestra ayuda para su cumplimentaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Para saber m&aacute;s sobre sus derechos de protecci&oacute;n de datos, puede consultar la &nbsp;p&aacute;gina web de la Agencia Espa&ntilde;ola de Protecci&oacute;n de Datos (www.agpd.es).</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">10. &nbsp;&iquest;Puede retirar su consentimiento si cambia de opini&oacute;n en un momento posterior?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Usted puede retirar su consentimiento si cambia de opini&oacute;n sobre el uso de sus datos en cualquier momento.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">As&iacute; por ejemplo, si usted en su d&iacute;a estuvo interesado/a en recibir publicidad de nuestros productos o servicios, pero ya no desea recibir m&aacute;s publicidad, puede hac&eacute;rnoslo constar a trav&eacute;s del formulario de oposici&oacute;n al tratamiento disponible en las oficinas de nuestra entidad.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">11. &nbsp;En caso de que entienda que sus derechos han sido desatendidos, &iquest;d&oacute;nde puede formular una reclamaci&oacute;n?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">En caso de que entienda que sus derechos han sido desatendidos por nuestra entidad, puede formular una reclamaci&oacute;n en la Agencia Espa&ntilde;ola de Protecci&oacute;n de Datos, a trav&eacute;s de alguno de los medios siguientes:</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">o &nbsp; Sede electr&oacute;nica: www.agpd.es</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;Direcci&oacute;n postal:</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Agencia Espa&ntilde;ola de Protecci&oacute;n de Datos C/ Jorge Juan, 6 28001-Madrid &nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">&bull; &nbsp; &nbsp;V&iacute;a telef&oacute;nica:</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Telf. 901 100 099&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Telf. 91 266 35 17</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Formular una reclamaci&oacute;n en la Agencia Espa&ntilde;ola de Protecci&oacute;n de Datos no conlleva ning&uacute;n coste y no es necesaria la asistencia de abogado ni procurador.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">12. &nbsp;&iquest;Elaboraremos perfiles sobre usted?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Nuestra pol&iacute;tica es no elaborar perfiles sobre los usuarios de nuestros servicios.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">13. &nbsp;&iquest;Usaremos sus datos para otros fines?</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Nuestra pol&iacute;tica es no usar sus datos para otras finalidades distintas a las que le hemos explicado. Si, no obstante, necesit&aacute;semos usar sus datos para actividades distintas, siempre le solicitaremos previamente su permiso a trav&eacute;s de opciones claras que le permitir&aacute;n decidir al respecto.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">--------</span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"><br></span></span></p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Las presentes Condiciones Generales de Uso tienen por objeto regular las condiciones de acceso y utilizaci&oacute;n de la aplicaci&oacute;n, as&iacute; como los servicios proporcionados a trav&eacute;s de dispositivos m&oacute;viles del usuario (en adelante, la &ldquo;Aplicaci&oacute;n&rdquo;), por parte de su titular, Centro de Ginecolog&iacute;a y Diagn&oacute;stico Prenatal Doctor Chac&oacute;n S.L., con domicilio en C/ Asunci&oacute;n N&ordm; 84, 2&ordm;C, CP 41011, Sevilla, inscrita en el Registro Mercantil de al tomo 4469, folio 130 hoja 69283, en adelante GineApp</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">GineApp pone a disposici&oacute;n de los usuarios la direcci&oacute;n de correo soporte@gineapp.com &nbsp;para que puedan plantear cualquier duda sobre las presentes Condiciones Generales de Uso y el funcionamiento de la Aplicaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">A los efectos de las presentes Condiciones Generales de Uso, se entender&aacute; por usuario la persona que acceda, navegue, utilice o participe en los servicios de GineApp, de conformidad con lo dispuesto a continuaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Realizador: _______________________________________________, (Espa&ntilde;a).</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">1. ACEPTACI&Oacute;N DE LAS CONDICIONES GENERALES DE USO</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">El acceso y navegaci&oacute;n de un usuario por la Aplicaci&oacute;n implica la aceptaci&oacute;n de forma plena, expresa y sin reservas de las presentes Condiciones Generales de Uso. Cualquier persona que no acepte estas Condiciones Generales de Uso deber&aacute; no podr&aacute; utilizar la Aplicaci&oacute;n y/o los servicios de La Aplicaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Asimismo, la utilizaci&oacute;n por parte del usuario de los servicios de GineApp supone, en todo caso, la adhesi&oacute;n a las presentes Condiciones Generales de Uso en la versi&oacute;n publicada en el momento mismo de utilizaci&oacute;n de la Aplicaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Mediante la aceptaci&oacute;n de las presentes Condiciones Generales de Uso el usuario declara que es una persona mayor de edad y que tiene derecho, capacidad y legitimaci&oacute;n para prestar su consentimiento a estas Condiciones Generales de Uso y para dar cumplimiento a las mismas. Podr&aacute;n ser usuarios de la Aplicaci&oacute;n todas las personas f&iacute;sicas o jur&iacute;dicas, independientemente de su nacionalidad o pa&iacute;s de residencia.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">2. SERVICIOS Y FUNCIONAMIENTO DE GineApp</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">La Aplicaci&oacute;n ha sido creada para, ofrecer informaci&oacute;n de salud de forma estructurada Los servicios incluidos en GineApp son:</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Fotograf&iacute;as de la gestaci&oacute;n , recordatorio de citas, publicidad.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">3. OBLIGACIONES DE LOS USUARIOS</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Toda la informaci&oacute;n que los usuarios faciliten a trav&eacute;s de la Aplicaci&oacute;n deber&aacute; ser veraz. A estos efectos, los usuarios garantizan la autenticidad de todos aquellos datos que comuniquen como consecuencia de la cumplimentaci&oacute;n de los formularios del LA TOMA DE DATOS PERSONALES</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Los usuarios deber&aacute;n facilitar informaci&oacute;n a GineApp de la que sean titulares, evitando la vulneraci&oacute;n de derechos de terceros y, en particular, derechos de propiedad intelectual o industrial y derechos de imagen. En el supuesto de que los usuarios incumplan dicha obligaci&oacute;n, responder&aacute;n frente a terceros de los da&ntilde;os y perjuicios ocasionados, manteniendo indemne en todo momento a GineApp.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Los usuarios se obligan a respetar las leyes aplicables y los derechos de terceros al utilizar los contenidos y servicios de la Aplicaci&oacute;n. Asimismo los usuarios se comprometen a utilizar la Aplicaci&oacute;n de conformidad con las presentes Condiciones Generales de Uso, las buenas pr&aacute;cticas de mercado y el orden p&uacute;blico.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">De modo ilustrativo y no limitativo, los usuarios no podr&aacute;n:</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">- Facilitar informaci&oacute;n falsa, denigrante, injuriosa, calumniosa o que afecte a los derechos al honor, intimidad y propia imagen de terceros; (En el caso que exista Chat)</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">- Bloquear, sobrescribir, modificar o copiar los contenidos de la Aplicaci&oacute;n, a no ser que ello sea necesario para la correcta utilizaci&oacute;n de los servicios de GineApp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">- Toda acci&oacute;n apta para perjudicar la funcionalidad de la infraestructura de la Aplicaci&oacute;n, especialmente para sobrecargarla;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">- Los usuarios ser&aacute;n responsables en caso de incumplimiento de las presentes Condiciones Generales de Uso y de las conductas que da&ntilde;en, inutilicen, sobrecarguen, deterioren o impidan la normal utilizaci&oacute;n de la Aplicaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">GineApp podr&aacute; denegar o retirar el acceso a la Aplicaci&oacute;n a los usuarios que incumplan las presentes Condiciones Generales de Uso, sin necesidad de preaviso alguno. Los usuarios que incumplan las presentes Condiciones Generales de Uso responder&aacute;n de todos los da&ntilde;os y perjuicios que se deriven de su actuaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">4. RESPONSABILIDAD DE GineApp</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">4.1. &nbsp; &nbsp; Exclusi&oacute;n de responsabilidad</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">GineApp se reserva el derecho de editar, actualizar, modificar, suspender, eliminar o finalizar los servicios ofrecidos por la Aplicaci&oacute;n, incluyendo todo o parte de su contenido, sin necesidad de previo aviso, as&iacute; como de modificar la forma o tipo de acceso a esta.  Las posibles causas de modificaci&oacute;n pueden tener lugar por motivos tales como su adaptaci&oacute;n a las posibles novedades legislativas y cambios en la propia Aplicaci&oacute;n, as&iacute; como a las que se puedan derivar de los c&oacute;digos tipos existentes en la materia o por motivos estrat&eacute;gicos o corporativos.  GineApp no ser&aacute; responsable del uso de la APLICACI&Oacute;N por un menor de edad, siendo la descarga y uso de la APLICACI&Oacute;N exclusiva responsabilidad del usuario.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">4.2. &nbsp; &nbsp; Responsabilidad por los servicios ofrecidos.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Los servicios ofrecidos por GineApp son de car&aacute;cter exclusivamente informativo para los usuarios.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Bajo ninguna circunstancia los usuarios deben utilizar la informaci&oacute;n ofrecida por GineApp para automedicarse.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">GineApp &nbsp;excluye cualquier tipo de responsabilidad por los da&ntilde;os y perjuicios de toda naturaleza que puedan deberse a la falta de veracidad, exactitud, exhaustividad y/o actualidad de los contenidos.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">La Informaci&oacute;n transmitida por GineApp est&aacute;n basan en la veracidad y exactitud de la informaci&oacute;n facilitada por el usuario. Consiguientemente, GineApp no se hace responsable de la falta de veracidad, exactitud y exhaustividad de los contenidos debida al incumplimiento del usuario de facilitar informaci&oacute;n veraz y exacta a trav&eacute;s de la consultas.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">GineApp no recomienda ni valida el uso de un f&aacute;rmaco en concreto o un determinado diagn&oacute;stico cl&iacute;nico, ni recomienda terapia alguna. GineApp es una fuente de informaci&oacute;n.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">4.3. &nbsp; &nbsp; Responsabilidad por el funcionamiento de la Aplicaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">GineApp no se hace responsable de los perjuicios que se pudieran derivar de interferencias, omisiones, interrupciones, virus inform&aacute;ticos, aver&iacute;as y/o desconexiones en el funcionamiento operativo de este sistema electr&oacute;nico o en los aparatos y equipos inform&aacute;ticos de los usuarios, motivadas por causas ajenas a GineApp, que impidan o retrasen la prestaci&oacute;n de los servicios o la navegaci&oacute;n por la Aplicaci&oacute;n, ni de los retrasos o bloqueos en el uso causados por deficiencias o sobrecargas de Internet o en otros sistemas electr&oacute;nicos, ni de la imposibilidad de dar el servicio o permitir el acceso por causas no imputables a GineApp, debidas a los usuarios, a terceros, o a supuestos de fuerza mayor.&nbsp;</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">GineApp no se hace responsable por los da&ntilde;os y perjuicios de toda naturaleza que pudieran derivarse de la disponibilidad y continuidad t&eacute;cnica del funcionamiento de la Aplicaci&oacute;n. En cualquier caso, GineApp llevar&aacute; a cabo todas las actuaciones necesarias para restablecer sus servicios en caso de fallo t&eacute;cnico.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Todo usuario de esta Aplicaci&oacute;n se compromete a mantener indemne a GineApp de cualquier reclamaci&oacute;n derivada de problemas surgidos por las causas descritas en el presente apartado.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">4.4. &nbsp; &nbsp; Responsabilidad de uso</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">La responsabilidad de uso de la APLICACI&Oacute;N corresponde solo al usuario. Salvo lo establecido en estos T&eacute;rminos y Condiciones, GineApp no es responsable de ninguna p&eacute;rdida o da&ntilde;o que se produzca en relaci&oacute;n con la descarga o el uso de la APLICACI&Oacute;N, tales como los producidos a consecuencia de fallos, aver&iacute;as o bloqueos en el funcionamiento de la APLICACI&Oacute;N (por ejemplo, y sin car&aacute;cter limitativo: error en las l&iacute;neas de comunicaciones, defectos en el hardware o software de la APLICACI&Oacute;N o fallos en la red de Internet). Igualmente, GineApp tampoco ser&aacute; responsable de los da&ntilde;os producidos a consecuencia de un uso indebido o inadecuado de la APLICACI&Oacute;N por parte de los usuarios</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">5. DERECHOS DE PROPIEDAD INDUSTRIAL E INTELECTUAL</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">Los derechos de propiedad intelectual e industrial sobre la APLICACI&Oacute;N son titularidad GineApp, correspondi&eacute;ndole el ejercicio exclusivo de los derechos de explotaci&oacute;n de los mismos en cualquier forma y, en especial, los derechos de reproducci&oacute;n, distribuci&oacute;n, comunicaci&oacute;n p&uacute;blica y transformaci&oacute;n.</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;"> Los terceros titulares de derechos de propiedad intelectual e industrial sobre fotograf&iacute;as, logotipos, y cualesquiera otros s&iacute;mbolos o contenidos incluidos en la APLICACI&Oacute;N han concedido las correspondientes autorizaciones para su reproducci&oacute;n, distribuci&oacute;n y puesta a disposici&oacute;n del p&uacute;blico.  El usuario reconoce que la reproducci&oacute;n, modificaci&oacute;n, distribuci&oacute;n, comercializaci&oacute;n, descompilaci&oacute;n, desensamblado, utilizaci&oacute;n de t&eacute;cnicas de ingenier&iacute;a inversa o de cualquier otro medio para obtener el c&oacute;digo fuente, transformaci&oacute;n o publicaci&oacute;n de cualquier resultado de pruebas de referencias no autorizadas de cualquiera de los elementos y utilidades integradas dentro del desarrollo constituye una infracci&oacute;n de los derechos de propiedad intelectual de GineApp, oblig&aacute;ndose, en consecuencia, a no realizar ninguna de las acciones mencionadas</span></span>
                        </p>
                        <p style="line-height: 1;"><span style="font-size: 16px;"><span
                                style="font-family: Tahoma,Geneva, sans-serif;">7. LEY APLICABLE Y JURISDICCI&Oacute;N</span></span>
                        </p>
                        <p style="line-height: 1;"><span
                                style="font-family: Tahoma, Geneva, sans-serif; font-size: 16px;">El usuario acepta que la legislaci&oacute;n aplicable y los Juzgados y Tribunales competentes para conocer de las divergencias derivadas de la interpretaci&oacute;n o aplicaci&oacute;n de este clausulado son los espa&ntilde;oles, y se somete, con renuncia expresa a cualquier otro fuero, a los juzgados y tribunales m&aacute;s cercanos a la ciudad de SEVILLA. </span>
                        </p>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" <%=botonModalPrivacidad%>Acepto

                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- Modal -->


<script>
    // Form Validation
    <%
    if (isIE11)
        {
    %>
    jQuery.validator.addMethod("validDate", function (value, element) {
        var fecha_valid = moment(value).isValid();
        console.log("value" + fecha_valid);
        console.log("value" + value);
        var years = moment().diff(moment(value), 'years', false);
        console.log("diferencia años:" + years);
        return true;
        // return this.optional(element) || moment(value,"DD/MM/YYYY").isValid() && (years >= 14);
    }, "Introduzca una fecha de nacimiento valida (DD/MM/AAAA)");


    $.validator.addMethod("dateFormat",
        function (value, element) {
            console.log(value);
            return value.match(/^dd?-dd?-dd$/);
        },
        "Please enter a date in the format dd-mm-yyyy.");

    function parseDMY(value) {
        var date = value.split("/");
        var d = parseInt(date[0], 10),
            m = parseInt(date[1], 10),
            y = parseInt(date[2], 10);
        console.log(new Date(y, m - 1, d));
        d = new Date(y, m - 1, d);
        console.log(!!(d && (d.getMonth() + 1) == date[1] && d.getDate() == Number(date[0])));
        //return new Date(y, m - 1, d);
        return !!(d && (d.getMonth() + 1) == date[1] && d.getDate() == Number(date[0]));
    }
    <% }
    else
    {%>



    <%
    }
    %>
    $(document).ready(() => {

        /** beta **/
        /*  $.validator.methods.date = function(value, element) {
               console.log("peusto de nuevo" + value);
               parseDMY(value);
               return this.optional(element) || parseDMY(value);
           }; */

        $("#appAltaUsuario").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 8
                },
                confirm_password: {
                    required: true,
                    minlength: 8,
                    equalTo: "#password"
                },
                first_name: {
                    required: true,
                    minlength: 3
                },
                last_name: {
                    required: true,
                    minlength: 3
                },
                app_email: {
                    required: true,
                    minlength: 5
                },
                confirm_app_email: {
                    required: true,
                    minlength: 5,
                    equalTo: "#app_email"
                },
                app_telefono_movil: {
                    required: true,
                    minlength: 9
                },
                nif: {
                    required: true,
                    minlength: 9
                },
                /* fecha_nac: {
                     date: true
                 },*/
                chkIceptarLOPD: {
                    required: true
                },
                chkIceptarPrivacidad: {
                    required: true
                }
            },
            messages: {
                app_email: {
                    required: "El correo electronico es obligatorio",
                    email: "Introduzca una direccion de correo valida"
                },
                confirm_app_email: {
                    required: "Confirme su correo electronico",
                    equalTo: "Los correos electronicos deben de coincidir"
                },
                first_name: {
                    required: "Por favor introduzca su nombre",
                    minlength: "Por favor introduzca su nombre"
                },
                last_name: {
                    required: "Por favor introduzca su apellido",
                    minlength: "Por favor introduzca su apellido"
                },
                app_telefono_movil: {
                    required: "Por favor introduzca su telefono movil",
                    minlength: "Introduzca un numero de telefono valido"
                },
                password: {
                    required: "Por favor introduzca una clave",
                    minlength: "Su clave debe de contener al menos 8 caracteres"
                },
                confirm_password: {
                    required: "Por favor repita su correo electronico",
                    equalTo: "Sus claves no coinciden",
                    minlength: "Su clave debe de contener al menos 8 caracteres"
                },
                nif: {
                    required: "Introduzca su documento de identidad",
                    minlength: "Introduzca un numero de documento valido"
                },
                chkIceptarLOPD: {
                    required: "Acepte las condiciones de uso por favor"
                },
                chkIceptarPrivacidad: {
                    required: "Acepte la Politica de Privacidad"
                },
                fecha_nac: {
                    required: "Debe de introducir su fecha nacimiento",
                    date: "Introduzca una fecha de nacimiento valida",

                }
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


    });
    var x = $("#aceptarTerminosModal");
    var y = $("#aceptarPrivacidadModal");

    x.on("hidden.bs.modal", function () {
        console.log("hidden");

    });

    function openTermsUseModal() {
        x.modal({backdrop: false, show: true});

    }

    function openPrivacyUseModal() {
        y.modal({backdrop: false, show: true});

    }

    function modalAcceptTermsUse() {
        x.modal('hide');
        $(document.body).removeClass("modal-open");
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
        $("#chkAceptarTerminos").prop('checked', true);
    }

    function modalAcceptPrivacyPolicy() {

        $('#aceptarPrivacidadModal').modal('hide');
        $(document.body).removeClass("modal-open");
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
        $("#chkAceptarPrivacidad").prop('checked', true);
    }


    $(document).on('show.bs.modal', '#reservationModal', function (e) {
        x.modal({backdrop: false, show: true});
    });

    function go() {
        var form = $('#appAltaUsuario');
        form.validate();
        if (form.valid()) {
            form.submit();
        }
    }


    /*  $('#fecha_nac').datepicker({
          format: "mm/dd/yyyy",
          language: "es"
      });*/

</script>
<script src="https://cdn.jsdelivr.net/npm/pikaday/pikaday.js"></script>
<script>
    <%
       if (isIE11)
           {
       %>
    function IEmodalAcceptTermsUse() {
        document.getElementById('chkAceptarTerminos').checked = true;
        $('#chkAceptarTerminosl').modal('hide');
        $(document.body).removeClass("modal-open");
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
        //($("#chkAceptarTerminos").prop('checked', true);
    }

    function IEmodalAcceptPrivacyPolicy() {
        document.getElementById('chkAceptarPrivacidad').checked = true;
        $('#aceptarPrivacidadModal').modal('hide');
        $(document.body).removeClass("modal-open");
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
        //$("#chkAceptarPrivacidad").prop('checked', true);
    }


    var picker = new Pikaday(
        {
            field: document.getElementById('fecha_nac'),
            format: 'DD/MM/YYYY',
            firstDay: 1,
            minDate: new Date(),
            maxDate: new Date(2020, 12, 31),
            yearRange: [2000, 2020],

            i18n: {
                previousMonth: 'Anterior',
                nextMonth: 'Siguiente',
                months: ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"],
                weekdays: ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"],
                weekdaysShort: ["dom", "lun", "mar", "mier", "jue", "vie", "sab"],
            },

        });

    var picker_parto = new Pikaday(
        {
            field: document.getElementById('app_fecha_parto'),
            format: 'DD/MM/YYYY',
            firstDay: 1,
            minDate: new Date(),
            maxDate: new Date(2020, 12, 31),
            yearRange: [2000, 2020],

            i18n: {
                previousMonth: 'Anterior',
                nextMonth: 'Siguiente',
                months: ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"],
                weekdays: ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"],
                weekdaysShort: ["dom", "lun", "mar", "mier", "jue", "vie", "sab"],
            },

        });

    <%
    }
    %>
    function goHome() {
        window.history.back();
    }


</script>
</body>
</html>
<%
    //   }
%>
