package com.disderm.view;

import com.disderm.utils.Settings;
import com.disderm.utils.Varios;

public class MainView {

    public static String getMenu() {
        String reply = "    <div class=\"app-sidebar-wrapper\">\n" +
                "               <div class=\"app-sidebar sidebar-shadow\">\n" +
                "                   <div class=\"app-header__logo\">\n" +
                "                       <a href=\"#\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Disderm Menu\"\n" +
                "                       class=\"logo-src\"></a>\n" +
                "                       <button type=\"button\" class=\"hamburger hamburger--elastic mobile-toggle-nav\">\n" +
                "                           <span class=\"hamburger-box\">\n" +
                "                               <span class=\"hamburger-inner\"></span>\n" +
                "                           </span>\n" +
                "                       </button>\n" +
                "               </div>\n" +
                "           <div class=\"scrollbar-sidebar scrollbar-container\">\n" +
                "               <div class=\"app-sidebar__inner\">\n" +
                "                   <ul class=\"vertical-nav-menu\">\n" +
                "                       <li class=\"app-sidebar__heading\">Menu</li>\n" +
                "<!-- ARRANCAMOS MENUS --> " +

                //PACIENTES DESACTIVADOS EN EL MENU LATERLA. Descomentar si es necesario
                /**"<!-- ITEM --> " +
                "<li>\n" +
                " <a href=\"#\">\n" +
                " <i class=\"metismenu-icon pe-7s-plugin\"></i>\n" +
                " Pacientes\n" +
                " <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>\n" +
                " </a>\n" +
                " <ul>\n" +
                " <li><a href=\"/backoffice/modulos/pacientes/crear_paciente.jsp\">Nuevo</a></li>\n" +
                " <li><a href=\"/backoffice/modulos/pacientes/tabla_pacientes.jsp\">Consultar</a></li>" +
                " </ul>" +
                " </li>" +
                "<!--FIN ITEM --> " +*/

                "<!-- ITEM --> " +
                "<li>\n" +
                " <a href=\"/backoffice/modulos/diagnosticos/tabla_diagnosticos_derma.jsp\">\n" +
                " <i class=\"metismenu-icon pe-7s-plugin\"></i>\n" +
                " Diagnósticos Pendientes\n" +
                " </a>\n" +
                " </li>" +
                "<!--FIN ITEM --> " +

                "<!-- ITEM --> " +
                "<li>\n" +
                " <a href=\"/backoffice/modulos/diagnosticos/tabla_diagnosticos_finalizados.jsp\">\n" +
                " <i class=\"metismenu-icon pe-7s-plugin\"></i>\n" +
                " Diagnósticos Finalizados\n" +
                " </a>\n" +
                " </li>" +
                "<!--FIN ITEM --> " +

                "<!--- FIN MENU -->" +
                "</ul>" +
                "</div>\n" +
                " </div>\n" +
                " </div>\n" +
                " </div>";
        return reply;
    }

    public static String getDrawer(){
        String reply = "";
        reply = "";
        reply = "";
        return reply;
    }

    public static String getBasicFooter() {
        String reply = "<div class=\"app-footer\">\n" +
                "                    <div class=\"app-footer__inner\">\n" +
                "                        <div class=\"app-footer-left\">\n" +
                "                            DISDERM</div>\n" +
                "                        </div>\n" +
                "                        <div class=\"app-footer-right\">\n" +
                "                        2020 1.01</div>\n" +
                "                    </div>\n" +
                "                </div>";
        return reply;
    }

