# Tomcat知识点

## 解析地址配置

- server.xml 在Host标签下添加Context标签

  ```xml
  <Context docBase="realPath" path="/needParsePath"  reloadable="true"/>
  ```

  

## 中文传参乱码

- server.xml  追加 URIEncoding="UTF-8" 

  ```xml
  <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8" />
  ```

- 在web.xml配置

  ```xml
   <context-param>
      <param-name>charset</param-name>
      <param-value>UTF-8</param-value>
    </context-param>
  ```



## Tomcat 原理

![Tomcat原理图](https://github.com/chenyaowu/myo2o/blob/master/myo2o/img/mysql/tomcat1.jpg)

- server 整个tomcat服务器，其中包含多个组件，负责管理和启动各个service，监听8005端口发过来shutdown命令用户关闭整个容器

- service是tomcat封装的，主要对外提供主键外部服务，其中包块Connector和Container核心组件，以及多个功能主键，各个service是独立，但它们会共享虚拟机资源。

- Connector是tomcat与外部的连接器，监听固件端口，接收外部请求，将请求传递给Container，并将Container处理结果返回给外部。

- Container是Servlet容器。内部由多层容器组成。主要用于管理Servlet生命周期，调用Servlet相关方法处理业务逻辑

  ![Container原理图](https://github.com/chenyaowu/myo2o/blob/master/myo2o/img/mysql/container.jpg)

  - Engine用于管理多个站点
  - Host代表一个站点（虚拟主机）
  - Context代表一个应用程序
  - Wrapper封装的Servlet

- Jasper是tomcat的jsp解析引擎，用于将jsp文件转换成java文件，并编译成.class文件

- Naming是命名服务，将对象和名称联系起来。使我们可以用名称去访问对象

- Session主要负责创建和管理session，它主要用于session的持久化，支持session集群，在服务器开发的内存空间

- logging主键，主要用于日志

- JMX，javaSE定义的技术规范，主要是一个为应用程序，设备，系统等职务管理功能的框架。通过JMX可以远程地监控tomcat的运行状况。

## tomcat目录结构

1. 一级目录
    bin ——Tomcat执行脚本目录
    conf ——Tomcat配置文件
    lib ——Tomcat运行需要的库文件（JARS）
    logs ——Tomcat执行时的LOG文件
    temp ——Tomcat临时文件存放目录
    webapps ——Tomcat的主要Web发布目录（存放我们自己的JSP,SERVLET,类）
    work ——Tomcat的工作目录，Tomcat将翻译JSP文件到的Java文件和class文件放在这里。

2. 二级目录（仅列出一级目录下几个重要的文件）
    (1) bin目录下的文件
    catalina.sh 用于启动和关闭tomcat服务器
    configtest.sh 用于检查配置文件
    startup.sh 启动Tomcat脚本
    shutdown.sh 关闭Tomcat脚本
    (2) conf目录下的文件
    server.xml Tomcat 的全局配置文件
    web.xml 为不同的Tomcat配置的web应用设置缺省值的文件
    tomcat-users.xml Tomcat用户认证的配置文件
    (3) lib目录下的文件
    包含被Tomcat使用的各种各样的jar文件。
    (4) logs目录下的文件
    localhost_access_log.2013-09-18.txt 访问日志
    localhost.2013-09-18.log 错误和其它日志
    manager.2013-09-18.log 管理日志
    catalina.2013-09-18.log Tomcat启动或关闭日志文件
    (5) webapps目录下的文件
    含Web应用的程序（JSP、Servlet和JavaBean等）
    (6) work目录下的文件
    由Tomcat自动生成，这是Tomcat放置它运行期间的中间(intermediate)文件(诸如编译的JSP文件)地方。如果当Tomcat运行时，你删除了这个目录那么将不能够执行包含JSP的页面。