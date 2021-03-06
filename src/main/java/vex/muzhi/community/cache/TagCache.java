package vex.muzhi.community.cache;

import org.apache.commons.lang3.StringUtils;
import vex.muzhi.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: lichuang
 * Date: Create in 18:48 2019/9/19
 * Description:
 */

public class TagCache {

    public static List<TagDTO> get() {
        List<TagDTO> tagDTOList = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOList.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDTOList.add(framework);


        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOList.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOList.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagDTOList.add(tool);
        return tagDTOList;
    }

    /**
     * 检验标签是否合法
     *
     * @param tag 多个标签拼接的字符串
     * @return 校验不通过的标签
     */
    public static String filterInvalid(String tag) {
        String[] tags = StringUtils.split(tag, ",");

        List<TagDTO> tagDTOList = get();
        // 合法标签库
        List<String> tagList = tagDTOList.stream()
                .flatMap(tagDTO -> tagDTO.getTags().stream())
                .collect(Collectors.toList());
        // 非法标签
        String invalid = Arrays.stream(tags)
                .filter(t -> StringUtils.isBlank(t) || !tagList.contains(t))
                .collect(Collectors.joining(","));
        return invalid;
    }
}
