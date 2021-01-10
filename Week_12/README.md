### 配置 redis 的主从复制

- 启动命令

```
./redis-server
./redis-server redis.conf
./redis-cli
info
set a 1
./redis-cli -h localhost -p 6380
info
get a
set b 1
```

- 从库配置文件
  redis.conf

### sentinel 高可用

- 启动命令

```
./redis-sentinel sentinel.conf
./redis-sentinel sentinel2.conf
```

- 关闭主redis
- redis从库变为了主库
- 配置文件
  sentinel.conf

### Cluster 集群

- 启动命令

```
../src/redis-server redis6379.conf
../src/redis-server redis6380.conf
../src/redis-server redis6381.conf
src/redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381
src/redis-cli -c
```

- 配置文件
  cluster.conf