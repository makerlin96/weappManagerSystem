# GzStarry企业安全开发框架

#### 介绍
一个基于SpringBoot、shiro、mybatis-plus、redis的轻量级、前后端分离、防范xss攻击、拥有分布式锁的企业快速开发框架

- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 前端页面交互使用vue-element-admin，极大的提高了开发效率
- 完善的代码生成机制，可在线生成entity、xml、dao、service、vue、sql代码，减少70%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- shiro + Jwt实现无状态鉴权机制(Token)
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云、fastDFS等
- 引入swagger文档支持，方便编写API接口文档

#### 软件架构
软件架构说明

#### 技术选型

| 技术                   | 版本   | 说明                                    |
| ---------------------- | ------ | --------------------------------------- |
| Spring Boot            | 2.2.1  | MVC核心框架                             |
| Shiro                  | 1.3.2  | 认证和授权框架                          |
| MyBatis                | 3.5.0  | ORM框架                                 |
| MyBatisPlus            | 3.2.0  | 基于mybatis，使用lambda表达式的         |
| Swagger-UI             | 2.9.2  | 文档生产工具                            |
| Hibernator-Validator   | 6.0.17 | 验证框架                                |
| redis                  | 3.10.6 | 对redis进行封装、集成分布式锁等         |
| druid                  | 1.1.20 | 数据库连接池                            |
| log4j2                 | 2.11.2 | 更快的log日志工具                       |
| fst                    | 2.57   | 更快的序列化和反序列化工具              |
| orika                  | 1.5.4  | 更快的bean复制工具                      |
| lombok                 | 1.18.8 | 简化对象封装工具                        |
| hutool                 | 4.6.6  | 更适合国人的java工具集


#### 程序逻辑

1.填写用户名密码用POST请求访问/login接口，返回token令牌等信息，失败则直接返回身份错误信息。

2.在之后需要验证身份的请求的Headers中添加token令牌。

3.服务端进行token认证，失败身份错误信息。

4.用JWT做认证（登录），Shiro做授权。


#### 安装教程

##### 1.开发环境

以下版本是最低要求的！！！


| 工具  | 版本 |
| ----- | ---- |
| jdk   | 1.8+ |
| mysql | 5.7+ |
| redis | 3.2+ |

##### 2.启动

- 推荐使用idea，安装lombok插件后，使用idea导入maven项目
- 将lunhui.sql导入到mysql中，修改`application-dev.yml`更改 `datasource.url、user、password`
- Redis需要自行安装Redis服务，端口密码默认
- 通过`WebApplication`启动项目后台接口，`ApiApplication` 启动项目前端接口

