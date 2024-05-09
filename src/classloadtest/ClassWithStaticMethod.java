package classloadtest;

public class ClassWithStaticMethod {
    public static final int finalStr = 1;

    public static int str = 2;


    static {
        System.out.println("Test静态代码块");
    }

    public void test(){
        System.out.println("Test成员方法开始执行");
    }

    public static void test1(){
        System.out.println("Test静态方法开始执行");
    }

}
