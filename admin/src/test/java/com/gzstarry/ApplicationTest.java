package com.gzstarry;

import com.gzstarry.common.utils.RedisUtils;
import com.gzstarry.dao.sys.SysUserMapper;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.service.cms.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private RedisUtils redisUtils;


	@Test
	public void contextLoads() {
	}

	@Test
	public void userList(){
		List<SysUserEntity> list = sysUserMapper.selectList(null);
		list.forEach(System.out::println);
	}

	@Test
	public void RedisTests(){
		try {
			List<SysUserEntity> sysUserEntity = sysUserMapper.selectList(null);
			redisUtils.set("sysUserEntity", sysUserEntity);
			Object object = redisUtils.get("sysUserEntity");
			logger.info("用户信息={}",object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*@Test
	public void getJianShuArticle(){
		//请求列表页
		String listContent = HttpUtil.get("https://www.jianshu.com/c/61314ad84456?order_by=added_at&page=1&per_page=20");
		//使用正则获取所有链接
		List<String> links = ReUtil.findAll("<a class=\"title\" target=\"_blank\" href=\"(.*?)\">(.*?)</a>", listContent, 1);
		for (String link : links) {
			//根据链接获取文章详情
			String detail = HttpUtil.get("https://www.jianshu.com/"+link);
			//获取文章标题
			String title = ReUtil.getGroup1("<h1 class=\"title\">(.*?)</h1>", detail);
			//获取文章详情
			String content = ReUtil.getGroup1("<div class=\"show-content-free\">(.*?)</div>", detail);
			ArticleEntity cms = new ArticleEntity();
			cms.setTitle(title);
			cms.setCateId(64);
			cms.setContent(content);
			cms.setStatus(1);
			cms.setCreateTime(new Date());
			cms.setUpdateTime(new Date());
			cms.setClosed(1);
			articleService.insert(cms);
			Console.log("文章写入成功，ID："+cms.getId());
			//打印标题
			//Console.log(title);
			//Console.log(content);
		}
	}*/
}
