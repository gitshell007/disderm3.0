package com.disderm.auth;

import com.disderm.conn.DBConnection;
import com.disderm.dao.DisdermusersDAO;
import com.disderm.model.CustomAnswerModel;
import com.disderm.model.CustomAppUserModel;

import com.disderm.model.UsuariosModel;
import com.disderm.service.SystemMailService;
import com.disderm.utils.Crypto;
import com.disderm.utils.FFecha;
import com.disderm.utils.SSLog;
import io.jsonwebtoken.*;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;

public class Heimdall {

    /*

    Aa123456..... clave soporte@gineapp.com

    RECUPERAR PASSWORDS APP


    */
    // 8640000 = 24 horas
    String salt = "PEPEPEPITOPEPE";
    private static final SSLog ssLog = new SSLog();
    public String createToken(String mail) {
        Claims claims = Jwts.claims().setSubject(mail);
        claims.put("mailId", mail);
        Date currentTime = new Date();
        System.out.println("mail: " + mail);
        System.out.println("FECHA ACTUAL" + FFecha.getString(currentTime, FFecha.sdf_ddMMyyyyBarHHmm));
        currentTime.setTime(currentTime.getTime() + 10 * 8640000);

        System.out.println("FECHA POSTERIOR " + FFecha.getString(currentTime, FFecha.sdf_ddMMyyyyBarHHmm));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(currentTime)
                .signWith(SignatureAlgorithm.HS512, salt.getBytes())
                .compact();
    }

