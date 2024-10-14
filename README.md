### SpringBoot3 WebSocket SaToken

如何在收到WebSocket后，读取当前登录用户的信息

登录：http://localhost:8080/login/{userId}
退出登录：http://localhost:8080/logout
查询当前登录用户：http://localhost:8080/userinfo
接口文档：http://localhost:8080/doc.html
WebSocket地址：ws://localhost:8080/websocket

1. 访问登录接口，获取token，http://localhost:8080/login/1
2. 访问 http://localhost:8080，填入token
3. 随便发送一条命令
