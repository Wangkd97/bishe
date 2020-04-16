package com.ysu.tour.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.ysu.tour.dao.PeopleMapper;
import com.ysu.tour.dao.PlayMapper;
import com.ysu.tour.dao.SeasonMapper;
import com.ysu.tour.pojo.People;
import com.ysu.tour.pojo.Play;
import com.ysu.tour.pojo.Season;
import com.ysu.tour.service.impl.StrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

public class TestHanLP {
    public TestHanLP(){}
    @Autowired
    SeasonMapper seasonMapper;
    @Autowired
    PeopleMapper peopleMapper;
    @Autowired
    PlayMapper playMapper;
    @Autowired
    StrategyImpl strategy;
//    public static void main(String[] args) {
//        String content="“红窗绿瓦映江滟，吊脚群楼石板连。 异域风情韵迷客，洪崖洞古换新天。”重庆在我心里一直是一个很美很迷人的地方，她有着迷人的建筑，建筑中体现了新旧文化的交融，" +
//                "你想看到的文化的碰撞都可以在这里看到。我和我的朋友们，她有着爽辣的性格，重庆人直爽之中带有热情；她有着好吃的美食，我的最爱“火锅”，吃一顿火锅酣畅淋漓，好像可以抵消所有的困难烦恼，" +
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
//
//        String content2 ="长安，是唐朝人心中的美梦；\n" +
////                "\n" +
////                "西安，是现代人心中的长安梦！八百里秦川尘土飞扬，\n" +
////                "\n" +
////                "三千万老陕齐吼秦腔，\n" +
////                "\n" +
////                "自从秦朝丰京镐京建都开始，\n" +
////                "\n" +
////                "十三朝古都的历史文化底蕴，\n" +
////                "\n" +
////                "西安一直都是一座炙手可热的网红都市，\n" +
////                "\n" +
////                "加持了摔碗酒、不倒翁小姐姐的西安，\n" +
////                "\n" +
////                "又一次在网络上爆红，让西安成为无数人竞相打卡的古城。从苏州达到西安入住的酒店，差不多2点钟左右。在酒店稍微休息后" +
////                "，就前往西安城墙景区，区欣赏刚刚开始亮灯的西安城墙新春灯会。安定门址原为隋唐长安皇城西面中门“顺义门”，唐末韩建改筑新城时保留。明洪武七年至十一年（1374年至1378年），扩建西安府城，此门沿用为西门，易名“安定门”，今安定门存瓮城、箭楼、正楼及二重门洞。\n" +
////                "\n" +
//                "城门两侧各开辟三个券洞，供车辆行人通行。文昌门的城墙上建有魁星楼。文昌门里是碑林，中山门（小东门）里面就是永兴坊，网红、摔碗酒。中山门里的街道叫东新街，也是美食街！";
//
//       String content3="这个十月一黄金周，我和我的朋友们一起去了秦皇岛。我们一起吃了海鲜，朋友去了北戴河，朋友去了山海关，还去了乐岛，乐岛真的太好玩了。";
//        List<String> keywordList = HanLP.extractKeyword(content3, 5);
////        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
////        List<Term> termList = segment.seg(content);
////        System.out.println(termList);
//
//        System.out.println(keywordList);
//    }
    List<Season> seasonlist = seasonMapper.selectAll();
    List<Play> playlist = playMapper.selectAll();
    List<People> peoplelist = peopleMapper.selectAll();
    public  void ss(){
        List<String >seasonstringlist = new ArrayList<>();
        List <String >playstringlist = new ArrayList<>();
        List<String>peoplestringlist=new ArrayList<>();
        for (Season s:seasonlist) {
            seasonstringlist.add(s.getSeasonName());
            CustomDictionary.add(s.getSeasonName());
        }
        for (Play p :playlist){
            playstringlist.add(p.getPlayName());
            CustomDictionary.add(p.getPlayName());
        }
        for (People p:peoplelist){
            peoplestringlist.add(p.getPeoName());
            CustomDictionary.add(p.getPeoName());
        }
        // 动态增加
        CustomDictionary.add("攻城狮");
        CustomDictionary.add("十月一");
        // 强行插入
        CustomDictionary.insert("白富美", "nz 1024");
        // 删除词语（注释掉试试）
//        CustomDictionary.remove("攻城狮");
        //  System.out.println(CustomDictionary.add("单身狗", "nz 1024 n 1"));
        // System.out.println(CustomDictionary.get("单身狗"));

        //String text = "攻城狮逆袭单身狗，迎娶白富美，走上人生巅峰";  // 怎么可能噗哈哈！
        String text="这个十月一黄金周清明节，我和我的朋友们第一次一起去了秦皇岛。我们一起吃了海鲜，朋友去了北戴河，朋友去了山海关，还去了乐岛，乐岛真的太好玩了。";
        // AhoCorasickDoubleArrayTrie自动机扫描文本中出现的自定义词语


        // 自定义词典在所有分词器中都有效
        System.out.println(HanLP.segment(text));
        List<String>list = new ArrayList<>();
        for (Term a:HanLP.segment(text)) {
            list.add(a.word);
            System.out.println(a.word);
        }
        System.out.println(receiveCollectionList(list,seasonstringlist));
        System.out.println(receiveCollectionList(list,playstringlist));
        System.out.println(receiveCollectionList(list,peoplestringlist));
    }
    public static void main(String[] args)
    {


    }

    public static List<String> receiveCollectionList(List<String> firstArrayList, List<String> secondArrayList) {
        List<String> resultList = new ArrayList<String>();
        LinkedList<String> result = new LinkedList<String>(firstArrayList);// 大集合用linkedlist
        HashSet<String> othHash = new HashSet<String>(secondArrayList);// 小集合用hashset
        Iterator<String> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
        while(iter.hasNext()) {
            if(!othHash.contains(iter.next())) {
                iter.remove();
            }
        }
        resultList = new ArrayList<String>(result);
        return resultList;
    }



}
