// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.coreprocess.model;

import java.io.Serializable;

/**
 * 表单信息
 *
 * @author zxiaozhou
 * @date 2022-01-03 11:31
 * @since JDK1.8
 */
public class ValidateModel implements Serializable {
    private static final long serialVersionUID = -8035187351349997365L;

    private String field;

    private String label;

    private String defaultValue;

    private String placeholder;
}
