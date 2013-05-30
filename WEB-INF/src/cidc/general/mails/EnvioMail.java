package cidc.general.mails;

import java.util.Properties;
import java.util.ResourceBundle;

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

public class EnvioMail {

	private String usuario=null;
	private String clave=null;
	private String de=null;
	private String smtpHost="mail.udistrital.edu.co";
	private ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.listaCorreos");;

	private class Autenticador extends Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(usuario,clave);
		}
	}

	public EnvioMail(String cuenta){
		this.usuario=rb.getString(cuenta);
		this.clave=rb.getString(cuenta+"Key");
		this.de="Centro Investigaciones SICIUD<"+usuario+">";
	}

	public boolean enviar(String[] destinatarios, String asunto, String texto) throws MessagingException,AddressException{
		// TODO Auto-generated method stub
		boolean retorno=true;
		Address []direcciones = new Address[destinatarios.length];

		for(int i=0;i<destinatarios.length;i++){
			direcciones[i]=new InternetAddress(destinatarios[i]);
		}
		Properties propiedades=System.getProperties();
		propiedades.put("mail.smtp.host",smtpHost);
		propiedades.put("mail.smtp.auth","true");
		Authenticator identificador =new Autenticador();
		Session sesion=Session.getInstance(propiedades,identificador);

		MimeMessage mensaje=new MimeMessage(sesion);
		mensaje.setSubject(asunto);
		mensaje.setFrom(new InternetAddress(de));
		mensaje.setSentDate(new java.util.Date());
		mensaje.setRecipients(Message.RecipientType.TO,direcciones);
		mensaje.setRecipients(Message.RecipientType.BCC,usuario);
		mensaje.setText(texto,"ISO-8859-1","html");
		Transport.send(mensaje);
		return retorno;
	}
}
