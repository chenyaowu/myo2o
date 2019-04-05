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

  