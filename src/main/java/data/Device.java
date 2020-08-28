package data;

public class Device {
    //data.Device type : computer or repeater.
    private char deviceType;

    //name of the device
    private String deviceName;

    int strength;

    final int DEFAULT_STRENGTH = 3;

    public Device(char deviceType , String deviceName)
    {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        if(deviceType == 'c')
            strength = DEFAULT_STRENGTH ;// default value
    }
    public Device(char deviceType , String deviceName , int strength)
    {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.strength = strength;
    }

    public char getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(char deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
