package com.atguigu.gmall0422.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class FileUploadController {
    /**
     * 图片上传
     * @return
     */
    //获取文件服务器IP地址
    @Value("${fileServer.url}")
    private String fileURl;

    @RequestMapping("fileUpload")
    public String fileUpload(MultipartFile file)throws IOException, MyException {
        String imgUrl=fileURl;
        if(file!=null) {
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //动态获取上传的文件名称
            String originalFilename = file.getOriginalFilename();
            //获取文件的后缀名
            String extName = StringUtils.substringAfterLast(originalFilename, ".");
            //String orginalFilename = "f://image//01.jpg";
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            for (int i = 0; i < upload_file.length; i++) {
                String path= upload_file[i];
                imgUrl+="/"+path;
                //System.out.println("s = " + s);
            }
        }
        return imgUrl;
    }
}
