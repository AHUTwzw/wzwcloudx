1. UUID
UUID是Universally Unique Identifier的缩写，它是在一定的范围内(从特定的名字空间到全球)唯一的机器生成的标识符，UUID是16字节128位长的数字，通常以36字节的字符串表示，比如：3F2504E0-4F89-11D3-9A0C-0305E82C3301。
UUID经由一定的算法机器生成，为了保证UUID的唯一性，规范定义了包括网卡MAC地址、时间戳、名字空间(Namespace)、随机或伪随机数、时序等元素，以及从这些元素生成UUID的算法。UUID的复杂特性在保证了其唯一性的同时，意味着只能由计算机生成。

优点：

本地生成ID，不需要进行远程调用，时延低，性能高。

缺点：

UUID过长，16字节128位，通常以36长度的字符串表示，很多场景不适用，比如用UUID做数据库索引字段。

没有排序，无法保证趋势递增。

2. Flicker方案（自增长机制）
这个方案是由Flickr团队提出，设计单独的库表，单独提供产生全局ID的服务，主要思路采用了MySQL自增长ID的机制(auto_increment + replace into)

CREATE TABLE Tickets64 (
    id bigint(20) unsigned NOT NULL auto_increment,
    stub char(1) NOT NULL default '',
    PRIMARY KEY (id),
    UNIQUE KEY stub (stub)
)ENGINE=MyISAM;

#每次业务使用下列SQL读写MySQL得到ID号

REPLACE INTO Tickets64 (stub) VALUES ('a');
SELECT LAST_INSERT_ID();

replace into 跟 insert 功能类似，不同点在于：replace into 首先尝试插入数据到表中，如果发现表中已经有此行数据(根据主键或者唯-索引判断)则先删除此行数据，然后插入新的数据， 否则直接插入新数据。
为了避免单点故障，最少需要两个数据库实例，通过区分auto_increment的起始值和步长来生成奇偶数的ID。
Server1：

auto-increment-increment = 2

auto-increment-offset = 1

Server2：

auto-increment-increment = 2

auto-increment-offset = 2

优点：

充分借助数据库的自增ID机制，可靠性高，生成有序的ID。

缺点：

ID生成性能依赖单台数据库读写性能。

依赖数据库，当数据库异常时整个系统不可用。

对于依赖MySql性能问题，可用如下方案解决： 

在分布式环境中我们可以部署多台，每台设置不同的初始值，并且步长为机器台数，比如部署N台，每台的初始值就为0，1，2，3…N-1，步长为N。 

 以上方案虽然解决了性能问题，但是也存在很大的局限性：

系统扩容困难：系统定义好步长之后，增加机器之后调整步长困难。

数据库压力大：每次获取一个ID都必须读写一次数据库。