# [spring-cloud-staging](https://github.com/bingjinb/spring-cloud-staging)

spring-cloud-staging是基于Spring Cloud开发的管理系统后台，未来会提供整套公共微服务模块，包含注册中心、基础服务、配置中心、网关、对外服务、监控，支持服务治理、监控和调用链追踪等功能。

坚持不易，如果觉得项目还不错的话可以给项目一个 <font color="red"><b>Star</b></font> 吧，也是对一直更新代码的一种鼓励，谢谢各位的支持。



## 请注意
当前后台功能只实现了很少一部分功能，未来会迭代逐渐完善。鄙人才疏学浅，不完善的地方，烦请指出。



## 后端技术

技术 | 名称 | 版本 | 官网
----|------|----|----
Spring Boot | 应用框架 | 2.2.5.RELEASE | [https://projects.spring.io/spring-boot/](https://projects.spring.io/spring-boot/)
MyBatis Plus | ORM框架 | 3.3.1 |  [https://mybatis.plus](https://mybatis.plus)
Swagger2 | 在线Api文档 | 2.8.0 |  [https://swagger.io/](https://swagger.io/)
Shiro | 权限管控 | 1.5.1 | [http://shiro.apache.org](http://shiro.apache.org)
Logback | 日志组件 | 1.2.3 |  [https://logback.qos.ch](https://logback.qos.ch/)
HikariCP | 数据库连接池 | 3.4.2 |  [https://www.baeldung.com/hikaricp](https://www.baeldung.com/hikaricp)


## 软件版本
- JDK 1.8.0_231
- MySQL 5.7.10
- Maven 3.2.3

## 当前进度
- [x] 日志格式统一处理
- [x] 日志出入参拦截打印
- [x] 日志指定方法通过注解入库
- [x] 日志内部调用链统一跟踪
- [x] 返回结构统一规范处理
- [x] 异常统一处理
- [x] p6spy实现SQL统一打印
- [x] shiro和JWT实现权限管控
- [x] swagger接口调测
- [x] XSS攻击防御设置
- [x] IP限流规避DoS攻击防御


## 联系作者

> 有问题或者建议可以在 <b>[issues](https://github.com/bingjinb/spring-cloud-staging/issues)</b> 中反馈给我，我会慢慢完善这个项目。

- 邮箱：bingjinb@gmail.com
- QQ群：785715909