// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import indi.zxiaozhou.skillfull.corecommon.constant.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 响应结果封装
 *
 * @author zxiaozhou
 * @date 2020-06-22 16:29
 * @since JDK11
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_EMPTY)
@Schema
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 2824340746431686918L;

    @Schema(title = "响应状态码")
    private int code;

    @Schema(title = "成功标识")
    private boolean success;

    @JsonInclude(value = Include.NON_NULL)
    @Schema(title = "响应消息")
    private String message;

    @JsonInclude(value = Include.NON_NULL)
    @Schema(title = "响应数据")
    private T data;

    @Schema(title = "响应时间")
    private long timestamp;

    public Result() {
    }

    public Result(Status status) {
        this.setSuccess(status.getCode() == 0);
        this.setCode(status.getCode());
        this.setMessage(status.getMessage());
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Status status, T data) {
        this.setSuccess(status.getCode() == 0);
        this.setCode(status.getCode());
        this.setMessage(status.getMessage());
        this.setData(data);
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Status status, String message) {
        this.setSuccess(status.getCode() == 0);
        this.setCode(status.getCode());
        this.setMessage(message);
        this.setData(data);
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Status status, String message, T data) {
        this.setSuccess(status.getCode() == 0);
        this.setCode(status.getCode());
        this.setMessage(StringUtils.isNotBlank(message) ? message : status.getMessage());
        this.setData(data);
        this.timestamp = System.currentTimeMillis();
    }

    public Result(int code, T data) {
        this.setSuccess(code == 0);
        this.setCode(code);
        this.setData(data);
        this.timestamp = System.currentTimeMillis();
    }

    public Result(int code, String message) {
        this.setSuccess(code == 0);
        this.setCode(code);
        this.setMessage(message);
        this.timestamp = System.currentTimeMillis();
    }

    public Result(int code, String message, T data) {
        this.setSuccess(code == 0);
        this.setCode(code);
        this.setData(data);
        this.setMessage(message);
        this.timestamp = System.currentTimeMillis();
    }
}