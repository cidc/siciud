package cidc.general.ws_coneccion_Bizagi;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class ConeccionDB_WS {

	
	String webServiceConnection = null;
	
//	final String EM= "http://200.69.103.29:22018/BizAgi-war/WebServices/EntityManagerSOA";
//	final String WF= "http://200.69.103.29:22018/BizAgi-war/WebServices/WorkflowEngineSOA";
	final String EM= "http://10.20.230.106:8080/BizAgi-war/WebServices/EntityManagerSOA";
	final String WF= "http://10.20.230.106:8080/BizAgi-war/WebServices/WorkflowEngineSOA";
	
	public void setConnectionEM() {
		webServiceConnection=EM;
	}
	
	public void setConnectionWF() {
		webServiceConnection=WF;
	}
	
	HttpResponse httpResponse;

	public String httpostConsultaEM (String consultaWS){
		String xmlbusqueda= null;
		
		try {
			HttpPost httppostWS = new HttpPost(webServiceConnection);
			httppostWS.setHeader("Content-Type", "application/soap+xml;charset=UTF-8");
			DefaultHttpClient httpClient = new DefaultHttpClient();
			try {
				httppostWS.setEntity(new StringEntity (consultaWS));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				httpResponse = httpClient.execute(httppostWS);
			}  catch (Exception e1) {
                if(e1 instanceof HttpException || e1 instanceof IOException ||
                        e1 instanceof InterruptedException  ) {
                   System.out.println("Error e1");
                     } else {
                       throw new RuntimeException(e1);    
                     }
               e1.printStackTrace();
              
           }

			System.out.println("Status Code " +
			        httpResponse.getStatusLine().getStatusCode());
		

			try {
				//System.out.println(EntityUtils.toString(httpResponse.getEntity()));
	            xmlbusqueda = StringEscapeUtils.unescapeXml(EntityUtils.toString(httpResponse.getEntity()));
	          //  System.out.println(StringEscapeUtils.unescapeXml(xmlbusqueda));
			}   catch (Exception e) {
               
                if(e instanceof ParseException || e instanceof IOException)
                {
                    System.out.println("Error e");
                      } else {
                        throw new RuntimeException(e);    
                      }
               
                e.printStackTrace();
            }
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlbusqueda;
	}
	


}
