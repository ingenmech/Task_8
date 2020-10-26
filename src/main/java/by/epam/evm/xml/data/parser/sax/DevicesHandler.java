package by.epam.evm.xml.data.parser.sax;

import by.epam.evm.xml.data.parser.AbstractParser;
import by.epam.evm.xml.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.epam.evm.xml.data.parser.AbstractParser.*;

//used static import AbstractParser.* for static constant fields
public class DevicesHandler extends DefaultHandler {

    private final static List<String> FIELD_VALUES = Arrays.asList(
            MANUFACTURER, CONFIGURATION, MEMORY_RAM, NESTED_PROCESSOR, FREQUENCY_CORE, CORES_NUMBER);
    private final List<AbstractComputerDevice> devices = new ArrayList<>();

    private AbstractComputerDevice device = null;
    private String currentField = null;
    private Processor nestedProcessor = null;

    public DevicesHandler() {
    }

    public List<AbstractComputerDevice> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (MOTHERBOARD.equals(localName) || VIDEO_CARD.equals(localName) || PROCESSOR.equals(localName)) {
            device = AbstractParser.createDevice(localName);
            String id = attributes.getValue(ID);
            device.setId(id);
            if (attributes.getLength() == 2) {
                String name = attributes.getValue(NAME);
                device.setName(name);
            }

        } else if (FIELD_VALUES.contains(localName)) {  // check name field, localName can contain device name
            currentField = localName;

            if (NESTED_PROCESSOR.equals(currentField)) {
                nestedProcessor = new Processor();
                String id = attributes.getValue(ID);
                nestedProcessor.setId(id);
                if (attributes.getLength() == 2) {
                    String name = attributes.getValue(NAME);
                    nestedProcessor.setName(name);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (MOTHERBOARD.equals(localName) || VIDEO_CARD.equals(localName) || PROCESSOR.equals(localName)) {
            devices.add(device);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String value = new String(ch, start, length);
        value = value.trim();

        if (currentField != null) {
            switch (currentField) {
                case MANUFACTURER:
                    if (nestedProcessor == null) {
                        device.setManufacturer(value);
                    } else {
                        nestedProcessor.setManufacturer(value);
                    }
                    break;
                case CONFIGURATION:
                    MotherBoardType type = MotherBoardType.valueOf(value);
                    ((MotherBoard) device).setConfiguration(type);
                    break;
                case MEMORY_RAM:
                    int memory = Integer.parseInt(value);
                    ((VideoCard) device).setMemoryRam(memory);
                    break;
                case NESTED_PROCESSOR:
                    if (device instanceof MotherBoard) {
                        ((MotherBoard) device).setProcessor(nestedProcessor);
                    }
                    if (device instanceof VideoCard) {
                        ((VideoCard) device).setProcessor(nestedProcessor);
                    }
                    break;
                case FREQUENCY_CORE:
                    setFrequencyCore(value);
                    break;
                case CORES_NUMBER:
                    setCoresNumber(value);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Field %s not exist in sequence", currentField));
            }
            currentField = null;
        }

    }

    private void setFrequencyCore(String value) {

        int frequency = Integer.parseInt(value);

        if (device instanceof Processor) {
            ((Processor) device).setFrequencyCore(frequency);
        } else if (device instanceof MotherBoard) {
            Processor processor = ((MotherBoard) device).getProcessor();
            processor.setFrequencyCore(frequency);
            nestedProcessor = null;
        } else if (device instanceof VideoCard) {
            Processor processor = ((VideoCard) device).getProcessor();
            processor.setFrequencyCore(frequency);
            nestedProcessor = null;
        }
    }

    private void setCoresNumber(String value) {

        int cores = Integer.parseInt(value);

        if (device instanceof Processor) {
            ((Processor) device).setCoresNumber(cores);
        } else if (device instanceof MotherBoard) {
            Processor processor = ((MotherBoard) device).getProcessor();
            processor.setCoresNumber(cores);
            nestedProcessor = null;
        } else if (device instanceof VideoCard) {
            Processor processor = ((VideoCard) device).getProcessor();
            processor.setCoresNumber(cores);
            nestedProcessor = null;
        }
    }

}
