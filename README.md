# 配置开发环境 

## 使用Vagrant配置Linux虚拟机

Vagrant官网：[进入官网](https://www.vagrantup.com/)

Virtual Box官网：[进入官网](https://www.virtualbox.org/)

知乎教程链接：[点击进入](https://zhuanlan.zhihu.com/p/259833884)

## 虚拟机网络设置

修改Vagrantfile文件
```shell
  #取消注释，并修改IP
  config.vm.network "private_network", ip: "192.168.56.10"
```

## 安装Docker

官方教程：[点击查看](https://docs.docker.com/engine/install/centos/)

### 卸载旧版docker
```shell
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

### 建立yum仓库
```shell
sudo yum install -y yum-utils
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

### 下载Docker
```shell
sudo yum install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

### 设置开机自启
```shell
sudo systemctl enable docker
```

### 配置阿里云镜像
```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://qkjfxye4.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

## 使用Docker安装Mysql

### 安装mysql

```shell
sudo docker pull mysql:5.7
```

### 启动mysql

```shell
docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7
```

参数说明

-p 3306:3306：将容器的 3306 端口映射到主机的 3306 端口

-v /mydata/mysql/conf:/etc/mysql：将配置文件夹挂载到主机

-v /mydata/mysql/log:/var/log/mysql：将日志文件夹挂载到主机

-v /mydata/mysql/data:/var/lib/mysql/：将配置文件夹挂载到主机

-e MYSQL_ROOT_PASSWORD=root：初始化 root 用户的密码

### 使用mysql

```shell
docker exec -it mysql mysql -uroot -proot
```

## 使用Docker安装Redis

### 安装redis

```shell
sudo docker pull redis
```

### 启动redis

```Linux
mkdir -p /mydata/redis/conf

touch /mydata/redis/conf/redis.conf

docker run -p 6379:6379 --name redis -v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```

### 连接redis

```shell
docker exec -it redis redis-cli
```

## 配置git

## 安装Node.js

菜鸟教程安装教程：[点击进入](https://www.runoob.com/nodejs/nodejs-install-setup.html)

# 构建项目

## 创建微服务模块

- 商品服务(product)

- 仓储服务(order)

- 订单服务(ware)

- 优惠卷服务(coupon)

- 会员服务(member)


## 聚合微服务模块

修改主项目pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.zhangsisiyao</groupId>
	<artifactId>gulimall</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>gulimall</name>
	<description>聚合服务</description>
	<packaging>pom</packaging>

	<modules>
		<module>gulimall-coupon</module>
		<module>gulimall-member</module>
		<module>gulimall-order</module>
		<module>gulimall-product</module>
		<module>gulimall-ware</module>
	</modules>

</project>
```

## 构建项目数据库

### 商品服务数据库(pms)

#### 生成代码概览

```sql
drop table if exists pms_attr_group;

/*==============================================================*/
/* Table: pms_attr_group                                        */
/*==============================================================*/
create table pms_attr_group
(
   attr_group_id        bigint not null auto_increment comment '分组id',
   attr_group_name      char(20) comment '组名',
   sort                 int comment '排序',
   descript             varchar(255) comment '描述',
   icon                 varchar(255) comment '组图标',
   catelog_id           bigint comment '所属分类id',
   primary key (attr_group_id)
);

alter table pms_attr_group comment '属性分组';

drop table if exists pms_category;

/*==============================================================*/
/* Table: pms_category                                          */
/*==============================================================*/
create table pms_category
(
   cat_id               bigint not null auto_increment comment '分类id',
   name                 char(50) comment '分类名称',
   parent_cid           bigint comment '父分类id',
   cat_level            int comment '层级',
   show_status          tinyint comment '是否显示[0-不显示，1显示]',
   sort                 int comment '排序',
   icon                 char(255) comment '图标地址',
   product_unit         char(50) comment '计量单位',
   product_count        int comment '商品数量',
   primary key (cat_id)
);

alter table pms_category comment '商品三级分类';

drop table if exists pms_brand;

/*==============================================================*/
/* Table: pms_brand                                             */
/*==============================================================*/
create table pms_brand
(
   brand_id             bigint not null auto_increment comment '品牌id',
   name                 char(50) comment '品牌名',
   logo                 varchar(2000) comment '品牌logo地址',
   descript             longtext comment '介绍',
   show_status          tinyint comment '显示状态[0-不显示；1-显示]',
   first_letter         char(1) comment '检索首字母',
   sort                 int comment '排序',
   primary key (brand_id)
);

alter table pms_brand comment '品牌';

drop table if exists pms_spu_comment;

/*==============================================================*/
/* Table: pms_spu_comment                                       */
/*==============================================================*/
create table pms_spu_comment
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(255) comment '商品名字',
   member_nick_name     varchar(255) comment '会员昵称',
   star                 tinyint(1) comment '星级',
   member_ip            varchar(64) comment '会员ip',
   create_time          datetime comment '创建时间',
   show_status          tinyint(1) comment '显示状态[0-不显示，1-显示]',
   spu_attributes       varchar(255) comment '购买时属性组合',
   likes_count          int comment '点赞数',
   reply_count          int comment '回复数',
   resources            varchar(1000) comment '评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]',
   content              text comment '内容',
   member_icon          varchar(255) comment '用户头像',
   comment_type         tinyint comment '评论类型[0 - 对商品的直接评论，1 - 对评论的回复]',
   primary key (id)
);

alter table pms_spu_comment comment '商品评价';

drop table if exists pms_attr;

/*==============================================================*/
/* Table: pms_attr                                              */
/*==============================================================*/
create table pms_attr
(
   attr_id              bigint not null auto_increment comment '属性id',
   attr_name            char(30) comment '属性名',
   search_type          tinyint comment '是否需要检索[0-不需要，1-需要]',
   icon                 varchar(255) comment '属性图标',
   value_select         char(255) comment '可选值列表[用逗号分隔]',
   attr_type            tinyint comment '属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]',
   enable               bigint comment '启用状态[0 - 禁用，1 - 启用]',
   catelog_id           bigint comment '所属分类',
   show_desc            tinyint comment '快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整',
   primary key (attr_id)
);

alter table pms_attr comment '商品属性';

drop table if exists pms_sku_info;

/*==============================================================*/
/* Table: pms_sku_info                                          */
/*==============================================================*/
create table pms_sku_info
(
   sku_id               bigint not null auto_increment comment 'skuId',
   spu_id               bigint comment 'spuId',
   sku_name             varchar(255) comment 'sku名称',
   sku_desc             varchar(2000) comment 'sku介绍描述',
   catalog_id           bigint comment '所属分类id',
   brand_id             bigint comment '品牌id',
   sku_default_img      varchar(255) comment '默认图片',
   sku_title            varchar(255) comment '标题',
   sku_subtitle         varchar(2000) comment '副标题',
   price                decimal(18,4) comment '价格',
   sale_count           bigint comment '销量',
   primary key (sku_id)
);

alter table pms_sku_info comment 'sku信息';

drop table if exists pms_sku_sale_attr_value;

/*==============================================================*/
/* Table: pms_sku_sale_attr_value                               */
/*==============================================================*/
create table pms_sku_sale_attr_value
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   attr_id              bigint comment 'attr_id',
   attr_name            varchar(200) comment '销售属性名',
   attr_value           varchar(200) comment '销售属性值',
   attr_sort            int comment '顺序',
   primary key (id)
);

alter table pms_sku_sale_attr_value comment 'sku销售属性&值';

drop table if exists pms_sku_images;

/*==============================================================*/
/* Table: pms_sku_images                                        */
/*==============================================================*/
create table pms_sku_images
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   img_url              varchar(255) comment '图片地址',
   img_sort             int comment '排序',
   default_img          int comment '默认图[0 - 不是默认图，1 - 是默认图]',
   primary key (id)
);

alter table pms_sku_images comment 'sku图片';

drop table if exists pms_spu_info;

/*==============================================================*/
/* Table: pms_spu_info                                          */
/*==============================================================*/
create table pms_spu_info
(
   id                   bigint not null auto_increment comment '商品id',
   spu_name             varchar(200) comment '商品名称',
   spu_description      varchar(1000) comment '商品描述',
   catalog_id           bigint comment '所属分类id',
   brand_id             bigint comment '品牌id',
   weight               decimal(18,4),
   publish_status       tinyint comment '上架状态[0 - 下架，1 - 上架]',
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table pms_spu_info comment 'spu信息';

drop table if exists pms_product_attr_value;

/*==============================================================*/
/* Table: pms_product_attr_value                                */
/*==============================================================*/
create table pms_product_attr_value
(
   id                   bigint not null auto_increment comment 'id',
   spu_id               bigint comment '商品id',
   attr_id              bigint comment '属性id',
   attr_name            varchar(200) comment '属性名',
   attr_value           varchar(200) comment '属性值',
   attr_sort            int comment '顺序',
   quick_show           tinyint comment '快速展示【是否展示在介绍上；0-否 1-是】',
   primary key (id)
);

alter table pms_product_attr_value comment 'spu属性值';

drop table if exists pms_spu_info_desc;

/*==============================================================*/
/* Table: pms_spu_info_desc                                     */
/*==============================================================*/
create table pms_spu_info_desc
(
   spu_id               bigint not null comment '商品id',
   decript              longtext comment '商品介绍',
   primary key (spu_id)
);

alter table pms_spu_info_desc comment 'spu信息介绍';

drop table if exists pms_spu_images;

/*==============================================================*/
/* Table: pms_spu_images                                        */
/*==============================================================*/
create table pms_spu_images
(
   id                   bigint not null auto_increment comment 'id',
   spu_id               bigint comment 'spu_id',
   img_name             varchar(200) comment '图片名',
   img_url              varchar(255) comment '图片地址',
   img_sort             int comment '顺序',
   default_img          tinyint comment '是否默认图',
   primary key (id)
);

alter table pms_spu_images comment 'spu图片';

drop table if exists pms_attr_attrgroup_relation;

/*==============================================================*/
/* Table: pms_attr_attrgroup_relation                           */
/*==============================================================*/
create table pms_attr_attrgroup_relation
(
   id                   bigint not null auto_increment comment 'id',
   attr_id              bigint comment '属性id',
   attr_group_id        bigint comment '属性分组id',
   attr_sort            int comment '属性组内排序',
   primary key (id)
);

alter table pms_attr_attrgroup_relation comment '属性&属性分组关联';
```



#### 商品三级分类表

表名：pms_category

生成代码：

```sql
drop table if exists pms_category;

/*==============================================================*/
/* Table: pms_category                                          */
/*==============================================================*/
create table pms_category
(
   cat_id               bigint not null auto_increment comment '分类id',
   name                 char(50) comment '分类名称',
   parent_cid           bigint comment '父分类id',
   cat_level            int comment '层级',
   show_status          tinyint comment '是否显示[0-不显示，1显示]',
   sort                 int comment '排序',
   icon                 char(255) comment '图标地址',
   product_unit         char(50) comment '计量单位',
   product_count        int comment '商品数量',
   primary key (cat_id)
);

alter table pms_category comment '商品三级分类';

```

#### 商品品牌表

表名：pms_brand

生成代码：

```sql
drop table if exists pms_brand;

/*==============================================================*/
/* Table: pms_brand                                             */
/*==============================================================*/
create table pms_brand
(
   brand_id             bigint not null auto_increment comment '品牌id',
   name                 char(50) comment '品牌名',
   logo                 varchar(2000) comment '品牌logo地址',
   descript             longtext comment '介绍',
   show_status          tinyint comment '显示状态[0-不显示；1-显示]',
   first_letter         char(1) comment '检索首字母',
   sort                 int comment '排序',
   primary key (brand_id)
);

alter table pms_brand comment '品牌';

```

#### 商品评价表

表名：pms_spu_comment

示例代码：

```sql
drop table if exists pms_spu_comment;

/*==============================================================*/
/* Table: pms_spu_comment                                       */
/*==============================================================*/
create table pms_spu_comment
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(255) comment '商品名字',
   member_nick_name     varchar(255) comment '会员昵称',
   star                 tinyint(1) comment '星级',
   member_ip            varchar(64) comment '会员ip',
   create_time          datetime comment '创建时间',
   show_status          tinyint(1) comment '显示状态[0-不显示，1-显示]',
   spu_attributes       varchar(255) comment '购买时属性组合',
   likes_count          int comment '点赞数',
   reply_count          int comment '回复数',
   resources            varchar(1000) comment '评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]',
   content              text comment '内容',
   member_icon          varchar(255) comment '用户头像',
   comment_type         tinyint comment '评论类型[0 - 对商品的直接评论，1 - 对评论的回复]',
   primary key (id)
);

alter table pms_spu_comment comment '商品评价';

```

#### 属性表

表名：pms_attr

生成代码：

```sql
drop table if exists pms_attr;

/*==============================================================*/
/* Table: pms_attr                                              */
/*==============================================================*/
create table pms_attr
(
   attr_id              bigint not null auto_increment comment '属性id',
   attr_name            char(30) comment '属性名',
   search_type          tinyint comment '是否需要检索[0-不需要，1-需要]',
   icon                 varchar(255) comment '属性图标',
   value_select         char(255) comment '可选值列表[用逗号分隔]',
   attr_type            tinyint comment '属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]',
   enable               bigint comment '启用状态[0 - 禁用，1 - 启用]',
   catelog_id           bigint comment '所属分类',
   show_desc            tinyint comment '快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整',
   primary key (attr_id)
);

alter table pms_attr comment '商品属性';

```

### 属性分组表

```sql
drop table if exists pms_attr_group;

/*==============================================================*/
/* Table: pms_attr_group                                        */
/*==============================================================*/
create table pms_attr_group
(
   attr_group_id        bigint not null auto_increment comment '分组id',
   attr_group_name      char(20) comment '组名',
   sort                 int comment '排序',
   descript             varchar(255) comment '描述',
   icon                 varchar(255) comment '组图标',
   catelog_id           bigint comment '所属分类id',
   primary key (attr_group_id)
);

alter table pms_attr_group comment '属性分组';

```



#### sku信息表

表名：pms_sku_info

示例代码：

```sql
drop table if exists pms_sku_info;

/*==============================================================*/
/* Table: pms_sku_info                                          */
/*==============================================================*/
create table pms_sku_info
(
   sku_id               bigint not null auto_increment comment 'skuId',
   spu_id               bigint comment 'spuId',
   sku_name             varchar(255) comment 'sku名称',
   sku_desc             varchar(2000) comment 'sku介绍描述',
   catalog_id           bigint comment '所属分类id',
   brand_id             bigint comment '品牌id',
   sku_default_img      varchar(255) comment '默认图片',
   sku_title            varchar(255) comment '标题',
   sku_subtitle         varchar(2000) comment '副标题',
   price                decimal(18,4) comment '价格',
   sale_count           bigint comment '销量',
   primary key (sku_id)
);

alter table pms_sku_info comment 'sku信息';

```

#### sku销售属性&值表

表名：pms_sku_sale_attr_value

生成代码：

```sql
drop table if exists pms_sku_sale_attr_value;

/*==============================================================*/
/* Table: pms_sku_sale_attr_value                               */
/*==============================================================*/
create table pms_sku_sale_attr_value
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   attr_id              bigint comment 'attr_id',
   attr_name            varchar(200) comment '销售属性名',
   attr_value           varchar(200) comment '销售属性值',
   attr_sort            int comment '顺序',
   primary key (id)
);

alter table pms_sku_sale_attr_value comment 'sku销售属性&值';

```

#### sku图片表

表名：

生成代码：

```sql
drop table if exists pms_sku_images;

/*==============================================================*/
/* Table: pms_sku_images                                        */
/*==============================================================*/
create table pms_sku_images
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   img_url              varchar(255) comment '图片地址',
   img_sort             int comment '排序',
   default_img          int comment '默认图[0 - 不是默认图，1 - 是默认图]',
   primary key (id)
);

