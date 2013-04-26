package cidc.general;

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

	private ResourceBundle rb=ResourceBundle.getBundle("cidc.general.conect");
	private String usuario="cidc@udistrital.edu.co";
	private String clave=rb.getString("siciudKey");
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
		Address []direcciones = new Address[destinatarios.length];
		try {
			for(int i=0;i<destinatarios.length;i++){
				direcciones[i]=new InternetAddress(destinatarios[i]);
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Properties propiedades=System.getProperties();
		propiedades.put("mail.smtp.host",smtpHost);
		propiedades.put("mail.smtp.auth","true");
	//	propiedades.put("mail.smtp.port","465");
		Authenticator identificador =new Autenticador();
		Session sesion=Session.getDefaultInstance(propiedades,identificador);


		try{
			Message mensaje=new MimeMessage(sesion);
			mensaje.setSubject("Motivo del mensaje");
			mensaje.setFrom(new InternetAddress(de));
			mensaje.addFrom(direcciones);
			mensaje.setRecipients(Message.RecipientType.TO,direcciones);
			mensaje.setText(texto);
			Transport.send(mensaje);
		}catch(MessagingException error){
			System.out.println("El mensaje no se pudo enviar por: "+error);
		}
		return retorno;
	}

}
