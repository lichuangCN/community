package vex.muzhi.community.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vex.muzhi.community.dto.FileDTO;
import vex.muzhi.community.enums.CustomizeErrorCode;
import vex.muzhi.community.enums.FileResultEnum;
import vex.muzhi.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Author: lichuang
 * Date: Create in 14:07 2019/9/21
 * Description:
 */
@Service
public class FileService {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.image.path}")
    private String imagePath;

    // 图片请求访问路径
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    /**
     * 上传图片
     *
     * @param file
     * @param request
     * @return
     */
    public FileDTO uploadImageFile(MultipartFile file, HttpServletRequest request) {
        // 上传图片的原始名称
        String originalFilename = file.getOriginalFilename();
        // 图片后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 随机生成新的图片名称
        String fileName = UUID.randomUUID().toString().replace("-", "").concat(suffix);
        // 生成上传图片
        File targetPath = new File(uploadFolder + imagePath);
        // 路径不存在，创建
        if (!targetPath.exists()) {
            targetPath.mkdirs();
        }

        // 生成的目标图片
        File targetImage = new File(targetPath, fileName);
        // 保存图片
        try {
            // 图片压缩
            Thumbnails.of(file.getInputStream())    // 图片文件
                    .size(650, 550) // 输出图片大小(宽x高)
                    .outputQuality(0.9f)// 压缩比
                    .toFile(targetImage);// 输出文件
            FileDTO fileDTO = new FileDTO();
            fileDTO.setUrl(staticAccessPath + fileName);
            fileDTO.setSuccess(FileResultEnum.SUCCESS.getCode());
            fileDTO.setMessage(FileResultEnum.SUCCESS.getMessage());
            return fileDTO;
        } catch (IOException e) {
            throw new CustomizeException(CustomizeErrorCode.IMAGE_UPLOAD_FAIL);
        }
    }
}
