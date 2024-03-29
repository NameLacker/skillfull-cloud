// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service;

import indi.zxiaozhou.skillfull.coredatabase.base.service.BaseService;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRoutePredicateVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRoutePredicateEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRoutePredicateDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 路由断言(ManageRoutePredicate)业务层接口
 *
 * @author zxiaozhou
 * @copyright zxiaozhou（https://divisu.com）
 * @date 2021-12-19 10:37:42
 * @since JDK1.8
 */
public interface IManageRoutePredicateService extends BaseService<ManageRoutePredicateEntity> {
    /**
     * 保存
     *
     * @param vos       ${@link List< ManageRoutePredicateVo >} 待保存数据
     * @param serviceId ${@link String} 服务id
     * @param override  ${@link Boolean} 是否覆盖:true-覆盖,false-不覆盖
     * @param routerId  ${@link String} 路由id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-12-19 10:37:42
     */
    void save(List<ManageRoutePredicateVo> vos, String routerId, String serviceId, boolean override) throws RuntimeException;

    /**
     * 通过id查询详情
     *
     * @param predicateId ${@link String} 断言id
     * @return ManageRoutePredicateDto ${@link ManageRoutePredicateDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-12-19 10:37:42
     */
    ManageRoutePredicateDto getById(String predicateId) throws RuntimeException;


    /**
     * 通过routeId查询详情
     *
     * @param routeId ${@link String} 路由id
     * @return List<ManageRoutePredicateDto> ${@link List<ManageRoutePredicateDto>} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-12-19 10:37:42
     */
    List<ManageRoutePredicateDto> getByRouteId(String routeId) throws RuntimeException;


    /**
     * 通过routeIds查询详情
     *
     * @param routeIds ${@link Set<String>} 路由ids
     * @return Map<String, List < ManageRoutePredicateDto>> ${@link Map<String, List<ManageRoutePredicateDto>>} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-12-19 10:37:42
     */
    Map<String, List<ManageRoutePredicateDto>> getByRouteId(Set<String> routeIds) throws RuntimeException;


    /**
     * 通过routerId删除
     *
     * @param routerId ${@link String} 路由id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-12-19 10:37:42
     */
    void deleteByRouterId(String routerId) throws RuntimeException;


    /**
     * 通过routerIds删除
     *
     * @param routerIds ${@link Set<String>} 路由ids
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2021-12-19 10:37:42
     */
    void deleteByRouterIds(Set<String> routerIds) throws RuntimeException;
}
