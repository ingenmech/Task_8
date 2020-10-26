package by.epam.evm.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processor")
public class Processor extends AbstractComputerDevice {

    @XmlElement(name = "frequency-core", namespace = "http://www.example.com/devices", required = true)
    private int frequencyCore;
    @XmlElement(name = "cores-number", namespace = "http://www.example.com/devices", required = true)
    private int coresNumber;

    public Processor() {
    }

    public Processor(String id, String name, String manufacturer, int frequencyCore, int coresNumber) {
        super(id, name, manufacturer);
        this.frequencyCore = frequencyCore;
        this.coresNumber = coresNumber;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Processor processor = (Processor) o;
        if (frequencyCore != processor.frequencyCore) {
            return false;
        }
        return coresNumber == processor.coresNumber;
    }

    @Override
    public int hashCode() {
        int result = frequencyCore;
        result = 31 * result + coresNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Processor{" + super.toString() +
                "frequencyCore=" + frequencyCore +
                ", coresNumber=" + coresNumber +
                '}';
    }
}
