package com.gzstarry.modules.controller.counts;

import com.gzstarry.common.utils.R;
import com.gzstarry.entity.cms.ArticleEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.cms.ArticleService;
import com.gzstarry.service.sys.SysDictService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 统计分析
 *
 * @author MakerLin [makerlin96@gmail.com]
 */
@Api(tags="统计分析")
@RestController
@RequestMapping("/counts")
public class CountsController extends AbstractController {
    @Autowired
    ArticleService articleService;
    @Autowired
    SysDictService sysDictService;


    @ApiOperation("文章类型统计")
    @GetMapping("/articleCounts")
    public R getArticleCounts(String createTime) {
        Map<String, Object> maps = new HashMap<>(2);
        if (StringUtils.isNotEmpty(createTime)) {
            String[] strArray = createTime.split(" - ");
            maps.put("startTime", strArray[0]);
            maps.put("endTime", strArray[1]);
        }
        List<ArticleEntity> list = articleService.selectCateTypeByCount(maps);
        JSONArray array = new JSONArray();
        for (ArticleEntity article : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("分类", article.getCateName());
            jsonObject.put("数量", article.getCateCount());
            array.add(jsonObject);
        }
        return R.ok().put("datas", array);
    }

}

