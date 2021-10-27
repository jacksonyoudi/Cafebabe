# mysql

## 索引

1. 什么是索引？
    字典
   
索引系统: 
    索引和数据 保存在磁盘， 加载到内存 

存储引擎： 不同数据文件在磁盘的不同存储方式

```shell
abc.frm
abc.ibd

db.frm
db.MYD
db.MYI
```


格式： 
k-v 
数据结构

hash表
树

为什么是B+ 


硬盘辅助，   

分开读取


分而治之

### 提高IO效率
1. 减少IO量
2. 减少IO次数


操作系统知识

1. 局部性原理
   时间局部性： 之前被访问过的数据很有可能再次被访问
   空间局部性： 数据和程序都有聚集成群的倾向
   
2. 磁盘预读
    内存和磁盘在进行交互的时候，有一个最小的逻辑单位， 这个单位称为页， 或者datapage
   一般是 4k或者8k， 由操作系统决定，我们在进行数据读取的时候，一般会读取页的整数倍。 innodb是 16kb
   

### hash表的问题
开链法
尾插法

需要比较的hash算法，出现不均匀
1. 无序 范围 效率比较低
memory支持hash索引，innodb支持自适应hash
   


### 树
二叉树
BST
AVL 
红黑树

1. 有两个节点
2. 有序
3. 平衡

劣势：
1. 插入更多数据的时候，当前数据会变得很高，加大读取次数，影响

https://www.cs.usfca.edu/~galles/visualization/Algorithms.html


B树
![msN2zB](https://raw.githubusercontent.com/jacksonyoudi/images/main/uPic/2021/10/26/msN2zB.png)

![YbU1c7](https://raw.githubusercontent.com/jacksonyoudi/images/main/uPic/2021/10/26/YbU1c7.png)


16 * 16 * 16 = 4096 
三层的B树 只能存储 4096


### B+树

16k  

10个字节 
16 * 1024 / 10 = 


![kwFTF6](https://raw.githubusercontent.com/jacksonyoudi/images/main/uPic/2021/10/26/kwFTF6.png)
![9Siqv9](https://raw.githubusercontent.com/jacksonyoudi/images/main/uPic/2021/10/26/9Siqv9.png)

一般 3-4层的B+树，足以使用 4kw的数据量存储 


选择索引的时候，选择int还是varchar
key 尽量少的占用空间 


基数：DV



### 聚簇索引
数据和索引存储在一起的叫 聚簇索引，没有存储在一起的叫非聚簇索引

innodb 存储引擎在进行数据插入的时候，数据必须要和某个索引列存储在一起的，这个索引可以是主键，如果没有主键，选择唯一键
如果没有唯一键，选择6个字节的rowid来进行存储

数据必定要和某一个索引绑定在一起的，绑定数据的索引叫做聚簇索引。
其他索引的叶子节点中存储的数据不再是整行的记录，而是聚簇索引的id值

id,name
id主键 name普通索引
id是聚簇索引，name对应的的索引的B+树的叶子节点存储的就是id值


innodb中既有聚簇索引也有非聚簇索引
myisam中只有非聚簇索引


### 回表 

id,name
id主键 name普通索引


回表

索引覆盖

最左匹配

```shell
select * from table where name='zs'

先根据name B+树匹配的对应的叶子节点，查询到对应的记录的id值，再根据id去找id的B+树索引中检索整行记录，这个过程就称之为回表
回表的效率低，






索引覆盖
select id,name from table where name='zs'

先根据name B+树匹配的对应的叶子节点，查询到对应的记录的id值，索引的叶子节点中包含了查询的所有列，此时不需要回表
这个过程叫索引覆盖 using index 提示信息

```

组合索引或联合索引

要遵循最左匹配原则

id主键，name, age gender
name age组合索引

select * from 



### 索引下推 
select * from table where name= 'zs' and age = 12;
没有索引下推之前
先根据name从存储引擎中拉取数据到server层中， 然后在server层中对age进行数据过滤。

有了索引下推之后，
根据name和age两个条件做数据筛选，将筛选之后的结果返回给server层。




## 如何回答面试中问到的优化 问题？
表达方式

工作中做过很多sql优化， 一般的优化我们并不是出现问题才进行优化的，




### 事务， 锁， MVCC
acid实现原理




MVCC： 多版本并发控制

读写效率，

基础知识， 
    当前读： 读取的是数据的最新版本 总是读取到最新的数据 -> select lock in share mode select * from update insert update...
    快照读： 读取的历史版本记录 ->  select ... 



















   





