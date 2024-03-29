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

import indi.zxiaozhou.skillfull.coredatabase.base.controller.vo.BasePageVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 分类字典表分页查询Request
 *
 * @author zxiaozhou
 * @date 2021-01-07 23:40:08
 * @since JDK11
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class CommonCategoryPageVo extends BasePageVo {
    private static final long serialVersionUID = -36807293234802561L;

    @Schema(name = "categoryName", title = "分类名称")
    private String categoryName;

    @Schema(name = "categoryCode", title = "分类编码")
    private String categoryCode;
}