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
import com.chengxusheji.domain.Jftjc;

@Service @Transactional
public class JftjcDAO {

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
    public void AddJftjc(Jftjc jftjc) throws Exception {
    	Session s = factory.getCurrentSession();
     s.save(jftjc);
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Jftjc> QueryJftjcInfo(String jftj,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Jftjc jftjc where 1=1";
    	if(!jftj.equals("")) hql = hql + " and jftjc.jftj like '%" + jftj + "%'";
    	Query q = s.createQuery(hql);
    	/*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List jftjcList = q.list();
    	return (ArrayList<Jftjc>) jftjcList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Jftjc> QueryJftjcInfo(String jftj) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Jftjc jftjc where 1=1";
    	if(!jftj.equals("")) hql = hql + " and jftjc.jftj like '%" + jftj + "%'";
    	Query q = s.createQuery(hql);
    	List jftjcList = q.list();
    	return (ArrayList<Jftjc>) jftjcList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Jftjc> QueryAllJftjcInfo() {
        Session s = factory.getCurrentSession(); 
        String hql = "From Jftjc";
        Query q = s.createQuery(hql);
        List jftjcList = q.list();
        return (ArrayList<Jftjc>) jftjcList;
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(String jftj) {
        Session s = factory.getCurrentSession();
        String hql = "From Jftjc jftjc where 1=1";
        if(!jftj.equals("")) hql = hql + " and jftjc.jftj like '%" + jftj + "%'";
        Query q = s.createQuery(hql);
        List jftjcList = q.list();
        recordNumber = jftjcList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*����������ȡ����*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Jftjc GetJftjcById(int id) {
        Session s = factory.getCurrentSession();
        Jftjc jftjc = (Jftjc)s.get(Jftjc.class, id);
        return jftjc;
    }

    /*����Jftjc��Ϣ*/
    public void UpdateJftjc(Jftjc jftjc) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(jftjc);
    }

    /*ɾ��Jftjc��Ϣ*/
    public void DeleteJftjc (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object jftjc = s.load(Jftjc.class, id);
        s.delete(jftjc);
    }

}
