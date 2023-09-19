package com.disderm.utils;

import com.google.common.base.Joiner;

import java.util.*;
import java.util.List;

public class Varios {

    public static Map<String, String> limpiarNullHashMap(Map _map) {
        Map<String, String> toReturn = new HashMap<>();
        for (Object o : _map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            System.out.println("LIMPIANDO " + pair.getValue());
            if (pair.getValue() != null) {

                _map.remove(pair.getKey());
                System.out.println("CLASS VARIOS: LIMPIAMOS ELEMENTO NULL DE MAP " + pair.getKey());
            }
        }

        return _map;
    }

    public static String getLogoutLocalURL() {
        return "location.assign('http://desarrollo.feelfarma.com:10000/feelfarma/logout/logout.jsp')";
    }

    public static void printMapInfo(HashMap<String,String> _map) {
        System.out.println("\r\n---------------");
        System.out.println(Joiner.on("\n").withKeyValueSeparator("=").join(_map));
        System.out.println("---------------");
    }


    public static String removeLastCharacter(String str) {
        String result = Optional.ofNullable(str)
                .filter(sStr -> sStr.length() != 0)
                .map(sStr -> sStr.substring(0, sStr.length() - 1))
                .orElse(str);
        return result;
    }

    public static String removeFirstCharacter(String str) {
        String result = Optional.ofNullable(str)
                .filter(sStr -> sStr.length() != 0)
                .map(sStr -> sStr.substring(1, sStr.length() - 1))
                .orElse(str);
        return result;
    }

    public static String removeLeadingZeros(String str) {
        return str.replaceFirst("^0+(?!$)", "");
    }

    public static String getVersion() {
        String toReturn = "0.43";
        return toReturn;
    }

    public static String getFullURL() {
        return "https://feelfarma.macross.is";
    }

    public static String getFullURLRoot() {
        return "https://feelfarma.macross.is/panel";
    }

    public static String getHost() {
        return "disderm.macross.is";
    }

    public static int getGineAppEcoDaemonVersion() {
        int toReturn = 5;
        return toReturn;
    }

    public static String getGineAppEcoDaemonUpdateFile() {
        String toReturn = "/opt/tomcat/latest/webapps/backoffice/box/gineapp_daemon/update.zip";
        return toReturn;
    }

    public static String getEcoFolder() {
        String toReturn = "/opt/tomcat/latest/resources/ecoupload";
        return toReturn;
    }


    public static String getResetURL() {
        String toReturn = "https://intranet.gineapp.com:10000/backoffice/nueva-clave-app?token=";
        return toReturn;
    }

    public static String getURL() {
        String toReturn = "https://intranet.gineapp.com:10000/backoffice/";
        return toReturn;
    }

    public static String getURLServices() {
        String toReturn = "https://intranet.gineapp.com:10000/backoffice/services";
        return toReturn;
    }

