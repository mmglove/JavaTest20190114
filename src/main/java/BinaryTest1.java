/**
 * @ Author     ：mengmeng17
 * @ Date       ：Created in 4:51 PM 2019/1/10
 * @ Description：蛇形打印二叉树
 * @ Modified By：
 * @Version: 01$
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTest1 {
    public ArrayList<ArrayList<Integer>> zhiPrint(TreeNode root) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.add(root);


        return res;
    }

    /**
     * 1 递归前序打印二叉树，根左右
     *
     * @param root
     * @return
     */
    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 2 递归中序打印二叉树，左根右
     *
     * @param root
     * @return
     */
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }

    /**
     * 3 递归后序打印二叉树，左右根
     *
     * @param root
     * @return
     */
    public static ArrayList<Integer> postOrder(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root != null) {
            res.addAll(postOrder(root.left));
            res.addAll(postOrder(root.right));
            res.add(root.val);
        }
        return res;
    }

    /**
     * 4 非递归前序打印二叉树，根左右,先进先出，使用队列
     * 使用栈的特点：进栈时打印即为先进先出的效果，出栈时打印即为先进后出的效果。
     *
     * @param root
     * @return
     */
    public static ArrayList<Integer> preOrder2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (true) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
//            如果栈为空表示数据打印完毕
            if (stack.isEmpty()) break;
//            进栈时打印节点，左边为空后右边开始进栈，
            root = stack.pop();
            root = root.right;
        }
        return res;
    }

    /**
     * 5 非递归中序遍历
     *
     * @param root
     * @returnin
     */
    public static ArrayList<Integer> inOrder2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) break;
