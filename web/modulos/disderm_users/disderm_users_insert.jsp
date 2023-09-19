<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.disderm.view.MainView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.disderm.utils.Crypto" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.model.DisdermusersModel" %>
<%@ page import="com.disderm.dao.DisdermusersDAO" %>
<%@ page import="java.sql.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    //Variables:
    String jsp_js_var = "";
    String funcion = "";
    String content = "";
    boolean show_form = true;
    String host = Varios.getFullURL();
    Date fecha_actual = new Date(new java.util.Date().getTime());
    String visible_name = "Administrador";
    String username = "support@iacell.com";
    String user_icon = "/backoffice/assets_intranet/backoffice/feelfarma_user.png";
    //Model:

    //DAO:

    if (request.getParameter("isSubmit") != null) {
        funcion = "openInsertModal();";
        DisdermusersModel disdermusersModel = new DisdermusersModel();
        DisdermusersDAO disdermusersDAO = new DisdermusersDAO();

        disdermusersModel.set_username(request.getParameter("username"));
        disdermusersModel.set_password(request.getParameter("password"));
        disdermusersModel.set_first_name(request.getParameter("first_name"));
        disdermusersModel.set_last_name(request.getParameter("last_name"));
        disdermusersModel.set_visible_name(request.getParameter("visible_name"));
        disdermusersModel.set_user_type_id(request.getIntHeader("user_type_id"));
        disdermusersModel.set_profile_image(request.getParameter("profile_image"));
        disdermusersModel.set_enabled(request.getIntHeader("enabled"));
        disdermusersModel.set_phone_mobile(request.getParameter("phone_mobile"));
        disdermusersModel.set_localidad(request.getParameter("localidad"));
        disdermusersModel.set_provincia(request.getParameter("provincia"));
        disdermusersModel.set_cp(request.getParameter("cp"));
        disdermusersModel.set_app_platform(request.getParameter("app_platform"));
        disdermusersModel.set_app_device_model(request.getParameter("app_device_model"));
        disdermusersModel.set_app_device_token(request.getParameter("app_device_token"));
        disdermusersModel.set_fecha_alta_sistema(Date.valueOf(request.getParameter("fecha_alta_sistema")));
        disdermusersModel.set_fecha_ultimo_acceso(Date.valueOf(request.getParameter("fecha_ultimo_acceso")));
        disdermusersModel.set_message(request.getParameter("message"));
        Long insertID = disdermusersDAO.insertDisdermusers(disdermusersModel);
        if (insertID != 0L) {
            content = "Item insertado correctamente";
            jsp_js_var = "showMsg(\"Atenci&oacute;n\",\"" + content + "\",\" function () { console.log('Closing message from event', d, e);});\");";
        } else {
            content = "Item no se inserto";
            jsp_js_var = "showMsg(\"Atenci&oacute;n\",\"" + content + "\",\" function () { console.log('Closing message from event', d, e);});\");";
        }

    }


