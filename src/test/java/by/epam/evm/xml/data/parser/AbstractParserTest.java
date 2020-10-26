package by.epam.evm.xml.data.parser;

import by.epam.evm.xml.model.*;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParserTest {

    private final static String FILE_NAME = "src/test/resources/devices.xml";

    public void testAbstractParse() throws ParserException {
        //given
        Processor processorBoard = new Processor(
                "ID-2", "i7", "Intel", 2100, 4);
        MotherBoard firstMotherBoard = new MotherBoard(
                "ID-1", "MB_A1", "Asus", MotherBoardType.ATX, processorBoard);
        Processor firstProcessorCard = new Processor(
                "ID-4", "Cuda", "NVIDIA", 2100, 4);
        VideoCard firstVideoCard = new VideoCard(
                "ID-3", "N/A", "Asus", 4, firstProcessorCard);
        Processor secondProcessorBoard = new Processor(
                "ID-6", "N/A", "Intel", 2100, 4);
        MotherBoard secondMotherBoard = new MotherBoard(
                "ID-5", "ZEN_A1", "GIGABYTE", MotherBoardType.M_ATX, secondProcessorBoard);
        Processor secondProcessorCard = new Processor(
                "ID-8", "AMD", "AMD", 2100, 4);
        VideoCard secondVideoCard = new VideoCard(
                "ID-7", "VEGA", "AMD", 16, secondProcessorCard);
        Processor firstProcessor = new Processor(
                "ID-9", "FX8300", "AMD", 2100, 4);
        Processor secondProcessor = new Processor(
                "ID-10", "Ryzen3500", "AMD", 2100, 8);
        List<AbstractComputerDevice> expectedDevice = Arrays.asList(
                firstMotherBoard, firstVideoCard, secondMotherBoard, secondVideoCard, firstProcessor, secondProcessor);
        //when
        Parser parser = getParser();
        List<AbstractComputerDevice> actualDevice = parser.parse(FILE_NAME);
        //then
        Assert.assertEquals(expectedDevice, actualDevice);
    }

    public abstract Parser getParser();
}