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

    /*�������Ҫ��ѯ������: ��������*/
    private String bname;
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return this.bname;
    }

    /*�������Ҫ��ѯ������: ��Ա����*/
    private String sname;
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSname() {
        return this.sname;
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
    @Resource ZxymjndDAO zxymjndDAO;

    /*��������Zxymjnd����*/
    private Zxymjnd zxymjnd;
    public void setZxymjnd(Zxymjnd zxymjnd) {
        this.zxymjnd = zxymjnd;
    }
    public Zxymjnd getZxymjnd() {
        return this.zxymjnd;
    }

    /*��ת�����Zxymjnd��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���Zxymjnd��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddZxymjnd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxymjndDAO.AddZxymjnd(zxymjnd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxymjnd��ӳɹ�!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("ͼƬ�ļ���ʽ����!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxymjnd���ʧ��!"));
            return "error";
        }
    }

    /*��ѯZxymjnd��Ϣ*/
    public String QueryZxymjnd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        List<Zxymjnd> zxymjndList = zxymjndDAO.QueryZxymjndInfo(bname, sname, jfjd, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxymjndDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxymjndDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��̨������excel*/
    public String QueryZxymjndOutputToExcel() { 
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        List<Zxymjnd> zxymjndList = zxymjndDAO.QueryZxymjndInfo(bname,sname,jfjd);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxymjnd��Ϣ��¼"; 
        String[] headers = { "id","��������","��Ա����","�������","���·�������","�����߷û���","������������","��λ���ӻ���","�ϼƷ�"};
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
		HttpServletResponse response = null;//����һ��HttpServletResponse���� 
		OutputStream out = null;//����һ����������� 
		try { 
			response = ServletActionContext.getResponse();//��ʼ��HttpServletResponse���� 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Zxymjnd.xls");//filename�����ص�xls���������������Ӣ�� 
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
    /*ǰ̨��ѯZxymjnd��Ϣ*/
    public String FrontQueryZxymjnd() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        List<Zxymjnd> zxymjndList = zxymjndDAO.QueryZxymjndInfo(bname, sname, jfjd, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxymjndDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxymjndDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��ѯҪ�޸ĵ�Zxymjnd��Ϣ*/
    public String ModifyZxymjndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxymjnd����*/
        Zxymjnd zxymjnd = zxymjndDAO.GetZxymjndById(id);

        ctx.put("zxymjnd",  zxymjnd);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Zxymjnd��Ϣ*/
    public String FrontShowZxymjndQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxymjnd����*/
        Zxymjnd zxymjnd = zxymjndDAO.GetZxymjndById(id);

        ctx.put("zxymjnd",  zxymjnd);
        return "front_show_view";
    }

    /*�����޸�Zxymjnd��Ϣ*/
    public String ModifyZxymjnd() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxymjndDAO.UpdateZxymjnd(zxymjnd);
            ctx.put("message",  java.net.URLEncoder.encode("Zxymjnd��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxymjnd��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Zxymjnd��Ϣ*/
    public String DeleteZxymjnd() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxymjndDAO.DeleteZxymjnd(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxymjndɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxymjndɾ��ʧ��!"));
            return "error";
        }
    }

}
