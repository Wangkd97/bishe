package com.ysu.tour.controller;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController   //让返回值为字符串
@RequestMapping("/manage/pic/")
@CrossOrigin
public class PictureController {

    @Autowired
    IPictureService iPictureService;

    @RequestMapping(value = "upload.do")
    public ServerResponse upload(@RequestParam(value = "uploadfile",required = false)MultipartFile file){
        String path="D:\\qrcode";
        return iPictureService.uploadPic(file,path);
    }
    @RequestMapping(value = "uploadCover.do")
    public ServerResponse uploadCover(@RequestParam(value = "uploadCover",required = false)MultipartFile file){
        String path="D:\\qrcode";
        return iPictureService.uploadPic(file,path);
    }


}
