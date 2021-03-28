# 基本数据结构

* 物理结构
    - 数组
    - 链表
    
* 逻辑结构
    - 顺序表
    - 链表
        - 双向链表
        - 循环链表
    - 栈
    - 队列
        - 循环队列
        - 优先级队列(本质上是二项堆)
    - 散列表
    - 树
        - 二叉树[Binary Tree]
        - 二叉搜索树[Binary Search Tree][Binary Sort Tree]
        - 平衡二叉树[self-balancing binary search tree]
            - AVL树[AVL Tree]
            - 红黑树[Read Black Tree]
            - 树堆[Treap]
    - 堆[Heap](以数组的方式存储树的结构)
        -最大堆
        -最小堆
    - 图

# 高级数据结构

 - N叉平衡树[多路查找树]
    - B  树[B Tree]
    - B+ 树[B+ Tree]
 - 二项堆[Binominal Heap]
 - 斐波那契堆
 - 不相交集合 (disjointed set)

# 特殊用途或者特殊数据结构
    
- 跳表[skip-list]


- 舞蹈链表[Dancing Link]


- 单调栈
  ```
  单调栈中存放的数据应该是有序的
  单调栈也分为单调递增栈和单调递减栈:
     - 单调递增栈:单调递增栈就是从栈底到栈顶数据是从大到小[从小到大入栈][从大到小出栈]
     - 单调递减栈:单调递减栈就是从栈底到栈顶数据是从小到大[从大到小入栈][从小到大出栈]
  应用范围:
  
  ```
    
- 单调队列
  ```
  单调队列的基本思想是，维护一个双向队列（deque），遍历序列，仅当一个元素可能成为某个区间最值时才保留它
  
  应用范围: 滑动窗口问题
  ``` 
        

- 优先级队列
        
- 特殊的树
    - 二叉索引树[Binary Indexed Tree]
      ```
      存储每个索引的项目数，查询索引 m 和 n 之间有多少个项目
      ```
    - 范围树[Range tree]
      ```
      存储点，查询哪些点落在了给定区间
      ```
    - 区间树[Interval Tree]
      ```
      存储区间，查询哪些区间与给定区间相交，也支持点查询（Segment tree）
      ```
    - 线段树[Segment Tree]
      ```
      存储区间，查询哪些区间包含给定的点
      ```
    
- 字符串处理
    - 字典树[Trie Tree]
      ```
      又名单词查找树,不是平衡树,N叉树,前缀树
      一般用于字符串到对象的映射
      ```
    - 前缀树[Prefix Tree]
      ```
      N叉树,
      ```
    - 后缀树[Suffix tree][compact prefix tree]
      ```
      首先,是一棵 Compressed Trie.
      其次,后缀树中存储的关键词为所有的后缀.
      ```
    - patricia tree
    - crit-bit tree
      ```
      一般用于存放字符串
      ```
    - double array trie
 
    - 基数树[Radix Tree]
      ```
      一种多叉搜索树,一般用于长整数到对象的映射
      ```
    
    - 默克尔树[Merkle Tree]

- 并查集 (union-find with disjointed set)