alter table pms_sku_images comment 'sku图片';

```
#### spu信息表

表名：pms_spu_info

生成代码：

```sql
drop table if exists pms_spu_info;

/*==============================================================*/
/* Table: pms_spu_info                                          */
/*==============================================================*/
create table pms_spu_info
(
   id                   bigint not null auto_increment comment '商品id',
   spu_name             varchar(200) comment '商品名称',
   spu_description      varchar(1000) comment '商品描述',
   catalog_id           bigint comment '所属分类id',
   brand_id             bigint comment '品牌id',
   weight               decimal(18,4),
   publish_status       tinyint comment '上架状态[0 - 下架，1 - 上架]',
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table pms_spu_info comment 'spu信息';
```


#### spu属性值表

表名：pms_product_attr_value

生成代码：

```sql
drop table if exists pms_product_attr_value;

/*==============================================================*/
/* Table: pms_product_attr_value                                */
/*==============================================================*/
create table pms_product_attr_value
(
   id                   bigint not null auto_increment comment 'id',
   spu_id               bigint comment '商品id',
   attr_id              bigint comment '属性id',
   attr_name            varchar(200) comment '属性名',
   attr_value           varchar(200) comment '属性值',
   attr_sort            int comment '顺序',
   quick_show           tinyint comment '快速展示【是否展示在介绍上；0-否 1-是】',
   primary key (id)
);

alter table pms_product_attr_value comment 'spu属性值';

```

#### spu信息介绍表

表名：pms_spu_info_desc

生成代码：

```sql
drop table if exists pms_spu_info_desc;

/*==============================================================*/
/* Table: pms_spu_info_desc                                     */
/*==============================================================*/
create table pms_spu_info_desc
(
   spu_id               bigint not null comment '商品id',
   decript              longtext comment '商品介绍',
   primary key (spu_id)
);

alter table pms_spu_info_desc comment 'spu信息介绍';

```

#### spu图片表

表名：

生成代码：

```sql
drop table if exists pms_spu_images;

/*==============================================================*/
/* Table: pms_spu_images                                        */
/*==============================================================*/
create table pms_spu_images
(
   id                   bigint not null auto_increment comment 'id',
   spu_id               bigint comment 'spu_id',
   img_name             varchar(200) comment '图片名',
   img_url              varchar(255) comment '图片地址',
   img_sort             int comment '顺序',
   default_img          tinyint comment '是否默认图',
   primary key (id)
);

alter table pms_spu_images comment 'spu图片';

```

#### 属性&属性分组关联表

表名：pms_attr_attrgroup_relation

生成代码：

```sql
drop table if exists pms_attr_attrgroup_relation;

/*==============================================================*/
/* Table: pms_attr_attrgroup_relation                           */
/*==============================================================*/
create table pms_attr_attrgroup_relation
(
   id                   bigint not null auto_increment comment 'id',
   attr_id              bigint comment '属性id',
   attr_group_id        bigint comment '属性分组id',
   attr_sort            int comment '属性组内排序',
   primary key (id)
);

alter table pms_attr_attrgroup_relation comment '属性&属性分组关联';

```

### 仓储服务数据库(wms)

#### 生成代码概览

```sql
drop table if exists wms_ware_info;

/*==============================================================*/
/* Table: wms_ware_info                                         */
/*==============================================================*/
create table wms_ware_info
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(255) comment '仓库名',
   address              varchar(255) comment '仓库地址',
   areacode             varchar(20) comment '区域编码',
   primary key (id)
);

alter table wms_ware_info comment '仓库信息';

drop table if exists wms_ware_sku;

/*==============================================================*/
/* Table: wms_ware_sku                                          */
/*==============================================================*/
create table wms_ware_sku
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   ware_id              bigint comment '仓库id',
   stock                int comment '库存数',
   sku_name             varchar(200) comment 'sku_name',
   stock_locked         int comment '锁定库存',
   primary key (id)
);

alter table wms_ware_sku comment '商品库存';

drop table if exists wms_ware_order_task;

/*==============================================================*/
/* Table: wms_ware_order_task                                   */
/*==============================================================*/
create table wms_ware_order_task
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment 'order_id',
   order_sn             varchar(255) comment 'order_sn',
   consignee            varchar(100) comment '收货人',
   consignee_tel        char(15) comment '收货人电话',
   delivery_address     varchar(500) comment '配送地址',
   order_comment        varchar(200) comment '订单备注',
   payment_way          tinyint(1) comment '付款方式【 1:在线付款 2:货到付款】',
   task_status          tinyint(2) comment '任务状态',
   order_body           varchar(255) comment '订单描述',
   tracking_no          char(30) comment '物流单号',
   create_time          datetime comment 'create_time',
   ware_id              bigint comment '仓库id',
   task_comment         varchar(500) comment '工作单备注',
   primary key (id)
);

alter table wms_ware_order_task comment '库存工作单';

drop table if exists wms_ware_order_task_detail;

/*==============================================================*/
/* Table: wms_ware_order_task_detail                            */
/*==============================================================*/
create table wms_ware_order_task_detail
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   sku_name             varchar(255) comment 'sku_name',
   sku_num              int comment '购买个数',
   task_id              bigint comment '工作单id',
   primary key (id)
);

alter table wms_ware_order_task_detail comment '库存工作单';

drop table if exists wms_purchase;

/*==============================================================*/
/* Table: wms_purchase                                          */
/*==============================================================*/
create table wms_purchase
(
   id                   bigint not null auto_increment,
   assignee_id          bigint,
   assignee_name        varchar(255),
   phone                char(13),
   priority             int(4),
   status               int(4),
   ware_id              bigint,
   amount               decimal(18,4),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table wms_purchase comment '采购信息';

drop table if exists wms_purchase_detail;

/*==============================================================*/
/* Table: wms_purchase_detail                                   */
/*==============================================================*/
create table wms_purchase_detail
(
   id                   bigint not null auto_increment,
   purchase_id          bigint comment '采购单id',
   sku_id               bigint comment '采购商品id',
   sku_num              int comment '采购数量',
   sku_price            decimal(18,4) comment '采购金额',
   ware_id              bigint comment '仓库id',
   status               int comment '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]',
   primary key (id)
);
```



#### 仓库信息

表名：wms_ware_info

生成代码：

```sql
drop table if exists wms_ware_info;

/*==============================================================*/
/* Table: wms_ware_info                                         */
/*==============================================================*/
create table wms_ware_info
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(255) comment '仓库名',
   address              varchar(255) comment '仓库地址',
   areacode             varchar(20) comment '区域编码',
   primary key (id)
);

alter table wms_ware_info comment '仓库信息';

```

#### 商品库存表

```sql
drop table if exists wms_ware_sku;

/*==============================================================*/
/* Table: wms_ware_sku                                          */
/*==============================================================*/
create table wms_ware_sku
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   ware_id              bigint comment '仓库id',
   stock                int comment '库存数',
   sku_name             varchar(200) comment 'sku_name',
   stock_locked         int comment '锁定库存',
   primary key (id)
);

alter table wms_ware_sku comment '商品库存';

```

#### 库存工作单表

```sql
drop table if exists wms_ware_order_task;

/*==============================================================*/
/* Table: wms_ware_order_task                                   */
/*==============================================================*/
create table wms_ware_order_task
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment 'order_id',
   order_sn             varchar(255) comment 'order_sn',
   consignee            varchar(100) comment '收货人',
   consignee_tel        char(15) comment '收货人电话',
   delivery_address     varchar(500) comment '配送地址',
   order_comment        varchar(200) comment '订单备注',
   payment_way          tinyint(1) comment '付款方式【 1:在线付款 2:货到付款】',
   task_status          tinyint(2) comment '任务状态',
   order_body           varchar(255) comment '订单描述',
   tracking_no          char(30) comment '物流单号',
   create_time          datetime comment 'create_time',
   ware_id              bigint comment '仓库id',
   task_comment         varchar(500) comment '工作单备注',
   primary key (id)
);

alter table wms_ware_order_task comment '库存工作单';

```

#### 库存工作单详情

```sql
drop table if exists wms_ware_order_task_detail;

/*==============================================================*/
/* Table: wms_ware_order_task_detail                            */
/*==============================================================*/
create table wms_ware_order_task_detail
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   sku_name             varchar(255) comment 'sku_name',
   sku_num              int comment '购买个数',
   task_id              bigint comment '工作单id',
   primary key (id)
);

alter table wms_ware_order_task_detail comment '库存工作单';

```



#### 采购单

```sql
drop table if exists wms_purchase;

/*==============================================================*/
/* Table: wms_purchase                                          */
/*==============================================================*/
create table wms_purchase
(
   id                   bigint not null auto_increment,
   assignee_id          bigint,
   assignee_name        varchar(255),
   phone                char(13),
   priority             int(4),
   status               int(4),
   ware_id              bigint,
   amount               decimal(18,4),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table wms_purchase comment '采购信息';

```

#### 采购单详情

```sql
drop table if exists wms_purchase_detail;

/*==============================================================*/
/* Table: wms_purchase_detail                                   */
/*==============================================================*/
create table wms_purchase_detail
(
   id                   bigint not null auto_increment,
   purchase_id          bigint comment '采购单id',
   sku_id               bigint comment '采购商品id',
   sku_num              int comment '采购数量',
   sku_price            decimal(18,4) comment '采购金额',
   ware_id              bigint comment '仓库id',
   status               int comment '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]',
   primary key (id)
);

```

### 订单服务数据库(oms)

#### 生成代码概览

```sql
drop table if exists oms_order;

/*==============================================================*/
/* Table: oms_order                                             */
/*==============================================================*/
create table oms_order
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   order_sn             char(32) comment '订单号',
   coupon_id            bigint comment '使用的优惠券',
   create_time          datetime comment 'create_time',
   member_username      varchar(200) comment '用户名',
   total_amount         decimal(18,4) comment '订单总额',
   pay_amount           decimal(18,4) comment '应付总额',
   freight_amount       decimal(18,4) comment '运费金额',
   promotion_amount     decimal(18,4) comment '促销优化金额（促销价、满减、阶梯价）',
   integration_amount   decimal(18,4) comment '积分抵扣金额',
   coupon_amount        decimal(18,4) comment '优惠券抵扣金额',
   discount_amount      decimal(18,4) comment '后台调整订单使用的折扣金额',
   pay_type             tinyint comment '支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】',
   source_type          tinyint comment '订单来源[0->PC订单；1->app订单]',
   status               tinyint comment '订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
   delivery_company     varchar(64) comment '物流公司(配送方式)',
   delivery_sn          varchar(64) comment '物流单号',
   auto_confirm_day     int comment '自动确认时间（天）',
   integration          int comment '可以获得的积分',
   growth               int comment '可以获得的成长值',
   bill_type            tinyint comment '发票类型[0->不开发票；1->电子发票；2->纸质发票]',
   bill_header          varchar(255) comment '发票抬头',
   bill_content         varchar(255) comment '发票内容',
   bill_receiver_phone  varchar(32) comment '收票人电话',
   bill_receiver_email  varchar(64) comment '收票人邮箱',
   receiver_name        varchar(100) comment '收货人姓名',
   receiver_phone       varchar(32) comment '收货人电话',
   receiver_post_code   varchar(32) comment '收货人邮编',
   receiver_province    varchar(32) comment '省份/直辖市',
   receiver_city        varchar(32) comment '城市',
   receiver_region      varchar(32) comment '区',
   receiver_detail_address varchar(200) comment '详细地址',
   note                 varchar(500) comment '订单备注',
   confirm_status       tinyint comment '确认收货状态[0->未确认；1->已确认]',
   delete_status        tinyint comment '删除状态【0->未删除；1->已删除】',
   use_integration      int comment '下单时使用的积分',
   payment_time         datetime comment '支付时间',
   delivery_time        datetime comment '发货时间',
   receive_time         datetime comment '确认收货时间',
   comment_time         datetime comment '评价时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);

alter table oms_order comment '订单';


drop table if exists oms_order_item;

/*==============================================================*/
/* Table: oms_order_item                                        */
/*==============================================================*/
create table oms_order_item
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment 'order_id',
   order_sn             char(32) comment 'order_sn',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(255) comment 'spu_name',
   spu_pic              varchar(500) comment 'spu_pic',
   spu_brand            varchar(200) comment '品牌',
   category_id          bigint comment '商品分类id',
   sku_id               bigint comment '商品sku编号',
   sku_name             varchar(255) comment '商品sku名字',
   sku_pic              varchar(500) comment '商品sku图片',
   sku_price            decimal(18,4) comment '商品sku价格',
   sku_quantity         int comment '商品购买的数量',
   sku_attrs_vals       varchar(500) comment '商品销售属性组合（JSON）',
   promotion_amount     decimal(18,4) comment '商品促销分解金额',
   coupon_amount        decimal(18,4) comment '优惠券优惠分解金额',
   integration_amount   decimal(18,4) comment '积分优惠分解金额',
   real_amount          decimal(18,4) comment '该商品经过优惠后的分解金额',
   gift_integration     int comment '赠送积分',
   gift_growth          int comment '赠送成长值',
   primary key (id)
);

