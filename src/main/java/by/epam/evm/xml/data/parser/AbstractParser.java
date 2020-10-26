package by.epam.evm.xml.data.parser;

import by.epam.evm.xml.model.AbstractComputerDevice;
import by.epam.evm.xml.model.MotherBoard;
import by.epam.evm.xml.model.Processor;
import by.epam.evm.xml.model.VideoCard;

import java.util.List;

public abstract class AbstractParser {

    public final static String MOTHERBOARD = "motherboard";
    public final static String VIDEO_CARD = "video-card";
    public final static String PROCESSOR = "processor";

    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String MANUFACTURER = "manufacturer";
    public final static String CONFIGURATION = "configuration";
    public final static String MEMORY_RAM = "memory-ram";
    public final static String NESTED_PROCESSOR = "nested-processor";
    public final static String FREQUENCY_CORE = "frequency-core";
    public final static String CORES_NUMBER = "cores-number";

    public abstract List<AbstractComputerDevice> parse(String file) throws ParserException;

    public static AbstractComputerDevice createDevice(String element) {
        AbstractComputerDevice computerDevice;
        switch (element) {
            case MOTHERBOARD:
                computerDevice = new MotherBoard();
                break;
            case VIDEO_CARD:
                computerDevice = new VideoCard();
                break;
            case PROCESSOR:
                computerDevice = new Processor();
                break;
            default:
                throw new IllegalArgumentException(String.format("Not exist element %s", element));
        }
        return computerDevice;
    }
}
