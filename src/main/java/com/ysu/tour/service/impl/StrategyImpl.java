package com.ysu.tour.service.impl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.*;
import com.ysu.tour.pojo.*;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.service.IStrategyService;
import com.ysu.tour.viewobject.StrategyVO;
import com.ysu.tour.viewobject.TagsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.*;

@Service
public class StrategyImpl implements IStrategyService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SeasonMapper seasonMapper;
    @Autowired
    PeopleMapper peopleMapper;
    @Autowired
    PlayMapper playMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    CategoryPicMapper categoryPicMapper;
    @Override
    public ServerResponse selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<StrategyVO> strategyVOS= new ArrayList<>();
        return strategyVo(categories,strategyVOS);

    }

    @Override
    public int insert(Category category) {
        if (category.getsName()==null||category.getsName().equals("")){
            return 0;
        }else {
            categoryMapper.insert(category);
            int value =category.getsId();

           if (value>=0){
               return value;

           }else{
               return 0;
           }
        }


    }

    @Override
    public ServerResponse selectByPrimaryKey(Integer id) {
       Category category = categoryMapper.selectByPrimaryKey(id);
       UserInfo userInfo=userInfoMapper.selectByPrimaryKey(category.getsMasterId());
       StrategyVO strategyVO=new StrategyVO();
        int season_id = category.getsSeasonId();
        Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
        People people=peopleMapper.selectByPrimaryKey(category.getsPeopleId()); //获取该攻略的人物
        List<Play> list = playMapper.selectById(category.getsId());
        List<String> stringList= new ArrayList<>();
        if(list!=null){
            for (Play p:list) {
                stringList.add(p.getPlayName());
            }
        }
        strategyVO.setsMasterId(category.getsMasterId());
        strategyVO.setsMasterUrl(userInfo.getuPic());
        strategyVO.setsMasterName(userInfo.getuName());
        strategyVO.setsId(category.getsId());
        strategyVO.setsCover(category.getsCover());
        strategyVO.setsText(category.getsText());
        strategyVO.setsPlay(stringList);
        strategyVO.setsPeople(people.getPeoName());
        strategyVO.setsCliNum(category.getsCliNum());
        strategyVO.setsComNum(category.getsComNum());
        strategyVO.setsDay(category.getsDay());
        strategyVO.setsGotime(category.getsGotime());
        strategyVO.setsName(category.getsName());
        strategyVO.setsLookNum(category.getsLookNum());
        strategyVO.setsPay(category.getsPay());
        if (season!=null)
            strategyVO.setsSeason(season.getSeasonName());
        strategyVO.setsTime(category.getsTime());

        if (category!=null){
            return ServerResponse.createServerResponseBySucess(strategyVO);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"查询失败");
        }
    }

    @Override
    public ServerResponse fenyeselect(Integer start) {
        List <Category>categories =  categoryMapper.fenyeselect(start);
        List<StrategyVO> strategyVOS=new ArrayList<>();
        if (categories!=null){
            for (Category cate:categories) {
                int season_id = cate.getsSeasonId();
                Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
                People people=peopleMapper.selectByPrimaryKey(cate.getsPeopleId()); //获取该攻略的人物
                List<Play> list = playMapper.selectById(cate.getsId());
                List<String> stringList= new ArrayList<>();
                List<String> piclist=new ArrayList<>();
                if(list!=null)
                for (Play p:list) {
                    stringList.add(p.getPlayName());
                }
                UserInfo userInfo=userInfoMapper.selectByPrimaryKey(cate.getsMasterId());
                List<CategoryPic> categoryPics=categoryPicMapper.selectBySId(cate.getsId());
                if (categoryPics.size()==0){
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                }else if(categoryPics.size()==1){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());

                }else if(categoryPics.size()==2){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                }else if(categoryPics.size()==3){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(categoryPics.get(2).getSpicUrl());
                    piclist.add(cate.getsCover());
                }else {
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(categoryPics.get(2).getSpicUrl());
                    piclist.add(categoryPics.get(3).getSpicUrl());
                }
                //声明strategyvo
                StrategyVO strategyVO= new StrategyVO();
                strategyVO.setsPics(piclist);
                strategyVO.setsMasterId(cate.getsMasterId());
                strategyVO.setsMasterUrl(userInfo.getuPic());
                strategyVO.setsMasterName(userInfo.getuName());
                strategyVO.setsId(cate.getsId());
                strategyVO.setsCover(cate.getsCover());
                strategyVO.setsText(cate.getsText());
                strategyVO.setsPlay(stringList);
                strategyVO.setsPeople(people.getPeoName());
                strategyVO.setsCliNum(cate.getsCliNum());
                strategyVO.setsComNum(cate.getsComNum());
                strategyVO.setsDay(cate.getsDay());
                strategyVO.setsGotime(cate.getsGotime());
                strategyVO.setsName(cate.getsName());
                strategyVO.setsLookNum(cate.getsLookNum());
                strategyVO.setsPay(cate.getsPay());
                if (season!=null)
                strategyVO.setsSeason(season.getSeasonName());
                strategyVO.setsTime(cate.getsTime());
                strategyVOS.add(strategyVO);
            }
            return ServerResponse.createServerResponseBySucess(strategyVOS);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无攻略");
        }

    }

    @Override
    public ServerResponse selectcountall() {
       int size = categoryMapper.selectcountall();
        return ServerResponse.createServerResponseBySucess(size);
    }

    @Override
    public ServerResponse recommTags(String text) {
        //在数据库首先获取季节，
        //获取玩法
        //获取人物。
        //调用hanlp。在词典中添加季节，玩法，人物。
        //比较
        //返回。
        List<TagsVo> value=fenci(text);
        return ServerResponse.createServerResponseBySucess(value);
    }

    @Override
    public ServerResponse fenleiselect(String month, String day, String pay, String people, String play,int start) {
        List<Category> categories= categoryMapper.fenleiselect(month,day,pay,people,play,start);
        List<StrategyVO> strategyVOS=new ArrayList<>();
        if (categories!=null){
            for (Category cate:categories) {
                int season_id = cate.getsSeasonId();
                Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
                People s_people=peopleMapper.selectByPrimaryKey(cate.getsPeopleId()); //获取该攻略的人物
                List<Play> list = playMapper.selectById(cate.getsId());
                List<String> stringList= new ArrayList<>();
                List<String> piclist=new ArrayList<>();
                if(list!=null)
                    for (Play p:list) {
                        stringList.add(p.getPlayName());
                    }
                UserInfo userInfo=userInfoMapper.selectByPrimaryKey(cate.getsMasterId());
                List<CategoryPic> categoryPics=categoryPicMapper.selectBySId(cate.getsId());
                if (categoryPics.size()==0){
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                }else if(categoryPics.size()==1){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());

                }else if(categoryPics.size()==2){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                }else if(categoryPics.size()==3){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(categoryPics.get(2).getSpicUrl());
                    piclist.add(cate.getsCover());
                }else {
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(categoryPics.get(2).getSpicUrl());
                    piclist.add(categoryPics.get(3).getSpicUrl());
                }
                //声明strategyvo
                StrategyVO strategyVO= new StrategyVO();
                strategyVO.setsPics(piclist);
                strategyVO.setsMasterId(cate.getsMasterId());
                strategyVO.setsMasterUrl(userInfo.getuPic());
                strategyVO.setsMasterName(userInfo.getuName());
                strategyVO.setsId(cate.getsId());
                strategyVO.setsCover(cate.getsCover());
                strategyVO.setsText(cate.getsText());
                strategyVO.setsPlay(stringList);
                strategyVO.setsPeople(s_people.getPeoName());
                strategyVO.setsCliNum(cate.getsCliNum());
                strategyVO.setsComNum(cate.getsComNum());
                strategyVO.setsDay(cate.getsDay());
                strategyVO.setsGotime(cate.getsGotime());
                strategyVO.setsName(cate.getsName());
                strategyVO.setsLookNum(cate.getsLookNum());
                strategyVO.setsPay(cate.getsPay());
                if (season!=null)
                    strategyVO.setsSeason(season.getSeasonName());
                strategyVO.setsTime(cate.getsTime());
                strategyVOS.add(strategyVO);
            }
            return ServerResponse.createServerResponseBySucess(strategyVOS);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无攻略");
        }

    }

    @Override
    public ServerResponse fuzzyselect(String name,int start) {
        String addname= "%"+name+"%";
        List <Category>categories =  categoryMapper.fuzzyselect(addname,start);
        List<StrategyVO> strategyVOS=new ArrayList<>();
        if (categories!=null){
            for (Category cate:categories) {
                int season_id = cate.getsSeasonId();
                Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
                People people=peopleMapper.selectByPrimaryKey(cate.getsPeopleId()); //获取该攻略的人物
                List<Play> list = playMapper.selectById(cate.getsId());
                List<String> stringList= new ArrayList<>();
                List<String> piclist=new ArrayList<>();
                if(list!=null)
                    for (Play p:list) {
                        stringList.add(p.getPlayName());
                    }
                UserInfo userInfo=userInfoMapper.selectByPrimaryKey(cate.getsMasterId());
                List<CategoryPic> categoryPics=categoryPicMapper.selectBySId(cate.getsId());
                if (categoryPics.size()==0){
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                }else if(categoryPics.size()==1){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());

                }else if(categoryPics.size()==2){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(cate.getsCover());
                    piclist.add(cate.getsCover());
                }else if(categoryPics.size()==3){
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(categoryPics.get(2).getSpicUrl());
                    piclist.add(cate.getsCover());
                }else {
                    piclist.add(categoryPics.get(0).getSpicUrl());
                    piclist.add(categoryPics.get(1).getSpicUrl());
                    piclist.add(categoryPics.get(2).getSpicUrl());
                    piclist.add(categoryPics.get(3).getSpicUrl());
                }
                //声明strategyvo
                StrategyVO strategyVO= new StrategyVO();
                strategyVO.setsPics(piclist);
                strategyVO.setsMasterId(cate.getsMasterId());
                strategyVO.setsMasterUrl(userInfo.getuPic());
                strategyVO.setsMasterName(userInfo.getuName());
                strategyVO.setsId(cate.getsId());
                strategyVO.setsCover(cate.getsCover());
                strategyVO.setsText(cate.getsText());
                strategyVO.setsPlay(stringList);
                strategyVO.setsPeople(people.getPeoName());
                strategyVO.setsCliNum(cate.getsCliNum());
                strategyVO.setsComNum(cate.getsComNum());
                strategyVO.setsDay(cate.getsDay());
                strategyVO.setsGotime(cate.getsGotime());
                strategyVO.setsName(cate.getsName());
                strategyVO.setsLookNum(cate.getsLookNum());
                strategyVO.setsPay(cate.getsPay());
                if (season!=null)
                    strategyVO.setsSeason(season.getSeasonName());
                strategyVO.setsTime(cate.getsTime());
                strategyVOS.add(strategyVO);
            }
            return ServerResponse.createServerResponseBySucess(strategyVOS);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无攻略");
        }

    }

    @Override
    public ServerResponse countfuzzyall(String name) {
        String addname= "%"+name+"%";
        int num = categoryMapper.countfuzzyall(addname);
        if (num>0){
            return ServerResponse.createServerResponseBySucess(num);
        }else {
            return ServerResponse.createServerResponseByFail("失败");
        }
    }

    @Override
    public ServerResponse countfenleiall(String month, String day, String pay, String people, String play) {
        int num = categoryMapper.countfenleiall(month, day, pay, people, play);
        if (num>0){
            return ServerResponse.createServerResponseBySucess(num);
        }else {
            return ServerResponse.createServerResponseByFail("失败");
        }
    }

    @Override
    public int insertPlay(int strategyId, int playId) {
        int value= categoryMapper.insertPlay(strategyId,playId);
        if (value>0){
            return 1;
        }else{
            return 0;
        }
    }


    public ServerResponse strategyVo( List<Category> categories,List<StrategyVO> strategyVOS){
        if (categories!=null){
            for (Category cate:categories) {
                int season_id = cate.getsSeasonId();
                Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
                People people=peopleMapper.selectByPrimaryKey(cate.getsPeopleId()); //获取该攻略的人物
                List<Play> list = playMapper.selectById(cate.getsId());
                List<String> stringList= new ArrayList<>();
                for (Play p:list) {
                    stringList.add(p.getPlayName());
                }
                //声明strategyvo
                StrategyVO strategyVO= new StrategyVO();
                strategyVO.setsPlay(stringList);
                strategyVO.setsPeople(people.getPeoName());
                strategyVO.setsCliNum(cate.getsCliNum());
                strategyVO.setsComNum(cate.getsComNum());
                strategyVO.setsDay(cate.getsDay());
                strategyVO.setsGotime(cate.getsGotime());
                System.out.println(strategyVO.getsGotime()+"=========");
                System.out.println(cate.getsGotime()+"**********");
                strategyVO.setsName(cate.getsName());
                strategyVO.setsLookNum(cate.getsLookNum());
                strategyVO.setsPay(cate.getsPay());
                strategyVO.setsSeason(season.getSeasonName());
                strategyVO.setsTime(cate.getsTime());
                System.out.println(cate.getsTime()+"=====time=====");
                strategyVOS.add(strategyVO);
            }
            return ServerResponse.createServerResponseBySucess(strategyVOS);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无攻略");
        }
    }

    public List<TagsVo> fenci(String text) {
        List<Season> seasonlist = seasonMapper.selectAll();
        List<Play> playlist = playMapper.selectAll();
        List<People> peoplelist = peopleMapper.selectAll();
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

        // 强行插入
       // CustomDictionary.insert("白富美", "nz 1024");
        // 自定义词典在所有分词器中都有效
        System.out.println(HanLP.segment(text));
        List<String >list=new ArrayList<>();
        for (Term a : HanLP.segment(text)) {
            list.add(a.word);
        }
        //调用交集方法求交集。然后从交集中找到id，传回去id,，和name
        //返回数据，season,play,people
        List<TagsVo> tagsVoList= new ArrayList<>();
        List<String> reciveseasonlist= receiveCollectionList(list,seasonstringlist);
        if (reciveseasonlist.size()!=0){
            for (Season season:seasonlist){
                if (reciveseasonlist.get(0).equals(season.getSeasonName())){
                    TagsVo tagsVo=new TagsVo();
                    tagsVo.setType("");
                    tagsVo.setLabel(reciveseasonlist.get(0));
                    tagsVo.setId(season.getSeasonId());
                    tagsVo.setColor("");
                    tagsVoList.add(tagsVo);
                }
            }
        } else{
            TagsVo tagsVo=new TagsVo();
            tagsVo.setType("");
            tagsVo.setLabel(seasonlist.get(0).getSeasonName());
            tagsVo.setId(seasonlist.get(0).getSeasonId());
            tagsVo.setColor("");
            tagsVoList.add(tagsVo);
        }
        List<String> reciveplaylist = receiveCollectionList(list,playstringlist);
        if (reciveplaylist.size()!=0){
            for (Play play:playlist){
                if (reciveplaylist.get(0).equals(play.getPlayName())){
                    TagsVo tagsVo=new TagsVo();
                    tagsVo.setType("");
                    tagsVo.setLabel(reciveplaylist.get(0));
                    tagsVo.setId(play.getPlayId());
                    tagsVo.setColor("");
                    tagsVoList.add(tagsVo);
                }
            }
        } else{
            TagsVo tagsVo=new TagsVo();
            tagsVo.setType("");
            tagsVo.setLabel(playlist.get(0).getPlayName());
            tagsVo.setId(playlist.get(0).getPlayId());
            tagsVo.setColor("");
            tagsVoList.add(tagsVo);
        }
        List<String> recivepeoplelist = receiveCollectionList(list,peoplestringlist);
        if (recivepeoplelist.size()!=0){
            for (People people:peoplelist){
                if (recivepeoplelist.get(0).equals(people.getPeoName())){
                    TagsVo tagsVo=new TagsVo();
                    tagsVo.setType("");
                    tagsVo.setLabel(recivepeoplelist.get(0));
                    tagsVo.setId(people.getPeoId());
                    tagsVo.setColor("");
                    tagsVoList.add(tagsVo);
                }
            }
        } else{
            TagsVo tagsVo=new TagsVo();
            tagsVo.setType("");
            tagsVo.setLabel(peoplelist.get(0).getPeoName());
            tagsVo.setId(peoplelist.get(0).getPeoId());
            tagsVo.setColor("");
            tagsVoList.add(tagsVo);
        }
        return tagsVoList;
    }

    //这个是两个list求交集
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



}//end
