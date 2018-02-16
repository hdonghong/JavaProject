
package pers.hdh.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pers.hdh.webservice package. 
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

    private final static QName _GetWeatherInfo_QNAME = new QName("http://webservice.hdh.pers/", "getWeatherInfo");
    private final static QName _GetWeatherInfoResponse_QNAME = new QName("http://webservice.hdh.pers/", "getWeatherInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pers.hdh.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWeatherInfo }
     * 
     */
    public GetWeatherInfo createGetWeatherInfo() {
        return new GetWeatherInfo();
    }

    /**
     * Create an instance of {@link GetWeatherInfoResponse }
     * 
     */
    public GetWeatherInfoResponse createGetWeatherInfoResponse() {
        return new GetWeatherInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWeatherInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.hdh.pers/", name = "getWeatherInfo")
    public JAXBElement<GetWeatherInfo> createGetWeatherInfo(GetWeatherInfo value) {
        return new JAXBElement<GetWeatherInfo>(_GetWeatherInfo_QNAME, GetWeatherInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWeatherInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.hdh.pers/", name = "getWeatherInfoResponse")
    public JAXBElement<GetWeatherInfoResponse> createGetWeatherInfoResponse(GetWeatherInfoResponse value) {
        return new JAXBElement<GetWeatherInfoResponse>(_GetWeatherInfoResponse_QNAME, GetWeatherInfoResponse.class, null, value);
    }

}
