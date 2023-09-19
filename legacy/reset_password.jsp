<%@ page import="com.disderm.auth.Heimdall" %>
<%@ page import="java.lang.reflect.Parameter" %>
<%

    response.addHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    Heimdall heimdall = new Heimdall();
    boolean itsOk = false;
    boolean finish = false;
    String content = "";
    String email = null;
    String token = request.getParameter("token");
    if (token != null) {
        email = heimdall.readMailIdFromToken(token);
        if (email != null) {
            itsOk = true;
        }
    }
    System.out.println("Nada mas llamar al archivo " + email);
    // Si tenemos que actualizar el password o no
    /* boolean sourceOk = false;
   String control = request.getParameter("control");
    if (control.equalsIgnoreCase("GINEAPPCONTROLCODE001-A"))
    {
        sourceOk = true;
    }*/
    if ((request.getParameter("isSubmit") == null) ? false : true) {
        finish = true;
        String password = request.getParameter("password");

        if (itsOk) {
            System.out.println("resetting password" + password + " " + email);
            heimdall.updatePacientePassword(password, email);
            content = " <h4>Operaci&oacute;n completada correctamente</h4><span>Su clave fue cambiada correctamente</span>\n<div class=\" mt-4 d-flex align-items-center\"><div class=\"ml-auto\"><button class=\"mb-2 mr-2 btn-icon btn-pill btn btn-primary\" onclick=\"goHome()\"><i class=\"lnr-chevron-left-circle btn-icon-wrapper\"> </i>Volver</button></div></div>";
        } else {
            content = " <h4>Operaci&oacute;n NO completada</h4><span>Ocurrio un error, intentelo de nuevo.</span>\n<div class=\" mt-4 d-flex align-items-center\"><div class=\"ml-auto\"><button class=\"mb-2 mr-2 btn-icon btn-pill btn btn-primary\" onclick=\"goHome()\"><i class=\"lnr-chevron-left-circle btn-icon-wrapper\"> </i>Volver</button></div></div>";
        }
    } else {
        finish = false;
    }
    if (itsOk) {
        System.out.println("CARGAMOS HTML");
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
                        <%
                            if (!finish) {
                        %>
                        <h4>
                            <div>&iquest;Olvid&oacute; su contrase&ntilde;a?</div>
                            <span>Introduzca su direcci&oacute;n de email a continuaci&oacute;n y recibir&aacute; instrucciones para recuperarlo.</span>
                        </h4>
                        <div>
                            <form class="" id="resetPassword" action="<%=request.getRequestURI()%>" method="post"
                                  oninput='up2.setCustomValidity(confirm_password.value != password.value ? "Las claves no coinciden" : "")'>
                                <div class="form-row">
                                    <div class="col-md-12">
                                        <div class="position-relative form-group">
                                            <input id="password" placeholder="Introduzca nueva clave" type="password"
                                                   class="form-control" required name=password>
                                        </div>
                                        <div class="position-relative form-group">
                                            <input id="confirm_password" placeholder="Repita nueva clave"
                                                   type="password"
                                                   class="form-control" name=confirm_password>
                                        </div>
                                        <input type="hidden" name="isSubmit" value="true">
                                        <input type="hidden" name="token" value="<%=token%>">

                                    </div>
                                </div>
                                <div class="mt-4 d-flex align-items-center">
                                    <div class="ml-auto">
                                        <button class="btn btn-primary btn-lg" onClick="go()">Restablecer clave</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <% } else { %>
                        <%=content%>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="js_framework.js"></script>
<script type="text/javascript" src="assets/js/validation/jquery.validate.js"></script>

<script>
    // Form Validation
    $(document).ready(() => {

        $("#resetPassword").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 8
                },
                confirm_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                }
            },
            messages: {

                password: {
                    required: "Por favor introduzca una clave",
                    minlength: "Su clave debe de contener al menos 8 caracteres"
                },
                confirm_password: {
                    required: "Por favor repita la clave",
                    equalTo: "Las claves deben de coincidir"
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `invalid-feedback` class to the error element
                error.addClass("invalid-feedback");
                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.next("label"));
                } else {
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

    });

    function go() {
        $("#resetPassword").validate();
        if ($('#resetPassword').valid())
            $('#resetPassword').submit();
    }

    function goHome() {
        location.href = "https://www.gineapp.com";
    }

</script>
</body>
</html>
<%
    }
%>