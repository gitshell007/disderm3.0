<jsp:useBean id="sessionBean" scope="session" class="com.agss.app.session.SessionBean"/>
<html>
<head>
<title><%= "ssTalk" %></title>

<link rel="stylesheet" type="text/css" href="resources/styles/sugar/navigation.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/sugar/style.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/sugar/colors.sugar.css" id="current_color_style" />
<link rel="stylesheet" type="text/css" href="resources/styles/sugar/fonts.normal.css" id="current_font_style"/>

<link rel="shortcut icon" type="image/x-icon" href="resources/images/favicon.ico" />
<link rel="icon" type="image/png" href="resources/images/favicon.ico" />

</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td colspan="2" id="header">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="logo" rowspan="2" valign="top">
						<img src="resources/images/logo_mini.png" width="240" height="60" alt="<%= "ssTalk" %>" border="0">
					</td>
					<td align="right">
					<table cellspacing="0" cellpadding="0" border="0">
					<tr>

					<td class="myArea">

					</td>
					<td valign="top" id="colorpicker" nowrap class="welcome" style="padding-right: 5px; border-left: 2px solid #ddd;">

					</td>
					</tr>
					</table>

					</td>
				</tr>
				<tr>

					<td colspan="4" align="right" valign="top" nowrap style="padding: 10px 10px 0 10px; font-size: 12px;">
						<br />
					</td>
				</tr>



				<tr>
				    <td colspan="4">
						<table cellspacing="0" cellpadding="0" border="0" width="100%">
							<tr>
							    <td class="subTabBar" colspan="3">
									<table width="100%" cellspacing="0" cellpadding="0" border="0" height="20">
										<tr>
										    <td id="subtabs" width="100%">


                                </table>
                            </td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                    </td>
                </tr>
        </table>

    <form method="post" action="/" name="logoutform" id="logoutform">

        <table cellpadding="0" cellspacing="0" border="0" align="center" style="margin-top:100px;">
            <tr>
            <td class="body"  style="padding-bottom: 10px;" ><strong>Bienvenido a</strong><br>
                <div align="center">
                    <img src="resources/images/logo.png" alt="<%= "ssTalk" %>" width="400" height="100"/>
                </div>
            </td>
            </tr>
            <tr>
            <td class="tabForm" align="center">

                <table cellpadding="0" cellspacing="2" border="0" align="center" width="100%">
                    <tr>
                        <td class="dataLabel" colspan="2" width="100%" style="font-size: 12px; padding-bottom: 5px; font-weight: normal;">
                            En estos momentos estamos actualizando la aplicaci&oacute;n.<br/>
                            En breves instantes volver&aacute; a estar disponible.
                            <br/>
                            <br/>
                        </td>
                    </tr>
                </table>



            </td>
            </tr>

        </table>

    </form>
<br>
<br>

<table cellpadding='0' cellspacing='0' width='100%' border='0' class='underFooter'>
    <tr>
        <td align='center' class='txt10_ver_ng_white'>
            Esta aplicaci&oacute;n ha sido optimizada para su uso con <a href="http://www.mozilla-europe.org/es/firefox/" target="_blank" class="copyRightLinkLogin">Mozilla Firefox</a>,<a href="https://www.google.com/intl/es/chrome/browser/" target="_blank" class="copyRightLinkLogin">Chrome</a>
            <br/><br/>&copy; 2010 - 2013
            <br/>Copyright de <a href="http://www.agss.es" target="_blank" class="copyRightLinkLogin">AGELEIA SECURITY SYSTEMS</a>
            <br/>Tfno: 662 181 608
        </td>
    </tr>
</table>

</body>
</html>