//            先是左边节点进栈，左边为空后，栈顶元素（当前根节点）出栈，右边进栈
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 6 非递归后序遍历
     * 左节点入栈，取栈顶节点判断其前驱节点是否为空，为空则表示左右节点都已经被打印，当前节点出栈，并作为前驱节点，继续判断
     * 如果不为空则退出当前循环，继续让右分支中的左节点入栈
     *
     * @param root
     * @return
     */

    public static ArrayList<Integer> postOrder2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode preNode = null;
            boolean flag = true;
            while (!stack.isEmpty() && flag) {
                root = stack.peek();
                if (root.right == preNode) {
//                    如果栈顶元素的右节点为空，则当前节点出栈并打印
                    root = stack.pop();
                    res.add(root.val);
                    if (stack.isEmpty()) {
//                        栈为空说明已经全部出栈，退出最大循环
                        root = null;
                    } else {
                        preNode = root;
                    }
                } else {
                    root = root.right;
                    flag = false;
                }
            }
        }
        return res;
    }

    /**
     * 7 层次遍历
     * 当前节点不为空则入队，出队时将左右节点顺序入队
     *
     * @param root
     * @return
     */
    public static ArrayList<Integer> cengci(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
//        队列是一个接口，使用链表实现
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root != null) {
            queue.offer(root);
            while (!queue.isEmpty()) {
                root = queue.poll();
                res.add(root.val);
                if (null != root.left) {
                    queue.offer(root.left);
                }
                if (null != root.right) {
                    queue.offer(root.right);
                }
            }
        }
        return res;
    }

    /**
     * 8 蛇形、之字形遍历
     * 奇数行从左至右进栈，偶数行从右至左入栈
     *
     * @param root
     * @return
     */
    public static ArrayList<Integer> sehxing(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
//        stack1顺序存储奇数层的节点；stack2倒序存储偶数层的节点
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
//        当前层数
        int hight = 1;
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            //存放临时层的数据
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            if (hight % 2 == 1) {
//                当前为奇数行，s1出栈，s2顺序进栈
                while (!stack1.isEmpty()) {
                    root = stack1.pop();
                    if (root != null) {
                        tempList.add(root.val);
                        stack2.push(root.left);
                        stack2.push(root.right);
                    }
                }
//                一个栈出尽，表示一层数据打印完毕，将临时层数据统一存入res
                if (!tempList.isEmpty()) {
                    res.addAll(tempList);
                    hight++;
                }
            } else {
//                当前为偶数行，s2出栈，s1倒序进栈
                while (!stack2.isEmpty()) {
                    root = stack2.pop();
                    if (root != null) {
                        tempList.add(root.val);
                        stack1.push(root.right);
                        stack1.push(root.left);
                    }
                }
//                一个栈出尽，表示一层数据打印完毕，将临时层数据统一存入res
                if (!tempList.isEmpty()) {
                    res.addAll(tempList);
                    hight++;
                }
            }
        }
        return res;
    }

    /**
     * 9 求二叉树的最大深度：根节点到最远叶子结点的距离
     * 递归的思想，将左右节点的最大高度+1
     *
     * @param root
     * @return
     */
    public static int maxHight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftH = maxHight(root.left);
        int rightH = maxHight(root.right);
        return Math.max(leftH, rightH) + 1;
    }

    /**
     * 10 求二叉树的最小深度：根节点到最近叶子结点的距离
     * 递归的思想，将左右节点的最小高度+1
     * 特别考虑根节点只有一个分支的情况
     *
     * @param root
     * @return
     */
    public static int minHight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (null == root.left) {
            return minHight(root.right) + 1;
        }
        if (null == root.right) {
            return minHight(root.left) + 1;
        }
        int leftH = minHight(root.left);
        int rightH = minHight(root.right);
        return Math.min(leftH, rightH) + 1;
    }

    /**
     * 11 求二叉树的节点个数
     * 递归的思路：左右分支的节点个数和+1
     *
     * @param root
     * @return
     */
    public static int nodeNum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return nodeNum(root.left) + nodeNum(root.right) + 1;
    }


    /**
     * 12 求二叉树的叶节点个数
     * 递归的思路：左右分支的叶节点个数和
     *
     * @param root
     * @return
     */
    public static int childNodeNum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (null == root.left && null == root.right) {
            return 1;
        }
        return childNodeNum(root.left) + childNodeNum(root.right);
    }

    /**
     * 13 求二叉树中第k层节点的个数
     * 递归的思路：左右分支的k-i层的节点和
     *
     * @param root
     * @return
     */
    public static int nodeNumK(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (1 == k) {
            return 1;
        }
        int numLeft = nodeNumK(root.left, k - 1);
        int numRight = nodeNumK(root.right, k - 1);
        return numLeft + numRight;
    }

    /**
     * 14 判断是否为平衡二叉树
     * 特别注意是对于每一个节点，它的右子树深度减去左子树的深度的绝对值必须小于2
     *
     * @param root return true;
     * @return
     */
    public static boolean isBalancedTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftHight = maxHight(root.left);
        int rightHight = maxHight(root.right);
        if (Math.abs(leftHight - rightHight) > 1) {
//            注意递归返回的结果，如果写<2 return true时，则该递归永远不会return false
            return false;
        }
        return isBalancedTree(root.left) && isBalancedTree(root.right);

    }

    /**
     * 15 判断是否为完全二叉树
     * 除第h层外其他各层的节点数都达到最大个数，且第h层所有的节点都连续集中在最左边
     * 层次遍历入队如果前面的节点没孩子，此时如果后面的节点有孩子则一定不是完全二叉树
     *
     * @param root
     * @return
     */
    public static boolean isCompleteTree(TreeNode root) {
        if (null == root) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean hasChild = true;
        boolean res = true;
        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.println("--" + root.val + hasChild);
            if (!hasChild) {
//                层次遍历入队如果前面的节点没孩子，此时如果后面的节点有孩子则一定不是完全二叉树
                if (null != root.left || null != root.right) {
                    System.out.println("---至少有一个孩子--");
                    res = false;
                    break;
                }
            } else {
                if (null != root.left && null != root.right) {
                    queue.offer(root.left);
                    queue.offer(root.right);
                } else if (null != root.left && null == root.right) {
                    queue.offer(root.left);
                    hasChild = false;
                } else if (null == root.left && null == root.right) {
                    res = false;
                    break;
                } else {
                    hasChild = false;
                }
            }
        }
        return res;
    }

    /**
     * 16 判断两个二叉树是否相等
     * 递归遍历左右子树是否相等
     *
     * @param t1
     * @param t2
     * @return
     */
    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (null == t1 && null == t2) {
            return true;
        } else if (null == t1 || null == t2) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    /**
     * 17 判断两个二叉树是否为镜像
     * 递归遍历左右子树是否为镜像
     *
     * @param t1
     * @param t2
     * @return
     */
    public static boolean isMirrorTree(TreeNode t1, TreeNode t2) {
        if (null == t1 && null == t2) {
            return true;
        } else if (null == t1 || null == t2) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isMirrorTree(t1.left, t2.right) && isMirrorTree(t1.right, t2.left);
    }

    /**
     * 18 镜像二叉树，翻转二叉树
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }

    /**
     * 19 判断二叉树是否是合法的二叉查找树/排序树（BST）
     * 一棵BST定义为：
     * * 节点的左子树中的值要严格小于该节点的值。
     * * 节点的右子树中的值要严格大于该节点的值。
     * * 左右子树也必须是二叉查找树。
     * <p>
     * * 一个节点的树也是二叉查找树。
     * 递归遍历左右子树是否为二叉排序树
     *
     * @param root
     * @return
     */
    public static boolean isBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBST(TreeNode root, int minVal, int maxVal) {
        if (null == root) {
            return true;
        }
//        左子树的值<root.val,右子树的值>root.val
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isBST(root.left, minVal, root.val) && isBST(root.right, root.val, maxVal);

    }

    /**
     * 20 重建二叉树
     * 先序+中序-》树；后序+中序-》树
     *
     * @return
     */
    public static TreeNode reCreatTree(int[] preOrder, int[] inOrder) {
        return reCreatTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    public static TreeNode reCreatTree(int[] preOrder, int start1, int end1, int[] inOrder, int start2, int end2) {
        if (start1 > end1 || start2 > end2) {
            return null;
        }
//        前序遍历的第一个节点即为根节点
//        1 确认根节点
        TreeNode root = new TreeNode(preOrder[start1]);
//        2 左子树创建、右子树创建
        int rootIndex = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (root.val == inOrder[i]) {
                rootIndex = i;
                break;
            }
        }
        int lengthLeft = rootIndex - start2 - 1;
//        2 创建左子树
        TreeNode left = reCreatTree(preOrder, start1 + 1, start1 + 1 + lengthLeft, inOrder, start2, start2 + lengthLeft);
//        3 创建右子树
        TreeNode right = reCreatTree(preOrder, start1 + 2 + lengthLeft, end1, inOrder, rootIndex + 1, end2);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 21 创建二叉排序树
     */



    /**
     * 22 二叉树中的对大路径和
     * 第一种是左子树的路径加上当前节点，第二种是右子树的路径加上当前节点，第三种是左右子树的路径加上当前节点（相当于一条横跨当前节点的路径），第四种是只有自己的路径。
     * 乍一看似乎以此为条件进行自下而上递归就行了，然而这四种情况只是用来计算以当前节点根的最大路径，如果当前节点上面还有节点，那它的父节点是不能累加第三种情况的。
     * 所以我们要计算两个最大值，一个是当前节点下最大路径和，另一个是如果要连接父节点时最大的路径和。我们用前者更新全局最大量，用后者返回递归值就行了。
     * @param root
     * @return
     */
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    public int helper(TreeNode root) {
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        //连接父节点的最大路径是一、二、四这三种情况的最大值
        int currSum = Math.max(Math.max(left + root.val, right + root.val), root.val);
        //当前节点的最大路径是一、二、三、四这四种情况的最大值
        int currMax = Math.max(currSum, left + right + root.val);
        //用当前最大来更新全局最大
        max = Math.max(currMax, max);
        return currSum;
    }


    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(10);
//        root.right.left = new TreeNode(5);
//        root.right.right = new TreeNode(6);
//        root.right.left.left = new TreeNode(7);
//        root.right.left.left.left = new TreeNode(8);
        System.out.println("--先序遍历--" + preOrder2(root).toString());
        System.out.println("--中序遍历--" + inOrder2(root).toString());
//       preOrder(root);
//        inOrder(root);
        //        postOrder(root);
        System.out.println("--后序遍历--" + postOrder2(root).toString());
        System.out.println("--层次遍历--" + cengci(root).toString());
        System.out.println("--蛇形遍历--" + sehxing(root).toString());
        System.out.println("--二叉树最大深度--" + maxHight(root));
        System.out.println("--二叉树最小深度--" + minHight(root));
        System.out.println("--总结点个数--" + nodeNum(root));
        System.out.println("--叶结点个数--" + childNodeNum(root));
        System.out.println("--第层节点个数--" + nodeNumK(root, 3));
        System.out.println("--是否为平衡二叉树--" + isBalancedTree(root));
        System.out.println("--是否为完全二叉树--" + isCompleteTree(root));
        System.out.println("--翻转二叉树--" + preOrder2(mirrorTree(root)).toString());
        System.out.println("--是否为二叉排序树--" + isBST(root));
        int[] preOrder = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
        int[] inOrder = {8, 4, 9, 2, 10, 5, 1, 6, 3, 7};
        System.out.println("--先序+中序->后序--" + postOrder2(reCreatTree(preOrder, inOrder)));
    }
}



