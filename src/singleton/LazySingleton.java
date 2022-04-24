package singleton;

/***
 * 懒汉模式（DCL双重检查锁机制）
 */
public class LazySingleton {
    private volatile static LazySingleton lazySingleton;
    private LazySingleton(){}
    public  static LazySingleton getLazySingleton(){
        if(lazySingleton == null){
            synchronized (LazySingleton.class){
                if(lazySingleton == null){
                    return new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

}
