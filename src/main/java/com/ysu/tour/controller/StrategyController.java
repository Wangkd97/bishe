package com.ysu.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller   //让返回值为字符串
@RequestMapping("/manage/strategy/send/")
@CrossOrigin
public class StrategyController {

    @RequestMapping(value = "show/{id}",method = RequestMethod.GET)
    public  String  update(@PathVariable("id") Integer categoryId,
                           HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println(categoryId+"=====categoryId========");
        try{
            response.sendRedirect("http://127.0.0.1:8080/#/StrategyShow?id="+categoryId);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "forward:/StrategyShow?id="+categoryId;
    }

}
