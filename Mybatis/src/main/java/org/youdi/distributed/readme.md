# 锁

锁是啥？
竞争资源：
排他性 第三方

目的：为了保护程序混乱 
分布式锁的 性能下降

分布式锁
分布式的情况下，有些资源必须要进行访问限制，比如扣减服务等

JVM锁的异同


### 在分布式锁的情况下，能保证多机，多进程，多线程访问资源一致性，这个时候还需要进行进程内部的JVM锁？
需要的， 可以进行过滤，减少并发，需要考虑中间件的性能。
比如一台机器上只会有一个请求， 这样可以降低1个数量级的请求。
分治思想
用锁： 串行化处理






1. mysql
2. redis
3. zk
4. etcd

轻量级，重量级分布式


### 两大类分布式锁





jvm锁
1. CAS自旋 
2. 系统调用 
    线程还循环？ 让出cpu自己阻塞，线程状态？ 后续如何唤醒，运行




## redis集群
1. 主从复制
   1. HA 单点故障 哨兵是监控 切换从为主  
   2. 数据同步 节点都是全量的
2. cluster模式
   1. 分治 分片的 槽位  容量 压力 瓶颈
   2. 每个节点存储的一部分数据
   
锁就是一个key  不同的锁访问不同的节点
setnx + timeout  + 续租 



redis挂：
    1. 业务剥离
    2. 运维 服务器 物理灾备
    
分布式要在同一个物理的数据中心完成
redis性能: 
    同主机和其他进程 10w以上
    同物理局域网为  4w 
    NAT 云主机到 k或百的QPS


### redlock
权衡

场景： 
    1. redis单机挂了
    2. redlock 多机
    3. 不是redis实现的，是client实现的算法




### zookeeper etcd
zk使用多台
分布式协调
    分布式锁
    分布式id
    分布式配置
    分布式注册发现
    分布式HA


#### zk 
主从
    leader单机: 两阶段提交 2PC 过半通过  性能不如redis, 有IO，有网络通信
    slave
    选举： 推让制，互相发消息，带着自己数据的id zxid, serverid 选择id大的

session,watch:
    会话会在多台上同步 session有超时时间的，不需要续租
    session是有心跳的    

    path node 
    持久 
    临时node绑定到session上
    
    watch： 监控回调   事件： delete create change  callback 在client执行的
    地址：注册回调
    



### redis vs zookeeper etcd取舍

使用队列， 异步 响应式 绕开分布式锁
资源性能提升





























    
    
    
    








































