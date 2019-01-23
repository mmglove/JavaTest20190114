import java.util.Stack;

/**
 * @ Author     ：mengmeng17
 * @ Date       ：Created in 10:58 AM 2019/1/14
 * @ Description：1 两个栈实现一个队列
 * @ Modified By：
 * @Version: 01$
 */
public class StackAndQueue {
    public static void main(String[] args) {
        StackAndQueue s = new StackAndQueue();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.pop();
        s.pop();
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 1 用两个栈实现一个队列的进队和出队操作
     * 栈的特点：先进后出，队列的特点：先进先出
     * 栈1表示进队，出队的时候需要将1的元素压入栈2 ，将2的栈顶元素出栈，再将2中元素压入1
     */
    public void StackToQueue() {


    }

    /**
     * 栈实现的队列入队操作
     *
     * @param node
     */
    public void push(int node) {
        stack1.push(node);
    }

    /**
     * 栈实现的队列出队操作
     *
     * @return
     */
    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int tmp = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return tmp;

    }
}
