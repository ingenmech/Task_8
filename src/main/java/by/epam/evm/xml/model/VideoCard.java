package by.epam.evm.xml.model;

public class VideoCard extends AbstractComputerDevices {
    private String memoryRam;
    private Processor processor;

    public String getMemoryRam() {
        return memoryRam;
    }

    public void setMemoryRam(String memoryRam) {
        this.memoryRam = memoryRam;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
}