alter table oms_order_item comment '订单项信息';


drop table if exists oms_payment_info;

/*==============================================================*/
/* Table: oms_payment_info                                      */
/*==============================================================*/
create table oms_payment_info
(
   id                   bigint not null auto_increment comment 'id',
   order_sn             char(32) comment '订单号（对外业务号）',
   order_id             bigint comment '订单id',
   alipay_trade_no      varchar(50) comment '支付宝交易流水号',
   total_amount         decimal(18,4) comment '支付总金额',
   subject              varchar(200) comment '交易内容',
   payment_status       varchar(20) comment '支付状态',
   create_time          datetime comment '创建时间',
   confirm_time         datetime comment '确认时间',
   callback_content     varchar(4000) comment '回调内容',
   callback_time        datetime comment '回调时间',
   primary key (id)
);

alter table oms_payment_info comment '支付信息表';

drop table if exists oms_order_operate_history;

/*==============================================================*/
/* Table: oms_order_operate_history                             */
/*==============================================================*/
create table oms_order_operate_history
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment '订单id',
   operate_man          varchar(100) comment '操作人[用户；系统；后台管理员]',
   create_time          datetime comment '操作时间',
   order_status         tinyint comment '订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
   note                 varchar(500) comment '备注',
   primary key (id)
);

alter table oms_order_operate_history comment '订单操作历史记录';

drop table if exists oms_order_setting;

/*==============================================================*/
/* Table: oms_order_setting                                     */
/*==============================================================*/
create table oms_order_setting
(
   id                   bigint not null auto_increment comment 'id',
   flash_order_overtime int comment '秒杀订单超时关闭时间(分)',
   normal_order_overtime int comment '正常订单超时时间(分)',
   confirm_overtime     int comment '发货后自动确认收货时间（天）',
   finish_overtime      int comment '自动完成交易时间，不能申请退货（天）',
   comment_overtime     int comment '订单完成后自动好评时间（天）',
   member_level         tinyint(2) comment '会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】',
   primary key (id)
);

alter table oms_order_setting comment '订单配置信息';

drop table if exists oms_order_return_apply;

/*==============================================================*/
/* Table: oms_order_return_apply                                */
/*==============================================================*/
create table oms_order_return_apply
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment 'order_id',
   sku_id               bigint comment '退货商品id',
   order_sn             char(32) comment '订单编号',
   create_time          datetime comment '申请时间',
   member_username      varchar(64) comment '会员用户名',
   return_amount        decimal(18,4) comment '退款金额',
   return_name          varchar(100) comment '退货人姓名',
   return_phone         varchar(20) comment '退货人电话',
   status               tinyint(1) comment '申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]',
   handle_time          datetime comment '处理时间',
   sku_img              varchar(500) comment '商品图片',
   sku_name             varchar(200) comment '商品名称',
   sku_brand            varchar(200) comment '商品品牌',
   sku_attrs_vals       varchar(500) comment '商品销售属性(JSON)',
   sku_count            int comment '退货数量',
   sku_price            decimal(18,4) comment '商品单价',
   sku_real_price       decimal(18,4) comment '商品实际支付单价',
   reason               varchar(200) comment '原因',
   description述         varchar(500) comment '描述',
   desc_pics            varchar(2000) comment '凭证图片，以逗号隔开',
   handle_note          varchar(500) comment '处理备注',
   handle_man           varchar(200) comment '处理人员',
   receive_man          varchar(100) comment '收货人',
   receive_time         datetime comment '收货时间',
   receive_note         varchar(500) comment '收货备注',
   receive_phone        varchar(20) comment '收货电话',
   company_address      varchar(500) comment '公司收货地址',
   primary key (id)
);

alter table oms_order_return_apply comment '订单退货申请';

drop table if exists oms_order_return_reason;

/*==============================================================*/
/* Table: oms_order_return_reason                               */
/*==============================================================*/
create table oms_order_return_reason
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '退货原因名',
   sort                 int comment '排序',
   status               tinyint(1) comment '启用状态',
   create_time          datetime comment 'create_time',
   primary key (id)
);

alter table oms_order_return_reason comment '退货原因';

drop table if exists oms_refund_info;

/*==============================================================*/
/* Table: oms_refund_info                                       */
/*==============================================================*/
create table oms_refund_info
(
   id                   bigint not null auto_increment comment 'id',
   order_return_id      bigint comment '退款的订单',
   refund               decimal(18,4) comment '退款金额',
   refund_sn            varchar(64) comment '退款交易流水号',
   refund_status        tinyint(1) comment '退款状态',
   refund_channel       tinyint comment '退款渠道[1-支付宝，2-微信，3-银联，4-汇款]',
   refund_content       varchar(5000),
   primary key (id)
);

alter table oms_refund_info comment '退款信息';

```



#### 订单表

```sql
drop table if exists oms_order;

/*==============================================================*/
/* Table: oms_order                                             */
/*==============================================================*/
create table oms_order
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   order_sn             char(32) comment '订单号',
   coupon_id            bigint comment '使用的优惠券',
   create_time          datetime comment 'create_time',
   member_username      varchar(200) comment '用户名',
   total_amount         decimal(18,4) comment '订单总额',
   pay_amount           decimal(18,4) comment '应付总额',
   freight_amount       decimal(18,4) comment '运费金额',
   promotion_amount     decimal(18,4) comment '促销优化金额（促销价、满减、阶梯价）',
   integration_amount   decimal(18,4) comment '积分抵扣金额',
   coupon_amount        decimal(18,4) comment '优惠券抵扣金额',
   discount_amount      decimal(18,4) comment '后台调整订单使用的折扣金额',
   pay_type             tinyint comment '支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】',
   source_type          tinyint comment '订单来源[0->PC订单；1->app订单]',
   status               tinyint comment '订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
   delivery_company     varchar(64) comment '物流公司(配送方式)',
   delivery_sn          varchar(64) comment '物流单号',
   auto_confirm_day     int comment '自动确认时间（天）',
   integration          int comment '可以获得的积分',
   growth               int comment '可以获得的成长值',
   bill_type            tinyint comment '发票类型[0->不开发票；1->电子发票；2->纸质发票]',
   bill_header          varchar(255) comment '发票抬头',
   bill_content         varchar(255) comment '发票内容',
   bill_receiver_phone  varchar(32) comment '收票人电话',
   bill_receiver_email  varchar(64) comment '收票人邮箱',
   receiver_name        varchar(100) comment '收货人姓名',
   receiver_phone       varchar(32) comment '收货人电话',
   receiver_post_code   varchar(32) comment '收货人邮编',
   receiver_province    varchar(32) comment '省份/直辖市',
   receiver_city        varchar(32) comment '城市',
   receiver_region      varchar(32) comment '区',
   receiver_detail_address varchar(200) comment '详细地址',
   note                 varchar(500) comment '订单备注',
   confirm_status       tinyint comment '确认收货状态[0->未确认；1->已确认]',
   delete_status        tinyint comment '删除状态【0->未删除；1->已删除】',
   use_integration      int comment '下单时使用的积分',
   payment_time         datetime comment '支付时间',
   delivery_time        datetime comment '发货时间',
   receive_time         datetime comment '确认收货时间',
   comment_time         datetime comment '评价时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);

alter table oms_order comment '订单';
```

#### 订单项信息

```sql
drop table if exists oms_order_item;

/*==============================================================*/
/* Table: oms_order_item                                        */
/*==============================================================*/
create table oms_order_item
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment 'order_id',
   order_sn             char(32) comment 'order_sn',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(255) comment 'spu_name',
   spu_pic              varchar(500) comment 'spu_pic',
   spu_brand            varchar(200) comment '品牌',
   category_id          bigint comment '商品分类id',
   sku_id               bigint comment '商品sku编号',
   sku_name             varchar(255) comment '商品sku名字',
   sku_pic              varchar(500) comment '商品sku图片',
   sku_price            decimal(18,4) comment '商品sku价格',
   sku_quantity         int comment '商品购买的数量',
   sku_attrs_vals       varchar(500) comment '商品销售属性组合（JSON）',
   promotion_amount     decimal(18,4) comment '商品促销分解金额',
   coupon_amount        decimal(18,4) comment '优惠券优惠分解金额',
   integration_amount   decimal(18,4) comment '积分优惠分解金额',
   real_amount          decimal(18,4) comment '该商品经过优惠后的分解金额',
   gift_integration     int comment '赠送积分',
   gift_growth          int comment '赠送成长值',
   primary key (id)
);

alter table oms_order_item comment '订单项信息';

```

#### 订单操作历史记录

```sql
drop table if exists oms_order_operate_history;

/*==============================================================*/
/* Table: oms_order_operate_history                             */
/*==============================================================*/
create table oms_order_operate_history
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment '订单id',
   operate_man          varchar(100) comment '操作人[用户；系统；后台管理员]',
   create_time          datetime comment '操作时间',
   order_status         tinyint comment '订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
   note                 varchar(500) comment '备注',
   primary key (id)
);

alter table oms_order_operate_history comment '订单操作历史记录';

```

#### 订单配置信息表

```sql
drop table if exists oms_order_setting;

/*==============================================================*/
/* Table: oms_order_setting                                     */
/*==============================================================*/
create table oms_order_setting
(
   id                   bigint not null auto_increment comment 'id',
   flash_order_overtime int comment '秒杀订单超时关闭时间(分)',
   normal_order_overtime int comment '正常订单超时时间(分)',
   confirm_overtime     int comment '发货后自动确认收货时间（天）',
   finish_overtime      int comment '自动完成交易时间，不能申请退货（天）',
   comment_overtime     int comment '订单完成后自动好评时间（天）',
   member_level         tinyint(2) comment '会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】',
   primary key (id)
);

alter table oms_order_setting comment '订单配置信息';

```

#### 订单退货申请

```sql
drop table if exists oms_order_return_apply;

/*==============================================================*/
/* Table: oms_order_return_apply                                */
/*==============================================================*/
create table oms_order_return_apply
(
   id                   bigint not null auto_increment comment 'id',
   order_id             bigint comment 'order_id',
   sku_id               bigint comment '退货商品id',
   order_sn             char(32) comment '订单编号',
   create_time          datetime comment '申请时间',
   member_username      varchar(64) comment '会员用户名',
   return_amount        decimal(18,4) comment '退款金额',
   return_name          varchar(100) comment '退货人姓名',
   return_phone         varchar(20) comment '退货人电话',
   status               tinyint(1) comment '申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]',
   handle_time          datetime comment '处理时间',
   sku_img              varchar(500) comment '商品图片',
   sku_name             varchar(200) comment '商品名称',
   sku_brand            varchar(200) comment '商品品牌',
   sku_attrs_vals       varchar(500) comment '商品销售属性(JSON)',
   sku_count            int comment '退货数量',
   sku_price            decimal(18,4) comment '商品单价',
   sku_real_price       decimal(18,4) comment '商品实际支付单价',
   reason               varchar(200) comment '原因',
   description述         varchar(500) comment '描述',
   desc_pics            varchar(2000) comment '凭证图片，以逗号隔开',
   handle_note          varchar(500) comment '处理备注',
   handle_man           varchar(200) comment '处理人员',
   receive_man          varchar(100) comment '收货人',
   receive_time         datetime comment '收货时间',
   receive_note         varchar(500) comment '收货备注',
   receive_phone        varchar(20) comment '收货电话',
   company_address      varchar(500) comment '公司收货地址',
   primary key (id)
);

