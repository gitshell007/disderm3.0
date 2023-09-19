package com.disderm.service;

import com.disderm.utils.SSLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SystemMailService {
    HashMap email_hashmap = new HashMap();
    private static final SSLog ssLog = new SSLog();

    public SystemMailService() {
        email_hashmap.put("smtp_host", "mail.gineapp.com");
        email_hashmap.put("smtp_port", "25");
        email_hashmap.put("smtp_user", "soporte@gineapp.com");
        email_hashmap.put("smtp_password", "Gineapp2020.");
        email_hashmap.put("mailFromAddress", "soporte@gineapp.com");
        email_hashmap.put("mailFromName", "Area soporte GineApp");
    }

    public boolean sendEmail(String subject, String message, ArrayList<String> arrayRecipientes) {
        boolean success = true;
        // ...
        Properties props = new Properties();

        props.put("mail.smtp.host", email_hashmap.get("smtp_host").toString());
        System.out.println( email_hashmap.get("smtp_host").toString());
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", email_hashmap.get("smtp_port").toString());
        props.setProperty("mail.smtp.user", email_hashmap.get("smtp_user").toString());
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        InternetAddress emailFrom = null;
        try {
            emailFrom = new InternetAddress(email_hashmap.get("mailFromAddress").toString(), email_hashmap.get("mailFromName").toString());
        } catch (IOException ex) {
            System.out.println("BAD FROM ADDRESS " + ex.getMessage());
        }


        InternetAddress[] address = new InternetAddress[arrayRecipientes.size()];
        try {
            for (int i = 0; i < arrayRecipientes.size(); i++) {
                address[i] = new InternetAddress(arrayRecipientes.get(i));
                System.out.println("Se enviará a ..." + arrayRecipientes.get(i));
            }
        } catch (Exception ex) {
            System.out.println("BAD RECIPIENTS ADDRESS " + ex.getMessage());
        }

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(emailFrom);
            msg.addRecipients(Message.RecipientType.BCC,
                    address);
            msg.setSubject(subject);
            msg.setText(message, "ISO-8859-1", "html");
            Transport t = session.getTransport("smtp");


            t.connect(email_hashmap.get("smtp_user").toString(), email_hashmap.get("smtp_password").toString());
            System.out.println("SENDING..");
            t.sendMessage(msg, address);
            t.close();

        } catch (AddressException e) {
            System.out.println("SENDING EMAIL (address): " + e.getMessage());
            success = false;
            // ...
        } catch (MessagingException e) {
            System.out.println("SENDING EMAIL (messaging) " + e.getMessage());
            success = false;
        }
        return success;
    }


    public boolean sendEmailOneRecipient(String subject, String message, String recipient) {

        boolean success = true;
        ssLog.info("Enviadno correo a " + recipient);
        // ...
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", email_hashmap.get("smtp_host").toString());

        //props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", email_hashmap.get("smtp_port").toString());
        props.setProperty("mail.smtp.user", email_hashmap.get("smtp_user").toString());
        props.setProperty("mail.smtp.auth", "true");
        /** HETZNER
         props.setProperty("mail.smtp.starttls.enable", "true");
         props.setProperty("mail.smtp.port",smanager.getServiceValueDec("EMAIL","Port"));
         props.setProperty("mail.smtp.user", smanager.getServiceValueDec("EMAIL","Username"));
         props.setProperty("mail.smtp.auth", "true");
         **/

        /** ZOHO PUERTO 465
         props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.setProperty("mail.smtp.socketFactory.fallback", "false");
         props.setProperty("mail.smtp.socketFactory.port", smanager.getServiceValueDec("EMAIL", "Port"));
         props.put("mail.smtp.startssl.enable", "true");*/
        /** FIN ZOHO **/

        Session session = Session.getDefaultInstance(props, null);
        InternetAddress emailFrom = null;
        InternetAddress[] recipientTo = new InternetAddress[1];
        try {
            emailFrom = new InternetAddress(email_hashmap.get("mailFromAddress").toString(), email_hashmap.get("mailFromName").toString());
            recipientTo[0] = new InternetAddress(recipient);
        } catch (Exception ex) {
            System.out.println("BAD RECIPIENTS ADDRESS " + ex.getMessage());
        }

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(emailFrom);
            msg.addRecipient(Message.RecipientType.BCC, recipientTo[0]);
            msg.setSubject(subject);
            msg.setText(message, "ISO-8859-1", "html");
            Transport t = session.getTransport("smtp");
            t.connect(email_hashmap.get("smtp_user").toString(), email_hashmap.get("smtp_password").toString());
            System.out.println("starting sendEmailOneRecipient..");
            t.sendMessage(msg, recipientTo);
            t.close();

        } catch (AddressException e) {
            System.out.println("SENDING EMAIL (address): " + e.getMessage() + " " + recipient);;
            success = false;
            // ...
        } catch (MessagingException e) {
            System.out.println("SENDING EMAIL (messaging) " + e.getMessage()+   " " + recipient);
            success = false;
        }
        return success;
    }


    public void sendAlertEmailEnabled(String _user, String _ip, String _agent) {
        Runnable task2 = () -> {
            String mensajehtml = html_template_aviso.replaceAll("<MENSAJEADMINISTRADOR>", "ALERTA: Usuario desactivado ha intentado acceder: " + _user + " desde IP: " + _ip + " / " + _agent);
            SystemManager smanager = new SystemManager();
            sendEmailOneRecipient("ALERTA INTENTOS ACCESO USUARIO " + _user, mensajehtml, smanager.getServiceValueDec("EMAIL", "AlertEmail1"));
        };

        new Thread(task2).start();
    }

    public void sendAlertEmailFail(String _user, String _ip, String _agent) {
        Runnable task2 = () -> {
            String mensajehtml = html_template_aviso.replaceAll("<MENSAJEADMINISTRADOR>", "ALERTA: El usuario introdujo mal el password " + _user + " desde IP: " + _ip + " / " + _agent);
            SystemManager smanager = new SystemManager();
            sendEmailOneRecipient("ALERTA BACKOFFICE PASSWORD INCORRECTO " + _user, mensajehtml, smanager.getServiceValueDec("EMAIL", "AlertEmail1"));
        };

        new Thread(task2).start();
    }

    public void sendAlertEmailAttempts(String _user, String _ip, String _agent) {
        Runnable task2 = () -> {
            String mensajehtml = html_template_aviso.replaceAll("<MENSAJEADMINISTRADOR>", "ALERTA BACKOFFICE: superados los intentos de acceso para el usuario: " + _user + " desde IP: " + _ip + " / " + _agent);
            SystemManager smanager = new SystemManager();
            sendEmailOneRecipient("ALERTA BACKOFFICE INTENTOS ACCESO " + _user, mensajehtml, smanager.getServiceValueDec("EMAIL", "AlertEmail1"));
        };

        new Thread(task2).start();
    }

    public void sendAlertEmailIPBLOCKED(String _ip, String _agent) {
        Runnable task2 = () -> {
            String mensajehtml = html_template_aviso.replaceAll("<MENSAJEADMINISTRADOR>", "ALERTA BACKOFFICE: IP BLOQUEADA : " + _ip + " / " + _agent);
            SystemManager smanager = new SystemManager();
            sendEmailOneRecipient("ALERTA BACKOFFICE IP BLOQUEADA " + _ip, mensajehtml, smanager.getServiceValueDec("EMAIL", "AlertEmail1"));
        };

        new Thread(task2).start();
    }

    public void sendResetAppPasswordEmail(String _url, String _email) {
        Runnable task2 = () -> {
            String mensajehtml = html_template_reset_app_password.replaceAll("<MENSAJEADMINISTRADOR>", "Acceda al link en un plazo de 24 horas para cambiar su clave: <a href=\"" + _url + "\">Pulse aqui para ir a la pagina</a>");
            sendEmailOneRecipient("Solicitud de cambio de clave", mensajehtml, _email);
        };
        new Thread(task2).start();
    }

    public void sendAltaAppdEmail(String _email, String password) {
        Runnable task2 = () -> {
            ssLog.info("sending email to " + _email);
            String mensajehtml = html_template_alta_app_v1.replaceAll("<MENSAJE_A_CAMBIAR>", "<br>Bienvenido a Gineapp. <br>Su usuario es " + _email + " y su clave de acceso: <b>" + password + "</b>");
            sendEmailOneRecipient("Alta en GineApp", mensajehtml, _email);

        };

        new Thread(task2).start();
    }




    private String html_template_reset_app_password = "<html lang= \"en\">\n" +
            "  <head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8=\">\n" +
            "    <meta name=\"viewport\" content=\"initial-scale=1.0\">\n" +
            "    <meta name=\"format-detection\" content=\"telephone=no\">\n" +
            "    <title>Solicitud de cambio de clave</title>\n" +
            "    <style type=\"text/css\">\n" +
            "  \n" +
            "        /* resets */\n" +
            "        #outlook a {padding:0;}\n" +
            "        .ReadMsgBody { width: 100%; background-color: #ccc;}\n" +
            "        .ExternalClass {width: 100%; background-color: #ccc;}\n" +
            "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height:100%;}\n" +
            "        body {width:100% !important; min-width:100%; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n" +
            "        table {border-spacing:0;}\n" +
            "        table td {border-collapse:collapse;}\n" +
            "        img {outline:0; text-decoration:none;-ms-interpolation-mode:bicubic; width:auto; max-width:100%; display:block;}\n" +
            "        a img {border:0}        \n" +
            "        .yshortcuts a {border-bottom: none !important;}\n" +
            "\n" +
            "        /* custom */\n" +
            "        body,table,td{color:#333; font-family:\"Helvetica\",\"Arial\",sans-serif; font-weight:normal; padding:0; margin:0; line-height:1.3;}\n" +
            "        .intro {padding-top:70px; padding-bottom:30px; font-size:24px;}\n" +
            "        .heading {border-top:4px solid #333; padding-top:20px; font-size:36px; font-weight:bold;}        \n" +
            "        .period {text-transform:uppercase; padding-top:6px; padding-bottom:20px; border-bottom:1px solid #666;}\n" +
            "        .balance {padding-bottom:20px;}     \n" +
            "        .balance td, .review td {text-transform:uppercase;}\n" +
            "        .balance td a {text-decoration:none; color:#333; text-transform:none;}\n" +
            "        .balance td a img {padding-bottom:12px;}\n" +
            "        .advice {border-top:1px solid #666; padding-top:15px; padding-bottom:80px; font-size:18px;}\n" +
            "        .advice a {color:#4184f0; text-decoration:none; font-weight:bold;}\n" +
            "        .review {padding-top:15px; border-top:1px solid #666; padding-bottom:15px;}\n" +
            "        .review td a {color:#333; text-decoration:none;}\n" +
            "        .highlight a {font-size:21px; font-weight:bold; text-transform:none;}\n" +
            "        .footer {padding-top:20px; border-top:1px solid #666;}\n" +
            "        .footer td {font-size:12px; color:#666; padding-bottom:15px;}\n" +
            "        .footer td a {color:#4184f0; text-decoration:underline;}\n" +
            "  \n" +
            "        /* columns to rows */\n" +
            "        @media only screen and (max-width : 600px) {\n" +
            "            table.header, table.content {\n" +
            "                width: 90% !important;              \n" +
            "            }\n" +
            "            td[class=\"force-col\"] {\n" +
            "                display: block;\n" +
            "                padding-right: 0 !important;\n" +
            "                text-align: left !important;\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "            .intro {padding-top:30px !important;}\n" +
            "            .advice {padding-bottom:40px;}              \n" +
            "        }\n" +
            "  \n" +
            "    </style>\n" +
            "  </head>\n" +
            "  <body style=\"margin:0; padding:0;\" bgcolor=\"#fff\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
            "\n" +
            "    <!-- wrapper -->\n" +
            "    <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#fff\">\n" +
            "      <tr>\n" +
            "        <td align=\"center\" valign=\"top\" bgcolor=\"#fff\" style=\"background-color:#fff;\">\n" +
            "\n" +
            "          <!-- header-wrapper -->\n" +
            "          <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#4184f0\">\n" +
            "            <tr>\n" +
            "              <td align=\"center\" valign=\"top\" bgcolor=\"#fff\" style=\"background-color:#4184f0\">\n" +
            " \n" +
            "                <!-- header -->\n" +
            "                <table border=\"0\" width=\"580\" cellpadding=\"0\" cellspacing=\"0\" class=\"header\" bgcolor=\"#bfc5cf\">\n" +
            "                  <tr>\n" +
            "                    <td style=\"padding-top:10px; padding-bottom:10px;\">\n" +
            "                      <p>Solicitud de cambio de clave de acceso a GineApp</p>                   \n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </table>\n" +
            "                <!-- /header --> \n" +
            "              \n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </table>\n" +
            "          <!-- /header-wrapper -->\n" +
            "\n" +
            "          <!-- content -->\n" +
            "          <table border=\"0\" width=\"580\" cellpadding=\"0\" cellspacing=\"0\" class=\"content\" bgcolor=\"#fff\">\n" +
            "          \n" +
            "            <!-- intro -->\n" +
            "            <tr>\n" +
            "              <td class=\"intro\" valign=\"top\" style=\"padding-top:50px;padding-right:20px; padding-bottom:20px; font-size:24px;\">\n" +
            "                <p>Hemos recibido su solicitud de cambio de clave</p><MENSAJEADMINISTRADOR>\n" +
            "<p></p>\n" +
            "              </td>              \n" +
            "            </tr>\n" +
            "          </table>\n" +
            "          <!-- /content -->        \n" +
            "    \n" +
            "        </td>\n" +
            "      </tr>\n" +
            "    </table>\n" +
            "    <!-- /wrapper-->\n" +
            "  </body>\n" +
            "</html> ";


    private String html_template_alta_app_v1 = "<html lang= \"es\">\n" +
            "  <head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8=\">\n" +
            "    <meta name=\"viewport\" content=\"initial-scale=1.0\">\n" +
            "    <meta name=\"format-detection\" content=\"telephone=no\">\n" +
            "    <title>Solicitud de alta en GineApp</title>\n" +
            "    <style type=\"text/css\">\n" +
            "  \n" +
            "        /* resets */\n" +
            "        #outlook a {padding:0;}\n" +
            "        .ReadMsgBody { width: 100%; background-color: #ccc;}\n" +
            "        .ExternalClass {width: 100%; background-color: #ccc;}\n" +
            "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height:100%;}\n" +
            "        body {width:100% !important; min-width:100%; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n" +
            "        table {border-spacing:0;}\n" +
            "        table td {border-collapse:collapse;}\n" +
            "        img {outline:0; text-decoration:none;-ms-interpolation-mode:bicubic; width:auto; max-width:100%; display:block;}\n" +
            "        a img {border:0}        \n" +
            "        .yshortcuts a {border-bottom: none !important;}\n" +
            "\n" +
            "        /* custom */\n" +
            "        body,table,td{color:#333; font-family:\"Helvetica\",\"Arial\",sans-serif; font-weight:normal; padding:0; margin:0; line-height:1.3;}\n" +
            "        .intro {padding-top:70px; padding-bottom:30px; font-size:24px;}\n" +
            "        .heading {border-top:4px solid #333; padding-top:20px; font-size:36px; font-weight:bold;}        \n" +
            "        .period {text-transform:uppercase; padding-top:6px; padding-bottom:20px; border-bottom:1px solid #666;}\n" +
            "        .balance {padding-bottom:20px;}     \n" +
            "        .balance td, .review td {text-transform:uppercase;}\n" +
            "        .balance td a {text-decoration:none; color:#333; text-transform:none;}\n" +
            "        .balance td a img {padding-bottom:12px;}\n" +
            "        .advice {border-top:1px solid #666; padding-top:15px; padding-bottom:80px; font-size:18px;}\n" +
            "        .advice a {color:#4184f0; text-decoration:none; font-weight:bold;}\n" +
            "        .review {padding-top:15px; border-top:1px solid #666; padding-bottom:15px;}\n" +
            "        .review td a {color:#333; text-decoration:none;}\n" +
            "        .highlight a {font-size:21px; font-weight:bold; text-transform:none;}\n" +
            "        .footer {padding-top:20px; border-top:1px solid #666;}\n" +
            "        .footer td {font-size:12px; color:#666; padding-bottom:15px;}\n" +
            "        .footer td a {color:#4184f0; text-decoration:underline;}\n" +
            "  \n" +
            "        /* columns to rows */\n" +
            "        @media only screen and (max-width : 600px) {\n" +
            "            table.header, table.content {\n" +
            "                width: 90% !important;              \n" +
            "            }\n" +
            "            td[class=\"force-col\"] {\n" +
            "                display: block;\n" +
            "                padding-right: 0 !important;\n" +
            "                text-align: left !important;\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "            .intro {padding-top:30px !important;}\n" +
            "            .advice {padding-bottom:40px;}              \n" +
            "        }\n" +
            "  \n" +
            "    </style>\n" +
            "  </head>\n" +
            "  <body style=\"margin:0; padding:0;\" bgcolor=\"#fff\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
            "\n" +
            "    <!-- wrapper -->\n" +
            "    <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#fff\">\n" +
            "      <tr>\n" +
            "        <td align=\"center\" valign=\"top\" bgcolor=\"#fff\" style=\"background-color:#fff;\">\n" +
            "\n" +
            "          <!-- header-wrapper -->\n" +
            "          <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#da35ca\">\n" +
            "            <tr>\n" +
            "              <td align=\"center\" valign=\"top\" bgcolor=\"#fff\" style=\"background-color:#da35ca\">\n" +
            " \n" +
            "                <!-- header -->\n" +
            "                <table border=\"0\" width=\"580\" cellpadding=\"0\" cellspacing=\"0\" class=\"header\" bgcolor=\"#bfc5cf\">\n" +
            "                  <tr>\n" +
            "                    <td style=\"padding-top:10px; padding-bottom:10px;\"  align=\"center\">\n" +
            "                      <span style=\"align-items: center; justify-content: center;\"><b>Bienvenido a GineApp</b></span>\n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </table>\n" +
            "                <!-- /header --> \n" +
            "              \n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </table>\n" +
            "          <!-- /header-wrapper -->\n" +
            "\n" +
            "          <!-- content -->\n" +
            "          <table border=\"0\" width=\"580\" cellpadding=\"0\" cellspacing=\"0\" class=\"content\" bgcolor=\"#fff\">\n" +
            "          \n" +
            "            <!-- intro -->\n" +
            "            <tr>\n" +
            "              <td class=\"intro\" valign=\"top\" style=\"padding-top:50px;padding-right:20px; padding-bottom:20px; font-size:24px;\">\n" +
            "                <MENSAJE_A_CAMBIAR>\n" +
            "<p></p>\n" +
            "              </td>              \n" +
            "            </tr>\n" +
            "            <tr>\n";
    /*
            "        <td><span>\n" +
            "          AVISO LEGAL:<br> \n" +
            "Este mensaje y sus archivos adjuntos van dirigidos exclusivamente a su destinatario, pudiendo contener información confidencial sometida a secreto profesional. No está permitida su comunicación, reproducción o distribución sin la autorización expresa CENTRO DE GINECOLOGÍA Y DIAGNOSTICO PRENATAL DOCTOR CHACÓN S.L.. Si usted no es el destinatario final, por favor, elimínelo e infórmenos por esta vía.<br>\n" +
            "\n" +
            "\n" +
            "<br>PROTECCIÓN DE DATOS:<br> \n" +
            "De conformidad con lo dispuesto en el Reglamento (UE) 2016/679, de 27 de abril (GDPR), y la Ley Orgánica 3/2018, de 5 de diciembre (LOPDGDD), le informamos de que los datos personales y la dirección de correo electrónico del interesado, se tratarán bajo la responsabilidad de CENTRO DE GINECOLOGÍA Y DIAGNOSTICO PRENATAL DOCTOR CHACÓN S.L. por un interés legítimo y para el envío de comunicaciones sobre nuestros productos y servicios, y se conservarán mientras ninguna de las partes se oponga a ello. Los datos no se comunicarán a terceros, salvo obligación legal. Le informamos de que puede ejercer los derechos de acceso, rectificación, portabilidad y supresión de sus datos y los de limitación y oposición a su tratamiento dirigiéndose a C/ Asunción Nº 84, 2ºC, CP 41011, Sevilla. Email: administacion@ginecologiaprenatal.com. \n" +
            "\n" +
            "Si considera que el tratamiento no se ajusta a la normativa vigente, podrá presentar una reclamación ante la autoridad de control en www.aepd.es.<br> \n" +
            "\n" +
            "PUBLICIDAD: \n" +
            "          En cumplimiento de lo previsto en el artículo 21 de la Ley 34/2002 de Servicios de la Sociedad de la Información y Comercio Electrónico (LSSICE), si usted no desea recibir más información sobre nuestros productos y/o servicios, puede darse de baja enviando un correo electrónico a administracion@ginecologiaprenatal.com, indicando en el Asunto «BAJA» o «NO ENVIAR».</span>\n" +
            "        </td>\n" +
            "      </tr>\n" +
            "          </table>\n" +
            "          <!-- /content -->        \n" +
            "    \n" +
            "        </td>\n" +
            "      </tr>\n" +
            "      \n" +
            "    </table>\n" +
            "    <!-- /wrapper-->\n" +
            "  </body>\n" +
            "</html>  ";
*/

    private String html_template_aviso = "<html lang= \"en\">\n" +
            "  <head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8=\">\n" +
            "    <meta name=\"viewport\" content=\"initial-scale=1.0\">\n" +
            "    <meta name=\"format-detection\" content=\"telephone=no\">\n" +
            "    <title>iA-Com Update Notice</title>\n" +
            "    <style type=\"text/css\">\n" +
            "  \n" +
            "        /* resets */\n" +
            "        #outlook a {padding:0;}\n" +
            "        .ReadMsgBody { width: 100%; background-color: #ccc;}\n" +
            "        .ExternalClass {width: 100%; background-color: #ccc;}\n" +
            "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height:100%;}\n" +
            "        body {width:100% !important; min-width:100%; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n" +
            "        table {border-spacing:0;}\n" +
            "        table td {border-collapse:collapse;}\n" +
            "        img {outline:0; text-decoration:none;-ms-interpolation-mode:bicubic; width:auto; max-width:100%; display:block;}\n" +
            "        a img {border:0}        \n" +
            "        .yshortcuts a {border-bottom: none !important;}\n" +
            "\n" +
            "        /* custom */\n" +
            "        body,table,td{color:#333; font-family:\"Helvetica\",\"Arial\",sans-serif; font-weight:normal; padding:0; margin:0; line-height:1.3;}\n" +
            "        .intro {padding-top:70px; padding-bottom:30px; font-size:24px;}\n" +
            "        .heading {border-top:4px solid #333; padding-top:20px; font-size:36px; font-weight:bold;}        \n" +
            "        .period {text-transform:uppercase; padding-top:6px; padding-bottom:20px; border-bottom:1px solid #666;}\n" +
            "        .balance {padding-bottom:20px;}     \n" +
            "        .balance td, .review td {text-transform:uppercase;}\n" +
            "        .balance td a {text-decoration:none; color:#333; text-transform:none;}\n" +
            "        .balance td a img {padding-bottom:12px;}\n" +
            "        .advice {border-top:1px solid #666; padding-top:15px; padding-bottom:80px; font-size:18px;}\n" +
            "        .advice a {color:#4184f0; text-decoration:none; font-weight:bold;}\n" +
            "        .review {padding-top:15px; border-top:1px solid #666; padding-bottom:15px;}\n" +
            "        .review td a {color:#333; text-decoration:none;}\n" +
            "        .highlight a {font-size:21px; font-weight:bold; text-transform:none;}\n" +
            "        .footer {padding-top:20px; border-top:1px solid #666;}\n" +
            "        .footer td {font-size:12px; color:#666; padding-bottom:15px;}\n" +
            "        .footer td a {color:#4184f0; text-decoration:underline;}\n" +
            "  \n" +
            "        /* columns to rows */\n" +
            "        @media only screen and (max-width : 600px) {\n" +
            "            table.header, table.content {\n" +
            "                width: 90% !important;              \n" +
            "            }\n" +
            "            td[class=\"force-col\"] {\n" +
            "                display: block;\n" +
            "                padding-right: 0 !important;\n" +
            "                text-align: left !important;\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "            .intro {padding-top:30px !important;}\n" +
            "            .advice {padding-bottom:40px;}              \n" +
            "        }\n" +
            "  \n" +
            "    </style>\n" +
            "  </head>\n" +
            "  <body style=\"margin:0; padding:0;\" bgcolor=\"#fff\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
            "\n" +
            "    <!-- wrapper -->\n" +
            "    <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#fff\">\n" +
            "      <tr>\n" +
            "        <td align=\"center\" valign=\"top\" bgcolor=\"#fff\" style=\"background-color:#fff;\">\n" +
            "\n" +
            "          <!-- header-wrapper -->\n" +
            "          <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#4184f0\">\n" +
            "            <tr>\n" +
            "              <td align=\"center\" valign=\"top\" bgcolor=\"#fff\" style=\"background-color:#4184f0\">\n" +
            " \n" +
            "                <!-- header -->\n" +
            "                <table border=\"0\" width=\"580\" cellpadding=\"0\" cellspacing=\"0\" class=\"header\" bgcolor=\"#bfc5cf\">\n" +
            "                  <tr>\n" +
            "                    <td style=\"padding-top:10px; padding-bottom:10px;\">\n" +
            "                      <p>Aviso del sistema iACom</p>                   \n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </table>\n" +
            "                <!-- /header --> \n" +
            "              \n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </table>\n" +
            "          <!-- /header-wrapper -->\n" +
            "\n" +
            "          <!-- content -->\n" +
            "          <table border=\"0\" width=\"580\" cellpadding=\"0\" cellspacing=\"0\" class=\"content\" bgcolor=\"#fff\">\n" +
            "          \n" +
            "            <!-- intro -->\n" +
            "            <tr>\n" +
            "              <td class=\"intro\" valign=\"top\" style=\"padding-top:50px;padding-right:20px; padding-bottom:20px; font-size:24px;\">\n" +
            "                <p>Mensaje del administrador:</p><MENSAJEADMINISTRADOR>\n" +
            "<p></p>\n" +
            "              </td>              \n" +
            "            </tr>\n" +
            "          </table>\n" +
            "          <!-- /content -->        \n" +
            "    \n" +
            "        </td>\n" +
            "      </tr>\n" +
            "    </table>\n" +
            "    <!-- /wrapper-->\n" +
            "  </body>\n" +
            "</html> ";

}


