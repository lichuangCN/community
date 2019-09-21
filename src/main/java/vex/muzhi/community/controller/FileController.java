package vex.muzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import vex.muzhi.community.dto.FileDTO;
import vex.muzhi.community.service.FileService;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: lichuang
 * Date: Create in 19:23 2019/9/20
 * Description:
 */

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(@RequestParam(name = "editormd-image-file") MultipartFile file,
                          HttpServletRequest request) {

        return fileService.uploadImageFile(file, request);
    }
}
