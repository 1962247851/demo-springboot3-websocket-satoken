### Demo SpringBoot3 WebSocket SaToken

### 问题

如何在收到WebSocket的消息后，使用StpUtil.getLoginId()读取当前登录用户？

```
cn.dev33.satoken.exception.NotWebContextException: 非 web 上下文无法获取 HttpServletRequest
	at cn.dev33.satoken.spring.SpringMVCUtil.getRequest(SpringMVCUtil.java:44)
	at cn.dev33.satoken.spring.SaTokenContextForSpringInJakartaServlet.getStorage(SaTokenContextForSpringInJakartaServlet.java:56)
	at cn.dev33.satoken.context.SaHolder.getStorage(SaHolder.java:69)
	at cn.dev33.satoken.stp.StpLogic.isSwitch(StpLogic.java:2536)
	at cn.dev33.satoken.stp.StpLogic.getLoginId(StpLogic.java:948)
	at cn.dev33.satoken.stp.StpLogic.getLoginIdAsString(StpLogic.java:1057)
	at cn.dev33.satoken.stp.StpUtil.getLoginIdAsString(StpUtil.java:379)
	at tech.ordinaryroad.demo.WebSocketController.demo(WebSocketController.kt:52)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119)
	at org.springframework.messaging.handler.invocation.AbstractMethodMessageHandler.handleMatch(AbstractMethodMessageHandler.java:567)
	at org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler.handleMatch(SimpAnnotationMethodMessageHandler.java:529)
	at org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler.handleMatch(SimpAnnotationMethodMessageHandler.java:93)
	at org.springframework.messaging.handler.invocation.AbstractMethodMessageHandler.handleMessageInternal(AbstractMethodMessageHandler.java:522)
	at org.springframework.messaging.handler.invocation.AbstractMethodMessageHandler.handleMessage(AbstractMethodMessageHandler.java:457)
	at org.springframework.messaging.support.ExecutorSubscribableChannel$SendTask.run(ExecutorSubscribableChannel.java:152)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at java.base/java.lang.Thread.run(Thread.java:833)
```

### 复现步骤

1. 访问登录接口，获取token，http://localhost:8080/login/1
2. 访问 http://localhost:8080，填入token并连接
3. 随便发送一条命令

### 项目接口

- Knife4j接口文档：http://localhost:8080/doc.html
- WebSocket地址：ws://localhost:8080/websocket
- 登录：http://localhost:8080/login/{userId}
- 退出登录：http://localhost:8080/logout
- 查询当前登录用户：http://localhost:8080/userinfo
