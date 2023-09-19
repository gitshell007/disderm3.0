<%@ page contentType="text/html;charset=iso-8859-15" language="java"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>MACROSS>COMM</title>


        <link rel="icon" type="image/ico" href="resources/images/favicon.ico">

        <link rel="shortcut icon" href="resources/images/favicon.ico">
        <link rel="stylesheet" type="text/css" href="resources/styles/style.css" />

        <script type="text/javascript" src="resources/scripts/extjs/lib/4.2/scripts/ext-all.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/scripts/extjs/lib/4.2/ext-theme-gray/ext-theme-gray-all.css" />

        <script type="text/javascript" src="resources/scripts/lib/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="resources/scripts/login.js"></script>

		<script type="text/javascript" src="resources/scripts/modernizer.js"></script>
		<script type="text/javascript" src="resources/scripts/jquery_placeholder.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300,900' rel='stylesheet' type='text/css'>
        <script type="text/javascript">
            var contextPath = '<%=request.getContextPath()%>';
        </script>
		<style>
			@import url(https://fonts.googleapis.com/css?family=Raleway:400,700);
			body {
				background: #ffffff url(resources/images/bg<%= (int) (Math.random() * 10) %>.jpg) no-repeat center top;
				-webkit-background-size: cover;
				-moz-background-size: cover;
				background-size: cover;
			}
			.container > header h1,
			.container > header h2 {
				color: #fff;
				text-shadow: 0 1px 1px rgba(0,0,0,0.7);
			}

            a.copyRightLinkLogin:link, a.copyRightLinkLogin:visited {
            color: #FFFFFF;
            }
            a.copyRightLinkLogin:hover {
            }
		</style>
    </head>
    <body onload="document.getElementById('username').focus();">
    <div class="container">

            <div class="codrops-top">
            	<img src="resources/images/logo_mini.png" width="32" style="vertical-align: middle; margin-left: 10px;  margin-right: 10px;  margin-bottom: 5px; margin-top: 5px;">
                # MACROSS-COMM #
                <span class="right">
                <a href="www.iacell.com/iacomm">
                    <strong>Support: </strong>support@iacell.com              </a>
                   </span>
            </div>

			<header>

				<h1> <img src="resources/images/logo_mini.png" width="64" style="vertical-align: middle"><strong>iA-Com</strong></h1>
				<h2>Please, enter your username and password</h2>
				<div class="support-note">
					<span class="note-ie">Optimized for Firefox and Chrome.</span>
				</div>

			</header>

			<section class="main">
				<form class="form-1" method="POST" enctype="application/x-www-form-urlencoded" action="auth/check.jsp">
				    <p class="field">
				     <span id="login_message" >
				     </span>
				    </p>
				    <p class="field">
				     <span id="post_error" class="error"></span>
				    </p>
					<p class="field">
						<input type="text" name="login" placeholder="Username" tabindex="1" id="username" value=""  />
						<i class="icon-user icon-large"></i>
					</p>

				     <p class="field">
							<input type="password" name="password" placeholder="Password" tabindex="2" id="password" value="" onkeydown="loginOnKeyDown(event);" />
							<i class="icon-lock icon-large"></i>
					</p>
					<p class="submit">
						<button type="submit" title="Login [Alt+L]" accessKey="Login [Alt+L]"  name="submit" tabindex="3" id="login_button" value="  Login  " onclick="check();return false;">
						<i class="icon-arrow-right icon-large"></i></button>
					</p>

                    <input type="hidden" name="module" value="Users">
                    <input type="hidden" name="action" value="Authenticate">
                    <input type="hidden" name="return_module" value="Users">
                    <input type="hidden" name="return_action" value="Login">
                    <input type="hidden" id="cant_login" name="cant_login" value="">

                    <input type="hidden" name="login_module" value="Home">
                    <input type="hidden" name="login_action" value="index">
                    <input type="hidden" name="login_record" value="">

				</form>
			</section>



            <script>
            $(function() {
                // Invoke the plugin
                $('input, textarea').placeholder();
               });
            </script>


    </div>
       <br/><br/><br/><br/>

        <div class="footer" >
            <table cellpadding='0' cellspacing='0' width='100%' border='0' class='underFooter'>
                <tr>
                    <td align='center' class='txt14_ver_ng_white'>
                       Optimized for <a href="http://www.mozilla-europe.org/es/firefox/" target="_blank" class="copyRightLinkLogin">Mozilla Firefox</a>,<a href="https://www.google.com/intl/es/chrome/browser/" target="_blank" class="copyRightLinkLogin">Chrome</a>
                        <br/><br/>&copy; 2010 - 2020
                        <br/>Copyright <a href="http://www.iacell.com" target="_blank" class="copyRightLinkLogin">iA-Cell Technologies Ltd</a>
                    </td>
                </tr>
            </table>
        </div>

    </body>
</html>