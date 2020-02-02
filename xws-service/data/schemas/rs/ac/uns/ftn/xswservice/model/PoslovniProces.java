//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.03 at 12:08:45 AM CET 
//


package rs.ac.uns.ftn.xswservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="NaucniRadId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PropratnoPismoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Faza" type="{http://www.ftn.uns.ac.rs/xws/tim5}EnumFaza"/>
 *         &lt;element name="StatusRada" type="{http://www.ftn.uns.ac.rs/xws/tim5}EnumStatusRada"/>
 *         &lt;element name="Recenzenti">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Recenzent" type="{http://www.ftn.uns.ac.rs/xws/tim5}CTRecenzent" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
    "naucniRadId",
    "propratnoPismoId",
    "faza",
    "statusRada",
    "recenzenti"
})
@XmlRootElement(name = "PoslovniProces")
public class PoslovniProces {

    @XmlElement(name = "NaucniRadId", required = true)
    protected String naucniRadId;
    @XmlElement(name = "PropratnoPismoId", required = true)
    protected String propratnoPismoId;
    @XmlElement(name = "Faza", required = true)
    @XmlSchemaType(name = "string")
    protected EnumFaza faza;
    @XmlElement(name = "StatusRada", required = true)
    @XmlSchemaType(name = "string")
    protected EnumStatusRada statusRada;
    @XmlElement(name = "Recenzenti", required = true)
    protected PoslovniProces.Recenzenti recenzenti;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the naucniRadId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaucniRadId() {
        return naucniRadId;
    }

    /**
     * Sets the value of the naucniRadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaucniRadId(String value) {
        this.naucniRadId = value;
    }

    /**
     * Gets the value of the propratnoPismoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropratnoPismoId() {
        return propratnoPismoId;
    }

    /**
     * Sets the value of the propratnoPismoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropratnoPismoId(String value) {
        this.propratnoPismoId = value;
    }

    /**
     * Gets the value of the faza property.
     * 
     * @return
     *     possible object is
     *     {@link EnumFaza }
     *     
     */
    public EnumFaza getFaza() {
        return faza;
    }

    /**
     * Sets the value of the faza property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumFaza }
     *     
     */
    public void setFaza(EnumFaza value) {
        this.faza = value;
    }

    /**
     * Gets the value of the statusRada property.
     * 
     * @return
     *     possible object is
     *     {@link EnumStatusRada }
     *     
     */
    public EnumStatusRada getStatusRada() {
        return statusRada;
    }

    /**
     * Sets the value of the statusRada property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumStatusRada }
     *     
     */
    public void setStatusRada(EnumStatusRada value) {
        this.statusRada = value;
    }

    /**
     * Gets the value of the recenzenti property.
     * 
     * @return
     *     possible object is
     *     {@link PoslovniProces.Recenzenti }
     *     
     */
    public PoslovniProces.Recenzenti getRecenzenti() {
        return recenzenti;
    }

    /**
     * Sets the value of the recenzenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoslovniProces.Recenzenti }
     *     
     */
    public void setRecenzenti(PoslovniProces.Recenzenti value) {
        this.recenzenti = value;
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
     *         &lt;element name="Recenzent" type="{http://www.ftn.uns.ac.rs/xws/tim5}CTRecenzent" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "recenzent"
    })
    public static class Recenzenti {

        @XmlElement(name = "Recenzent", required = true)
        protected List<CTRecenzent> recenzent;

        /**
         * Gets the value of the recenzent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the recenzent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRecenzent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CTRecenzent }
         * 
         * 
         */
        public List<CTRecenzent> getRecenzent() {
            if (recenzent == null) {
                recenzent = new ArrayList<CTRecenzent>();
            }
            return this.recenzent;
        }

    }

}
