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

    /*�������Ҫ��ѯ������: ���ּ���*/
    private String jfjd;
    public void setJfjd(String jfjd) {
        this.jfjd = jfjd;
    }
    public String getJfjd() {
        return this.jfjd;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private String jfdate;
    public void setJfdate(String jfdate) {
        this.jfdate = jfdate;
    }
    public String getJfdate() {
        return this.jfdate;
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
    @Resource ZxyjfDAO zxyjfDAO;

    /*��������Zxyjf����*/
    private Zxyjf zxyjf;
    public void setZxyjf(Zxyjf zxyjf) {
        this.zxyjf = zxyjf;
    }
    public Zxyjf getZxyjf() {
        return this.zxyjf;
    }

    /*��ת�����Zxyjf��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���Zxyjf��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddZxyjf() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyjfDAO.AddZxyjf(zxyjf);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyjf��ӳɹ�!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("ͼƬ�ļ���ʽ����!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyjf���ʧ��!"));
            return "error";
        }
    }

    /*��ѯZxyjf��Ϣ*/
    public String QueryZxyjf() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        if(jfdate == null) jfdate = "";
        List<Zxyjf> zxyjfList = zxyjfDAO.QueryZxyjfInfo(bname, sname, jfjd, jfdate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxyjfDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd, jfdate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxyjfDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��̨������excel*/
    public String QueryZxyjfOutputToExcel() { 
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        if(jfdate == null) jfdate = "";
        List<Zxyjf> zxyjfList = zxyjfDAO.QueryZxyjfInfo(bname,sname,jfjd,jfdate);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxyjf��Ϣ��¼"; 
        String[] headers = { "id","��������","��������","��Ա����","��������","���ּ���","��������","���ּ��ȿ�ʼʱ��","����","����","���·�������","�����߷û���","������������","��λ���ӻ���"};
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
		HttpServletResponse response = null;//����һ��HttpServletResponse���� 
		OutputStream out = null;//����һ����������� 
		try { 
			response = ServletActionContext.getResponse();//��ʼ��HttpServletResponse���� 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Zxyjf.xls");//filename�����ص�xls���������������Ӣ�� 
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
    /*ǰ̨��ѯZxyjf��Ϣ*/
    public String FrontQueryZxyjf() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(sname == null) sname = "";
        if(jfjd == null) jfjd = "";
        if(jfdate == null) jfdate = "";
        List<Zxyjf> zxyjfList = zxyjfDAO.QueryZxyjfInfo(bname, sname, jfjd, jfdate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxyjfDAO.CalculateTotalPageAndRecordNumber(bname, sname, jfjd, jfdate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxyjfDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��ѯҪ�޸ĵ�Zxyjf��Ϣ*/
    public String ModifyZxyjfQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxyjf����*/
        Zxyjf zxyjf = zxyjfDAO.GetZxyjfById(id);

        ctx.put("zxyjf",  zxyjf);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Zxyjf��Ϣ*/
    public String FrontShowZxyjfQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxyjf����*/
        Zxyjf zxyjf = zxyjfDAO.GetZxyjfById(id);

        ctx.put("zxyjf",  zxyjf);
        return "front_show_view";
    }

    /*�����޸�Zxyjf��Ϣ*/
    public String ModifyZxyjf() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyjfDAO.UpdateZxyjf(zxyjf);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyjf��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyjf��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Zxyjf��Ϣ*/
    public String DeleteZxyjf() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxyjfDAO.DeleteZxyjf(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyjfɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyjfɾ��ʧ��!"));
            return "error";
        }
    }

}