%>
<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="utf-8">
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <!--<meta http-equiv="Content-Language" content="es">-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8"/>
    <link rel="icon" href="<c:url value='/assets/images/feelfarma/favicon/favicon.ico'/>" type="image/ico"/>
    <title>disdermusers</title>
    <!--<meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>-->

    <!-- Disable tap highlight on IE -->
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="<c:url value='/assets_intranet/css/base.min.css' />">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.4/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
    <! --- loading animation -->
    <style>
        #lock-modal {
            display: none;
            background-color: black;
            opacity: 0.6;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            border-radius: inherit;
        }

        #loading-circle {
            display: none;
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            margin: auto;
            width: 40px;
            height: 40px;
            border: 4px solid #f3f3f3;
            border-top: 4px solid #3498db;
            border-radius: 50%;
            animation: spin 0.6s ease-in infinite;
        }
    </style>
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
                        <a href="#" data-toggle="tooltip" data-placement="bottom" title="disdermfarma Admin"
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

                <%=MainView.getTopBar("Insertar disderm_users", "Formulario de introducci&oacute;n para disderm_users", visible_name, username, user_icon)%>
                <!-- END HEADER TOP BAR -->


                <div class="app-inner-layout app-inner-layout-page">
                    <div class="app-inner-layout__wrapper">
                        <div class="app-inner-layout__content pt-1">
                            <div class="tab-content">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="main-card mb-3 card">
                                                <div class="card-body">
                                                    <!-- CONTENIDO -->
                                                    <form id="insertardisdermusersForm"
                                                          name="insertardisdermusersForm">
                                                        <div class="position-relative row form-group"><label
                                                                for="username"
                                                                class="col-sm-2 col-form-label">Email</label>
                                                            <div class="col-sm-10"><input name="username"
                                                                                          id="username"
                                                                                          placeholder="Email"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="password"
                                                                class="col-sm-2 col-form-label">Contraseña</label>
                                                            <div class="col-sm-10"><input name="password"
                                                                                          id="password"
                                                                                          placeholder="Pass"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="first_name"
                                                                class="col-sm-2 col-form-label">Nombre</label>
                                                            <div class="col-sm-10"><input name="first_name"
                                                                                          id="first_name"
                                                                                          placeholder="Nombre"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="last_name"
                                                                class="col-sm-2 col-form-label">Apellidos</label>
                                                            <div class="col-sm-10"><input name="last_name"
                                                                                          id="last_name"
                                                                                          placeholder="Apellidos"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="visible_name"
                                                                class="col-sm-2 col-form-label">Nombre de Usuario</label>
                                                            <div class="col-sm-10"><input name="visible_name"
                                                                                          id="visible_name"
                                                                                          placeholder="Codigo"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="user_type_id"
                                                                class="col-sm-2 col-form-label">Codigo</label>
                                                            <div class="col-sm-10"><input name="user_type_id"
                                                                                          id="user_type_id"
                                                                                          placeholder="User type"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="profile_image"
                                                                class="col-sm-2 col-form-label">Imagen de perfil</label>
                                                            <div class="col-sm-10"><input name="profile_image"
                                                                                          id="profile_image"
                                                                                          placeholder="Codigo"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="enabled"
                                                                class="col-sm-2 col-form-label">Activado</label>
                                                            <div class="col-sm-10">
                                                                <select class="custom-select" name="enabled" id="enabled">
                                                                    <option selected disabled>Seleccione</option>
                                                                    <option value="1">Sí</option>
                                                                    <option value="0">No</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="phone_mobile"
                                                                class="col-sm-2 col-form-label">Número de Teléfono</label>
                                                            <div class="col-sm-10"><input name="phone_mobile"
                                                                                          id="phone_mobile"
                                                                                          placeholder="Codigo"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="localidad"
                                                                class="col-sm-2 col-form-label">Localidad</label>
                                                            <div class="col-sm-10"><input name="localidad"
                                                                                          id="localidad"
                                                                                          placeholder="Localidad"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="provincia"
                                                                class="col-sm-2 col-form-label">Provincia</label>
                                                            <div class="col-sm-10"><input name="provincia"
                                                                                          id="provincia"
                                                                                          placeholder="Provincia"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="app_platform"
                                                                class="col-sm-2 col-form-label">App Platform</label>
                                                            <div class="col-sm-10"><input name="app_platform"
                                                                                          id="app_platform"
                                                                                          placeholder="Password"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="app_device_model"
                                                                class="col-sm-2 col-form-label">App device model</label>
                                                            <div class="col-sm-10"><input name="app_device_model"
                                                                                          id="app_device_model"
                                                                                          placeholder="Password"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="app_device_token"
                                                                class="col-sm-2 col-form-label">App device token</label>
                                                            <div class="col-sm-10"><input name="app_device_token"
                                                                                          id="app_device_token"
                                                                                          placeholder="Password"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="fecha_alta_sistema"
                                                                class="col-sm-2 col-form-label">Fecha de alta</label>
                                                            <div class="col-sm-10"><input name="fecha_alta_sistema"
                                                                                          id="fecha_alta_sistema"
                                                                                          placeholder="Fecha Nacimiento"
                                                                                          type="text"
                                                                                          class="form-control" value="<%=fecha_actual%>" readonly>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="fecha_ultimo_acceso"
                                                                class="col-sm-2 col-form-label">Fecha Nacimiento</label>
                                                            <div class="col-sm-10"><input name="fecha_ultimo_acceso"
                                                                                          id="fecha_ultimo_acceso"
                                                                                          placeholder="Fecha Nacimiento"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="message"
                                                                class="col-sm-2 col-form-label">Password</label>
                                                            <div class="col-sm-10"><input name="message"
                                                                                          id="message"
                                                                                          placeholder="Password"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row">
                                                            <div class="col-sm-12">
                                                                <button class="btn btn-primary form-group"
                                                                        onclick="actionInsertardisdermusers()"
                                                                        style="width: inherit;">
                                                                    Insertar
                                                                </button>
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
                <%=MainView.getBasicFooter()%>
            </div>
        </div>
        <!--THEME OPTIONS START-->
        <%=MainView.getThemeSettings()%>

        <!--THEME OPTIONS END-->
    </div>
    <!--DRAWER START-->
    <%=MainView.getDrawer()%>
    <!-- END DRAWER -->
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

