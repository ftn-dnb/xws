//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.02 at 09:56:38 PM CET 
//


package rs.ac.uns.ftn.xswservice.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumFaza.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumFaza">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ZaReviziju"/>
 *     &lt;enumeration value="ZaRecenziju"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumFaza")
@XmlEnum
public enum EnumFaza {

    @XmlEnumValue("ZaReviziju")
    ZA_REVIZIJU("ZaReviziju"),
    @XmlEnumValue("ZaRecenziju")
    ZA_RECENZIJU("ZaRecenziju");
    private final String value;

    EnumFaza(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumFaza fromValue(String v) {
        for (EnumFaza c: EnumFaza.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}