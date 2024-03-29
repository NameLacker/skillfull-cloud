// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.constant;

/**
 * 缓存常量
 *
 * @author zxiaozhou
 * @date 2020-07-22 16:18
 * @since JDK11
 */
public interface CoreCommonCacheConstant {

    /**
     * 字典信息缓存
     */
    String ENGINE_DICT_CACHE = "ENGINE_DICT_CACHE:";

    /**
     * 常量字典缓存
     */
    String ENGINE_CONSTANT_DICT_CACHE = "ENGINE_CONSTANT_DICT_CACHE:";

    /**
     * 分类字典信息缓存
     */
    String ENGINE_DICT_CATEGORY_CACHE = "ENGINE_DICT_CATEGORY_CACHE:";

    /**
     * 区域编码缓存
     */
    String ENGINE_AREA_CACHE = "ENGINE_AREA_CACHE:";

    /**
     * 组织机构
     */
    String ENGINE_ORG_CACHE = "ENGINE_ORG_CACHE";

    /**
     * 有效路由缓存
     */
    String SYSTEM_ROUTE_INFO_CACHE = "SYSTEM_ROUTE_INFO_CACHE";

    /**
     * 系统所有有效按钮权限缓存
     */
    String SYSTEM_AUTH_ACTION_CACHE = "SYSTEM_AUTH_ACTION_CACHE";

    /**
     * 数据加解密基本信息缓存
     */
    String USER_DATA_SECURITY_CACHE = "USER_DATA_SECURITY_CACHE:";

    /**
     * 数据加解密基本信息缓存待失效区
     */
    String USER_DATA_SECURITY_WAIT_INVALID_CACHE = "USER_DATA_SECURITY_WAIT_INVALID_CACHE:";
}
