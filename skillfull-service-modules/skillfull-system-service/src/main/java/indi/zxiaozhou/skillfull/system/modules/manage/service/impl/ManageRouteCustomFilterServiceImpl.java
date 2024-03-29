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
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageRouteCustomFilterVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageCustomFilterEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageRouteCustomFilterEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.mapper.ManageRouteCustomFilterMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageCustomFilterService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.IManageRouteCustomFilterService;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageCustomFilterSimpleDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageRouteCustomFilterDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.RouterCustomFilterDto;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.ManageCustomFilterSimpleCopyMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.ManageRouteCustomFilterCopyMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.ManageRouteCustomFilterPageCopyMap;
import indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct.ManageRouteCustomFilterQueryCopyMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 路由-自定义过滤器表(ManageRouteCustomFilter)业务层实现
 *
 * @author zxiaozhou
 * @copyright zxiaozhou（https://divisu.com）
 * @date 2021-12-19 00:22:17
 * @since JDK1.8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManageRouteCustomFilterServiceImpl extends ServiceImpl<ManageRouteCustomFilterMapper, ManageRouteCustomFilterEntity> implements IManageRouteCustomFilterService {
    private final ManageRouteCustomFilterCopyMap map;
    private final ManageRouteCustomFilterPageCopyMap pageMap;
    private final ManageRouteCustomFilterQueryCopyMap queryMap;
    private final ManageRouteCustomFilterMapper mapper;
    private final ManageCustomFilterSimpleCopyMap simpleCopyMap;
    private final IManageCustomFilterService customFilterService;

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void save(List<ManageRouteCustomFilterVo> customFilters, String routerId, boolean override) throws RuntimeException {
        if (override) {
            deleteByRouterId(routerId);
        }
        // 保存新数据
        if (CollectionUtil.isNotEmpty(customFilters)) {
            List<ManageRouteCustomFilterEntity> list = new ArrayList<>(customFilters.size());
            customFilters.forEach(v -> {
                ManageRouteCustomFilterEntity entity = ManageRouteCustomFilterEntity.builder()
                        .customFilterId(v.getCustomFilterId())
                        .routeId(routerId)
                        .filterType(v.getFilterType())
                        .build();
                list.add(entity);
            });
            boolean b = this.saveBatch(list);
            if (!b) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "保存数据失败");
            }
        }
    }

    @Override
    public Map<String, List<ManageCustomFilterSimpleDto>> getByRouterIds(Set<String> routerIds) throws RuntimeException {
        if (CollectionUtil.isEmpty(routerIds)) {
            return Collections.emptyMap();
        }
        // 查询所有自定义过滤器id
        LambdaQueryWrapper<ManageRouteCustomFilterEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(ManageRouteCustomFilterEntity::getRouteId, routerIds);
        List<ManageRouteCustomFilterEntity> list = this.list(lambdaQueryWrapper);
        Map<String, List<ManageCustomFilterSimpleDto>> resultMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(list)) {
            Map<String, List<String>> customFilterIdMap = new HashMap<>();
            Set<String> customFilterIds = new HashSet<>(list.size());
            list.forEach(v -> {
                customFilterIds.add(v.getCustomFilterId());
                List<String> strings = customFilterIdMap.get(v.getRouteId());
                if (CollectionUtil.isEmpty(strings)) {
                    strings = new ArrayList<>();
                }
                strings.add(v.getCustomFilterId());
                customFilterIdMap.put(v.getRouteId(), strings);
            });
            LambdaQueryWrapper<ManageCustomFilterEntity> customFilterLambdaQueryWrapper = new LambdaQueryWrapper<>();
            customFilterLambdaQueryWrapper.in(ManageCustomFilterEntity::getCustomFilterId, customFilterIds)
                    .eq(ManageCustomFilterEntity::getFilterStatus, 1);
            List<ManageCustomFilterEntity> customFilterEntityList = customFilterService.list(customFilterLambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(customFilterEntityList)) {
                List<ManageCustomFilterSimpleDto> manageCustomFilterSimpleDtos = simpleCopyMap.aToB(customFilterEntityList);
                customFilterIdMap.forEach((k, v) -> {
                    List<ManageCustomFilterSimpleDto> collect = manageCustomFilterSimpleDtos.stream().filter(sv -> v.contains(sv.getCustomFilterId())).collect(Collectors.toList());
                    resultMap.put(k, collect);
                });
            }
        }
        return resultMap;
    }


    @Override
    public List<ManageCustomFilterSimpleDto> getByRouterId(String routerId) throws RuntimeException {
        // 查询所有自定义过滤器id
        LambdaQueryWrapper<ManageRouteCustomFilterEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ManageRouteCustomFilterEntity::getRouteId, routerId);
        List<ManageRouteCustomFilterEntity> list = this.list(lambdaQueryWrapper);
        List<ManageCustomFilterSimpleDto> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list)) {
            // 获取自定义过滤器
            Set<String> customFilterIds = new HashSet<>();
            list.forEach(v -> customFilterIds.add(v.getCustomFilterId()));
            LambdaQueryWrapper<ManageCustomFilterEntity> customFilterLambdaQueryWrapper = new LambdaQueryWrapper<>();
            customFilterLambdaQueryWrapper.in(ManageCustomFilterEntity::getCustomFilterId, customFilterIds)
                    .eq(ManageCustomFilterEntity::getFilterStatus, 1);
            List<ManageCustomFilterEntity> customFilterEntityList = customFilterService.list(customFilterLambdaQueryWrapper);
            result = simpleCopyMap.aToB(customFilterEntityList);
        }
        return result;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, readOnly = true)
    public ManageRouteCustomFilterDto getById(String routeCustomFilterId) throws RuntimeException {
        ManageRouteCustomFilterEntity byId = super.getById(routeCustomFilterId);
        if (Objects.isNull(byId)) {
            throw new ResponseException(Status.DATABASE_BASE_ERROR, "未找到符合条件数据");
        }
        return map.eToD(byId);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteByRouterId(String routerId) throws RuntimeException {
        LambdaQueryWrapper<ManageRouteCustomFilterEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ManageRouteCustomFilterEntity::getRouteId, routerId);
        List<ManageRouteCustomFilterEntity> list = this.list(lambdaQueryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            Set<String> routeCustomFilterIds = new HashSet<>(list.size());
            list.forEach(v -> routeCustomFilterIds.add(v.getRouteCustomFilterId()));
            int i = mapper.physicalDeleteBatchIds(routeCustomFilterIds);
            if (i <= 0) {
                throw new ResponseException(Status.DATABASE_BASE_ERROR, "删除历史数据失败");
            }
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void deleteByRouterIds(Set<String> routerIds) throws RuntimeException {
        LambdaQueryWrapper<ManageRouteCustomFilterEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(ManageRouteCustomFilterEntity::getRouteId, routerIds);
        this.remove(lambdaQueryWrapper);
    }


    @Override
    public Map<String, RouterCustomFilterDto> getGatewayByRouterIds(Set<String> routerIds) throws RuntimeException {

        return new HashMap<>();
    }
}
