package by.epam.evm.xml.data.parser.jaxb;

import by.epam.evm.xml.data.parser.Parser;
import by.epam.evm.xml.data.parser.ParserException;
import by.epam.evm.xml.model.AbstractComputerDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DeviceJaxbParser implements Parser {

    private final static Logger LOGGER = LogManager.getLogger(DeviceJaxbParser.class);

    @Override
    public List<AbstractComputerDevice> parse(String file) throws ParserException {

        FileReader reader = null;
        try {

            JAXBContext context = JAXBContext.newInstance(Devices.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new FileReader(file);
            Devices devices = (Devices) unmarshaller.unmarshal(reader);
            return devices.getDevices();

        } catch (JAXBException | FileNotFoundException e) {
            throw new ParserException(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }
}
