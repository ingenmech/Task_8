package by.epam.evm.xml.model;

public class MotherBoard extends AbstractComputerDevices {
    private MotherBoardType configuration;
    private Processor processor;

    public MotherBoardType getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MotherBoardType configuration) {
        this.configuration = configuration;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
}
