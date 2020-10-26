package by.epam.evm.xml.data.parser.sax;

import by.epam.evm.xml.data.parser.AbstractParser;
import by.epam.evm.xml.data.parser.ParserException;
import by.epam.evm.xml.model.AbstractComputerDevice;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class DeviceSaxParser extends AbstractParser {

    @Override
    public List<AbstractComputerDevice> parse(String file) throws ParserException {

        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            DevicesHandler handler = new DevicesHandler();
            reader.setContentHandler(handler);
            reader.parse(file);

            return handler.getDevices();

        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }
}
