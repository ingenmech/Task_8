package by.epam.evm.xml.data.parser;

import by.epam.evm.xml.model.AbstractComputerDevice;

import java.util.List;

public interface Parser {
    List<AbstractComputerDevice> parse(String file) throws ParserException;
}
