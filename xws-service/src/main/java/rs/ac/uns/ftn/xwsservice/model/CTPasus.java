//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.24 at 02:43:33 PM CET 
//


package rs.ac.uns.ftn.xwsservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CTPasus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTPasus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="Slika">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                   &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IdReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Tabela">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Red" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Celija" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Lista">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Stavka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Vrsta" default="neuredjena">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="uredjena"/>
 *                       &lt;enumeration value="neuredjena"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Tekst">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="IdReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "CTPasus", propOrder = {
    "content"
})
public class CTPasus {

    @XmlElementRefs({
        @XmlElementRef(name = "Tekst", namespace = "http://www.ftn.uns.ac.rs/xws/tim5", type = JAXBElement.class),
        @XmlElementRef(name = "Tabela", namespace = "http://www.ftn.uns.ac.rs/xws/tim5", type = JAXBElement.class),
        @XmlElementRef(name = "Slika", namespace = "http://www.ftn.uns.ac.rs/xws/tim5", type = JAXBElement.class),
        @XmlElementRef(name = "Lista", namespace = "http://www.ftn.uns.ac.rs/xws/tim5", type = JAXBElement.class)
    })
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Tekst }{@code >}
     * {@link JAXBElement }{@code <}{@link Slika }{@code >}
     * {@link JAXBElement }{@code <}{@link Lista }{@code >}
     * {@link JAXBElement }{@code <}{@link Tabela }{@code >}
     * {@link String }
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
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
     *         &lt;element name="Stavka" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Vrsta" default="neuredjena">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="uredjena"/>
     *             &lt;enumeration value="neuredjena"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "stavka"
    })
    public static class Lista {

        @XmlElement(name = "Stavka", required = true)
        protected String stavka;
        @XmlAttribute(name = "Vrsta")
        protected String vrsta;

        /**
         * Gets the value of the stavka property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStavka() {
            return stavka;
        }

        /**
         * Sets the value of the stavka property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStavka(String value) {
            this.stavka = value;
        }

        /**
         * Gets the value of the vrsta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVrsta() {
            if (vrsta == null) {
                return "neuredjena";
            } else {
                return vrsta;
            }
        }

        /**
         * Sets the value of the vrsta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVrsta(String value) {
            this.vrsta = value;
        }

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
     *         &lt;element name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *         &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="IdReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "sadrzaj",
        "naziv",
        "idReference"
    })
    public static class Slika {

        @XmlElement(name = "Sadrzaj", required = true)
        protected byte[] sadrzaj;
        @XmlElement(name = "Naziv", required = true)
        protected String naziv;
        @XmlElement(name = "IdReference", required = true)
        protected String idReference;

        /**
         * Gets the value of the sadrzaj property.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getSadrzaj() {
            return sadrzaj;
        }

        /**
         * Sets the value of the sadrzaj property.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setSadrzaj(byte[] value) {
            this.sadrzaj = value;
        }

        /**
         * Gets the value of the naziv property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNaziv() {
            return naziv;
        }

        /**
         * Sets the value of the naziv property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNaziv(String value) {
            this.naziv = value;
        }

        /**
         * Gets the value of the idReference property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdReference() {
            return idReference;
        }

        /**
         * Sets the value of the idReference property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdReference(String value) {
            this.idReference = value;
        }

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
     *         &lt;element name="Red" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Celija" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "red"
    })
    public static class Tabela {

        @XmlElement(name = "Red", required = true)
        protected List<Red> red;

        /**
         * Gets the value of the red property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the red property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRed().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Red }
         * 
         * 
         */
        public List<Red> getRed() {
            if (red == null) {
                red = new ArrayList<Red>();
            }
            return this.red;
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
         *         &lt;element name="Celija" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
            "celija"
        })
        public static class Red {

            @XmlElement(name = "Celija", required = true)
            protected List<String> celija;

            /**
             * Gets the value of the celija property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the celija property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCelija().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getCelija() {
                if (celija == null) {
                    celija = new ArrayList<String>();
                }
                return this.celija;
            }

        }

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
     *         &lt;element name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="IdReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "sadrzaj",
        "idReference"
    })
    public static class Tekst {

        @XmlElement(name = "Sadrzaj", required = true)
        protected String sadrzaj;
        @XmlElement(name = "IdReference", required = true)
        protected String idReference;

        /**
         * Gets the value of the sadrzaj property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSadrzaj() {
            return sadrzaj;
        }

        /**
         * Sets the value of the sadrzaj property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSadrzaj(String value) {
            this.sadrzaj = value;
        }

        /**
         * Gets the value of the idReference property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdReference() {
            return idReference;
        }

        /**
         * Sets the value of the idReference property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdReference(String value) {
            this.idReference = value;
        }

    }

}
