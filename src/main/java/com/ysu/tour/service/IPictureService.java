package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.CategoryPic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPictureService {

    ServerResponse uploadPic(MultipartFile file, String path);
    List<String> getListPic(String content);
    ServerResponse selectBySId(int sId);
    int insert(CategoryPic categoryPic);
}
