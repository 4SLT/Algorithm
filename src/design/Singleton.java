package design;

/**
 * 单例模式,懒汉式
 */
public class Singleton {
    /**
     * 私有构造方法，保证了Singleton类不会被外界通过new初始化
     */
    private Singleton() {
    }

    /**
     * 关键：静态内部类不会随着外部类的加载而加载，只有在第一次被访问时才会被加载到内存中
     */
    private static class LazyHolder {
        /**
         * final保证了被初始化后不可更改，保证了唯一性
         */
        static final Singleton INSTANCE = new Singleton();
    }

    /**
     * 获取Singleton实例的唯一入口
     * @return
     */
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
