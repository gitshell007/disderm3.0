<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 08/11/2021
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.disderm.view.MainView" %>
<%@ page import="com.disderm.utils.Settings" %>
<%@ page import="com.disderm.auth.SessionBean" %>
<%@ page import="com.disderm.dao.PacienteDAO" %>
<%@ page import="com.disderm.model.DisdermusersModel" %>
<%@ page import="com.disderm.dao.DisdermusersDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% String usuario = "";
    Long user_id = 0L;

    DisdermusersModel usermodel = new DisdermusersModel();
    DisdermusersDAO userdao = new DisdermusersDAO();


    HttpSession httpSession = request.getSession();


    PacienteDAO pacienteDAO = new PacienteDAO();
    SessionBean sBean = (SessionBean) httpSession.getAttribute("sessionBean");
    if (sBean != null) {
        if ((sBean.getUser() != null) && (sBean.getSession_id() != null)) {
            //para eliminar
            if (request.getParameter("eliminar_paciente_id") != null) {
                pacienteDAO.deletePacientesByID(Long.valueOf(request.getParameter("eliminar_paciente_id")));
            }

            usuario = sBean.getUser().get_username();
            usermodel = userdao.getDisdermusersByUsername(usuario);
            user_id = usermodel.get_id();
            String user_icon = "/backoffice/assets_intranet/images/avatars/doctora.png";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Disderm BackOffice System</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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

                <%=MainView.getTopBar("Mantenimiento Pacientes", "Lista de todos los pacientes", "admin", "admin", user_icon)%>
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
                                                    <table style="width: 100%;" id="tblAgenda"
                                                           class="table table-hover table-striped table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>IDENTIFICADOR</th>
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
                                                            <th>IDENTIFICADOR</th>
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
    <form id="paciente_id" name="paciente_id" method="post">
        <input type="hidden" name="pacienteID" id="pacienteID">
    </form>
    <form id="eliminar_paciente" name="eliminar_paciente" method="post">
        <input type="hidden" name="eliminar_paciente_id" id="eliminar_paciente_id">
    </form>
</div>

<%=MainView.getImportsScripts(false, true, false, true, true)%>

<!-- END SCRIPTS -->

<!--SCRIPTS INCLUDES-->
<script>


    $(document).ready(function () {
        var table_Agenda = $('#tblAgenda').DataTable({
            dom: 'Bfrtip',
            processing: true,
            searching: true,
            serverSide: true,
            select: {
                style: 'single'
            },
            ajax: "/backoffice/services/paciente/getCustomDataTable_pacientes",
            aoColumns: [
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": false, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false},
                {"bSortable": true, "bSearchable": false}],
            pageLength: 25,
            buttons: [
                {
                    text: 'Crear',
                    action: function (e, dt, node, config) {
                        CrearConcepto();
                    },

                    enabled: true
                },
                {

                    text: '<ion-icon name="create-outline"></ion-icon>Editar',
                    action: function (e, dt, node, config) {
                        editarConcepto();
                    },
                    enabled: true
                },
                {
                    text: '<ion-icon name="create-outline"></ion-icon>Eliminar',
                    action: function (e, dt, node, config) {
                        eliminarConcepto();
                    },
                    enabled: true
                },

            ]
        });
        table_Agenda.button(0).nodes().css('background', '#b42d8f');
        table_Agenda.button(0).nodes().css('color', '#ffffff');
        table_Agenda.button(1).nodes().css('background', '#b42d8f');
        table_Agenda.button(1).nodes().css('color', '#ffffff');
        table_Agenda.button(2).nodes().css('background', '#b42d8f');
        table_Agenda.button(2).nodes().css('color', '#ffffff');
        table_Agenda.on('select deselect', function () {
            var selectedRows = table_Agenda.rows({selected: true}).count();
            //console.log('x' + selectedRows);
            table_Agenda.button(0).enable(selectedRows === 1);
        });


        var CrearConcepto = function () {
            window.location = "<%=Settings.contentRootModules%>" +'pacientes/crear_paciente.jsp';
        };
        var editarConcepto = function () {
            var selData = table_Agenda.rows(".selected").data();
            var form_main = $("#paciente_id")
            var input = $("#pacienteID")
            input.val(selData[0][0]);
            console.log(selData[0][0]);
            form_main.attr('action', "<%=Settings.contentRootModules%>" +'pacientes/editar_paciente.jsp');
            form_main.submit();
        };
        var eliminarConcepto = function () {
            var selData = table_Agenda.rows(".selected").data();
            var form_main = $("#eliminar_paciente")
            var input = $("#eliminar_paciente_id")
            input.val(selData[0][0]);
            form_main.attr('action', "<%=Settings.contentRootModules%>" +'pacientes/tabla_pacientes.jsp');
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