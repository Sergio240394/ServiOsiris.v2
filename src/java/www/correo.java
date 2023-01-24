/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www;

import BDatos.BDServicios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;



/**
 *
 * @author Paulina Alvarado
 */
public class correo extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       response.setContentType("text/html;charset=iso-8859-1");
       // response.setContentType("text/html;charset=iso-8859-1");

        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet correo</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet correo at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);

    }

  public String InicioEmail(String usuario, String password) {
      //response.setContentType("text/html;charset=UTF-8");
        String url = new String("");
       // String host = new String("exchange.escuelaing.edu.co");
        String login = "";
        usuario = usuario + "@escuelaing.edu.co";
        long dato = 0;
        String tiene = new String();
        Properties prop = new Properties();
            prop.setProperty("mail.pop3.starttls.enable", "false");
            prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.pop3.socketFactory.fallback", "false");
            prop.setProperty("mail.pop3.port", "995");
            prop.setProperty("mail.pop3.socketFactory.port", "995");
            Session sesion = Session.getInstance(prop);
            sesion.setDebug(true);
            try {
       OkHttpClient client = new OkHttpClient().newBuilder()
      .build();
    Request request = new Request.Builder()
      .url("https://login.microsoftonline.com/50640584-2a40-4216-a84b-9b3ee0f3f6cf/oauth2/v2.0/token")
      .method("GET", null)
      .addHeader("Content-Type", "application/x-www-form-urlencoded")
      .addHeader("Cookie", "fpc=AucaHn-V50tKl7QPQYVkChNh_mJTAQAAAJhuWdsOAAAA; stsservicecookie=estsfd; x-ms-gateway-slice=estsfd")
      .build();
      Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
           } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
       
            login = "conectado";
           

        return login;

    }
  
   public String ecorreo(Vector sol, String idev, final String usr, final String pas, String dest, String depend, String ind, HttpServletResponse response) {
       response.setContentType("text/html;charset=UTF-8");
         String usu = (String)sol.elementAt(5);
         String bloque = (String)sol.elementAt(3);
         String ext = (String)sol.elementAt(4);
         String asunto = (String)sol.elementAt(1);
         String solu = (String)sol.elementAt(7);
         String fsol = (String)sol.elementAt(8);
         String resp = (String)sol.elementAt(9);
         String obs = (String)sol.elementAt(10);
         String as = "", texto = "";
         //String destino =  "paulina.alvarado@escuelaing.edu.co"; //dest+"@escuelaing.edu.co"
         String destino =  dest ; //+"@escuelaing.edu.co"; //dest+"@escuelaing.edu.co"
         String blext = "Bloque: " + bloque + " - Extensión: " + ext;

      
         String smtp, mensaje = "";
           try {
            smtp = "smtp.office365.com";
           // MimeMultipart multipart = new MimeMultipart();
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", smtp);
            properties.put("mail.smtp.port", "587");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usr, pas);
                }
            });
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(usr));
            msg.setRecipients(Message.RecipientType.BCC, usr);
    

            msg.setRecipients(Message.RecipientType.BCC, destino);
            if (ind.equals("0")){
                as = "Creación de Servicio OSIRIS" ;
                texto = "Le fue Asignada la solicitud No. " + idev + " \n \n Nombre del Solicitante: " + usu + " \n \n Dependencia: " + depend + " \n \n Descripción de la solicitud: " + asunto + " \n \n " + blext + "" ;
            } else if (ind.equals("1")){
                as = "Asignación de Solicitud" ;
                texto = "Se le ha asignado el registro No. " + idev + " \n \n Nombre del Usuario: " + usu + " \n \n Dependencia: " + depend + " \n \n Descripci�n de la solicitud: " + asunto + "\n \n " + blext + "" ;
            } else if (ind.equals("2")){
                as = "Cierre de Solicitud" ;
                texto = "Se ha tramitado la solicitud No. " + idev + " \n \n Nombre del Usuario: " + usu + " \n \n  Descripción de la solicitud: " + asunto + "\n \n " + blext + ""
                        + " \n \n Responsable: " + dest + "" 
                        + " \n \n Nota** Favor validar solución y finalizar o reasignar Solicitud";
            } else if (ind.equals("3")){
                as = "Solicitud para Reasignar" ;
                texto = "Se solicita reasignar la solicitud No. " + idev + " \n \n Nombre del Usuario: " + usu + " \n \n  Descripción de la solicitud: " + asunto + "\n \n " + blext + ""
                        + " \n \n Responsable: " + dest + "" 
                        + " \n \n Nota**  Favor validar y reasignar Solicitud";
            }else if (ind.equals("5")){
                as = "Reasignación de Servicio OSIRIS" ;
                texto = "Se le ha reasignado la Solicitud No. " + idev + " \n \n Nombre del Usuario que solicita: " + usu + " \n \n Descripción de la solicitud: " + asunto + "\n \n " + blext + "" ;
               }
            else if (ind.equals("6")){
                as = "Finalización Solicitud" ;
                texto = "Se le informa el Cierre de la Solicitud No. " + idev + " \n \n Nombre del Usuario que solicita: " + usu + " \n \n Descripción de la solicitud: " + asunto + "\n \n " + blext + "" ;
               }
            msg.setSubject(as);
             //msg.setSubject(as, "UTF-8"); //Nuevo tycho
            msg.setSentDate(new Date());           
            BodyPart msgBP = new MimeBodyPart();
            msgBP.setText(texto);
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(msgBP);              
            msg.setText(texto);
            //msg.setText(texto, "UTF-8"); //Nuevo tycho
            Transport.send(msg);
            mensaje = "ok";
        } catch (Exception e) {
            mensaje = "error:"  + usr + "/" + " ---- " + e.getMessage();
        }
       return mensaje;
      }

   public void correoVencLic(final String usr, final String pas, String dest, String serial, String UsuResp, String fechVenc) {
      
       
       
         String smtp, mensaje = "";
           try {
            smtp = "smtp.office365.com";
           // MimeMultipart multipart = new MimeMultipart();
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", smtp);
            properties.put("mail.smtp.port", "587");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usr, pas);
                }
            });
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(usr));
            msg.setRecipients(Message.RecipientType.BCC, usr);
    
            msg.setRecipients(Message.RecipientType.BCC, dest);

              String  as = "Vencimiento de Licencia" ;
              String  texto = "Se le informa que esta proximo a vencerse la siguiente Licencia: \n \n Software: "+serial+"  \n Version: "+UsuResp +" \n Fecha de Vencimiento: "+fechVenc+" \n \n Gracias.";
        
            msg.setSubject(as);
             //msg.setSubject(as, "UTF-8"); //Nuevo tycho
            msg.setSentDate(new Date());           
            BodyPart msgBP = new MimeBodyPart();
            msgBP.setText(texto);
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(msgBP);              
            msg.setText(texto);
            //msg.setText(texto, "UTF-8"); //Nuevo tycho
            Transport.send(msg);
            mensaje = "ok";
        } catch (Exception e) {
            mensaje = "error:"  + usr + "/" + " ---- " + e.getMessage();
        }
      
      }
   
   public String correoaprobacion(final String usr, final String pas, String dest, String serial, String UsuResp) {
      
       
       
         String smtp, mensaje = "";
           try {
            smtp = "smtp.office365.com";
           // MimeMultipart multipart = new MimeMultipart();
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", smtp);
            properties.put("mail.smtp.port", "587");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usr, pas);
                }
            });
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(usr));
            msg.setRecipients(Message.RecipientType.BCC, usr);
    
            msg.setRecipients(Message.RecipientType.BCC, dest);

              String  as = "Aprobación de Equipo" ;
              String  texto = "Se le informa que fue aprobada la asignación de equipo en Mesa de Ayuda y puede proceder con la entrega: \n \n Serial: "+serial+"  \n Usuario: "+UsuResp +"\n \n Gracias.";
        
            msg.setSubject(as);
             //msg.setSubject(as, "UTF-8"); //Nuevo tycho
            msg.setSentDate(new Date());           
            BodyPart msgBP = new MimeBodyPart();
            msgBP.setText(texto);
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(msgBP);              
            msg.setText(texto);
            //msg.setText(texto, "UTF-8"); //Nuevo tycho
            Transport.send(msg);
            mensaje = "ok";
        } catch (Exception e) {
            mensaje = "error:"  + usr + "/" + " ---- " + e.getMessage();
        }
       return mensaje;
      }

   
   
   public String CorreoSolicitudAprob(final String usrs, final String pas, String dest, String UsuResp, String serial, String ccresponsable, String Obs, String salonant) {
       
       BDServicios bd = new BDServicios();
       
         Vector infequipo = bd.Detalle_Equipo(serial);
                  
         String as = "", texto = "";
         String destino =  dest ; //+"@escuelaing.edu.co"; //dest+"@escuelaing.edu.co"
         
          final String usr = usrs+"@escuelaing.edu.co";
      
         String smtp, mensaje = "";
           try {
            smtp = "smtp.office365.com";
           // MimeMultipart multipart = new MimeMultipart();
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", smtp);
            properties.put("mail.smtp.port", "587");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usr, pas);
                }
            });
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(usr));
            msg.setRecipients(Message.RecipientType.BCC, usr);
    

            msg.setRecipients(Message.RecipientType.BCC, destino);
            
            
            String tipo="";
            
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("1")){tipo="All in One";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("2")){tipo="Portatil";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("3")){tipo="Desktop";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("4")){tipo="Pantalla";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("5")){tipo="Teléfono";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("6")){tipo="Escaner";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("7")){tipo="Video Beam";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("8")){tipo="Switch";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("9")){tipo="Servidor";}
           if(infequipo.elementAt(10).toString().equalsIgnoreCase("10")){tipo="Televisor";}
          
            
                as = "Inventario - Cambio de responsable. "+serial ;
                texto = "\n\n\n Se informa el siguiente movimiento en el inventario: \n"
                        + "\n"
                        + "\n Serial Equipo: "+ serial + " \n  "
                        + " Placa:  "     + infequipo.elementAt(0) + " \n  "
                        + " Tipo:   "      + tipo + " \n  "
                        + " Marca:  "     + infequipo.elementAt(8) + " \n  "
                        + " Modelo: "    + infequipo.elementAt(9) + " \n  "
                        + " Usuario Anterior: "+ bd.ConsultaNombreCC(ccresponsable) + " \n  "
                        + " Usuario Nuevo:    "+ bd.ConsultaNombreCC(infequipo.elementAt(13).toString()) + " \n  "
                        + " Salon/Bodega/Área Anterior: "+ salonant + " \n  "
                        + " Salon/Bodega/Área Nuevo:    "+ infequipo.elementAt(14) + " \n  "
                        + " Observación:                "+ Obs + " \n  "
                        + "\n\n Favor proceder a la verificación y aprobación.  ";
            
            msg.setSubject(as);
             //msg.setSubject(as, "UTF-8"); //Nuevo tycho
            msg.setSentDate(new Date());           
            BodyPart msgBP = new MimeBodyPart();
            msgBP.setText(texto);
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(msgBP);              
            msg.setText(texto);
            //msg.setText(texto, "UTF-8"); //Nuevo tycho
            Transport.send(msg);
            mensaje = "ok";
        } catch (Exception e) {
            mensaje = "error:"  + usr + "/" + " ---- " + e.getMessage();
        }
       return mensaje;
      }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
