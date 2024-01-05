# 解决原Master恢复后不同步问题

原来的Master(191)恢复成Slave,他的同步状态不OK,状态为 master_link_status:down,这是为什么?

    原因：这是因为我们只设置了 192 和 193 的masterauth，这是用于同步master的数据
    
            但是191一开始是 master 是不受影响的
    
            当master转变为slave，由于它没有设置masterauth，
    
            所以他不能从新的master同步数据。
    
            随之导致 info replication的时候，同步状态为 down，
    
            所以只需要修改redis.conf 中 的 masterauth 为 imooc即可

一般master数据无法同步给slave的方案检查步骤

    ◆    网络通信问题，要保证互相ping通，内网互通。   
    
    ◆    关闭防火墙，对应的端口开放（虚拟机中建议永久关闭防火墙，云服务器的话需要保证内网互通）
    
    ◆    统一所有的密码，通过逐台检查机器以防某个节点被遗漏