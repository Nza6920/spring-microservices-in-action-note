# spring-microservices-in-action-note
> #### spring microservices in action 书中的项目案例, 使用 spring-cloud(Hoxton.SR10) 版本进行重构, 删减了 Docker 相关的内容, 并对一些功能实现的细节做了调整.
> #### 项目结构图: /src/main/resources/结构图.png

# 项目启动说明
## 环境
> java8, redis(>= 3.0), mysql(>= 8.0)

## 步骤
> 1. 运行 sql (src/main/resources/sql/eagle.sql) 脚本
> 2. 修改 conf-service/src/main/resources/config 下的相关配置
> 3. 启动 zipkin (src/main/resources/zipkin)
> 4. 启动 eureka-service
> 5. 启动 conf-service
> 6. 启动其他服务
> 7. 导入 postman 接口文件 (src/main/resources/postman)

# 模块说明:
> ## authentication-service(认证服务)
> > - oauth2
> > - jwt


> ## conf-service(配置中心)
> > - spring-cloud-config


> ## eureka-service(注册中心)
 > > - eureka

> ## licensing-service(许可证服务)
 > > - spring boot
 > > - spring cloud sleuth
 > > - spring cloud stream(整合 rabbitmq)
 > > - netflix hystrix
 > > - spring boot data redis

> ## organization-service(机构服务)
 > > - spring boot
 > > - spring cloud sleuth
 > > - spring cloud stream

> ## organization-v2-service(机构服务V2)
 > > - spring boot
 > > - spring cloud sleuth
 > > - spring cloud stream(整合 rabbitmq)

> ## outside-service(外部独立服务)
 > > - spring boot

> ## sidecar-service(外部调用服务 - 负责调用外部独立服务)
 > > - spring boot
 > > - spring boot
 > > - spring cloud netflix sidecar

> ## specialroutes-service(路由检索服务)
 > > - spring boot

> ## zuul-service(网关服务)
 > > - zuul