alter table oms_order_return_apply comment '订单退货申请';

```



#### 支付信息表

```sql
drop table if exists oms_payment_info;

/*==============================================================*/
/* Table: oms_payment_info                                      */
/*==============================================================*/
create table oms_payment_info
(
   id                   bigint not null auto_increment comment 'id',
   order_sn             char(32) comment '订单号（对外业务号）',
   order_id             bigint comment '订单id',
   alipay_trade_no      varchar(50) comment '支付宝交易流水号',
   total_amount         decimal(18,4) comment '支付总金额',
   subject              varchar(200) comment '交易内容',
   payment_status       varchar(20) comment '支付状态',
   create_time          datetime comment '创建时间',
   confirm_time         datetime comment '确认时间',
   callback_content     varchar(4000) comment '回调内容',
   callback_time        datetime comment '回调时间',
   primary key (id)
);

alter table oms_payment_info comment '支付信息表';

```

#### 退货原因表

```sql
drop table if exists oms_order_return_reason;

/*==============================================================*/
/* Table: oms_order_return_reason                               */
/*==============================================================*/
create table oms_order_return_reason
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '退货原因名',
   sort                 int comment '排序',
   status               tinyint(1) comment '启用状态',
   create_time          datetime comment 'create_time',
   primary key (id)
);

alter table oms_order_return_reason comment '退货原因';

```

#### 退款信息表

```sql
drop table if exists oms_refund_info;

/*==============================================================*/
/* Table: oms_refund_info                                       */
/*==============================================================*/
create table oms_refund_info
(
   id                   bigint not null auto_increment comment 'id',
   order_return_id      bigint comment '退款的订单',
   refund               decimal(18,4) comment '退款金额',
   refund_sn            varchar(64) comment '退款交易流水号',
   refund_status        tinyint(1) comment '退款状态',
   refund_channel       tinyint comment '退款渠道[1-支付宝，2-微信，3-银联，4-汇款]',
   refund_content       varchar(5000),
   primary key (id)
);

alter table oms_refund_info comment '退款信息';

```



### 优惠卷服务数据库(sms)

#### 生成代码概览

```sql
drop table if exists sms_member_price;

/*==============================================================*/
/* Table: sms_member_price                                      */
/*==============================================================*/
create table sms_member_price
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   member_level_id      bigint comment '会员等级id',
   member_level_name    varchar(100) comment '会员等级名',
   member_price         decimal(18,4) comment '会员对应价格',
   add_other            tinyint(1) comment '可否叠加其他优惠[0-不可叠加优惠，1-可叠加]',
   primary key (id)
);

alter table sms_member_price comment '商品会员价格';

drop table if exists sms_sku_full_reduction;

/*==============================================================*/
/* Table: sms_sku_full_reduction                                */
/*==============================================================*/
create table sms_sku_full_reduction
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'spu_id',
   full_price           decimal(18,4) comment '满多少',
   reduce_price         decimal(18,4) comment '减多少',
   add_other            tinyint(1) comment '是否参与其他优惠',
   primary key (id)
);

alter table sms_sku_full_reduction comment '商品满减信息';

drop table if exists sms_sku_ladder;

/*==============================================================*/
/* Table: sms_sku_ladder                                        */
/*==============================================================*/
create table sms_sku_ladder
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'spu_id',
   full_count           int comment '满几件',
   discount             decimal(4,2) comment '打几折',
   price                decimal(18,4) comment '折后价',
   add_other            tinyint(1) comment '是否叠加其他优惠[0-不可叠加，1-可叠加]',
   primary key (id)
);

alter table sms_sku_ladder comment '商品阶梯价格';

drop table if exists sms_spu_bounds;

/*==============================================================*/
/* Table: sms_spu_bounds                                        */
/*==============================================================*/
create table sms_spu_bounds
(
   id                   bigint not null auto_increment comment 'id',
   spu_id               bigint,
   grow_bounds          decimal(18,4) comment '成长积分',
   buy_bounds           decimal(18,4) comment '购物积分',
   work                 tinyint(1) comment '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
   primary key (id)
);

alter table sms_spu_bounds comment '商品spu积分设置';

drop table if exists sms_coupon;

/*==============================================================*/
/* Table: sms_coupon                                            */
/*==============================================================*/
create table sms_coupon
(
   id                   bigint not null auto_increment comment 'id',
   coupon_type          tinyint(1) comment '优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]',
   coupon_img           varchar(2000) comment '优惠券图片',
   coupon_name          varchar(100) comment '优惠卷名字',
   num                  int comment '数量',
   amount               decimal(18,4) comment '金额',
   per_limit            int comment '每人限领张数',
   min_point            decimal(18,4) comment '使用门槛',
   start_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   use_type             tinyint(1) comment '使用类型[0->全场通用；1->指定分类；2->指定商品]',
   note                 varchar(200) comment '备注',
   publish_count        int(11) comment '发行数量',
   use_count            int(11) comment '已使用数量',
   receive_count        int(11) comment '领取数量',
   enable_start_time    datetime comment '可以领取的开始日期',
   enable_end_time      datetime comment '可以领取的结束日期',
   code                 varchar(64) comment '优惠码',
   member_level         tinyint(1) comment '可以领取的会员等级[0->不限等级，其他-对应等级]',
   publish              tinyint(1) comment '发布状态[0-未发布，1-已发布]',
   primary key (id)
);

alter table sms_coupon comment '优惠券信息';

drop table if exists sms_coupon_history;

/*==============================================================*/
/* Table: sms_coupon_history                                    */
/*==============================================================*/
create table sms_coupon_history
(
   id                   bigint not null auto_increment comment 'id',
   coupon_id            bigint comment '优惠券id',
   member_id            bigint comment '会员id',
   member_nick_name     varchar(64) comment '会员名字',
   get_type             tinyint(1) comment '获取方式[0->后台赠送；1->主动领取]',
   create_time          datetime comment '创建时间',
   use_type             tinyint(1) comment '使用状态[0->未使用；1->已使用；2->已过期]',
   use_time             datetime comment '使用时间',
   order_id             bigint comment '订单id',
   order_sn             bigint comment '订单号',
   primary key (id)
);

alter table sms_coupon_history comment '优惠券领取历史记录';

drop table if exists sms_coupon_spu_category_relation;

/*==============================================================*/
/* Table: sms_coupon_spu_category_relation                      */
/*==============================================================*/
create table sms_coupon_spu_category_relation
(
   id                   bigint not null auto_increment comment 'id',
   coupon_id            bigint comment '优惠券id',
   category_id          bigint comment '产品分类id',
   category_name        varchar(64) comment '产品分类名称',
   primary key (id)
);

alter table sms_coupon_spu_category_relation comment '优惠券分类关联';

drop table if exists sms_coupon_spu_relation;

/*==============================================================*/
/* Table: sms_coupon_spu_relation                               */
/*==============================================================*/
create table sms_coupon_spu_relation
(
   id                   bigint not null auto_increment comment 'id',
   coupon_id            bigint comment '优惠券id',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(255) comment 'spu_name',
   primary key (id)
);

alter table sms_coupon_spu_relation comment '优惠券与产品关联';

drop table if exists sms_category_bounds;

/*==============================================================*/
/* Table: sms_category_bounds                                   */
/*==============================================================*/
create table sms_category_bounds
(
   id                   bigint not null auto_increment comment 'id',
   category_id          bigint,
   grow_bounds          decimal(18,4) comment '成长积分',
   buy_bounds           decimal(18,4) comment '购物积分',
   work                 tinyint(1) comment '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
   primary key (id)
);

alter table sms_category_bounds comment '商品分类积分设置';

drop table if exists sms_sku_bounds;

/*==============================================================*/
/* Table: sms_sku_bounds                                        */
/*==============================================================*/
create table sms_sku_bounds
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint,
   grow_bounds          decimal(18,4) comment '成长积分',
   buy_bounds           decimal(18,4) comment '购物积分',
   work                 tinyint(1) comment '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
   primary key (id)
);

alter table sms_sku_bounds comment '商品sku积分设置';

drop table if exists sms_seckill_promotion;

/*==============================================================*/
/* Table: sms_seckill_promotion                                 */
/*==============================================================*/
create table sms_seckill_promotion
(
   id                   bigint not null auto_increment comment 'id',
   title                varchar(255) comment '活动标题',
   start_time           datetime comment '开始日期',
   end_time             datetime comment '结束日期',
   status               tinyint comment '上下线状态',
   create_time          datetime comment '创建时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table sms_seckill_promotion comment '秒杀活动';

drop table if exists sms_seckill_sku_relation;

/*==============================================================*/
/* Table: sms_seckill_sku_relation                              */
/*==============================================================*/
create table sms_seckill_sku_relation
(
   id                   bigint not null auto_increment comment 'id',
   promotion_id         bigint comment '活动id',
   promotion_session_id bigint comment '活动场次id',
   sku_id               bigint comment '商品id',
   seckill_price        decimal comment '秒杀价格',
   seckill_count        decimal comment '秒杀总量',
   seckill_limit        decimal comment '每人限购数量',
   seckill_sort         int comment '排序',
   primary key (id)
);

alter table sms_seckill_sku_relation comment '秒杀活动商品关联';

drop table if exists sms_seckill_session;

/*==============================================================*/
/* Table: sms_seckill_session                                   */
/*==============================================================*/
create table sms_seckill_session
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '场次名称',
   start_time           datetime comment '每日开始时间',
   end_time             datetime comment '每日结束时间',
   status               tinyint(1) comment '启用状态',
   create_time          datetime comment '创建时间',
   primary key (id)
);

alter table sms_seckill_session comment '秒杀活动场次';

drop table if exists sms_seckill_sku_notice;

/*==============================================================*/
/* Table: sms_seckill_sku_notice                                */
/*==============================================================*/
create table sms_seckill_sku_notice
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   sku_id               bigint comment 'sku_id',
   session_id           bigint comment '活动场次id',
   subcribe_time        datetime comment '订阅时间',
   send_time            datetime comment '发送时间',
   notice_type          tinyint(1) comment '通知方式[0-短信，1-邮件]',
   primary key (id)
);

alter table sms_seckill_sku_notice comment '秒杀商品通知订阅';

drop table if exists sms_home_adv;

/*==============================================================*/
/* Table: sms_home_adv                                          */
/*==============================================================*/
create table sms_home_adv
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(100) comment '名字',
   pic                  varchar(500) comment '图片地址',
   start_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   status               tinyint(1) comment '状态',
   click_count          int comment '点击数',
   url                  varchar(500) comment '广告详情连接地址',
   note                 varchar(500) comment '备注',
   sort                 int comment '排序',
   publisher_id         bigint comment '发布者',
   auth_id              bigint comment '审核者',
   primary key (id)
);

alter table sms_home_adv comment '首页轮播广告';

drop table if exists sms_home_subject;

/*==============================================================*/
/* Table: sms_home_subject                                      */
/*==============================================================*/
create table sms_home_subject
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '专题名字',
   title                varchar(255) comment '专题标题',
   sub_title            varchar(255) comment '专题副标题',
   status               tinyint(1) comment '显示状态',
   url                  varchar(500) comment '详情连接',
   sort                 int comment '排序',
   img                  varchar(500) comment '专题图片地址',
   primary key (id)
);

alter table sms_home_subject comment '首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】';

drop table if exists sms_home_subject_spu;

/*==============================================================*/
/* Table: sms_home_subject_spu                                  */
/*==============================================================*/
create table sms_home_subject_spu
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '专题名字',
   subject_id           bigint comment '专题id',
   spu_id               bigint comment 'spu_id',
   sort                 int comment '排序',
   primary key (id)
);

alter table sms_home_subject_spu comment '专题商品';

```

#### 商品阶梯价格表

```sql
drop table if exists sms_sku_ladder;

/*==============================================================*/
/* Table: sms_sku_ladder                                        */
/*==============================================================*/
create table sms_sku_ladder
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'spu_id',
   full_count           int comment '满几件',
   discount             decimal(4,2) comment '打几折',
   price                decimal(18,4) comment '折后价',
   add_other            tinyint(1) comment '是否叠加其他优惠[0-不可叠加，1-可叠加]',
   primary key (id)
);

alter table sms_sku_ladder comment '商品阶梯价格';

```



#### 商品会员价格

```sql
drop table if exists sms_member_price;

/*==============================================================*/
/* Table: sms_member_price                                      */
/*==============================================================*/
create table sms_member_price
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'sku_id',
   member_level_id      bigint comment '会员等级id',
   member_level_name    varchar(100) comment '会员等级名',
   member_price         decimal(18,4) comment '会员对应价格',
   add_other            tinyint(1) comment '可否叠加其他优惠[0-不可叠加优惠，1-可叠加]',
   primary key (id)
);

alter table sms_member_price comment '商品会员价格';

```

#### 商品满减信息表

```sql
drop table if exists sms_sku_full_reduction;

/*==============================================================*/
/* Table: sms_sku_full_reduction                                */
/*==============================================================*/
create table sms_sku_full_reduction
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint comment 'spu_id',
   full_price           decimal(18,4) comment '满多少',
   reduce_price         decimal(18,4) comment '减多少',
   add_other            tinyint(1) comment '是否参与其他优惠',
   primary key (id)
);

