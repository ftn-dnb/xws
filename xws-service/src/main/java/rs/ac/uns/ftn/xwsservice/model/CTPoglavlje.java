//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.21 at 06:33:51 PM CET 
//


package rs.ac.uns.ftn.xwsservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CTPoglavlje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTPoglavlje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Naslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pasus" type="{http://www.ftn.uns.ac.rs/xws/tim5}CTPasus" maxOccurs="unbounded"/>
 *         &lt;element name="Poglavlje" type="{http://www.ftn.uns.ac.rs/xws/tim5}CTPoglavlje" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTPoglavlje", propOrder = {
    "naslov",
    "pasus",
    "poglavlje"
})
public class CTPoglavlje {

    @XmlElement(name = "Naslov", required = true)
    protected String naslov;
    @XmlElement(name = "Pasus", required = true)
    protected List<CTPasus> pasus;
    @XmlElement(name = "Poglavlje")
    protected CTPoglavlje poglavlje;

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the pasus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pasus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPasus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTPasus }
     * 
     * 
     */
    public List<CTPasus> getPasus() {
        if (pasus == null) {
            pasus = new ArrayList<CTPasus>();
        }
        return this.pasus;
    }

    /**
     * Gets the value of the poglavlje property.
     * 
     * @return
     *     possible object is
     *     {@link CTPoglavlje }
     *     
     */
    public CTPoglavlje getPoglavlje() {
        return poglavlje;
    }

    /**
     * Sets the value of the poglavlje property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTPoglavlje }
     *     
     */
    public void setPoglavlje(CTPoglavlje value) {
        this.poglavlje = value;
    }

}
