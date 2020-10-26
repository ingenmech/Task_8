package by.epam.evm.xml.data.parser.sax;

import by.epam.evm.xml.data.parser.DeviceFactory;
import by.epam.evm.xml.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DevicesHandler extends DefaultHandler {

    //    private String field = null;
//    private EnumSet<DeviceField> fields;

    private final static String MOTHERBOARD = "motherboard";
    private final static String VIDEO_CARD = "video-card";
    private final static String PROCESSOR = "processor";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String MANUFACTURER = "manufacturer";
    private final static String CONFIGURATION = "configuration";
    private final static String MEMORY_RAM = "memory-ram";
    private final static String NESTED_PROCESSOR = "nested-processor";
    private final static String FREQUENCY_CORE = "frequency-core";
    private final static String CORES_NUMBER = "cores-number";

    private final List<String> fieldValues;
    private final DeviceFactory factory;
    private List<AbstractComputerDevice> devices;
    private AbstractComputerDevice device = null;
    private String currentField = null;
    private Processor nestedProcessor = null;

    public DevicesHandler(DeviceFactory factory) {
        this.factory = factory;
        devices = new ArrayList<>();
        fieldValues = Arrays.asList(MANUFACTURER, CONFIGURATION, MEMORY_RAM,
                NESTED_PROCESSOR, FREQUENCY_CORE, CORES_NUMBER);

//        DeviceField startFiled = DeviceField.MANUFACTURER;
//        DeviceField endField = DeviceField.CORES_NUMBER;
//        fields = EnumSet.range(startFiled, endField);
    }

    public List<AbstractComputerDevice> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

//        String id;
//        String name;

//        DeviceField field = DeviceField.ID;
//        String fieldId = field.getValue();
//        field = DeviceField.NAME;
//        String fieldName = field.getValue();

        if (MOTHERBOARD.equals(localName) || VIDEO_CARD.equals(localName) || PROCESSOR.equals(localName)) {
            device = factory.createDevice(localName);
            String id = attributes.getValue(ID);
            device.setId(id);
            if (attributes.getLength() == 2) {
                String name = attributes.getValue(NAME);
                device.setName(name);
            }
        } else {

//            String fieldName = localName.toUpperCase();
//            DeviceField currentField = DeviceField.valueOf(fieldName);
//            if (fields.contains(currentField)) {
//                this.currentField = currentField;
//            }

            if (fieldValues.contains(localName)) {
                currentField = localName;
            }

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
