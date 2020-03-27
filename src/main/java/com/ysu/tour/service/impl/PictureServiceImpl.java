package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.service.IPictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PictureServiceImpl  implements IPictureService{
    @Override
    public ServerResponse uploadPic(MultipartFile file, String path) {
        if(file==null){
            ServerResponse.createServerResponseByFail("上传文件为空");
        }
        //获取图片名字
        String orignFileNamae=file.getOriginalFilename();
        //获取图片扩展名
        String extendName = orignFileNamae.substring(orignFileNamae.lastIndexOf("."));
        //重新生成名字
        String newFileName = UUID.randomUUID().toString()+extendName;

        File pathFile=new File(path);
        if(!pathFile.exists()){
            pathFile.setWritable(true);
            pathFile.mkdir();
        }

        File file1=new File(path,newFileName);
        try {
            file.transferTo(file1);
            Map <String,String> map = new HashMap<>();
            map.put("filename",newFileName);
            map.put("url","img.neuedu.com/"+newFileName);
            return ServerResponse.createServerResponseBySucces("上传成功",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.createServerResponseByFail("上传失败");

    }
}
