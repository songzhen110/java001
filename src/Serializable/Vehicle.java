package Serializable;

import java.io.*;

public class Vehicle implements Serializable,Cloneable {
    private static final long serialVersionUID = 999052740038766065L;
    private Long id;
    private String vin;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected Object deepClone() throws IOException,ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        // 反序列化vehicle来使用对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    private Device device;
    private transient String imei;

    public Vehicle(Long id, String vin, Device device, String imei) {
        this.id = id;
        this.vin = vin;
        this.device = device;
        this.imei = imei;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
