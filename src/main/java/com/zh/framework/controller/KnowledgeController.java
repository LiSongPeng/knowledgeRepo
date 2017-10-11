package com.zh.framework.controller;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.*;
import com.zh.framework.service.BaseService;
import com.zh.framework.service.KnowledgeRepoService;
import com.zh.framework.service.KnowledgeService;
import com.zh.framework.util.Constant;
import com.zh.framework.util.TypeTester;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/kno")
public class KnowledgeController  extends BaseController<Knowledge>{

    @Autowired
    KnowledgeService knowledgeService;
    private KnowledgeRepoService knowledgeRepoService;

    @Resource(name = "knowledgeRepoService")
    public void setKnowledgeRepoService(KnowledgeRepoService knowledgeRepoService) {
        this.knowledgeRepoService = knowledgeRepoService;
    }

    /**k
     * 分页查询
     *
     * @param page 请求的页码
     * @param rows 数据集
     *
     */
    @RequestMapping("/selectPage.form")
    @ResponseBody
    public PageBean selectPage(@RequestParam("sord")String sord,@RequestParam("sidx")String sidx,@RequestParam(value="page")int page,@RequestParam(value="rows")int rows,String kTitle){
        System.out.println(kTitle);
        PageBean pageBean=new PageBean();
        pageBean.setSidx(sidx);
        pageBean.setSord(sord);
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);


        if (sidx != null && !"".equals(sidx) ){
            System.out.println("排序");
            pageBean=knowledgeService.queryKnowledgeOrder(pageBean);
        }else {
            System.out.println("未排序");
            pageBean=knowledgeService.queryAllKnowledge(pageBean);

        }



