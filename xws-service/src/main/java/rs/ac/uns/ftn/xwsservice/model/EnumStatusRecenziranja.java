//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.03 at 10:34:01 PM CET 
//


package rs.ac.uns.ftn.xwsservice.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumStatusRecenziranja.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumStatusRecenziranja">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Prihvacen"/>
 *     &lt;enumeration value="Odbijen"/>
 *     &lt;enumeration value="Cekanje"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumStatusRecenziranja")
@XmlEnum
public enum EnumStatusRecenziranja {

    @XmlEnumValue("Prihvacen")
    PRIHVACEN("Prihvacen"),
    @XmlEnumValue("Odbijen")
    ODBIJEN("Odbijen"),
    @XmlEnumValue("Cekanje")
    CEKANJE("Cekanje");
    private final String value;

    EnumStatusRecenziranja(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumStatusRecenziranja fromValue(String v) {
        for (EnumStatusRecenziranja c: EnumStatusRecenziranja.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
