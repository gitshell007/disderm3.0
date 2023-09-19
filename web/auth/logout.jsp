<%@ page contentType="text/html;charset=iso-8859-15" language="java"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>iA-Comm</title>


        <link rel="icon" type="image/ico" href="../resources/images/favicon.ico">

        <link rel="shortcut icon" href="../resources/images/favicon.ico">
        <link rel="stylesheet" type="text/css" href="../resources/styles/style.css" />

        <script type="text/javascript" src="../resources/scripts/extjs/lib/4.2/scripts/ext-all.js"></script>
        <link rel="stylesheet" type="text/css" href="../resources/scripts/extjs/lib/4.2/ext-theme-gray/ext-theme-gray-all.css" />

        <script type="text/javascript" src="../resources/scripts/lib/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="../resources/scripts/login.js"></script>

		<script type="text/javascript" src="../resources/scripts/modernizer.js"></script>
		<script type="text/javascript" src="../resources/scripts/jquery_placeholder.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300,900' rel='stylesheet' type='text/css'>
        <script type="text/javascript">
            var contextPath = '<%=request.getContextPath()%>';
        </script>
		<style>
			@import url(https://fonts.googleapis.com/css?family=Raleway:400,700);
			body {
				background: #ffffff url(../resources/images/bg8.jpg) no-repeat center top;
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
            	<img src="../resources/images/logo_mini.png" width="32" style="vertical-align: middle; margin-left: 10px;  margin-right: 10px;  margin-bottom: 5px; margin-top: 5px;">
                Secure System Talk # ssTalk #
                <span class="right">
                <a href="www.iacell.com">
                    <strong>Support: </strong>support@iacell.com </a>
                   </span>
            </div>

			<header>


				<h2> The session has expired.<br/>
                    Please, register again using your Username and Password in the link below</h2>
                <h1> <a href="../" style="color:#cedff5">.:Login:. </a></h1>
				<div class="support-note">
					<span class="note-ie">Optimized for Firefox and Chrome.</span>
				</div>

			</header>

			<!--section class="main">
                <form method="post" action="../" name="logoutform" id="logoutform">
                    <table cellpadding="0" cellspacing="2" border="0" align="center" width="100%">
                        <tr>
                            <td  colspan="2" width="100%" style="font-size: 14px;font-weight: 300;margin: 0;padding: 15px 0 5px 0;color: #000000;font-family: Cambria, Georgia, serif;font-style: italic;">
                                <b>
                                    The session has expired.<br/>
                                    Please, register again using your Username and Password in the link below:
                                    <br/>
                                    <a href="../" class="vinculo">Access</a>
                                </b>

                            </td>
                        </tr>
                    </table>

				</form>
			</section-->



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
                        <br/><br/>&copy; 2010 - 2015
                        <br/>Copyright <a href="http://www.iacell.com" target="_blank" class="copyRightLinkLogin">iA-Cell Technologies Ltd</a>
                    </td>
                </tr>
            </table>
        </div>

    </body>
</html>