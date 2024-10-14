package tech.ordinaryroad.demo.websocket

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.*
import java.util.Objects.requireNonNull

/**
 * WebSocket客户端状态监听
 *
 * @author mjz
 * @date 2024/10/12
 */
@Component
class WebSocketEventListener {

    /**
     * 监听客户端连接
     *
     * @param event 连接事件对象
     */
    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectEvent) {
        val principal: StompPrincipal = event.user as StompPrincipal
        log.info(
            "WebSocket 客户端开始连接: {}",
            ("{ 客户端主机名: " + principal.name +
                    ", 客户端主机IP地址: " + principal.hostAddress +
                    ", 会话ID: " + requireNonNull(event.message.headers["simpSessionId"])) + " }"
        )
    }

    /**
     * 监听客户端连接
     *
     * @param event 连接事件对象
     */
    @EventListener
    fun handleWebSocketConnectedListener(event: SessionConnectedEvent) {
        val principal: StompPrincipal = event.user as StompPrincipal
        val sessionId = event.message.headers["simpSessionId"].toString()
        log.info(
            "WebSocket 客户端已连接: {}",
            ("{ 客户端主机名: " + principal.name +
                    ", 客户端主机IP地址: " + principal.hostAddress +
                    ", 会话ID: " + requireNonNull(sessionId)) + " }"
        )
    }

    /**
     * 监听客户端关闭事件
     *
     * @param event 关闭事件对象
     */
    @EventListener
    fun handleWebSocketCloseListener(event: SessionDisconnectEvent) {
        val principal: StompPrincipal = event.user as StompPrincipal
        val sessionId = event.message.headers["simpSessionId"]!!
        log.info(
            "WebSocket 客户端已关闭: {}",
            ("{ 客户端主机名: " + principal.name +
                    ", 客户端主机IP地址: " + principal.hostAddress +
                    ", 会话ID: " + requireNonNull(sessionId)) + " }"
        )
    }

    /**
     * 监听客户端订阅事件
     *
     * @param event 订阅事件对象
     */
    @EventListener
    fun handleSubscription(event: SessionSubscribeEvent) {
        val principal: StompPrincipal = event.user as StompPrincipal
        log.info(
            "WebSocket 客户端已订阅: {}",
            ("{ 客户端主机名: " + principal.name +
                    ", 客户端主机IP地址: " + principal.hostAddress +
                    ", 订阅节点: " + requireNonNull(event.message.headers["simpDestination"])) + " }"
        )
    }

    /**
     * 监听客户端取消订阅事件
     *
     * @param event 取消订阅事件对象
     */
    @EventListener
    fun handleUnSubscription(event: SessionUnsubscribeEvent) {
        val principal: StompPrincipal = event.user as StompPrincipal
        log.info(
            "WebSocket 客户端已取消订阅: {}",
            ("{ 客户端主机名: " + principal.name +
                    ", 客户端主机IP地址: " + principal.hostAddress +
                    ", 取消订阅节点: " + requireNonNull(event.message.headers["simpDestination"])) + " }"
        )
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)
    }
}
