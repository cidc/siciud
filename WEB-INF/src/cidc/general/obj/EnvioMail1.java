package cidc.general.obj;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import cidc.general.servlet.ServletGeneral;

public class EnvioMail1 extends ServletGeneral{

	private String usuario="cidc@udistrital.edu.co";
	private String clave="cidc2007";
	private String de="Centro_Investigaciones_UD";
	private String smtpHost="mail.udistrital.edu.co";
	private String respaldo="cidc@udistrital.edu.co";

	private class Autenticador extends Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(usuario,clave);
		}
	}

	public boolean enviar(String[] destinatarios, String texto) {
		// TODO Auto-generated method stub
		boolean retorno=true;
		Session session = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env/mail/Session");
			session = (Session) envCtx.lookup("mail/Session");

		} catch (Exception ex) {
			System.out.println("lookup error");
			System.out.println( ex.getMessage());
		}


		Address []direcciones = new Address[destinatarios.length];
		try {
			for(int i=0;i<destinatarios.length;i++){
				direcciones[i]=new InternetAddress(destinatarios[i]);
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		try{
			Message mensaje=new MimeMessage(session);
			mensaje.setSubject("Motivo del mensaje");
			mensaje.setFrom(new InternetAddress(de));
			mensaje.addFrom(direcciones);
			mensaje.setRecipients(Message.RecipientType.TO,direcciones);
			mensaje.setText(texto);
			Transport.send(mensaje);
	//		System.out.println("mensaje enviado");
		}catch(MessagingException error){
			System.out.println("El mensaje no se pudo enviar por: "+error);
		}
		return retorno;
	}

}
