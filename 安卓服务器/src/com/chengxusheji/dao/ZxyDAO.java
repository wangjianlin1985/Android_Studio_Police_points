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
    	/*计算当前显示页码的开始记录*/
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

    /*计算总的页数和记录数*/
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

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Zxy GetZxyById(int id) {
        Session s = factory.getCurrentSession();
        Zxy zxy = (Zxy)s.get(Zxy.class, id);
        return zxy;
    }

    /*更新Zxy信息*/
    public void UpdateZxy(Zxy zxy) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(zxy);
    }

    /*删除Zxy信息*/
    public void DeleteZxy (int id) throws Exception {
        Session s = factory.getCurrentSession();
        Object zxy = s.load(Zxy.class, id);
        s.delete(zxy);
    }

}