    public static String getHTMLTerminosCondiciones() {
        String toReturn = "<p><center><span style=\"font-family: Tahoma, Geneva, sans-serif; font-size: 24px;\"><b>SOPORTE</b></center></p><p> <span style=\"font-family: Tahoma, Geneva, sans-serif; font-size: 24px;  font-weight: bold;\">Para cualquier duda o problema p&oacute;ngase en contacto con <a href=\"mailto:soporte@gineapp.com\">soporte@gineapp.com</a> en horario de 9:00 a 14:00 y 16:00 a 18:00 horas</span></p>" +
                "<p><span style=\"font-family: Tahoma, Geneva, sans-serif; font-size: 16px;\">Las presentes Condiciones Generales de Uso tienen por objeto regular las condiciones de acceso y utilizaci&oacute;n de la aplicaci&oacute;n, as&iacute; como los servicios proporcionados a trav&eacute;s de dispositivos m&oacute;viles del usuario (en adelante, la &ldquo;Aplicaci&oacute;n&rdquo;), por parte de su titular, Centro de Ginecolog&iacute;a y Diagn&oacute;stico Prenatal Doctor Chac&oacute;n S.L., con domicilio en C/ Asunci&oacute;n N&ordm; 84, 2&ordm;C, CP 41011, Sevilla, inscrita en el Registro Mercantil de al tomo 4469, folio 130 hoja 69283, en adelante GineApp&nbsp;</span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">GineApp pone a disposici&oacute;n de los usuarios la direcci&oacute;n de correo soporte@gineapp.com &nbsp;para que puedan plantear cualquier duda sobre las presentes Condiciones Generales de Uso y el funcionamiento de la Aplicaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">A los efectos de las presentes Condiciones Generales de Uso, se entender&aacute; por usuario la persona que acceda, navegue, utilice o participe en los servicios de GineApp, de conformidad con lo dispuesto a continuaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">1. ACEPTACI&Oacute;N DE LAS CONDICIONES GENERALES DE USO&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">El acceso y navegaci&oacute;n de un usuario por la Aplicaci&oacute;n implica la aceptaci&oacute;n de forma plena, expresa y sin reservas de las presentes Condiciones Generales de Uso. Cualquier persona que no acepte estas Condiciones Generales de Uso deber&aacute; no podr&aacute; utilizar la Aplicaci&oacute;n y/o los servicios de La Aplicaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Asimismo, la utilizaci&oacute;n por parte del usuario de los servicios de GineApp supone, en todo caso, la adhesi&oacute;n a las presentes Condiciones Generales de Uso en la versi&oacute;n publicada en el momento mismo de utilizaci&oacute;n de la Aplicaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Mediante la aceptaci&oacute;n de las presentes Condiciones Generales de Uso el usuario declara que es una persona mayor de edad y que tiene derecho, capacidad y legitimaci&oacute;n para prestar su consentimiento a estas Condiciones Generales de Uso y para dar cumplimiento a las mismas. Podr&aacute;n ser usuarios de la Aplicaci&oacute;n todas las personas f&iacute;sicas o jur&iacute;dicas, independientemente de su nacionalidad o pa&iacute;s de residencia.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">2. SERVICIOS Y FUNCIONAMIENTO DE GineApp&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">La Aplicaci&oacute;n ha sido creada para, ofrecer informaci&oacute;n de salud de forma estructurada Los servicios incluidos en GineApp son:&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Fotograf&iacute;as de la gestaci&oacute;n , recordatorio de citas, publicidad.</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">3. OBLIGACIONES DE LOS USUARIOS&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Toda la informaci&oacute;n que los usuarios faciliten a trav&eacute;s de la Aplicaci&oacute;n deber&aacute; ser veraz. A estos efectos, los usuarios garantizan la autenticidad de todos aquellos datos que comuniquen como consecuencia de la cumplimentaci&oacute;n de los formularios del LA TOMA DE DATOS PERSONALES&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Los usuarios deber&aacute;n facilitar informaci&oacute;n a GineApp de la que sean titulares, evitando la vulneraci&oacute;n de derechos de terceros y, en particular, derechos de propiedad intelectual o industrial y derechos de imagen. En el supuesto de que los usuarios incumplan dicha obligaci&oacute;n, responder&aacute;n frente a terceros de los da&ntilde;os y perjuicios ocasionados, manteniendo indemne en todo momento a GineApp.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Los usuarios se obligan a respetar las leyes aplicables y los derechos de terceros al utilizar los contenidos y servicios de la Aplicaci&oacute;n. Asimismo los usuarios se comprometen a utilizar la Aplicaci&oacute;n de conformidad con las presentes Condiciones Generales de Uso, las buenas pr&aacute;cticas de mercado y el orden p&uacute;blico.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">De modo ilustrativo y no limitativo, los usuarios no podr&aacute;n:&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">- Facilitar informaci&oacute;n falsa, denigrante, injuriosa, calumniosa o que afecte a los derechos al honor, intimidad y propia imagen de terceros; (En el caso que exista Chat)&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">- Bloquear, sobrescribir, modificar o copiar los contenidos de la Aplicaci&oacute;n, a no ser que ello sea necesario para la correcta utilizaci&oacute;n de los servicios de GineApp;&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">- Toda acci&oacute;n apta para perjudicar la funcionalidad de la infraestructura de la Aplicaci&oacute;n, especialmente para sobrecargarla;&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">- Los usuarios ser&aacute;n responsables en caso de incumplimiento de las presentes Condiciones Generales de Uso y de las conductas que da&ntilde;en, inutilicen, sobrecarguen, deterioren o impidan la normal utilizaci&oacute;n de la Aplicaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">GineApp podr&aacute; denegar o retirar el acceso a la Aplicaci&oacute;n a los usuarios que incumplan las presentes Condiciones Generales de Uso, sin necesidad de preaviso alguno. Los usuarios que incumplan las presentes Condiciones Generales de Uso responder&aacute;n de todos los da&ntilde;os y perjuicios que se deriven de su actuaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">4. RESPONSABILIDAD DE GineApp&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">4.1. &nbsp; &nbsp; Exclusi&oacute;n de responsabilidad&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">GineApp se reserva el derecho de editar, actualizar, modificar, suspender, eliminar o finalizar los servicios ofrecidos por la Aplicaci&oacute;n, incluyendo todo o parte de su contenido, sin necesidad de previo aviso, as&iacute; como de modificar la forma o tipo de acceso a esta.Las posibles causas de modificaci&oacute;n pueden tener lugar por motivos tales como su adaptaci&oacute;n a las posibles novedades legislativas y cambios en la propia Aplicaci&oacute;n, as&iacute; como a las que se puedan derivar de los c&oacute;digos tipos existentes en la materia o por motivos estrat&eacute;gicos o corporativos.GineApp no ser&aacute; responsable del uso de la APLICACI&Oacute;N por un menor de edad, siendo la descarga y uso de la APLICACI&Oacute;N exclusiva responsabilidad del usuario.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">4.2. &nbsp; &nbsp; Responsabilidad por los servicios ofrecidos.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Los servicios ofrecidos por GineApp son de car&aacute;cter exclusivamente informativo para los usuarios.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Bajo ninguna circunstancia los usuarios deben utilizar la informaci&oacute;n ofrecida por GineApp para automedicarse.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">GineApp &nbsp;excluye cualquier tipo de responsabilidad por los da&ntilde;os y perjuicios de toda naturaleza que puedan deberse a la falta de veracidad, exactitud, exhaustividad y/o actualidad de los contenidos.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">La Informaci&oacute;n transmitida por GineApp est&aacute;n basan en la veracidad y exactitud de la informaci&oacute;n facilitada por el usuario. Consiguientemente, GineApp no se hace responsable de la falta de veracidad, exactitud y exhaustividad de los contenidos debida al incumplimiento del usuario de facilitar informaci&oacute;n veraz y exacta a trav&eacute;s de la consultas.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">GineApp no recomienda ni valida el uso de un f&aacute;rmaco en concreto o un determinado diagn&oacute;stico cl&iacute;nico, ni recomienda terapia alguna. GineApp es una fuente de informaci&oacute;n. &nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">4.3. &nbsp; &nbsp; Responsabilidad por el funcionamiento de la Aplicaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">GineApp no se hace responsable de los perjuicios que se pudieran derivar de interferencias, omisiones, interrupciones, virus inform&aacute;ticos, aver&iacute;as y/o desconexiones en el funcionamiento operativo de este sistema electr&oacute;nico o en los aparatos y equipos inform&aacute;ticos de los usuarios, motivadas por causas ajenas a GineApp, que impidan o retrasen la prestaci&oacute;n de los servicios o la navegaci&oacute;n por la Aplicaci&oacute;n, ni de los retrasos o bloqueos en el uso causados por deficiencias o sobrecargas de Internet o en otros sistemas electr&oacute;nicos, ni de la imposibilidad de dar el servicio o permitir el acceso por causas no imputables a GineApp, debidas a los usuarios, a terceros, o a supuestos de fuerza mayor. &nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">GineApp no se hace responsable por los da&ntilde;os y perjuicios de toda naturaleza que pudieran derivarse de la disponibilidad y continuidad t&eacute;cnica del funcionamiento de la Aplicaci&oacute;n. En cualquier caso, GineApp llevar&aacute; a cabo todas las actuaciones necesarias para restablecer sus servicios en caso de fallo t&eacute;cnico.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Todo usuario de esta Aplicaci&oacute;n se compromete a mantener indemne a GineApp de cualquier reclamaci&oacute;n derivada de problemas surgidos por las causas descritas en el presente apartado.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">4.4. &nbsp; &nbsp; Responsabilidad de uso&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">La responsabilidad de uso de la APLICACI&Oacute;N corresponde solo al usuario. Salvo lo establecido en estos T&eacute;rminos y Condiciones, GineApp no es responsable de ninguna p&eacute;rdida o da&ntilde;o que se produzca en relaci&oacute;n con la descarga o el uso de la APLICACI&Oacute;N, tales como los producidos a consecuencia de fallos, aver&iacute;as o bloqueos en el funcionamiento de la APLICACI&Oacute;N (por ejemplo, y sin car&aacute;cter limitativo: error en las l&iacute;neas de comunicaciones, defectos en el hardware o software de la APLICACI&Oacute;N o fallos en la red de Internet). Igualmente, GineApp tampoco ser&aacute; responsable de los da&ntilde;os producidos a consecuencia de un uso indebido o inadecuado de la APLICACI&Oacute;N por parte de los usuarios&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">5. DERECHOS DE PROPIEDAD INDUSTRIAL E INTELECTUAL&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Los derechos de propiedad intelectual e industrial sobre la APLICACI&Oacute;N son titularidad GineApp, correspondi&eacute;ndole el ejercicio exclusivo de los derechos de explotaci&oacute;n de los mismos en cualquier forma y, en especial, los derechos de reproducci&oacute;n, distribuci&oacute;n, comunicaci&oacute;n p&uacute;blica y transformaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Los terceros titulares de derechos de propiedad intelectual e industrial sobre fotograf&iacute;as, logotipos, y cualesquiera otros s&iacute;mbolos o contenidos incluidos en la APLICACI&Oacute;N han concedido las correspondientes autorizaciones para su reproducci&oacute;n, distribuci&oacute;n y puesta a disposici&oacute;n del p&uacute;blico.El usuario reconoce que la reproducci&oacute;n, modificaci&oacute;n, distribuci&oacute;n, comercializaci&oacute;n, descompilaci&oacute;n, desensamblado, utilizaci&oacute;n de t&eacute;cnicas de ingenier&iacute;a inversa o de cualquier otro medio para obtener el c&oacute;digo fuente, transformaci&oacute;n o publicaci&oacute;n de cualquier resultado de pruebas de referencias no autorizadas de cualquiera de los elementos y utilidades integradas dentro del desarrollo constituye una infracci&oacute;n de los derechos de propiedad intelectual de GineApp, oblig&aacute;ndose, en consecuencia, a no realizar ninguna de las acciones mencionadas&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">7. LEY APLICABLE Y JURISDICCI&Oacute;N&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-family: Tahoma, Geneva, sans-serif; font-size: 16px;\">El usuario acepta que la legislaci&oacute;n aplicable y los Juzgados y Tribunales competentes para conocer de las divergencias derivadas de la interpretaci&oacute;n o aplicaci&oacute;n de este clausulado son los espa&ntilde;oles, y se somete, con renuncia expresa a cualquier otro fuero, a los juzgados y tribunales m&aacute;s cercanos a la ciudad de SEVILLA.</span>\n" +
                "                        </p>\n";
        return toReturn;
    }


