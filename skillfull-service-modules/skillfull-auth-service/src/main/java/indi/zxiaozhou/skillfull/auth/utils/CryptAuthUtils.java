// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.utils;


import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * 加密工具
 *
 * @author zxiaozhou
 * @date 2020-06-29 15:58
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
public class CryptAuthUtils {
    private static CryptAuthUtils utils;
    private final PasswordEncoder encoder;


    /**
     * 加密明文密码
     *
     * @param password  ${@link String} 明文密码
     * @param secretKey ${@link String} 盐
     * @return String ${@link String}
     * @author zxiaozhou
     * @date 2019-05-19 00:05
     */
    public static String getEncoderPassword(@NotNull String password, String secretKey) {
        if (StringUtils.isNotBlank(password)) {
            password = password.trim();
        } else {
            password = "";
        }
        if (StringUtils.isNotBlank(secretKey)) {
            secretKey = secretKey.trim();
        } else {
            secretKey = "";
        }
        return utils.encoder.encode(password + secretKey);
    }


    /**
     * 密码匹配
     *
     * @param rawPassword     ${@link CharSequence} 原密码
     * @param encodedPassword ${@link String} 加密后的密码
     * @author zxiaozhou
     * @date 2019-05-19 00:41
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return utils.encoder.matches(rawPassword, encodedPassword);
    }


    /**
     * 密码匹配
     *
     * @param password        ${@link String} 明文密码
     * @param salt            ${@link String} 密码盐
     * @param encodedPassword ${@link String} 加密后的密码
     * @author zxiaozhou
     * @date 2019-05-19 00:41
     */
    public static boolean matches(String password, String salt, String encodedPassword) {
        CharSequence rawPassword = password + salt;
        return matches(rawPassword, encodedPassword);
    }


    /**
     * 通过明文密码获取密码信息
     *
     * @param password ${@link String} 明文密码
     * @return PasswordInfo ${@link PasswordInfo} 密码信息
     * @author zxiaozhou
     * @date 2020-11-12 17:35
     */
    public static PasswordInfo getPasswordInfo(String password) {
        String secretKey = CoreCommonUtils.get32UUId();
        String secretPassword = getEncoderPassword(password, secretKey);
        PasswordInfo passwordInfo = new PasswordInfo();
        passwordInfo.setSalt(secretKey);
        passwordInfo.setEncodedPassword(secretPassword);
        return passwordInfo;
    }


    @PostConstruct
    private void init() {
        utils = this;
    }


    @Setter
    @Getter
    @ToString
    @EqualsAndHashCode
    public static class PasswordInfo {
        /**
         * 密码盐
         */
        private String salt;

        /**
         * 密文密码
         */
        private String encodedPassword;
    }
}
