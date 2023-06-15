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
    /*每页显示记录数目*/
    private final int PAGE_SIZE = 10;

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加图书信息*/
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
    	/*计算当前显示页码的开始记录*/
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

    /*计算总的页数和记录数*/
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

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Zxynd GetZxyndById(int id) {
        Session s = factory.getCurrentSession();
        Zxynd zxynd = (Zxynd)s.get(Zxynd.class, id);
        return zxynd;
    }

    /*更新Zxynd信息*/
    public void UpdateZxynd(Zxynd zxynd) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(zxynd);
    }

    /*删除Zxynd信息*/
    public void DeleteZxynd (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object zxynd = s.load(Zxynd.class, id);
        s.delete(zxynd);
    }

}