<!--DRAWER END-->

<!-- START SCRIPTS IMPORTS -->
<!--CORE-->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/metismenu"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/app.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/demo.js' />"></script>

<!--CHARTS-->

<!--Apex Charts-->
<script src="<c:url value='/assets_intranet/js/vendors/charts/apex-charts.js' />"></script>

<script src="<c:url value='/assets_intranet/js/scripts-init/charts/apex-charts.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/charts/apex-series.js' />"></script>

<!--Sparklines-->
<script src="<c:url value='/assets_intranet/js/vendors/charts/charts-sparklines.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/charts/charts-sparklines.js' />"></script>


<!--Sparklines-->
<script src="<c:url value='/assets_intranet/js/vendors/charts/charts-sparklines.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/charts/charts-sparklines.js' />"></script>


<!--Chart.js-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/charts/chartsjs-utils.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/charts/chartjs.js' />"></script>

<!--FORMS-->

<!--Clipboard-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/clipboard.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/clipboard.js' />"></script>

<!--Datepickers-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/datepicker.js' />"></script>
<script src="<c:url value='/assets_intranet/js/vendors/form-components/daterangepicker.js' />"></script>
<script src="<c:url value='/assets_intranet/js/vendors/form-components/moment.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/datepicker.js' />"></script>

<!--Multiselect-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/bootstrap-multiselect.js' />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/input-select.js' />"></script>

<!--Form Validation-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/form-validation.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/form-validation.js' />"></script>

<!--Form Wizard-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/form-wizard.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/form-wizard.js' />"></script>

<!--Input Mask-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/input-mask.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/input-mask.js' />"></script>

<!--RangeSlider-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/wnumb.js' />"></script>
<script src="<c:url value='/assets_intranet/js/vendors/form-components/range-slider.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/range-slider.js' />"></script>

<!--Textarea Autosize-->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/textarea-autosize.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/form-components/textarea-autosize.js' />"></script>

<!--Toggle Switch -->
<script src="<c:url value='/assets_intranet/js/vendors/form-components/toggle-switch.js' />"></script>


<!--COMPONENTS-->

<!--BlockUI -->
<script src="<c:url value='/assets_intranet/js/vendors/blockui.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/blockui.js' />"></script>

<!--Calendar -->
<script src="<c:url value='/assets_intranet/js/vendors/calendar.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/calendar.js' />"></script>

<!--Slick Carousel -->
<script src="<c:url value='/assets_intranet/js/vendors/carousel-slider.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/carousel-slider.js' />"></script>

<!--Circle Progress -->
<script src="<c:url value='/assets_intranet/js/vendors/circle-progress.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/circle-progress.js' />"></script>

<!--CountUp -->
<script src="<c:url value='/assets_intranet/js/vendors/count-up.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/count-up.js' />"></script>

<!--Cropper -->
<script src="<c:url value='/assets_intranet/js/vendors/cropper.js' />"></script>
<script src="<c:url value='/assets_intranet/js/vendors/jquery-cropper.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/image-crop.js' />"></script>

<!--Maps -->
<script src="<c:url value='/assets_intranet/js/vendors/gmaps.js' />"></script>
<script src="<c:url value='/assets_intranet/js/vendors/jvectormap.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/maps-word-map.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/maps.js' />"></script>

