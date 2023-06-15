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

    /*�������Ҫ��ѯ������: ��������*/
    private String jftj;
    public void setJftj(String jftj) {
        this.jftj = jftj;
    }
    public String getJftj() {
        return this.jftj;
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
    @Resource JftjcDAO jftjcDAO;

    /*��������Jftjc����*/
    private Jftjc jftjc;
    public void setJftjc(Jftjc jftjc) {
        this.jftjc = jftjc;
    }
    public Jftjc getJftjc() {
        return this.jftjc;
    }

    /*��ת�����Jftjc��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���Jftjc��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddJftjc() {
        ActionContext ctx = ActionContext.getContext();
        try {
            jftjcDAO.AddJftjc(jftjc);
            ctx.put("message",  java.net.URLEncoder.encode("Jftjc��ӳɹ�!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("ͼƬ�ļ���ʽ����!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Jftjc���ʧ��!"));
            return "error";
        }
    }

    /*��ѯJftjc��Ϣ*/
    public String QueryJftjc() {
        if(currentPage == 0) currentPage = 1;
        if(jftj == null) jftj = "";
        List<Jftjc> jftjcList = jftjcDAO.QueryJftjcInfo(jftj, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        jftjcDAO.CalculateTotalPageAndRecordNumber(jftj);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = jftjcDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = jftjcDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("jftjcList",  jftjcList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("jftj", jftj);
        return "query_view";
    }

    /*��̨������excel*/
    public String QueryJftjcOutputToExcel() { 
        if(jftj == null) jftj = "";
        List<Jftjc> jftjcList = jftjcDAO.QueryJftjcInfo(jftj);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Jftjc��Ϣ��¼"; 
        String[] headers = { "����id","��������","����","��������","mtypeid"};
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
		HttpServletResponse response = null;//����һ��HttpServletResponse���� 
		OutputStream out = null;//����һ����������� 
		try { 
			response = ServletActionContext.getResponse();//��ʼ��HttpServletResponse���� 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Jftjc.xls");//filename�����ص�xls���������������Ӣ�� 
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
    /*ǰ̨��ѯJftjc��Ϣ*/
    public String FrontQueryJftjc() {
        if(currentPage == 0) currentPage = 1;
        if(jftj == null) jftj = "";
        List<Jftjc> jftjcList = jftjcDAO.QueryJftjcInfo(jftj, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        jftjcDAO.CalculateTotalPageAndRecordNumber(jftj);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = jftjcDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = jftjcDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("jftjcList",  jftjcList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("jftj", jftj);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�Jftjc��Ϣ*/
    public String ModifyJftjcQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡJftjc����*/
        Jftjc jftjc = jftjcDAO.GetJftjcById(id);

        ctx.put("jftjc",  jftjc);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Jftjc��Ϣ*/
    public String FrontShowJftjcQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡJftjc����*/
        Jftjc jftjc = jftjcDAO.GetJftjcById(id);

        ctx.put("jftjc",  jftjc);
        return "front_show_view";
    }

    /*�����޸�Jftjc��Ϣ*/
    public String ModifyJftjc() {
        ActionContext ctx = ActionContext.getContext();
        try {
            jftjcDAO.UpdateJftjc(jftjc);
            ctx.put("message",  java.net.URLEncoder.encode("Jftjc��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Jftjc��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Jftjc��Ϣ*/
    public String DeleteJftjc() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            jftjcDAO.DeleteJftjc(id);
            ctx.put("message",  java.net.URLEncoder.encode("Jftjcɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Jftjcɾ��ʧ��!"));
            return "error";
        }
    }

}
