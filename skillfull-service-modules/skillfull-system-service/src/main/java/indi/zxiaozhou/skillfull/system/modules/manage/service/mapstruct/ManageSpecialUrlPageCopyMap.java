// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.service.mapstruct;

import indi.zxiaozhou.skillfull.corecommon.base.service.mapstruct.BaseThreeMap;
import indi.zxiaozhou.skillfull.system.modules.manage.controller.vo.ManageSpecialUrlPageVo;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageSpecialUrlEntity;
import indi.zxiaozhou.skillfull.system.modules.manage.service.dto.ManageSpecialUrlPageDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 实体相互转换
 *
 * @author zxiaozhou
 * @copyright zxiaozhou（https://divisu.com）
 * @since 2021-12-19 09:34:51
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface ManageSpecialUrlPageCopyMap extends BaseThreeMap<ManageSpecialUrlEntity, ManageSpecialUrlPageDto, ManageSpecialUrlPageVo> {
}
