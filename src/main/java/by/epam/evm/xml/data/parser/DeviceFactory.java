package by.epam.evm.xml.data.parser;

import by.epam.evm.xml.model.AbstractComputerDevice;
import by.epam.evm.xml.model.MotherBoard;
import by.epam.evm.xml.model.Processor;
import by.epam.evm.xml.model.VideoCard;

public class DeviceFactory {

    private final static String MOTHER_BOARD = "motherboard";
    private final static String VIDEO_CARD = "video-card";
    private final static String PROCESSOR = "processor";

    public AbstractComputerDevice createDevice(String element) {
        AbstractComputerDevice computerDevice;
        switch (element) {
            case MOTHER_BOARD:
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
