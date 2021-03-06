# mysql知识点
## datetime与timestamp区别

|                  | datetime                                                     | timestamp                                                    |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 时间范围         | '1000-01-01 00:00:00.000000' 到 '9999-12-31 23:59:59.999999' | '1970-01-01 00:00:01.000000' 到 '2038-01-19 03:14:07.999999' |
| 存储方式         | 不做任何改变，基本上是原样输入和输出                         | 把客户端插入的时间从当前时区转化为UTC（世界标准时间）进行存储。查询时，将其又转化为客户端当前时区进行返回。 |
| 适用场景         |                                                              | 跨时区的业务                                                 |
| 自动初始化和更新 | 支持（自动初始化指的是如果对该字段（譬如上例中的hiredate字段）没有显性赋值，则自动设置为当前系统时间。） | 支持（自动更新指的是如果修改了其它字段，则该字段的值将自动更新为当前系统时间。） |

## MySQL 中NULL和空值的区别

1. 空值('')是不占用空间的。
2. NULL是占用空间的。
3. null会影响索引效率
4. 在设置默认值的时候，尽量不要用Null来当默认值，用空字符串（”）会更好一些。带有null的默认值还是可以走索引的，只是会影响效率。当然，如果确认该字段不会用到索引的话，也是可以设置为null的

## MySQL存储引擎MyISAM与InnoDB区别
- MyISAM存储引擎的特点是：表级锁、不支持事务和全文索引，适合一些CMS内容管理系统作为后台数据库使用，但是使用大并发、重负荷生产系统上，表锁结构的特性就显得力不从心
- innoDB存储引擎的特点是：行级锁、事务安全（ACID兼容）、支持外键、不支持FULLTEXT类型的索引。InnoDB存储引擎提供了具有提交、回滚和崩溃恢复能力的事务安全存储引擎。InnoDB是为处理巨大量时拥有最大性能而设计的。它的CPU效率可能是任何其他基于磁盘的关系数据库引擎所不能匹敌的。



## Mysql主从分离

- 主从同步如何工作

  ![MySQL-Master-Slave](https://github.com/chenyaowu/myo2o/blob/master/myo2o/img/mysql/MySQL-Master-Slave.jpg)

  1. 主服务器(Master)将对数据的操作记录到二进制文件(Binary log)中，Master通知存储引擎提交事务。
  2. 从服务器(Slave)将Binary log拷贝到从服务器中继日志(Relay log)当中。(Slave开启I/O thread读取Binary log写入到Relay log)
  3. SQL thrad读取Relay log，写入Slave数据库

## Linux卸载mysql

1. 查看当前mysql安装情况

   ```bash
   rpm -qa|grep -i mysql
   ```

2. 删除安装的mysql(上一步展示的所有mysql)

   ```bash
   rpm -ev xxxxxxx  --nodeps
   ```

3. 查找mysql目录并删除

   ```bash
   find / -name mysql
   
   /var/lib/mysql
   /var/lib/mysql/mysql
   /usr/lib64/mysql
   
   rm -rf /var/lib/mysql
   rm -rf /var/lib/mysql
   rm -rf /usr/lib64/mysql
   
   ```

4. 删除/etc/my.cnf

   ```bash
   rm -rf /etc/my.cnf
   ```

   



