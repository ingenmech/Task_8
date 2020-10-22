package by.epam.evm.xml.data.sax;

import by.epam.evm.xml.model.AbstractComputerDevices;
import by.epam.evm.xml.model.MotherBoard;
import by.epam.evm.xml.model.Processor;
import by.epam.evm.xml.model.VideoCard;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DevicesHandler extends DefaultHandler {

    private final static int ID_INDEX = 0;
    private final static int NAME_INDEX = 0;

    private List<AbstractComputerDevices> devices;
    private AbstractComputerDevices device = null;
    private DeviceField field = null;
    private EnumSet<DeviceField> fields;

    public DevicesHandler() {
        devices = new ArrayList<>();
        DeviceField startFiled = DeviceField.MANUFACTURER;
        DeviceField endField = DeviceField.TYPE;
        fields = EnumSet.range(startFiled, endField);
    }

    public List<AbstractComputerDevices> getDevices() {
        return devices;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        String id;
        String name;

        if ("mother-board".equals(localName) || "video-card".equals(localName) || "processor".equals(localName)) {
            device = createDevice(localName);
            id = attributes.getValue(ID_INDEX);
            device.setId(id);
            if (attributes.getLength() == 2) {
                name = attributes.getValue(NAME_INDEX);
                device.setName(name);
            }
        } else {
            //name = localName.toUpperCase();
            DeviceField thisField = DeviceField.valueOf(localName);
            if (fields.contains(thisField)) {
                field = thisField;
            }
        }
    }

    private AbstractComputerDevices createDevice(String type) {
        AbstractComputerDevices computerDevice;
        switch (type) {
            case "mother-board":
                computerDevice = new MotherBoard();
                break;
            case "video-card":
                computerDevice = new VideoCard();
                break;
            case "processor":
                computerDevice = new Processor();
                break;
            default:
                throw new IllegalArgumentException(String.format("Not exist type %s", type));
        }
        return computerDevice;
    }

    public void endElement(String uri, String localName, String qName) {
        if ("mother-board".equals(localName) || "video-card".equals(localName) || "processor".equals(localName)) {
            devices.add(device);
        }
    }

    public void characters(char[] ch, int start, int length) {

        String value = new String(ch, start, length).trim();

        if (field != null) {
            switch (field) {
                case MANUFACTURER:
                    device.setManufacturer(value);
                    break;
                case CONFIGURATION:
                    (MotherBoard) device.setManufac
                    break;
                case MEMORY_RAM:
                    break;
                case PROCESSOR:
                    break;
                case FREQUENCY_CORE:
                    break;
                case CORES_NUMBER:
                    break;
                case TYPE:
                    break;
            }
        }
    }

}
