package by.epam.evm.xml.data.parser.dom;

import by.epam.evm.xml.data.parser.AbstractParserTest;
import by.epam.evm.xml.data.parser.Parser;
import by.epam.evm.xml.data.parser.ParserException;
import org.junit.Test;

public class DeviceDomParserTest extends AbstractParserTest {

    @Test
    public void testParseShouldReturnListDeviceWhenDataIsValid() throws ParserException {
        testAbstractParse();
    }

    @Override
    public Parser getParser() {
        return new DeviceDomParser();
    }
}