    public static String getFooter() {
        String reply = "<div class=\"app-footer\">\n" +
                "                    <div class=\"app-footer__inner\">\n" +
                "                        <div class=\"app-footer-right\">\n" +
                "                            <ul class=\"header-megamenu nav\">\n" +
                "                                <li class=\"nav-item\">\n" +
                "                                    <a data-placement=\"top\" rel=\"popover-focus\" data-offset=\"300\"\n" +
                "                                       data-toggle=\"popover-custom\"\n" +
                "                                       class=\"nav-link\">\n" +
                "                                        Footer Menu\n" +
                "                                        <div class=\"badge badge-success ml-0 ml-1\">\n" +
                "                                            <small>Old</small>\n" +
                "                                        </div>\n" +
                "                                        <i class=\"fa fa-angle-up ml-2 opacity-8\"></i>\n" +
                "                                    </a>\n" +
                "                                    <div class=\"rm-max-width rm-pointers\">\n" +
                "                                        <div class=\"d-none popover-custom-content\">\n" +
                "                                            <div class=\"dropdown-mega-menu dropdown-mega-menu-sm\">\n" +
                "                                                <div class=\"grid-menu grid-menu-2col\">\n" +
                "                                                    <div class=\"no-gutters row\">\n" +
                "                                                        <div class=\"col-sm-6 col-xl-6\">\n" +
                "                                                            <ul class=\"nav flex-column\">\n" +
                "                                                                <li class=\"nav-item-header nav-item\">Overview</li>\n" +
                "                                                                <li class=\"nav-item\"><a href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link\"><i\n" +
                "                                                                        class=\"nav-link-icon lnr-inbox\"> </i><span>Contacts</span></a>\n" +
                "                                                                </li>\n" +
                "                                                                <li class=\"nav-item\"><a href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link\"><i\n" +
                "                                                                        class=\"nav-link-icon lnr-book\"> </i><span>Incidents</span>\n" +
                "                                                                    <div class=\"ml-auto badge badge-pill badge-danger\">\n" +
                "                                                                        5\n" +
                "                                                                    </div>\n" +
                "                                                                </a></li>\n" +
                "                                                                <li class=\"nav-item\"><a href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link\"><i\n" +
                "                                                                        class=\"nav-link-icon lnr-picture\"> </i><span>Companies</span></a>\n" +
                "                                                                </li>\n" +
                "                                                                <li class=\"nav-item\"><a disabled=\"\"\n" +
                "                                                                                        href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link disabled\"><i\n" +
                "                                                                        class=\"nav-link-icon lnr-file-empty\"> </i><span>Dashboards</span></a>\n" +
                "                                                                </li>\n" +
                "                                                            </ul>\n" +
                "                                                        </div>\n" +
                "                                                        <div class=\"col-sm-6 col-xl-6\">\n" +
                "                                                            <ul class=\"nav flex-column\">\n" +
                "                                                                <li class=\"nav-item-header nav-item\">Sales &amp;\n" +
                "                                                                    Marketing\n" +
                "                                                                </li>\n" +
                "                                                                <li class=\"nav-item\"><a href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link\">Queues</a></li>\n" +
                "                                                                <li class=\"nav-item\"><a href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link\">Resource\n" +
                "                                                                    Groups</a></li>\n" +
                "                                                                <li class=\"nav-item\"><a href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link\">Goal\n" +
                "                                                                    Metrics\n" +
                "                                                                    <div class=\"ml-auto badge badge-warning\">3</div>\n" +
                "                                                                </a></li>\n" +
                "                                                                <li class=\"nav-item\"><a href=\"javascript:void(0);\"\n" +
                "                                                                                        class=\"nav-link\">Campaigns</a>\n" +
                "                                                                </li>\n" +
                "                                                            </ul>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </li>\n" +
                "                            </ul>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>";
        return reply;
    }

    public static String getThemeSettings() {
        String reply = "<div class=\"ui-theme-settings\">\n" +
                "            <button type=\"button\" id=\"TooltipDemo\" class=\"btn-open-options btn btn-outline-2x btn-outline-focus\">\n" +
                "                <i class=\"fa fa-sync-alt icon-anim-pulse fa-2x\"></i>\n" +
                "            </button>\n" +
                "            <div class=\"theme-settings__inner\">\n" +
                "                <div class=\"scrollbar-container\">\n" +
                "                    <div class=\"theme-settings__options-wrapper\">\n" +
                "                        <h3 class=\"themeoptions-heading\">Opciones de accesibilida\n" +
                "                        </h3>\n" +
                "                        <div class=\"p-3\">\n" +
                "                            <ul class=\"list-group\">\n" +
                "                                <li class=\"list-group-item\">\n" +
                "                                    <div class=\"widget-content p-0\">\n" +
                "                                        <div class=\"widget-content-wrapper\">\n" +
                "                                            <div class=\"widget-content-left mr-3\">\n" +
                "                                                <div class=\"switch has-switch switch-container-class\"\n" +
                "                                                     data-class=\"app-sidebar-full\">\n" +
                "                                                    <div class=\"switch-animate switch-off\">\n" +
                "                                                        <input type=\"checkbox\" data-toggle=\"toggle\"\n" +
                "                                                               data-onstyle=\"success\">\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"widget-content-left\">\n" +
                "                                                <div class=\"widget-heading\">Activar menu para smartphones\n" +
                "                                                </div>\n" +
                "                                                <div class=\"widget-subheading\">Adapta el menu para su visualizacion en smartphones o tablets.\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </li>\n" +
                "                            </ul>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
        return reply;
    }


