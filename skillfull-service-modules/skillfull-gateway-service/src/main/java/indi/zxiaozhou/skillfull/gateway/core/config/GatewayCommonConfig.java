// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.core.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import indi.zxiaozhou.skillfull.corecommon.base.model.stream.ConfigTokenModel;
import indi.zxiaozhou.skillfull.corecommon.base.model.system.ConfigDataSecurityModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 网关通用配置
 *
 * @author zxiaozhou
 * @date 2021-06-17 11:58
 * @since JDK1.8
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class GatewayCommonConfig {
    private final NacosDiscoveryProperties properties;

    /**
     * 自定义doc config 信息获取
     *
     * @param swaggerUiConfig ${@link SwaggerUiConfigProperties}
     * @param disClient       ${@link DiscoveryClient}
     * @param webClient       ${@link WebClient.Builder}
     * @return SwaggerUiConfigParameters ${@link SwaggerUiConfigParameters}
     * @author zxiaozhou
     * @date 2022-01-05 03:32
     */
    @Bean
    SwaggerUiConfigParameters swaggerUiConfigParameters(SwaggerUiConfigProperties swaggerUiConfig,
                                                        DiscoveryClient disClient,
                                                        WebClient.Builder webClient,
                                                        Map<String, AbstractSwaggerUiConfigProperties.SwaggerUrl> apiDocInfoUrlModel) {
        return new CustomSwaggerUiConfigParameters(swaggerUiConfig, disClient, webClient, apiDocInfoUrlModel);
    }

    /**
     * 初始化swagger url信息缓存
     *
     * @return Map<String, AbstractSwaggerUiConfigProperties.SwaggerUrl> ${@link Map<String, AbstractSwaggerUiConfigProperties.SwaggerUrl>}
     * @author zxiaozhou
     * @date 2022-01-01 21:31
     */
    @Bean
    public Map<String, AbstractSwaggerUiConfigProperties.SwaggerUrl> apiDocInfoUrlModel() {
        return new ConcurrentHashMap<>(16);
    }

    /**
     * 初始化NamingService
     *
     * @return NamingService ${@link NamingService}
     * @author zxiaozhou
     * @date 2021-01-28 18:16
     */
    @Bean
    public NamingService getNamingService() throws NacosException {
        return NacosFactory.createNamingService(properties.getNacosProperties());
    }

    /**
     * token相关配置
     *
     * @return BaseSystemModel ${@link ConfigTokenModel}
     * @author zxiaozhou
     * @date 2021-06-17 12:01
     */
    @Bean
    public ConfigTokenModel baseSystemModel() {
        return new ConfigTokenModel();
    }

    /**
     * 数据加解密配置
     *
     * @return ConfigDataSecurityModel ${@link ConfigDataSecurityModel}
     * @author zxiaozhou
     * @date 2021-07-14 17:23
     */
    @Bean
    public ConfigDataSecurityModel dataSecurityModel() {
        return new ConfigDataSecurityModel();
    }


    /**
     * url比较配置
     *
     * @return AntPathMatcher ${@link AntPathMatcher}
     * @author zxiaozhou
     * @date 2021-07-14 21:44
     */
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

}
