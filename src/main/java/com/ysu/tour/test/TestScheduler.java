package com.ysu.tour.test;

import com.ysu.tour.dao.CategoryMapper;
import com.ysu.tour.dao.UserActiveMapper;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.pojo.UserActive;
import com.ysu.tour.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class TestScheduler {
//策略：先取出所有攻略的活跃度，时间。
    //评论数，点赞数，浏览数。
    //进行计算。

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    UserActiveMapper userActiveMapper;
    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨1点
    public void calculate1() {
        //更新用户活跃度
        updateUserActive();

        //获取当前日期
        Calendar cal=Calendar.getInstance();
        int m=cal.get(Calendar.MONTH);
        int d=cal.get(Calendar.DATE)+1;
        List<Category> list = categoryMapper.selectAllToActive();
        for (Category c : list){
            int clicknum=c.getsCliNum(); //3scord
            int commentnum=c.getsComNum(); //5scord
            int looknum =c.getsLookNum();  //1scord

            String time = c.getsTime();
            String ymd= time.split(" ")[0];
            String day= ymd.split("-")[2];
            String month = ymd.split("-")[1];
            String year = ymd.split("-")[0];
            //先将时间转换int。
            //计算出时间差
            int newm=Integer.parseInt(month);
            int newd=Integer.parseInt(day);
            int daynum=0;
            //不会超过一年，一段时间就会清0了。
            if (m-newm>0){
                daynum=30*(m-newd)+d-newd;
            }else{
                daynum=d-newd;
            }
            int value = calculate(clicknum,commentnum,looknum,daynum);
            if (value<0){
                value=0;
                categoryMapper.updateActiveNum(value,c.getsId());
            }else{
                categoryMapper.updateActiveNum(value,c.getsId());
            }

        }


    }

    public int calculate(int clicknum,int commentnum,int looknum,int daynum){

        int Htotal= clicknum*3+looknum*1+commentnum*5;
        double Htime = 100*Math.pow(2,daynum);
        double Activenum =Htotal-Htime;
        if (Activenum<0){
            int zeron =0;
            Activenum=zeron;
            return (int)Activenum;
        }

        return (int)Activenum;
    }

    public void updateUserActive(){
        //先获取用户的点赞数，评论数，浏览数。
        //计算用户的活跃度

        List<UserActive> list =userActiveMapper.selectAll();
        for (UserActive userActive:list){
            int clicknum=userActive.getuClickNum();
            int commentnum=userActive.getuCommentNum();
            int looknum=userActive.getuLookNum();
            int activenum=clicknum*3+commentnum*5+looknum*1;
            userActiveMapper.updateActive(activenum,userActive.getuId());
        }


    }


}
