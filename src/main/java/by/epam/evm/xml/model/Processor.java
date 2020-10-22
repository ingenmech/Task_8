package by.epam.evm.xml.model;

public class Processor extends AbstractComputerDevices {
    private int frequencyCore;
    private int coresNumber;
    private ProcessorType type;

    public int getFrequencyCore() {
        return frequencyCore;
    }

    public void setFrequencyCore(int frequencyCore) {
        this.frequencyCore = frequencyCore;
    }

    public int getCoresNumber() {
        return coresNumber;
    }

    public void setCoresNumber(int coresNumber) {
        this.coresNumber = coresNumber;
    }

    public ProcessorType getType() {
        return type;
    }

    public void setType(ProcessorType type) {
        this.type = type;
    }
}
