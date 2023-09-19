<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 24/11/2021
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.disderm.view.MainView" %>
<%@ page import="com.disderm.utils.Settings" %>
<%@ page import="com.disderm.auth.SessionBean" %>
<%@ page import="com.disderm.dao.DiagnosticoDAO" %>
<%@ page import="com.disderm.model.DisdermusersModel" %>
<%@ page import="com.disderm.dao.DisdermusersDAO" %>
<%@ page import="com.disderm.model.UsuariosModel" %>
<%@ page import="com.disderm.dao.UsuariosDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String usuario = "";
    Long user_id = 0L;

    UsuariosModel usermodel = new UsuariosModel();
    UsuariosDAO userdao = new UsuariosDAO();


    HttpSession httpSession = request.getSession();


    DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
    SessionBean sBean = (SessionBean) httpSession.getAttribute("sessionBean");
    if (sBean != null) {
        if ((sBean.getUser() != null) && (sBean.getSession_id() != null)) {
            //para eliminar
            if (request.getParameter("eliminar_diagnostico_id") != null) {
                diagnosticoDAO.deleteDiagnosticosByID(Long.valueOf(request.getParameter("eliminar_diagnostico_id")));
            }

            usuario = sBean.getUser().get_nombre_usuario();
            usermodel = userdao.getUsuariosByNombre(usuario);
            user_id = usermodel.get_id();
            String user_icon = "/backoffice/assets_intranet/images/avatars/doctora.png";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Disderm BackOffice System</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="<c:url value='/assets_intranet/css/base.min.css' />">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.4/css/buttons.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
    <style>
        .page-item.active .page-link, .pagination .active.page-number .page-link{
            color: #fff;
            background-color: #b42d8f;
        }
        .title {
            font-weight: bold;
            color: #b42d8f;
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
                <%=MainView.getTopBar("Mantenimiento Diagnosticos", "Lista de todos los diagnosticos", "admin", "admin", user_icon)%>
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
                                                    <h3 class="title">Diagnósticos Finalizados</h3>
                                                    <table style="width: 100%;" id="tblAgenda"
                                                           class="table table-hover table-striped table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Código Paciente</th>
                                                            <th>Fecha</th>
                                                            <th>Urgente</th>
                                                            <th>DNI</th>
                                                            <th>Nombre</th>
                                                            <th>Apellidos</th>
                                                            <th>Tfno</th>
                                                            <th>correo</th>
                                                            <th>Nacimiento</th>
                                                        </tr>
                                                        </thead>
                                                        <tfoot>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Código Paciente</th>
                                                            <th>Fecha</th>
                                                            <th>Urgente</th>
                                                            <th>DNI</th>
                                                            <th>Nombre</th>
                                                            <th>Apellidos</th>
                                                            <th>Tfno</th>
                                                            <th>correo</th>
                                                            <th>Nacimiento</th>
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
    <form id="diagnostico_id" name="diagnostico_id" method="post">
        <input type="hidden" name="diagnosticoID" id="diagnosticoID">
    </form>
    <form id="eliminar_diagnostico" name="eliminar_diagnostico" method="post">
        <input type="hidden" name="eliminar_diagnostico_id" id="eliminar_diagnostico_id">
    </form>
</div>

<%=MainView.getImportsScripts(false, true, false, true, true)%>
<script>

    var table_Agenda;
    $(document).ready(function () {
        table_Agenda = $('#tblAgenda').DataTable({
            dom: 'Bfrtip',
            processing: true,
            searching: true,
            serverSide: true,
            pageLength: 10,
            select: {
                style: 'single'
            },
            ajax: "/backoffice/services/diagnostico/getCustomDataTable_diagnosticos_finalizados",
            aoColumns: [
                {"bSortable": true, "bSearchable": false, "bVisible": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": false, "bSearchable": false},
                {"bSortable": true, "bSearchable": false, "render": function (data, type, row) {
                        if (data==='1'){return 'Si'}else return 'No';
                    }},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false}],
            buttons: [
                {
                    text: 'Realizar Diagnóstico',
                    action: function (e, dt, node, config) {
                        CrearConcepto();
                    },

                    enabled: true
                },
                {

                    text: 'Editar',
                    action: function (e, dt, node, config) {
                        editarConcepto();
                    },
                    enabled: true
                },
                {
                    text: 'Eliminar',
                    action: function (e, dt, node, config) {
                        eliminarConcepto();
                    },
                    enabled: true
                },

            ]
        });

        table_Agenda.on('select deselect', function () {
            var selectedRows = table_Agenda.rows({selected: true}).count();
            //console.log('x' + selectedRows);
            table_Agenda.button(1).enable(selectedRows === 1);
        });

        var CrearConcepto = function () {
            var selData = table_Agenda.rows(".selected").data();
            var form_main = $("#diagnostico_id")
            var input = $("#diagnosticoID")
            input.val(selData[0][0]);
            console.log(selData[0][0]);
            form_main.attr('action', "<%=Settings.contentRootModules%>" +'informes/crear_informe.jsp');
            form_main.submit();
        };
        table_Agenda.on('dblclick', 'tr', function () {
            var selData = table_Agenda.rows(".selected").data();
            var form_main = $("#diagnostico_id")
            var input = $("#diagnosticoID")
            input.val(selData[0][0]);
            console.log(selData[0][0]);
            form_main.attr('action', "<%=Settings.contentRootModules%>" +'informes/crear_informe.jsp');
            form_main.submit();
        } );

        table_Agenda.button(0).nodes().css('background', '#b42d8f');
        table_Agenda.button(0).nodes().css('color', '#ffffff');
        table_Agenda.button(1).nodes().css('background', '#b42d8f');
        table_Agenda.button(1).nodes().css('color', '#ffffff');
        table_Agenda.button(2).nodes().css('background', '#b42d8f');
        table_Agenda.button(2).nodes().css('color', '#ffffff');
        var editarConcepto = function () {
            var selData = table_Agenda.rows(".selected").data();
            var form_main = $("#diagnostico_id")
            var input = $("#diagnosticoID")
            input.val(selData[0][0]);
            form_main.attr('action', "<%=Settings.contentRootModules%>" +'informes/crear_informe.jsp');
            form_main.submit();
        };
        var eliminarConcepto = function () {
            var selData = table_Agenda.rows(".selected").data();
            var form_main = $("#eliminar_diagnostico")
            var input = $("#eliminar_diagnostico_id")
            input.val(selData[0][0]);
            form_main.attr('action', "<%=Settings.contentRootModules%>" +'diagnosticos/tabla_diagnosticos_derma.jsp');
            form_main.submit();
        };


        $('#tblAgenda_next').removeAttr('disabled');

        TBL_AGENDA_TBL = $('#tblAgenda').DataTable();
    })

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
</script>
</body>
</html>
<%
        } else {
            response.sendRedirect("/backoffice/login.jsp");
        }
    } else {
        response.sendRedirect("/backoffice/login.jsp");
    }
%>