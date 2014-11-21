
package cidc.bizagi.soa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bizagi.soa package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PingResponse_QNAME = new QName("http://SOA.BizAgi/", "pingResponse");
    private final static QName _AssignActivityAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "assignActivityAsStringResponse");
    private final static QName _RevokeCaseAccessAsString_QNAME = new QName("http://SOA.BizAgi/", "revokeCaseAccessAsString");
    private final static QName _GetCasesAsString_QNAME = new QName("http://SOA.BizAgi/", "getCasesAsString");
    private final static QName _GetWorkflowClassesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "getWorkflowClassesAsStringResponse");
    private final static QName _CreateCasesAsString_QNAME = new QName("http://SOA.BizAgi/", "createCasesAsString");
    private final static QName _SetEventAsString_QNAME = new QName("http://SOA.BizAgi/", "setEventAsString");
    private final static QName _AbortCasesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "abortCasesAsStringResponse");
    private final static QName _GetAssignationLogAsString_QNAME = new QName("http://SOA.BizAgi/", "getAssignationLogAsString");
    private final static QName _GetClosedActivitiesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "getClosedActivitiesAsStringResponse");
    private final static QName _GrantCaseAccessAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "grantCaseAccessAsStringResponse");
    private final static QName _GetEventsAsString_QNAME = new QName("http://SOA.BizAgi/", "getEventsAsString");
    private final static QName _GetWorkflowClassesAsString_QNAME = new QName("http://SOA.BizAgi/", "getWorkflowClassesAsString");
    private final static QName _GetActivitiesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "getActivitiesAsStringResponse");
    private final static QName _GetCategoriesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "getCategoriesAsStringResponse");
    private final static QName _ResumeCasesAsString_QNAME = new QName("http://SOA.BizAgi/", "resumeCasesAsString");
    private final static QName _CreateCasesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "createCasesAsStringResponse");
    private final static QName _AbortCasesAsString_QNAME = new QName("http://SOA.BizAgi/", "abortCasesAsString");
    private final static QName _SetEventAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "setEventAsStringResponse");
    private final static QName _GetCasesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "getCasesAsStringResponse");
    private final static QName _SaveActivityAsString_QNAME = new QName("http://SOA.BizAgi/", "saveActivityAsString");
    private final static QName _SuspendCasesAsString_QNAME = new QName("http://SOA.BizAgi/", "suspendCasesAsString");
    private final static QName _AssignActivityAsString_QNAME = new QName("http://SOA.BizAgi/", "assignActivityAsString");
    private final static QName _GetCategoriesAsString_QNAME = new QName("http://SOA.BizAgi/", "getCategoriesAsString");
    private final static QName _PerformActivityAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "performActivityAsStringResponse");
    private final static QName _PerformActivityAsString_QNAME = new QName("http://SOA.BizAgi/", "performActivityAsString");
    private final static QName _RevokeCaseAccessAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "revokeCaseAccessAsStringResponse");
    private final static QName _GetActivitiesAsString_QNAME = new QName("http://SOA.BizAgi/", "getActivitiesAsString");
    private final static QName _ResumeCasesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "resumeCasesAsStringResponse");
    private final static QName _GetClosedActivitiesAsString_QNAME = new QName("http://SOA.BizAgi/", "getClosedActivitiesAsString");
    private final static QName _GetEventsAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "getEventsAsStringResponse");
    private final static QName _Ping_QNAME = new QName("http://SOA.BizAgi/", "ping");
    private final static QName _SaveActivityAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "saveActivityAsStringResponse");
    private final static QName _GetAssignationLogAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "getAssignationLogAsStringResponse");
    private final static QName _SuspendCasesAsStringResponse_QNAME = new QName("http://SOA.BizAgi/", "suspendCasesAsStringResponse");
    private final static QName _GrantCaseAccessAsString_QNAME = new QName("http://SOA.BizAgi/", "grantCaseAccessAsString");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bizagi.soa
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAssignationLogAsString }
     * 
     */
    public GetAssignationLogAsString createGetAssignationLogAsString() {
        return new GetAssignationLogAsString();
    }

    /**
     * Create an instance of {@link SetEventAsStringResponse }
     * 
     */
    public SetEventAsStringResponse createSetEventAsStringResponse() {
        return new SetEventAsStringResponse();
    }

    /**
     * Create an instance of {@link GrantCaseAccessAsString }
     * 
     */
    public GrantCaseAccessAsString createGrantCaseAccessAsString() {
        return new GrantCaseAccessAsString();
    }

    /**
     * Create an instance of {@link GetCasesAsStringResponse }
     * 
     */
    public GetCasesAsStringResponse createGetCasesAsStringResponse() {
        return new GetCasesAsStringResponse();
    }

    /**
     * Create an instance of {@link GrantCaseAccessAsStringResponse }
     * 
     */
    public GrantCaseAccessAsStringResponse createGrantCaseAccessAsStringResponse() {
        return new GrantCaseAccessAsStringResponse();
    }

    /**
     * Create an instance of {@link SaveActivityAsStringResponse }
     * 
     */
    public SaveActivityAsStringResponse createSaveActivityAsStringResponse() {
        return new SaveActivityAsStringResponse();
    }

    /**
     * Create an instance of {@link CreateCasesAsStringResponse }
     * 
     */
    public CreateCasesAsStringResponse createCreateCasesAsStringResponse() {
        return new CreateCasesAsStringResponse();
    }

    /**
     * Create an instance of {@link GetActivitiesAsStringResponse }
     * 
     */
    public GetActivitiesAsStringResponse createGetActivitiesAsStringResponse() {
        return new GetActivitiesAsStringResponse();
    }

    /**
     * Create an instance of {@link AbortCasesAsStringResponse }
     * 
     */
    public AbortCasesAsStringResponse createAbortCasesAsStringResponse() {
        return new AbortCasesAsStringResponse();
    }

    /**
     * Create an instance of {@link AbortCasesAsString }
     * 
     */
    public AbortCasesAsString createAbortCasesAsString() {
        return new AbortCasesAsString();
    }

    /**
     * Create an instance of {@link SuspendCasesAsStringResponse }
     * 
     */
    public SuspendCasesAsStringResponse createSuspendCasesAsStringResponse() {
        return new SuspendCasesAsStringResponse();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link SuspendCasesAsString }
     * 
     */
    public SuspendCasesAsString createSuspendCasesAsString() {
        return new SuspendCasesAsString();
    }

    /**
     * Create an instance of {@link GetAssignationLogAsStringResponse }
     * 
     */
    public GetAssignationLogAsStringResponse createGetAssignationLogAsStringResponse() {
        return new GetAssignationLogAsStringResponse();
    }

    /**
     * Create an instance of {@link ResumeCasesAsStringResponse }
     * 
     */
    public ResumeCasesAsStringResponse createResumeCasesAsStringResponse() {
        return new ResumeCasesAsStringResponse();
    }

    /**
     * Create an instance of {@link GetWorkflowClassesAsString }
     * 
     */
    public GetWorkflowClassesAsString createGetWorkflowClassesAsString() {
        return new GetWorkflowClassesAsString();
    }

    /**
     * Create an instance of {@link GetEventsAsString }
     * 
     */
    public GetEventsAsString createGetEventsAsString() {
        return new GetEventsAsString();
    }

    /**
     * Create an instance of {@link GetClosedActivitiesAsStringResponse }
     * 
     */
    public GetClosedActivitiesAsStringResponse createGetClosedActivitiesAsStringResponse() {
        return new GetClosedActivitiesAsStringResponse();
    }

    /**
     * Create an instance of {@link GetCategoriesAsString }
     * 
     */
    public GetCategoriesAsString createGetCategoriesAsString() {
        return new GetCategoriesAsString();
    }

    /**
     * Create an instance of {@link AssignActivityAsStringResponse }
     * 
     */
    public AssignActivityAsStringResponse createAssignActivityAsStringResponse() {
        return new AssignActivityAsStringResponse();
    }

    /**
     * Create an instance of {@link SaveActivityAsString }
     * 
     */
    public SaveActivityAsString createSaveActivityAsString() {
        return new SaveActivityAsString();
    }

    /**
     * Create an instance of {@link PerformActivityAsStringResponse }
     * 
     */
    public PerformActivityAsStringResponse createPerformActivityAsStringResponse() {
        return new PerformActivityAsStringResponse();
    }

    /**
     * Create an instance of {@link PerformActivityAsString }
     * 
     */
    public PerformActivityAsString createPerformActivityAsString() {
        return new PerformActivityAsString();
    }

    /**
     * Create an instance of {@link ResumeCasesAsString }
     * 
     */
    public ResumeCasesAsString createResumeCasesAsString() {
        return new ResumeCasesAsString();
    }

    /**
     * Create an instance of {@link GetCasesAsString }
     * 
     */
    public GetCasesAsString createGetCasesAsString() {
        return new GetCasesAsString();
    }

    /**
     * Create an instance of {@link GetWorkflowClassesAsStringResponse }
     * 
     */
    public GetWorkflowClassesAsStringResponse createGetWorkflowClassesAsStringResponse() {
        return new GetWorkflowClassesAsStringResponse();
    }

    /**
     * Create an instance of {@link RevokeCaseAccessAsStringResponse }
     * 
     */
    public RevokeCaseAccessAsStringResponse createRevokeCaseAccessAsStringResponse() {
        return new RevokeCaseAccessAsStringResponse();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link CreateCasesAsString }
     * 
     */
    public CreateCasesAsString createCreateCasesAsString() {
        return new CreateCasesAsString();
    }

    /**
     * Create an instance of {@link AssignActivityAsString }
     * 
     */
    public AssignActivityAsString createAssignActivityAsString() {
        return new AssignActivityAsString();
    }

    /**
     * Create an instance of {@link GetActivitiesAsString }
     * 
     */
    public GetActivitiesAsString createGetActivitiesAsString() {
        return new GetActivitiesAsString();
    }

    /**
     * Create an instance of {@link SetEventAsString }
     * 
     */
    public SetEventAsString createSetEventAsString() {
        return new SetEventAsString();
    }

    /**
     * Create an instance of {@link GetClosedActivitiesAsString }
     * 
     */
    public GetClosedActivitiesAsString createGetClosedActivitiesAsString() {
        return new GetClosedActivitiesAsString();
    }

    /**
     * Create an instance of {@link GetCategoriesAsStringResponse }
     * 
     */
    public GetCategoriesAsStringResponse createGetCategoriesAsStringResponse() {
        return new GetCategoriesAsStringResponse();
    }

    /**
     * Create an instance of {@link RevokeCaseAccessAsString }
     * 
     */
    public RevokeCaseAccessAsString createRevokeCaseAccessAsString() {
        return new RevokeCaseAccessAsString();
    }

    /**
     * Create an instance of {@link GetEventsAsStringResponse }
     * 
     */
    public GetEventsAsStringResponse createGetEventsAsStringResponse() {
        return new GetEventsAsStringResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignActivityAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "assignActivityAsStringResponse")
    public JAXBElement<AssignActivityAsStringResponse> createAssignActivityAsStringResponse(AssignActivityAsStringResponse value) {
        return new JAXBElement<AssignActivityAsStringResponse>(_AssignActivityAsStringResponse_QNAME, AssignActivityAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevokeCaseAccessAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "revokeCaseAccessAsString")
    public JAXBElement<RevokeCaseAccessAsString> createRevokeCaseAccessAsString(RevokeCaseAccessAsString value) {
        return new JAXBElement<RevokeCaseAccessAsString>(_RevokeCaseAccessAsString_QNAME, RevokeCaseAccessAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCasesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getCasesAsString")
    public JAXBElement<GetCasesAsString> createGetCasesAsString(GetCasesAsString value) {
        return new JAXBElement<GetCasesAsString>(_GetCasesAsString_QNAME, GetCasesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkflowClassesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getWorkflowClassesAsStringResponse")
    public JAXBElement<GetWorkflowClassesAsStringResponse> createGetWorkflowClassesAsStringResponse(GetWorkflowClassesAsStringResponse value) {
        return new JAXBElement<GetWorkflowClassesAsStringResponse>(_GetWorkflowClassesAsStringResponse_QNAME, GetWorkflowClassesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCasesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "createCasesAsString")
    public JAXBElement<CreateCasesAsString> createCreateCasesAsString(CreateCasesAsString value) {
        return new JAXBElement<CreateCasesAsString>(_CreateCasesAsString_QNAME, CreateCasesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetEventAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "setEventAsString")
    public JAXBElement<SetEventAsString> createSetEventAsString(SetEventAsString value) {
        return new JAXBElement<SetEventAsString>(_SetEventAsString_QNAME, SetEventAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbortCasesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "abortCasesAsStringResponse")
    public JAXBElement<AbortCasesAsStringResponse> createAbortCasesAsStringResponse(AbortCasesAsStringResponse value) {
        return new JAXBElement<AbortCasesAsStringResponse>(_AbortCasesAsStringResponse_QNAME, AbortCasesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssignationLogAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getAssignationLogAsString")
    public JAXBElement<GetAssignationLogAsString> createGetAssignationLogAsString(GetAssignationLogAsString value) {
        return new JAXBElement<GetAssignationLogAsString>(_GetAssignationLogAsString_QNAME, GetAssignationLogAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClosedActivitiesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getClosedActivitiesAsStringResponse")
    public JAXBElement<GetClosedActivitiesAsStringResponse> createGetClosedActivitiesAsStringResponse(GetClosedActivitiesAsStringResponse value) {
        return new JAXBElement<GetClosedActivitiesAsStringResponse>(_GetClosedActivitiesAsStringResponse_QNAME, GetClosedActivitiesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantCaseAccessAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "grantCaseAccessAsStringResponse")
    public JAXBElement<GrantCaseAccessAsStringResponse> createGrantCaseAccessAsStringResponse(GrantCaseAccessAsStringResponse value) {
        return new JAXBElement<GrantCaseAccessAsStringResponse>(_GrantCaseAccessAsStringResponse_QNAME, GrantCaseAccessAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getEventsAsString")
    public JAXBElement<GetEventsAsString> createGetEventsAsString(GetEventsAsString value) {
        return new JAXBElement<GetEventsAsString>(_GetEventsAsString_QNAME, GetEventsAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkflowClassesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getWorkflowClassesAsString")
    public JAXBElement<GetWorkflowClassesAsString> createGetWorkflowClassesAsString(GetWorkflowClassesAsString value) {
        return new JAXBElement<GetWorkflowClassesAsString>(_GetWorkflowClassesAsString_QNAME, GetWorkflowClassesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActivitiesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getActivitiesAsStringResponse")
    public JAXBElement<GetActivitiesAsStringResponse> createGetActivitiesAsStringResponse(GetActivitiesAsStringResponse value) {
        return new JAXBElement<GetActivitiesAsStringResponse>(_GetActivitiesAsStringResponse_QNAME, GetActivitiesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategoriesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getCategoriesAsStringResponse")
    public JAXBElement<GetCategoriesAsStringResponse> createGetCategoriesAsStringResponse(GetCategoriesAsStringResponse value) {
        return new JAXBElement<GetCategoriesAsStringResponse>(_GetCategoriesAsStringResponse_QNAME, GetCategoriesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResumeCasesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "resumeCasesAsString")
    public JAXBElement<ResumeCasesAsString> createResumeCasesAsString(ResumeCasesAsString value) {
        return new JAXBElement<ResumeCasesAsString>(_ResumeCasesAsString_QNAME, ResumeCasesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCasesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "createCasesAsStringResponse")
    public JAXBElement<CreateCasesAsStringResponse> createCreateCasesAsStringResponse(CreateCasesAsStringResponse value) {
        return new JAXBElement<CreateCasesAsStringResponse>(_CreateCasesAsStringResponse_QNAME, CreateCasesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbortCasesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "abortCasesAsString")
    public JAXBElement<AbortCasesAsString> createAbortCasesAsString(AbortCasesAsString value) {
        return new JAXBElement<AbortCasesAsString>(_AbortCasesAsString_QNAME, AbortCasesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetEventAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "setEventAsStringResponse")
    public JAXBElement<SetEventAsStringResponse> createSetEventAsStringResponse(SetEventAsStringResponse value) {
        return new JAXBElement<SetEventAsStringResponse>(_SetEventAsStringResponse_QNAME, SetEventAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCasesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getCasesAsStringResponse")
    public JAXBElement<GetCasesAsStringResponse> createGetCasesAsStringResponse(GetCasesAsStringResponse value) {
        return new JAXBElement<GetCasesAsStringResponse>(_GetCasesAsStringResponse_QNAME, GetCasesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveActivityAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "saveActivityAsString")
    public JAXBElement<SaveActivityAsString> createSaveActivityAsString(SaveActivityAsString value) {
        return new JAXBElement<SaveActivityAsString>(_SaveActivityAsString_QNAME, SaveActivityAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuspendCasesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "suspendCasesAsString")
    public JAXBElement<SuspendCasesAsString> createSuspendCasesAsString(SuspendCasesAsString value) {
        return new JAXBElement<SuspendCasesAsString>(_SuspendCasesAsString_QNAME, SuspendCasesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignActivityAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "assignActivityAsString")
    public JAXBElement<AssignActivityAsString> createAssignActivityAsString(AssignActivityAsString value) {
        return new JAXBElement<AssignActivityAsString>(_AssignActivityAsString_QNAME, AssignActivityAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategoriesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getCategoriesAsString")
    public JAXBElement<GetCategoriesAsString> createGetCategoriesAsString(GetCategoriesAsString value) {
        return new JAXBElement<GetCategoriesAsString>(_GetCategoriesAsString_QNAME, GetCategoriesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformActivityAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "performActivityAsStringResponse")
    public JAXBElement<PerformActivityAsStringResponse> createPerformActivityAsStringResponse(PerformActivityAsStringResponse value) {
        return new JAXBElement<PerformActivityAsStringResponse>(_PerformActivityAsStringResponse_QNAME, PerformActivityAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformActivityAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "performActivityAsString")
    public JAXBElement<PerformActivityAsString> createPerformActivityAsString(PerformActivityAsString value) {
        return new JAXBElement<PerformActivityAsString>(_PerformActivityAsString_QNAME, PerformActivityAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevokeCaseAccessAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "revokeCaseAccessAsStringResponse")
    public JAXBElement<RevokeCaseAccessAsStringResponse> createRevokeCaseAccessAsStringResponse(RevokeCaseAccessAsStringResponse value) {
        return new JAXBElement<RevokeCaseAccessAsStringResponse>(_RevokeCaseAccessAsStringResponse_QNAME, RevokeCaseAccessAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActivitiesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getActivitiesAsString")
    public JAXBElement<GetActivitiesAsString> createGetActivitiesAsString(GetActivitiesAsString value) {
        return new JAXBElement<GetActivitiesAsString>(_GetActivitiesAsString_QNAME, GetActivitiesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResumeCasesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "resumeCasesAsStringResponse")
    public JAXBElement<ResumeCasesAsStringResponse> createResumeCasesAsStringResponse(ResumeCasesAsStringResponse value) {
        return new JAXBElement<ResumeCasesAsStringResponse>(_ResumeCasesAsStringResponse_QNAME, ResumeCasesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClosedActivitiesAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getClosedActivitiesAsString")
    public JAXBElement<GetClosedActivitiesAsString> createGetClosedActivitiesAsString(GetClosedActivitiesAsString value) {
        return new JAXBElement<GetClosedActivitiesAsString>(_GetClosedActivitiesAsString_QNAME, GetClosedActivitiesAsString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getEventsAsStringResponse")
    public JAXBElement<GetEventsAsStringResponse> createGetEventsAsStringResponse(GetEventsAsStringResponse value) {
        return new JAXBElement<GetEventsAsStringResponse>(_GetEventsAsStringResponse_QNAME, GetEventsAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveActivityAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "saveActivityAsStringResponse")
    public JAXBElement<SaveActivityAsStringResponse> createSaveActivityAsStringResponse(SaveActivityAsStringResponse value) {
        return new JAXBElement<SaveActivityAsStringResponse>(_SaveActivityAsStringResponse_QNAME, SaveActivityAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssignationLogAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "getAssignationLogAsStringResponse")
    public JAXBElement<GetAssignationLogAsStringResponse> createGetAssignationLogAsStringResponse(GetAssignationLogAsStringResponse value) {
        return new JAXBElement<GetAssignationLogAsStringResponse>(_GetAssignationLogAsStringResponse_QNAME, GetAssignationLogAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuspendCasesAsStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "suspendCasesAsStringResponse")
    public JAXBElement<SuspendCasesAsStringResponse> createSuspendCasesAsStringResponse(SuspendCasesAsStringResponse value) {
        return new JAXBElement<SuspendCasesAsStringResponse>(_SuspendCasesAsStringResponse_QNAME, SuspendCasesAsStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantCaseAccessAsString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOA.BizAgi/", name = "grantCaseAccessAsString")
    public JAXBElement<GrantCaseAccessAsString> createGrantCaseAccessAsString(GrantCaseAccessAsString value) {
        return new JAXBElement<GrantCaseAccessAsString>(_GrantCaseAccessAsString_QNAME, GrantCaseAccessAsString.class, null, value);
    }

}