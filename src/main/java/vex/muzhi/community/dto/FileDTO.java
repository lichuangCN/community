package vex.muzhi.community.dto;

import lombok.Data;

/**
 * Author: lichuang
 * Date: Create in 19:23 2019/9/20
 * Description: markdown上传图片的返回信息
 */
@Data
public class FileDTO {

    //0表示上传失败;1表示上传成功
    private int success;
    //上传成功时才返回,图片的存储路径
    private String url;
    // 提示的信息
    private String message;

}