alter table sms_sku_full_reduction comment '商品满减信息';

```

#### 商品spu积分设置

```sql
drop table if exists sms_spu_bounds;

/*==============================================================*/
/* Table: sms_spu_bounds                                        */
/*==============================================================*/
create table sms_spu_bounds
(
   id                   bigint not null auto_increment comment 'id',
   spu_id               bigint,
   grow_bounds          decimal(18,4) comment '成长积分',
   buy_bounds           decimal(18,4) comment '购物积分',
   work                 tinyint(1) comment '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
   primary key (id)
);

alter table sms_spu_bounds comment '商品spu积分设置';
```

#### 优惠券信息

```sql
drop table if exists sms_coupon;

/*==============================================================*/
/* Table: sms_coupon                                            */
/*==============================================================*/
create table sms_coupon
(
   id                   bigint not null auto_increment comment 'id',
   coupon_type          tinyint(1) comment '优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]',
   coupon_img           varchar(2000) comment '优惠券图片',
   coupon_name          varchar(100) comment '优惠卷名字',
   num                  int comment '数量',
   amount               decimal(18,4) comment '金额',
   per_limit            int comment '每人限领张数',
   min_point            decimal(18,4) comment '使用门槛',
   start_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   use_type             tinyint(1) comment '使用类型[0->全场通用；1->指定分类；2->指定商品]',
   note                 varchar(200) comment '备注',
   publish_count        int(11) comment '发行数量',
   use_count            int(11) comment '已使用数量',
   receive_count        int(11) comment '领取数量',
   enable_start_time    datetime comment '可以领取的开始日期',
   enable_end_time      datetime comment '可以领取的结束日期',
   code                 varchar(64) comment '优惠码',
   member_level         tinyint(1) comment '可以领取的会员等级[0->不限等级，其他-对应等级]',
   publish              tinyint(1) comment '发布状态[0-未发布，1-已发布]',
   primary key (id)
);

alter table sms_coupon comment '优惠券信息';

```

#### 优惠券领取历史记录

```sql
drop table if exists sms_coupon_history;

/*==============================================================*/
/* Table: sms_coupon_history                                    */
/*==============================================================*/
create table sms_coupon_history
(
   id                   bigint not null auto_increment comment 'id',
   coupon_id            bigint comment '优惠券id',
   member_id            bigint comment '会员id',
   member_nick_name     varchar(64) comment '会员名字',
   get_type             tinyint(1) comment '获取方式[0->后台赠送；1->主动领取]',
   create_time          datetime comment '创建时间',
   use_type             tinyint(1) comment '使用状态[0->未使用；1->已使用；2->已过期]',
   use_time             datetime comment '使用时间',
   order_id             bigint comment '订单id',
   order_sn             bigint comment '订单号',
   primary key (id)
);

alter table sms_coupon_history comment '优惠券领取历史记录';

```

#### 优惠券分类关联表

```sql
drop table if exists sms_coupon_spu_category_relation;

/*==============================================================*/
/* Table: sms_coupon_spu_category_relation                      */
/*==============================================================*/
create table sms_coupon_spu_category_relation
(
   id                   bigint not null auto_increment comment 'id',
   coupon_id            bigint comment '优惠券id',
   category_id          bigint comment '产品分类id',
   category_name        varchar(64) comment '产品分类名称',
   primary key (id)
);

alter table sms_coupon_spu_category_relation comment '优惠券分类关联';

```

#### 优惠券与产品关联表

```sql
drop table if exists sms_coupon_spu_relation;

/*==============================================================*/
/* Table: sms_coupon_spu_relation                               */
/*==============================================================*/
create table sms_coupon_spu_relation
(
   id                   bigint not null auto_increment comment 'id',
   coupon_id            bigint comment '优惠券id',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(255) comment 'spu_name',
   primary key (id)
);

alter table sms_coupon_spu_relation comment '优惠券与产品关联';

```

#### 商品分类积分设置

```sql
drop table if exists sms_category_bounds;

/*==============================================================*/
/* Table: sms_category_bounds                                   */
/*==============================================================*/
create table sms_category_bounds
(
   id                   bigint not null auto_increment comment 'id',
   category_id          bigint,
   grow_bounds          decimal(18,4) comment '成长积分',
   buy_bounds           decimal(18,4) comment '购物积分',
   work                 tinyint(1) comment '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
   primary key (id)
);

alter table sms_category_bounds comment '商品分类积分设置';

```

#### 商品sku积分设置

```sql
drop table if exists sms_sku_bounds;

/*==============================================================*/
/* Table: sms_sku_bounds                                        */
/*==============================================================*/
create table sms_sku_bounds
(
   id                   bigint not null auto_increment comment 'id',
   sku_id               bigint,
   grow_bounds          decimal(18,4) comment '成长积分',
   buy_bounds           decimal(18,4) comment '购物积分',
   work                 tinyint(1) comment '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
   primary key (id)
);

alter table sms_sku_bounds comment '商品sku积分设置';

```

#### 秒杀活动表

```sql
drop table if exists sms_seckill_promotion;

/*==============================================================*/
/* Table: sms_seckill_promotion                                 */
/*==============================================================*/
create table sms_seckill_promotion
(
   id                   bigint not null auto_increment comment 'id',
   title                varchar(255) comment '活动标题',
   start_time           datetime comment '开始日期',
   end_time             datetime comment '结束日期',
   status               tinyint comment '上下线状态',
   create_time          datetime comment '创建时间',
   user_id              bigint comment '创建人',
   primary key (id)
);

alter table sms_seckill_promotion comment '秒杀活动';

```

#### 秒杀活动商品关联表

```sql
drop table if exists sms_seckill_sku_relation;

/*==============================================================*/
/* Table: sms_seckill_sku_relation                              */
/*==============================================================*/
create table sms_seckill_sku_relation
(
   id                   bigint not null auto_increment comment 'id',
   promotion_id         bigint comment '活动id',
   promotion_session_id bigint comment '活动场次id',
   sku_id               bigint comment '商品id',
   seckill_price        decimal comment '秒杀价格',
   seckill_count        decimal comment '秒杀总量',
   seckill_limit        decimal comment '每人限购数量',
   seckill_sort         int comment '排序',
   primary key (id)
);

alter table sms_seckill_sku_relation comment '秒杀活动商品关联';

```

#### 秒杀活动场次表

```sql
drop table if exists sms_seckill_session;

/*==============================================================*/
/* Table: sms_seckill_session                                   */
/*==============================================================*/
create table sms_seckill_session
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '场次名称',
   start_time           datetime comment '每日开始时间',
   end_time             datetime comment '每日结束时间',
   status               tinyint(1) comment '启用状态',
   create_time          datetime comment '创建时间',
   primary key (id)
);

alter table sms_seckill_session comment '秒杀活动场次';

```

#### 秒杀商品通知订阅表

```sql
drop table if exists sms_seckill_sku_notice;

/*==============================================================*/
/* Table: sms_seckill_sku_notice                                */
/*==============================================================*/
create table sms_seckill_sku_notice
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   sku_id               bigint comment 'sku_id',
   session_id           bigint comment '活动场次id',
   subcribe_time        datetime comment '订阅时间',
   send_time            datetime comment '发送时间',
   notice_type          tinyint(1) comment '通知方式[0-短信，1-邮件]',
   primary key (id)
);

alter table sms_seckill_sku_notice comment '秒杀商品通知订阅';

```

#### 首页轮播广告

```sql
drop table if exists sms_home_adv;

/*==============================================================*/
/* Table: sms_home_adv                                          */
/*==============================================================*/
create table sms_home_adv
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(100) comment '名字',
   pic                  varchar(500) comment '图片地址',
   start_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   status               tinyint(1) comment '状态',
   click_count          int comment '点击数',
   url                  varchar(500) comment '广告详情连接地址',
   note                 varchar(500) comment '备注',
   sort                 int comment '排序',
   publisher_id         bigint comment '发布者',
   auth_id              bigint comment '审核者',
   primary key (id)
);

alter table sms_home_adv comment '首页轮播广告';

```

#### 首页专题表

```sql
drop table if exists sms_home_subject;

/*==============================================================*/
/* Table: sms_home_subject                                      */
/*==============================================================*/
create table sms_home_subject
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '专题名字',
   title                varchar(255) comment '专题标题',
   sub_title            varchar(255) comment '专题副标题',
   status               tinyint(1) comment '显示状态',
   url                  varchar(500) comment '详情连接',
   sort                 int comment '排序',
   img                  varchar(500) comment '专题图片地址',
   primary key (id)
);

alter table sms_home_subject comment '首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】';

```

#### 专题商品表

```sql
drop table if exists sms_home_subject_spu;

/*==============================================================*/
/* Table: sms_home_subject_spu                                  */
/*==============================================================*/
create table sms_home_subject_spu
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(200) comment '专题名字',
   subject_id           bigint comment '专题id',
   spu_id               bigint comment 'spu_id',
   sort                 int comment '排序',
   primary key (id)
);

alter table sms_home_subject_spu comment '专题商品';

```



### 会员服务数据库(ums)

#### 生成代码概览

```sql
drop table if exists ums_member;

/*==============================================================*/
/* Table: ums_member                                            */
/*==============================================================*/
create table ums_member
(
   id                   bigint not null auto_increment comment 'id',
   level_id             bigint comment '会员等级id',
   username             char(64) comment '用户名',
   password             varchar(64) comment '密码',
   nickname             varchar(64) comment '昵称',
   mobile               varchar(20) comment '手机号码',
   email                varchar(64) comment '邮箱',
   header               varchar(500) comment '头像',
   gender               tinyint comment '性别',
   birth                date comment '生日',
   city                 varchar(500) comment '所在城市',
   job                  varchar(255) comment '职业',
   sign                 varchar(255) comment '个性签名',
   source_type          tinyint comment '用户来源',
   integration          int comment '积分',
   growth               int comment '成长值',
   status               tinyint comment '启用状态',
   create_time          datetime comment '注册时间',
   primary key (id)
);

alter table ums_member comment '会员';


drop table if exists ums_member_receive_address;

/*==============================================================*/
/* Table: ums_member_receive_address                            */
/*==============================================================*/
create table ums_member_receive_address
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   name                 varchar(255) comment '收货人姓名',
   phone                varchar(64) comment '电话',
   post_code            varchar(64) comment '邮政编码',
   province             varchar(100) comment '省份/直辖市',
   city                 varchar(100) comment '城市',
   region               varchar(100) comment '区',
   detail_address       varchar(255) comment '详细地址(街道)',
   areacode             varchar(15) comment '省市区代码',
   default_status       tinyint(1) comment '是否默认',
   primary key (id)
);

alter table ums_member_receive_address comment '会员收货地址';

drop table if exists ums_member_login_log;

/*==============================================================*/
/* Table: ums_member_login_log                                  */
/*==============================================================*/
create table ums_member_login_log
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   create_time          datetime comment '创建时间',
   ip                   varchar(64) comment 'ip',
   city                 varchar(64) comment 'city',
   login_type           tinyint(1) comment '登录类型[1-web，2-app]',
   primary key (id)
);

alter table ums_member_login_log comment '会员登录记录';

drop table if exists ums_member_level;

/*==============================================================*/
/* Table: ums_member_level                                      */
/*==============================================================*/
create table ums_member_level
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(100) comment '等级名称',
   growth_point         int comment '等级需要的成长值',
   default_status       tinyint comment '是否为默认等级[0->不是；1->是]',
   free_freight_point   decimal(18,4) comment '免运费标准',
   comment_growth_point int comment '每次评价获取的成长值',
   priviledge_free_freight tinyint comment '是否有免邮特权',
   priviledge_member_price tinyint comment '是否有会员价格特权',
   priviledge_birthday  tinyint comment '是否有生日特权',
   note                 varchar(255) comment '备注',
   primary key (id)
);

alter table ums_member_level comment '会员等级';

drop table if exists ums_member_collect_spu;

/*==============================================================*/
/* Table: ums_member_collect_spu                                */
/*==============================================================*/
create table ums_member_collect_spu
(
   id                   bigint not null comment 'id',
   member_id            bigint comment '会员id',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(500) comment 'spu_name',
   spu_img              varchar(500) comment 'spu_img',
   create_time          datetime comment 'create_time',
   primary key (id)
);

alter table ums_member_collect_spu comment '会员收藏的商品';

drop table if exists ums_member_collect_subject;

/*==============================================================*/
/* Table: ums_member_collect_subject                            */
/*==============================================================*/
create table ums_member_collect_subject
(
   id                   bigint not null auto_increment comment 'id',
   subject_id           bigint comment 'subject_id',
   subject_name         varchar(255) comment 'subject_name',
   subject_img          varchar(500) comment 'subject_img',
   subject_urll         varchar(500) comment '活动url',
   primary key (id)
);

alter table ums_member_collect_subject comment '会员收藏的专题活动';

drop table if exists ums_member_statistics_info;

/*==============================================================*/
/* Table: ums_member_statistics_info                            */
/*==============================================================*/
create table ums_member_statistics_info
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment '会员id',
   consume_amount       decimal(18,4) comment '累计消费金额',
   coupon_amount        decimal(18,4) comment '累计优惠金额',
   order_count          int comment '订单数量',
   coupon_count         int comment '优惠券数量',
   comment_count        int comment '评价数',
   return_order_count   int comment '退货数量',
   login_count          int comment '登录次数',
   attend_count         int comment '关注数量',
   fans_count           int comment '粉丝数量',
   collect_product_count int comment '收藏的商品数量',
   collect_subject_count int comment '收藏的专题活动数量',
   collect_comment_count int comment '收藏的评论数量',
   invite_friend_count  int comment '邀请的朋友数量',
   primary key (id)
);

