<%@ page import="com.disderm.auth.Heimdall" %>
<%@ page import="com.disderm.model.CustomAnswerModel" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.disderm.service.SystemMailService" %>
<%@ page import="com.disderm.utils.Varios" %>
<%
    response.addHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    String content = "";
    CustomAnswerModel csam = new CustomAnswerModel();


%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Recuperar clave GINEAPP</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"
    />
    <meta name="description" content="KeroUI HTML Bootstrap 4 Dashboard Template">

    <!-- Disable tap highlight on IE -->
    <meta name="msapplication-tap-highlight" content="no">

    <link href="css_framework.css" rel="stylesheet">
    <link rel="icon" href="assets/images/gineapp/favicon.ico" type="image/ico"/>
</head>

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


</style>
<body>
<div class="app-container app-theme-white body-tabs-shadow">
    <div class="app-container">
        <div class="h-100">
            <div class="h-100 no-gutters row">
                <div class="d-none d-lg-block col-lg-4">
                    <div class="slider-light">
                        <div class="slick-slider slick-initialized">
                            <div>
                                <div class="h-100 d-flex justify-content-center align-items-center bg-premium-dark"
                                     tabindex="-1">
                                    <div class="slide-img-bg"
                                         style="background-image: url('assets/images/gineapp/register_background.jpg');"></div>
                                    <div class="slider-content"><h3>Gineapp, ecograf&iacute;as, citas..</h3>
                                        <p>Todo al alcance de su mano</p></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="h-100 d-flex bg-white justify-content-center align-items-center col-md-12 col-lg-8">
                    <div class="mx-auto app-login-box col-sm-12 col-md-8 col-lg-6">
                        <div class="app-logo-gineapp">
                            <img alt="Gineapp" height="82" longdesc="http://www.gineapp.com"
                                 src="assets/images/gineapp/logo_gineapp_medium.png" width="340"/>

                        </div>
                        <%=content%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js_framework.js"></script>
<script>
    function go() {
        location.href = "<%=request.getRequestURI()%>";
    }
</script>

</body>
</html>