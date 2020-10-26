package by.epam.evm.xml.data.parser.sax;

import by.epam.evm.xml.data.parser.DeviceFactory;
import by.epam.evm.xml.data.parser.Parser;
import by.epam.evm.xml.data.parser.ParserException;
import by.epam.evm.xml.model.AbstractComputerDevice;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class DeviceSaxParser implements Parser {

    private final DeviceFactory factory;

    public DeviceSaxParser() {
        this.factory = new DeviceFactory();
    }

    //package private for test
    DeviceSaxParser(DeviceFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<AbstractComputerDevice> parse(String file) throws ParserException {
        try {
            return run(file);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }

    private List<AbstractComputerDevice> run(String file) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);
        SAXParser parser = parserFactory.newSAXParser();
        XMLReader reader = parser.getXMLReader();
        DevicesHandler handler = new DevicesHandler(factory);
        reader.setContentHandler(handler);
        reader.parse(file);
        return handler.getDevices();

    }
}
