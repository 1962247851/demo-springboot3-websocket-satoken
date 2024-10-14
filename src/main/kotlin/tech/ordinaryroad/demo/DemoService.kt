package tech.ordinaryroad.demo

import cn.dev33.satoken.stp.StpUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 *
 *
 * @author mjz
 * @date 2024/10/14
 */
@Service
class DemoService {

    fun doSomething() {
        log.info("${StpUtil.getLoginIdAsString()} did something...")
    }

    companion object {
        val log = LoggerFactory.getLogger(DemoService::class.java)
    }

}