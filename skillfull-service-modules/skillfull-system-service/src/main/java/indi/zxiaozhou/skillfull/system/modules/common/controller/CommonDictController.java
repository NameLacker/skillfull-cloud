// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.controller;

import indi.zxiaozhou.skillfull.corecommon.base.Result;
import indi.zxiaozhou.skillfull.corecommon.constant.CoreCommonCacheConstant;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotNullSize;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlankOrNull;
import indi.zxiaozhou.skillfull.coredatabase.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonDictItemPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonDictItemVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonDictPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonDictVo;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonDictItemService;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonDictService;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonDictItemDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonDictItemPageDto;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonDictPageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据字典表(CommonDict)控制层
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:14
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "CommonDict", description = "数据字典表Api接口相关")
@RequestMapping(value = "/common-dict", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonDictController extends BaseController {
    private final ICommonDictService service;
    private final ICommonDictItemService itemService;


    @Operation(summary = "数据字典表添加", tags = {"v1.0.0"}, description = "添加数据字典表")
    @PostMapping(value = "/insert")
    public Result<String> insert(@RequestBody @Valid CommonDictVo vo) {
        service.save(vo);
        return ok("添加成功");
    }


    @Operation(summary = "通过字典id修改", tags = {"v1.0.0"}, description = "修改数据字典表")
    @Parameter(in = ParameterIn.PATH, description = "字典id", name = "dictId", required = true)
    @PutMapping(value = "/update/{dictId}")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> update(@PathVariable(required = false) @PathNotBlankOrNull(message = "字典id不能为空") String dictId,
                                 @RequestBody @Valid CommonDictVo vo) {
        service.updateById(dictId, vo);
        return ok("修改成功");
    }


    @Operation(summary = "修改字段状态", tags = {"v1.0.0"}, description = "修改字段状态")
    @Parameters({
            @Parameter(description = "字典id", name = "dictId", required = true),
            @Parameter(description = "操作类型:0-禁用,1-启用", name = "type", required = true)
    })
    @GetMapping(value = "/update/state/dict")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> updateDictState(@RequestParam(required = false) @NotBlank(message = "字典id不能为空") String dictId,
                                          @RequestParam(required = false) @NotNull(message = "操作类型不能为空") Integer type) {
        service.updateDictState(dictId, type);
        return ok(type == 0 ? "禁用成功" : "启用成功");
    }


    @Operation(summary = "数据字典表逻辑删除", tags = {"v1.0.0"}, description = "删除数据字典表")
    @Parameter(in = ParameterIn.PATH, description = "字典id", name = "dictId", required = true)
    @DeleteMapping(value = "/delete-one/{dictId}")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> deleteById(@PathVariable(required = false) @PathNotBlankOrNull(message = "字典id不能为空") String dictId) {
        service.deleteById(dictId);
        return ok("删除成功");
    }


    @Operation(summary = "数据字典表逻辑批量删除", tags = {"v1.0.0"}, description = "批量删除数据字典表")
    @PostMapping(value = "/delete-batch")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> deleteBatch(@RequestBody @NotNullSize(message = "待删除字典id不能为空") List<String> dictIds) {
        service.deleteBatch(dictIds);
        return ok("批量删除成功");
    }


    @Operation(summary = "通过编码获取数据字典", tags = {"v1.0.0"}, description = "通过编码获取数据字典")
    @Parameter(in = ParameterIn.PATH, description = "字典编码", name = "dictCode", required = true)
    @GetMapping(value = "/select/by-code/{dictCode}")
    @Cacheable(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, key = "#dictCode")
    public Result<List<CommonDictItemDto>> getListByCode(@PathVariable(required = false) @PathNotBlankOrNull(message = "字典编码不能为空") String dictCode) {
        return ok(itemService.selectListByCode(dictCode));
    }


    @Operation(summary = "数据字典表分页查询", tags = {"v1.0.0"}, description = "分页查询数据字典表")
    @PostMapping(value = "/select/page")
    public Result<PageDto<CommonDictPageDto>> selectPage(@RequestBody CommonDictPageVo vo) {
        return ok(service.pageByModel(vo));
    }


    @Operation(summary = "数据字典配置项表添加", tags = {"v1.0.0"}, description = "添加数据字典配置项表")
    @PostMapping(value = "/insert-item")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> insertItem(@RequestBody @Valid CommonDictItemVo vo) {
        itemService.save(vo);
        return ok("添加子项成功");
    }


    @Operation(summary = "通过字典项id修改", tags = {"v1.0.0"}, description = "修改数据字典配置项表")
    @Parameter(in = ParameterIn.PATH, description = "字典项id", name = "itemId", required = true)
    @PutMapping(value = "/update-item/{itemId}")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> updateItem(@PathVariable(required = false) @PathNotBlankOrNull(message = "字典项id不能为空") String itemId,
                                     @RequestBody @Valid CommonDictItemVo vo) {
        itemService.updateById(itemId, vo);
        return ok("修改子项成功");
    }


    @Operation(summary = "通过字典id修改", tags = {"v1.0.0"}, description = "修改数据字典表")
    @Parameters({
            @Parameter(description = "字典项id", name = "itemId", required = true),
            @Parameter(description = "操作类型:0-禁用,1-启用", name = "type", required = true)
    })
    @GetMapping(value = "/update/state/item")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> updateDictItemState(@RequestParam(required = false) @NotBlank(message = "字典项id不能为空") String itemId,
                                              @RequestParam(required = false) @NotNull(message = "操作类型不能为空") Integer type) {
        itemService.updateDictItemState(itemId, type);
        return ok(type == 0 ? "禁用成功" : "启用成功");
    }


    @Operation(summary = "数据字典配置项表逻辑删除", tags = {"v1.0.0"}, description = "删除数据字典配置项表")
    @Parameter(in = ParameterIn.PATH, description = "字典项id", name = "itemId", required = true)
    @DeleteMapping(value = "/delete-item-one/{itemId}")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> deleteItem(@PathVariable(required = false) @PathNotBlankOrNull(message = "字典项id不能为空") String itemId) {
        itemService.deleteById(itemId);
        return ok("删除子项成功");
    }


    @Operation(summary = "数据字典配置项表逻辑批量删除", tags = {"v1.0.0"}, description = "批量删除数据字典配置项表")
    @PostMapping(value = "/delete-item-batch")
    @CacheEvict(value = CoreCommonCacheConstant.ENGINE_DICT_CACHE, allEntries = true)
    public Result<String> deleteItemBatch(@RequestBody @NotNullSize(message = "待删除字典项id不能为空") List<String> itemIds) {
        itemService.deleteBatch(itemIds);
        return ok("批量删除子项成功");
    }


    @Operation(summary = "数据字典项表分页查询", tags = {"v1.0.0"}, description = "数据字典项表分页查询")
    @PostMapping(value = "/select/item-page")
    public Result<PageDto<CommonDictItemPageDto>> selectItemPage(@RequestBody CommonDictItemPageVo vo) {
        return ok(itemService.pageByModel(vo));
    }
}