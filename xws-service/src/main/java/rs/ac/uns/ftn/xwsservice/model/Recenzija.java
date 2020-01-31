//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.31 at 05:38:46 PM CET 
//


package rs.ac.uns.ftn.xwsservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Komentar" type="{http://www.ftn.uns.ac.rs/xws/tim5}CTKomentar" maxOccurs="unbounded"/>
 *         &lt;element name="Preporuka">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="PRIHVATI"/>
 *               &lt;enumeration value="ODBIJ"/>
 *               &lt;enumeration value="MANJE_REVIZIJE"/>
 *               &lt;enumeration value="VECE_REVIZIJE"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "komentar",
    "preporuka"
})
@XmlRootElement(name = "Recenzija")
public class Recenzija {

    @XmlElement(name = "Komentar", required = true)
    protected List<CTKomentar> komentar;
    @XmlElement(name = "Preporuka", required = true)
    protected String preporuka;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the komentar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the komentar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKomentar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTKomentar }
     * 
     * 
     */
    public List<CTKomentar> getKomentar() {
        if (komentar == null) {
            komentar = new ArrayList<CTKomentar>();
        }
        return this.komentar;
    }

    /**
     * Gets the value of the preporuka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreporuka() {
        return preporuka;
    }

    /**
     * Sets the value of the preporuka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreporuka(String value) {
        this.preporuka = value;
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
