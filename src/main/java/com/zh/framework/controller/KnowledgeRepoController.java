package com.zh.framework.controller;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.KnowledgeIndex;
import com.zh.framework.entity.Response;
import com.zh.framework.service.KnowledgeRepoService;
import com.zh.framework.util.Constant;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Response<PageInfo> getKnowledgeList(@RequestParam("orderBy") String orderBy, @RequestParam("page") int page) {
        PageInfo<Knowledge> pageInfo = knowledgeRepoService.listDisplay(orderBy, page, Constant.PAGE_SIZE);
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

    @GetMapping("/searchIndex.form")
    @ResponseBody
    public Response<List> searchIndex(@RequestParam("keyWord") String keyWord, int page) {
        Response<List> response = new Response<>();
        response.setFlag(Response.SUCCESS);
        response.setMessage("SUCCESS");
        List<KnowledgeIndex> data;
        try {
            data = knowledgeRepoService.searchIndex(keyWord, page, Constant.PAGE_SIZE);
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
}
