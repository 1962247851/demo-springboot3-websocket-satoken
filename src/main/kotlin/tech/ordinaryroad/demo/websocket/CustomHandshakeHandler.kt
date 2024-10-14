package tech.ordinaryroad.demo.websocket

import org.springframework.http.server.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import tech.ordinaryroad.demo.websocket.WebSocketInterceptor.Companion.KEY_USER_ID
import java.security.Principal

/**
 * WebSocket自定义握手管理器
 *
 * @author mjz
 * @date 2024/10/12
 */
@Component
class CustomHandshakeHandler : DefaultHandshakeHandler() {
    /**
     * 重写定义用户信息方法
     *
     * @param request    握手请求对象
     * @param wsHandler  WebSocket管理器，用于管理信息
     * @param attributes 用于传递WebSocket会话的握手属性
     * @return StompPrincipal 自定义用户信息
     */
    override fun determineUser(
        request: ServerHttpRequest,
        wsHandler: WebSocketHandler,
        attributes: Map<String, Any>
    ): Principal {
        // 获取客户端主机名称
        val hostName = request.remoteAddress.hostName
        // 获取客户端主机IP地址
        val hostAddress = request.remoteAddress.address.hostAddress
        // 握手通过后标记userId
        val userId = attributes[KEY_USER_ID] as String
        return StompPrincipal(hostName, hostAddress, userId)
    }
}
