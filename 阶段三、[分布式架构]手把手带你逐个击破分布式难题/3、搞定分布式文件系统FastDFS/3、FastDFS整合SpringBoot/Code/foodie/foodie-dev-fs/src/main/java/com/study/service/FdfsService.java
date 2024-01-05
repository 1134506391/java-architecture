package com.study.service;

import org.springframework.web.multipart.MultipartFile;

public interface FdfsService {
    // 图片上传
    public String upload(MultipartFile file, String fileExtName) throws Exception;

    public String uploadOSS(MultipartFile file, String userId, String fileExtName) throws Exception;
}
