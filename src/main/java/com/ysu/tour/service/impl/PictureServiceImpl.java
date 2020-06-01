package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.CategoryPicMapper;
import com.ysu.tour.pojo.CategoryPic;
import com.ysu.tour.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PictureServiceImpl  implements IPictureService{
   @Autowired
    CategoryPicMapper categoryPicMapper;


    @Override
    public ServerResponse uploadPic(MultipartFile file, String path) {
        if(file==null){
            ServerResponse.createServerResponseByFail("上传文件为空");
        }
        //获取图片名字
        String orignFileNamae=file.getOriginalFilename();
        //获取图片扩展名
        //String extendName = orignFileNamae.substring(orignFileNamae.lastIndexOf("."));
        String extendName =".jpg";
        //重新生成名字
        String newFileName = UUID.randomUUID().toString()+extendName;
        System.out.println("newFileName======"+newFileName);
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

    @Override
    public List<String> getListPic(String content) {
        List<String> list=getImgSrc(content);
        return list;
    }

    @Override
    public ServerResponse selectBySId(int sId) {
        List<CategoryPic> categoryPic=categoryPicMapper.selectBySId(sId);
        if (categoryPic!=null){
            return  ServerResponse.createServerResponseBySucess(categoryPic);
        }else{
            return  ServerResponse.createServerResponseByFail("图片为空");
        }

    }

    @Override
    public int insert(CategoryPic categoryPic) {
        int  a = categoryPicMapper.insert(categoryPic);
        if (a!=0){
            return 1;

        }else{
            return 0;
        }
    }

    public static List<String> getImgSrc(String content){
        int num=0;
        List<String> list = new ArrayList<String>();
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);

                //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    if (num==4){
                        break;
                    }
                    String str_src = m_src.group(3);
                    list.add(str_src);
                    num++;

                }
                //结束匹配<img />标签中的src
                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }
}
