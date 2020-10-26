package by.epam.evm.xml.data;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    private final static String XML_NAME = "src/test/resources/devices.xml";
    private final static String XML_INVALID_NAME = "src/test/resources/devicesInvalid.xml";
    private final static String XSD_NAME = "src/test/resources/devicesSchema.xsd";


    @Test
    public void testValidShodReturnTrueWhenDataIsValid(){
        XmlValidator validator = new XmlValidator(XSD_NAME);

        boolean actual  = validator.valid(XML_NAME);

        Assert.assertTrue(actual);
    }

    @Test
    public void testValidShodReturnFalseWhenDataNotCorrect(){
        XmlValidator validator = new XmlValidator(XSD_NAME);

        boolean actual  = validator.valid(XML_INVALID_NAME);

        Assert.assertFalse(actual);
    }

}
