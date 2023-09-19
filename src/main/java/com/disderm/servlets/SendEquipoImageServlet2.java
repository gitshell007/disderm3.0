package com.disderm.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.*;

public class SendEquipoImageServlet2 implements Servlet {


    private static final long serialVersionUID = 1L;

    public SendEquipoImageServlet2() {
        super();
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter out = servletResponse.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hello, world!</h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

/*public class SendEquipoImageServlet2 extends HttpServlet {

    public SendEquipoImageServlet2() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String control = "pepepepe";

        String pathInfo = request.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");
        String part1 = pathParts[1]; // {value}
        String[] pathPartsData = part1.split("separador");
        String name = pathPartsData[1]; // {value}
        String nhis = pathPartsData[0]; // {value}


        ServletContext sc = getServletContext();
        String path = Varios.getEcoFolder();

        File fichero = new File(path + "/" + nhis + "/" + name);


        int c = 0;
        //System.out.println("longitud eco video : " + length);
        OutputStream os = response.getOutputStream();
        if (name.endsWith(".mp4")) {
            try {
                MultipartFileSender.fromFile(fichero).with(request).with(response).serveResource();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else
        {
            ServletOutputStream out = response.getOutputStream();
            InputStream is = new FileInputStream(path + "/" + nhis + "/" + name);
            int length = is.available();
            response.setContentType("image/png");
            response.setContentLength(length);
            byte [] buf = new byte[4096];
            int read;
            while((read = is.read(buf)) != -1)
            {
                out.write(buf, 0, read);
            }
            is.close();
            out.flush();
            out.close();
        }
    }

} */