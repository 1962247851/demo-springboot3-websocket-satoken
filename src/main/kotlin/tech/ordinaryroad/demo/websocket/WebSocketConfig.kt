package tech.ordinaryroad.demo.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(
    private val webSocketInterceptor: WebSocketInterceptor,
    private val customHandshakeHandler: CustomHandshakeHandler,
) : WebSocketMessageBrokerConfigurer {

    /**
     * 代理配置
     *
     * @param config 配置对象
     */
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // 客户端subscribe的前缀
        config.enableSimpleBroker("/topic", "/user")
        // 客户端publish的前缀
        config.setApplicationDestinationPrefixes("/app")
    }

    /**
     * 注册STOMP协议节点并映射指定url
     *
     * @param registry 注册对象
     */
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // WebSocket路径 ws://localhost:8080/websocket
        registry.addEndpoint("/websocket")
            .addInterceptors(webSocketInterceptor)
            .setHandshakeHandler(customHandshakeHandler)
            .setAllowedOriginPatterns("*")
    }
}