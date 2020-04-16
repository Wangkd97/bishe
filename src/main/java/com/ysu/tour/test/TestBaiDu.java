package com.ysu.tour.test;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

import java.util.HashMap;

public class TestBaiDu {

    public static final String APP_ID = "19191458";
    public static final String API_KEY = "MfAcXWKgpgLbYIRoxv4VMFlg";
    public static final String SECRET_KEY = "Ios6mQu0tG3AXzGpFqhu2FfUz1eyhMo7";

//    public static void main(String[] args) {
//        //初始化一个AipNlp
//        AipNlp client = new AipNlp(APP_ID,API_KEY,SECRET_KEY);
//        //可选，设置网络参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        //可选：设置网络连接参数
//        //client.setHttpProxy("proxy_host",proxy_port);
//        //client.setSocketProxy("proxy_host",proxy_port);
//
//        //可选：设置log4j日志输出格式，若不设置，则使用默认配置
//        //System.setProperties("aip.log.conf","path/to/your/log4j.proprties");
//
//        //调用接口
//        String text = "百度是一家高科技公司";
//        String title="遇见重庆——红窗绿瓦映江滟，吊脚群楼石板连";
//        String content="“红窗绿瓦映江滟，吊脚群楼石板连。 异域风情韵迷客，洪崖洞古换新天。”重庆在我心里一直是一个很美很迷人的地方，她有着迷人的建筑，建筑中体现了新旧文化的交融，" +
//                "你想看到的文化的碰撞都可以在这里看到。她有着爽辣的性格，重庆人直爽之中带有热情；她有着好吃的美食，我的最爱“火锅”，吃一顿火锅酣畅淋漓，好像可以抵消所有的困难烦恼，" +
//                "反正先吃这一顿下一顿再想。重庆的生活又是松弛有度的，茶馆文化历史悠久，走在期间可以放松心灵。这段时间因为工作压力，精神紧绷了很久，终于等到了假期，和好友订好了票，" +
//                "我们去重庆旅游放松一下。DAY1:机场——洪崖洞——朝天门夜景——降龙爪爪——捞锅坝子\n" +
//                "\n" +
//                "DAY2:川美大学城——抱汁汁花甲——磁器口——签斤小姐串串 \n" +
//                "\n" +
//                "DAY3:铁路四村\u001F——中兴路旧货市场——渝大狮老火锅\n" +
//                "\n" +
//                "DAY4:关岳庙——鹅岭二厂——晓宇火锅——弹子石老街——杨记隆府\n" +
//                "\n" +
//                "机场怎么去市区：\n" +
//                "\n" +
//                "因为我们是坐飞机来的，所以路线是先从机场到市区。\n" +
//                "\n" +
//                "淡季的时候机票价格非常合理，有点开心，又省下一笔钱吃好吃的啦。机场去市区的话推荐【轻轨】和【机场大巴】都比较合适，它们都是可以到达市中心解放碑附近，需要1小时左右的时间。行李比较多的朋友或者全家游的朋友，某滴打车也可以的。\n" +
//                "\n" +
//                "▲不过要注意哦！地铁与大巴末班是10点30。\n" +
//                "\n" +
//                "坐轻轨从江北机场到市区需要大概60分钟时间左右，花费7元。\n" +
//                "\n" +
//                "【到解放碑附近】：\n" +
//                "\n" +
//                "可以坐轻轨3号线转2号线到临江门站下车，或者坐轻轨3号线转1号线到较场口站下车;\n" +
//                "\n" +
//                "【到洪崖洞附近】：\n" +
//                "\n" +
//                "可以坐轻轨3号线转1号线到小什字站下车，当然也可以3号线转6号线到小什字站下车!\n" +
//                "\n" +
//                "温馨小Tips请收好~\n" +
//                "\n" +
//                "1.在3D魔幻城市重庆，打车的时候如果有些站点和地图有出入的话，请打开实时地图软件；如果还发现在市内距离定位不准的时候，那不如问问路人吧，毕竟这可是8楼下去就可以到15楼的城市\n" +
//                "\n" +
//                "2.山路很多，上坡下坎十分辛苦，需要一定的脚力，穿上一双舒适的鞋子，旅行能够更舒服哦。\n" +
//                "\n" +
//                "3.重庆很多地方都是单行道，下车一个站，返程时有可能要到很远的另一个站坐，所以多多注意看车站旁的站牌。\n" +
//                "\n" +
//                "4.重庆的饮食以麻辣为主，不习惯吃辣的多带些降火的药，防止上火。\n" +
//                "\n" +
//                "5.出去前关注一下天气。重庆有时候气候潮湿，洗了衣服第二天不一定会干，所以多带几套轻便的衣服用于换洗。一把小巧的太阳伞下雨的时候就可以帮上忙啦。\n" +
//                "\n洪崖洞的璀璨夜景一直是让人心驰神往的，这边有最具巴渝传统建筑特色的“吊脚楼”，夜幕降临的时候灯光亮起来，明晃晃灯光让人心都随着荡漾。\n" +
//                "\n" +
//                "洪崖洞这边一共有11层，夜晚灯光是从晚上8点开灯，10点熄灯。小伙伴们想要踩点的可以吃了晚饭过来~\n" +
//                "\n" +
//                "这边是老牌的重庆必玩景点了，来拍几张美照我们就去吃饭。";
//        HashMap<String ,Object> hashMap=new HashMap<>();
//        JSONObject res2 = client.keyword(title,content,hashMap);
//        JSONObject res3 =client.simnet("朋友","三五好友",hashMap);
//
//        //JSONObject res = client.lexer(text,null);
//        System.out.println(res3.toString());
//
//
//    }


}
