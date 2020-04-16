package com.ysu.tour.service;


import com.ysu.tour.comment.ServerResponse;

public interface IPlayService {

    ServerResponse selectAll();
    ServerResponse modifyAll();
}
