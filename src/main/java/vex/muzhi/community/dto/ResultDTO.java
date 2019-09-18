package vex.muzhi.community.dto;

import lombok.Data;
import vex.muzhi.community.enums.ResultCode;
import vex.muzhi.community.exception.CustomizeException;
import vex.muzhi.community.exception.ICustomizeErrorCode;

/**
 * Author: lichuang
 * Date: Create in 11:11 2019/9/17
 * Description:
 */
@Data
public class ResultDTO {

    private Integer code;

    private String message;

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

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ResultCode.SUCCESS.getCode());
        resultDTO.setMessage(ResultCode.SUCCESS.getMessage());
        return resultDTO;
    }

}
