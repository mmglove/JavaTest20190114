import java.util.Arrays;

/**
 * @ Author     ：mengmeng17
 * @ Date       ：Created in 3:07 PM 2019/1/14
 * @ Description：数组的基本操作
 * @ Modified By：
 * @Version: 01$
 */
public class ArrayTest {
    public static void main(String[] args) {
//        CreateArray();
        System.out.println("--去重后的元素个数--" + removeDuplicates(new int[]{1, 2, 2, 2, 3, 3, 4}));
        System.out.println("--最大子数组和--" + FindGreatestSumOfSubArray(new int[]{1, -1, -2, 2, 3, 0, 4}));
        System.out.println("--数组中出现次数超过长度一半的数字--" + MoreThanHalfNum_Solution(new int[]{1, 1, 2, 1, 3, 1, 1}));
        int num1[] = new int[1];
        int num2[] = new int[1];
        int a[] = new int[]{1, 1, 2, 2, 3, 7, 3, 9};
        System.out.println("--数组中只出现一次的数字--");
        FindNumsAppearOnce(a, num1, num2);
    }

    /**
     * 01 创建数组的三种方式
     *
     * @return
     */
    public static void CreateArray() {
        //创建数组的第一种方法
        int[] arr = new int[6];
        int intValue = arr[5];
        System.out.println(intValue);
//创建数组的第二种方法
        int[] x = {1, 2, 3, 4};
        System.out.println(x[1]);

//创建数组的第三种方法
        int[] y = new int[]{1, 2, 3, 4, 5};
//打印数组的三种方式
//        1 传统的for循环方式

        for (int i = 0; i < y.length; i++) {
            System.out.println(y[i]);
        }
//        2 for each循环

        for (int a : y)
            System.out.println(a);
//        利用Array类中的toString方法
//        调用Array.toString(a)，返回一个包含数组元素的字符串，这些元素被放置在括号内，并用逗号分开
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(array));

    }

    /**
     * 2 输出数组中重复的数字
     * 可能有好几个重复的，任意输出一个，长度为n的数组中【0,n-1】范围的数字
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length != length) {
            duplication[0] = -1;
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 3 删除数组中重复的数字，重复的数字是保留一次
     *
     * @param A
     * @return 返回新的数组的长度，去重的后的数组放在A中
     */
    public static int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int k = 1;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] != A[i + 1]) {
                A[k] = A[i + 1];
                k++;
            }
        }
        System.out.println("-----" + Arrays.toString(A));
        return k;
    }

    /**
     * 4 数组中连续子数组的最大和
     * 可能含有负数
     *
     * @param array
     * @return
     */
    public static int FindGreatestSumOfSubArray(int[] array) {
//        当前子数组的和
        int cursum = 0;
//        当前最大子数组的和
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (cursum <= 0) {
//                如果加入当前元素，当前的最大和<0,则当前元素就不能被加入
                cursum = array[i];
            } else {
                cursum += array[i];
            }
            max = cursum > max ? cursum : max;
        }
        return max;
    }

    /**
     * 5 数组中出现次数超过长度一半的数字
     * 遇见与tmp相等的就num++,否则num--;最后的tmp就是重复最多的元素，再检查其重复次数是否达到长度一半以上
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution(int[] array) {
        int num = 0;
        int m = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == m) {
                num++;
            } else {
                num--;
            }
            if (num == 0) {
                m = array[i];
                num++;
            }
        }
        num = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == m) {
                num++;
            }
        }
        if (num * 2 > array.length) return m;
        else return 0;
    }

    /**
     * 6 数组中只出现了一次的数字
     * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
     *
     * @param a
     * @param num1
     * @param num2
     */
    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public static void FindNumsAppearOnce(int[] a, int num1[], int num2[]) {
        if (a == null || a.length < 2) {
            return;

        }
//        1） 整个数组异或，留下的就是只出现一次的两个数字的异或结果
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            res ^= a[i];
        }
//      2） 寻找第一个1 的位置k
        int flag = FindFirst1(res);
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < a.length; i++) {
//      3） 将数据按照k位置是否为1分组
            if ((a[i] & flag) == 0) num1[0] ^= a[i];
            else num2[0] ^= a[i];
        }
        System.out.println("---只出现一次的数字--" + num1[0] + "---" + num2[0]);
    }

    private static int FindFirst1(int num) {
        // TODO Auto-generated method stub

        int flag = 1;
        while ((num & flag) == 0) {
            flag <<= 1;
            System.out.println("---" + flag);
        }
        System.out.println("--flag-" + flag);
        return flag;
    }

}
