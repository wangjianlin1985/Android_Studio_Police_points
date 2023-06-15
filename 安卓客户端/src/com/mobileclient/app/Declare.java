package com.mobileclient.app;
 
import java.io.File;

import com.mobileclient.util.HttpUtil;

import android.app.Application;
import android.content.Context;

public class Declare extends Application {

	
	@Override
	public void onCreate() {
		super.onCreate(); 
		CrashHandler crashHandler = CrashHandler.getInstance();    
	    crashHandler.init(getApplicationContext()); 
	    context = this.getApplicationContext(); 
	    File path = new File(HttpUtil.FILE_PATH);
	    if(!path.exists()) path.mkdirs();
	}
	 
	public static Context context;
	 
	
	private int id;
    private String userName;
    private String mc; //用户部门名称;
    
    public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
}
