package com.ysu.tour;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.CategoryMapper;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class TourApplicationTests {

	@Test
	void contextLoads() {
		String content ="<h1>碎碎念</h1><p>这是一场蓄谋已久的旅行。</p><p>作为一只大四老狗，" +
				"曾经信誓旦旦的说，毕业旅行一定要去&nbsp;西藏&nbsp;。</p><p>but，" +
				"</p><p>四年下来一次高原地区都没有去过的我，</p><p>在唐天天的危言耸听下，" +
				"</p><p>对高反产生了极度强烈的恐惧。</p><p>于是，</p><p>决定先来大&nbsp;西北&nbsp;" +
				"探探路。</p><p>因为&nbsp;西北&nbsp;地域辽阔，而我们四个人时间有限。</p>" +
				"<p>毕竟，我们还赶着回学校继续毕业论文以及毕业设计的修改。</p><p>十天，</p>" +
				"<p>已经是我们所能挤出来的最长期限了。</p><p>除去来回路上所花费的时间，剩下六天正好够走一条&nbsp;西北&nbsp;大环线。</p><p>所以经过再三权衡和思量，我们还是选择了包车的方式。</p><p>一开始我是先在淘宝上了解关于包车的信息。</p><p>因为我也是第一次包车，也不太清楚，于是还是选择了按销量最多的来排。</p>" +
				"<p>五月份包轿车六天大环线的价格在4000。</p><p>但很凑巧的是，正好在我们准备出去玩之前，我高中同桌拼车去了一趟&nbsp;西北&nbsp;，她觉得司机很不错，于是推给了我。</p><p>在小陈哥这边包车的价格是4300一辆车，虽然是比淘宝上贵这么300块，但是我们思量再三，还是决定包小陈哥的车。</p><p>起码，是有朋友体验过的，会让人心理上有踏实感。</p><p>因为还带着其他三个舍友出去玩，不仅是我自己，会担心因为自己没做好周全的准备导致大家玩的不愉快。</p>" +
				"<p>所以我关注的比较烦琐细微，问题也很多，小陈哥也都回复的很及时、耐心。</p><p>一直以为是小陈哥带我们，直到出发前两天才知道并不是滴。</p><p>司机是小陈哥的亲哥哥，我们都叫他尕（gǎ）林大哥。</p><p>大哥给我们解释，“尕”在他们当地语言中便是“小”的意思。</p><p>道别之前，大哥还嘱咐我说游记写好了一定要给他看啊。</p><p>那我就勉强在这里夸一下他吧～</p><p>首先呢，大哥人很逗逼，时不时会蹦出一两句很搞笑的话。</p><p>其次，大哥人还是很耐心的，偶尔我们出景区的时候总会比约定的时间晚，" +
				"但他也总是在车里等着我们，也没有催促。</p><p>还有，虽然我们很少让大哥给我们拍照，" +
				"但是他拍照的时候也是很拼了哈哈哈哈。</p><p>最重要的是，大哥车技好呀，像金子这种重度晕车患者，" +
				"在车上也没有吐，偶尔还能嗨起来唱唱歌。</p><h1>西北大环线</h1><p><img src=\"http://127.0.0.1/557603a2-a35e-4753-9fe1-c966f5b59b3a.jpeg\"></p><p><span style=\"background-color: rgb(255, 255, 255); color: rgb(51, 51, 51);\">" +
				"从车窗望出去，连绵的雪山，这是我第一次看到雪山。</span></p><p><img src=\"http://127.0.0.1/8ea1e47b-6702-4597-b854-4d01fd10f4ec.jpeg\"></p><p><span style=\"background-color: rgb(255, 255, 255); color: rgb(51, 51, 51);\">拉脊山的海拔在三千八百米左右，下车时第一次感受到高反，心跳加速。</span></p><p>" +
				"<img src=\"http://127.0.0.1/d96f1f35-4929-41bf-9b7e-e3fc1b8aea7a.jpeg\"></p><p>这个神殿是后来才建的，名为宗喀拉则，门票多少钱我忘了，因为我没进去。</p><p>神殿后方有一个漂亮的经幡，但因为我们穿的帆布鞋，不便踏上雪山，于是就放弃了进去。</p>" +
				"<h1>青海湖</h1><p><img src=\"http://127.0.0.1/8d986b50-df47-4125-b5ef-defa87cb2c9e.jpeg\"></p><p>青海湖&nbsp;是&nbsp;中国&nbsp;最大的湖泊，也是&nbsp;中国&nbsp;最大的咸水湖。</p><p>从远处看，湖水的颜色是深蓝色的，听说天气好的时候，湖光天色便会融为一体。</p>";
	List<String> list=getImgSrc(content);
		for (String li:list) {
			System.out.println(li);
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
	@Autowired
	IUserService userService;

	@Autowired
	CategoryMapper categoryMapper;
	@Test
	void insertUser(){
		try {

//			BufferedReader reader = new BufferedReader(new FileReader("E:\\qq记录\\229354948\\FileRecv\\2.csv"));//换成你的文件名
//
//			reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\qq记录\\229354948\\FileRecv\\2.csv"), "GBK"));//GBK
              reader.readLine();//显示标题行,没有则注释掉
//                 System.out.println(reader.readLine());

			String line = null;
			int num=3;
			while((line=reader.readLine())!=null&&num<=201){
				String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
				String uEmail=item[0]+"@qq.com";
				String uName=item[2];
				String uSex = item[3];
				Integer uGender ;
				if(uSex=="男"){
					uGender=1;
				}else{
					uGender=0;
				}
				Integer uAge=18;
				Integer uStatus=0;
				String uPic="";
				String uPwd = "123456";
				UserInfo userInfo = new UserInfo();
				userInfo.setuAge(uAge);
				userInfo.setuEmail(uEmail);
				userInfo.setuGender(uGender);
				userInfo.setuName(uName);
				userInfo.setuPic(uPic);
				userInfo.setuPwd(uPwd);
				userInfo.setuStatus(uStatus);
				userInfo.setuId(num);
				userService.updateByPrimaryKey(userInfo);
			//	userService.insert(userInfo);
				String last = item[item.length-1];//这就是你要的数据了
				//int value = Integer.parseInt(last);//如果是数值，可以转化为数值
				System.out.println(uName);
				num++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void teststringtoint(){
//		String a="01";
//		String ss= "2020-5-15 16:14:2";
//		String ymd=ss.split(" ")[0];
//		System.out.println("ymd===="+ymd);
//		String day=ymd.split("-")[2];
//		System.out.println("day==="+day);
//		int aa = Integer.parseInt(a);
//		System.out.println("aa==="+aa);
		Calendar cal=Calendar.getInstance();
		int m=cal.get(Calendar.MONTH);
		int d=cal.get(Calendar.DATE);
		System.out.println("m=="+m+" d=="+d);
	}
	@Test
	void updateUPIC(){
		String content = "<ul class=\"artCont cl\">\n" +
				"<li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/81253a12119b41b38b66df26723456ff!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/81253a12119b41b38b66df26723456ff!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/8ef90f84570443f3a7f5e7b650bc9b13!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/8ef90f84570443f3a7f5e7b650bc9b13!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/9989859c4d704b3c9fc4825aeb374099!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/9989859c4d704b3c9fc4825aeb374099!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/70167162aee9458f9376c24439ffe369!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/70167162aee9458f9376c24439ffe369!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/52f234533d924b5c835d2742655eb43e!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/52f234533d924b5c835d2742655eb43e!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/7d7d4e802c1944888ffddbba88e78f79!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/7d7d4e802c1944888ffddbba88e78f79!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/b9ff4ea3aea740f6b8d934cac1917a8d!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/b9ff4ea3aea740f6b8d934cac1917a8d!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/ad83ccb3ff6f49d6a371841a0ba63dee!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/ad83ccb3ff6f49d6a371841a0ba63dee!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/850bf35923b447b98de19ebafe68abca!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/850bf35923b447b98de19ebafe68abca!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/1947cfc640c04bb5ae8a6bb358588f46!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/1947cfc640c04bb5ae8a6bb358588f46!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/1eee190c3df34b7b9a1892e51edc8b3a!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/1eee190c3df34b7b9a1892e51edc8b3a!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/126938c6c2904515876f62198d9fa8c5!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/126938c6c2904515876f62198d9fa8c5!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/7b7eb4c91b3049a4ad458ac06016cc4c!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/7b7eb4c91b3049a4ad458ac06016cc4c!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/3486c5bf1033424d89ca19f738aef9ff!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/3486c5bf1033424d89ca19f738aef9ff!400x400.jpeg\" width=\"200\" height=\"200\"></a></li><li class=\"tx-img\"><a href=\"//img2.woyaogexing.com/2020/05/06/b6260c65a0004228b70963b1a0e70fdf!400x400.jpeg\" class=\"swipebox\"><img class=\"lazy\" src=\"//img2.woyaogexing.com/2020/05/06/b6260c65a0004228b70963b1a0e70fdf!400x400.jpeg\" width=\"200\" height=\"200\"></a></li>                </ul>";
		List <String> list= getImgSrc(content);
		int num=29;
		for (String s:list
				) {
			UserInfo userInfo=new UserInfo();
			userInfo.setuStatus(0);
			userInfo.setuPwd(null);
			userInfo.setuName(null);
			userInfo.setuEmail(null);
			userInfo.setuGender(0);
			userInfo.setuAge(null);
			userInfo.setuId(num);
			String a="ll";
			userInfo.setuPic(a);
			userService.updateById(userInfo);
			num++;
			if (num==39){
				break;
			}

		}

	}

	@Test
	void TestActive(){
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
	}//zheshi zuihou yige
