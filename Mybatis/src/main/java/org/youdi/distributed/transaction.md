# 分布式事务

多个分布式集群将一系列操作都完成才算 事务



### ACID
AD undo redo log
CI lock mvcc



## 2PC 
降低一次请求中事故发生的概率： 彩排， 试试

事务协调器
第一阶段： 预提交
第二阶段： commit 

缺点：
    1. 资源占用高，效率低
    2. 不能做到 100%保证事务


### 3PC
三阶段提交

一阶段
can commit 
不占用资源

二阶段：
预提交

三阶段
do commit
加入了超时机制

如果没有收到 事务协调器 的提交指令 默认提交的 



不能百分百的 人工补偿  脚本 根据业务 


 




