// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.base.model.stream.router;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 动态路由信息
 *
 * @author zxiaozhou
 * @date 2020-09-12 16:04:47
 * @since JDK11
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class SystemRouterModel implements Serializable {
    private static final long serialVersionUID = 550568918804547444L;

    @Schema(name = "routeId", title = "路由id")
    private String routeId;

    @Schema(name = "serviceId", title = "服务id")
    private String serviceId;

    @Schema(name = "serviceCode", title = "服务编码(注册中心服务名)")
    private String serviceCode;

    @Schema(name = "routeCode", title = "路由编码(唯一)")
    private String routeCode;

    @Schema(name = "routeName", title = "路由名称")
    private String routeName;

    @Schema(name = "url", title = "路由url地址,当选择非负载均衡器时必填")
    private String url;

    @Schema(name = "metadata", title = "路由元数据")
    private Map<String, Object> metadata;

    @Schema(name = "isLoadBalancer", title = "是否负载均衡器:0-不是,1-是，默认0。选择均衡器时服务名必填，url不填")
    private Integer isLoadBalancer;

    @Schema(name = "loadBalancerType", title = "负载均衡器类型:0-普通,1-ws,2-wss,当选的负载均衡器时必填")
    private String loadBalancerType;

    @Schema(name = "routeOrder", title = "路由排序,越小越靠前，默认0")
    private Integer routeOrder;

    @Schema(name = "routePredicates", title = "路由断言")
    private List<RoutePredicateModel> routePredicates;

    @Schema(name = "routeFilters", title = "路由过滤器")
    private List<RouteFilterModel> routeFilters;
}