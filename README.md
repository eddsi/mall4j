![输入图片说明](https://images.gitee.com/uploads/images/2019/0711/174845_6db7724e_5094767.png "商城.png")
Mall4j开源商城，一个基于spring boot、spring oauth2.0、mybatis、redis的轻量级、前后端分离、防范xss攻击、拥有分布式锁，为生产环境多实例完全准备，数据库为b2b2c设计，拥有完整sku和下单流程的开源商城

## Spring以及VUE官方宣布，SpringBoot2与Vue2已在2023年底停止维护。新项目建议使用SpringBoot3+Vue3的组合，本商城已完成升级!!!

## 前言

`Mall4j`商城系统致力于为中小企业打造一个完整、易于维护的开源的电商商城系统，采用现阶段流行技术实现。后台管理系统包含商品管理、订单管理、运费模板、规格管理、会员管理、运营管理、内容管理、统计报表、权限管理、设置等模块。开源版本商城属于B2C单商户商城系统，不含营销活动，如需更多模式的商城请查看[Mall4j商城官网](https://www.mall4j.com)。

## 商城文档

这代码有没有文档呀？ 当然有啦，你已经下载了，在doc这个文件夹上，实在不知道，我就给链接出来咯：

gitee：https://gitee.com/gz-yami/mall4j/tree/master/doc

看云：https://www.kancloud.cn/yami/mall4j

**开发环境搭建视频（推荐先看下文档再看视频）：https://www.bilibili.com/video/BV1eW4y1V7c1** 

有声音了。如果视频对你有用，记得点赞投币噢。

## 商城授权

除了开源版本，我们商业版有B2C商城、B2B2C商城、O2O商城、S2B2C商城、SAAS商城，多端呈现：小程序 + PC + H5 + APP，更多详情请查看官网 

Mall4j商城官网 https://www.mall4j.com

Mall4j商城开源版 使用 AGPLv3 开源，请遵守 AGPLv3 的相关条款，或者联系作者获取商业授权(https://www.mall4j.com)

## 项目链接

java后台：https://gitee.com/gz-yami/mall4j

vue后台前端：https://gitee.com/gz-yami/mall4v

小程序：https://gitee.com/gz-yami/mall4m

uni-app：https://gitee.com/gz-yami/mall4uni


## 商城演示地址

 商业版商城小程序演示

![输入图片说明](screenshot/%E5%AE%87%E5%AE%99%E7%89%88%E5%B0%8F%E7%A8%8B%E5%BA%8F.png)

## 商城技术选型

| 技术                  | 版本      | 说明                           |
|---------------------|---------|------------------------------|
| Spring Boot         | 3.0.4   | MVC核心框架                      |
| Spring Security web | 3.0.4   | web应用安全防护                    |
| satoken             | 1.34.0  | 一个轻量级 Java 权限认证框架，取代spring oauth2 |
| MyBatis             | 3.5.10  | ORM框架                        |
| MyBatisPlus         | 3.5.3.1 | 基于mybatis，使用lambda表达式的       |
| spring-doc          | 2.0.0   | 接口文档工具                       |
| jakarta-validation  | 3.0.2   | 验证框架                         |
| redisson            | 3.19.3  | 对redis进行封装、集成分布式锁等           |
| hikari              | 5.0.1   | 数据库连接池                       |
| logback             | 1.4.5   | log日志工具                      |
| lombok              | 1.18.26 | 简化对象封装工具                     |
| hutool              | 5.8.15  | 更适合国人的java工具集                |
| knife4j             | 4.0.0   | 基于swagger，更便于国人使用的swagger ui |


通过阿里的代码规范扫描工具（Alibaba Java Coding Guidelines plugin），扫描无异常：

![规约扫描结果](screenshot/规约.png)

## 部署教程

ps: 如果你不清楚如何启动我们的商城，请仔细阅wiki当中的文档


https://gitee.com/gz-yami/mall4j/wikis

**开发环境搭建视频（推荐先看下文档再看视频）：https://www.bilibili.com/video/BV1eW4y1V7c1** 

有声音了。如果视频对你有用，记得点赞投币噢。

## 真正的部署教程！！

### 部署后端（java后台）
#### 1. 安装Maven
   首先，在Linux服务器上安装Maven。如果你已经安装了Maven，可以跳过此步骤
```
sudo apt update
sudo apt install maven
```
如果服务器从repo.maven拉取代码太慢，可以考虑设置源，在~/.m2下新建文件settings.xml，内容如下。
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
<mirrors>
  <mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>
  </mirror>
  <mirror>
    <id>maven.net.cn</id>
    <name>Mirror from Maven in china</name>
    <url>http://maven.net.cn/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>
  </mirror>
</mirrors>
</settings>
```
#### 2. 拉取仓库代码
   使用Git从你的代码仓库中拉取最新的代码
```
git clone https://gitee.com/eddsi/mall4j.git
cd mall4j
```
#### 3. 安装Docker和Docker Compose
   如果你没有安装Docker和Docker Compose，请按照以下步骤进行安装
```
sudo apt update
sudo apt upgrade
sudo apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt update
sudo apt install docker-ce
sudo apt install docker-compose
docker-compose --version
docker --version
```
如果docker拉取容器镜像太慢就配置代理
```
sudo mkdir -p /etc/systemd/system/docker.service.d
sudo vim /etc/systemd/system/docker.service.d/http-proxy.conf
```
添加以下内容
```
[Service]
Environment="HTTP_PROXY=http://your-proxy-server:proxy-port/"
Environment="HTTPS_PROXY=http://your-proxy-server:proxy-port/"
Environment="NO_PROXY=localhost,127.0.0.1"
```
然后重启
```
sudo systemctl daemon-reload
sudo systemctl restart docker
```
#### 4. 安装OpenJDK 17
   在使用Maven打包之前，需要安装OpenJDK 17
```angular2html
sudo apt update
sudo apt install openjdk-17-jdk
```

#### 5. 设置环境变量

将.env.example复制一份，重命名为.env，设置好每一个环境变量，如果缺少相应的环境变量，代码将无法运行。

环境变量MINIO_ACCESS_KEY和MINIO_SECRET_KEY的设置方法，请参考S3部署部分。

#### 6. 利用Maven打包并启动Docker Compose
使用Maven将项目打包成一个可执行的JAR文件，使用Docker Compose启动所有相关的服务
```
. .env
mvn clean install
docker-compose up -d
```

#### 7. 重新启动容器时注意事项
   由于docker启动容器使用了jar包，而maven打jar包过程中，配置文件中使用，所以每一次环境变量变动后重新启动容器，都要重新构建
```angular2html
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi mall4j-api
docker rmi mall4j-admin
. .env
mvn clean install
docker-compose up -d
```

#### ❓ 常见问题 Q&A
使用Docker Compose部署时，就算配置了代理，也可能遇到无法拉取镜像的问题，
此时，有3种解决方案

1、增加站点，但这种方式受站点稳定性影响，不推荐

2、如果拉取mysql失败，可以不适用docker内置数据库，而使用外部数据库，在.env中配置好环境变量就可以了（记住去数据库控制台添加白名单）

3、直接加载已有镜像，具体步骤如下

首先要手动找到镜像（自行解决），然后将文件放到某个路径下，比如/home/user。
最后在目标主机上使用docker load命令加载这些镜像

```shell
docker load -i /path/to/destination/openjdk.tar
docker load -i /path/to/destination/mysql.tar
docker load -i /path/to/destination/redis.tar
```
### 部署S3（文件上传服务）
.env中设置好MINIO_ROOT_USER、MINIO_ROOT_PASSWORD、MINIO_SERVER_URL
这三个环境变量，然后执行
```
docker-compose -f docker-compose-minio.yml up -d
```
注意!MINIO_ROOT_USER=MINIO_ACCESS_KEY

MINIO_ROOT_PASSWORD=MINIO_SECRET_KEY

### 部署后台管理界面的前端
请查看./front-end/mall4v/README.md

### 部署小程序
请查看./front-end/mall4m/README.md

### 部署uni-app
请查看./front-end/mall4uni/README.md

## 相关截图

### 1. 后台截图
![商城后台](https://gitee.com/gz-yami/mall4j/raw/master/screenshot/mall4jV.gif)



### 2. 移动端截图

![输入图片说明](https://images.gitee.com/uploads/images/2021/1110/145209_2ec1ad04_5094767.png "商城.png")



## 提交反馈
- Mall4j商城官网 https://www.mall4j.com


- Mall4j商城官方技术QQ 1群：722835385（3000人群已满）
- Mall4j商城官方技术QQ 2群：729888395
- 如需购买商城商业版源码，请联系商务微信

  ![输入图片说明](https://19838323.s21i.faiusr.com/4/4/ABUIABAEGAAgksmNlAYojomK2gIwrAI4rAI!160x160.png)

## 特别鸣谢

- wxjava: https://github.com/Wechat-Group/WxJava
- sa-token: https://gitee.com/dromara/sa-token



## mall4cloud微服务商城版本已上线
https://gitee.com/gz-yami/mall4cloud

## 更多信息请查看Mall4j商城官网 <https://www.mall4j.com>