alter table ums_member_statistics_info comment '会员统计信息';

drop table if exists ums_integration_change_history;

/*==============================================================*/
/* Table: ums_integration_change_history                        */
/*==============================================================*/
create table ums_integration_change_history
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   create_time          datetime comment 'create_time',
   change_count         int comment '变化的值',
   note                 varchar(255) comment '备注',
   source_tyoe          tinyint comment '来源[0->购物；1->管理员修改;2->活动]',
   primary key (id)
);

alter table ums_integration_change_history comment '积分变化历史记录';

drop table if exists ums_growth_change_history;

/*==============================================================*/
/* Table: ums_growth_change_history                             */
/*==============================================================*/
create table ums_growth_change_history
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   create_time          datetime comment 'create_time',
   change_count         int comment '改变的值（正负计数）',
   note                 varchar(0) comment '备注',
   source_type          tinyint comment '积分来源[0-购物，1-管理员修改]',
   primary key (id)
);

alter table ums_growth_change_history comment '成长值变化历史记录';

	
```



#### 会员表

```sql
drop table if exists ums_member;

/*==============================================================*/
/* Table: ums_member                                            */
/*==============================================================*/
create table ums_member
(
   id                   bigint not null auto_increment comment 'id',
   level_id             bigint comment '会员等级id',
   username             char(64) comment '用户名',
   password             varchar(64) comment '密码',
   nickname             varchar(64) comment '昵称',
   mobile               varchar(20) comment '手机号码',
   email                varchar(64) comment '邮箱',
   header               varchar(500) comment '头像',
   gender               tinyint comment '性别',
   birth                date comment '生日',
   city                 varchar(500) comment '所在城市',
   job                  varchar(255) comment '职业',
   sign                 varchar(255) comment '个性签名',
   source_type          tinyint comment '用户来源',
   integration          int comment '积分',
   growth               int comment '成长值',
   status               tinyint comment '启用状态',
   create_time          datetime comment '注册时间',
   primary key (id)
);

alter table ums_member comment '会员';

```

#### 会员统计信息

```sql
drop table if exists ums_member_statistics_info;

/*==============================================================*/
/* Table: ums_member_statistics_info                            */
/*==============================================================*/
create table ums_member_statistics_info
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment '会员id',
   consume_amount       decimal(18,4) comment '累计消费金额',
   coupon_amount        decimal(18,4) comment '累计优惠金额',
   order_count          int comment '订单数量',
   coupon_count         int comment '优惠券数量',
   comment_count        int comment '评价数',
   return_order_count   int comment '退货数量',
   login_count          int comment '登录次数',
   attend_count         int comment '关注数量',
   fans_count           int comment '粉丝数量',
   collect_product_count int comment '收藏的商品数量',
   collect_subject_count int comment '收藏的专题活动数量',
   collect_comment_count int comment '收藏的评论数量',
   invite_friend_count  int comment '邀请的朋友数量',
   primary key (id)
);

alter table ums_member_statistics_info comment '会员统计信息';

```



#### 会员登录记录

```sql
drop table if exists ums_member_login_log;

/*==============================================================*/
/* Table: ums_member_login_log                                  */
/*==============================================================*/
create table ums_member_login_log
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   create_time          datetime comment '创建时间',
   ip                   varchar(64) comment 'ip',
   city                 varchar(64) comment 'city',
   login_type           tinyint(1) comment '登录类型[1-web，2-app]',
   primary key (id)
);

alter table ums_member_login_log comment '会员登录记录';

```

#### 会员收货地址表

```sql
drop table if exists ums_member_receive_address;

/*==============================================================*/
/* Table: ums_member_receive_address                            */
/*==============================================================*/
create table ums_member_receive_address
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   name                 varchar(255) comment '收货人姓名',
   phone                varchar(64) comment '电话',
   post_code            varchar(64) comment '邮政编码',
   province             varchar(100) comment '省份/直辖市',
   city                 varchar(100) comment '城市',
   region               varchar(100) comment '区',
   detail_address       varchar(255) comment '详细地址(街道)',
   areacode             varchar(15) comment '省市区代码',
   default_status       tinyint(1) comment '是否默认',
   primary key (id)
);

alter table ums_member_receive_address comment '会员收货地址';

```

#### 会员等级表

```sql
drop table if exists ums_member_level;

/*==============================================================*/
/* Table: ums_member_level                                      */
/*==============================================================*/
create table ums_member_level
(
   id                   bigint not null auto_increment comment 'id',
   name                 varchar(100) comment '等级名称',
   growth_point         int comment '等级需要的成长值',
   default_status       tinyint comment '是否为默认等级[0->不是；1->是]',
   free_freight_point   decimal(18,4) comment '免运费标准',
   comment_growth_point int comment '每次评价获取的成长值',
   priviledge_free_freight tinyint comment '是否有免邮特权',
   priviledge_member_price tinyint comment '是否有会员价格特权',
   priviledge_birthday  tinyint comment '是否有生日特权',
   note                 varchar(255) comment '备注',
   primary key (id)
);

alter table ums_member_level comment '会员等级';

```

#### 会员收藏的商品表

```sql
drop table if exists ums_member_collect_spu;

/*==============================================================*/
/* Table: ums_member_collect_spu                                */
/*==============================================================*/
create table ums_member_collect_spu
(
   id                   bigint not null comment 'id',
   member_id            bigint comment '会员id',
   spu_id               bigint comment 'spu_id',
   spu_name             varchar(500) comment 'spu_name',
   spu_img              varchar(500) comment 'spu_img',
   create_time          datetime comment 'create_time',
   primary key (id)
);

alter table ums_member_collect_spu comment '会员收藏的商品';

```

#### 会员收藏的专题活动

```sql
drop table if exists ums_member_collect_subject;

/*==============================================================*/
/* Table: ums_member_collect_subject                            */
/*==============================================================*/
create table ums_member_collect_subject
(
   id                   bigint not null auto_increment comment 'id',
   subject_id           bigint comment 'subject_id',
   subject_name         varchar(255) comment 'subject_name',
   subject_img          varchar(500) comment 'subject_img',
   subject_urll         varchar(500) comment '活动url',
   primary key (id)
);

alter table ums_member_collect_subject comment '会员收藏的专题活动';

```

#### 积分变化历史记录表

```sql
drop table if exists ums_integration_change_history;

/*==============================================================*/
/* Table: ums_integration_change_history                        */
/*==============================================================*/
create table ums_integration_change_history
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   create_time          datetime comment 'create_time',
   change_count         int comment '变化的值',
   note                 varchar(255) comment '备注',
   source_tyoe          tinyint comment '来源[0->购物；1->管理员修改;2->活动]',
   primary key (id)
);

alter table ums_integration_change_history comment '积分变化历史记录';

```

#### 成长值变化历史记录表

```sql
drop table if exists ums_growth_change_history;

/*==============================================================*/
/* Table: ums_growth_change_history                             */
/*==============================================================*/
create table ums_growth_change_history
(
   id                   bigint not null auto_increment comment 'id',
   member_id            bigint comment 'member_id',
   create_time          datetime comment 'create_time',
   change_count         int comment '改变的值（正负计数）',
   note                 varchar(0) comment '备注',
   source_type          tinyint comment '积分来源[0-购物，1-管理员修改]',
   primary key (id)
);

alter table ums_growth_change_history comment '成长值变化历史记录';

```

## 搭建人人开源后台管理系统

人人开源后台管理系统后端源码：[点击进入](https://gitee.com/renrenio/renren-fast)

人人开源后台管理系统前端源码：[点击进入](https://gitee.com/renrenio/renren-fast-vue)

## 搭建人人开源逆向工程

renren-generator项目地址：[点击进入](https://gitee.com/renrenio/renren-generator)

## 通过renren-generator为每个服务模块创建CRUD

### 配置MybatisPlus及数据库信息

创建或修改application.yml

```yaml
spring:
  datasource:
    username: root
    password: root
    # 数据库url
    url: jdbc:mysql://192.168.56.10:3306/gulimall_pms
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
```

## 配置公共微服务模块(common)

### 说明

每个微服务的公共依赖，Bean，工具类等，每个微服务都应导入此依赖

### 导入依赖

```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.20</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.1</version>
        </dependency>
        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>2.9.9</version>
        </dependency>
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-core</artifactId>
            <version>9.0.58</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpcore</artifactId>
            <version>4.4.13</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.32</version>
        </dependency>

```

# SpringCloudAlibaba介绍

SpringCloudAlibaba介绍文档：[点击进入](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html#_%E4%BB%8B%E7%BB%8D)

## 导入SpringCloudAlibaba的组件信息

```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.2.7.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

版本选择：

|     Spring Cloud Alibaba版本      | Spring Cloud版本 | Spring Boot版本 |
| :-------------------------------: | :--------------: | :-------------: |
|            2.2.10-RC1*            |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.9.RELEASE           |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.8.RELEASE           |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.7.RELEASE           |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.6.RELEASE           |    Hoxton.SR9    |  2.3.2.RELEASE  |
|           2.2.1.RELEASE           |    Hoxton.SR3    |  2.2.5.RELEASE  |
|           2.2.0.RELEASE           |  Hoxton.RELEASE  |  2.2.X.RELEASE  |
|           2.1.4.RELEASE           |  Greenwich.SR6   | 2.1.13.RELEASE  |
|           2.1.2.RELEASE           |    Greenwich     |  2.1.X.RELEASE  |
| 2.0.4.RELEASE(停止维护，建议升级) |     Finchley     |  2.0.X.RELEASE  |
| 1.5.1.RELEASE(停止维护，建议升级) |     Edgware      |  1.5.X.RELEASE  |

## Nacos注册中心和配置中心组件

### 配置Nacos-Server

