<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.disderm.view.MainView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.disderm.utils.Crypto" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ page import="com.disderm.model.SalesModel" %>
<%@ page import="com.disderm.dao.SalesDAO" %>
<%@ page import="java.sql.Date" %>
<%@ page import="com.disderm.service.SalesService" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String jsp_js_var = "";
    String funcion = "";
    String content = "";
    boolean show_form = true;
    String host = Varios.getFullURL();
    String visible_name = "Administrador";
    String username = "support@iacell.com";
    String user_icon = "/backoffice/assets_intranet/backoffice/feelfarma_user.png";
    if (request.getParameter("isSubmit") != null) {
        funcion = "openInsertModal();";
        SalesModel salesModel = new SalesModel();
        SalesDAO salesDAO = new SalesDAO();

        String description = request.getParameter("description");
        int pharmacy = Integer.valueOf(request.getParameter("pharmacy"));
        int product = Integer.valueOf(request.getParameter("product"));
        Date upload_date = Date.valueOf(request.getParameter("upload_date"));
        Date sale_date = Date.valueOf(request.getParameter("sale_date"));
        int units_number = Integer.valueOf(request.getParameter("units_number"));
        BigDecimal units_price = BigDecimal.valueOf(Long.parseLong(request.getParameter("units_number")));
        int import_source = Integer.valueOf(request.getParameter("import_source"));
        int payment_type = Integer.valueOf(request.getParameter("payment_type"));
        int sale_type = Integer.valueOf(request.getParameter("sale_type"));

        SalesService SS = new SalesService();
        boolean resultService = SS.serviceInsertSales(pharmacy, description, product, upload_date, sale_date, units_number, units_price, import_source, payment_type, sale_type);

        if (resultService) {
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
    <title>Sales</title>
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
                        <a href="#" data-toggle="tooltip" data-placement="bottom" title="feelfarma Admin"
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

                <%=MainView.getTopBar("Sales insert", "Formulario de introducci&oacute;n para Sales", visible_name, username, user_icon)%>
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
                                                    <form id="salesInsertForm" name="salesInsertForm">
                                                        <div class="position-relative row form-group"><label
                                                                for="pharmacy"
                                                                class="col-sm-2 col-form-label">Description</label>
                                                            <div class="col-sm-10"><select name="description"
                                                                                           id="description"
                                                                                           class="form-control">

                                                            </select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="pharmacy"
                                                                class="col-sm-2 col-form-label">Pharmacy</label>
                                                            <div class="col-sm-10"><select name="pharmacy"
                                                                                          id="pharmacy"
                                                                                           class="form-control">

                                                            </select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="product"
                                                                class="col-sm-2 col-form-label">Product</label>
                                                            <div class="col-sm-10"><select name="product"
                                                                                           id="product"
                                                                                           class="form-control">

                                                            </select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="upload_date"
                                                                class="col-sm-2 col-form-label">Upload Date</label>
                                                            <div class="col-sm-10"><input name="upload_date"
                                                                                           id="upload_date"
                                                                                          type="date"
                                                                                           class="form-control">

                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="sale_date"
                                                                class="col-sm-2 col-form-label">Sale Date</label>
                                                            <div class="col-sm-10"><input name="sale_date"
                                                                                           id="sale_date"
                                                                                          type="date"
                                                                                           class="form-control">

                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="units_number"
                                                                class="col-sm-2 col-form-label">Units number</label>
                                                            <div class="col-sm-10"><input name="units_number"
                                                                                           id="units_number"
                                                                                          type="number"
                                                                                           class="form-control">

                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="units_price"
                                                                class="col-sm-2 col-form-label">Units Price</label>
                                                            <div class="col-sm-10"><input name="units_price"
                                                                                           id="units_price"
                                                                                           type="number"
                                                                                           class="form-control">

                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="import_source"
                                                                class="col-sm-2 col-form-label">Import Source</label>
                                                            <div class="col-sm-10"><select name="import_source"
                                                                                           id="import_source"
                                                                                           class="form-control">

                                                            </select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="payment_type"
                                                                class="col-sm-2 col-form-label">Payment Type</label>
                                                            <div class="col-sm-10"><select name="payment_type"
                                                                                           id="payment_type"
                                                                                           class="form-control">

                                                            </select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="sale_type"
                                                                class="col-sm-2 col-form-label">Sale Type</label>
                                                            <div class="col-sm-10"><select name="sale_type"
                                                                                           id="sale_type"
                                                                                           class="form-control">

                                                            </select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row">
                                                            <div class="col-sm-12">
                                                                <button class="btn btn-primary form-group"
                                                                        onclick="salesInsertAction()"
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

    const form = $("#salesInsertForm");
    const lockModal = $("#lock-modal");
    const loadingCircle = $("#loading-circle");
    var salesInsertAction = function () {
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

    let dropdownPharmacy = $('#pharmacy');
    const url_pharmacy = 'http://localhost:8080/feelfarma/services/pharmacy/getSelectBootstrap/';
    function loadSelectPharmacy(){
        dropdownPharmacy.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione Valor'));
        const mock = [{"dropdown_id":1, "code": 0, "des":"ERROR"}];
        genericFetch(url_pharmacy, dropdownPharmacy, mock);
    }
    $.getJSON(url_pharmacy, function (data) {
        $.each(data, function (key, entry) {
            dropdownPharmacy.append($('<option></option>').attr('value', entry.code).text(entry.des));
        })
    })

    let dropdownImport_source = $('#import_source');
    const url_import_source = 'http://localhost:8080/feelfarma/services/import_source/getSelectBootstrap/';
    function loadSelectImport_source(){
        dropdownImport_source.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione Valor'));
        const mock = [{"dropdown_id":1, "code": 0, "des":"ERROR"}];
        genericFetch(url_import_source, dropdownImport_source, mock);
    }
    $.getJSON(url_import_source, function (data) {
        $.each(data, function (key, entry) {
            dropdownImport_source.append($('<option></option>').attr('value', entry.code).text(entry.des));
        })
    })

    let dropdownCategory = $('#product');
    const url_category = 'http://localhost:8080/feelfarma/services/product/getSelectBootstrap/';
    function loadSelectCategory(){
        dropdownCategory.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione Valor'));
        const mock = [{"dropdown_id":1, "code": 0, "des":"ERROR"}];
        genericFetch(url_category, dropdownCategory, mock);
    }
    $.getJSON(url_category, function (data) {
        $.each(data, function (key, entry) {
            dropdownCategory.append($('<option></option>').attr('value', entry.code).text(entry.des));
        })
    })

    let dropdownPayment = $('#payment_type');
    const url_payment_type = 'http://localhost:8080/feelfarma/services/payment_type/getSelectBootstrap/';
    function loadSelectPayment(){
        dropdownPayment.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione Valor'));
        const mock = [{"dropdown_id":1, "code": 0, "des":"ERROR"}];
        genericFetch(url_payment_type, dropdownPayment, mock);
    }
    $.getJSON(url_payment_type, function (data) {
        $.each(data, function (key, entry) {
            dropdownPayment.append($('<option></option>').attr('value', entry.code).text(entry.des));
        })
    })

    let dropdownSale_type = $('#sale_type');
    const url_sale_type = 'http://localhost:8080/feelfarma/services/sale_type/getSelectBootstrap/';
    function loadSelectCategory(){
        dropdownCategory.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione Valor'));
        const mock = [{"dropdown_id":1, "code": 0, "des":"ERROR"}];
        genericFetch(url_sale_type, dropdownSale_type, mock);
    }
    $.getJSON(url_sale_type, function (data) {
        $.each(data, function (key, entry) {
            dropdownSale_type.append($('<option></option>').attr('value', entry.code).text(entry.des));
        })
    })

    function genericFetch(url,dropdown,mock) {
        fetch(url).then(response=> {
            response.json()
        } ).catch(e=> {
            return mock
        }).then(
            data=>{
                $.each(data, function (key, entry) {
                    dropdown.append($('<option></option>').attr('value', entry.code).text(entry.des));
                })
            }
        )
    }

    $(document).ready(() => {
        <%=jsp_js_var%>
        $("#salesInsertForm").validate({
            rules: {
                description:
                    {
                        required: true,
                        minlength: 5
                    },
            }, messages: {
                description: {
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
            window.location.replace("/backoffice/modulos/sales/tbl_custom_search_sales.jsp");
        });

    });

</script>
</body>
</html>
        

        