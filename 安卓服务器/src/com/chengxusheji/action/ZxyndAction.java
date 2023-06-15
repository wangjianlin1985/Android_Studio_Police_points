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
import com.chengxusheji.dao.ZxyndDAO;
import com.chengxusheji.domain.Zxynd;
import com.chengxusheji.utils.FileTypeException;
import com.chengxusheji.utils.ExportExcelUtil;

@Controller @Scope("prototype")
public class ZxyndAction extends BaseAction {

    /*界面层需要查询的属性: 部门名称*/
    private String bname;
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return this.bname;
    }

    /*界面层需要查询的属性: 积分年度*/
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
    @Resource ZxyndDAO zxyndDAO;

    /*待操作的Zxynd对象*/
    private Zxynd zxynd;
    public void setZxynd(Zxynd zxynd) {
        this.zxynd = zxynd;
    }
    public Zxynd getZxynd() {
        return this.zxynd;
    }

    /*跳转到添加Zxynd视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*添加Zxynd信息*/
    @SuppressWarnings("deprecation")
    public String AddZxynd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyndDAO.AddZxynd(zxynd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxynd添加成功!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("图片文件格式不对!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxynd添加失败!"));
            return "error";
        }
    }

    /*查询Zxynd信息*/
    public String QueryZxynd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxynd> zxyndList = zxyndDAO.QueryZxyndInfo(bname, jfjd, currentPage);
        /*计算总的页数和总的记录数*/
        zxyndDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*获取到总的页码数目*/
        totalPage = zxyndDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxyndDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxyndList",  zxyndList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("jfjd", jfjd);
        return "query_view";
    }

    /*后台导出到excel*/
    public String QueryZxyndOutputToExcel() { 
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxynd> zxyndList = zxyndDAO.QueryZxyndInfo(bname,jfjd);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxynd信息记录"; 
        String[] headers = { "id","部门名称","积分年度","号码走访积分","测评反馈积分","单位重视积分","合计分"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<zxyndList.size();i++) {
        	Zxynd zxynd = zxyndList.get(i); 
        	dataset.add(new String[]{zxynd.getId() + "",zxynd.getBname(),zxynd.getJfjd(),zxynd.getHmzfjf() + "",zxynd.getCpfkjf() + "",zxynd.getDwzsjf() + "",zxynd.getHjf() + ""});
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
			response.setHeader("Content-disposition","attachment; filename="+"Zxynd.xls");//filename是下载的xls的名，建议最好用英文 
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
    /*前台查询Zxynd信息*/
    public String FrontQueryZxynd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxynd> zxyndList = zxyndDAO.QueryZxyndInfo(bname, jfjd, currentPage);
        /*计算总的页数和总的记录数*/
        zxyndDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*获取到总的页码数目*/
        totalPage = zxyndDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxyndDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxyndList",  zxyndList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("jfjd", jfjd);
        return "front_query_view";
    }

    /*查询要修改的Zxynd信息*/
    public String ModifyZxyndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxynd对象*/
        Zxynd zxynd = zxyndDAO.GetZxyndById(id);

        ctx.put("zxynd",  zxynd);
        return "modify_view";
    }

    /*查询要修改的Zxynd信息*/
    public String FrontShowZxyndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxynd对象*/
        Zxynd zxynd = zxyndDAO.GetZxyndById(id);

        ctx.put("zxynd",  zxynd);
        return "front_show_view";
    }

    /*更新修改Zxynd信息*/
    public String ModifyZxynd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyndDAO.UpdateZxynd(zxynd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxynd信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxynd信息更新失败!"));
            return "error";
       }
   }

    /*删除Zxynd信息*/
    public String DeleteZxynd() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxyndDAO.DeleteZxynd(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxynd删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxynd删除失败!"));
            return "error";
        }
    }

}
