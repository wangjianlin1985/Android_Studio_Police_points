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
import com.chengxusheji.domain.Zxynd;

@Service @Transactional
public class ZxyndDAO {

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
    public void AddZxynd(Zxynd zxynd) throws Exception {
    	Session s = factory.getCurrentSession();
     s.save(zxynd);
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxynd> QueryZxyndInfo(String bname,String jfjd,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxynd zxynd where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxynd.bname like '%" + bname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxynd.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	/*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List zxyndList = q.list();
    	return (ArrayList<Zxynd>) zxyndList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxynd> QueryZxyndInfo(String bname,String jfjd) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxynd zxynd where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxynd.bname like '%" + bname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxynd.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	List zxyndList = q.list();
    	return (ArrayList<Zxynd>) zxyndList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxynd> QueryAllZxyndInfo() {
        Session s = factory.getCurrentSession(); 
        String hql = "From Zxynd";
        Query q = s.createQuery(hql);
        List zxyndList = q.list();
        return (ArrayList<Zxynd>) zxyndList;
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(String bname,String jfjd) {
        Session s = factory.getCurrentSession();
        String hql = "From Zxynd zxynd where 1=1";
        if(!bname.equals("")) hql = hql + " and zxynd.bname like '%" + bname + "%'";
        if(!jfjd.equals("")) hql = hql + " and zxynd.jfjd like '%" + jfjd + "%'";
        Query q = s.createQuery(hql);
        List zxyndList = q.list();
        recordNumber = zxyndList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*����������ȡ����*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Zxynd GetZxyndById(int id) {
        Session s = factory.getCurrentSession();
        Zxynd zxynd = (Zxynd)s.get(Zxynd.class, id);
        return zxynd;
    }

    /*����Zxynd��Ϣ*/
    public void UpdateZxynd(Zxynd zxynd) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(zxynd);
    }

    /*ɾ��Zxynd��Ϣ*/
    public void DeleteZxynd (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object zxynd = s.load(Zxynd.class, id);
        s.delete(zxynd);
    }

}
