// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.system.modules.manage.mapper;

import indi.zxiaozhou.skillfull.coredatabase.base.mapper.BaseMapper;
import indi.zxiaozhou.skillfull.system.modules.manage.entity.ManageCustomFilterEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * 自定义过滤器(ManageCustomFilter)持久层
 *
 * @author zxiaozhou
 * @copyright zxiaozhou（https://divisu.com）
 * @date 2021-12-19 00:22:14
 * @since JDK1.8
 */
@Repository
public interface ManageCustomFilterMapper extends BaseMapper<ManageCustomFilterEntity> {

    /**
     * 通过自定义过滤器id物理删除
     *
     * @param customFilterId ${@link String} 自定义过滤器id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author zxiaozhou
     * @date 2021-12-19 00:22:14
     */
    int physicalDeleteById(@Param("id") String customFilterId);


    /**
     * 通过自定义过滤器id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     * @author zxiaozhou
     * @date 2021-12-19 00:22:14
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);
}
