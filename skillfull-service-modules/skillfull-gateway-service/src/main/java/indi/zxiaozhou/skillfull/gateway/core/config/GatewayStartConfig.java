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


import indi.zxiaozhou.skillfull.corecommon.base.service.ICoreCommonService;
import indi.zxiaozhou.skillfull.corewebflux.config.properfy.SpringDocCoreWebFluxProperty;
import indi.zxiaozhou.skillfull.gateway.modules.manage.service.IDynamicRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动处理
 *
 * @author zxiaozhou
 * @date 2019-04-16 10:38
 * @since JDK11
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class GatewayStartConfig implements ApplicationRunner {
    private final IDynamicRouteService routeService;
    private final ICoreCommonService coreCommonService;
    private final SpringDocCoreWebFluxProperty webFluxProperty;


    @Override
    public void run(ApplicationArguments args) {
        // 加载路由信息
        routeService.loadRoute(null);
        // 加载常量字典
        coreCommonService.loadConstantDict(false);
    }
}
