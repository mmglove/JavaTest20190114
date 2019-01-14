/**
 * 2018-12-13
 * java数组对象中的对象类型
 */
public class A {
    int a1;
    String a2;

    public A(int a1, String a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    //    　在程序中要直接输出类对象，可以在类中重写toString()方法，定制自己的输出。
    public String toString() {
        return "a1： " + a1 + ",a2: " + a2;
    }
}
