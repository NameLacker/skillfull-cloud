// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.common.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.zxiaozhou.skillfull.coredatabase.base.mapper.BaseMapper;
import indi.zxiaozhou.skillfull.system.modules.common.controller.vo.CommonCategoryPageVo;
import indi.zxiaozhou.skillfull.system.modules.common.entity.CommonCategoryEntity;
import indi.zxiaozhou.skillfull.system.modules.common.service.dto.CommonCategoryPageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * 分类字典表(CommonCategory)持久层
 *
 * @author zxiaozhou
 * @date 2021-01-07 23:40:04
 * @since JDK11
 */
@Repository
public interface CommonCategoryMapper extends BaseMapper<CommonCategoryEntity> {
    /**
     * 分页查询
     *
     * @param vo   ${@link CommonCategoryPageVo} 查询条件
     * @param page ${@link Page<CommonCategoryPageDto>} 分页信息
     * @return IPage<CommonCategoryPageDto> ${@link IPage<CommonCategoryPageDto>} 结果
     * @author zxiaozhou
     * @date 2021-01-07 23:40:04
     */
    IPage<CommonCategoryPageDto> pageByModel(Page<CommonCategoryPageDto> page, @Param("query") CommonCategoryPageVo vo);

    /**
     * 通过分类id物理删除
     *
     * @param categoryId ${@link String} 分类id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteById(@Param("id") String categoryId);


    /**
     * 通过分类id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     * @author zxiaozhou
     * @date 2020-08-28 11:36
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);
}