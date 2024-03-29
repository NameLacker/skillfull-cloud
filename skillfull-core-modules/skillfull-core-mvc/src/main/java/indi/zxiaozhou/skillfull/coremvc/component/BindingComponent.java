// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coremvc.component;

import indi.zxiaozhou.skillfull.corecommon.constant.BindingStreamConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * stream工具类
 *
 * @author zxiaozhou
 * @date 2021-05-29 17:11
 * @since JDK1.8
 */
@RequiredArgsConstructor
@Component
@Slf4j
@Async
public class BindingComponent {
    private static final String TTL_KEY = "x-message-ttl";
    private final StreamBridge streamBridge;

    /**
     * 发送消息并设置未消费过期时间
     *
     * @param bindingName ${@link String} bind名称
     * @param t           ${@link Object} 数据
     * @param ttl         ${@link Long} 过期时间,单位:s
     * @author zxiaozhou
     * @date 2021-07-29 17:52
     */
    public <T> void out(String bindingName, T t, long ttl) {
        MessageBuilder<T> messageBuilder = MessageBuilder.withPayload(t);
        if (ttl > 0) {
            messageBuilder.setHeader(TTL_KEY, ttl * 1000);
        }
        Message<T> stringMessage = messageBuilder.build();
        log.debug("------------StreamUtil------发送消息------>out:{}", stringMessage);
        log.debug("------------BindingComponent------------>out:{}", bindingName + BindingStreamConstant.OUT_SUFFIX);
        streamBridge.send(bindingName + BindingStreamConstant.OUT_SUFFIX, stringMessage);
    }


    /**
     * 发送消息
     *
     * @param bindingName ${@link String} bind名称
     * @param t           ${@link Object} 数据
     * @author zxiaozhou
     * @date 2021-08-30 15:32
     */
    public <T> void out(String bindingName, T t) {
        out(bindingName, t, 0);
    }


}