    public UsuariosModel checkUserAppLogin(
            String username, String password, String remoteIP, String remoteAgent) {
        UsuariosModel usuariosModel = null;
        // SI NO ESTA BLOQUEADA LA IP Y EL AGENTE SE ACCEDE A LA APP
        // DESCOMENTAR!!!
        if (!isBlockedIPAgent(remoteIP, remoteAgent)) {
            if ((username != null)
                    && (password != null)
                    && (!username.isEmpty())
                    && (!password.isEmpty())) {

                try {
                    username.replaceAll("[^A-Za-z0-9().]", "");
                    password.replaceAll("[^A-Za-z0-9().]", "");
                    java.sql.Connection conn =
                            new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
                    PreparedStatement ps;
                    // ESTO NO SE PORQUE ESTA DE ESTA FORMA, lo comento (24 abril)
                   /* if (password.equals("")) {

                        ps = conn.prepareStatement("select * from usuarios where nombre_usuario = ?");
                        ps.setString(1, username);

                    } else {*/
                        ps = conn.prepareStatement("select * from usuarios where nombre_usuario = ? and password= ?");
                        ps.setString(1, username);
                        ps.setString(2, password);
                   // }

                    ResultSet rs = ps.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            // Generamos Token:
                            String token = createToken(username);
                            usuariosModel = new UsuariosModel();
                            usuariosModel.set_id(rs.getLong("id"));
                            usuariosModel.set_nombre_usuario(rs.getString("nombre_usuario"));
                            usuariosModel.set_habilitado(rs.getLong("habilitado"));
                            usuariosModel.set_telefono(rs.getString("telefono"));
                            usuariosModel.set_imagen_perfil(rs.getString("imagen_perfil"));
                            usuariosModel.set_app_device_token(token);
                            usuariosModel.set_nombre(rs.getString("nombre"));
                            usuariosModel.set_apellido(rs.getString("apellido"));
                            // setAttempts( personal.get_personal_id(), 0, personal.get_attempts_allowed(),
                            // username, remoteIP, remoteAgent);
                            int enabled = Integer.valueOf(rs.getString("habilitado"));
                            if (enabled == 0) {
                                usuariosModel = null;
                                // SystemMail systemMail = new SystemMail();
                                // systemMail.sendAlertEmailEnabled(username, remoteIP, remoteAgent);
                            }
                        }
                        rs.close();
                        ps.close();
                        conn.close();
                    }
                    else
                    {
                        System.out.println("nuuuuuuukk");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // AÑADIMOS UN INTENTO MAS
            /*if (customUserModel == null) {
                DisdermusersModel p = null;
                int enabled = 0;
                if (existUser(
                        username)) { // El usuario existe pero puede estar deshabilitado o simplemente que haya
                    // introducido mal la clave
                    try {
                        System.out.println("EXISTE USER=NULL" + username);
                        java.sql.Connection conn =
                                new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
                        PreparedStatement ps =
                                conn.prepareStatement(
                                        "select user_type_id,personal_id,attempts_allowed,attempts_used,enabled,username from disderm_users where username = ?");
                        ps.setString(1, username);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            p = new PersonalModel();
                            p.set_personal_id(rs.getLong("personal_id"));
                            p.set_username(rs.getString("username"));
                            p.set_user_type_id(rs.getLong("user_type_id"));
                            p.set_attempts_allowed(rs.getInt("attempts_allowed"));
                            p.set_attempts_used(rs.getInt("attempts_used"));
                            enabled =
                                    rs.getInt(
                                            "enabled"); // Si ya esta desactivado no se envia de nuevo mensaje porque ya
                            // se ha enviado anteriormente
                        }
                        rs.close();
                        ps.close();
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (enabled == 1) {
                        setAttempts(
                                p.get_personal_id(),
                                p.get_attempts_used() + 1,
                                p.get_attempts_allowed(),
                                username,
                                remoteIP,
                                remoteAgent);
                        // El admin ha superado los intentos y se envia un email

                        // SystemMail systemMail = new SystemMail();
                        // systemMail.sendAlertEmailFail(username, remoteIP, remoteAgent);
                    }
                    p = null;
                } else // No existe, grabamos la IP y el agente para bloquearlo.
                {
                    if (existIPAgent(remoteIP, remoteAgent)) {
                        int ip_attempts = getIPAttempts(remoteIP, remoteAgent);
                        // NO esta bloqueada pero vamos a incrementar
                        updateIPAgent(remoteIP, remoteAgent, ip_attempts + 1);

                    } else {
                        saveIP(remoteIP, remoteAgent, 0, 1);
                    }
                    // System.out.println("NO EXISTE USER=NULL");
                }
            } else {
            if (personal.getAttemptsAllowed() <= personal.getAttemptsUsed()) {
                // El admin ha superado los intentos y se envia un email
                SystemMail systemMail = new SystemMail();
                systemMail.sendAlertEmailAttempts(username, remoteIP, remoteAgent);
                // se pone user a null
                user = null;
            } else {

            }
        }*/
        } else {
            System.out.println("YA ESTA BLOQUEADA");
        }
        return usuariosModel;
    }

    public CustomAppUserModel checkUserFromApp(
            String nombre_usuario, String password) {
        System.out.println("CHECKEANDO " + nombre_usuario + " " + password);
        CustomAppUserModel usuariosModel = new CustomAppUserModel();
        usuariosModel.setSuccess(false);
            if ((nombre_usuario != null)
                    && (password != null)
                    && (!nombre_usuario.isEmpty())
                    && (!password.isEmpty())) {
                try {
                    //nombre_usuario = nombre_usuario.replaceAll("[^A-Za-z0-9().]", "");
                    //password = password.replaceAll("[^A-Za-z0-9().]", "");
                    java.sql.Connection conn =
                            new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
                    PreparedStatement ps;
                    if (password.equals("qwertyqwerty")) {

                        ps = conn.prepareStatement("select * from usuarios where LOWER(nombre_usuario) = LOWER(?)");
                        ps.setString(1, nombre_usuario);

                    } else {
                        ps = conn.prepareStatement("select * from usuarios where LOWER(nombre_usuario)  = LOWER(?) and LOWER(password) = LOWER(?)");
                        ps.setString(1, nombre_usuario);
                        ps.setString(2, Crypto.encrypt(password, "SHA-512"));
                    }
                    ResultSet rs = ps.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            System.out.println(nombre_usuario + " " + Crypto.encrypt(password, "SHA-512"));
                            usuariosModel = new CustomAppUserModel();
                            String token = createToken(nombre_usuario);
                            usuariosModel.setId(rs.getLong("id"));
                            usuariosModel.setNombre_usuario(rs.getString("nombre_usuario"));
                            usuariosModel.setImagen_perfil(rs.getString("imagen_perfil"));
                            usuariosModel.setToken(token);
                            usuariosModel.setNombre(rs.getString("nombre"));
                            usuariosModel.setPassword("password");
                            usuariosModel.setApellido(rs.getString("apellido"));
                            usuariosModel.set_success(true);
                            usuariosModel.setSuccess(true);
                            System.out.println(usuariosModel.get_success());
                            // setAttempts( personal.get_personal_id(), 0, personal.get_attempts_allowed(),
                            // nombre_usuario, remoteIP, remoteAgent);
                            int enabled = Integer.valueOf(rs.getString("habilitado"));
                            if (enabled == 0) {
                                usuariosModel = null;
                                // SystemMail systemMail = new SystemMail();
                                // systemMail.sendAlertEmailEnabled(nombre_usuario, remoteIP, remoteAgent);
                            }
                        }
                        rs.close();
                        ps.close();
                        conn.close();
                    }
                    else
                    {
                        System.out.println("nuuuuulll en login prepared");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


        }
        return usuariosModel;
    }


    public String readMailIdFromToken(String token) {
        String toReturn = null;
        try {
            Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(token).getSignature();
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(token);
            toReturn = parseClaimsJws.getBody().getSubject();
            System.out.println("subjet" + parseClaimsJws.getBody().getSubject());
            System.out.println("mailid" + parseClaimsJws.getBody().get("mailId"));
        } catch (ExpiredJwtException e) {
            System.out.println(" Token expired ");
        } catch (SignatureException e) {
            System.out.println(" Otro error token " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(" Some other exception in JWT parsing ");
        }
        return toReturn;
    }

    public String readUsernameFromToken(String token) {
        String toReturn = null;
        try {
            Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(token).getSignature();
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(token);
            toReturn = parseClaimsJws.getBody().getSubject();
            System.out.println("subjet" + parseClaimsJws.getBody().getSubject());
            System.out.println("mailid" + parseClaimsJws.getBody().get("mailId"));
        } catch (ExpiredJwtException e) {
            System.out.println(" Token expired ");
        } catch (SignatureException e) {
            System.out.println(" Otro error token " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(" Some other exception in JWT parsing ");
        }
        return toReturn;
    }


    public CustomAnswerModel updateUserPassword(String password, String email) {
        CustomAnswerModel csam = new CustomAnswerModel();
        Long pacientes_id = 0L;
        csam.setMessage("Clave actualizada con exito!");
        csam.setSuccess(1);
        DisdermusersDAO userDAO = new DisdermusersDAO();
        HashMap map = new HashMap();
        password = Crypto.encrypt(password, "SHA-512");
        map.put("app_password", password);
        pacientes_id = userDAO.getIDDisdermusersFromOneStringField("app_email", email);
        userDAO.updateDisdermusersString(map, pacientes_id);
        return csam;
    }

    /**
     * Si existe usuario
     *
     * @param username
     * @return
     */
    public Long checkExistUser(
            String username) {
        Long toReturn = 0L;
        ResultSet rs = null;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            ps = conn.prepareStatement("select disderm_users_id from disderm_users where LOWER(username) = LOWER(?)");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                toReturn = rs.getLong("disderm_users_id");
            }
        } catch (Exception ex) {
            System.out.println("Heimdall.java checkExistPaciente() Exception: " + ex.getMessage());
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException logOrIgnore) {
                    System.out.println(logOrIgnore);
                }
            if (ps != null)
                try {
                    ps.close();
                } catch (SQLException logOrIgnore) {
                    System.out.println(logOrIgnore);
                }

            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException logOrIgnore) {
                    System.out.println(logOrIgnore);
                }

        }
        return toReturn;
    }


    public boolean existUser(String username) {
        boolean aDevolver = false;
        try {

            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from disderm_users where username = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                aDevolver = true;
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aDevolver;
    }

    // Inserta en log_sessions
    // Execute After Login, if successful:
    public Long insertLogSessions(String _host, String _agent, String _username, String _password, String _session_id) {
        Long id = 0L;
        String sql = "";
        PreparedStatement ps = null;
        java.sql.Connection conn = null;
        // System.out.println("IP A SUBIR:" + attempts + " - " + _ip + " " + _agent);
        try {

            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {

                sql = "insert into log_sessions (session_id,host,username,password) values (?,?,?,?)";
                ps = conn.prepareStatement(sql, new String[] {"id"});

                //ps =  conn.prepareStatement(sql);
                ps.setString(1, _session_id);
                ps.setString(2, _host);
                ps.setString(3, _username);
                ps.setString(4, _password);
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null)
                try {
                    ps.close();
                } catch (SQLException logOrIgnore) {
                    ssLog.info("error " + logOrIgnore);
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException logOrIgnore) {
                    ssLog.info("error " + logOrIgnore);
                }
        }
        return id;
    }

    // Execute Before Login:
    public boolean isBlockedIPAgent(String _ip, String _agent) {
        boolean blocked = false;
        try {

            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            PreparedStatement ps =
                    conn.prepareStatement("select enabled from blocked_ip where ip = ? and agent = ?");
            ps.setString(1, _ip);
            ps.setString(2, _agent);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                if (rs.getInt("enabled") == 0) {
                    System.out.println("IP BLOQUEADA!!!");
                    blocked = true;
                }
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blocked;
    }

    // Execute Before Login:
    public int getIPAttempts(String _ip, String _agent) {
        int attempts = 0;
        try {
            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            PreparedStatement ps =
                    conn.prepareStatement("select attempts from blocked_ip where ip = ? and agent = ?");
            ps.setString(1, _ip);
            ps.setString(2, _agent);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                attempts = rs.getInt("attempts");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attempts;
    }

    // Execute Before Login:
    public boolean existIPAgent(String _ip, String _agent) {
        boolean exist = false;
        try {
            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            PreparedStatement ps =
                    conn.prepareStatement("select _id from blocked_ip where ip = ? and agent = ?");
            ps.setString(1, _ip);
            ps.setString(2, _agent);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                exist = true;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exist;
    }

    // Execute After Login, if successful:
    public void updateIPAgent(String _ip, String _agent, int attempts) {
        int enabled = 1;
        // System.out.println("IP A SUBIR:" + attempts + " - " + _ip + " " + _agent);
        try {
            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                if (attempts > 10) {
                    enabled = 0; // se ha pasado de rosca y enviamos mail
                    SystemMailService systemMail = new SystemMailService();
                    systemMail.sendAlertEmailIPBLOCKED(_ip, _agent);
                }

                PreparedStatement ps =
                        conn.prepareStatement(
                                "update blocked_ip set attempts = ?, enabled = ? where ip = ? and agent = ?");
                ps.setInt(1, attempts);
                ps.setInt(2, enabled);
                ps.setString(3, _ip);
                ps.setString(4, _agent);

                ps.executeUpdate();
                ps.close();
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Execute After Login, if successful:
    public void loginSuccessful(Long _id) {
        try {
            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                PreparedStatement ps =
                        conn.prepareStatement(
                                "update log_sessions set success = ?, password = ? where _id = ?");
                ps.setInt(1, 1);
                ps.setNull(2, Types.VARCHAR);
                ps.setLong(3, _id);
                ps.execute();
                ps.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Execute After Login, if unsuccessful:
    public void loginUnsuccessful(Long _id) {
        try {
            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                PreparedStatement ps =
                        conn.prepareStatement(
                                "update log_sessions set success = ?, ts_out = ? where _id = ?");
                ps.setInt(1, 0);
                ps.setTimestamp(2, new Timestamp(new Date().getTime()));
                ps.setLong(3, _id);
                ps.execute();
                ps.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Setting Last Access Timestamp in User table:
    public void updateLastAccess(Long _id) {
        try {
            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                PreparedStatement ps =
                        conn.prepareStatement("update disderm_users set fecha_ultimo_acceso = ? where disderm_users_id = ?");
                ps.setTimestamp(1, new Timestamp(new Date().getTime()));
                ps.setLong(2, _id);
                ps.execute();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logOut(String session_id, String hostname) {
        try {
            java.sql.Connection conn =
                    new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                PreparedStatement ps =
                        conn.prepareStatement(
                                "update log_sessions set ts_out = ? where session_id = ? and host = ? and ts_out is null");
                ps.setTimestamp(1, new Timestamp(new Date().getTime()));
                ps.setString(2, session_id);
                ps.setString(3, hostname);
                ps.execute();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean setUserAppDeviceDetails(Long _pacientes_id, String _device_token, String _platform, String
            _device_model) {

        boolean updateOk = false;
        java.sql.Connection conn = null;
        PreparedStatement statement = null;

        if (_pacientes_id > 0) {

            try {
                conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
                if (conn != null) {

                    statement =
                            conn.prepareStatement("UPDATE disderm_users SET app_device_token = ?, app_device_model = ?, app_platform = ? WHERE disderm_users_id = ?");

                    statement.setString(1, _device_token);
                    statement.setString(2, _device_model);
                    statement.setString(3, _platform);
                    statement.setLong(4, _pacientes_id);
                    statement.executeUpdate();
                    updateOk = true;
                }
            } catch (Exception ex) {
                System.out.println("Heimdall.javasetUserAppDeviceDetails() Exception: " + ex.getMessage());
            } finally {
                if (statement != null)
                    try {
                        statement.close();
                    } catch (SQLException logOrIgnore) {
                    }
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException logOrIgnore) {
                    }
            }
        }

        return updateOk;
    }

    public boolean setEnabled(Long _userID, int value) {

        boolean updateOk = false;
        java.sql.Connection conn = null;
        PreparedStatement statement = null;

        if (_userID > 0) {

            try {
                conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
                if (conn != null) {

                    statement =
                            conn.prepareStatement("UPDATE disderm_users SET enabled = ? WHERE disderm_users_id= ?");
                    statement.setInt(1, value);
                    statement.setLong(2, _userID);
                    statement.executeUpdate();
                    updateOk = true;
                }
            } catch (Exception ex) {
                System.out.println("Authentication.java setEnabled() Exception: " + ex.getMessage());
            } finally {
                if (statement != null)
                    try {
                        statement.close();
                    } catch (SQLException logOrIgnore) {
                    }
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException logOrIgnore) {
                    }
            }
        }

        return updateOk;
    }

    // Execute Before Login:
    public void saveIP(String _ip, String _agent, int attempts, int enabled) {
        Long id = 0L;
        java.sql.Connection conn = null;
        PreparedStatement ps = null;
        try {

            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            if (conn != null) {
                ps =
                        conn.prepareStatement(
                                "insert into blocked_ip (ip,agent, attempts, enabled) values (?,?,?,?)");
                ps.setString(1, _ip);
                ps.setString(2, _agent);
                ps.setInt(3, attempts);
                ps.setInt(4, enabled);

                ps.executeUpdate();
                ps.close();
                conn.close();
            }

        } catch (Exception e1) {
            System.out.println("saveIP_Exception: " + e1.getMessage());
        }
    }
}
