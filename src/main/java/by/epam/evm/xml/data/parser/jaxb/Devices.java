package by.epam.evm.xml.data.parser.jaxb;

import by.epam.evm.xml.model.AbstractComputerDevice;
import by.epam.evm.xml.model.MotherBoard;
import by.epam.evm.xml.model.Processor;
import by.epam.evm.xml.model.VideoCard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "devices", namespace = "http://www.example.com/devices")
public class Devices {

    @XmlElements({@XmlElement(
            name = "motherboard", namespace = "http://www.example.com/devices", type = MotherBoard.class),
            @XmlElement(name = "video-card", namespace = "http://www.example.com/devices", type = VideoCard.class),
            @XmlElement(name = "processor", namespace = "http://www.example.com/devices", type = Processor.class)})
    private List<AbstractComputerDevice> devices = new ArrayList<>();

    public Devices() {
    }

    @XmlTransient
    public List<AbstractComputerDevice> getDevices() {
        return devices;
    }


    public void setDevices(List<AbstractComputerDevice> devices) {
        this.devices = devices;
    }

    public void addDevice(AbstractComputerDevice device) {
        devices.add(device);
    }

    @Override
    public String toString() {
        return "Devices{" +
                "devices=" + devices +
                '}';
    }
}
