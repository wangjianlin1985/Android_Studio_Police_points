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

    /*�������Ҫ��ѯ������: ��������*/
    private String bname;
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return this.bname;
    }

    /*�������Ҫ��ѯ������: ���ּ���*/
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
    @Resource ZxyDAO zxyDAO;

    /*��������Zxy����*/
    private Zxy zxy;
    public void setZxy(Zxy zxy) {
        this.zxy = zxy;
    }
    public Zxy getZxy() {
        return this.zxy;
    }

    /*��ת�����Zxy��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���Zxy��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddZxy() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyDAO.AddZxy(zxy);
            ctx.put("message",  java.net.URLEncoder.encode("Zxy��ӳɹ�!"));
            return "add_success";
        } catch(FileTypeException ex) {
        	ctx.put("error",  java.net.URLEncoder.encode("ͼƬ�ļ���ʽ����!"));
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxy���ʧ��!"));
            return "error";
        }
    }

    /*��ѯZxy��Ϣ*/
    public String QueryZxy() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxy> zxyList = zxyDAO.QueryZxyInfo(bname, jfjd, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxyDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxyDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��̨������excel*/
    public String QueryZxyOutputToExcel() { 
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxy> zxyList = zxyDAO.QueryZxyInfo(bname,jfjd);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Zxy��Ϣ��¼"; 
        String[] headers = { "id","��������","��������","��������","���ּ���","���ּ��ȿ�ʼʱ��","���ּ��Ƚ���ʱ��","���·�������","�����߷û���","������������","��λ���ӻ���","�ϼƷ�"};
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
		HttpServletResponse response = null;//����һ��HttpServletResponse���� 
		OutputStream out = null;//����һ����������� 
		try { 
			response = ServletActionContext.getResponse();//��ʼ��HttpServletResponse���� 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Zxy.xls");//filename�����ص�xls���������������Ӣ�� 
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
    /*ǰ̨��ѯZxy��Ϣ*/
    public String FrontQueryZxy() {
        if(currentPage == 0) currentPage = 1;
        if(bname == null) bname = "";
        if(jfjd == null) jfjd = "";
        List<Zxy> zxyList = zxyDAO.QueryZxyInfo(bname, jfjd, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        zxyDAO.CalculateTotalPageAndRecordNumber(bname, jfjd);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = zxyDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��ѯҪ�޸ĵ�Zxy��Ϣ*/
    public String ModifyZxyQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxy����*/
        Zxy zxy = zxyDAO.GetZxyById(id);

        ctx.put("zxy",  zxy);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Zxy��Ϣ*/
    public String FrontShowZxyQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������id��ȡZxy����*/
        Zxy zxy = zxyDAO.GetZxyById(id);

        ctx.put("zxy",  zxy);
        return "front_show_view";
    }

    /*�����޸�Zxy��Ϣ*/
    public String ModifyZxy() {
        ActionContext ctx = ActionContext.getContext();
        try {
            zxyDAO.UpdateZxy(zxy);
            ctx.put("message",  java.net.URLEncoder.encode("Zxy��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxy��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Zxy��Ϣ*/
    public String DeleteZxy() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            zxyDAO.DeleteZxy(id);
            ctx.put("message",  java.net.URLEncoder.encode("Zxyɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Zxyɾ��ʧ��!"));
            return "error";
        }
    }

}
