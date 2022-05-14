package Serializable;

import java.io.Serializable;

public class Device implements Serializable {
    private static final long serialVersionUID = 5419613593978032736L;

    private long id;
    private String deviceCode;

    public Device(long id, String deviceCode) {
        this.id = id;
        this.deviceCode = deviceCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}