    public static String getTopBar(String _title,String _subtitle, String _visible_name, String _username, String _user_icon){
        String reply = "  <div class=\"app-header\">\n" +
                "                    <div class=\"app-header-left\">\n" +
                "                        <div class=\"app-logo-disderm\">\n" +
                "                             <img height=\"50\" alt=\"disderm\" longdesc=\"http://backoffice.disderm.com\"\n" +
                "                                  src=\""+ Settings.contentRoot +"assets/images/disderm/disderm-logo.jpeg\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"app-header-right\">\n" +
                "                        <div class=\"header-btn-lg pr-0\">\n" +
                "                            <div class=\"header-dots\">\n" +
                "                                <div class=\"dropdown\">\n" +
                "                                    <button type=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\"\n" +
                "                                            data-toggle=\"dropdown\"\n" +
                "                                            class=\"p-0 btn btn-link\">\n" +
                "                                        <i class=\"typcn typcn-bell\"></i>\n" +
                "                                        <span class=\"badge badge-dot badge-dot-sm badge-danger\">Notifications</span>\n" +
                "                                    </button>\n" +
                "                                    <div tabindex=\"-1\" role=\"menu\" aria-hidden=\"true\"\n" +
                "                                         class=\"dropdown-menu-xl rm-pointers dropdown-menu dropdown-menu-right\">\n" +
                "                                        <div class=\"dropdown-menu-header mb-0\">\n" +
                "                                            <div class=\"dropdown-menu-header-inner bg-night-sky\">\n" +
                "                                                <div class=\"menu-header-image opacity-5\"\n" +
                "                                                     style=\"background-image: url('<c:url\n" +
                "                                                             value='/assets_intranet/images/dropdown-header/city2.jpg'/>');\">\n" +
                "                                                </div>\n" +
                "                                                <div class=\"menu-header-content text-light\">\n" +
                "                                                    <h5 class=\"menu-header-title\">Notificaciones</h5>\n" +
                "                                                    <h6 class=\"menu-header-subtitle\">Tienes 20 mensajes sin leer\n" +
                "                                                    </h6>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                        <ul class=\"tabs-animated-shadow tabs-animated nav nav-justified tabs-shadow-bordered p-3\">\n" +
                "                                            <li class=\"nav-item\">\n" +
                "                                                <a role=\"tab\" class=\"nav-link active\" data-toggle=\"tab\"\n" +
                "                                                   href=\"#tab-messages-header\">\n" +
                "                                                    <span>Mensajes</span>\n" +
                "                                                </a>\n" +
                "                                            </li>\n" +
                "                                        </ul>\n" +
                "                                        <div class=\"tab-content\">\n" +
                "                                            <div class=\"tab-pane active\" id=\"tab-messages-header\" role=\"tabpanel\">\n" +
                "                                                <div class=\"scroll-area-sm\">\n" +
                "                                                    <div class=\"scrollbar-container\">\n" +
                "                                                        <div class=\"p-3\">\n" +
                "                                                            <div class=\"notifications-box\">\n" +
                "                                                                <div class=\"vertical-time-simple vertical-without-time vertical-timeline vertical-timeline--one-column\">\n" +
                "                                                                    <div class=\"vertical-timeline-item dot-danger vertical-timeline-element\">\n" +
                "                                                                        <div><span\n" +
                "                                                                                class=\"vertical-timeline-element-icon bounce-in\"></span>\n" +
                "                                                                            <div class=\"vertical-timeline-element-content bounce-in\">\n" +
                "                                                                                <h4 class=\"timeline-title\">Actualizacion 0.1</h4><span\n" +
                "                                                                                    class=\"vertical-timeline-element-date\"></span>\n" +
                "                                                                            </div>\n" +
                "                                                                        </div>\n" +
                "                                                                    </div>\n" +
                "                                                                </div>\n" +
                "                                                            </div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"tab-pane\" id=\"tab-events-header\" role=\"tabpanel\">\n" +
                "                                                <div class=\"scroll-area-sm\">\n" +
                "                                                    <div class=\"scrollbar-container\">\n" +
                "                                                        <div class=\"p-3\">\n" +
                "                                                            <div class=\"vertical-without-time vertical-timeline vertical-timeline--animate vertical-timeline--one-column\">\n" +
                "                                                                <div class=\"vertical-timeline-item vertical-timeline-element\">\n" +
                "                                                                    <div><span\n" +
                "                                                                            class=\"vertical-timeline-element-icon bounce-in\"><i\n" +
                "                                                                            class=\"badge badge-dot badge-dot-xl badge-success\"> </i></span>\n" +
                "                                                                        <div class=\"vertical-timeline-element-content bounce-in\">\n" +
                "                                                                            <h4 class=\"timeline-title\">Bievenido a Feelfarma</h4>\n" +
                "                                                                            <p>Lorem ipsum dolor sic amet, today at <a\n" +
                "                                                                                    href=\"javascript:void(0);\">12:00\n" +
                "                                                                                PM</a></p>\n" +
                "                                                                            <span class=\"vertical-timeline-element-date\"></span>\n" +
                "                                                                        </div>\n" +
                "                                                                    </div>\n" +
                "                                                                </div>\n" +
                "                                                                <div class=\"vertical-timeline-item vertical-timeline-element\">\n" +
                "                                                                    <div><span\n" +
                "                                                                            class=\"vertical-timeline-element-icon bounce-in\"><i\n" +
                "                                                                            class=\"badge badge-dot badge-dot-xl badge-warning\"> </i></span>\n" +
                "                                                                        <div class=\"vertical-timeline-element-content bounce-in\">\n" +
                "                                                                            <p>Another meeting today, at <b\n" +
                "                                                                                    class=\"text-danger\">12:00\n" +
                "                                                                                PM</b></p>\n" +
                "                                                                            <p>Yet another one, at <span\n" +
                "                                                                                    class=\"text-success\">15:00 PM</span>\n" +
                "                                                                            </p><span\n" +
                "                                                                                class=\"vertical-timeline-element-date\"></span>\n" +
                "                                                                        </div>\n" +
                "                                                                    </div>\n" +
                "                                                                </div>\n" +
                "                                                            </div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"tab-pane\" id=\"tab-errors-header\" role=\"tabpanel\">\n" +
                "                                                <div class=\"scroll-area-sm\">\n" +
                "                                                    <div class=\"scrollbar-container\">\n" +
                "                                                        <div class=\"no-results pt-3 pb-0\">\n" +
                "                                                            <div class=\"swal2-icon swal2-success swal2-animate-success-icon\">\n" +
                "                                                                <div class=\"swal2-success-circular-line-left\"\n" +
                "                                                                     style=\"background-color: rgb(255, 255, 255);\"></div>\n" +
                "                                                                <span class=\"swal2-success-line-tip\"></span>\n" +
                "                                                                <span class=\"swal2-success-line-long\"></span>\n" +
                "                                                                <div class=\"swal2-success-ring\"></div>\n" +
                "                                                                <div class=\"swal2-success-fix\"\n" +
                "                                                                     style=\"background-color: rgb(255, 255, 255);\"></div>\n" +
                "                                                                <div class=\"swal2-success-circular-line-right\"\n" +
                "                                                                     style=\"background-color: rgb(255, 255, 255);\"></div>\n" +
                "                                                            </div>\n" +
                "                                                            <div class=\"results-subtitle\">All caught up!</div>\n" +
                "                                                            <div class=\"results-title\">There are no system errors!</div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                        <ul class=\"nav flex-column\">\n" +
                "                                            <li class=\"nav-item-divider nav-item\"></li>\n" +
                "                                            <li class=\"nav-item-btn text-center nav-item\">\n" +
                "                                                <button class=\"btn-shadow btn-wide btn-pill btn btn-focus btn-sm\">View\n" +
                "                                                    Latest\n" +
                "                                                    Changes\n" +
                "                                                </button>\n" +
                "                                            </li>\n" +
                "                                        </ul>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"header-btn-lg pr-0\">\n" +
                "                            <div class=\"widget-content p-0\">\n" +
                "                                <div class=\"widget-content-wrapper\">\n" +
                "                                    <div class=\"widget-content-left\">\n" +
                "                                        <div class=\"btn-group\">\n" +
                "                                            <a data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\" class=\"p-0 btn\">\n" +
                "                                                <img width=\"42\" class=\"rounded\" src=\"/backoffice/assets_intranet/backoffice/feelfarma_user.png\" alt=\"\">\n" +
                "                                                <i class=\"fa fa-angle-down ml-2 opacity-8\"></i>\n" +
                "                                            </a>\n" +
                "                                            <div tabindex=\"-1\" role=\"menu\" aria-hidden=\"true\"\n" +
                "                                                 class=\"rm-pointers dropdown-menu-lg dropdown-menu dropdown-menu-right\">\n" +
                "                                                <div class=\"dropdown-menu-header\">\n" +
                "                                                    <div class=\"dropdown-menu-header-inner bg-info\">\n" +
                "                                                        <div class=\"menu-header-image opacity-2\"\n" +
                "                                                             style=\"background-image: url('<c:url\n" +
                "                                                                     value='/backoffice/assets_intranet/images/dropdown-header/city1.jpg');\">\n" +
                "                                                        </div>\n" +
                "                                                        <div class=\"menu-header-content text-left\">\n" +
                "                                                            <div class=\"widget-content p-0\">\n" +
                "                                                                <div class=\"widget-content-wrapper\">\n" +
                "                                                                    <div class=\"widget-content-left mr-3\">\n" +
                "                                                                        <img width=\"42\" class=\"rounded-circle\"\n" +
                "                                                                             src=\"/backoffice/assets_intranet/backoffice/feelfarma_user.png\"\n" +
                "                                                                             alt=\"\">\n" +
                "                                                                    </div>\n" +
                "                                                                    <div class=\"widget-content-left\">\n" +
                "                                                                        <div class=\"widget-heading\">" + _visible_name +
                "                                                                        </div>\n" +
                "                                                                        <div class=\"widget-subheading opacity-8\">" + _username +
                "                                                                        </div>\n" +
                "                                                                    </div>\n" +
                "                                                                    <div class=\"widget-content-right mr-2\">\n" +
                "                                                                        <button onclick="+ Varios.getLogoutLocalURL()+" class=\"btn-pill btn-shadow btn-shine btn btn-focus\">\n" +
                "                                                                            Salir\n" +
                "                                                                        </button>\n" +
                "                                                                    </div>\n" +
                "                                                                </div>\n" +
                "                                                            </div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"app-header-overlay d-none animated fadeIn\"></div>\n" +
                "                </div>";
        return reply;
    }

