
package cidc.bizagi.soa;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "WorkflowEngineSOAImplService", targetNamespace = "http://SOA.BizAgi/", wsdlLocation = "http://10.20.230.106:8080/BizAgi-war/WebServices/WorkflowEngineSOA?wsdl")
public class WorkflowEngineSOAImplService
    extends Service
{

    private final static URL WORKFLOWENGINESOAIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(cidc.bizagi.soa.WorkflowEngineSOAImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = cidc.bizagi.soa.WorkflowEngineSOAImplService.class.getResource(".");
            url = new URL(baseUrl, "http://10.20.230.106:8080/BizAgi-war/WebServices/WorkflowEngineSOA?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://10.20.230.106:8080/BizAgi-war/WebServices/WorkflowEngineSOA?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WORKFLOWENGINESOAIMPLSERVICE_WSDL_LOCATION = url;
    }

    public WorkflowEngineSOAImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WorkflowEngineSOAImplService() {
        super(WORKFLOWENGINESOAIMPLSERVICE_WSDL_LOCATION, new QName("http://SOA.BizAgi/", "WorkflowEngineSOAImplService"));
    }

    /**
     * 
     * @return
     *     returns WorkflowEngineSOA
     */
    @WebEndpoint(name = "WorkflowEngineSOAImplPort")
    public WorkflowEngineSOA getWorkflowEngineSOAImplPort() {
        return super.getPort(new QName("http://SOA.BizAgi/", "WorkflowEngineSOAImplPort"), WorkflowEngineSOA.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WorkflowEngineSOA
     */
    @WebEndpoint(name = "WorkflowEngineSOAImplPort")
    public WorkflowEngineSOA getWorkflowEngineSOAImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://SOA.BizAgi/", "WorkflowEngineSOAImplPort"), WorkflowEngineSOA.class, features);
    }

}
