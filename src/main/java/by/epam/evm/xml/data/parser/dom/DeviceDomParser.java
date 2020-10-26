package by.epam.evm.xml.data.parser.dom;

import by.epam.evm.xml.data.parser.DeviceFactory;
import by.epam.evm.xml.data.parser.Parser;
import by.epam.evm.xml.data.parser.ParserException;
import by.epam.evm.xml.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDomParser implements Parser {

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String MANUFACTURER = "manufacturer";
    private final static String CONFIGURATION = "configuration";
    private final static String MEMORY_RAM = "memory-ram";
    private final static String NESTED_PROCESSOR = "nested-processor";
    private final static String FREQUENCY_CORE = "frequency-core";
    private final static String CORES_NUMBER = "cores-number";

    private final static int FIRST_CONTENT = 0;

    private final DeviceFactory factory;

    public DeviceDomParser() {
        this.factory = new DeviceFactory();
    }

    //package private for test
    DeviceDomParser(DeviceFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<AbstractComputerDevice> parse(String file) throws ParserException {

        List<AbstractComputerDevice> devices = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element root = document.getDocumentElement();

            NodeList deviceNodes = root.getChildNodes();
            for (int i = 0; i < deviceNodes.getLength(); i++) {
                if (deviceNodes.item(i) instanceof Element) {
                    Element deviceElement = (Element) deviceNodes.item(i);
                    AbstractComputerDevice device = parseDevice(deviceElement);
                    devices.add(device);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
        return devices;
    }

    private AbstractComputerDevice parseDevice(Element deviceElement) {

        AbstractComputerDevice device = factory.createDevice(deviceElement.getNodeName());
        String id = deviceElement.getAttribute(ID);
        device.setId(id);
        String name = deviceElement.getAttribute(NAME);
        if (name != null) {
            device.setName(name);
        }
        device.setManufacturer(getElementContent(deviceElement, MANUFACTURER));

        if (device instanceof MotherBoard) {

            String content = getElementContent(deviceElement, CONFIGURATION);
            MotherBoardType type = MotherBoardType.valueOf(content);
            ((MotherBoard) device).setConfiguration(type);
            Processor nestedProcessor = parseNestedProcessor(deviceElement);
            ((MotherBoard) device).setProcessor(nestedProcessor);

        } else if (device instanceof VideoCard) {

            String content = getElementContent(deviceElement, MEMORY_RAM);
            int memory = Integer.parseInt(content);
            ((VideoCard) device).setMemoryRam(memory);
            Processor nestedProcessor = parseNestedProcessor(deviceElement);
            ((VideoCard) device).setProcessor(nestedProcessor);

        } else if (device instanceof Processor) {

            String content = getElementContent(deviceElement, CORES_NUMBER);
            int number = Integer.parseInt(content);
            ((Processor) device).setCoresNumber(number);

            content = getElementContent(deviceElement, FREQUENCY_CORE);
            number = Integer.parseInt(content);
            ((Processor) device).setFrequencyCore(number);
        }
        return device;
    }

    private Processor parseNestedProcessor(Element deviceElement) {

        NodeList nestedElements = deviceElement.getElementsByTagName(NESTED_PROCESSOR);
        Element processorElement = (Element) nestedElements.item(FIRST_CONTENT);
        Processor processor = new Processor();

        String content = getElementContent(processorElement, CORES_NUMBER);
        int number = Integer.parseInt(content);
        processor.setCoresNumber(number);

        content = getElementContent(processorElement, FREQUENCY_CORE);
        number = Integer.parseInt(content);
        processor.setFrequencyCore(number);
        return processor;
    }

    private String getElementContent(Element element, String elementName) {
        NodeList nodes = element.getElementsByTagName(elementName);
        Node node = nodes.item(FIRST_CONTENT);
        return node.getTextContent();
    }
}
