package vex.muzhi.community.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import vex.muzhi.community.dto.ResultDTO;
import vex.muzhi.community.exception.CustomizeErrorCode;
import vex.muzhi.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: lichuang
 * Date: Create in 16:09 2019/9/16
 * Description:
 */
@ControllerAdvice()
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ModelAndView handle(Throwable ex,
                                Model model,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if (request.getContentType().equals("application/json")) {
            // 返回的数据是JSON
            ResultDTO resultDTO = null;
            if (ex instanceof CustomizeException) {
                // 自定义的异常信息
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            } else {
                // 出现其他未定义的异常
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                // 将错误信息转换为JSON字符串返回
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            // 跳转错误页面
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

}
