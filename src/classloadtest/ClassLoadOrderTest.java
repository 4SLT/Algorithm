package classloadtest;

/**
 * 测试类加载顺序与静态成员变量是否有关
 * 启动此方法时，需要添加jvm启动参数：-verbose:class
 * <p>
 * 结论：即使类中有静态成员变量/静态方法/静态代码块，也不会在程序启动时直接被加载，只有在静态方法/静态变量（大部分情况）被调用时，才会加载该类
 * ！！！注意！！！：如果是静态final变量被调用，且这个变量是基本类型（例如int -128 ~ 127）的时候，它的值直接存储在常量池里，即使被调用了也不会加载该类
 */
public class ClassLoadOrderTest {

    static {
        System.out.println("main方法静态代码块");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main方法开始执行");
        Thread.sleep(5000);
        System.out.println("运行期间开始加载++++++++++++++++++++++++++++++++++");

        System.out.println("调用静态成员常量：" + ClassWithStaticMethod.finalStr);
        System.out.println("调用静态成员变量：" + ClassWithStaticMethod.str);

    }
}