教程地址：[点击进入](https://nacos.io/zh-cn/docs/quick-start.html)

Docker部署Nacos-Server：

1. 拉取Nacos-Server镜像

   ```shell
   docker pull nacos/nacos-server:2.0.3
   ```

2. 挂载目录

   ```shell
   # 新建logs目录
   mkdir -p /mydata/nacos/logs/
   # 新建配置文件目录
   mkdir -p /mydata/nacos/conf/
   ```

3. 添加配置文件

   ```shell
   vi /mydata/nacos/conf/application.properties
   ```

   ```shell
   #
   # Copyright 1999-2021 Alibaba Group Holding Ltd.
   #
   # Licensed under the Apache License, Version 2.0 (the "License");
   # you may not use this file except in compliance with the License.
   # You may obtain a copy of the License at
   #
   #      http://www.apache.org/licenses/LICENSE-2.0
   #
   # Unless required by applicable law or agreed to in writing, software
   # distributed under the License is distributed on an "AS IS" BASIS,
   # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   # See the License for the specific language governing permissions and
   # limitations under the License.
   #
   
   #*************** Spring Boot Related Configurations ***************#
   ### Default web context path:
   server.servlet.contextPath=/nacos
   ### Default web server port:
   server.port=8848
   
   #*************** Network Related Configurations ***************#
   ### If prefer hostname over ip for Nacos server addresses in cluster.conf:
   # nacos.inetutils.prefer-hostname-over-ip=false
   
   ### Specify local server's IP:
   #nacos.inetutils.ip-address=192.168.56.11
   
   
   #*************** Config Module Related Configurations ***************#
   ### If use MySQL as datasource:
   spring.datasource.platform=mysql
   
   ### Count of DB:
   db.num=1
   
   ### Connect URL of DB:
   db.url.0=jdbc:mysql://192.168.56.10:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
   db.user.0=root
   db.password.0=root
   
   ### Connection pool configuration: hikariCP
   db.pool.config.connectionTimeout=30000
   db.pool.config.validationTimeout=10000
   db.pool.config.maximumPoolSize=20
   db.pool.config.minimumIdle=2
   
   #*************** Naming Module Related Configurations ***************#
   ### Data dispatch task execution period in milliseconds: Will removed on v2.1.X, replace with nacos.core.protocol.distro.data.sync.delayMs
   # nacos.naming.distro.taskDispatchPeriod=200
   
   ### Data count of batch sync task: Will removed on v2.1.X. Deprecated
   # nacos.naming.distro.batchSyncKeyCount=1000
   
   ### Retry delay in milliseconds if sync task failed: Will removed on v2.1.X, replace with nacos.core.protocol.distro.data.sync.retryDelayMs
   # nacos.naming.distro.syncRetryDelay=5000
   
   ### If enable data warmup. If set to false, the server would accept request without local data preparation:
   # nacos.naming.data.warmup=true
   
   ### If enable the instance auto expiration, kind like of health check of instance:
   # nacos.naming.expireInstance=true
   
   ### will be removed and replaced by `nacos.naming.clean` properties
   nacos.naming.empty-service.auto-clean=true
   nacos.naming.empty-service.clean.initial-delay-ms=50000
   nacos.naming.empty-service.clean.period-time-ms=30000
   
   ### Add in 2.0.0
   ### The interval to clean empty service, unit: milliseconds.
   # nacos.naming.clean.empty-service.interval=60000
   
   ### The expired time to clean empty service, unit: milliseconds.
   # nacos.naming.clean.empty-service.expired-time=60000
   
   ### The interval to clean expired metadata, unit: milliseconds.
   # nacos.naming.clean.expired-metadata.interval=5000
   
   ### The expired time to clean metadata, unit: milliseconds.
   # nacos.naming.clean.expired-metadata.expired-time=60000
   
   ### The delay time before push task to execute from service changed, unit: milliseconds.
   # nacos.naming.push.pushTaskDelay=500
   
   ### The timeout for push task execute, unit: milliseconds.
   # nacos.naming.push.pushTaskTimeout=5000
   
   ### The delay time for retrying failed push task, unit: milliseconds.
   # nacos.naming.push.pushTaskRetryDelay=1000
   
   ### Since 2.0.3
   ### The expired time for inactive client, unit: milliseconds.
   # nacos.naming.client.expired.time=180000
   
   #*************** CMDB Module Related Configurations ***************#
   ### The interval to dump external CMDB in seconds:
   # nacos.cmdb.dumpTaskInterval=3600
   
   ### The interval of polling data change event in seconds:
   # nacos.cmdb.eventTaskInterval=10
   
   ### The interval of loading labels in seconds:
   # nacos.cmdb.labelTaskInterval=300
   
   ### If turn on data loading task:
   # nacos.cmdb.loadDataAtStart=false
   
   
   #*************** Metrics Related Configurations ***************#
   ### Metrics for prometheus
   #management.endpoints.web.exposure.include=*
   
   ### Metrics for elastic search
   management.metrics.export.elastic.enabled=false
   #management.metrics.export.elastic.host=http://localhost:9200
   
   ### Metrics for influx
   management.metrics.export.influx.enabled=false
   #management.metrics.export.influx.db=springboot
   #management.metrics.export.influx.uri=http://localhost:8086
   #management.metrics.export.influx.auto-create-db=true
   #management.metrics.export.influx.consistency=one
   #management.metrics.export.influx.compressed=true
   
   #*************** Access Log Related Configurations ***************#
   ### If turn on the access log:
   server.tomcat.accesslog.enabled=true
   
   ### The access log pattern:
   server.tomcat.accesslog.pattern=%h %l %u %t "%r" %s %b %D %{User-Agent}i %{Request-Source}i
   
   ### The directory of access log:
   server.tomcat.basedir=
   
   #*************** Access Control Related Configurations ***************#
   ### If enable spring security, this option is deprecated in 1.2.0:
   #spring.security.enabled=false
   
   ### The ignore urls of auth, is deprecated in 1.2.0:
   nacos.security.ignore.urls=/,/error,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-ui/public/**,/v1/auth/**,/v1/console/health/**,/actuator/**,/v1/console/server/**
   
   ### The auth system to use, currently only 'nacos' and 'ldap' is supported:
   nacos.core.auth.system.type=nacos
   
   ### If turn on auth system:
   nacos.core.auth.enabled=false
   
   ### worked when nacos.core.auth.system.type=ldap，{0} is Placeholder,replace login username
   # nacos.core.auth.ldap.url=ldap://localhost:389
   # nacos.core.auth.ldap.userdn=cn={0},ou=user,dc=company,dc=com
   
   ### The token expiration in seconds:
   nacos.core.auth.default.token.expire.seconds=18000
   
   ### The default token:
   nacos.core.auth.default.token.secret.key=SecretKey012345678901234567890123456789012345678901234567890123456789
   
   ### Turn on/off caching of auth information. By turning on this switch, the update of auth information would have a 15 seconds delay.
   nacos.core.auth.caching.enabled=true
   
   ### Since 1.4.1, Turn on/off white auth for user-agent: nacos-server, only for upgrade from old version.
   nacos.core.auth.enable.userAgentAuthWhite=false
   
   ### Since 1.4.1, worked when nacos.core.auth.enabled=true and nacos.core.auth.enable.userAgentAuthWhite=false.
   ### The two properties is the white list for auth and used by identity the request from other server.
   nacos.core.auth.server.identity.key=serverIdentity
   nacos.core.auth.server.identity.value=security
   
   #*************** Istio Related Configurations ***************#
   ### If turn on the MCP server:
   nacos.istio.mcp.server.enabled=false
   
   #*************** Core Related Configurations ***************#
   
   ### set the WorkerID manually
   # nacos.core.snowflake.worker-id=
   
   ### Member-MetaData
   # nacos.core.member.meta.site=
   # nacos.core.member.meta.adweight=
   # nacos.core.member.meta.weight=
   
   ### MemberLookup
   ### Addressing pattern category, If set, the priority is highest
   # nacos.core.member.lookup.type=[file,address-server]
   ## Set the cluster list with a configuration file or command-line argument
   # nacos.member.list=192.168.16.101:8847?raft_port=8807,192.168.16.101?raft_port=8808,192.168.16.101:8849?raft_port=8809
   ## for AddressServerMemberLookup
   # Maximum number of retries to query the address server upon initialization
   # nacos.core.address-server.retry=5
   ## Server domain name address of [address-server] mode
   # address.server.domain=jmenv.tbsite.net
   ## Server port of [address-server] mode
   # address.server.port=8080
   ## Request address of [address-server] mode
   # address.server.url=/nacos/serverlist
   
   #*************** JRaft Related Configurations ***************#
   
   ### Sets the Raft cluster election timeout, default value is 5 second
   # nacos.core.protocol.raft.data.election_timeout_ms=5000
   ### Sets the amount of time the Raft snapshot will execute periodically, default is 30 minute
   # nacos.core.protocol.raft.data.snapshot_interval_secs=30
   ### raft internal worker threads
   # nacos.core.protocol.raft.data.core_thread_num=8
   ### Number of threads required for raft business request processing
   # nacos.core.protocol.raft.data.cli_service_thread_num=4
   ### raft linear read strategy. Safe linear reads are used by default, that is, the Leader tenure is confirmed by heartbeat
   # nacos.core.protocol.raft.data.read_index_type=ReadOnlySafe
   ### rpc request timeout, default 5 seconds
   # nacos.core.protocol.raft.data.rpc_request_timeout_ms=5000
   
   #*************** Distro Related Configurations ***************#
   
   ### Distro data sync delay time, when sync task delayed, task will be merged for same data key. Default 1 second.
   # nacos.core.protocol.distro.data.sync.delayMs=1000
   
   ### Distro data sync timeout for one sync data, default 3 seconds.
   # nacos.core.protocol.distro.data.sync.timeoutMs=3000
   
   ### Distro data sync retry delay time when sync data failed or timeout, same behavior with delayMs, default 3 seconds.
   # nacos.core.protocol.distro.data.sync.retryDelayMs=3000
   
   ### Distro data verify interval time, verify synced data whether expired for a interval. Default 5 seconds.
   # nacos.core.protocol.distro.data.verify.intervalMs=5000
   
   ### Distro data verify timeout for one verify, default 3 seconds.
   # nacos.core.protocol.distro.data.verify.timeoutMs=3000
   
   ### Distro data load retry delay when load snapshot data failed, default 30 seconds.
   # nacos.core.protocol.distro.data.load.retryDelayMs=30000
   ```

4. 启动容器

   ```shell
   # 普通启动命令
   docker run --rm -e MODE=standalone --name nacos -p 8848:8848 -p 9848:9848 -p 9849:9849 -d nacos/nacos-server:2.0.3
   # 带配置信息的启动
   docker  run --name nacos -p 8848:8848 -p 9848:9848 -p 9849:9849 \
   --privileged=true \
   --restart=always \
   -e JVM_XMS=128m \
   -e JVM_XMX=128m \
   -e MODE=standalone \
   -e PREFER_HOST_MODE=hostname \
   -v /mydata/nacos/logs:/home.vue/nacos/logs \
   -v /mydata/nacos/conf/application.properties:/home.vue/nacos/conf/application.properties \
   -d nacos/nacos-server:2.0.3
   ```

5. 设置容器自启动

   ```shell
   docker update --restart=always nacos
   ```

### 启动注册中心

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
   </dependency>
   ```

2. 在../src/main/resources/application.yml中配置Nacos Server地址和应用名

   ```xml
   # nacos服务器地址
   spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
   # 应用名
   spring.application.name=example
   ```

3. 启用@EnableDiscoveryClient注解

   ```java
   @EnableDiscoveryClient
   @SpringBootApplication
   public class SpringBootTestApplication {
       public static void main(String[] args) {
           SpringApplication.run(SpringBootTestApplication.class, args);
       }
   }
   ```

### 启动配置管理

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
   </dependency>
   ```

2. 在../src/main/resources/bootstrap.properties中配置配置中心信息

   ```xml
   spring.application.name=example
   spring.cloud.nacos.config.server-addr=127.0.0.1:8848
   ```

3. 在配置中心新建**数据集(Data Id)**，默认命名规则：应用名.properties

4. 动态获取配置，@RefreshScope 、@Value("${配置项名称}")

   如果配置文件和配置中心配置相同的项，则优先使用配置中心的配置

   ```java
   @RefreshScope
   @RestController
   @RequestMapping("test")
   public class TestController{
       @Value(${test.value})
       String name;
   }
   ```

***细节***

1. 命名空间：配置隔离

   - 默认命名空间：public    默认新增的所有配置都在public空间中。

   - 基于环境配置隔离：利用命名空间做环境隔离。注意：需要在bootstrap.properties配置上需要使用的环境命名空间

   - 基于微服务配置隔离：每一个微服务之间配置相互隔离，每一个微服务使用一个命名空间

     ```xml
     # 在bootstrap.properties中配置命名空间
     spring.cloud.nacos.config.namespace=
     ```

2. 配置集：所有配置的集合

3. 配置集ID(Data ID)：类似于文件名

4. 配置分组：

   - 默认所有配置集都属于：DEFAULT_GROUP

     ```xml
     # 在bootstrap.properties中配置配置组
     spring.cloud.nacos.config.group=DEFAULT_GROUP
     ```

5. 加载多个配置集

   - 加载多个配置集模板

     ```xml
     # 配置集ID
     spring.cloud.nacos.config.extension-configs[x].data-id=example.yml
     # 配置分组组名
     spring.cloud.nacos.config.extension-configs[x].group=example
     # 是否开启动态刷新
     spring.cloud.nacos.config.extension-configs[x].refresh=true
     ```

   - 在配置中心读取数据库信息

     ```xml
     # 在bootstrap.properties中配置数据库配置信息
     spring.cloud.nacos.config.extension-configs[0].data-id=datasource.yml
     spring.cloud.nacos.config.extension-configs[0].group=dev
     spring.cloud.nacos.config.extension-configs[0].refresh=true
     ```

   - 在配置中心读取Mybatis

     ```xml
     
     ```

   - 在配置中心读取其他信息

     ```xml
     
     ```

## OpenFeign远程调用服务

1. 导入依赖

2. 使用@EnableFeignClients启用远程调用服务

   ```java
   @EnableFeignClients()
   @SpringBootApplication
   public class SpringBootTestApplication {
       public static void main(String[] args) {
           SpringApplication.run(SpringBootTestApplication.class, args);
       }
   }
   ```

3. 使用@FeignClient注解实现调用远程服务接口

   ```java
   //参数为nacos的服务ID
   @FeignClient("test")
   public interface TestFeignService {
       //要调用的服务url
       @RequestMapping("coupon/smscoupon/test")
        public R get();
   }
   ```

## GateWay网关组件

## 



# MybatisPlus用法介绍

## 逻辑删除

1.    修改mabatis-plus全局信息

   - 例: application.yml

   ```yaml
   mybatis-plus:
     global-config:
       db-config:
         logic-delete-field: flag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
         logic-delete-value: 1 # 逻辑已删除值(默认为 1)
         logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
   ```

2.   实体类字段上加上`@TableLogic`注解

   ```java
   @TableLogic
   private Integer deleted;
   ```

# ElasticSearch用法介绍

## 基本概念

- 索引：与数据库DataBase类似，是ElasticSearch的最上级目录。

- 类型：与数据库的表类似。

- 文档：与数据库中表内的数据行列字段值。

## 使用docker部署ElasticSearch

```shell
docker pull elasticsearch:7.17.9

mkdir -p /mydata/elasticsearch/config
mkdir -p /mydata/elasticsearch/data
echo "http.host: 0.0.0.0" >> /mydata/elasticsearch/config/elasticsearch.yml

# 设置文件可读可写权限
chmod -R 777 /mydata/elasticsearch/

docker run --name elasticsearch -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms64m -Xmx512m" \
-v /mydata/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data \
-v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.17.9
```

## 基本命令

### _cat命令

```shell
#查询ElasticSearch的健康状况   
GET /_cat/health
#查询ElasticSearch的主节点信息  
GET /_cat/master
#查询ElasticSearch的所有节点
GET /_cat/nodes
#查询ElasticSearch的所有索引
GER /_cat/indices
```

## ES入门

###   索引创建、查询、删除

- 创建索引

  ```shell
  # 创建名为name的索引,请求为PUT请求
  PUT /{name}
  ```

- 查询索引

  ```shell
  # 查询名为name的索引信息，请求为GET请求
  GET /{name}
  ```

- 删除索引

  ```shell
  # 删除名为name的索引，请求为DELETE请求
  DELETE /{name}
  ```

### 文档创建、更新、删除、查询

- 创建文档

  ```shell
  # 创建文档
  # 参数:
  # name: 索引名称
  # id: 自定义文档id(选填)
  POST /{name}/_doc/{id}
  POST /{name}/_create/{id}
  
  # 请求体为文档信息
  {
  	"name":"zhangsiyao",
  	"age":17,
  }
  ```

- 查询文档

  主键查询

  ```shell
  # 查询主键为id的文档
  # 参数:
  # name: 索引名称
  # id: 文档id
  GET /{name}/_doc/{id}
  ```

  全查询

  ```shell
  # 查询索引内的所有文档
  # 参数:
  # name: 索引名称
  GET /{name}/_search
  ```

  

- 删除文档

# 数据检验

## 基本校验

1.导入依赖

```xml
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>

<!--springboot 新版本需要validation启动器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

2. 常用校验注解

   | 注解                        | 类型                                      | 说明                                                         |
   | :-------------------------- | ----------------------------------------- | ------------------------------------------------------------ |
   | @Null                       | Object                                    | 被注释的元素必须为 null                                      |
   | @NotNull                    | Object                                    | 被注释的元素必须不为 null                                    |
   | @AssertTrue                 | boolean/Boolean                           | 被注释的元素必须为 true                                      |
   | @AssertFalse                | boolean/Boolean                           | 被注释的元素必须为 false                                     |
   | @Min(value)                 | BigDecimal/BigInteger/byte/short/int/long | 被注释数字必须大于等于指定的最小值                           |
   | @Max(value)                 | BigDecimal/BigInteger/byte/short/int/long | 被注释数字必须小于等于指定的最大值                           |
   | @Range(min=,max=)           | BigDecimal/BigInteger/byte/short/int/long | 被注释数字必须在[min,max]区间内                              |
   | @DecimalMin(value)          | BigDecimal/BigInteger/byte/short/int/long | 被注释的数字必须大于等于指定的最小值                         |
   | @DecimalMax(value)          | BigDecimal/BigInteger/byte/short/int/long | 被注释的数字必须小于等于指定的最大值                         |
   | @Size(max=, min=)           | CharSequence/Collection/Map/Array         | 被注释的集合、字符串的长度必须在指定的范围内                 |
   | @Digits (integer, fraction) | BigDecimal/BigInteger/byte/short/int/long | 被注释的元素必须是一个数字，且满足 integer 参数表示整数位数最大值，fraction 表示小数位数的最大值 |
   | @Past                       | Date/Calendar                             | 被注释的日期必须是一个过去的日期                             |
   | @Future                     | Date/Calendar                             | 被注释的日期必须是一个将来的日期                             |
   | @Pattern(regex=,flag=)      | String                                    | 被注释的字符串必须符合指定的正则表达式                       |
   | @NotBlank                   | String                                    | 验证字符串非null，且长度必须大于0                            |
   | @Length(min=,max=)          | String                                    | 被注释的字符串的长度必须在指定的范围内                       |
   | @NotEmpty                   | String/collection/map/array               | 被注释的字符串或集合不为空                                   |
   | @Email                      | String                                    | 被注释的字符串必须是电子邮箱地址                             |

   3. 启用校验，需要在请求参数上添加@Valid启动校验

      - 加上 `@valid` 后台验证，在验证的参数后加 `BindingResult` 就可以获取验证结果，不获取结果会抛出 `MethodArgumentNotValidException` 异常

      ```java
      @PostMapping(value = "/createStudent")
      public R save(@Valid @RequestBody Student student, BindingResult result){
          if (result.hasErrors()) {
              Map<String , String > map = new HashMap<>();
              result.getFieldErrors().forEach(item ->{
                  String msg = item.getDefaultMessage();
                  String field = item.getField();
                  map.put(field, msg);
              });
              return R.error(400, "提交的数据不合法").put("data", map);
          }
      
      	studentService.save(student);
      
          return R.ok();
      }
      ```

## 分组校验

1. 创建校验接口，用于标记校验分组信息

   ```java
   public interface AddGroup {
   }
   
   public interface UpdateGroup {
   }
   ```

2. 使用 `@Validated` 注解来指定使用哪个分组的校验规则，如果不指定，则只会校验没有指定分组规则，所有有 `group` 参数的规则都不会校验

   ```java
   @RequestMapping("/save")
   public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand){
   	brandService.save(brand);
       return R.ok();
   }
   ```

3. 通过 `groups` 参数给字段上的注解分组，可以指定多个

   ```java
   @NotNull(message = "修改必须指定品牌id", groups = {UpdateGroup.class})
   @Null(message = "新增不能指定id", groups = {AddGroup.class})
   private Long brandId;
   ```

## 嵌套校验

1. 当实体类的字段为实体类或者为 `List<Object>` 时，要使用嵌套校验才可以校验内部的实体类， 要在字段上再加 `@Valid` 注解

   ```java
   @Valid
   @NotNull(message = "departure不能为空")
   private List<Departure> departure;
   ```
## 自定义校验注解
1. 定义校验注解

   ```java
   @Documented
   //标记自定义校验器的类
   @Constraint(validatedBy = {ListValueConstraintValidator.class})
   @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
   @Retention(RUNTIME)
   public @interface ListValue {
       //根据规范，每个校验注解都必须此参数
       String message() default "{com.atguigu.common.valid.ListValue.message}";
       //根据规范，每个校验注解都必须此参数
       Class<?>[] groups() default {};
       //根据规范，每个校验注解都必须此参数
       Class<? extends Payload>[] payload() default {};
       
       int[] vals() default {};
   }
   ```

2. 实现自定义校验器

   ```java
   public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
   
       private Set<Integer> set = new HashSet<>();
   
   	/**
   	 * 初始化方法，一般用于将自定义注解中的参数缓存
   	 * @param constraintAnnotation  参数是自定义的注解
   	 */
       @Override
       public void initialize(ListValue constraintAnnotation) {
           int[] vals = constraintAnnotation.vals();
           for (int val : vals) {
               set.add(val);
           }
       }
   
       /**
        * @param value	传过来的参数
        * @param context
        * @return	true 校验通过
        */
       @Override
       public boolean isValid(Integer value, ConstraintValidatorContext context) {
           return set.contains(value);
       }
   }
   ```

# 前端基础知识

## ES6

### let和var的区别

1. var声明的变量往往会越狱，let声明的变量只能在作用域内使用。

   ```javascript
   {
   	var a=1;
   	let b=2;
   }
   console.log(a);//1
   console.log(b);//ReferenceError: b is not defined
   ```

2. var声明的变量可以多次声明，let声明的变量多次声明会报错。

   ```javascript
   var m=1;
   var m=2;
   let n=1;
   let n=2;//Identifier 'n' has already been declared
   ```

### const变量(常量)

### 解构表达式

1. 数组解构

   ```javascript
   let arr=[1,2,3];
   let [a,b,c]=arr;
   console.log(a,b,c);//output: 1,2,3
   ```

2. 对象解构

   ```javascript
   const person={
       name: "jack",
       age: 21,
       language: ['java','js','c++']
   }
   const {name,age,language}=person;
   console.log(name,age,language)
   ```

### 字符串

1. 字符串扩展

2. 字符串模板

   - 长段落

   ```javascript
   let ss=`<div>
       <span>Hello World</span>
   </div>`;
   console.log(ss);
   ```

	- 字符串插入

    ```javascript
    let person={
        name:"zhangsiyao1",
        age: 21
    }
    let info=`名字:${name},年龄:${age}`;
    console.log(info)
    ```
   
   - 调用方法插入
   
   ```javascript
   function f(){
       return "这是一个函数";
   }
   console.log(`${f()}`)
   ```
   
### 方法

1. 方法默认值

   ```javascript
   function f(a,b=1){
       console.log(a,b)
   }
   ```

2. 方法不定参数

   ```javascript
   function f(...args){
       console.log(args.length)
   }
   ```

3. lambda表达式

   ```javascript
   let p= (a,b)=>{return a*b};
   ```

### 对象优化

1. 对象合并

   ```javascript
   const target={a:1};
   const s1={b:2};
   const s2={c:3}
   console.log(Object.assign(target,s1,s2));//output:{a:1,b:2,c:3}
   //运算符合并
   const result={...target,...s1,...s2};
   ```

2. 深拷贝对象

   ```javascript
   const target={a:1};
   const result={...target}
   ```

### Promise对象的使用

1. 作用：代替多层ajax嵌套请求，简化代码，方便理解

2. 示例代码：

   ```javascript
   function get(url,data){
       return new Promise((resolve,reject) => {
           $.ajax({
               url: url,
               data: data,
               success: function (data){
                   resolve(data);
               },
               error: function (err){
                   reject(err);
               }
           })
       });
   }
    get("test")
           .then(data =>{
               return get("test1")
           }).then(data=>{
               return get("test2")   
           })
   ```
   

## Vue

# 项目开发

# 常见问题

## SpringBoot及SpringCloud版本选择

|   SpringCloud版本    |         兼容的SpringBoot版本          |
| :------------------: | :-----------------------------------: |
| 2022.0.x aka Kilburn |                 3.0.x                 |
| 2021.0.x aka Jubilee | 2.6.x, 2.7.x (Starting with 2021.0.3) |
| 2020.0.x aka Ilford  | 2.4.x, 2.5.x (Starting with 2020.0.3) |
|        Hoxton        |   2.2.x, 2.3.x (Starting with SR5)    |
|      Greenwich       |                 2.1.x                 |
|       Finchley       |                 2.0.x                 |
|       Edgware        |                 1.5.x                 |
|       Dalston        |                 1.5.x                 |


## SpringCloudAlibaba及其组件版本选择

SpringCloudAlibaba版本说明:[点击进入](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

|     Spring Cloud Alibaba版本      | Spring Cloud版本 | Spring Boot版本 |
| :-------------------------------: | :--------------: | :-------------: |
|            2.2.10-RC1*            |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.9.RELEASE           |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.8.RELEASE           |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.7.RELEASE           |   Hoxton.SR12    | 2.3.12.RELEASE  |
|           2.2.6.RELEASE           |    Hoxton.SR9    |  2.3.2.RELEASE  |
|           2.2.1.RELEASE           |    Hoxton.SR3    |  2.2.5.RELEASE  |
|           2.2.0.RELEASE           |  Hoxton.RELEASE  |  2.2.X.RELEASE  |
|           2.1.4.RELEASE           |  Greenwich.SR6   | 2.1.13.RELEASE  |
|           2.1.2.RELEASE           |    Greenwich     |  2.1.X.RELEASE  |
| 2.0.4.RELEASE(停止维护，建议升级) |     Finchley     |  2.0.X.RELEASE  |
| 1.5.1.RELEASE(停止维护，建议升级) |     Edgware      |  1.5.X.RELEASE  |

SpringCloudAlibaba组件版本对照：

|                 Spring Cloud Alibaba版本                  | Sentinel版本 | Nacos版本 | RocketMQ版本 | Dubbo版本 | Seata版本 |
| :-------------------------------------------------------: | :----------: | :-------: | :----------: | :-------: | :-------: |
|                        2021.0.1.0                         |    1.8.3     |   1.4.2   |    4.9.2     |  2.7.15   |   1.4.2   |
|                       2.2.7.RELEASE                       |    1.8.1     |   2.0.3   |    4.6.1     |  2.7.13   |   1.3.0   |
|                       2.2.6.RELEASE                       |    1.8.1     |   1.4.2   |    4.4.0     |   2.7.8   |   1.3.0   |
| 2021.1 or 2.2.5.RELEASE or 2.1.4.RELEASE or 2.0.4.RELEASE |    1.8.0     |   1.4.1   |    4.4.0     |   2.7.8   |   1.3.0   |
|      2.2.3.RELEASE or 2.1.3.RELEASE or 2.0.3.RELEASE      |    1.8.0     |   1.3.3   |    4.4.0     |   2.7.8   |   1.3.0   |
|      2.2.1.RELEASE or 2.1.2.RELEASE or 2.0.2.RELEASE      |    1.7.1     |   1.2.1   |    4.4.0     |   2.7.6   |   1.2.0   |
|                       2.2.0.RELEASE                       |    1.7.1     |   1.1.4   |    4.4.0     |  2.7.4.1  |   1.0.0   |
|      2.1.1.RELEASE or 2.0.1.RELEASE or 1.5.1.RELEASE      |    1.7.0     |   1.1.4   |    4.4.0     |   2.7.3   |   0.9.0   |
|      2.1.0.RELEASE or 2.0.0.RELEASE or 1.5.0.RELEASE      |    1.6.3     |   1.1.1   |    4.4.0     |   2.7.3   |   0.7.1   |

## 解决跨域请求问题

### 开发时临时解决

1. 新建配置类

2. 配置允许的跨域请求

   ```java
   @Configuration
   public class GateWayCorsConfiguration {
       
       @Bean
       public CorsWebFilter corsWebFilter(){
           UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
   
           //在此类配置跨域
           CorsConfiguration corsConfiguration=new CorsConfiguration();
           //配置跨域
           corsConfiguration.addAllowedHeader("*");
           corsConfiguration.addAllowedMethod("*");
           corsConfiguration.addAllowedOrigin("*");
           corsConfiguration.setAllowCredentials(true);
   
           source.registerCorsConfiguration("/**",corsConfiguration);
           return new CorsWebFilter(source);
       }
   }
   ```

### 部署nagix

## Nacos注册中心服务使用命名空间，导致无法使用服务
解决方案：使用服务发现时，不要指定命名空间
