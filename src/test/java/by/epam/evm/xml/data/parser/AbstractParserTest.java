package by.epam.evm.xml.data.parser;

import by.epam.evm.xml.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParserTest {

    private final static String XML_NAME = "src/test/resources/devices.xml";
    private final static String INVALID_XML_NAME = "src/test/resources/d.xml";

    private final static List<AbstractComputerDevice> EXPECTED_DEVICE = Arrays.asList(
            new MotherBoard("ID-1", "MB_A1", "Asus", MotherBoardType.ATX,
                    new Processor("ID-2", "i7", "Intel", 2100, 4)),
            new VideoCard("ID-3", "N/A", "Asus", 4,
                    new Processor("ID-4", "Cuda", "NVIDIA", 2100, 4)),
            new MotherBoard("ID-5", "ZEN_A1", "GIGABYTE", MotherBoardType.M_ATX,
                    new Processor("ID-6", "N/A", "Intel", 2100, 4)),
            new VideoCard("ID-7", "VEGA", "AMD", 16,
                    new Processor("ID-8", "AMD", "AMD", 2100, 4)),
            new Processor("ID-9", "FX8300", "AMD", 2100, 4),
            new Processor("ID-10", "Ryzen3500", "AMD", 2100, 8)
    );

    @Test
    public void testParseShouldReturnListWhenDataIsCorrect() throws ParserException {
        //when
        AbstractParser parser = createParser();
        List<AbstractComputerDevice> actualDevice = parser.parse(XML_NAME);
        //then
        Assert.assertEquals(EXPECTED_DEVICE, actualDevice);
    }

    @Test(expected = ParserException.class)//then
    public void testParseShouldReturnExceptionWhenFileNotExist() throws ParserException {
        //when
        AbstractParser parser = createParser();
        parser.parse(INVALID_XML_NAME);

    }

    public abstract AbstractParser createParser();
}