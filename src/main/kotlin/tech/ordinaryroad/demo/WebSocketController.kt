package tech.ordinaryroad.demo

import cn.dev33.satoken.stp.StpUtil
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.ExceptionUtil
import org.apache.commons.lang3.exception.ExceptionUtils
import org.apache.coyote.BadRequestException
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import tech.ordinaryroad.demo.websocket.StompPrincipal
import java.time.LocalDateTime

/**
 *
 *
 * @author mjz
 * @date 2024/10/14
 */
@Controller
class WebSocketController(
    val objectMapper: ObjectMapper,
    val simpMessagingTemplate: SimpMessagingTemplate,
    val demoService: DemoService
) {
    @MessageMapping("/demo")
    @SendTo("/topic/demo")
    fun demo(
        request: String?,
        @Headers map: Map<String, String>,
        stompPrincipal: StompPrincipal,
    ): JsonNode {
        println("headers: $map")
        println("stompPrincipal: ${objectMapper.writeValueAsString(stompPrincipal)}")


        Thread {
            Thread.sleep(1000L)
            simpMessagingTemplate.convertAndSendToUser(
                stompPrincipal.userId,
                "demo",
                "这是异步发送的消息 ${LocalDateTime.now()}"
            )
        }.start()

        var exception: Exception? = null
        try {
            demoService.doSomething()
        } catch (e: Exception) {
            exception = e
            e.printStackTrace()
        }
        return objectMapper.createObjectNode().apply {
            putPOJO("request", request)
            putPOJO("exception", ExceptionUtils.getRootCauseMessage(exception))
        }
    }
}