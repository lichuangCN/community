package vex.muzhi.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: lichuang
 * Date: Create in 18:52 2019/9/6
 * Description:
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
