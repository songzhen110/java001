package Serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/***
 * JDK系列化相关内容
 * 1.JDK序列化和反序列化中重要组成 ObjectOutputStream ObjectInputStream serialVersionUID
 * 2.浅复制和深复制 浅复制可以使用Object里的clone方法，但其引用对象会随着原对象变更
 *     如果要实现深复制 可以重写clone（）内部使用JDK序列化方式反序列化
 */
public class TestJDKSerializable {
    public static void main(String[] args)  {

        Vehicle vehicle = new Vehicle(1L,"ABCDSFS", new Device(11L,"88000112121"),"imei001");

        try {
            System.out.println("old vehicle hashcode= " + vehicle.hashCode());
            // 序列化vehicle来传输数据
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(vehicle);

            Vehicle cloneVehicle = (Vehicle)vehicle.clone();
            Vehicle deepCloneVehicle = (Vehicle)vehicle.deepClone();

            //序列化之后修改vehicle对象
            vehicle.getDevice().setDeviceCode("修改了");
            vehicle.setVin("xiugai");
            vehicle.setId(1000L);

            // 反序列化vehicle来使用对象
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Vehicle o = (Vehicle)ois.readObject();
            System.out.println("old vehicle hashcode= " + o.hashCode());


        } catch (Exception e){
            System.err.println(e.getMessage());
        }


    }
}
