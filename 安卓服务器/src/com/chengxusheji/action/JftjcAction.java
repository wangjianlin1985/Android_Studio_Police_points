package com.chengxusheji.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionContext;
import com.chengxusheji.dao.JftjcDAO;
import com.chengxusheji.domain.Jftjc;
import com.chengxusheji.utils.FileTypeException;
import com.chengxusheji.utils.ExportExcelUtil;

@Controller @Scope("prototype")
public class JftjcAction extends BaseAction {

    /*界面层需要查询的属性: 积分条件*/
    private String jftj;
    public void setJftj(String jftj) {
        this.jftj = jftj;
    }
    public String getJftj() {
        return this.jftj;
    }

    /*当前第几页*/
    private int currentPage;
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    /*一共多少页*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    private int id;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    /*当前查询的总记录数目*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*业务层对象*/
    @Resource JftjcDAO jftjcDAO;

    /*待操作的Jftjc对象*/
    private Jftjc jftjc;
    public void setJftjc(Jftjc jftjc) {
        this.jftjc = jftjc;
    }
    public Jftjc getJftjc() {
        return this.jftjc;
    }

    /*跳转到添加Jftjc视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*添加Jftjc信息*/
    @SuppressWarnings("deprecation")
    public String AddJftjc() {
        ActionContext ctx = ActionContext.getContext();
        try {
            jftjcDAO.AddJftjc(jftjc);
            ctx.put("message",  java.net.URLEncoder.encode("Jftjc添加成功!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("图片文件格式不对!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Jftjc添加失败!"));
            return "error";
        }
    }

    /*查询Jftjc信息*/
    public String QueryJftjc() {
        if(currentPage == 0) currentPage = 1;
        if(jftj == null) jftj = "";
        List<Jftjc> jftjcList = jftjcDAO.QueryJftjcInfo(jftj, currentPage);
        /*计算总的页数和总的记录数*/
        jftjcDAO.CalculateTotalPageAndRecordNumber(jftj);
        /*获取到总的页码数目*/
        totalPage = jftjcDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = jftjcDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("jftjcList",  jftjcList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("jftj", jftj);
        return "query_view";
    }

    /*后台导出到excel*/
    public String QueryJftjcOutputToExcel() { 
        if(jftj == null) jftj = "";
        List<Jftjc> jftjcList = jftjcDAO.QueryJftjcInfo(jftj);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Jftjc信息记录"; 
        String[] headers = { "积分id","积分条件","分数","积分类型","mtypeid"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<jftjcList.size();i++) {
        	Jftjc jftjc = jftjcList.get(i); 
        	dataset.add(new String[]{jftjc.getId() + "",jftjc.getJftj(),jftjc.getFs() + "",jftjc.getTypeid() + "",jftjc.getMtypeid() + ""});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		HttpServletResponse response = null;//创建一个HttpServletResponse对象 
		OutputStream out = null;//创建一个输出流对象 
		try { 
			response = ServletActionContext.getResponse();//初始化HttpServletResponse对象 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Jftjc.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = ServletActionContext.getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
		return null;
    }
    /*前台查询Jftjc信息*/
    public String FrontQueryJftjc() {
        if(currentPage == 0) currentPage = 1;
        if(jftj == null) jftj = "";
        List<Jftjc> jftjcList = jftjcDAO.QueryJftjcInfo(jftj, currentPage);
        /*计算总的页数和总的记录数*/
        jftjcDAO.CalculateTotalPageAndRecordNumber(jftj);
        /*获取到总的页码数目*/
        totalPage = jftjcDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = jftjcDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("jftjcList",  jftjcList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("jftj", jftj);
        return "front_query_view";
    }

    /*查询要修改的Jftjc信息*/
    public String ModifyJftjcQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Jftjc对象*/
        Jftjc jftjc = jftjcDAO.GetJftjcById(id);

        ctx.put("jftjc",  jftjc);
        return "modify_view";
    }

    /*查询要修改的Jftjc信息*/
    public String FrontShowJftjcQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Jftjc对象*/
        Jftjc jftjc = jftjcDAO.GetJftjcById(id);

        ctx.put("jftjc",  jftjc);
        return "front_show_view";
    }

    /*更新修改Jftjc信息*/
    public String ModifyJftjc() {
        ActionContext ctx = ActionContext.getContext();
        try {
            jftjcDAO.UpdateJftjc(jftjc);
            ctx.put("message",  java.net.URLEncoder.encode("Jftjc信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Jftjc信息更新失败!"));
            return "error";
       }
   }

    /*删除Jftjc信息*/
    public String DeleteJftjc() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            jftjcDAO.DeleteJftjc(id);
            ctx.put("message",  java.net.URLEncoder.encode("Jftjc删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Jftjc删除失败!"));
            return "error";
        }
    }

}
