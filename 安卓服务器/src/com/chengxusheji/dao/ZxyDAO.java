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
import com.chengxusheji.domain.Zxy;

@Service @Transactional
public class ZxyDAO {

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
    public void AddZxy(Zxy zxy) throws Exception {
    	Session s = factory.getCurrentSession();
     s.save(zxy);
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxy> QueryZxyInfo(String bname,String jfjd,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxy zxy where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxy.bname like '%" + bname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxy.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	/*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List zxyList = q.list();
    	return (ArrayList<Zxy>) zxyList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxy> QueryZxyInfo(String bname,String jfjd) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Zxy zxy where 1=1";
    	if(!bname.equals("")) hql = hql + " and zxy.bname like '%" + bname + "%'";
    	if(!jfjd.equals("")) hql = hql + " and zxy.jfjd like '%" + jfjd + "%'";
    	Query q = s.createQuery(hql);
    	List zxyList = q.list();
    	return (ArrayList<Zxy>) zxyList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Zxy> QueryAllZxyInfo() {
        Session s = factory.getCurrentSession(); 
        String hql = "From Zxy";
        Query q = s.createQuery(hql);
        List zxyList = q.list();
        return (ArrayList<Zxy>) zxyList;
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(String bname,String jfjd) {
        Session s = factory.getCurrentSession();
        String hql = "From Zxy zxy where 1=1";
        if(!bname.equals("")) hql = hql + " and zxy.bname like '%" + bname + "%'";
        if(!jfjd.equals("")) hql = hql + " and zxy.jfjd like '%" + jfjd + "%'";
        Query q = s.createQuery(hql);
        List zxyList = q.list();
        recordNumber = zxyList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*����������ȡ����*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Zxy GetZxyById(int id) {
        Session s = factory.getCurrentSession();
        Zxy zxy = (Zxy)s.get(Zxy.class, id);
        return zxy;
    }

    /*����Zxy��Ϣ*/
    public void UpdateZxy(Zxy zxy) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(zxy);
    }

    /*ɾ��Zxy��Ϣ*/
    public void DeleteZxy (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object zxy = s.load(Zxy.class, id);
        s.delete(zxy);
    }

}