        return pageBean;
    }

    @RequestMapping("/search.form")
    @ResponseBody
    public PageBean search(@RequestParam("sord")String sord,@RequestParam("sidx")String sidx,String status,@RequestParam(value="page")int page,@RequestParam(value="rows")int rows,String kTitle){
        System.out.println(kTitle);



        PageBean pageBean=new PageBean();
        pageBean.setSidx(sidx);
        pageBean.setSord(sord);
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);

        List<Knowledge> list =new ArrayList<Knowledge>();
        if (kTitle!=null){
            kTitle=kTitle.replaceAll("^\\s*","").replaceAll("\\s*$","");

            pageBean=knowledgeService.search("kTitle",kTitle,pageBean);
        }else if(status!=null){
            status=status.replaceAll("^\\s*","").replaceAll("\\s*$","");
            pageBean=knowledgeService.search("status",status,pageBean);
        }else {
            //pageBean=knowledgeService.search("status",kTitle,pageBean);
            pageBean=knowledgeService.queryKnowledgeOrder(pageBean);
        }






        return pageBean;
    }



    /**
     * 条件查询（用于知识审批）
     *
     * @param page 请求的页码
     * @param rows 数据集
     *
     */
    @RequestMapping("/selectPage2.form")

    @ResponseBody
    public PageBean selectPage2(@RequestParam("sord")String sord,@RequestParam("sidx")String sidx,@RequestParam(value="page")int page,@RequestParam(value="rows")int rows){


        PageBean pageBean=new PageBean();
        pageBean.setSidx(sidx);
        pageBean.setSord(sord);
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);


        if (sidx != null && !"".equals(sidx) ){
            System.out.println("排序");
            pageBean=knowledgeService.queryKnowledgeOrder(pageBean);
        }else {
            System.out.println("未排序");
            pageBean=knowledgeService.querySomeKnowledge(pageBean);

        }
        return pageBean;


    }


    /**
     * 添加知识
     *
     * @param kTitle 知识标题
     * @param createUserId 创建人ID
     * @param kAnswer 知识解答
     *
     */
    @RequestMapping("/addKnowledge.form")
    @ResponseBody
    public String add(HttpServletRequest request,@RequestParam(value="kTitle")String kTitle, @RequestParam(value="createUserId")String createUserId,  @RequestParam(value="kAnswer")String kAnswer){

        String aa="0";

        System.out.println(kAnswer);
        System.out.println("*****"+knowledgeService.queryByKtitle(kTitle));
        System.out.println("addKnowledge");
        String cuid=request.getHeader("Current-UserId");
        System.out.println("用户ID:"+cuid);
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
        k.setCreateUserId(cuid);
        k.setCreateTime(date);
        k.setkUserTimeLast(date);

        if(knowledgeService.queryByKtitle(kTitle).size()!=0){
            aa="1";
        }else {knowledgeService.addKnowledge(k);}

        //

        return aa;

    }

    /**
     * 删除知识（删除待审批）
     *
     * @param id 知识ID
     */
    @RequestMapping("/knowledgeDelete.form")
    @ResponseBody
    public void knowledgeDelete(@RequestParam("id") String id){



        try {
            knowledgeRepoService.buildAIndex(knowledgeService.queryKnowledgeById(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        knowledgeService.updateKnowledgeStatus(id,Knowledge.DELETE_WAITING);

    }

    /**
     * 根据id查询知识
     *
     * @param id 知识ID
     */
    @RequestMapping("/queryKnowledgeById.form")
    @ResponseBody
    public Knowledge queryKnowledgeById(@RequestParam("id") String id){

        return knowledgeService.queryKnowledgeById(id);

    }

    /**
     * 编辑更新知识
     *
     * @param kTitle 知识标题
     * @param createUserId 创建人ID
     * @param kAnswer 知识解答
     */
    @RequestMapping("/updateKnowledge.form")
    @ResponseBody
    public void updateKnowledge(@RequestParam(value="id") String id,@RequestParam(value="kTitle")String kTitle, @RequestParam(value="createUserId")String createUserId,  @RequestParam(value="kAnswer")String kAnswer){
        System.out.println(id+kTitle);
        Knowledge k=new Knowledge();
        k.setId(id);
        k.setkTitle(kTitle);
        k.setCreateUserId(createUserId);
        k.setkAnswer(kAnswer);
        knowledgeService.updateKnowledge(id,kTitle,createUserId,kAnswer);
        knowledgeService.updateKnowledgeStatus(id,Knowledge.UPDATE_WAITING);
        try {
            knowledgeRepoService.updateIndex(k);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 知识审批
     *
     * @param id 知识ID
     */
    @RequestMapping("/knowledgeApprova.form")
    @ResponseBody
    public void knowledgeApprova(HttpServletRequest request,@RequestParam("id") String id,@RequestParam("kApprUserId") String kApprUserId,@RequestParam("kApprMemo") String kApprMemo,@RequestParam("button") String button){

        String cuid=request.getHeader("Current-UserId");
        Knowledge k=knowledgeService.queryKnowledgeById(id);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String s=formatter.format(new Date());

        Date date=null;
        try{
            date =  formatter.parse(s);
        }catch(Exception e){
            System.out.println("异常错误");
        }
        System.out.println(id+kApprUserId+kApprMemo);
        knowledgeService.updateAppr(id,cuid,kApprMemo,date);

        UUID uuid  =  UUID.randomUUID();
        String aid = UUID.randomUUID().toString();

        ApprovalRecord approvalRecord=new ApprovalRecord();
        approvalRecord.setId(aid);
        approvalRecord.setaTime(date);
        approvalRecord.setKid(id);
        approvalRecord.setbStatus(k.getkApprStatus());
        approvalRecord.setaStatus(button);
        approvalRecord.setDs(kApprMemo);
        knowledgeService.addAppar(approvalRecord);
        if(button.equals("通过")){

            try {
                knowledgeRepoService.buildAIndex(k);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if(k.getkApprStatus().equals(Knowledge.DELETE_WAITING)){
                knowledgeService.deleteKnowledge(id);
                try {
                    knowledgeRepoService.removeIndex(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(k.getkApprStatus().equals(Knowledge.INSERT_WAITING)){
                knowledgeService.updateKnowledgeStatus(id,Knowledge.APPROVED);
            }else if(k.getkApprStatus().equals(Knowledge.UPDATE_WAITING)){
                knowledgeService.updateKnowledgeStatus(id,Knowledge.APPROVED);
            }

        }else if(button.equals("不通过")){

            knowledgeService.updateKnowledgeStatus(id,Knowledge.UNAPPROVED);

        }








    }

    @RequestMapping(value = "/queryAppar.form")
    @ResponseBody
    public List<ApprovalRecord> queryAppar(@RequestParam("id") String id){

        System.out.println(id);
        return knowledgeService.queryAppar(id);


    }


    @Override
    @RequestMapping(value = "/knowledgeAdd/add.form")
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request){
        return super.add(request);
    }

    @RequestMapping(value = "/knowledgeAdd/check.form")
    @ResponseBody
    public Map<String, Object> check(){
        Map<String,Object> result=new HashMap<>();
        result.put("msg","成功");
        return result;
    }


}

