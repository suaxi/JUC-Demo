> synchronized 和 Lock锁的区别
1. synchronized Java内置的关键字，Lock是一个Java类
2. synchronized 无法判断获取锁的状态，Lock锁可以判断
3. synchronized 会自动释放锁，Lock锁必须手动释放
4. synchronized 获得锁--->等待，Lock锁不一定会等待
5. synchronized 可重入锁，不可中断，非公平，Lock可以重入锁可以判断锁，可自定义是否公平
6. synchronized 适合锁少量的代码同步问题，Lock锁反之

+ Lock 锁
+ PC 生产者和消费者问题
+ Lock8 8锁问题
+ unsafe 集合类不安全
+ callable
+ auxiliaryTools 常用辅助类
+ readWrite 读写锁
+ blockingQueue 阻塞队列
+ threadPool 线程池
+ forkJoin 拆分任务
+ future 异步回调