package refer;
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
public class MyNode<T extends Comparable<T>> {
    private final int L; //最大item数;
    public T[] items;
    public   MyNode<T>[] children;
    public int itemSize;
    public boolean isLeaf;
    public MyNode<T> parent;

    public MyNode(int L) {
        this.L = L;
        isLeaf = true;
        itemSize = 0;
        items    = (T[]) java.lang.reflect.Array.newInstance(Comparable.class, L + 1);
        children = (MyNode<T>[]) java.lang.reflect.Array.newInstance(MyNode.class, L + 2);
        parent = null;
    }

    public int findMid(int x) {
        return x % 2 == 0 ? x / 2 - 1 : x / 2;
    }

    public boolean isOverFull() {
        return itemSize > L;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < itemSize; i++) {
            if (children[i] != null)
                result += children[i].toString() + " ";
            result += items[i].toString() + " ";
        }
        if (children[itemSize] != null)
            result += children[itemSize].toString();
        return result;
    }
}
