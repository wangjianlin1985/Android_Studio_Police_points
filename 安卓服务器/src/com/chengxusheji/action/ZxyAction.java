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
import com.chengxusheji.dao.ZxyDAO;
import com.chengxusheji.domain.Zxy;
import com.chengxusheji.utils.FileTypeException;
import com.chengxusheji.utils.ExportExcelUtil;

@Controller @Scope("prototype")
public class ZxyAction extends BaseAction {

    /*界面层需要查询的属性: 部门名称*/
    private String bname;
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return this.bname;
    }

    /*界面层需要查询的属性: 积分季度*/
    private String jfjd;
    public void setJfjd(String jfjd) {
        this.jfjd = jfjd;
    }
    public String getJfjd() {
        return this.jfjd;
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
    @Resource ZxyDAO zxyDAO;

    /*待操作的Zxy对象*/
    private Zxy zxy;
    public void setZxy(Zxy zxy) {
        this.zxy = zxy;
    }
    public Zxy getZxy() {
        return this.zxy;
    }

    /*跳转到添加Zxy视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*添加Zxy信息*/
    @SuppressWarnings("deprecation")
    public String AddZxy() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyDAO.AddZxy(zxy);
            ctx.put("message",  java.net.URLEncoder.encode("Zxy添加成功!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("图片文件格式不对!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxy添加失败!"));
            return "error";
        }
    }

    /*查询Zxy信息*/
    public String QueryZxy() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxy> zxyList = zxyDAO.QueryZxyInfo(bname, jfjd, currentPage);
        /*计算总的页数和总的记录数*/
        zxyDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*获取到总的页码数目*/
        totalPage = zxyDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxyDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxyList",  zxyList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("jfjd", jfjd);
        return "query_view";
    }

    /*后台导出到excel*/
    public String QueryZxyOutputToExcel() { 
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxy> zxyList = zxyDAO.QueryZxyInfo(bname,jfjd);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxy信息记录"; 
        String[] headers = { "id","部门名称","部门类型","积分条件","积分季度","积分季度开始时间","积分季度结束时间","刑事发案积分","号码走访积分","测评反馈积分","单位重视积分","合计分"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<zxyList.size();i++) {
        	Zxy zxy = zxyList.get(i); 
        	dataset.add(new String[]{zxy.getId() + "",zxy.getBname(),zxy.getBtypes() + "",zxy.getJid() + "",zxy.getJfjd(),new SimpleDateFormat("yyyy-MM-dd").format(zxy.getJdsdate()),new SimpleDateFormat("yyyy-MM-dd").format(zxy.getJdedate()),zxy.getXsfajf() + "",zxy.getHmzfjf() + "",zxy.getCpfkjf() + "",zxy.getDwzsjf() + "",zxy.getHjf() + ""});
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
			response.setHeader("Content-disposition","attachment; filename="+"Zxy.xls");//filename是下载的xls的名，建议最好用英文 
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
    /*前台查询Zxy信息*/
    public String FrontQueryZxy() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxy> zxyList = zxyDAO.QueryZxyInfo(bname, jfjd, currentPage);
        /*计算总的页数和总的记录数*/
        zxyDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*获取到总的页码数目*/
        totalPage = zxyDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxyDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxyList",  zxyList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("jfjd", jfjd);
        return "front_query_view";
    }

    /*查询要修改的Zxy信息*/
    public String ModifyZxyQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxy对象*/
        Zxy zxy = zxyDAO.GetZxyById(id);

        ctx.put("zxy",  zxy);
        return "modify_view";
    }

    /*查询要修改的Zxy信息*/
    public String FrontShowZxyQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxy对象*/
        Zxy zxy = zxyDAO.GetZxyById(id);

        ctx.put("zxy",  zxy);
        return "front_show_view";
    }

    /*更新修改Zxy信息*/
    public String ModifyZxy() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyDAO.UpdateZxy(zxy);
            ctx.put("message",  java.net.URLEncoder.encode("Zxy信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxy信息更新失败!"));
            return "error";
       }
   }

    /*删除Zxy信息*/
    public String DeleteZxy() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxyDAO.DeleteZxy(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxy删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxy删除失败!"));
            return "error";
        }
    }

}
