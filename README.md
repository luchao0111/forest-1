# Forest - HTTP客户端访问框架


[![Build Status](https://api.travis-ci.org/mySingleLive/forest.svg?branch=master)](https://travis-ci.org/mySingleLive/forest)
[![codecov](https://codecov.io/gh/mySingleLive/forest/branch/master/graph/badge.svg)](https://codecov.io/gh/mySingleLive/forest)
[![license](https://img.shields.io/badge/license-MIT%20License-blue.svg)](https://opensource.org/licenses/mit-license.php)

项目介绍：
-------------------------------------

Forest是一个通过动态代理模式实现的HTTP客户端框架。<br>
相比直接使用Httpclient您不再用写一大堆重复的代码了，只需像调用本地方法一样调用HTTP链接。

项目特点：
-----
* 通过调用本地方法的方式去发送Http请求
* 基于本地接口方法的定义
* 基于注解、配置化的方式定义Http请求
* 支持Spring集成：除了基本信息配置，它能自动扫描、注入到你自己的代码中
* JSON字符串到Java对象的自动化解析
* JSON转换器可以随意扩展和替换
* 可以通过OnSuccess和OnError接口参数实现请求结果的回调
* 配置简单，一般只需要@Request一个注解就能完成绝大多数请求的定义
* 实现了业务逻辑与Http协议之间的解耦


项目状态：
-------------------------------------

* 主流程已完成
* 已支持所有请求方法：GET, HEAD, OPTIONS, TRACE, POST, DELETE, PUT, PATCH
* 已支持的JSON转换框架: Fastjson, Jackson, Gson
* 已支持Spring集成
* 已支持JAXB形式的XML转换
* 已支持异步请求
* 现只支持文本和JSON两种响应解析方式
* 支持Commons-Log, SLF4j, Log4J等日志框架


如何获取：
-------------------------------------

### Maven配置

设置maven仓库地址

```xml
    <repository>
        <id>mySingleLive-maven-repo</id>
        <url>https://raw.githubusercontent.com/mySingleLive/maven-repo/master/repository</url>
    </repository>
```

依赖配置

```xml

    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient-cache</artifactId>
        <version>4.1.2</version>
    </dependency>

    <dependency>
        <groupId>org.forest</groupId>
        <artifactId>forest-core</artifactId>
        <version>0.0.7</version>
    </dependency>

```


使用方法：
-------------------------------------
### 创建一个Interface作为远程调用接口

```java

import org.forest.annotation.Request;
import org.forest.annotation.DataParam;

public interface Www {

    /**
     * 百度短链接API
     * @param url
     * @return
     */
    @Request(
        url = "http://dwz.cn/create.php",
        type = "post",
        dataType = "json"
    )
    Map getShortUrl(@DataParam("url") String url);


}


```


### 调用远程接口
```java

ForestConfiguration configuration = ForestConfiguration.configuration();
ProxyFactory<Www> factory = configuration.getProxyFactory(Www.class);
Www www = factory.createInstance();
Map result = www.getShortUrl("https://github.com/mySingleLive/forest");
System.out.println(result);

```


详细文档:<br>
* [变量/参数](https://github.com/mySingleLive/forest/blob/master/forest-core/src/main/doc/DOCUMENTATION.md)<br>
* [回调方法](https://github.com/mySingleLive/forest/blob/master/forest-core/src/main/doc/CALLBACK.md)<br>
* [在Spring中使用](https://github.com/mySingleLive/forest/blob/master/forest-core/src/main/doc/SPRING.md)


项目协议
--------------------------
The MIT License (MIT)

Copyright (c) 2016 Jun Gong