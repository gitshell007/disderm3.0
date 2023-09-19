package com.disderm.conn;

import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private java.sql.Connection connection;
    public static String MAINDB = "disderm";

    private Properties properties;

    public DBConnection() throws Exception {
        try {
            javax.naming.Context initContext = new javax.naming.InitialContext();
            javax.naming.Context envContext = (javax.naming.Context) initContext.lookup("java:/comp/env");
            javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup("jdbc/" + MAINDB);
            this.connection = ds.getConnection();
        } catch (Exception e) {
            throw new Exception("Connection.java Error en la conexin a la Base de Datos porque el Bean es nulo o ha expirado: " + e.getMessage());
        }
    }

    public DBConnection(String nombrebbdd) throws Exception {
        try {
            javax.naming.Context initContext = new javax.naming.InitialContext();
            javax.naming.Context envContext = (javax.naming.Context) initContext.lookup("java:/comp/env");
            javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup("jdbc/" + nombrebbdd);
            this.connection = ds.getConnection();

        } catch (Exception e) {
            throw new Exception("Connection.java Error en la conexion a la Base de Datos porque el Bean es nulo o ha expirado: " + e.getMessage());
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
