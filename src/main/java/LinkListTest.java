import java.util.ArrayList;
import java.util.Stack;

/**
 * @ Author     ：mengmeng17
 * @ Date       ：Created in 4:18 PM 2019/1/13
 * @ Description：链表的基本操作
 * @ Modified By：
 * @Version: 01$
 */
public class LinkListTest {
    //    内部类

    /**
     * 1 从头到尾打印单链表
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromHeadToTail(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (listNode != null) {
            res.add(listNode.val);
            listNode = listNode.next;
        }
        return res;
    }

    /**
     * 2 从尾到头打印单链表
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<ListNode> stack = new Stack<ListNode>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            ListNode listNode1 = stack.pop();
            res.add(listNode1.val);
        }
        return res;
    }

    /**
     * 3 链表中倒数第k个结点
     * 两个相差k的指针，第一个走到末尾时，第二个就是倒数第K个节点
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode head, int k) {
        ListNode head2 = head;
//       第一个链表先走k步
        int i = 0;
        while (head != null && i < k) {
            head = head.next;
            i++;
        }
//        如果链表长度<k时，返回空
        if (i < k) return null;
        while (head != null) {
            head = head.next;
            head2 = head2.next;
        }
        return head2;
    }

    /**
     * 4 翻转链表
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        if (null == head) {
            return null;
        }
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 5 后插法创建链表
     *
     * @return
     */
    public static ListNode createListNode(int vals[]) {
        ListNode head = null;
        ListNode temp = head;
        if (vals.length < 1) {
            return null;
        }
        if (null == head) {
            head = new ListNode(vals[0]);
            temp = head;
        }
        int i = 1;
        while (i < vals.length) {
            temp.next = new ListNode(vals[i]);
            temp = temp.next;
            i++;
        }
        return head;
    }

    /**
     * 6 对链表进行排序
     * 替换链表中数据的大小，并未改变链表的指向
     *
     * @param head
     */
    public static ListNode SortListNode(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode curNode = head;
        ListNode nextNode = null;
        int temp = 0;
        while (curNode.next != null) {
            nextNode = curNode.next;
//            相当于一次冒泡，一次循环后一个数据到达最后的位置
            while (nextNode != null) {
//                升序，所以将大数换到最后位置
                if (curNode.val > nextNode.val) {
                    temp = curNode.val;
                    curNode.val = nextNode.val;
                    nextNode.val = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 7 合并两个排序的链表，在原始链表的基础上
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return list1;
        }
        ListNode head = null;
        if (list1.val <= list2.val) {
            head = list1;
            head.next = Merge(list1.next, list2);
        } else {
            head = list2;
            head.next = Merge(list1, list2.next);
        }
        return head;
    }

    /**
     * 8 删除链表中重复的点，不保留成重复的节点
     *
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication(ListNode pHead) {
        if (null == pHead) {
            return null;
        }
        // 递归停止条件
        if (pHead == null || pHead.next == null)
            return pHead;
        ListNode current = pHead.next;
        // 如果pHead是重复元素
        if (pHead.val == current.val) {
            current = current.next;
            while (current != null && current.val == pHead.val)
                current = current.next;
            pHead = current;
            return deleteDuplication(current);
        } else {
            // pHead不是重复元素
            pHead.next = deleteDuplication(current);
            return pHead;
        }
    }

    /**
     * 9 删除链表中重复的点，保留成重复的节点
     *
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication2(ListNode pHead) {
        if (null == pHead) {
            return null;
        }
        if (pHead == null) return null;
        ListNode first = new ListNode(-1);
        first.next = pHead;
        ListNode p = pHead;
        ListNode last = first;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                int v = p.val;
                last.next = p;
                last = last.next;
                while (p != null && p.val == v) {
                    p = p.next;
                }
                //last链接上下一个没有重复的节点
                last.next = p;
            } else {
                last = p;
                p = p.next;
            }

        }
        return first.next;

    }

    /**
     * 10 链表中包含环，请找出该链表的环的入口结点，否则，输出null。
     *
     * @param pHead
     * @return
     */
    public static ListNode EntryNodeOfLoop(ListNode pHead) {

//        第一步：判断是否含有环,两个快慢指针若相遇则说明有环
        if (null == pHead || null == pHead.next) {
            return null;
        }
        ListNode p1 = pHead;
        ListNode p2 = pHead.next.next;
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                break;
            } else {
                p1 = p1.next;
                p2 = p2.next.next;
            }
        }
        if (p1 == p2) {
            System.out.println("含有环" + p1.val);
//            说明含有环
//            第二步，先确定环中节点个数k
            p1 = p2.next;
            int i = 1;
            while (p1 != p2) {
                p1 = p1.next;
                i++;
//                System.out.println("iiii---" + i);
            }

//            第三步，寻找环的入口：先再让快的指针先走k步，慢的指针在重头走，相遇的节点记为环的入口。
            p1 = p2 = pHead;
            while (i > 0) {
                p1 = p1.next;
                i--;
//                System.out.println("iiii---" + i);
            }
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p2;

        } else {
            return null;
        }
    }

    /**
     * 11 两个链表的第一个公共结点
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (null == pHead1 || null == pHead2) {
            return null;
        }

    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkListTest l = new LinkListTest();

        ListNode head = l.createListNode(new int[]{1, 1, 3, 3, 4, 4, 5});
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        h1.next = h2;
        ListNode h3 = new ListNode(3);
        h2.next = h3;
        ListNode h4 = new ListNode(4);
        h3.next = h4;
        ListNode h5 = new ListNode(5);
        h4.next = h5;
        h5.next = h3;

        System.out.println("--创建链表--");
//        while (head != null) {
//            System.out.println(head.val);q
//            head = head.next;
//        }
//        System.out.println("--倒数第K个--" + FindKthToTail(head, 2).val);
//        System.out.println("--链表排序--" + SortListNode(head));
        ListNode tmp = EntryNodeOfLoop(h1);
        System.out.println(tmp.val);
        System.out.println("--链表去重---");
//        while (tmp != null) {
//            System.out.println(tmp.val);
//            tmp = tmp.next;
//        }
    }
}