    public static String getScriptsImports() {

        String reply = "<!--CORE-->\n" +
                "<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"\n" +
                "        integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/metismenu\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/app.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/demo.js\"></script>\n" +
                "\n" +
                "<!--CHARTS-->\n" +
                "\n" +
                "<!--Apex Charts-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/charts/apex-charts.js\"></script>\n" +
                "\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/charts/apex-charts.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/charts/apex-series.js\"></script>\n" +
                "\n" +
                "<!--Sparklines-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/charts/charts-sparklines.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/charts/charts-sparklines.js\"></script>\n" +
                "\n" +
                "\n" +
                "<!--Sparklines-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/charts/charts-sparklines.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/charts/charts-sparklines.js\"></script>\n" +
                "\n" +
                "\n" +
                "<!--Chart.js-->\n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/charts/chartsjs-utils.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/charts/chartjs.js\"></script>\n" +
                "\n" +
                "<!--FORMS-->\n" +
                "\n" +
                "<!--Clipboard-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/clipboard.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/clipboard.js\"></script>\n" +
                "\n" +
                "<!--Datepickers-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/datepicker.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/daterangepicker.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/moment.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/datepicker.js\"></script>\n" +
                "\n" +
                "<!--Multiselect-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/bootstrap-multiselect.js\"></script>\n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/input-select.js\"></script>\n" +
                "\n" +
                "<!--Form Validation-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/form-validation.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/form-validation.js\"></script>\n" +
                "\n" +
                "<!--Form Wizard-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/form-wizard.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/form-wizard.js\"></script>\n" +
                "\n" +
                "<!--Input Mask-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/input-mask.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/input-mask.js\"></script>\n" +
                "\n" +
                "<!--RangeSlider-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/wnumb.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/range-slider.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/range-slider.js\"></script>\n" +
                "\n" +
                "<!--Textarea Autosize-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/textarea-autosize.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/form-components/textarea-autosize.js\"></script>\n" +
                "\n" +
                "<!--Toggle Switch -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/form-components/toggle-switch.js\"></script>\n" +
                "\n" +
                "\n" +
                "<!--COMPONENTS-->\n" +
                "\n" +
                "<!--BlockUI -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/blockui.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/blockui.js\"></script>\n" +
                "\n" +
                "<!--Calendar -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/calendar.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/calendar.js\"></script>\n" +
                "\n" +
                "<!--Slick Carousel -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/carousel-slider.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/carousel-slider.js\"></script>\n" +
                "\n" +
                "<!--Circle Progress -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/circle-progress.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/circle-progress.js\"></script>\n" +
                "\n" +
                "<!--CountUp -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/count-up.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/count-up.js\"></script>\n" +
                "\n" +
                "<!--Cropper -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/cropper.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/jquery-cropper.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/image-crop.js\"></script>\n" +
                "\n" +
                "<!--Maps -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/gmaps.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/jvectormap.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/maps-word-map.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/maps.js\"></script>\n" +
                "\n" +
                "<!--Guided Tours -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/guided-tours.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/guided-tours.js\"></script>\n" +
                "\n" +
                "<!--Ladda Loading Buttons -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/ladda-loading.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/spin.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/ladda-loading.js\"></script>\n" +
                "\n" +
                "<!--Rating -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/rating.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/rating.js\"></script>\n" +
                "\n" +
                "<!--Perfect Scrollbar -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/scrollbar.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/scrollbar.js\"></script>\n" +
                "\n" +
                "<!--Toastr-->\n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/toastr.js\"></script>\n" +
                "\n" +
                "<!--SweetAlert2-->\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@8\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/sweet-alerts.js\"></script>\n" +
                "\n" +
                "<!--Tree View -->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/treeview.js\"></script>\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/treeview.js\"></script>\n" +
                "\n" +
                "\n" +
                "<!--TABLES -->\n" +
                "<!--DataTables-->\n" +
                "<script src=\"https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/datatables.net-bs4@1.10.21/js/dataTables.bootstrap4.min.js\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "\n" +
                "<!--Bootstrap Tables-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/vendors/tables.js\"></script>\n" +
                "\n" +
                "<!--Tables Init-->\n" +
                "<script src=\"/backoffice/assets_intranet/js/scripts-init/tables.js\"></script>";
        return reply;
    }

