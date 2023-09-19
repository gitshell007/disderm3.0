
        <%@ page import="com.disderm.view.MainView" %>
        <%@ page import="java.util.HashMap" %>
        <%@ page import="com.disderm.utils.Crypto" %>
        <%@ page import="com.disderm.utils.Varios" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%
            String funcion = "";
            if (request.getParameter("isSubmit") != null) {
                System.out.println("hola" + request.getParameter("isSubmit") + " " + request.getParameter("message"));
                funcion = "openAvisoEliminarModal();";

            }
            String host = Varios.getFullURL();
            String visible_name = "Administrador";
            String username = "support@iacell.com";
            String user_icon = "/backoffice/assets_intranet/backoffice/feelfarma_user.png";
        %>
        <!DOCTYPE html>
        <html lang="es">
        <head>

            <meta charset="utf-8">
            <!--<meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
            <!--<meta http-equiv="Content-Language" content="es">-->
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
            <meta charset="UTF-8"/>
            <link rel="icon" href="/assets/images/feelfarma/favicon/favicon.ico" type="image/ico"/>
            <title>Laboratory</title>
            <!--<meta name="viewport"
                  content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>-->

            <!-- Disable tap highlight on IE -->
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

                        <%=MainView.getTopBar("Laboratory Consult", "Formulario de consulta para Laboratory",visible_name,username,user_icon)%>
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
                                                                <table style="width: 100%;" id="tblLaboratory"
                                                       class="table table-hover table-striped table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>ID</th><th>Description</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot>
                                                    <tr>
                                                         <th>ID</th><th>Description</th>
                                                    </tr>
                                                    </tfoot></table>
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
            <!--TABLES -->
            <!--DataTables-->
            <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/datatables.net-bs4@1.10.21/js/dataTables.bootstrap4.min.js"
                    crossorigin="anonymous"></script>
            <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js" crossorigin="anonymous"></script>
            <script src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js" crossorigin="anonymous"></script>
            <script src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js" crossorigin="anonymous"></script>
            
            <! -- icons -->
            <script type="module" src="https://unpkg.com/ionicons@5.2.3/dist/ionicons/ionicons.esm.js"></script>
<script nomodule="" src="https://unpkg.com/ionicons@5.2.3/dist/ionicons/ionicons.js"></script>
        <!--SCRIPTS INCLUDES-->
            <script>
                
                $(document).ready(function () {
                    <%=funcion%>
                    
        var table_Laboratory = $('#tblLaboratory').DataTable({
            dom: 'Bfrtip',   
            processing: true,
            searching: true,
            serverSide: true,
            select: {
                style: 'single'
            },
            ajax: "/backoffice/services/laboratory/getAllDataTable",
            aoColumns: [
{"bSortable": true, "bSearchable": false},
{"bSortable": true, "bSearchable": false}],
pageLength: 20,buttons: [
                {
                    text: '<ion-icon name="create-outline"></ion-icon>Editar',
                    action: function (e, dt, node, config) {
                        LaboratoryEdit();
                    },
                    enabled: true
                },
                {
                    text: '<ion-icon name="create-outline"></ion-icon>Eliminar',
                    action: function ( e, dt, node, config ) {
                        LaboratoryDelete();
                    },
                    enabled:true
                }
                                ]
 }); table_Laboratory.on( 'select deselect', function () {
                                    var selectedRows = table_Laboratory.rows( { selected: true } ).count();
                                    console.log('x' + selectedRows);
                                    table_Laboratory.button( 0 ).enable( selectedRows === 1 );
                                    } );
                                 var LaboratoryEdit = function ()
                                    {
                                        var selData =   table_Laboratory.rows(".selected").data();
                                        window.location = "laboratory_edit.jsp?id=" + selData[0][0];
                                    };

                                var LaboratoryDelete = function ()
                                    {
                                        var selData =   table_Laboratory.rows(".selected").data();
                                        window.location = "laboratory_delete.jsp?id=" + selData[0][0];
                                    };
                        

                });
                $.extend(true, $.fn.dataTable.defaults, {
        "language": {
            "decimal": ",",
            "thousands": ".",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "infoPostFix": "",
            "infoFiltered": "(filtrado de un total de _MAX_ registros)",
            "loadingRecords": "Cargando...",
            "lengthMenu": "Mostrar _MENU_ registros",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "processing": "Procesando...",
            "search": "Buscar:",
            "searchPlaceholder": "Buscar...",
            "zeroRecords": "No se encontraron resultados",
            "emptyTable": "Ning&uacuten dato disponible en esta tabla",
            "aria": {
                "sortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sortDescending": ": Activar para ordenar la columna de manera descendente"
            },
            //only works for built-in buttons, not for custom buttons
            "buttons": {
                "create": "Nuevo",
                "edit": "Cambiar",
                "remove": "Borrar",
                "copy": "Copiar",
                "csv": "fichero CSV",
                "excel": "tabla Excel",
                "pdf": "documento PDF",
                "print": "Imprimir",
                "colvis": "Visibilidad columnas",
                "collection": "Colección",
                "upload": "Seleccione fichero...."
            },
            "select": {
                "rows": {
                    _: '%d filas seleccionadas',
                    0: 'clic fila para seleccionar',
                    1: 'una fila seleccionada'
                }
            }
        }
    });
            </script>
            </body>
            </html>
        

           