package vex.muzhi.community.dto;

import lombok.Data;
import vex.muzhi.community.enums.ResultCodeEnum;
import vex.muzhi.community.exception.CustomizeException;
import vex.muzhi.community.enums.ICustomizeErrorCode;

/**
 * Author: lichuang
 * Date: Create in 11:11 2019/9/17
 * Description:
 */
@Data
public class ResultDTO<T> {

    private Integer code;

    private String message;

    private T data;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(ICustomizeErrorCode code) {
        return errorOf(code.getCode(), code.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(), ex.getMessage());
    }

    public static <T> ResultDTO okOf(T data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ResultCodeEnum.SUCCESS.getCode());
        resultDTO.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        resultDTO.setData(data);
        return resultDTO;
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ResultCodeEnum.SUCCESS.getCode());
        resultDTO.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return resultDTO;
    }
}
