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
import com.chengxusheji.domain.Zxymj;

@Service @Transactional
public class ZxymjDAO {

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
    public void AddZxymj(Zxymj zxymj) throws Exception {
    	Session s = factory.getCurrentSession();
     s.save(zxymj);
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxymj> QueryZxymjInfo(String bname,String sname,String jfjd,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxymj zxymj where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxymj.bname like '%" + bname + "%'";
    	if(!sname.equals("")) hql = hql + " and zxymj.sname like '%" + sname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxymj.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	/*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List zxymjList = q.list();
    	return (ArrayList<Zxymj>) zxymjList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxymj> QueryZxymjInfo(String bname,String sname,String jfjd) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxymj zxymj where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxymj.bname like '%" + bname + "%'";
    	if(!sname.equals("")) hql = hql + " and zxymj.sname like '%" + sname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxymj.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	List zxymjList = q.list();
    	return (ArrayList<Zxymj>) zxymjList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxymj> QueryAllZxymjInfo() {
        Session s = factory.getCurrentSession(); 
        String hql = "From Zxymj";
        Query q = s.createQuery(hql);
        List zxymjList = q.list();
        return (ArrayList<Zxymj>) zxymjList;
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(String bname,String sname,String jfjd) {
        Session s = factory.getCurrentSession();
        String hql = "From Zxymj zxymj where 1=1";
        if(!bname.equals("")) hql = hql + " and zxymj.bname like '%" + bname + "%'";
        if(!sname.equals("")) hql = hql + " and zxymj.sname like '%" + sname + "%'";
        if(!jfjd.equals("")) hql = hql + " and zxymj.jfjd like '%" + jfjd + "%'";
        Query q = s.createQuery(hql);
        List zxymjList = q.list();
        recordNumber = zxymjList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*����������ȡ����*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Zxymj GetZxymjById(int id) {
        Session s = factory.getCurrentSession();
        Zxymj zxymj = (Zxymj)s.get(Zxymj.class, id);
        return zxymj;
    }

    /*����Zxymj��Ϣ*/
    public void UpdateZxymj(Zxymj zxymj) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(zxymj);
    }

    /*ɾ��Zxymj��Ϣ*/
    public void DeleteZxymj (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object zxymj = s.load(Zxymj.class, id);
        s.delete(zxymj);
    }

}
