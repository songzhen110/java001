package singleton;

/***
 * 饿汉模式
 */
public class StaticSingleton {
    private final static StaticSingleton staticSingleton = new StaticSingleton();
    private StaticSingleton(){}
    public StaticSingleton getStaticSingleton(){
        return staticSingleton;
    }
}
