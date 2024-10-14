package tech.ordinaryroad.demo.websocket

import java.security.Principal

/**
 * 客户端信息实体类
 *
 * @author mjz
 * @date 2024/10/12
 */
class StompPrincipal(
    /**
     * 客户端主机名称，对应 HostName
     */
    var hostName: String,
    /**
     * 客户端主机IP地址，对应 HostAddress
     */
    var hostAddress: String,
    /**
     * 登录的用户ID
     */
    var userId: String,
) : Principal {
    override fun getName(): String {
        return this.hostName
    }
}
