package by.epam.evm.xml.data.parser.dom;

import by.epam.evm.xml.data.parser.AbstractParser;
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

public class DeviceDomParser extends AbstractParser {

    private final static int FIRST_CONTENT = 0;

    public DeviceDomParser() {
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
                    AbstractComputerDevice device = buildDevice(deviceElement);
                    devices.add(device);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
        return devices;
    }

    private AbstractComputerDevice buildDevice(Element deviceElement) {

        String deviceName = deviceElement.getNodeName();
        AbstractComputerDevice device = AbstractParser.createDevice(deviceName);
        String id = deviceElement.getAttribute(ID);
        device.setId(id);
        String name = deviceElement.getAttribute(NAME);
        if (name != null) {
            device.setName(name);
        }
        device.setManufacturer(getElementContent(deviceElement, MANUFACTURER));

        if (MOTHERBOARD.equals(deviceName)) {
            buildMotherBoard(deviceElement, (MotherBoard) device);
        } else if (VIDEO_CARD.equals(deviceName)) {
            buildVideoCard(deviceElement, (VideoCard) device);
        } else if (PROCESSOR.equals(deviceName)) {
            buildProcessor(deviceElement, (Processor) device);
        }
        return device;
    }

    private void buildProcessor(Element deviceElement, Processor device) {
        String content = getElementContent(deviceElement, CORES_NUMBER);
        int number = Integer.parseInt(content);
        device.setCoresNumber(number);

        content = getElementContent(deviceElement, FREQUENCY_CORE);
        number = Integer.parseInt(content);
        device.setFrequencyCore(number);
    }

    private void buildMotherBoard(Element deviceElement, MotherBoard device) {
        String content = getElementContent(deviceElement, CONFIGURATION);
        MotherBoardType type = MotherBoardType.valueOf(content);
        device.setConfiguration(type);
        Processor nestedProcessor = buildNestedProcessor(deviceElement);
        device.setProcessor(nestedProcessor);
    }

    private void buildVideoCard(Element deviceElement, VideoCard device) {
        String content = getElementContent(deviceElement, MEMORY_RAM);
        int memory = Integer.parseInt(content);
        device.setMemoryRam(memory);
        Processor nestedProcessor = buildNestedProcessor(deviceElement);
        device.setProcessor(nestedProcessor);
    }

    private Processor buildNestedProcessor(Element deviceElement) {

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
