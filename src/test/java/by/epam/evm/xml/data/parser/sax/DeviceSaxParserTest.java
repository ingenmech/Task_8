package by.epam.evm.xml.data.parser.sax;

import by.epam.evm.xml.data.parser.AbstractParser;
import by.epam.evm.xml.data.parser.AbstractParserTest;

public class DeviceSaxParserTest extends AbstractParserTest {

    @Override
    public AbstractParser createParser() {
        return new DeviceSaxParser();
    }
}
