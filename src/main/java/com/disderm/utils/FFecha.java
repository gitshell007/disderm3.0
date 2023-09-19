package com.disderm.utils;


import java.util.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class FFecha {

    // En el 'locale' por defecto:
    public static SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat sdf_ddMMyyyyBar = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat sdf_MMddyyyyBar = new SimpleDateFormat("MM/dd/yyyy");
    public static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdf_ddMMyy = new SimpleDateFormat("dd/MM/yy");
    public static SimpleDateFormat sdf_EEE = new SimpleDateFormat("EEE");
    public static SimpleDateFormat sdf_MMM = new SimpleDateFormat("MMM");
    public static SimpleDateFormat sdf_yyyy = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat sdf_yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat sdf_ddMMyyyyHHmmss = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static SimpleDateFormat sdf_ddMMyyyyBarHHmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static SimpleDateFormat sdf_ddMMyyyyBarHHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static SimpleDateFormat sdf_ddMMyyyyGuionHHmm = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public static SimpleDateFormat sdf_long = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.S");
    public static SimpleDateFormat sdf_hhmm = new SimpleDateFormat("hh:mm");
    public static SimpleDateFormat sdf_HHmm = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat sdf_HHmmss = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat sdf_ddMMyyyyHHmmssFile = new SimpleDateFormat("ddMMyyyy_HHmmss");
    public static SimpleDateFormat sdf_ddMMyyyyFile = new SimpleDateFormat("ddMMyyyy");
    public static SimpleDateFormat sdf_yyyyMMddFile = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat sdf_dd = new SimpleDateFormat("dd");
    public static SimpleDateFormat sdf_EEEEE = new SimpleDateFormat("EEEEE");
    public static SimpleDateFormat sdf_MMMMM = new SimpleDateFormat("MMMMM");
    public static SimpleDateFormat sdf_ddMMyyBarHHmmss = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    // En ingles:
    public static SimpleDateFormat sdf_en_MMMMM = new SimpleDateFormat("MMMMM", Locale.ENGLISH);

    public static java.sql.Date convertir (java.util.Date fecha) {
        if (fecha != null)
            return new java.sql.Date(fecha.getTime());
        else
            return null;
    }

    public static String getString (Date fecha, SimpleDateFormat sdf,String _fuction) {
        try {
            return sdf.format(fecha);
        } catch (Exception e) {
            System.out.println ("FFecha.getString: " + e.getMessage() + " valor = " + fecha + " funcion " + _fuction);
            return null;
        }
    }

    public static String getString (Date fecha, SimpleDateFormat sdf) {
        try {
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(fecha);
        } catch (Exception e) {
            System.out.println ("FFecha.getString: " + e.getMessage() + " valor = " + fecha + " funcion " );
            return null;
        }
    }


    public static Date getDate (String fecha, SimpleDateFormat sdf) {
        try {

            return sdf.parse(fecha);
        } catch (Exception e) {
            System.out.println ("Exception: " + e.getMessage());
            return null;
        }
    }

    public String getHoyDDMMAAAA () {
        String fechad;
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        int dia = cal.get(java.util.Calendar.DAY_OF_MONTH);
        int mes = cal.get(java.util.Calendar.MONTH) + 1;
        String smes;
        String sdia;
        if (dia < 10)
        {
            sdia = "0" + dia;
        }
        else
        {
            sdia = "" + dia;
        }
        if (mes < 10)
        {
            smes = "0" + mes;
        }
        else
        {
            smes = "" + mes;
        }
        fechad = sdia + "-" + smes + "-" + cal.get(java.util.Calendar.YEAR);
        return fechad;
    }

    public static String getHoyDDMMAAAAHHMM () {
        String fechad;
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        int dia = cal.get(java.util.Calendar.DAY_OF_MONTH);
        int mes = cal.get(java.util.Calendar.MONTH) + 1;
        int hora = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int minutos = cal.get(java.util.Calendar.MINUTE);
        String smes;
        String sdia;
        if (dia < 10)
        {
            sdia = "0" + dia;
        }
        else
        {
            sdia = "" + dia;
        }
        if (mes < 10)
        {
            smes = "0" + mes;
        }
        else
        {
            smes = "" + mes;
        }

        String h = Integer.toString(hora);
        String m = Integer.toString(minutos);
        if (hora < 10) {
            h = "0" + hora;
        }
        if (minutos < 10) {
            m = "0" + minutos;
        }
        fechad = sdia + "-" + smes + "-" + cal.get(java.util.Calendar.YEAR) + " / " + h + ":" + m;
        return fechad;
    }


    public String getHoyAAAAMMDD () {
        String fechad;
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        int dia = cal.get(java.util.Calendar.DAY_OF_MONTH);
        int mes = cal.get(java.util.Calendar.MONTH) + 1;
        String smes;
        String sdia;
        if (dia < 10)
        {
            sdia = "0" + dia;
        }
        else
        {
            sdia = "" + dia;
        }
        if (mes < 10)
        {
            smes = "0" + mes;
        }
        else
        {
            smes = "" + mes;
        }
        fechad = cal.get(java.util.Calendar.YEAR) + "-" + smes + "-" + sdia;
        return fechad;
    }

    public Date getHoy2Date () {
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        return new Date(cal.getTimeInMillis());
    }

    public java.sql.Date getHoy2SqlDate () {
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public String getAhoraHHMM () {
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        int hora = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int minutos = cal.get(java.util.Calendar.MINUTE);
        String h = Integer.toString(hora);
        String m = Integer.toString(minutos);
        if (hora < 10) {
            h = "0" + hora;
        }
        if (minutos < 10) {
            m = "0" + minutos;
        }
        return h + ":" + m;
    }

    public Time getAhoraHHMM2Time () {
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        return new Time(cal.getTimeInMillis());
    }

    public String getDigitoAnyoActual () {
        String fechad;
        java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.US);
        int anyo = cal.get(java.util.Calendar.YEAR);
        fechad = Integer.toString(anyo);
        return fechad.substring(2);
    }

    public int[] getInts (String _fecha) {
        int fec[] = new int[3];
        fec[0] = 0;
        fec[1] = 0;
        fec[2] = 0;

        String temp[];
        if (_fecha.indexOf("-") != -1) {
            temp = _fecha.split("-");
        } else if (_fecha.indexOf("/") != -1) {
            temp = _fecha.split("/");
        } else {
            temp = null;
        }

        if (temp != null) {

            if (temp[2].length() > 4)
                temp[2] = temp[2].substring(0, temp[2].indexOf(" "));

            if (temp[2].length() == 2) {
                fec[0] = Integer.parseInt(temp[2]);
                fec[2] = Integer.parseInt(temp[0]);
            } else if (temp[0].length() == 2) {
                fec[2] = Integer.parseInt(temp[2]);
                fec[0] = Integer.parseInt(temp[0]);
            } else {
                fec = null;
            }

            fec[1] = Integer.parseInt(temp[1]);

        } else {
            fec = null;
        }

        return fec;
    }

    public int[] getIntsHM (String _fecha) {
        int fec[] = new int[2];
        fec[0] = 0;
        fec[1] = 0;

        String temp[];
        if (_fecha.indexOf(":") != -1) {
            temp = _fecha.split(":");
        } else {
            temp = null;
        }

        if (temp != null) {
            if(temp.length == 2){
                fec[0] = Integer.parseInt(temp[0]);
                fec[1] = Integer.parseInt(temp[1]);
            }else{
                System.out.println("FFecha No ha obtenido DOS PARAMETROS!!!!!!!!!!!!!!!");
            }
        } else {
            fec = null;
        }

        return fec;
    }

    // Comparar Fechas:
    // Verdadero si _fecha1 es mayor o igual que _fecha2.
    public boolean esMayorOIgual (String _fecha1, String _fecha2) {
        String temp1[];
        String temp2[];
        if (_fecha1.indexOf("-") != -1) {
            temp1 = _fecha1.split("-");
        } else if (_fecha1.indexOf("/") != -1) {
            temp1 = _fecha1.split("/");
        } else {
            temp1 = null;
        }
        if (_fecha2.indexOf("-") != -1) {
            temp2 = _fecha2.split("-");
        } else if (_fecha2.indexOf("/") != -1) {
            temp2 = _fecha2.split("/");
        } else {
            temp2 = null;
        }

        Date d1;
        Date d2;
        Calendar c1;
        Calendar c2;
        if ((temp1 != null) && (temp2 != null)) {
            c1 = new GregorianCalendar(Integer.parseInt(temp1[2]), Integer.parseInt(temp1[1]), Integer.parseInt(temp1[0]));
            c2 = new GregorianCalendar(Integer.parseInt(temp2[2]), Integer.parseInt(temp2[1]), Integer.parseInt(temp2[0]));
            d1 = new Date(c1.getTimeInMillis());
            d2 = new Date(c2.getTimeInMillis());
            return d1.after(d2) || !d1.before(d2);
        }

        return false;
    }

    // Calendario:
    public int getDiaActual () {
        int n;
        Calendar calend = new GregorianCalendar();
        Date f = new Date ();
        calend.setTime(f);

        n = calend.get(Calendar.DAY_OF_MONTH);
        //n++;
        return n;
    }

    public int getMesActual () {
        int n;
        Calendar calend = new GregorianCalendar ();
        Date f = new Date ();
        calend.setTime(f);

        n = calend.get(Calendar.MONTH);
        n++;
        return n;
    }

    public int getAnyoActual () {
        int n;
        Calendar calend = new GregorianCalendar ();
        Date f = new Date ();
        calend.setTime(f);

        n = calend.get(Calendar.YEAR);
        return n;
    }

    public String getNombreMes (int mes) {
        String ret;
        switch (mes) {
            case 0: ret = "Enero"; break;
            case 1: ret = "Febrero"; break;
            case 2: ret = "Marzo"; break;
            case 3: ret = "Abril"; break;
            case 4: ret = "Mayo"; break;
            case 5: ret = "Junio"; break;
            case 6: ret = "Julio"; break;
            case 7: ret = "Agosto"; break;
            case 8: ret = "Septiembre"; break;
            case 9: ret = "Octubre"; break;
            case 10: ret = "Noviembre"; break;
            case 11: ret = "Diciembre"; break;
            default: ret = "";
        }
        return ret;
    }

    public String getNombreDia (int diaSemana) {
        String ret;
        switch (diaSemana) {
            case 1: ret = "Domingo"; break;
            case 2: ret = "Lunes"; break;
            case 3: ret = "Martes"; break;
            case 4: ret = "Mi&eacute;rcoles"; break;
            case 5: ret = "Jueves"; break;
            case 6: ret = "Viernes"; break;
            case 7: ret = "S&aacute;bado"; break;
            case 8: ret = "Domingo"; break;
            default: ret = "";
        }
        return ret;
    }

    public String getNombreFechaCompleta (int dia,int mes,int anyo) {
        Calendar calend = new GregorianCalendar ();
        calend.set(anyo, mes-1, dia);

        int diaSemana = calend.get(Calendar.DAY_OF_WEEK);

        String m = this.getNombreMes(mes-1);
        String dSem = this.getNombreDia(diaSemana);

        return dSem + ", " + dia + " de " + m + " del "+ anyo;
    }

    public int getDiasMes (int mes) {
        int ret;
        mes--;
        switch (mes) {
            case 0: ret = 31; break;
            case 1: ret = 28; break;
            case 2: ret = 31; break;
            case 3: ret = 30; break;
            case 4: ret = 31; break;
            case 5: ret = 30; break;
            case 6: ret = 31; break;
            case 7: ret = 31; break;
            case 8: ret = 30; break;
            case 9: ret = 31; break;
            case 10: ret = 30; break;
            case 11: ret = 31; break;
            default: ret = 0;
        }
        return ret;
    }

    public int getPrimerDia (int dia, int diaSemana) {
        int d = diaSemana+1;

        for (int i = dia; i > 0; i--) {
            //System.out.println ("i: " + i);
            d--;
            if (d == 0)
                d = 7;
            //System.out.println ("||d: " + d);
        }

        if (d == 1) {
            d = 8;
        }

        return d;
    }

    public boolean estaEntreDosFechas (Date fecha, Date inicio, Date fin) {
        return ((fecha.after(inicio)) && (fecha.before(fin)));
    }

    public Date diaSiguiente (Date fecha) {
        Calendar c = new GregorianCalendar();
        c.setTime(fecha);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    public int getSemanaDeAnyo (Date fecha) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public int getDia (Date fecha) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getMes (Date fecha) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        return cal.get(Calendar.MONTH);
    }

    public int getAnyo (Date fecha) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        return cal.get(Calendar.YEAR);
    }

    public static boolean esMenor (Date fecha1, Date fecha2) {
        int comp = fecha1.compareTo(fecha2);
        return (comp < 0);
    }

    public static String getHeaderDate() {
        Date fecha = new Date();
        String html = FFecha.getString(fecha,FFecha.sdf_EEEEE);
        html = html.substring(0,1).toUpperCase() + html.substring(1);
        html += ", " + FFecha.getString(fecha,FFecha.sdf_dd);
        html += " de " + FFecha.getString(fecha,FFecha.sdf_MMMMM);
        html += " del " + FFecha.getString(fecha,FFecha.sdf_yyyy);
        html += ". " + FFecha.getString(fecha,FFecha.sdf_HHmm);
        return html;
    }
    public static String getDateTimeString (String fecha, SimpleDateFormat sdf) {
        try {
            System.out.println("ehh" + fecha);
            Date toConvert = sdf.parse(fecha);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String string2 = format.format(toConvert.getTime());
            System.out.println("ehh1" + string2);
            return string2;
        } catch (Exception e) {
            System.out.println ("Exception getDateTimeString: " + e.getMessage());
            return null;
        }
    }
}
