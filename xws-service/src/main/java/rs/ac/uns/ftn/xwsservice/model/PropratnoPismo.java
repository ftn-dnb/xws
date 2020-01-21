//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.21 at 06:33:51 PM CET 
//


package rs.ac.uns.ftn.xwsservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Posaljilac" type="{http://www.ftn.uns.ac.rs/xws/tim5}CTAutor"/>
 *         &lt;element name="Primalac" type="{http://www.ftn.uns.ac.rs/xws/tim5}CTAutor"/>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SadrzajPisma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Potpis" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
@XmlType(name = "", propOrder = {
    "posaljilac",
    "primalac",
    "datum",
    "sadrzajPisma",
    "potpis"
})
@XmlRootElement(name = "PropratnoPismo")
public class PropratnoPismo {

    @XmlElement(name = "Posaljilac", required = true)
    protected CTAutor posaljilac;
    @XmlElement(name = "Primalac", required = true)
    protected CTAutor primalac;
    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "SadrzajPisma", required = true)
    protected String sadrzajPisma;
    @XmlElement(name = "Potpis", required = true)
    protected byte[] potpis;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the posaljilac property.
     * 
     * @return
     *     possible object is
     *     {@link CTAutor }
     *     
     */
    public CTAutor getPosaljilac() {
        return posaljilac;
    }

    /**
     * Sets the value of the posaljilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAutor }
     *     
     */
    public void setPosaljilac(CTAutor value) {
        this.posaljilac = value;
    }

    /**
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link CTAutor }
     *     
     */
    public CTAutor getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTAutor }
     *     
     */
    public void setPrimalac(CTAutor value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the sadrzajPisma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSadrzajPisma() {
        return sadrzajPisma;
    }

    /**
     * Sets the value of the sadrzajPisma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSadrzajPisma(String value) {
        this.sadrzajPisma = value;
    }

    /**
     * Gets the value of the potpis property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPotpis() {
        return potpis;
    }

    /**
     * Sets the value of the potpis property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPotpis(byte[] value) {
        this.potpis = value;
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
