// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.modules.rbac.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 角色表查询Response
 *
 * @author zxiaozhou
 * @date 2020-10-08 13:44:02
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
public class RbacRoleEffectiveDto implements Serializable {
    private static final long serialVersionUID = -17389117269525927L;

    @Schema(name = "roleId", title = "角色id")
    private String roleId;

    @Schema(name = "roleName", title = "角色名称")
    private String roleName;

    @Schema(name = "roleCode", title = "角色编码")
    private String roleCode;

    @Schema(name = "roleSysCode", title = "角色系统编码(系统自动创建)")
    private String roleSysCode;

    @Schema(name = "remark", title = "备注")
    private String remark;

    @Schema(name = "createUserName", title = "创建用户姓名")
    private String createUserName;

    @Schema(name = "parentRoleId", title = "上级角色id", hidden = true)
    private String parentRoleId;

}