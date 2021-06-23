# 目录说明
- jpa 记录了spring jpa 学习的内容
- service1 和 service2 是两个服务，服务发现使用的是 nacos，集成 feign，ziplin
- zuul-nacos 是服务网关
## spring-jpa
<b>base</b> 包含了 jpa 的基本用法，包括 <b>crud</b>，<b>分页排序</b>，返回值 <b>List/Page/Slice</b>。

<b>advance</b> 包含了自定义返回值的三种方式：<b>projections, dto, 接口</b>，以及<b>@Query</b>的一般用法。

<b>entity</b> 包含了 <b>Entity</b> 常用注解，一对一、一对多、多对多等<b>对应关系</b>，以及级联增加，级联删除等<b>级联操作</b>。

<b>jse</b> 包含了 <b>QueryByExampleExecutor</b> 以及 <b>JpaSpecificationExecutor</b>。
