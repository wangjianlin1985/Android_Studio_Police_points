package com.mobileclient.domain;

import java.io.Serializable;

public class Zxy implements Serializable {
    /*id*/
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /*部门ID*/
    private int bid;
    public int getBid() {
        return bid;
    }
    public void setBid(int bid) {
        this.bid = bid;
    }

    /*部门名称*/
    private String bname;
    public String getBname() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }

    /*部门类型*/
    private int btypes;
    public int getBtypes() {
        return btypes;
    }
    public void setBtypes(int btypes) {
        this.btypes = btypes;
    }

    /*积分条件*/
    private int jid;
    public int getJid() {
        return jid;
    }
    public void setJid(int jid) {
        this.jid = jid;
    }
    
    private String jidString; 
    public String getJidString() {
		return jidString;
	}
	public void setJidString(String jidString) {
		this.jidString = jidString;
	}

	/*积分季度*/
    private String jfjd;
    public String getJfjd() {
        return jfjd;
    }
    public void setJfjd(String jfjd) {
        this.jfjd = jfjd;
    }
    
    
    /*积分季度开始时间*/
    private java.sql.Timestamp jdsdate;
    public java.sql.Timestamp getJdsdate() {
        return jdsdate;
    }
    public void setJdsdate(java.sql.Timestamp jdsdate) {
        this.jdsdate = jdsdate;
    }

    /*积分季度结束时间*/
    private java.sql.Timestamp jdedate;
    public java.sql.Timestamp getJdedate() {
        return jdedate;
    }
    public void setJdedate(java.sql.Timestamp jdedate) {
        this.jdedate = jdedate;
    }

    /*刑事发案积分*/
    private float xsfajf;
    public float getXsfajf() {
        return xsfajf;
    }
    public void setXsfajf(float xsfajf) {
        this.xsfajf = xsfajf;
    }

    /*号码走访积分*/
    private float hmzfjf;
    public float getHmzfjf() {
        return hmzfjf;
    }
    public void setHmzfjf(float hmzfjf) {
        this.hmzfjf = hmzfjf;
    }

    /*测评反馈积分*/
    private float cpfkjf;
    public float getCpfkjf() {
        return cpfkjf;
    }
    public void setCpfkjf(float cpfkjf) {
        this.cpfkjf = cpfkjf;
    }

    /*单位重视积分*/
    private float dwzsjf;
    public float getDwzsjf() {
        return dwzsjf;
    }
    public void setDwzsjf(float dwzsjf) {
        this.dwzsjf = dwzsjf;
    }

    /*合计分*/
    private float hjf;
    public float getHjf() {
        return hjf;
    }
    public void setHjf(float hjf) {
        this.hjf = hjf;
    }
	

}