    public static String getHTMLTerminosCondicionesJavi() {
        String toReturn = "<!DOCTYPE html><html><head><meta name=\"viewport\" content=\"width=device-width, user-scalable=no\"></head><p><span style=\"font-family: Tahoma, Geneva, sans-serif; font-size: 16px;\">Las presentes Condiciones Generales de Uso tienen por objeto regular las condiciones de acceso y utilizaci&oacute;n de la aplicaci&oacute;n, as&iacute; como los servicios proporcionados a trav&eacute;s de dispositivos m&oacute;viles del usuario (en adelante, la &ldquo;Aplicaci&oacute;n&rdquo;), por parte de su titular, \n" +
                "<b>Javier Cantos Vargas</b>\n" +
                "NIF: 28632520G C/ Verdejo, 70 41807 Espartinas Sevilla\n" +
                "Espa&ntilde;a, en adelante GineApp&nbsp;</span>\n" +

                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\"><b>DERECHOS DE PROPIEDAD INDUSTRIAL E INTELECTUAL&nbsp;</b></span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Los derechos de propiedad intelectual e industrial sobre la APLICACI&Oacute;N son titularidad GineApp, correspondi&eacute;ndole el ejercicio exclusivo de los derechos de explotaci&oacute;n de los mismos en cualquier forma y, en especial, los derechos de reproducci&oacute;n, distribuci&oacute;n, comunicaci&oacute;n p&uacute;blica y transformaci&oacute;n.&nbsp;</span></span>\n" +
                "                        </p>\n" +
                "                        <p><span style=\"font-size: 16px;\"><span style=\"font-family: Tahoma,Geneva, sans-serif;\">Los terceros titulares de derechos de propiedad intelectual e industrial sobre fotograf&iacute;as, logotipos, y cualesquiera otros s&iacute;mbolos o contenidos incluidos en la APLICACI&Oacute;N han concedido las correspondientes autorizaciones para su reproducci&oacute;n, distribuci&oacute;n y puesta a disposici&oacute;n del p&uacute;blico.El usuario reconoce que la reproducci&oacute;n, modificaci&oacute;n, distribuci&oacute;n, comercializaci&oacute;n, descompilaci&oacute;n, desensamblado, utilizaci&oacute;n de t&eacute;cnicas de ingenier&iacute;a inversa o de cualquier otro medio para obtener el c&oacute;digo fuente, transformaci&oacute;n o publicaci&oacute;n de cualquier resultado de pruebas de referencias no autorizadas de cualquiera de los elementos y utilidades integradas dentro del desarrollo constituye una infracci&oacute;n de los derechos de propiedad intelectual de GineApp, oblig&aacute;ndose, en consecuencia, a no realizar ninguna de las acciones mencionadas&nbsp;</span></span>\n" +
                "                        </p></html>\n";

        return toReturn;

    }


