package by.epam.evm.xml.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractComputerDevice")
@XmlSeeAlso({
        MotherBoard.class,
        VideoCard.class,
        Processor.class
})
public abstract class AbstractComputerDevice {
    @XmlAttribute(required = true)
    @XmlID
    private String id;
    @XmlAttribute
    private String name;
    @XmlElement(namespace = "http://www.example.com/devices", required = true)
    private String manufacturer;

    public AbstractComputerDevice() {

    }

    public AbstractComputerDevice(String id, String name, String manufacturer) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractComputerDevice device = (AbstractComputerDevice) o;
        if (id != null ? !id.equals(device.id) : device.id != null) {
            return false;
        }
        if (name != null ? !name.equals(device.name) : device.name != null) {
            return false;
        }
        return manufacturer != null ? manufacturer.equals(device.manufacturer) : device.manufacturer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'';
    }
}
