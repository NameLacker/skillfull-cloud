// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.controller.vo;

import indi.zxiaozhou.skillfull.corecommon.validation.annotation.NotBlankOrNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 数据字典配置项表添加或修改Request
 *
 * @author zxiaozhou
 * @date 2020-11-02 09:25:24
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
public class CommonDictItemVo implements Serializable {
    private static final long serialVersionUID = -38550636353943616L;

    @Schema(name = "dictId", title = "字典id", required = true)
    @NotBlankOrNull(message = "字典id不能为空")
    private String dictId;

    @Schema(name = "itemText", title = "字典项名称", required = true)
    @NotBlankOrNull(message = "字典项名称不能为空")
    private String itemText;

    @Schema(name = "itemValue", title = "字典项值", required = true)
    @NotBlankOrNull(message = "字典项值不能为空")
    private String itemValue;

    @Schema(name = "sortOrder", title = "排序,越小越靠前,默认0")
    private int sortOrder;

    @Schema(name = "itemStatus", title = "字典项状态：1启用，0禁用，默认0")
    private int itemStatus;

    @Schema(name = "remark", title = "备注")
    private String remark;
}