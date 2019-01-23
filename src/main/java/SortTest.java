import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ Author     ：mengmeng17
 * @ Date       ：Created in 9:28 PM 2019/1/14
 * @ Description：排序练习
 * @ Modified By：
 * @Version: 01$
 */
public class SortTest {
    public static void main(String[] args) {
        int arr[] = new int[]{2, 3, 6, 1, 4, 8, 0};
        System.out.println("--1直接插入排序--排序前：" + Arrays.toString(arr) + "排序后：" + Arrays.toString(InsertionSort(new int[]{2, 3, 6, 1, 4, 8, 0})));
        System.out.println("--2希尔排序--排序前：" + Arrays.toString(arr) + "排序后：" + Arrays.toString(ShellSort(new int[]{2, 3, 6, 1, 4, 8, 0})));
        System.out.println("--3选择排序--排序前：" + Arrays.toString(arr) + "排序后：" + Arrays.toString(selectionSort(new int[]{2, 3, 6, 1, 4, 8, 0})));
        System.out.println("--4堆排序---排序前：" + Arrays.toString(arr) + "排序后：" + Arrays.toString(HeapSort(new int[]{2, 3, 6, 1, 4, 8, 0})));
        System.out.println("--5冒泡排序--排序前：" + Arrays.toString(arr) + "排序后：" + Arrays.toString(BubbleSort(new int[]{2, 3, 6, 1, 4, 8, 0})));
        System.out.println("--6快速排序--排序前：" + Arrays.toString(arr) + "排序后：" + Arrays.toString(QuickSort(new int[]{2, 3, 6, 1, 4, 8, 0}, 0, arr.length - 1)));
//        System.out.println("--合并两个有序数组1--" + Arrays.toString(mergeSortArray(new int[]{1, 3, 5, 8}, new int[]{2, 3, 6, 10})));
//        System.out.println("--合并两个有序数组2--" + Arrays.toString(mergeSortArray2(new int[]{1, 3, 5, 8}, new int[]{2, 3, 6, 10})));
        System.out.println("--7 归并排序--" + Arrays.toString(MergeSort(new int[]{1, 0, 3, 5, 8, 2, 7, 9, 0, 10})));
        System.out.println("--8 基数排序--" + Arrays.toString(RadixSort(new int[]{1, 0, 3, 5, 8, 2, 7, 9, 0, 10})));
    }

    /**
     * 1 直接插入排序
     * 前N-1个元素是有序的
     *
     * @param arr
     * @return
     */
    public static int[] InsertionSort(int[] arr) {
        int[] array = arr;
        if (array.length <= 1) {
            return array;
        }
        int temp;
        int i = 0;
        while (i < array.length - 1) {
            temp = array[i + 1];
            int j = i;
            while (j >= 0 && temp < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
            i++;
        }
        return array;
    }

    /**
     * 2 希尔排序--缩小增量排序
     *
     * @param arr
     * @return
     */
    public static int[] ShellSort(int[] arr) {
        int[] array = arr;
        int len = array.length;
        int temp = 0;
//        增量
        int gap = len / 2;
        //增量》0时
        while (gap > 0) {
//            分组进行插入排序
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int j = i - gap;
//                组内插入排序
                while (j >= 0 && temp < array[j]) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                array[j + gap] = temp;
            }
//            减少增量
            gap /= 2;

        }
        return array;
    }

    /**
     * 3 直接选择排序
     * 每次选择一个最小的放到最前面，前N-1个元素有序
     *
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        int[] array = arr;
        if (array.length <= 1) {
            return array;
        }
        int temp = 0;
        int minIndex = 0;
        for (int i = 0; i < array.length; i++) {
            temp = array[i];
            minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (temp > array[j]) {
                    minIndex = j;
                }
            }
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    /**
     * 4 堆排序
     *
     * @param array
     * @return
     */
    public static int[] HeapSort(int[] array) {
        int len = array.length;
        System.out.println("--初始--" + Arrays.toString(array));
//        1.创建最大堆
        buildMaxHeap(array);
        int temp = 0;
        //            2. 循环将堆顶元素置换到最后的位置,堆顶元素为第一个元素
        while (len > 0) {
            System.out.println("--len--" + len + "---" + Arrays.toString(array));
            temp = array[len - 1];
            array[len - 1] = array[0];
            array[0] = temp;
            len--;
//            重新调整为大顶堆
            adjustHeap(array, 0, len);
        }
        return array;
    }

    /**
     * 堆排序辅助类——创建最大堆
     *
     * @param arr
     */
    public static void buildMaxHeap(int[] arr) {
        int[] array = arr;
        int len = array.length;
//        从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
        for (int i = (len / 2 - 1); i >= 0; i--) {
            System.out.println("--i--" + i + "----" + Arrays.toString(array));
            adjustHeap(array, i, len);
        }

    }

    /**
     * 堆排序辅助类——调整当前堆为大根堆
     *
     * @param array
     * @param i
     * @param len   表示调整的数组的长度
     */
    public static void adjustHeap(int[] array, int i, int len) {
        int maxIndex = i;
        //如果有左子树节点，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[2 * i] > array[maxIndex]) {
            maxIndex = 2 * i;
        }
        //如果有右子树节点，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[2 * i + 1] > array[maxIndex]) {
            maxIndex = 2 * i + 1;
        }
