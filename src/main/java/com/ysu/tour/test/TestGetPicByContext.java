package com.ysu.tour.test;

import com.ysu.tour.dao.UserInfoMapper;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestGetPicByContext {

    @Autowired
    IUserService userService;


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

    public void ss( int num,UserInfo userInfo){
        userInfo.setuId(num);
        userService.updateByPrimaryKey(userInfo);
    }
    public static void main(String[] args) {
        TestGetPicByContext testGetPicByContext= new TestGetPicByContext();
        String content = "<ul class=\"artCont cl\">\n" +
                "<li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/8e2c2d80ac48490db9edfa240408e339!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/8e2c2d80ac48490db9edfa240408e339!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/9fa5bb23bb8542bf9d1ea53904b382db!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/9fa5bb23bb8542bf9d1ea53904b382db!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/25f17534fd6a40138ad3cf84486bba83!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/25f17534fd6a40138ad3cf84486bba83!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/abfdfc0403b44f46bce2dd822144f669!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/abfdfc0403b44f46bce2dd822144f669!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/9237a61d939940308fd18c5f0a8a841b!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/9237a61d939940308fd18c5f0a8a841b!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/f159ab43aaba452fa87e23a931a8725b!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/f159ab43aaba452fa87e23a931a8725b!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/31a87c3a0a204ee896d3ca2c18206a7e!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/31a87c3a0a204ee896d3ca2c18206a7e!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/7034fbd8b03347c98801b04ab775cc5c!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/7034fbd8b03347c98801b04ab775cc5c!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/23957e9b2b4b403dace5a44a2cc08a7c!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/23957e9b2b4b403dace5a44a2cc08a7c!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/cc445fba2f5140abb175e6ebf7745211!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/cc445fba2f5140abb175e6ebf7745211!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/8abe834343104ef59125eebd61c0c02b!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/8abe834343104ef59125eebd61c0c02b!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/5bb609c1575346b7a42367fc8143605f!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/5bb609c1575346b7a42367fc8143605f!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/ecd42dc0ab4742219cf418ea148dbf16!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/ecd42dc0ab4742219cf418ea148dbf16!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/aa457877ee0145a38a37351c8dae796d!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/aa457877ee0145a38a37351c8dae796d!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/d96bec4573404cf58f46c145123ca80a!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/d96bec4573404cf58f46c145123ca80a!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/46c0a3220d944addbc3f5765ac9f114c!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/46c0a3220d944addbc3f5765ac9f114c!400x400.jpeg\" width=\"200\" height=\"200\"></a></li>                </ul> " ;
        List <String> list= getImgSrc(content);
        int num=6;
        for (String s:list
             ) {
            UserInfo userInfo=new UserInfo();
            userInfo.setuId(num);
            userInfo.setuPic(s);
           testGetPicByContext.ss(num,userInfo);
           num++;
        }

    }
}
