/*
 * insert
 *   if this is a leaf:
 *       search the right place to insert, then check is overfull or not:
 *               split the node recursively if necessary
 *   else:
 *       search the right children pointer then insert the item to it recursively
 *
 * split
 *   if is not overfull:
 *       do nothing
 *   else if the node is root:
 *       create a new root
 *   else
 *       split recursively*/

public class MyBTree<T extends Comparable<T>> {
    private MyNode<T> root;
    private int L;

    public MyBTree(int L) {
        this.L = L;
        root = new MyNode<>(L);
    }

    public void insert(T item) {
        insert(root, item);
    }

    @Override
    public String toString() {
        return root.toString();
    }
    private void insert(MyNode<T> node, T item) {
            if (item == null) return;
            if (node.isLeaf) {
                for (int i = 0; i < node.itemSize; i++) {
                    if (node.items[i] == null) continue;
                    int cmp = item.compareTo(node.items[i]);
                    if (cmp == 0) return;
                    else if (cmp < 0) {
                        for (int j = node.itemSize; j > i; j--) {
                            node.items[j] = node.items[j - 1];
                        }
                        node.items[i] = item;
                        node.itemSize++;
                        split(node);
                        return;
                    }
                }
                node.items[node.itemSize] = item;
                node.itemSize++;
                split(node);
                return;
            } else {
                for (int i = 0; i < node.itemSize; i++) {
                    int cmp = item.compareTo(node.items[i]);
                    if (cmp == 0) return;
                    else if (cmp < 0) {
                        //node.children[i].insert(item);
                        insert(node.children[i], item);
                        return;
                    }
                }
                //node.children[node.itemSize].insert(item);
                insert(node.children[node.itemSize], item);
            }
        }

    private void split(MyNode<T> node) {
        if (!node.isOverFull()) return;

        int mid = node.findMid(node.itemSize);
        MyNode<T> leftNode = new MyNode<>(L);
        leftNode.itemSize += mid;
        System.arraycopy(node.items, 0, leftNode.items, 0, mid);
        System.arraycopy(node.children, 0, leftNode.children, 0,mid + 1);

        MyNode<T> rightNode = new MyNode<>(L);
        int count = node.itemSize - mid - 1;
        rightNode.itemSize += count;
        System.arraycopy(node.items, mid + 1, rightNode.items, 0, count);
        System.arraycopy(node.children, mid + 1, rightNode.children, 0, count + 1);

        MyNode<T> midNode = new MyNode<>(L);
        insert(midNode, node.items[mid]);

        if (node.isRoot()) {
            midNode.children[0] = leftNode;
            midNode.children[1] = rightNode;
            leftNode.parent = midNode;
            rightNode.parent = midNode;
            midNode.isLeaf = false;
            root = midNode;
         } else {
            int index = 0;
            MyNode<T> parent = node.parent;
            while (index <= parent.itemSize && parent.children[index] != node)
                index++;

            for (int j = parent.itemSize + 1; j > index + 1; j--) {
                parent.children[j] = parent.children[j - 1];
            }
            // 移动keys
            for (int j = parent.itemSize; j > index; j--) {
                parent.items[j] = parent.items[j - 1];
            }

            // 插入中间key
            parent.items[index] = midNode.items[0];
            parent.itemSize++;

            // 挂接左右子节点

            leftNode.parent = parent;
            rightNode.parent = parent;

            parent.children[index] = leftNode;
            parent.children[index + 1] = rightNode;

            // 父节点可能也满了，需要递归分裂
            split(parent);
        }
    }
    public static void main(String[] args) {
        MyBTree<Integer> tree = new MyBTree<>(10);
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
            System.out.println(tree);
        }
    }
}
