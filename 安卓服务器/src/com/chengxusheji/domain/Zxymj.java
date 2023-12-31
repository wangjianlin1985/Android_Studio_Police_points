package com.chengxusheji.domain;

import java.sql.Timestamp;
public class Zxymj {
    /*id*/
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /*部门id*/
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

    /*警员id*/
    private int sid;
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }

    /*警员姓名*/
    private String sname;
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }

    /*部门类型*/
    private int btypes;
    public int getBtypes() {
        return btypes;
    }
    public void setBtypes(int btypes) {
        this.btypes = btypes;
    }

    /*积分条件ID*/
    private int jid;
    public int getJid() {
        return jid;
    }
    public void setJid(int jid) {
        this.jid = jid;
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
    private Timestamp jdsdate;
    public Timestamp getJdsdate() {
        return jdsdate;
    }
    public void setJdsdate(Timestamp jdsdate) {
        this.jdsdate = jdsdate;
    }

    /*积分季度结束时间*/
    private Timestamp jdedate;
    public Timestamp getJdedate() {
        return jdedate;
    }
    public void setJdedate(Timestamp jdedate) {
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