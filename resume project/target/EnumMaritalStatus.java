//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.17 at 08:17:25 PM IST 
//


package target;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumMaritalStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumMaritalStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Married"/>
 *     &lt;enumeration value="Unmarried"/>
 *     &lt;enumeration value="Married_But_Alone"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumMaritalStatus")
@XmlEnum
public enum EnumMaritalStatus {

    Married,
    Unmarried,
    Married_But_Alone;

    public String value() {
        return name();
    }

    public static EnumMaritalStatus fromValue(String v) {
        return valueOf(v);
    }

}
