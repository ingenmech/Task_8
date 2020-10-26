package by.epam.evm.xml.data.parser.jaxb;

import by.epam.evm.xml.data.parser.AbstractParserTest;
import by.epam.evm.xml.data.parser.Parser;
import by.epam.evm.xml.data.parser.ParserException;
import org.junit.Test;

public class DeviceJAXBParserTest extends AbstractParserTest {

    @Test
    public void testParseShouldReturnListDeviceWhenDataIsValid() throws ParserException {
        testAbstractParse();
    }

    @Override
    public Parser getParser() {
        return new DeviceJaxbParser();
    }
}
