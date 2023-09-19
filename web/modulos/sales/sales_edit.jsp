<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/04/2021
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.disderm.view.MainView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.disderm.dao.SalesDAO" %>
<%@ page import="com.disderm.model.SalesModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

    String visible_name = "Administrador";
    String username = "support@iacell.com";
    String user_icon = "/backoffice/assets_intranet/backoffice/feelfarma_user.png";

    SalesDAO salesdao = new SalesDAO();
    SalesModel salesModel = new SalesModel();
    Long id = 0L;

    if (request.getParameter("isSubmit") != null) {
        if (request.getParameter("action").equals("Edit")) {

            SalesDAO salesDAO = new SalesDAO();
            HashMap map = new HashMap();
            map.put("pharmacy_id",request.getParameter("pharmacy_id"));
            map.put("product_id",request.getParameter("product_id"));
            map.put("upload_date",request.getParameter("upload_date"));
            map.put("sale_date",request.getParameter("sale_date"));
            map.put("units_number",request.getParameter("units_number"));
            map.put("units_price",request.getParameter("units_price"));
            map.put("import_source_id",request.getParameter("import_source_id"));
            map.put("payment_type_id",request.getParameter("payment_type_id"));
            map.put("sale_type_id",request.getParameter("sale_type_id"));
            Long sales_id = Long.parseLong(request.getParameter("sales_id"));
            salesdao.updateSalesString(map,sales_id);
            response.sendRedirect("/backoffice/modulos/sales/tbl_custom_search_sales.jsp");
        }

    } else {
        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }
        salesModel = salesdao.getSalesModelByID(id);
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

                <%=MainView.getTopBar("Sales Edit", "Formulario de edicio&oacute;n para Sales", visible_name, username, user_icon)%>
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
                                                    <form id="salesEditForm" name="salesEditForm" method="post">
                                                        <div class="position-relative row form-group"><label
                                                                for="pharmacy_id"
                                                                class="col-sm-2 col-form-label">Pharmacy</label>
                                                            <div class="col-sm-10">
                                                                <select name="pharmacy_id" id="pharmacy_id"
                                                                        class="form-control"></select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="pharmacy_id"
                                                                class="col-sm-2 col-form-label">Product</label>
                                                            <div class="col-sm-10">
                                                                <select name="product_id" id="product_id"
                                                                        class="form-control"></select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="upload_date"
                                                                class="col-sm-2 col-form-label">Upload date</label>
                                                            <div class="col-sm-10"><input name="upload_date"
                                                                                          id="upload_date"
                                                                                          placeholder="upload_date"
                                                                                          type="text"
                                                                                          class="form-control"
                                                                                          value="<%= salesModel.get_upload_date()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="sale_date"
                                                                class="col-sm-2 col-form-label">Sale date</label>
                                                            <div class="col-sm-10"><input name="sale_date"
                                                                                          id="sale_date"
                                                                                          placeholder="sale_date"
                                                                                          type="text"
                                                                                          class="form-control"
                                                                                          value="<%= salesModel.get_sale_date()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="units_number"
                                                                class="col-sm-2 col-form-label">Units Number</label>
                                                            <div class="col-sm-10"><input name="units_number"
                                                                                          id="units_number"
                                                                                          placeholder="units_number"
                                                                                          type="text"
                                                                                          class="form-control"
                                                                                          value="<%= salesModel.get_units_number()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="units_price"
                                                                class="col-sm-2 col-form-label">Units Price</label>
                                                            <div class="col-sm-10"><input name="units_price"
                                                                                          id="units_price"
                                                                                          placeholder="units_price"
                                                                                          type="text"
                                                                                          class="form-control"
                                                                                          value="<%= salesModel.get_units_price()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="import_source_id"
                                                                class="col-sm-2 col-form-label">Import Source</label>
                                                            <div class="col-sm-10">
                                                                <select name="import_source_id" id="import_source_id"
                                                                        class="form-control"></select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="payment_type_id"
                                                                class="col-sm-2 col-form-label">Payment Type</label>
                                                            <div class="col-sm-10">
                                                                <select name="payment_type_id" id="payment_type_id"
                                                                        class="form-control"></select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="sale_type_id"
                                                                class="col-sm-2 col-form-label">Sale Type</label>
                                                            <div class="col-sm-10">
                                                                <select name="sale_type_id" id="sale_type_id"
                                                                        class="form-control"></select>
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row">
                                                            <div class="col-sm-12">
                                                                <button class="btn btn-primary form-group"
                                                                        onclick="actionSalesEdit()"
                                                                        style="width: inherit;">
                                                                    Editar
                                                                </button>
                                                                <input type="hidden" name="sales_id" id="sales_id" value="<%=id%>">
                                                                <input type="hidden" name="action" id="action" value="eliminar">
                                                                <input type="hidden" name="isSubmit" id="isSubmit" value="true">
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

    const form = $("#salesEditForm");
    const lockModal = $("#lock-modal");
    const loadingCircle = $("#loading-circle");

    function genericFetchImportSource(url, dropdown, mock) {
        importSource = <%=salesModel.get_import_source_id()%>
            fetch(url).then(response => {
                response.json()
            }).catch(e => {
                return mock
            }).then(
                data => {
                    $.getJSON(url, function (data) {
                    $.each(data, function (key, entry) {
                        if (entry.code == importSource) {
                            dropdown.append($('<option selected></option>').attr('value', entry.code).text(entry.des));
                        } else {
                            dropdown.append($('<option></option>').attr('value', entry.code).text(entry.des));
                        }
                    })
                })
                }
            )
    }

    let dropdownimport_source = $('#import_source_id');
    const url_import_source = 'http://localhost:8080/feelfarma/services/import_source/getSelectBootstrap/';

    function loadSelectImport_source() {
        // Valor por defecto
        dropdownimport_source.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione valor'));

        genericFetchImportSource(url_import_source, dropdownimport_source);
    }

    function genericFetchPharmacy(url, dropdown, mock) {
        pharmacy = <%=salesModel.get_pharmacy_id()%>
            fetch(url).then(response => {
                response.json()
            }).catch(e => {
                return mock
            }).then(
                data => {
                    $.getJSON(url, function (data) {
                    $.each(data, function (key, entry) {
                        if (entry.code == pharmacy) {
                            dropdown.append($('<option selected></option>').attr('value', entry.code).text(entry.des));
                        } else {
                            dropdown.append($('<option></option>').attr('value', entry.code).text(entry.des));
                        }
                    })
                })
                }
            )
    }

    let dropdownpharmacy = $('#pharmacy_id');
    const url_pharmacy = 'http://localhost:8080/feelfarma/services/pharmacy/getSelectBootstrap/';

    function loadSelectPharmacy() {
        // Valor por defecto
        dropdownpharmacy.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione valor'));


        genericFetchPharmacy(url_pharmacy, dropdownpharmacy);
    }

    function genericFetchProduct(url, dropdown, mock) {
        product = <%=salesModel.get_product_id()%>
            fetch(url).then(response => {
                response.json()
            }).catch(e => {
                return mock
            }).then(
                data => {
                    $.getJSON(url, function (data) {
                    $.each(data, function (key, entry) {
                        if (entry.code == product) {
                            dropdown.append($('<option selected></option>').attr('value', entry.code).text(entry.des));
                        } else {
                            dropdown.append($('<option></option>').attr('value', entry.code).text(entry.des));
                        }
                    })
                })
                }
            )
    }

    let dropdownproduct = $('#product_id');
    const url_product = 'http://localhost:8080/feelfarma/services/product/getSelectBootstrap/';

    function loadSelectProduct() {
        // Valor por defecto
        dropdownproduct.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione valor'));
        genericFetchProduct(url_product, dropdownproduct);
    }


    function genericFetchPaymentType(url, dropdown) {
        paymentType = <%=salesModel.get_payment_type_id()%>
            fetch(url).then(response => {
                response.json()
            }).catch(e => {

            }).then(
                data => {
                    $.getJSON(url, function (data) {
                    $.each(data, function (key, entry) {
                        if (entry.code == paymentType) {
                            dropdown.append($('<option selected></option>').attr('value', entry.code).text(entry.des));
                        } else {
                            dropdown.append($('<option></option>').attr('value', entry.code).text(entry.des));
                        }
                    })
                })
                }
            )
    }

    let dropdownpayment_type = $('#payment_type_id');
    const url_payment_type = 'http://localhost:8080/feelfarma/services/payment_type/getSelectBootstrap/';

    function loadSelectPaymentType() {
        // Valor por defecto
        dropdownpayment_type.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione valor'));

        genericFetchPaymentType(url_payment_type, dropdownpayment_type);
    }


    function genericFetchSaleType(url, dropdown, mock) {
        saleType = <%=salesModel.get_sale_type_id()%>
            fetch(url).then(response => {
                response.json()
            }).catch(e => {
                return mock
            }).then(
                data => {
                    $.getJSON(url, function (data) {
                            $.each(data, function (key, entry) {
                                if (entry.code == saleType) {
                                    dropdown.append($('<option selected></option>').attr('value', entry.code).text(entry.des));
                                } else {
                                    dropdown.append($('<option></option>').attr('value', entry.code).text(entry.des));
                                }
                            })
                        }
                    )
                })
    }

    let dropdownsale_type = $('#sale_type_id');
    const url_sale_type = 'http://localhost:8080/feelfarma/services/sale_type/getSelectBootstrap/';

    function loadSelectSaleType() {
        // Valor por defecto
        dropdownsale_type.append($('<option selected disabled hidden></option>').attr('value', '0').text('Seleccione valor'));
        // Populate dropdown with list of import source

        const mockSaleType = [{"dropdown_id": 1, "code": "1", "des": "Test"}];
        /*
        $.getJSON(url_sale_type, function (data) {
            $.each(data, function (key, entry) {
                dropdownsale_type.append($('<option></option>').attr('value', entry.code).text(entry.des));
            })
        });*/

        genericFetchSaleType(url_sale_type, dropdownsale_type, mockSaleType);
    }

    var actionSalesEdit = function () {
        $('#action').val('Edit');
        var editForm = $('#salesEditForm');
        editForm.submit();
    }

    $("salesEditForm").submit(function(){

    });

    $(document).ready(() => {
        $("#salesEditForm").validate({
            rules: {

            }, messages: {

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
        loadSelectImport_source();
        loadSelectPharmacy();
        loadSelectProduct();
        loadSelectPaymentType();
        loadSelectSaleType();
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



    });

</script>
</body>
</html>


</html>
