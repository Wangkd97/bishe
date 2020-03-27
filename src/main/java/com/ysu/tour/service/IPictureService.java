package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

    ServerResponse uploadPic(MultipartFile file, String path);
}