//        如果当前的堆顶元素并不是最大的值，则将其与最大值换位置
        if (maxIndex != i) {
//            则将其与最大值换位置
            int temp = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = temp;
//            重新调整堆是之成为最大堆，刚才交换到分支中的元素有可能会影响堆的结构，因此需要重新调整使之成为大顶堆
//            System.out.println("--adjustHeap--" + Arrays.toString(array) + maxIndex);
            adjustHeap(array, maxIndex, len);
        }
    }

    /**
     * 5 冒泡排序
     * 依次左右交换，一次循环最大的元素到达末尾
     *
     * @param arr
     * @return
     */
    public static int[] BubbleSort(int[] arr) {
        int[] array = arr;
        if (array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 6 快速排序
     * 递归形式
     *
     * @param array
     * @return
     */
    public static int[] QuickSort(int[] array, int start, int end) {
        //        每次的基准k都是第一个元素
        int key = array[start];
        int i = start;
        int j = end;
        while (j > i) {
//            从后往前比较
            while (j > i && array[j] >= key) {
//                如果没有比关键值小的，则比较下一个，
                j--;
            }
//            直到有比关键值小的交换位置
            if (array[j] < key) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
//            然后又从前往后比较
            while (i < j && array[i] <= key) {
//                如果没有比关键值小的，则比较下一个，
                i++;
            }

//            直到有比关键值大的交换位置
            if (array[i] > key) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
//        左右下标相遇后说明本轮快排结束，递归分别进行关键值左右的快排
        if (start > i) {
//            如果关键值左边含有元素，则对左边进行快排
            QuickSort(array, i, start - 1);
        }
        if (end > j) {
//            如果关键值右边含有元素，则对右边进行快排
            QuickSort(array, j + 1, end);
        }
        return array;
    }

    /**
     * 7 归并排序
     * 递归将数组拆分为2进行排序再合并
     *
     * @param array
     * @return
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int left = 0;
        int right = array.length - 1;
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temp = new int[array.length];
//        调用排序递归辅助类
        MergeSort(array, left, right, temp);
        return array;
    }

    /**
     * 归并排序辅助类——递归使用的方法
     *
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    public static void MergeSort(int[] array, int left, int right, int[] temp) {
//        进入递归的条件 即子序列中只有一个元素的时候退出递归
        if (left < right) {
            int mid = (right + left) / 2;
//            左边归并，使得左子序列有序
            MergeSort(array, 0, mid, temp);
            //            右边归并，使得右子序列有序
            MergeSort(array, mid + 1, right, temp);
            mergeInArray(array, left, mid, right, temp);
        }
    }

    /**
     * 归并排序辅助类——数组内元素合并
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public static void mergeInArray(int[] array, int left, int mid, int right, int[] temp) {
//        左序列指针
        int i = left;
//        右序列指针
        int j = mid + 1;
//        临时数组指针
        int t = 0;
        while (i <= mid && j <= right) {
            temp[t++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        //将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        //将右序列剩余元素填充进temp中
        while (j <= right) {
            temp[t++] = array[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            array[left++] = temp[t++];
        }
    }

    /**
     * 辅助1 合并两个有序数组
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] mergeSortArray(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int array[] = new int[len1 + len2];
//        当其中一个数组的最小值大于另一个元素的最大值时，直接复制两个数组即可。
        if (arr1[0] >= arr2[len2 - 1]) {
//            使用方法 System.arraycopy（Object src, int srcPos, Object dest, int desPos, int length)
//            参数定义：（原数组， 原数组的开始位置， 目标数组， 目标数组的开始位置， 拷贝个数）
            System.arraycopy(arr2, 0, array, 0, len2);
            System.arraycopy(arr1, 0, array, len2, len1);
        } else if (arr2[0] >= arr1[len1 - 1]) {
//            使用方法 System.arraycopy（Object src, int srcPos, Object dest, int desPos, int length)
//            参数定义：（原数组， 原数组的开始位置， 目标数组， 目标数组的开始位置， 拷贝个数）
            System.arraycopy(arr1, 0, array, 0, len1);
            System.arraycopy(arr2, 0, array, len1, len2);
        } else {
            int i = 0, j = 0, k = 0;
            while (i < len1 & j < len2) {
                if (arr1[i] < arr2[j]) {
                    array[k] = arr1[i];
                    i++;
                    k++;
                } else {
                    array[k] = arr2[j];
                    j++;
                    k++;
                }
            }
            while (i < len1) {
                array[k++] = arr1[i++];
            }
            while (j < len2) {
                array[k++] = arr2[j++];
            }
        }
        return array;
    }

    /**
     * 辅助2 合并有序数组简洁版本
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] mergeSortArray2(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int array[] = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            array[k++] = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];
        }
        while (i < len1) {
            array[k++] = arr1[i++];
        }
        while (j < len2) {
            array[k++] = arr2[j++];
        }
        return array;
    }

    /**
     * 8 基数排序  只能适合int正数
     * 按位上数组的大小排序，MSD 高位优先排序；LSD 低位优先排序
     *
     * @param array
     * @return
     */
    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
//        1. 找出最大数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
//        2. 计算最大数的位数，确定需要几轮排序
        int maxDigit = 0;
        while (max != 0) {
            max = max / 10;
            maxDigit++;
        }
//        用于计算数组中元素位数的参数
        int mod = 10;
        int dig = 1;
//        初始化10个位数盛放元素的动态数组
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());

        for (int i = 0; i < maxDigit; i++, mod *= 10, dig *= 10) {
            //        按该位数的值入桶，相当于入队操作
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / dig;
                bucketList.get(num).add(array[j]);
            }

//            回收进原始数组，清空辅助桶数据；出队操作，先入先出
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    array[index++] = bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
        }
        return array;
    }

    /**
     * 9 桶排序
     *
     * @param array
     * @return
     */
    public static int[] RadixSort2(int[] array) {
        return array;
    }

    /**
     * 10 计数排序
     *
     * @param array
     * @return
     */
    public static int[] RadixSort3(int[] array) {
        return array;
    }


}
