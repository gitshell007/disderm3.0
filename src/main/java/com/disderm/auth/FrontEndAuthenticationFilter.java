package com.disderm.auth;

import com.disderm.utils.Crypto;
import com.disderm.utils.SSLog;

import javax.annotation.Priority;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.util.*;
import java.util.stream.Collectors;


@Provider
@Priority(1000)
public class FrontEndAuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final SSLog ssLog = new SSLog();
    private String usernameReceivedDecoded = "";
    private String passwordReceivedDecoded = "";
    private String tokenReceivedDecoded = "";
    List<String> listaWhiteURLs = new ArrayList<>();
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletRequest sr;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        /*
        loadlistaWhiteURL();
        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {

            String path = requestContext.getUriInfo().getPath();

            String x = getRequestInfo(requestContext);
            System.out.println("NO AUTH: " + path);
            return;
        }

        //Get encoded username and password
        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String[] usernameAndPassword = null;

        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(encodedUserPassword);


        //If the decode fails in any case
        if (decodedBytes == null || decodedBytes.length == 0) {
            requestContext.abortWith(Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("SERVER ERROR")
                    .build());
            return;
        }
        usernameAndPassword = new String(decodedBytes).split(":", 2);
        if (usernameAndPassword.length > 0) {
            System.out.println("el token y password es " + usernameAndPassword[1]);
            usernameReceivedDecoded = usernameAndPassword[0];
            passwordReceivedDecoded = usernameAndPassword[1].split("<<<<>>>>")[0];
            tokenReceivedDecoded = usernameAndPassword[1].split("<<<<>>>>")[1];
            HashMap<String,String>  toPrint = new  HashMap<String,String>();
            //password = Crypto.encrypt(password,"SHA-512");
            toPrint.put("username", usernameReceivedDecoded);
            toPrint.put("password received", passwordReceivedDecoded);
            toPrint.put("password received2",    Arrays.toString(Base64.getDecoder().decode(passwordReceivedDecoded)));
           // byte[] decodedBytesPassword = DatatypeConverter.parseBase64Binary(passwordReceivedDecoded);
            //toPrint.put("password received", new String(decodedBytesPassword ));
            toPrint.put("token", tokenReceivedDecoded);



            // Print the decoded array



            // Print the decoded string
            Varios.printMapInfo(toPrint);
        } else {
            System.out.println("lka cabecera de auth no venia muy alla" + new String(decodedBytes));
        }
        String path = requestContext.getUriInfo().getPath();


        if (Varios.checkStringItem(listaWhiteURLs, path)) {
            // MAINTENANCE COMMAND
            System.out.println("ACCESO PERMITIDO: " + path);
        } else if ((requestContext.getHeaderString("User-Agent").startsWith("FEELFARMA")) || SSLog.DevENABLED()) {
            boolean isUsuarioValido = esUsuarioValidoUserPass(usernameReceivedDecoded, passwordReceivedDecoded, sr.getRemoteAddr() + " / " + requestContext.getHeaderString("host"));
            if (!isUsuarioValido) {
                requestContext.abortWith(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("User cannot access the resource. NOT VALID")
                        .build());
                System.out.println("LA RUTA QUE NO ES CAPAZ DE DESCIFRAR PARA USER PERSONALIZADO ES: " + path + " - " + usernameAndPassword[0] + " y pass: " + usernameAndPassword[1]);
            }
        }
        */

    }

    public boolean esUsuarioValido(String usuario, String clave) {
        //System.out.println("holaa: \r\n" + usuario + " \r\n " + clave);
        return (usuario.equalsIgnoreCase(Crypto.encrypt("ssadmin", "SHA-256")) && clave.equalsIgnoreCase(Crypto.encrypt("macrossmipass0000000000", "SHA-256")));

    }

    public boolean esUsuarioValidoUserPass(String _username, String _password, String host) {

        boolean aDevolver = false;
        Heimdall heimdall = new Heimdall();

        aDevolver = heimdall.checkUserFromApp(_username,_password).get_success();
        // METODO NUEVO BLOQUEA ACCESO A API SI EL USER PASS ESTA MAL IGUAL QUE SI ESTUVIESE EN LOGIN
        //System.out.println("USER: " + usuario + " / " + clave);
     /*   java.sql.Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {

            conn = new api.macross.autorizacion.Connection().getConnection();
            if (conn != null) {
                String sql = "SELECT id, pwd, enabled, "
                        + " attempts_allowed, attempts_used "
                        + "FROM talk_users "
                        + "WHERE sha2(concat(username,'@iacom'),256) = ? and pwd = ? ";

                statement = conn.prepareStatement(sql);
                statement.setString(1, usuario);
                statement.setString(2, clave);
                rs = statement.executeQuery();
                if (rs.next()) {
                    int attempts_allowed = rs.getInt("attempts_allowed");
                    int attempts_used = rs.getInt("attempts_used");
                    if (attempts_allowed <= attempts_used) {
                        UserManager userManager = new UserManager();
                        userManager.setEnabled(rs.getLong("id"), 0);
                        SystemMail systemMail = new SystemMail();
                        systemMail.sendAlertEmailAttempts(usuario);

                        // LOG -check-ID DISPOSITIVO- -check ip-
                        SystemLogs.saveLogAction(16, usuario, 0, "USER FROM API", "", host, "");
                        // END LOG


                    }
                    else if (rs.getInt("enabled") != 1) {
                        //System.out.println("PROBLEMA ENABLED");
                        SystemMail systemMail = new SystemMail();
                        systemMail.sendAlertEmailEnabled(usuario);
                        SystemLogs.saveLogAction(19, usuario, 0, "USER FROM API","", host, "");


                    }
                    else
                    {
                        aDevolver= true;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("ESUSUARIOVALIDO_PROBLEMA CATCH " + ex.getMessage());
            SystemLogs.saveLogAction(20, usuario, 0, "USER FROM API", "", "127.0.0.1", "");
            return false;
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException logOrIgnore) {
            }
            if (statement != null) try {
                statement.close();
            } catch (SQLException logOrIgnore) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException logOrIgnore) {
            }
        }*/
        return aDevolver;


    }

    private void loadlistaWhiteURL() {
        listaWhiteURLs.add("/assets");
        listaWhiteURLs.add("/auth");
        listaWhiteURLs.add("/login.jsp");
    }


    private String getRequestInfo(ContainerRequestContext request) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nRequest Method = [" + request.getRequest().getMethod() + "], ");
        sb.append("\nRequest URL Path = [" + request.getUriInfo().getPath() + "], ");

        MultivaluedMap<String, String> params = request.getUriInfo().getPathParameters();
        MultivaluedMap<String, String> rh = request.getHeaders();
        sb.append(rh.entrySet()
                .stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining("\n" +
                        "HEADER:")));

        sb.append(params.entrySet()
                .stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining("\r\nPARAM:")));
        System.out.println(sb.toString());
        return sb.toString();
    }

}

        /*Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println(path + " - " + sdf.format(cal.getTime()) +" - " + sr.getRemoteAddr() + " / " + requestContext.getHeaderString("host") + "-" + requestContext.getHeaderString("User-Agent") + "-User: " + usernameAndPassword[0]);
        if(requestContext.getHeaderString("host").startsWith("10.0.1.") && requestContext.getHeaderString("User-Agent").startsWith("macross/1.0a (iP") && (requestContext.getHeaderString("User-Agent").contains("iOS 8.3") || requestContext.getHeaderString("User-Agent").contains("iOS 8.4") || requestContext.getHeaderString("User-Agent").contains("iOS 9") || requestContext.getHeaderString("User-Agent").contains("Roboto x2124"))    || SSLog.DevENABLED() ) {
        */

    /*requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource2.")
                    .build());*/
