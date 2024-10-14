package tech.ordinaryroad.demo.websocket

import cn.dev33.satoken.stp.StpUtil
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor

private val s = "userId"

/**
 * WebSocket 握手的前置拦截器
 *
 * @author mjz
 * @date 2024/10/12
 */
@Component
class WebSocketInterceptor : HandshakeInterceptor {

    // 握手之前触发 (return true 才会握手成功 )
    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        handler: WebSocketHandler,
        attr: MutableMap<String, Any>
    ): Boolean {
        println("---- 握手之前触发 " + StpUtil.getTokenValue())

        // 未登录情况下拒绝握手
        if (!StpUtil.isLogin()) {
            println("---- 未授权客户端，连接失败")
            return false
        } else {
            println("---- 欢迎${StpUtil.getLoginIdAsString()}")
        }

        // 标记 userId，握手成功 
        attr[KEY_USER_ID] = StpUtil.getLoginIdAsString()
        return true
    }

    // 握手之后触发 
    override fun afterHandshake(
        request: ServerHttpRequest, response: ServerHttpResponse, wsHandler: WebSocketHandler,
        exception: Exception?
    ) {
        println("---- 握手之后触发 ")
    }

    companion object {
        const val KEY_USER_ID = "userId"
    }
}