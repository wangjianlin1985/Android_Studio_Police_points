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

    /*�������Ҫ��ѯ������: ��������*/
    private String bname;
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return this.bname;
    }

    /*�������Ҫ��ѯ������: �������*/
    private String jfjd;
    public void setJfjd(String jfjd) {
        this.jfjd = jfjd;
    }
    public String getJfjd() {
        return this.jfjd;
    }

    /*��ǰ�ڼ�ҳ*/
    private int currentPage;
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    /*һ������ҳ*/
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

    /*��ǰ��ѯ���ܼ�¼��Ŀ*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*ҵ������*/
    @Resource ZxyndDAO zxyndDAO;

    /*��������Zxynd����*/
    private Zxynd zxynd;
    public void setZxynd(Zxynd zxynd) {
        this.zxynd = zxynd;
    }
    public Zxynd getZxynd() {
        return this.zxynd;
    }

    /*��ת�����Zxynd��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���Zxynd��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddZxynd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyndDAO.AddZxynd(zxynd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxynd��ӳɹ�!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("ͼƬ�ļ���ʽ����!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxynd���ʧ��!"));
            return "error";
        }
    }

    /*��ѯZxynd��Ϣ*/
    public String QueryZxynd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxynd> zxyndList = zxyndDAO.QueryZxyndInfo(bname, jfjd, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxyndDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxyndDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��̨������excel*/
    public String QueryZxyndOutputToExcel() { 
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxynd> zxyndList = zxyndDAO.QueryZxyndInfo(bname,jfjd);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxynd��Ϣ��¼"; 
        String[] headers = { "id","��������","�������","�����߷û���","������������","��λ���ӻ���","�ϼƷ�"};
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
		HttpServletResponse response = null;//����һ��HttpServletResponse���� 
		OutputStream out = null;//����һ����������� 
		try { 
			response = ServletActionContext.getResponse();//��ʼ��HttpServletResponse���� 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Zxynd.xls");//filename�����ص�xls���������������Ӣ�� 
			response.setContentType("application/msexcel;charset=UTF-8");//�������� 
			response.setHeader("Pragma","No-cache");//����ͷ 
			response.setHeader("Cache-Control","no-cache");//����ͷ 
			response.setDateHeader("Expires", 0);//��������ͷ  
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
    /*ǰ̨��ѯZxynd��Ϣ*/
    public String FrontQueryZxynd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxynd> zxyndList = zxyndDAO.QueryZxyndInfo(bname, jfjd, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxyndDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxyndDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��ѯҪ�޸ĵ�Zxynd��Ϣ*/
    public String ModifyZxyndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxynd����*/
        Zxynd zxynd = zxyndDAO.GetZxyndById(id);

        ctx.put("zxynd",  zxynd);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Zxynd��Ϣ*/
    public String FrontShowZxyndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxynd����*/
        Zxynd zxynd = zxyndDAO.GetZxyndById(id);

        ctx.put("zxynd",  zxynd);
        return "front_show_view";
    }

    /*�����޸�Zxynd��Ϣ*/
    public String ModifyZxynd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyndDAO.UpdateZxynd(zxynd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxynd��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxynd��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Zxynd��Ϣ*/
    public String DeleteZxynd() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxyndDAO.DeleteZxynd(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyndɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyndɾ��ʧ��!"));
            return "error";
        }
    }

}
