// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.auth.security.validate.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.alibaba.fastjson.JSONObject;
import indi.zxiaozhou.skillfull.auth.security.config.properties.SecurityProperties;
import indi.zxiaozhou.skillfull.auth.security.validate.*;
import indi.zxiaozhou.skillfull.corecommon.utils.CoreCommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static indi.zxiaozhou.skillfull.auth.core.constant.CommonAuthConstant.PICTURE_CODE_KEY_PREFIX;


/**
 * 图片验证码实现
 *
 * @author zxiaozhou
 * @date 2020-06-29 02:30
 * @since JDK11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PictureValidate implements IValidate {
    private final SecurityProperties properties;
    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public ValidateDto getVerification(JSONObject parameter, HttpServletRequest request) {
        // 生成验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(300, 150, 4, 20);
        String imageBase64 = captcha.getImageBase64Data();
        String code = captcha.getCode();
        String codeId = CoreCommonUtils.get32UUId();
        // 存储
        redisTemplate.opsForValue().set(PICTURE_CODE_KEY_PREFIX + codeId, code, properties.getCodeValidityInSeconds(), TimeUnit.SECONDS);
        // 构造验证码web端信息
        ValidateDto validateDto = new ValidateDto();
        validateDto.setCodeType(CodeType.PICTURE_CODE);
        validateDto.setValidTime(properties.getCodeValidityInSeconds());
        validateDto.setCodeValue(imageBase64);
        validateDto.setCodeId(codeId);
        validateDto.setStatus(true);
        return validateDto;
    }


    @Override
    public CheckDto checkVerification(CheckModel parameter) {
        CheckDto checkDto = new CheckDto();
        Object data = redisTemplate.opsForValue().get(PICTURE_CODE_KEY_PREFIX + parameter.getCodeId());
        redisTemplate.delete(PICTURE_CODE_KEY_PREFIX + parameter.getCodeId());
        if (Objects.nonNull(data)) {
            String code = data.toString();
            if (code.equalsIgnoreCase(parameter.getCodeValue())) {
                checkDto.setResult(true);
            } else {
                checkDto.setMsg("验证码错误");
            }
        } else {
            checkDto.setMsg("验证码过期");
        }
        return checkDto;
    }
}
