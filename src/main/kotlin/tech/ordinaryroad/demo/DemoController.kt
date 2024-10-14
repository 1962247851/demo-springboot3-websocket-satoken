package tech.ordinaryroad.demo

import cn.dev33.satoken.stp.StpUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 *
 *
 * @author mjz
 * @date 2024/10/14
 */
@RestController
class DemoController {
    @GetMapping("login/{userId}")
    fun login(@PathVariable userId: String): String {
        if (!map.containsKey(userId)) {
            throw RuntimeException("用户 $userId 不存在，可用用户${map.keys}")
        }
        StpUtil.login(userId)
        return StpUtil.getTokenValue()
    }

    @GetMapping("logout")
    fun logout() {
        StpUtil.logout()
    }

    @GetMapping("userinfo")
    fun userinfo(): DemoUser? {
        val userId = StpUtil.getLoginIdAsString()
        return map[userId]
    }

    companion object {
        /**
         * userId, DemoUser
         */
        val map = HashMap<String, DemoUser>().apply {
            put("1", DemoUser("1", "张三"))
            put("2", DemoUser("2", "李四"))
            put("3", DemoUser("3", "小明"))
        }
    }
}