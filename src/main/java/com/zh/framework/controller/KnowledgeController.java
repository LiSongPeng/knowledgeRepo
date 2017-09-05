package com.zh.framework.controller;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.Response;
import com.zh.framework.service.BaseService;
import com.zh.framework.service.KnowledgeService;
import com.zh.framework.util.Constant;
import com.zh.framework.util.TypeTester;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class KnowledgeController{

    @Autowired
    KnowledgeService knowledgeService;


    @RequestMapping("/selectPage.form")
    @ResponseBody
    public PageBean selectPage(@RequestParam(value="page")int page,@RequestParam(value="rows")int rows){
        PageBean pageBean=new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(8);
        pageBean=knowledgeService.queryAllKnowledge(pageBean);
        return pageBean;
    }

    @RequestMapping("/addKnowledge.form")
    @ResponseBody
    public void add(@RequestParam(value="kTitle")String kTitle, @RequestParam(value="createUserId")String createUserId,  @RequestParam(value="kAnswer")String kAnswer){

        System.out.println("addKnowledge");
        UUID uuid  =  UUID.randomUUID();
        String id = UUID.randomUUID().toString();
        Knowledge k=new Knowledge();
        //System.out.println(createTime);

        //手动输入时间

//        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
//        String s= "2011-07-09 ";
//        Date date=null;
//        try{
//            date =  formatter.parse(s);
//        }catch(Exception e){
//            System.out.println("错误");
//        }

        //获取系统时间

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String s=formatter.format(new Date());

        Date date=null;
        try{
            date =  formatter.parse(s);
        }catch(Exception e){
            System.out.println("异常错误");
        }



        k.setId(id);
        k.setkTitle(kTitle);
        k.setkAnswer(kAnswer);
        k.setCreateUserId(createUserId);
        k.setCreateTime(date);

        knowledgeService.addKnowledge(k);

    }











}

//    /**
//     * 知识列表
//     *
//     * @param tableName 执行查询的表的名称
//     *
//     */
//
//
//    @RequestMapping("/query.form")
//    @ResponseBody
//
//    public List<Knowledge> query(String tableName) {
//
//        List<Knowledge> list = service.query("tb_knowledge");
//
//        System.out.println(list.toString());
//
//        return list;
//    }
//
//    /**
//     * 知识列表（分页）
//     *
//     * @param pageNumber 请求的页码
//     *
//     */
//
//    @GetMapping("/pagedQuery.form")
//    @ResponseBody
//
//    public PageBean pagedQuery(@RequestParam("pageNumber")int pageNumber) {
//
//        PageBean aa=service.pagedQuery("tb_knowledge",pageNumber,2);
//        System.out.println(aa);
//        return aa;
//
//    }
//
//
//    @GetMapping("/page.form")
//    @ResponseBody
//
//    public PageBean page(@RequestParam(value="page")int page,@RequestParam(value="rows")int rows) {
//
//
//        //ServletContext context = ServletActionContext.getServletContext();
//
//        PageBean aa=service.pagedQuery("tb_knowledge",page,rows);
//        System.out.println(aa);
//
//        System.out.println(aa);
//
//        return aa;
//
//    }
//
//
//    /**
//     * 知识删除
//     *
//     * @param tableName 要操作的数据表
//     * @param id 主键ID
//     *
//     */
//
//    @GetMapping("/delete.form")
//    @ResponseBody
//
//    public void delete(String tableName,String id) {
//
//
//        service.delete("tb_knowledge","2");
//
//        System.out.println("delete successful!!");
//
//    }
//    /**
//     * 知识录入
//     *
//     * @param k 录入的数据
//     *
//     */
//
//        @GetMapping("/update.form")
//        @ResponseBody
//        public void updateKnowledge(Knowledge k) {
//            Knowledge cc=new Knowledge();
//            cc.setkTitle("你好");
//            cc.setId("333");
//            knowledgeService.insertKnowledge(cc);
//
//        }
//
//
//}