<!--Guided Tours -->
<script src="<c:url value='/assets_intranet/js/vendors/guided-tours.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/guided-tours.js' />"></script>

<!--Ladda Loading Buttons -->
<script src="<c:url value='/assets_intranet/js/vendors/ladda-loading.js' />"></script>
<script src="<c:url value='/assets_intranet/js/vendors/spin.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/ladda-loading.js' />"></script>

<!--Rating -->
<script src="<c:url value='/assets_intranet/js/vendors/rating.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/rating.js' />"></script>

<!--Perfect Scrollbar -->
<script src="<c:url value='/assets_intranet/js/vendors/scrollbar.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/scrollbar.js' />"></script>

<!--Toastr-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"
        crossorigin="anonymous"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/toastr.js' />"></script>

<!--SweetAlert2-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/sweet-alerts.js' />"></script>

<!--Tree View -->
<script src="<c:url value='/assets_intranet/js/vendors/treeview.js' />"></script>
<script src="<c:url value='/assets_intranet/js/scripts-init/treeview.js' />"></script>


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

    const form = $("#insertardisdermusersForm");
    const lockModal = $("#lock-modal");
    const loadingCircle = $("#loading-circle");
    var actionInsertardisdermusers = function () {
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
        $("#insertardisdermusersForm").validate({
            rules: {
                username:
                    {
                        required: true,
                        minlength: 5
                    },
                password:
                    {
                        required: true,
                        minlength: 2
                    },
                first_name:
                    {
                        required: true,
                        minlength: 2
                    },
                last_name:
                    {
                        required: true,
                        minlength: 2
                    },
                visible_name:
                    {
                        required: true,
                        minlength: 2
                    },
                user_type_id:
                    {
                        required: true,
                        minlength: 2
                    },
                profile_image:
                    {
                        required: true,
                        minlength: 2
                    },
                enabled:
                    {
                        required: true,
                        minlength: 2
                    },
                phone_mobile:
                    {
                        required: true,
                        minlength: 2
                    },
                localidad:
                    {
                        required: true,
                        minlength: 4
                    },
                provincia:
                    {
                        required: true,
                        minlength: 4
                    },
                cp:
                    {
                        required: true,
                        minlength: 5
                    },
                app_platform:
                    {
                        required: true,
                        minlength: 4
                    },
                app_device_model:
                    {
                        required: true,
                        minlength: 4
                    },
                app_device_token:
                    {
                        required: true,
                        minlength: 4
                    },
                fecha_alta_sistema:
                    {
                        required: true,
                        minlength: 8
                    },
                fecha_ultimo_acceso:
                    {
                        required: true,
                        minlength: 8
                    },
                message:
                    {
                        required: true,
                        minlength: 4
                    },
            }, messages: {
                username: {
                    required: 'Este campo es obligatorio'
                }, password: {
                    required: 'Este campo es obligatorio'
                }, first_name: {
                    required: 'Este campo es obligatorio'
                }, last_name: {
                    required: 'Este campo es obligatorio'
                }, visible_name: {
                    required: 'Este campo es obligatorio'
                }, user_type_id: {
                    required: 'Este campo es obligatorio'
                }, profile_image: {
                    required: 'Este campo es obligatorio'
                }, enabled: {
                    required: 'Este campo es obligatorio'
                }, phone_mobile: {
                    required: 'Este campo es obligatorio'
                }, localidad: {
                    required: 'Este campo es obligatorio'
                }, provincia: {
                    required: 'Este campo es obligatorio'
                }, cp: {
                    required: 'Este campo es obligatorio'
                }, app_platform: {
                    required: 'Este campo es obligatorio'
                }, app_device_model: {
                    required: 'Este campo es obligatorio'
                }, app_device_token: {
                    required: 'Este campo es obligatorio'
                }, fecha_alta_sistema: {
                    required: 'Este campo es obligatorio'
                }, fecha_ultimo_acceso: {
                    required: 'Este campo es obligatorio'
                }, message: {
                    required: 'Este campo es obligatorio'
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
            window.location.replace("/backoffice/modulos/parcelas/tbl_custom_search_parcelas.jsp");
        });

    });

</script>
</body>
</html>
        

        