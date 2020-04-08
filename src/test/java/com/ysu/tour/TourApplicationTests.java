package com.ysu.tour;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
				}
				//结束匹配<img />标签中的src
				//匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
				result_img = m_img.find();
			}
		}
		return list;
	}


	}//zheshi zuihou yige
