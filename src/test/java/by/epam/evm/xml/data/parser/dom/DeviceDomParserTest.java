package by.epam.evm.xml.data.parser.dom;

import by.epam.evm.xml.data.parser.AbstractParser;
import by.epam.evm.xml.data.parser.AbstractParserTest;

public class DeviceDomParserTest extends AbstractParserTest {

    @Override
    public AbstractParser createParser() {
        return new DeviceDomParser();
    }
}
