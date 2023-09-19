<%@ page import="com.disderm.view.MainView" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.disderm.utils.Crypto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

    Long id = 0L;
    boolean is_processing = false;


    if (request.getParameter("id") != null) {
        id = Long.parseLong(request.getParameter("id"));
    }


%>
<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="utf-8">
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <!--<meta http-equiv="Content-Language" content="es">-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>ArchitectUI HTML Demo</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>

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

                <%=MainView.getTopBar("Edicion usuario","Edite los datos del usuario registrado en GineApp")%>
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





                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <table style="width: 100%;" id="ecografias_table"
                                                       class="table table-hover table-striped table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Apellidos</th>
                                                        <th>Nombre</th>
                                                        <th>F.Nac</th>
                                                        <th>F.Nac</th>

                                                    </tr>
                                                    </thead>
                                                    <tfoot>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Apellidos</th>
                                                        <th>Nombre</th>
                                                        <th>F.Nac</th>
                                                        <th>DNI</th>
                                                        <th>NHIS</th>
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
            <div class="app-wrapper-footer">
                <%=MainView.getFooter()%>
            </div>
        </div>
        <!--THEME OPTIONS START-->
        <%=MainView.getThemeSettings()%>

        <!--THEME OPTIONS END-->
    </div>
    <!--DRAWER START-->
    <div class="app-drawer-wrapper">
        <div class="drawer-nav-btn">
            <button type="button" class="hamburger hamburger--elastic is-active">
                <span class="hamburger-box"><span class="hamburger-inner"></span></span></button>
        </div>
        <div class="drawer-content-wrapper">
            <div class="scrollbar-container">
                <h3 class="drawer-heading">Servers Status</h3>
                <div class="drawer-section">
                    <div class="row">
                        <div class="col">
                            <div class="progress-box"><h4>Server Load 1</h4>
                                <div class="circle-progress circle-progress-gradient-xl mx-auto">
                                    <small></small>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="progress-box"><h4>Server Load 2</h4>
                                <div class="circle-progress circle-progress-success-xl mx-auto">
                                    <small></small>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="progress-box"><h4>Server Load 3</h4>
                                <div class="circle-progress circle-progress-danger-xl mx-auto">
                                    <small></small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="divider"></div>
                    <div class="mt-3"><h5 class="text-center card-title">Live Statistics</h5>
                        <div id="sparkline-carousel-3"></div>
                        <div class="row">
                            <div class="col">
                                <div class="widget-chart p-0">
                                    <div class="widget-chart-content">
                                        <div class="widget-numbers text-warning fsize-3">43</div>
                                        <div class="widget-subheading pt-1">Packages</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="widget-chart p-0">
                                    <div class="widget-chart-content">
                                        <div class="widget-numbers text-danger fsize-3">65</div>
                                        <div class="widget-subheading pt-1">Dropped</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="widget-chart p-0">
                                    <div class="widget-chart-content">
                                        <div class="widget-numbers text-success fsize-3">18</div>
                                        <div class="widget-subheading pt-1">Invalid</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="divider"></div>
                        <div class="text-center mt-2 d-block">
                            <button class="mr-2 border-0 btn-transition btn btn-outline-danger">Escalate Issue</button>
                            <button class="border-0 btn-transition btn btn-outline-success">Support Center</button>
                        </div>
                    </div>
                </div>
                <h3 class="drawer-heading">File Transfers</h3>
                <div class="drawer-section p-0">
                    <div class="files-box">
                        <ul class="list-group list-group-flush">
                            <li class="pt-2 pb-2 pr-2 list-group-item">
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left opacity-6 fsize-2 mr-3 text-primary center-elem">
                                            <i class="fa fa-file-alt"></i>
                                        </div>
                                        <div class="widget-content-left">
                                            <div class="widget-heading font-weight-normal">TPSReport.docx</div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <button class="btn-icon btn-icon-only btn btn-link btn-sm">
                                                <i class="fa fa-cloud-download-alt"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="pt-2 pb-2 pr-2 list-group-item">
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left opacity-6 fsize-2 mr-3 text-warning center-elem">
                                            <i class="fa fa-file-archive"></i>
                                        </div>
                                        <div class="widget-content-left">
                                            <div class="widget-heading font-weight-normal">Latest_photos.zip</div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <button class="btn-icon btn-icon-only btn btn-link btn-sm">
                                                <i class="fa fa-cloud-download-alt"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="pt-2 pb-2 pr-2 list-group-item">
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left opacity-6 fsize-2 mr-3 text-danger center-elem">
                                            <i class="fa fa-file-pdf"></i>
                                        </div>
                                        <div class="widget-content-left">
                                            <div class="widget-heading font-weight-normal">Annual Revenue.pdf</div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <button class="btn-icon btn-icon-only btn btn-link btn-sm">
                                                <i class="fa fa-cloud-download-alt"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="pt-2 pb-2 pr-2 list-group-item">
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left opacity-6 fsize-2 mr-3 text-success center-elem">
                                            <i class="fa fa-file-excel"></i>
                                        </div>
                                        <div class="widget-content-left">
                                            <div class="widget-heading font-weight-normal">Analytics_GrowthReport.xls
                                            </div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <button class="btn-icon btn-icon-only btn btn-link btn-sm">
                                                <i class="fa fa-cloud-download-alt"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <h3 class="drawer-heading">Tasks in Progress</h3>
                <div class="drawer-section p-0">
                    <div class="todo-box">
                        <ul class="todo-list-wrapper list-group list-group-flush">
                            <li class="list-group-item">
                                <div class="todo-indicator bg-warning"></div>
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left mr-2">
                                            <div class="custom-checkbox custom-control">
                                                <input type="checkbox" id="exampleCustomCheckbox1266"
                                                       class="custom-control-input">
                                                <label class="custom-control-label"
                                                       for="exampleCustomCheckbox1266">&nbsp;</label></div>
                                        </div>
                                        <div class="widget-content-left">
                                            <div class="widget-heading">Wash the car
                                                <div class="badge badge-danger ml-2">Rejected</div>
                                            </div>
                                            <div class="widget-subheading"><i>Written by Bob</i></div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <button class="border-0 btn-transition btn btn-outline-success">
                                                <i class="fa fa-check"></i>
                                            </button>
                                            <button class="border-0 btn-transition btn btn-outline-danger">
                                                <i class="fa fa-trash-alt"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="todo-indicator bg-focus"></div>
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left mr-2">
                                            <div class="custom-checkbox custom-control">
                                                <input type="checkbox" id="exampleCustomCheckbox1666"
                                                       class="custom-control-input">
                                                <label class="custom-control-label"
                                                       for="exampleCustomCheckbox1666">&nbsp;</label></div>
                                        </div>
                                        <div class="widget-content-left">
                                            <div class="widget-heading">Task with hover dropdown menu</div>
                                            <div class="widget-subheading">
                                                <div>By Johnny
                                                    <div class="badge badge-pill badge-info ml-2">NEW</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <div class="d-inline-block dropdown">
                                                <button type="button" data-toggle="dropdown" aria-haspopup="true"
                                                        aria-expanded="false"
                                                        class="border-0 btn-transition btn btn-link">
                                                    <i class="fa fa-ellipsis-h">
                                                    </i>
                                                </button>
                                                <div tabindex="-1" role="menu" aria-hidden="true"
                                                     class="dropdown-menu dropdown-menu-right"><h6 tabindex="-1"
                                                                                                   class="dropdown-header">
                                                    Header</h6>
                                                    <button type="button" disabled="" tabindex="-1"
                                                            class="disabled dropdown-item">Action
                                                    </button>
                                                    <button type="button" tabindex="0" class="dropdown-item">Another
                                                        Action
                                                    </button>
                                                    <div tabindex="-1" class="dropdown-divider"></div>
                                                    <button type="button" tabindex="0" class="dropdown-item">Another
                                                        Action
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="todo-indicator bg-primary"></div>
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left mr-2">
                                            <div class="custom-checkbox custom-control">
                                                <input type="checkbox" id="exampleCustomCheckbox4777"
                                                       class="custom-control-input">
                                                <label class="custom-control-label"
                                                       for="exampleCustomCheckbox4777">&nbsp;</label></div>
                                        </div>
                                        <div class="widget-content-left flex2">
                                            <div class="widget-heading">Badge on the right task</div>
                                            <div class="widget-subheading">This task has show on hover actions!</div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <button class="border-0 btn-transition btn btn-outline-success">
                                                <i class="fa fa-check">
                                                </i>
                                            </button>
                                        </div>
                                        <div class="widget-content-right ml-3">
                                            <div class="badge badge-pill badge-success">Latest Task</div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="todo-indicator bg-info"></div>
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left mr-2">
                                            <div class="custom-checkbox custom-control">
                                                <input type="checkbox" id="exampleCustomCheckbox2444"
                                                       class="custom-control-input">
                                                <label class="custom-control-label"
                                                       for="exampleCustomCheckbox2444">&nbsp;</label></div>
                                        </div>
                                        <div class="widget-content-left mr-3">
                                            <div class="widget-content-left"><img width="42" class="rounded"
                                                                                  src="<c:url value='/assets_intranet/images/avatars/1.jpg' />"
                                                                                  alt=""/></div>
                                        </div>
                                        <div class="widget-content-left">
                                            <div class="widget-heading">Go grocery shopping</div>
                                            <div class="widget-subheading">A short description ...</div>
                                        </div>
                                        <div class="widget-content-right widget-content-actions">
                                            <button class="border-0 btn-transition btn btn-sm btn-outline-success">
                                                <i class="fa fa-check"></i>
                                            </button>
                                            <button class="border-0 btn-transition btn btn-sm btn-outline-danger">
                                                <i class="fa fa-trash-alt"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="todo-indicator bg-success"></div>
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left mr-2">
                                            <div class="custom-checkbox custom-control">
                                                <input type="checkbox" id="exampleCustomCheckbox3222"
                                                       class="custom-control-input">
                                                <label class="custom-control-label"
                                                       for="exampleCustomCheckbox3222">&nbsp;</label></div>
                                        </div>
                                        <div class="widget-content-left flex2">
                                            <div class="widget-heading">Development Task</div>
                                            <div class="widget-subheading">Finish React ToDo List App</div>
                                        </div>
                                        <div class="widget-content-right">
                                            <div class="badge badge-warning mr-2">69</div>
                                        </div>
                                        <div class="widget-content-right">
                                            <button class="border-0 btn-transition btn btn-outline-success">
                                                <i class="fa fa-check">
                                                </i>
                                            </button>
                                            <button class="border-0 btn-transition btn btn-outline-danger">
                                                <i class="fa fa-trash-alt">
                                                </i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <h3 class="drawer-heading">Urgent Notifications</h3>
                <div class="drawer-section">
                    <div class="notifications-box">
                        <div class="vertical-time-simple vertical-without-time vertical-timeline vertical-timeline--one-column">
                            <div class="vertical-timeline-item dot-danger vertical-timeline-element">
                                <div><span class="vertical-timeline-element-icon bounce-in"></span>
                                    <div class="vertical-timeline-element-content bounce-in"><h4 class="timeline-title">
                                        All
                                        Hands Meeting</h4><span class="vertical-timeline-element-date"></span></div>
                                </div>
                            </div>
                            <div class="vertical-timeline-item dot-warning vertical-timeline-element">
                                <div><span class="vertical-timeline-element-icon bounce-in"></span>
                                    <div class="vertical-timeline-element-content bounce-in"><p>Yet another one, at
                                        <span
                                                class="text-success">15:00 PM</span></p><span
                                            class="vertical-timeline-element-date"></span></div>
                                </div>
                            </div>
                            <div class="vertical-timeline-item dot-success vertical-timeline-element">
                                <div><span class="vertical-timeline-element-icon bounce-in"></span>
                                    <div class="vertical-timeline-element-content bounce-in">
                                        <h4 class="timeline-title">Build the production release
                                            <div class="badge badge-danger ml-2">NEW</div>
                                        </h4>
                                        <span class="vertical-timeline-element-date"></span></div>
                                </div>
                            </div>
                            <div class="vertical-timeline-item dot-primary vertical-timeline-element">
                                <div><span class="vertical-timeline-element-icon bounce-in"></span>
                                    <div class="vertical-timeline-element-content bounce-in">
                                        <h4 class="timeline-title">Something not important
                                            <div class="avatar-wrapper mt-2 avatar-wrapper-overlap">
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/1.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/2.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/3.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/4.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/5.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/6.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/7.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm">
                                                    <div class="avatar-icon"><img
                                                            src="<c:url value='/assets_intranet/images/avatars/8.jpg' />"
                                                            alt=""></div>
                                                </div>
                                                <div class="avatar-icon-wrapper avatar-icon-sm avatar-icon-add">
                                                    <div class="avatar-icon"><i>+</i></div>
                                                </div>
                                            </div>
                                        </h4>
                                    </div>
                                    <span class="vertical-timeline-element-date"></span></div>
                            </div>
                        </div>
                        <div class="vertical-timeline-item dot-info vertical-timeline-element">
                            <div><span class="vertical-timeline-element-icon bounce-in"></span>
                                <div class="vertical-timeline-element-content bounce-in"><h4 class="timeline-title">This
                                    dot has an info state</h4><span class="vertical-timeline-element-date"></span></div>
                            </div>
                        </div>
                        <div class="vertical-timeline-item dot-dark vertical-timeline-element">
                            <div><span class="vertical-timeline-element-icon is-hidden"></span>
                                <div class="vertical-timeline-element-content is-hidden"><h4 class="timeline-title">This
                                    dot has a dark state</h4><span class="vertical-timeline-element-date"></span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
        $('#exampleTable2').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/backoffice/services/pacientes/getAllDataTable",
            "lengthMenu": [ 10, 25, 50 ],
            "pageLength":25
        });
    });
    $.extend( true, $.fn.dataTable.defaults, {
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
                "sortAscending":  ": Activar para ordenar la columna de manera ascendente",
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
    } );
    /* $(document).ready(function () {
         $('#exampleTable3').DataTable({
             "processing": true,
             "serverSide": true,
             "ajax": "SubSearch"
         });
     });*/

</script>

<!--SCRIPTS INCLUDES-->
<script>


    $(document).ready(function () {
        $('#ecografias_table').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": "/backoffice/services/app_ecografias/getAllDataTable/<%=id%>"
        });




    });
    $.extend( true, $.fn.dataTable.defaults, {
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
                "sortAscending":  ": Activar para ordenar la columna de manera ascendente",
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
    } );
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
