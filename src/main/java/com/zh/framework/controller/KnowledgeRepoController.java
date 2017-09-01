package com.zh.framework.controller;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.KnowledgeIndex;
import com.zh.framework.entity.Response;
import com.zh.framework.service.KnowledgeRepoService;
import com.zh.framework.util.Constant;
import com.zh.framework.util.TypeTester;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by lihuibo on 17-8-29 下午1:49
 */
@Controller
@Scope("prototype")
@RequestMapping("/repo")
public class KnowledgeRepoController {
    private KnowledgeRepoService knowledgeRepoService;

    @Resource(name = "knowledgeRepoService")
    public void setKnowledgeRepoService(KnowledgeRepoService knowledgeRepoService) {
        this.knowledgeRepoService = knowledgeRepoService;
    }

    @GetMapping("/getKnowledgeList.form")
    @ResponseBody
    public Response<PageInfo> getKnowledgeList(@RequestParam(value = "orderBy", required = false) String orderBy, @RequestParam(value = "page", required = false) int page, @RequestParam(value = "order", required = false) int order) {
        if (TypeTester.isEmpty(orderBy))
            orderBy = null;
        PageInfo<Knowledge> pageInfo = knowledgeRepoService.listDisplay(orderBy, page, Constant.PAGE_SIZE, order);//order 1 升序 其他值 降序
        Response<PageInfo> response = new Response<>();
        response.setFlag(Response.SUCCESS);
        response.setMessage("SUCCESS");
        response.setData(pageInfo);
        return response;
    }

    @GetMapping("/getKnowledgeDetail.form")
    @ResponseBody
    public Response<Knowledge> getKnowledgeDetail(@RequestParam("id") String id) {
        Response response = new Response();
        response.setFlag(Response.SUCCESS);
        response.setMessage("SUCCESS");
        Knowledge k;
        try {
            k = knowledgeRepoService.viewKnowledgeDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
            response.setFlag(Response.FAIL);
            response.setMessage("FAIL");
            return response;
        }
        response.setData(k);
        return response;
    }

    @GetMapping("/getInputHint.form")
    @ResponseBody
    public Response<List> getInputHint(@RequestParam("keyWord") String keyWord) {
        Response<List> response = new Response<>();
        response.setFlag(Response.SUCCESS);
        response.setMessage("SUCCESS");
        List<String> hints;
        try {
            hints = knowledgeRepoService.inputHint(keyWord);
        } catch (Exception e) {
            e.printStackTrace();
            response.setFlag(Response.FAIL);
            response.setMessage("FAIL");
            return response;
        }
        response.setData(hints);
        return response;
    }

    @GetMapping("/searchIndexNoPage.form")
    @ResponseBody
    public Response<List> searchIndexNoPage(@RequestParam("keyWord") String keyWord) {
        Response<List> response = new Response<>();
        response.setFlag(Response.SUCCESS);
        response.setMessage("SUCCESS");
        List<KnowledgeIndex> data;
        try {
            data = knowledgeRepoService.searchIndexNoPage(keyWord);
        } catch (Exception e) {
            e.printStackTrace();
            response.setFlag(Response.FAIL);
            response.setMessage("FAIL");
            return response;
        }
        for (KnowledgeIndex index : data) {
            System.out.println(index.getkTitle());
            System.out.println(index.getkAnswer());
        }
        response.setData(data);
        return response;
    }

    @GetMapping("/searchIndex.form")
    @ResponseBody
    public Response<PageInfo> searchIndex(@RequestParam("keyWord") String keyWord, @RequestParam("page") int page, @RequestParam("orderBy") int orderBy, @RequestParam("order") int order) {
        Response<PageInfo> response = new Response<>();
        response.setFlag(Response.SUCCESS);
        response.setMessage("SUCCESS");
        PageInfo<KnowledgeIndex> pageInfo;
        try {
            pageInfo = knowledgeRepoService.searchIndex(keyWord, page, Constant.PAGE_SIZE, orderBy, order);
        } catch (Exception e) {
            e.printStackTrace();
            response.setFlag(Response.FAIL);
            response.setMessage("FAIL");
            return response;
        }
        response.setData(pageInfo);
        return response;
    }
}
