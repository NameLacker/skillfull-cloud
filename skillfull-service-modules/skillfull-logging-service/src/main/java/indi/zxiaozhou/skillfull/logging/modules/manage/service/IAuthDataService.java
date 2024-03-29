// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.logging.modules.manage.service;

import indi.zxiaozhou.skillfull.corecommon.base.model.stream.AuthLogModel;
import indi.zxiaozhou.skillfull.coredatabase.base.service.BaseService;
import indi.zxiaozhou.skillfull.coredatabase.base.service.dto.PageDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.controller.vo.AuthDataPageVo;
import indi.zxiaozhou.skillfull.logging.modules.manage.entity.AuthDataEntity;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.AuthDataDto;
import indi.zxiaozhou.skillfull.logging.modules.manage.service.dto.AuthDataPageDto;

import java.util.List;

/**
 * 登录日志(AuthData)业务层接口
 *
 * @author zxiaozhou
 * @copyright zxiaozhou（https://divisu.com）
 * @date 2022-01-26 21:53:03
 * @since JDK1.8
 */
public interface IAuthDataService extends BaseService<AuthDataEntity> {
    /**
     * 日志存储
     *
     * @param model ${@link AuthLogModel}
     * @author zxiaozhou
     * @date 2022-01-27 19:48
     */
    void save(AuthLogModel model) throws RuntimeException;


    /**
     * 日志批量存储
     *
     * @param models ${@link List< AuthLogModel >}
     * @author zxiaozhou
     * @date 2022-01-27 19:48
     */
    void saveBatch(List<AuthLogModel> models) throws RuntimeException;


    /**
     * 分页查询
     *
     * @param vo ${@link AuthDataPageVo} 登录日志分页查询Vo
     * @return PageDto<AuthDataPageDto> ${@link PageDto<AuthDataPageDto>} 分页查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2022-01-26 21:53:03
     */
    PageDto<AuthDataPageDto> pageByModel(AuthDataPageVo vo) throws RuntimeException;


    /**
     * 通过id查询详情
     *
     * @param authLogId ${@link String} 授权日志id
     * @return AuthDataDto ${@link AuthDataDto} 查询结果
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2022-01-26 21:53:03
     */
    AuthDataDto getById(String authLogId) throws RuntimeException;


    /**
     * 通过authLogId删除
     *
     * @param authLogId ${@link String} 授权日志id
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2022-01-26 21:53:03
     */
    void deleteById(String authLogId) throws RuntimeException;


    /**
     * 登录日志批量删除
     *
     * @param authLogIds ${@link List<String>}
     * @throws RuntimeException ${@link RuntimeException}
     * @author zxiaozhou
     * @date 2022-01-26 21:53:03
     */
    void deleteBatch(List<String> authLogIds) throws RuntimeException;
}
