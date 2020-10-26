package by.epam.evm.xml.data.parser.jaxb;

import by.epam.evm.xml.data.parser.AbstractParser;
import by.epam.evm.xml.data.parser.AbstractParserTest;

public class DeviceJaxbParserTest extends AbstractParserTest {

    @Override
    public AbstractParser createParser() {
        return new DeviceJaxbParser();
    }
}
