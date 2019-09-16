package vex.muzhi.community.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import vex.muzhi.community.exception.CustomizeException;

/**
 * Author: lichuang
 * Date: Create in 16:09 2019/9/16
 * Description:
 */
@ControllerAdvice()
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ModelAndView handle(Throwable ex, Model model) {
        if (ex instanceof CustomizeException) {
            model.addAttribute("message", ex.getMessage());
        } else {
            model.addAttribute("message", "服务正忙，请稍后重试~");
        }
        return new ModelAndView("error");
    }

}