    public static String getImportsScripts(boolean charts_imports, boolean form_imports, boolean maps_imports, boolean other_imports, boolean datatables_imports) {

        String reply = "<!--JS CORE IMPORTS-->\n" +
                "<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"\n" +
                "        integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://unpkg.com/metismenu\"></script>\n" +
                "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/app.js\"></script>\n" +
                "<script src=\"" + Settings.contentRootJavaScript + "vendors/juicebox/juicebox.js\"></script>\n";

        if (charts_imports)
        {
            reply +=  "\n" +
                    "<!--CHARTS-->\n" +
                    "\n" +
                    "<!--Apex Charts-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/charts/apex-charts.js\"></script>\n" +
                    "\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/charts/apex-charts.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/charts/apex-series.js\"></script>\n" +
                    "\n" +
                    "<!--Sparklines-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/charts/charts-sparklines.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/charts/charts-sparklines.js\"></script>\n" +
                    "\n" +
                    "\n" +
                    "<!--Sparklines-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/charts/charts-sparklines.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/charts/charts-sparklines.js\"></script>\n" +
                    "\n" +
                    "\n" +
                    "<!--Chart.js-->\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/charts/chartsjs-utils.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/charts/chartjs.js\"></script>\n" +
                    "\n";
        }
        if (form_imports) {
            reply += "<!--FORMS-->\n" +
                    "\n" +
                    "<!--Clipboard-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/clipboard.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/clipboard.js\"></script>\n" +
                    "\n" +
                    "<!--Datepickers-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/datepicker.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/daterangepicker.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/moment.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/datepicker.js\"></script>\n" +
                    "\n" +
                    "<!--Multiselect-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/bootstrap-multiselect.js\"></script>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/input-select.js\"></script>\n" +
                    "\n" +
                    "<!--Form Validation-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/form-validation.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/form-validation.js\"></script>\n" +
                    "\n" +
                    "<!--Form Wizard-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/form-wizard.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/form-wizard.js\"></script>\n" +
                    "\n" +
                    "<!--Input Mask-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/input-mask.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/input-mask.js\"></script>\n" +
                    "\n" +
                    "<!--RangeSlider-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/wnumb.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/range-slider.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/range-slider.js\"></script>\n" +
                    "\n" +
                    "<!--Textarea Autosize-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/textarea-autosize.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/form-components/textarea-autosize.js\"></script>\n" +
                    "\n" +
                    "<!--Toggle Switch -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/form-components/toggle-switch.js\"></script>\n" +
                    "\n" +
                    "\n" +
                    "<!--COMPONENTS-->\n" +
                    "\n" +
                    "<!--BlockUI -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/blockui.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/blockui.js\"></script>\n" +
                    "\n" +
                    "<!--Calendar -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/calendar.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/calendar.js\"></script>\n" +
                    "\n" +
                    "<!--Slick Carousel -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/carousel-slider.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/carousel-slider.js\"></script>\n" +
                    "\n" +
                    "<!--Circle Progress -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/circle-progress.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/circle-progress.js\"></script>\n" +
                    "\n" +
                    "<!--CountUp -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/count-up.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/count-up.js\"></script>\n" +
                    "\n" +
                    "<!--Cropper -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/cropper.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/jquery-cropper.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/image-crop.js\"></script>\n" +
                    "\n";
        }
        if (maps_imports){
            reply += "<!--Maps -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/gmaps.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/jvectormap.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/maps-word-map.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/maps.js\"></script>\n" +
                    "\n";
        }
        if (other_imports) {

            reply += "<!--Guided Tours -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/guided-tours.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/guided-tours.js\"></script>\n" +
                    "\n" +
                    "<!--Ladda Loading Buttons -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/ladda-loading.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/spin.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/ladda-loading.js\"></script>\n" +
                    "\n" +
                    "<!--Rating -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/rating.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/rating.js\"></script>\n" +
                    "\n" +
                    "<!--Perfect Scrollbar -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/scrollbar.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/scrollbar.js\"></script>\n" +
                    "\n" +
                    "<!--Toastr-->\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js\"\n" +
                    "        crossorigin=\"anonymous\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/toastr.js\"></script>\n" +
                    "\n" +
                    "<!--SweetAlert2-->\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@8\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/sweet-alerts.js\"></script>\n" +
                    "\n" +
                    "<!--Tree View -->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/treeview.js\"></script>\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/treeview.js\"></script>\n" +
                    "\n" +
                    "\n";
        }
        if (datatables_imports) {
            reply += "<!--TABLES -->\n" +
                    "<!--DataTables-->\n" +
                    "<script src=\"https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js\" crossorigin=\"anonymous\"></script>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/datatables.net-bs4@1.10.21/js/dataTables.bootstrap4.min.js\"\n" +
                    "        crossorigin=\"anonymous\"></script>\n" +
                    "<script src=\"https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js\"\n" +
                    "        crossorigin=\"anonymous\"></script>\n" +
                    "<script src=\"https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js\"></script>\n" +
                    "<script src=\"https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js\" crossorigin=\"anonymous\"></script>\n" +
                    "<script src=\"https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js\" crossorigin=\"anonymous\"></script>" +
                    "\n" +
                    "<!--Bootstrap Tables-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "vendors/tables.js\"></script>\n" +
                    "\n" +
                    "<!--Tables Init-->\n" +
                    "<script src=\"" + Settings.contentRootJavaScript + "scripts-init/tables.js\"></script>";
        }
        return reply;
    }
}

