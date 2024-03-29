// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import indi.zxiaozhou.skillfull.corecommon.exception.ResponseException;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouteVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.mapper.ManageRouteMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.service.*;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageCustomFilterSimpleDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouteDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouteFilterDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRoutePredicateDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.ManageRouteCopyMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.ManageRoutePageCopyMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.ManageRouteQueryCopyMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 路由(ManageRoute)业务层实现
 *
 * @author zxiaozhou
 * @copyright zxiaozhou（https://divisu.com）
 * @date 2021-12-19 00:22:16
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageRouteServiceImpl extends ServiceImpl<ManageRouteMapper, ManageRouteEntity> implements IManageRouteService {
    private final ManageRouteCopyMap map;
    private final ManageRoutePageCopyMap pageMap;
    private final ManageRouteQueryCopyMap queryMap;
    private final ManageRouteMapper mapper;
    private final IManageRouteFilterService filterService;
    private final IManageRoutePredicateService predicateService;
    private final IManageRouteCustomFilterService routeCustomFilterService;
    private final IManageCustomFilterService customFilterService;
    private final IManageSyncService syncService;


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(ManageRouteVo vo) throws RuntimeException {
        ManageRouteEntity entity = map.vToE(vo);
        boolean result = super.save(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
        }
        // 保存过滤器
        filterService.save(vo.getRouteFilters(), entity.getRouteId(), entity.getServiceId(), true);
        // 保存断言
        predicateService.save(vo.getRoutePredicates(), entity.getRouteId(), entity.getServiceId(), true);
        // 保存自定义过滤器
        routeCustomFilterService.save(vo.getCustomFilters(), entity.getRouteId(), true);
        // 刷新路由
        syncService.syncRoute(true);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateById(String routeId, ManageRouteVo vo) throws RuntimeException {
        // 查询数据是否存在
        this.getById(routeId);
        // 更新数据
        ManageRouteEntity entity = map.vToE(vo);
        entity.setRouteId(routeId);
        boolean result = super.updateById(entity);
        if (!result) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新数据失败");
        }
        // 保存断言
        predicateService.save(vo.getRoutePredicates(), entity.getRouteId(), entity.getServiceId(), true);
        // 保存过滤器
        filterService.save(vo.getRouteFilters(), entity.getRouteId(), entity.getServiceId(), true);
        // 保存自定义过滤器
        routeCustomFilterService.save(vo.getCustomFilters(), entity.getRouteId(), true);
        // 刷新路由
        syncService.syncRoute(true);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public List<ManageRouteDto> selectList(String serviceId) throws RuntimeException {
        LambdaQueryWrapper<ManageRouteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ManageRouteEntity::getServiceId, serviceId);
        List<ManageRouteDto> manageRouteList = map.eToD(this.list(lambdaQueryWrapper));
        if (CollectionUtil.isNotEmpty(manageRouteList)) {
            Set<String> routerIds = new HashSet<>(manageRouteList.size());
            manageRouteList.forEach(v -> routerIds.add(v.getRouteId()));
            // 获取过滤器
            Map<String, List<ManageRouteFilterDto>> manageRouteFilterMap = filterService.getByRouteId(routerIds);
            // 获取断言
            Map<String, List<ManageRoutePredicateDto>> manageRoutePredicateMap = predicateService.getByRouteId(routerIds);
            // 获取自定义过滤器
            Map<String, List<ManageCustomFilterSimpleDto>> customServiceFilterMap = routeCustomFilterService.getByRouterIds(routerIds);
            // 数据处理
            if (CollectionUtil.isNotEmpty(manageRouteFilterMap) || CollectionUtil.isNotEmpty(manageRoutePredicateMap) || CollectionUtil.isNotEmpty(customServiceFilterMap)) {
                manageRouteList.forEach(v -> {
                    v.setRouteFilters(manageRouteFilterMap.get(v.getRouteId()));
                    v.setRoutePredicates(manageRoutePredicateMap.get(v.getRouteId()));
                    List<ManageCustomFilterSimpleDto> manageCustomFilterSimpleDtos = customServiceFilterMap.get(v.getRouteId());
                    if (CollectionUtil.isNotEmpty(manageCustomFilterSimpleDtos)) {
                        v.setCustomFilters(manageCustomFilterSimpleDtos);
                        List<String> customFilterIdList = new ArrayList<>(manageRouteFilterMap.size());
                        manageCustomFilterSimpleDtos.forEach(sv -> customFilterIdList.add(sv.getCustomFilterId()));
                        v.setCustomFilterIds(customFilterIdList);
                    }
                });
            }
        }
        return manageRouteList;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public ManageRouteDto getById(String routeId) throws RuntimeException {
        ManageRouteEntity byId = super.getById(routeId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "未找到符合条件数据");
        }
        ManageRouteDto manageRouteDto = map.eToD(byId);
        // 获取过滤器
        manageRouteDto.setRouteFilters(filterService.getByRouteId(routeId));
        // 获取断言
        manageRouteDto.setRoutePredicates(predicateService.getByRouteId(routeId));
        // 获取自定义过滤器
        List<ManageCustomFilterSimpleDto> byRouterId = routeCustomFilterService.getByRouterId(routeId);
        if (CollectionUtil.isNotEmpty(byRouterId)) {
            manageRouteDto.setCustomFilters(byRouterId);
            List<String> customFilterIdList = new ArrayList<>(byRouterId.size());
            byRouterId.forEach(v -> customFilterIdList.add(v.getCustomFilterId()));
            manageRouteDto.setCustomFilterIds(customFilterIdList);
        }
        return manageRouteDto;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteById(String routeId) throws RuntimeException {
        // 查询数据是否存在
        this.getById(routeId);
        // 删除数据
        boolean b = this.removeById(routeId);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除数据失败");
        }
        // 删除断言
        predicateService.deleteByRouterId(routeId);
        // 删除过滤器
        filterService.deleteByRouterId(routeId);
        // 删除自定义过滤器关联关系
        routeCustomFilterService.deleteByRouterId(routeId);
        // 刷新路由
        syncService.syncRoute(true);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteByServiceId(String serviceId) throws RuntimeException {
        LambdaQueryWrapper<ManageRouteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ManageRouteEntity::getServiceId, serviceId);
        List<ManageRouteEntity> list = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            boolean remove = this.remove(lambdaQueryWrapper);
            if (!remove) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除路由失败");
            }
            Set<String> routerIds = new HashSet<>(list.size());
            list.forEach(v -> routerIds.add(v.getRouteId()));
            // 删除断言
            predicateService.deleteByRouterIds(routerIds);
            // 删除过滤器
            filterService.deleteByRouterIds(routerIds);
            // 删除自定义过滤器关联关系
            routeCustomFilterService.deleteByRouterIds(routerIds);
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updateStatus(String routeId, Integer state) {
        // 查询数据是否存在
        this.getById(routeId);
        ManageRouteEntity updateEntity = ManageRouteEntity.builder()
                .routeId(routeId)
                .routeState(state)
                .build();
        boolean b = this.updateById(updateEntity);
        if (!b) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "更新状态失败");
        }
        // 刷新路由
        syncService.syncRoute(true);
    }
}
