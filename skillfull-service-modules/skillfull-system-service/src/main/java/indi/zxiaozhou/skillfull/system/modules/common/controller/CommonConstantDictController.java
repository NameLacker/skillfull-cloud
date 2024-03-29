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
import indi.zxiaozhou.skillfull.corecommon.constant.model.ConstantDictModel;
import indi.zxiaozhou.skillfull.corecommon.validation.annotation.PathNotBlankOrNull;
import indi.zxiaozhou.skillfull.coremvc.base.controller.BaseController;
import indi.zxiaozhou.skillfull.system.modules.common.service.ICommonConstantDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 常量字典相关控制层
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:14
 * @since JDK11
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "CommonConstantDict", description = "常量字典相关")
@RequestMapping(value = "/common-constant-dict", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonConstantDictController extends BaseController {
    private final ICommonConstantDictService service;


    @Operation(summary = "通过类型获取常量字典", tags = {"v1.0.0"}, description = "通过类型获取常量字典")
    @Parameter(in = ParameterIn.PATH, description = "常量字典类型,多个英文逗号隔开", name = "constantTypes", required = true)
    @GetMapping(value = "/select/{constantTypes}")
    public Result<List<ConstantDictModel>> getListByConstantType(@PathVariable(required = false) @PathNotBlankOrNull(message = "字典类型不能为空") String constantTypes) {
        return ok(service.getListByConstantTypes(constantTypes));
    }
}