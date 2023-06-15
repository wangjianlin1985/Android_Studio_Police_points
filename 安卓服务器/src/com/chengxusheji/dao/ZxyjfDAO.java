package com.chengxusheji.dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.chengxusheji.domain.Zxyjf;

@Service @Transactional
public class ZxyjfDAO {

	@Resource SessionFactory factory;
    /*ÿҳ��ʾ��¼��Ŀ*/
    private final int PAGE_SIZE = 10;

    /*�����ѯ���ܵ�ҳ��*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*�����ѯ�����ܼ�¼��*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*���ͼ����Ϣ*/
    public void AddZxyjf(Zxyjf zxyjf) throws Exception {
    	Session s = factory.getCurrentSession();
     s.save(zxyjf);
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxyjf> QueryZxyjfInfo(String bname,String sname,String jfjd,String jfdate,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxyjf zxyjf where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxyjf.bname like '%" + bname + "%'";
    	if(!sname.equals("")) hql = hql + " and zxyjf.sname like '%" + sname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxyjf.jfjd like '%" + jfjd + "%'";
    	if(!jfdate.equals("")) hql = hql + " and zxyjf.jfdate like '%" + jfdate + "%'";
    	Query q = s.createQuery(hql);
    	/*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List zxyjfList = q.list();
    	return (ArrayList<Zxyjf>) zxyjfList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxyjf> QueryZxyjfInfo(String bname,String sname,String jfjd,String jfdate) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxyjf zxyjf where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxyjf.bname like '%" + bname + "%'";
    	if(!sname.equals("")) hql = hql + " and zxyjf.sname like '%" + sname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxyjf.jfjd like '%" + jfjd + "%'";
    	if(!jfdate.equals("")) hql = hql + " and zxyjf.jfdate like '%" + jfdate + "%'";
    	Query q = s.createQuery(hql);
    	List zxyjfList = q.list();
    	return (ArrayList<Zxyjf>) zxyjfList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxyjf> QueryAllZxyjfInfo() {
        Session s = factory.getCurrentSession(); 
        String hql = "From Zxyjf";
        Query q = s.createQuery(hql);
        List zxyjfList = q.list();
        return (ArrayList<Zxyjf>) zxyjfList;
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(String bname,String sname,String jfjd,String jfdate) {
        Session s = factory.getCurrentSession();
        String hql = "From Zxyjf zxyjf where 1=1";
        if(!bname.equals("")) hql = hql + " and zxyjf.bname like '%" + bname + "%'";
        if(!sname.equals("")) hql = hql + " and zxyjf.sname like '%" + sname + "%'";
        if(!jfjd.equals("")) hql = hql + " and zxyjf.jfjd like '%" + jfjd + "%'";
        if(!jfdate.equals("")) hql = hql + " and zxyjf.jfdate like '%" + jfdate + "%'";
        Query q = s.createQuery(hql);
        List zxyjfList = q.list();
        recordNumber = zxyjfList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*����������ȡ����*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Zxyjf GetZxyjfById(int id) {
        Session s = factory.getCurrentSession();
        Zxyjf zxyjf = (Zxyjf)s.get(Zxyjf.class, id);
        return zxyjf;
    }

    /*����Zxyjf��Ϣ*/
    public void UpdateZxyjf(Zxyjf zxyjf) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(zxyjf);
    }

    /*ɾ��Zxyjf��Ϣ*/
    public void DeleteZxyjf (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object zxyjf = s.load(Zxyjf.class, id);
        s.delete(zxyjf);
    }

}
