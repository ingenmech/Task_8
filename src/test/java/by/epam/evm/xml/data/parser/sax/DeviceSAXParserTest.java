package by.epam.evm.xml.data.parser.sax;

import by.epam.evm.xml.data.parser.AbstractParserTest;
import by.epam.evm.xml.data.parser.Parser;
import by.epam.evm.xml.data.parser.ParserException;
import org.junit.Test;

public class DeviceSAXParserTest extends AbstractParserTest {

    @Test
    public void testParseShouldReturnListDeviceWhenDataIsValid() throws ParserException {
        testAbstractParse();
    }

    @Override
    public Parser getParser() {
        return new DeviceSaxParser();
    }
}
