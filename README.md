

```markdown
# MyBTree

Java 实现的简单 **B 树**（B-Tree），支持泛型与插入操作，节点满时会进行分裂以保持平衡。

## 功能

- 使用泛型类型 `T extends Comparable<T>`。
- 根据指定阶数 `L` 创建 B 树。
- 支持插入操作：
  - 叶子节点插入。
  - 节点满时分裂。
  - 根节点分裂自动生成新根。
  - 支持递归分裂父节点。
- 插入后可直接通过 `toString()` 输出中序遍历结果。

## 文件结构


refer/
├── MyBTree.java    # B 树实现
├── MyNode.java     # B 树节点类（需提供）
```

## 使用方法

1. 编译
```bash
javac refer/*.java
```

2. 运行
```bash
java refer.MyBTree
```

## 运行示例

阶数 L 为 3，插入 0~5 输出：
```
0 
0 1 
0 1 2 
0 1 2 3 
0 1 2 3 4 
0 1 2 3 4 5 
```

## 核心方法

### insert(T item)
向 B 树插入一个元素，查找合适位置后插入，必要时调用 `split()`。

### split(MyNode<T> node)
节点满时进行分裂，分裂结果插入父节点，如果父节点也满则递归分裂。

## 运行入口

`main` 方法示例：
```java
public static void main(String[] args) {
    MyBTree<Integer> tree = new MyBTree<>(3);
    for (int i = 0; i < 100; i++) {
        tree.insert(i);
        System.out.println(tree);
    }
}
```

