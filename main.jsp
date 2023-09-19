<%@ page import="com.disderm.view.MainView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.disderm.utils.Crypto" %>
<%@ page import="com.disderm.utils.Varios" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

    Long id = 0L;
    boolean is_processing = false;
    if (request.getParameter("isSubmit") != null) {
        System.out.println("ACTIOOON" + request.getParameter("action"));

        if (request.getParameter("action").equals("Edit")) {
            is_processing = true;
            PacientesDAO pacientesDAO = new PacientesDAO();
            HashMap map = new HashMap();
            //password = Crypto.encrypt(password,"SHA-512");
            map.put("historia_id", request.getParameter("historia_id"));
            map.put("first_name", request.getParameter("first_name"));
            map.put("last_name", request.getParameter("last_name"));
            map.put("nif", request.getParameter("nif"));
            Long pacientes_id = Long.parseLong(request.getParameter("pacientes_id"));

            pacientesDAO.updatePacientesString(map, pacientes_id);

            response.sendRedirect("/backoffice/smartphone/tables/tabla_usuarios_gineapp.jsp");
        } else if (request.getParameter("action").equals("Delete")) {
            PacientesDAO pacientesDAO = new PacientesDAO();
            AppecografiasDAO appEcografiasDAO = new AppecografiasDAO();
            Long pacientes_id = Long.parseLong(request.getParameter("pacientes_id"));
            pacientesDAO.deletePacientes(pacientes_id);
            appEcografiasDAO.deleteAppecografiasByPacientesID(pacientes_id);
            response.sendRedirect("/backoffice/smartphone/tables/tabla_usuarios_gineapp.jsp");
        }

    } else {

        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }
        pacientesmodel = pacientesdao.getPacientesModelByID(id);
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
    <link rel="icon" href="/backoffice/assets/images/gineapp/favicon.ico" type="image/ico"/>
    <title>Editar usuarios</title>
    <!--<meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>-->

    <!-- Disable tap highlight on IE -->
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="<c:url value='/assets_intranet/css/base.min.css' />">

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

                <%=MainView.getTopBar("Edicion usuario", "Edite los datos del usuario registrado en GineApp")%>
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
                                                    <!-- CONTENIDO !-->
                                                    <form id="editUserForm" name="editUserForm" class="">
                                                        <div class="position-relative row form-group"><label
                                                                for="first_name"
                                                                class="col-sm-2 col-form-label">Nombre</label>
                                                            <div class="col-sm-10"><input name="first_name"
                                                                                          id="first_name"
                                                                                          placeholder="with a placeholder"
                                                                                          type="text"
                                                                                          class="form-control"
                                                                                          value="<%=pacientesmodel.get_first_name()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="last_name" class="col-sm-2 col-form-label">Apellidos</label>
                                                            <div class="col-sm-10"><input name="last_name"
                                                                                          id="last_name"
                                                                                          placeholder="with a placeholder"
                                                                                          type="text"
                                                                                          class="form-control"
                                                                                          value="<%=pacientesmodel.get_last_name()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="historia_id" class="col-sm-2 col-form-label">N&ordm;:His:</label>
                                                            <div class="col-sm-10"><input name="historia_id"
                                                                                          id="historia_id"
                                                                                          placeholder="Numero historia"
                                                                                          type="text"
                                                                                          class="form-control"
                                                                                          value="<%=pacientesmodel.get_historia_id()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label for="nif"
                                                                                                             class="col-sm-2 col-form-label">DNI</label>
                                                            <div class="col-sm-10"><input name="nif" id="nif"
                                                                                          placeholder="DNI" type="text"
                                                                                          class="form-control"
                                                                                          value="<%=pacientesmodel.get_nif()%>">
                                                            </div>
                                                        </div>
                                                        <div class="position-relative row form-group"><label
                                                                for="password"
                                                                class="col-sm-2 col-form-label">Password</label>
                                                            <div class="col-sm-10"><input name="password" id="password"
                                                                                          placeholder="Introduzca nueva clave"
                                                                                          type="text"
                                                                                          class="form-control">
                                                            </div>
                                                        </div>
                                                        <input type="hidden" name="pacientes_id" id="pacientes_id" value="<%=id%>">
                                                        <input type="hidden" name="action" id="action" value="eliminar">
                                                        <input type="hidden" name="isSubmit" id="isSubmit" value="true">
                                                    </form>
                                                    <div class="position-relative row form-check">
                                                        <div class="col-sm-10 offset-sm-2">
                                                            <button class="btn btn-secondary" onclick="editUser()">
                                                                Actualizar
                                                            </button>
                                                            <button class="btn btn-warning"
                                                                    onclick="openAvisoEliminarModal()">Eliminar
                                                            </button>
                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="card-body">
                                                    <table style="width: 100%;" id="ecografias_table"
                                                           class="table table-hover table-striped table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th>Fecha</th>
                                                            <th>Ecografia</th>

                                                        </tr>
                                                        </thead>
                                                        <tfoot>
                                                        <tr>
                                                            <th>Fecha</th>
                                                            <th>Ecografia</th>
                                                        </tr>
                                                        </tfoot>
                                                    </table>
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
    <!--DRAWER START-->
    <%=MainView.getDrawer()%>
    <!-- END DRAWER -->
</div>
<div class="app-drawer-overlay d-none animated fadeIn"></div>


<div class="modal fade" id="avisoBorrarModal" name="avisoBorrarModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">ATENCI&OACUTE;N</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                &iquest; Est&aacute; seguro de borrar el registro de paciente?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-danger" onclick="modalConfirmarEliminacion()">Eliminar</button>
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


<!--TABLES -->
<!--DataTables-->
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/datatables.net-bs4@1.10.21/js/dataTables.bootstrap4.min.js"
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"
        crossorigin="anonymous"></script>

<!--Bootstrap Tables-->
<script src="<c:url value='/assets_intranet/js/vendors/tables.js' />"></script>

<!--Tables Init-->
<script src="<c:url value='/assets_intranet/js/scripts-init/tables.js' />"></script>

<!-- END SCRIPTS -->

<!--SCRIPTS INCLUDES-->
<script>

    $(document).ready(function () {
        $('#ecografias_table').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/backoffice/services/app_ecografias/getAllDataTable/<%=id%>"
        });

    });


    var modalAvisoBorrar = $("#avisoBorrarModal");

    function openAvisoEliminarModal() {
        modalAvisoBorrar.modal({backdrop: false, show: true});
    }


    function editUser() {
        $('#action').val('Edit');

        var editForm = $('#editUserForm');
        editForm.submit();
    }

    $("editUserForm").submit(function(){


    });

    function modalConfirmarEliminacion() {
        modalAvisoBorrar.modal('hide');
        $(document.body).removeClass("modal-open");
        $('body').removeClass('modal-open');

        $('.modal-backdrop').remove();
        $('#action').val('Delete');
        var form = $('#editUserForm');
        form.submit();
    }


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
            "emptyTable": "Ningún dato disponible en esta tabla",
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
    /* $(document).ready(function () {
         $('#exampleTable3').DataTable({
             "processing": true,
             "serverSide": true,
             "ajax": "SubSearch"
         });
     });*/

</script>
</body>
</html>
