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
import com.chengxusheji.dao.ZxymjndDAO;
import com.chengxusheji.domain.Zxymjnd;
import com.chengxusheji.utils.FileTypeException;
import com.chengxusheji.utils.ExportExcelUtil;

@Controller @Scope("prototype")
public class ZxymjndAction extends BaseAction {

    /*界面层需要查询的属性: 部门名称*/
    private String bname;
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return this.bname;
    }

    /*界面层需要查询的属性: 警员名称*/
    private String sname;
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSname() {
        return this.sname;
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
    @Resource ZxymjndDAO zxymjndDAO;

    /*待操作的Zxymjnd对象*/
    private Zxymjnd zxymjnd;
    public void setZxymjnd(Zxymjnd zxymjnd) {
        this.zxymjnd = zxymjnd;
    }
    public Zxymjnd getZxymjnd() {
        return this.zxymjnd;
    }

    /*跳转到添加Zxymjnd视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*添加Zxymjnd信息*/
    @SuppressWarnings("deprecation")
    public String AddZxymjnd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxymjndDAO.AddZxymjnd(zxymjnd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxymjnd添加成功!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("图片文件格式不对!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxymjnd添加失败!"));
            return "error";
        }
    }

    /*查询Zxymjnd信息*/
    public String QueryZxymjnd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        List<Zxymjnd> zxymjndList = zxymjndDAO.QueryZxymjndInfo(bname, sname, jfjd, currentPage);
        /*计算总的页数和总的记录数*/
        zxymjndDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd);
        /*获取到总的页码数目*/
        totalPage = zxymjndDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxymjndDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxymjndList",  zxymjndList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("sname", sname);
        ctx.put("jfjd", jfjd);
        return "query_view";
    }

    /*后台导出到excel*/
    public String QueryZxymjndOutputToExcel() { 
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        List<Zxymjnd> zxymjndList = zxymjndDAO.QueryZxymjndInfo(bname,sname,jfjd);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxymjnd信息记录"; 
        String[] headers = { "id","部门名称","警员名称","积分年度","刑事发案积分","号码走访积分","测评反馈积分","单位重视积分","合计分"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<zxymjndList.size();i++) {
        	Zxymjnd zxymjnd = zxymjndList.get(i); 
        	dataset.add(new String[]{zxymjnd.getId() + "",zxymjnd.getBname(),zxymjnd.getSname(),zxymjnd.getJfjd(),zxymjnd.getXsfajf() + "",zxymjnd.getHmzfjf() + "",zxymjnd.getCpfkjf() + "",zxymjnd.getDwzsjf() + "",zxymjnd.getHjf() + ""});
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
			response.setHeader("Content-disposition","attachment; filename="+"Zxymjnd.xls");//filename是下载的xls的名，建议最好用英文 
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
    /*前台查询Zxymjnd信息*/
    public String FrontQueryZxymjnd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        List<Zxymjnd> zxymjndList = zxymjndDAO.QueryZxymjndInfo(bname, sname, jfjd, currentPage);
        /*计算总的页数和总的记录数*/
        zxymjndDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd);
        /*获取到总的页码数目*/
        totalPage = zxymjndDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxymjndDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxymjndList",  zxymjndList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("sname", sname);
        ctx.put("jfjd", jfjd);
        return "front_query_view";
    }

    /*查询要修改的Zxymjnd信息*/
    public String ModifyZxymjndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxymjnd对象*/
        Zxymjnd zxymjnd = zxymjndDAO.GetZxymjndById(id);

        ctx.put("zxymjnd",  zxymjnd);
        return "modify_view";
    }

    /*查询要修改的Zxymjnd信息*/
    public String FrontShowZxymjndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxymjnd对象*/
        Zxymjnd zxymjnd = zxymjndDAO.GetZxymjndById(id);

        ctx.put("zxymjnd",  zxymjnd);
        return "front_show_view";
    }

    /*更新修改Zxymjnd信息*/
    public String ModifyZxymjnd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxymjndDAO.UpdateZxymjnd(zxymjnd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxymjnd信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxymjnd信息更新失败!"));
            return "error";
       }
   }

    /*删除Zxymjnd信息*/
    public String DeleteZxymjnd() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxymjndDAO.DeleteZxymjnd(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxymjnd删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxymjnd删除失败!"));
            return "error";
        }
    }

}
