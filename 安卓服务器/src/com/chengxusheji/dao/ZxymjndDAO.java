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
import com.chengxusheji.domain.Zxymjnd;

@Service @Transactional
public class ZxymjndDAO {

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
    public void AddZxymjnd(Zxymjnd zxymjnd) throws Exception {
    	Session s = factory.getCurrentSession();
     s.save(zxymjnd);
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxymjnd> QueryZxymjndInfo(String bname,String sname,String jfjd,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxymjnd zxymjnd where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxymjnd.bname like '%" + bname + "%'";
    	if(!sname.equals("")) hql = hql + " and zxymjnd.sname like '%" + sname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxymjnd.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	/*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List zxymjndList = q.list();
    	return (ArrayList<Zxymjnd>) zxymjndList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxymjnd> QueryZxymjndInfo(String bname,String sname,String jfjd) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxymjnd zxymjnd where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxymjnd.bname like '%" + bname + "%'";
    	if(!sname.equals("")) hql = hql + " and zxymjnd.sname like '%" + sname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxymjnd.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	List zxymjndList = q.list();
    	return (ArrayList<Zxymjnd>) zxymjndList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxymjnd> QueryAllZxymjndInfo() {
        Session s = factory.getCurrentSession(); 
        String hql = "From Zxymjnd";
        Query q = s.createQuery(hql);
        List zxymjndList = q.list();
        return (ArrayList<Zxymjnd>) zxymjndList;
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(String bname,String sname,String jfjd) {
        Session s = factory.getCurrentSession();
        String hql = "From Zxymjnd zxymjnd where 1=1";
        if(!bname.equals("")) hql = hql + " and zxymjnd.bname like '%" + bname + "%'";
        if(!sname.equals("")) hql = hql + " and zxymjnd.sname like '%" + sname + "%'";
        if(!jfjd.equals("")) hql = hql + " and zxymjnd.jfjd like '%" + jfjd + "%'";
        Query q = s.createQuery(hql);
        List zxymjndList = q.list();
        recordNumber = zxymjndList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*����������ȡ����*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Zxymjnd GetZxymjndById(int id) {
        Session s = factory.getCurrentSession();
        Zxymjnd zxymjnd = (Zxymjnd)s.get(Zxymjnd.class, id);
        return zxymjnd;
    }

    /*����Zxymjnd��Ϣ*/
    public void UpdateZxymjnd(Zxymjnd zxymjnd) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(zxymjnd);
    }

    /*ɾ��Zxymjnd��Ϣ*/
    public void DeleteZxymjnd (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object zxymjnd = s.load(Zxymjnd.class, id);
        s.delete(zxymjnd);
    }

}
