package by.epam.evm.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "motherboard")
public class MotherBoard extends AbstractComputerDevice {

    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private MotherBoardType configuration;
    @XmlElement(name = "nested-processor", namespace = "http://www.example.com/devices", required = true)
    private Processor processor;

    public MotherBoard() {
    }

    public MotherBoard(String id, String name, String manufacturer, MotherBoardType configuration, Processor processor) {
        super(id, name, manufacturer);
        this.configuration = configuration;
        this.processor = processor;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MotherBoard that = (MotherBoard) o;
        if (configuration != that.configuration) {
            return false;
        }
        return processor != null ? processor.equals(that.processor) : that.processor == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        result = 31 * result + (processor != null ? processor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MotherBoard{" + super.toString() +
                "configuration=" + configuration +
                ", processor=" + processor +
                "} ";
    }
}
