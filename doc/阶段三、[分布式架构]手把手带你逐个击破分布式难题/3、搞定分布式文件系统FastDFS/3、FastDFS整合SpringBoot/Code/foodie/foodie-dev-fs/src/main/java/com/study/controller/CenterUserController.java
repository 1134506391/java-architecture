package com.study.controller;

import com.study.pojo.Users;
import com.study.pojo.vo.UsersVO;
import com.study.resourse.FileResource;
import com.study.service.FdfsService;
import com.study.service.center.CenterUserService;
import com.study.utils.CookieUtils;
import com.study.utils.JsonUtils;
import com.study.utils.StudyJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("fdfs")
public class CenterUserController extends com.study.controller.BaseController {

    @Autowired
    private FileResource fileResource;
    @Autowired
    private CenterUserService centerUserService;
    @Autowired
    private FdfsService fdfsService;

    // 头像上传
    @PostMapping("uploadFace")
    public StudyJSONResult uploadFace(
            String userId,
            MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String path = "";
        // 开始文件上传
        if (file != null) {
            // 获得文件上传的文件名称
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)) {

                // 文件重命名  study-face.png -> ["study-face", "png"]
                String fileNameArr[] = fileName.split("\\.");

                // 获取文件的后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];

                if (!suffix.equalsIgnoreCase("png") &&
                        !suffix.equalsIgnoreCase("jpg") &&
                        !suffix.equalsIgnoreCase("jpeg")) {
                    return StudyJSONResult.errorMsg("图片格式不正确！");
                }
//                图片上传
//                path = fdfsService.upload(file, suffix);

                path = fdfsService.uploadOSS(file, userId, suffix);
                System.out.println(path);
            }
        } else {
            return StudyJSONResult.errorMsg("文件不能为空！");
        }

        // 更新头像图片到数据库
        if (StringUtils.isNotBlank(path)) {
//            String finalUserFaceUrl = fileResource.getHost() + path;
            String finalUserFaceUrl = fileResource.getOssHost() + path;

            Users userResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);

            UsersVO usersVO = conventUsersVO(userResult);

            CookieUtils.setCookie(request, response, "user",
                    JsonUtils.objectToJson(usersVO), true);
        } else {
            return StudyJSONResult.errorMsg("上传头像失败");
        }

        return StudyJSONResult.ok();
    }

}
