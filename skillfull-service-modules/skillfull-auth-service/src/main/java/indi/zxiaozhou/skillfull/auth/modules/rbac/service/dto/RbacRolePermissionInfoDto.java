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
import java.util.Objects;
import java.util.Set;

/**
 * @author zxiaozhou
 * @date 2022-01-28 09:22
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class RbacRolePermissionInfoDto implements Serializable {
    private static final long serialVersionUID = -63399996952662860L;

    @Schema(name = "roleCode", title = "角色编码")
    private String roleCode;

    @Schema(name = "roleSysCode", title = "角色系统编码(系统自动创建)")
    private String roleSysCode;

    @Schema(name = "roleId", title = "角色id")
    private String roleId;

    @Schema(name = "permissionId", title = "权限id")
    private String permissionId;

    @Schema(name = "metaTitle", title = "菜单名称")
    private String metaTitle;

    @Schema(name = "actionSet", title = "菜单按钮权限")
    private Set<Action> actionSet;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RbacRolePermissionInfoDto)) {
            return false;
        }
        RbacRolePermissionInfoDto that = (RbacRolePermissionInfoDto) o;
        return Objects.equals(getPermissionId(), that.getPermissionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPermissionId());
    }


    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema
    public static class Action implements Serializable {
        private static final long serialVersionUID = 6588630894991792617L;

        @Schema(name = "permissionId", title = "权限id")
        private String permissionId;

        @Schema(name = "actions", title = "按钮权限action,多个英文逗号隔开")
        private String actions;

        @Schema(name = "roleId", title = "角色id")
        private String roleId;

        @Schema(name = "actionType", title = "按钮权限类型:1-前端型,2-后端型,3-前后端型")
        private Integer actionType;

        @Schema(name = "metaTitle", title = "菜单名称")
        private String metaTitle;

        @Schema(name = "parentId", title = "父id")
        private String parentId;


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Action)) {
                return false;
            }
            Action action = (Action) o;
            return Objects.equals(getPermissionId(), action.getPermissionId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPermissionId());
        }
    }
}
