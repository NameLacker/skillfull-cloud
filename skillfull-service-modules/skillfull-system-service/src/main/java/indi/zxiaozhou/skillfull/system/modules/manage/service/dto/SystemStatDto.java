// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author zxiaozhou zxiaozhou
 * @date 2020-10-11 21:04
 * @since JDK1.8
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
public class SystemStatDto implements Serializable {
    private static final long serialVersionUID = 2784324896203301262L;

    @Schema(name = "manageTotalService", title = "纳入管理总服务数")
    private int manageTotalService;

    @Schema(name = "notManageTotalService", title = "未纳入管理总服务数")
    private int notManageTotalService;

    @Schema(name = "healthyInstanceCount", title = "健康实例数")
    private int healthyInstanceCount;

    @Schema(name = "noHealthyInstanceCount", title = "不健康实例数")
    private int noHealthyInstanceCount;
}
