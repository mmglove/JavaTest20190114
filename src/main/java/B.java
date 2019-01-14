/**
 * java数组对象测试类
 * 20181213
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B {
    public void temp() {
        //params :  {参数1,参数2（对象数组）a：[[],[],[]],参数3}
//        String str = javaSamplerContext.getParameter("params");
//        jmeter传递的参数，一行为一个strp,sss像当于一个对象A数组。{"1111","{{"a","b","c",},{"d","e","f",},{"g","h","i"}}","2222"}
        String sss="{{\"1\",\"b\",\"c\",},{\"2\",\"e\",\"f\",},{\"3\",\"h\",\"i\"}}";
        String[][] str={{"1","b","c",},{"2","e","f",},{"3","h","i"}};
//      jmeter传递的参数 strp格式={"1111","{{"a","b","c",},{"d","e","f",},{"g","h","i"}}","2222"}
        String strp[]={"111","{{\"1\",\"b\",\"c\",},{\"2\",\"e\",\"f\",},{\"3\",\"h\",\"i\"}}","222"};
        //遍历str方法3:想要快速地打印一个二维数组的数据元素列表，可以直接调用
        System.out.println("---str--"+Arrays.deepToString(str));
        //需要拼的对象数组数据
        A[] a=new A[str.length];
        List list=new ArrayList();
        list.add("你好");
        A aa=new  A(1,"A对象");
        list.add(aa);
        System.out.println("---list--"+list);
        for(int j=0;j<a.length;j++){
            a[j]=new A(1,"你好");
        }
        System.out.println("---a--"+Arrays.deepToString(a));
        for (int i = 0; i < str.length; i++) {
            String  s2[] = str[i];
            System.out.println("---s2[1]--"+s2[1]);
            //使用前三个参数构造一个A，并添加进A数组
            A aaa=new A(Integer.parseInt(s2[0]),s2[1]);
            a[i]=aaa;
            System.out.println("--i--"+i+"-a1--"+a[i].getA1());
            System.out.println("--i--"+i+"-a2--"+a[i].getA2());
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.temp();
    }
}
