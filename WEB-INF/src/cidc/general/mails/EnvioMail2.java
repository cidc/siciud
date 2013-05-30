package cidc.general.mails;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnvioMail2 {

	private String usuario=null;
	private String clave=null;
	private String de=null;
	private String smtpHost="mail.udistrital.edu.co";
	private ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.listaCorreos");;

	private class Autenticador extends Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			//System.out.println("Autenticando a: "+usuario+" con clave: "+clave);
			return new PasswordAuthentication(usuario,clave);
		}
	}

	public EnvioMail2(String cuenta){
		//System.out.println("Bandera 9-1-1 "+cuenta);
		this.usuario=rb.getString(cuenta);
		//System.out.println("Bandera 9-1-2 "+usuario);
		this.clave=rb.getString(cuenta+"Key");
		//System.out.println("Bandera 9-1-3 "+clave);
		this.de="Centro Investigaciones SICIUD<"+usuario+">";
		//System.out.println("desde "+this.de);
	}

	public boolean enviar(String[] destinatarios, String asunto, String texto) throws MessagingException,AddressException{
		// TODO Auto-generated method stub
		boolean retorno=true;
		Address []direcciones = new Address[destinatarios.length];

		for(int i=0;i<destinatarios.length;i++){
			//////System.out.println("destino:"+destinatarios[i]);
			direcciones[i]=new InternetAddress(destinatarios[i]);
		}
		MimeMultipart multiParte = new MimeMultipart();
		MimeBodyPart msg = new MimeBodyPart();
		////System.out.println("Antes "+msg.getContentType());
		msg.setContent(texto,"text/html");
		////System.out.println("Despues "+msg.getContentType());
	//	msg.setHeader(,"html");
		multiParte.addBodyPart(msg);

	/*	BodyPart adjunto = new MimeBodyPart();
		adjunto.setDataHandler(new DataHandler(new FileDataSource("c:/pagina.html")));
		adjunto.setFileName("presupuesto.html");
		multiParte.addBodyPart(adjunto); */

		Properties propiedades=System.getProperties();
		propiedades.put("mail.smtp.host",smtpHost);
		propiedades.put("mail.smtp.auth","true");
		propiedades.put("mail.smtp.port","25");
		Authenticator identificador =new Autenticador();
		Session sesion=Session.getDefaultInstance(propiedades,identificador);
	//	sesion.setDebug(true);
		MimeMessage mensaje=new MimeMessage(sesion);
		mensaje.setSubject(asunto);
		mensaje.setFrom(new InternetAddress(de));
		mensaje.setSentDate(new java.util.Date());
	//	mensaje.addFrom(new InternetAddress(usuario));
		mensaje.setRecipients(Message.RecipientType.TO,direcciones);
	//	mensaje.setRecipients(Message.RecipientType.BCC,usuario);
		mensaje.setContent(multiParte);
		Transport.send(mensaje);
		//System.out.println("enviado");
		return retorno;
	}

	public static void main(String []args){
		EnvioMail2 e= new EnvioMail2("siciud");
		String [] lista={"gloriafer18@gmail.com","gloria_fer18@hotmail.com"};
		try {
			e.enviar(lista, "hola", "<b>tons que parce<b><h2>esta cosa ya funciona</h2>");
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