    public static String replaceAnsi(String text) {
        String toReturn = "";
        text = text.replace("Ã¡", "a");
        text = text.replace("Ã©", "e");
        text = text.replace("Ã­", "i");
        text = text.replace("Ã³", "o");
        text = text.replace("Ãº", "u");

        // caracteres raros: tildes mayusculas
        text = text.replace("Ã", "A");
        text = text.replace("Ã‰", "E");
        text = text.replace("Ã", "I");
        text = text.replace("Ã“", "O");
        text = text.replace("Ãš", "U");


        // caracteres raros: tildes inversas minusculas
        text = text.replace("Ã ", "a");
        text = text.replace("Ã¨", "e");
        text = text.replace("Ã¬", "i");
        text = text.replace("Ã²", "o");
        text = text.replace("Ã¹", "u");

        // caracteres raros: tildes inversas mayusculas
        text = text.replace("Ã€", "A");
        text = text.replace("Ãˆ", "E");
        text = text.replace("ÃŒ", "I");
        text = text.replace("Ã’", "O");
        text = text.replace("Ã™", "U");

        // caracteres raros: ñ minuscula y mayuscula
        text = text.replace("Ã‘", "n");
        text = text.replace("Ã±", "N");
        return toReturn;
    }

    public static boolean checkStringItem(List<String> _list, String toCheck) {
        int result = _list.indexOf(toCheck);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }
}
