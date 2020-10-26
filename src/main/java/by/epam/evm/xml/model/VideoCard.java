package by.epam.evm.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "video-card")
public class VideoCard extends AbstractComputerDevice {

    @XmlElement(name = "memory-ram", namespace = "http://www.example.com/devices", required = true)
    private int memoryRam;
    @XmlElement(name = "nested-processor", namespace = "http://www.example.com/devices", required = true)
    private Processor processor;

    public VideoCard() {
    }

    public VideoCard(String id, String name, String manufacturer, int memoryRam, Processor processor) {
        super(id, name, manufacturer);
        this.memoryRam = memoryRam;
        this.processor = processor;
    }

    public int getMemoryRam() {
        return memoryRam;
    }

    public void setMemoryRam(int memoryRam) {
        this.memoryRam = memoryRam;
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
        VideoCard videoCard = (VideoCard) o;
        if (memoryRam != videoCard.memoryRam) {
            return false;
        }
        return processor != null ? processor.equals(videoCard.processor) : videoCard.processor == null;
    }

    @Override
    public int hashCode() {
        int result = memoryRam;
        result = 31 * result + (processor != null ? processor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideoCard{" + super.toString() +
                "memoryRam=" + memoryRam +
                ", processor=" + processor +
                "} ";
    }
}
