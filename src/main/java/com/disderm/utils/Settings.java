package com.disderm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Settings {
    /* IMPORTANTE DEFINIR EL HOST DONDE SE ESTA EJECUTANDO
    1 = locahost
    2 = desarrollo
    3 = produccion
     */
    public static int runningHost = 2;

    public static String contentRootJavaScript = "/backoffice/assets_intranet/js/";
    public static String contentRootServices = "/backoffice/services/";
    public static String contentRootModules = "/backoffice/modulos/";
    public static String contentRoot = "/backoffice/";

    //Metodo para formatear la fecha
    public static SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
    public static String getFechaDDMMAAAA(Date date){
        String toReturn = "";
        try
        {
            toReturn = sdf_ddMMyyyy.format(date);
            System.out.println("getFechaString" + toReturn);
        }
        catch (Exception ex) {
            System.out.println("getFechaString" + ex.getMessage());

        }
        return toReturn;
    }
}
