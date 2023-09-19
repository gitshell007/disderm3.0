package com.disderm.servlets;

import com.disderm.conn.DBConnection;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "SendEquipoImageServlet", urlPatterns = {"/fotoequipo/*"})
public class SendEquipoImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String pathInfo = request.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");
        String part1 = pathParts[1]; // {value}
        /**********************/
        String campoFoto = "";
        //Abrimos conexión con BBDD
        try {
            Connection conn = new DBConnection(DBConnection.MAINDB).getConnection();
            PreparedStatement ps;
            ResultSet rs;
            //recuperamos el id del equipo seleccionado
            int key = Integer.parseInt(part1);

            // MAL USA SIEMPRE UN PARAMETRO PARA EL VALOR NO UN JOIN CON EL + PORQUE TE DA UN SQL INJECTION
            String sql = "SELECT * FROM equipos WHERE equipos_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, key);
            rs = ps.executeQuery();
            if (rs.next()) {
                campoFoto = rs.getString("foto");
            }
            String imageBase64 = campoFoto;
            OutputStream out = response.getOutputStream();
            writeOutputStream(imageBase64, out);

            response.setContentType("image/png");
            response.setHeader("Pragma", "");
            response.setHeader("Cache-Control", "");
            response.setHeader("Content-Disposition", "inline; fileName=image.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void writeOutputStream(String value, OutputStream outputStream) throws IOException {

        byte[] imgBytes = DatatypeConverter.parseBase64Binary(value);
        BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imgBytes));
        ImageIO.write(bufImg, "png", outputStream);
    }

}