/**
 * ORIGINAL VIEW
 *
 * <div class="app-sidebar-wrapper">
 * <div class="app-sidebar sidebar-shadow">
 * <div class="app-header__logo">
 * <a href="#" data-toggle="tooltip" data-placement="bottom" title="KeroUI Admin Template"
 * class="logo-src"></a>
 * <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
 * <span class="hamburger-box">
 * <span class="hamburger-inner"></span>
 * </span>
 * </button>
 * </div>
 *
 *
 *
 * <div class="scrollbar-sidebar scrollbar-container">
 * <div class="app-sidebar__inner">
 * <ul class="vertical-nav-menu">
 * <li class="app-sidebar__heading">Menu</li>
 * <li>
 * <a href="#">
 * <i class="metismenu-icon pe-7s-rocket"></i>
 * Dashboards
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul>
 *
 *
 * <li><a href="index.html">Analytics</a></li>
 * <li><a href="lista_usuarios.jsp">Lista de usuarios</a></li>
 * <li><a href="advertisement-dashboard.html">Logs</a></li>
 * <li><a href="helpdesk-dashboard.html">Helpdesk</a></li>
 * <li><a href="monitoring-dashboard.html">Monitoring</a></li>
 * <li><a href="crypto-dashboard.html">Cryptocurrency</a></li>
 * <li><a href="pm-dashboard.html">Project Management</a></li>
 * <li><a href="product-dashboard.html">Product</a></li>
 * <li><a href="statistics-dashboard.html">Statistics</a></li>
 * </ul>
 * </li>
 * <li
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon pe-7s-browser"></i>
 * Registros
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul>
 * <li>
 * <a href="pages-login.html">
 * <i class="metismenu-icon"></i>
 * Logs
 * </a>
 * </li>
 * <li>
 * <a href="lista_usuarios.jsp">
 * <i class="metismenu-icon">
 * </i>Login Boxed
 * </a>
 * </li>
 * <li>
 * <a href="pages-register.html">
 * <i class="metismenu-icon">
 * </i>Register
 * </a>
 * </li>
 * <li>
 * <a href="pages-register-boxed.html">
 * <i class="metismenu-icon">
 * </i>Register Boxed
 * </a>
 * </li>
 * <li>
 * <a href="pages-forgot-password.html">
 * <i class="metismenu-icon">
 * </i>Forgot Password
 * </a>
 * </li>
 * <li>
 * <a href="pages-forgot-password-boxed.html">
 * <i class="metismenu-icon">
 * </i>Forgot Password Boxed
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon pe-7s-plugin"></i>
 * Applications
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul
 * <p>
 * <p>
 * >
 * <li>
 * <a href="mailbox.html">
 * <i class="metismenu-icon">
 * </i>Mailbox
 * </a>
 * </li>
 * <li>
 * <a href="chat.html">
 * <i class="metismenu-icon">
 * </i>Chat
 * </a>
 * </li>
 * <li>
 * <a href="faq-section.html">
 * <i class="metismenu-icon">
 * </i>FAQ Section
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li class="app-sidebar__heading">UI Components</li>
 * <li
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon pe-7s-diamond"></i>
 * Elements
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul
 * <p>
 * <p>
 * >
 * <li
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon"></i>
 * Buttons
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul
 * <p>
 * <p>
 * >
 * <li>
 * <a href="buttons-standard.html">
 * <i class="metismenu-icon">
 * </i>Standard
 * </a>
 * </li>
 * <li>
 * <a href="buttons-pills.html">
 * <i class="metismenu-icon">
 * </i>Pills
 * </a>
 * </li>
 * <li>
 * <a href="buttons-square.html">
 * <i class="metismenu-icon">
 * </i>Square
 * </a>
 * </li>
 * <li>
 * <a href="buttons-shadow.html">
 * <i class="metismenu-icon">
 * </i>Shadow
 * </a>
 * </li>
 * <li>
 * <a href="buttons-icons.html">
 * <i class="metismenu-icon">
 * </i>With Icons
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li>
 * <a href="dropdowns.html">
 * <i class="metismenu-icon">
 * </i>Dropdowns
 * </a>
 * </li>
 * <li>
 * <a href="icons.html">
 * <i class="metismenu-icon">
 * </i>Icons
 * </a>
 * </li>
 * <li>
 * <a href="badges-labels.html">
 * <i class="metismenu-icon">
 * </i>Badges
 * </a>
 * </li>
 * <li>
 * <a href="cards.html">
 * <i class="metismenu-icon">
 * </i>Cards
 * </a>
 * </li>
 * <li>
 * <a href="loaders.html">
 * <i class="metismenu-icon">
 * </i>Loading Indicators
 * </a>
 * </li>
 * <li>
 * <a href="list-group.html">
 * <i class="metismenu-icon">
 * </i>List Groups
 * </a>
 * </li>
 * <li>
 * <a href="navigation.html">
 * <i class="metismenu-icon">
 * </i>Navigation Menus
 * </a>
 * </li>
 * <li>
 * <a href="timelines.html">
 * <i class="metismenu-icon">
 * </i>Timeline
 * </a>
 * </li>
 * <li>
 * <a href="utilities.html">
 * <i class="metismenu-icon">
 * </i>Utilities
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon pe-7s-car"></i>
 * Components
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul
 * <p>
 * <p>
 * >
 * <li>
 * <a href="tabs.html">
 * <i class="metismenu-icon">
 * </i>Tabs
 * </a>
 * </li>
 * <li>
 * <a href="accordions.html">
 * <i class="metismenu-icon">
 * </i>Accordions
 * </a>
 * </li>
 * <li>
 * <a href="notifications.html">
 * <i class="metismenu-icon">
 * </i>Notifications
 * </a>
 * </li>
 * <li>
 * <a href="modals.html">
 * <i class="metismenu-icon">
 * </i>Modals
 * </a>
 * </li>
 * <li>
 * <a href="loading-blocks.html">
 * <i class="metismenu-icon">
 * </i>Loading Blockers
 * </a>
 * </li>
 * <li>
 * <a href="progress-bar.html">
 * <i class="metismenu-icon">
 * </i>Progress Bar
 * </a>
 * </li>
 * <li>
 * <a href="tooltips-popovers.html">
 * <i class="metismenu-icon">
 * </i>Tooltips &amp; Popovers
 * </a>
 * </li>
 * <li>
 * <a href="carousel.html">
 * <i class="metismenu-icon">
 * </i>Carousel
 * </a>
 * </li>
 * <li>
 * <a href="calendar.html">
 * <i class="metismenu-icon">
 * </i>Calendar
 * </a>
 * </li>
 * <li>
 * <a href="pagination.html">
 * <i class="metismenu-icon">
 * </i>Pagination
 * </a>
 * </li>
 * <li>
 * <a href="count-up.html">
 * <i class="metismenu-icon">
 * </i>Count Up
 * </a>
 * </li>
 * <li>
 * <a href="scrollable-elements.html">
 * <i class="metismenu-icon">
 * </i>Scrollable
 * </a>
 * </li>
 * <li>
 * <a href="tree-view.html">
 * <i class="metismenu-icon">
 * </i>Tree View
 * </a>
 * </li>
 * <li>
 * <a href="maps.html">
 * <i class="metismenu-icon">
 * </i>Maps
 * </a>
 * </li>
 * <li>
 * <a href="ratings.html">
 * <i class="metismenu-icon">
 * </i>Ratings
 * </a>
 * </li>
 * <li>
 * <a href="image-crop.html">
 * <i class="metismenu-icon">
 * </i>Image Crop
 * </a>
 * </li>
 * <li>
 * <a href="guided-tours.html">
 * <i class="metismenu-icon">
 * </i>Guided Tours
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li
 * class="mm-active"
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon pe-7s-display2"></i>
 * Tables
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul
 * class="mm-show"
 * <p>
 * <p>
 * >
 * <li>
 * <a href="data-tables.html" class="mm-active">
 * <i class="metismenu-icon">
 * </i>Data Tables
 * </a>
 * </li>
 * <li>
 * <a href="regular.html">
 * <i class="metismenu-icon">
 * </i>Regular Tables
 * </a>
 * </li>
 * <li>
 * <a href="grid.html">
 * <i class="metismenu-icon">
 * </i>Grid Tables
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li class="app-sidebar__heading">Dashboard Widgets</li>
 * <li>
 * <a href="chart-boxes.html">
 * <i class="metismenu-icon pe-7s-graph">
 * </i>Chart Boxes 1
 * </a>
 * </li>
 * <li>
 * <a href="chart-boxes-2.html">
 * <i class="metismenu-icon pe-7s-way">
 * </i>Chart Boxes 2
 * </a>
 * </li>
 * <li>
 * <a href="chart-boxes-3.html">
 * <i class="metismenu-icon pe-7s-ball">
 * </i>Chart Boxes 3
 * </a>
 * </li>
 * <li>
 * <a href="profile-boxes.html">
 * <i class="metismenu-icon pe-7s-id">
 * </i>Profile Boxes
 * </a>
 * </li>
 * <li class="app-sidebar__heading">Forms</li>
 * <li
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon pe-7s-light"></i>
 * Elements
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul
 * <p>
 * <p>
 * >
 * <li>
 * <a href="controls.html">
 * <i class="metismenu-icon">
 * </i>Controls
 * </a>
 * </li>
 * <li>
 * <a href="layouts.html">
 * <i class="metismenu-icon">
 * </i>Layouts
 * </a>
 * </li>
 * <li>
 * <a href="validation.html">
 * <i class="metismenu-icon">
 * </i>Validation
 * </a>
 * </li>
 * <li>
 * <a href="wizard.html">
 * <i class="metismenu-icon">
 * </i>Wizard
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li
 * <p>
 * <p>
 * >
 * <a href="#">
 * <i class="metismenu-icon pe-7s-joy"></i>
 * Widgets
 * <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
 * </a>
 * <ul
 * <p>
 * <p>
 * >
 * <li>
 * <a href="datepicker.html">
 * <i class="metismenu-icon">
 * </i>Datepicker
 * </a>
 * </li>
 * <li>
 * <a href="range-slider.html">
 * <i class="metismenu-icon">
 * </i>Range Slider
 * </a>
 * </li>
 * <li>
 * <a href="input-selects.html">
 * <i class="metismenu-icon">
 * </i>Input Selects
 * </a>
 * </li>
 * <li>
 * <a href="toggle-switch.html">
 * <i class="metismenu-icon">
 * </i>Toggle Switch
 * </a>
 * </li>
 * <li>
 * <a href="wysiwyg-editor.html">
 * <i class="metismenu-icon">
 * </i>WYSIWYG Editor
 * </a>
 * </li>
 * <li>
 * <a href="input-mask.html">
 * <i class="metismenu-icon">
 * </i>Input Mask
 * </a>
 * </li>
 * <li>
 * <a href="clipboard.html">
 * <i class="metismenu-icon">
 * </i>Clipboard
 * </a>
 * </li>
 * <li>
 * <a href="textarea-autosize.html">
 * <i class="metismenu-icon">
 * </i>Textarea Autosize
 * </a>
 * </li>
 * </ul>
 * </li>
 * <li class="app-sidebar__heading">Charts</li>
 * <li>
 * <a href="chartjs.html">
 * <i class="metismenu-icon pe-7s-graph2">
 * </i>ChartJS
 * </a>
 * </li>
 * <li>
 * <a href="apexcharts.html">
 * <i class="metismenu-icon pe-7s-graph">
 * </i>Apex Charts
 * </a>
 * </li>
 * <li>
 * <a href="sparklines.html">
 * <i class="metismenu-icon pe-7s-graph1">
 * </i>Chart Sparklines
 * </a>
 * </li>
 * </ul>
 * </div>
 * </div>
 * </div>
 * </div>
 */