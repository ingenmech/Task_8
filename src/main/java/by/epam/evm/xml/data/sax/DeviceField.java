package by.epam.evm.xml.data.sax;

public enum DeviceField {

    DEVICES("devices"),
    ID("id"),
    NAME("name"),
    MANUFACTURER("manufacturer"),
    CONFIGURATION("configuration"),
    MEMORY_RAM("memory-ram"),
    PROCESSOR("processor"),
    FREQUENCY_CORE("frequency-core"),
    CORES_NUMBER("cores-number"),
    TYPE("type");

    private String value;

    private DeviceField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
