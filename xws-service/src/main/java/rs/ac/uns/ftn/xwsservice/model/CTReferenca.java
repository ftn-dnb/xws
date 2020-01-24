//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.24 at 02:01:02 PM CET 
//


package rs.ac.uns.ftn.xwsservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CTReferenca complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTReferenca">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NazivRada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="URLRada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence maxOccurs="unbounded">
 *           &lt;element name="Autor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTReferenca", propOrder = {
    "nazivRada",
    "urlRada",
    "autor"
})
public class CTReferenca {

    @XmlElement(name = "NazivRada", required = true)
    protected String nazivRada;
    @XmlElement(name = "URLRada", required = true)
    protected String urlRada;
    @XmlElement(name = "Autor", required = true)
    protected List<String> autor;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the nazivRada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivRada() {
        return nazivRada;
    }

    /**
     * Sets the value of the nazivRada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivRada(String value) {
        this.nazivRada = value;
    }

    /**
     * Gets the value of the urlRada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURLRada() {
        return urlRada;
    }

    /**
     * Sets the value of the urlRada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURLRada(String value) {
        this.urlRada = value;
    }

    /**
     * Gets the value of the autor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAutor() {
        if (autor == null) {
            autor = new ArrayList<String>();
        }
        return this.autor;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
