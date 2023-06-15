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
import com.chengxusheji.dao.ZxyjfDAO;
import com.chengxusheji.domain.Zxyjf;
import com.chengxusheji.utils.FileTypeException;
import com.chengxusheji.utils.ExportExcelUtil;

@Controller @Scope("prototype")
public class ZxyjfAction extends BaseAction {

    /*界面层需要查询的属性: 部门名称*/
    private String bname;
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return this.bname;
    }

    /*界面层需要查询的属性: 警员姓名*/
    private String sname;
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSname() {
        return this.sname;
    }

    /*界面层需要查询的属性: 积分季度*/
    private String jfjd;
    public void setJfjd(String jfjd) {
        this.jfjd = jfjd;
    }
    public String getJfjd() {
        return this.jfjd;
    }

    /*界面层需要查询的属性: 积分日期*/
    private String jfdate;
    public void setJfdate(String jfdate) {
        this.jfdate = jfdate;
    }
    public String getJfdate() {
        return this.jfdate;
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
    @Resource ZxyjfDAO zxyjfDAO;

    /*待操作的Zxyjf对象*/
    private Zxyjf zxyjf;
    public void setZxyjf(Zxyjf zxyjf) {
        this.zxyjf = zxyjf;
    }
    public Zxyjf getZxyjf() {
        return this.zxyjf;
    }

    /*跳转到添加Zxyjf视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*添加Zxyjf信息*/
    @SuppressWarnings("deprecation")
    public String AddZxyjf() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyjfDAO.AddZxyjf(zxyjf);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyjf添加成功!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("图片文件格式不对!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyjf添加失败!"));
            return "error";
        }
    }

    /*查询Zxyjf信息*/
    public String QueryZxyjf() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        if(jfdate == null) jfdate = "";
        List<Zxyjf> zxyjfList = zxyjfDAO.QueryZxyjfInfo(bname, sname, jfjd, jfdate, currentPage);
        /*计算总的页数和总的记录数*/
        zxyjfDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd, jfdate);
        /*获取到总的页码数目*/
        totalPage = zxyjfDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxyjfDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxyjfList",  zxyjfList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("sname", sname);
        ctx.put("jfjd", jfjd);
        ctx.put("jfdate", jfdate);
        return "query_view";
    }

    /*后台导出到excel*/
    public String QueryZxyjfOutputToExcel() { 
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        if(jfdate == null) jfdate = "";
        List<Zxyjf> zxyjfList = zxyjfDAO.QueryZxyjfInfo(bname,sname,jfjd,jfdate);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxyjf信息记录"; 
        String[] headers = { "id","部门名称","部门类型","警员姓名","积分条件","积分季度","积分日期","积分季度开始时间","分数","数量","刑事发案积分","号码走访积分","测评反馈积分","单位重视积分"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<zxyjfList.size();i++) {
        	Zxyjf zxyjf = zxyjfList.get(i); 
        	dataset.add(new String[]{zxyjf.getId() + "",zxyjf.getBname(),zxyjf.getBtypes() + "",zxyjf.getSname(),zxyjf.getJftj(),zxyjf.getJfjd(),new SimpleDateFormat("yyyy-MM-dd").format(zxyjf.getJfdate()),new SimpleDateFormat("yyyy-MM-dd").format(zxyjf.getJdsdate()),zxyjf.getFs() + "",zxyjf.getSl() + "",zxyjf.getXsfajf() + "",zxyjf.getHmzfjf() + "",zxyjf.getCpfkjf() + "",zxyjf.getDwzsjf() + ""});
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
			response.setHeader("Content-disposition","attachment; filename="+"Zxyjf.xls");//filename是下载的xls的名，建议最好用英文 
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
    /*前台查询Zxyjf信息*/
    public String FrontQueryZxyjf() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        if(jfdate == null) jfdate = "";
        List<Zxyjf> zxyjfList = zxyjfDAO.QueryZxyjfInfo(bname, sname, jfjd, jfdate, currentPage);
        /*计算总的页数和总的记录数*/
        zxyjfDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd, jfdate);
        /*获取到总的页码数目*/
        totalPage = zxyjfDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = zxyjfDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("zxyjfList",  zxyjfList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bname", bname);
        ctx.put("sname", sname);
        ctx.put("jfjd", jfjd);
        ctx.put("jfdate", jfdate);
        return "front_query_view";
    }

    /*查询要修改的Zxyjf信息*/
    public String ModifyZxyjfQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxyjf对象*/
        Zxyjf zxyjf = zxyjfDAO.GetZxyjfById(id);

        ctx.put("zxyjf",  zxyjf);
        return "modify_view";
    }

    /*查询要修改的Zxyjf信息*/
    public String FrontShowZxyjfQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键id获取Zxyjf对象*/
        Zxyjf zxyjf = zxyjfDAO.GetZxyjfById(id);

        ctx.put("zxyjf",  zxyjf);
        return "front_show_view";
    }

    /*更新修改Zxyjf信息*/
    public String ModifyZxyjf() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyjfDAO.UpdateZxyjf(zxyjf);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyjf信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyjf信息更新失败!"));
            return "error";
       }
   }

    /*删除Zxyjf信息*/
    public String DeleteZxyjf() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxyjfDAO.DeleteZxyjf(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyjf删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyjf删除失败!"));
            return "error";
        }
    }

}
