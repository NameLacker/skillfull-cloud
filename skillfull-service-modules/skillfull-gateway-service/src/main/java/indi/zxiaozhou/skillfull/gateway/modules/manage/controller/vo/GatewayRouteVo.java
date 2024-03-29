// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.gateway.modules.manage.controller.vo;


import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 路由入参
 *
 * @author zxiaozhou
 * @date 2020-09-10 22:43
 * @since JDK11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema
public class GatewayRouteVo implements Serializable {
    private static final long serialVersionUID = -5358228979136756020L;

    @Schema(name = "routeCode", title = "路由编码(唯一)", required = true)
    @NotBlankOrNull(message = "路由编码(唯一)不能为空")
    private String routeCode;

    @Schema(name = "url", title = "路由url地址,当选择非负载均衡器时必填")
    private String url;

    @Schema(name = "isLoadBalancer", title = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时服务名必填，url不填", required = true)
    @NotBlankOrNull(message = "请确定是否负载均衡器")
    @Builder.Default
    private Integer isLoadBalancer = 0;

    @Schema(name = "loadBalancerType", title = "负载均衡器类型:0-普通,1-ws,2-wss,当选的负载均衡器时必填")
    private String loadBalancerType;

    @Schema(name = "serviceName", title = "服务名,当选择负载均衡器时使用必填")
    private String serviceName;

    @Schema(name = "routeOrder", title = "路由排序,越小越靠前，默认0")
    @Builder.Default
    private Integer routeOrder = 0;

    @Schema(name = "metadata", title = "路由元数据")
    private Map<String, Object> metadata;

    @Schema(name = "routePredicates", title = "路由断言", required = true)
    @NotNullSize(message = "路由断言不能为空")
    @Valid
    private List<RoutePredicate> routePredicates;

    @Schema(name = "routeFilters", title = "路由过滤器")
    @Valid
    private List<RouteFilter> routeFilters;

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Schema
    public static class RoutePredicate implements Serializable {
        private static final long serialVersionUID = -67116125178064715L;

        @Schema(name = "predicateType", title = "断言类型", required = true)
        @NotBlankOrNull(message = "断言类型不能为空")
        private String predicateType;

        @Schema(name = "rules", title = "断言规则", required = true)
        @NotNullSize(message = "断言规则不能为空")
        @Valid
        private Set<Rule> ruleSet;
    }


    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Schema
    public static class RouteFilter implements Serializable {
        private static final long serialVersionUID = 5625992009673170739L;

        @Schema(name = "filterType", title = "过滤器类型", required = true)
        @NotBlankOrNull(message = "过滤器类型不能为空")
        private String filterType;

        @Schema(name = "rules", title = "过滤器规则", required = true)
        @NotNullSize(message = "过滤器规则不能为空")
        @Valid
        private Set<Rule> ruleSet;
    }


    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Schema
    public static class Rule {
        @Schema(name = "ruleName", title = "规则名称", required = true)
        @NotBlankOrNull(message = "规则名称不能为空")
        private String ruleName;

        @Schema(name = "ruleValue", title = "规则值", required = true)
        @NotBlankOrNull(message = "规则值不能为空")
        private String ruleValue;
    }
}
