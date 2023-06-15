package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.Zxymj;
import com.mobileclient.service.ZxymjService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class ZxymjAddActivity extends Activity {
	// 声明确定添加按钮
	private Button btnAdd;
	// 声明部门id输入框
	private EditText ET_bid;
	// 声明部门名称输入框
	private EditText ET_bname;
	// 声明警员id输入框
	private EditText ET_sid;
	// 声明警员姓名输入框
	private EditText ET_sname;
	// 声明部门类型输入框
	private EditText ET_btypes;
	// 声明积分条件ID输入框
	private EditText ET_jid;
	// 声明积分季度输入框
	private EditText ET_jfjd;
	// 出版积分季度开始时间控件
	private DatePicker dp_jdsdate;
	// 出版积分季度结束时间控件
	private DatePicker dp_jdedate;
	// 声明刑事发案积分输入框
	private EditText ET_xsfajf;
	// 声明号码走访积分输入框
	private EditText ET_hmzfjf;
	// 声明测评反馈积分输入框
	private EditText ET_cpfkjf;
	// 声明单位重视积分输入框
	private EditText ET_dwzsjf;
	// 声明合计分输入框
	private EditText ET_hjf;
	protected String carmera_path;
	/*要保存的警员季度积分信息*/
	Zxymj zxymj = new Zxymj();
	/*警员季度积分管理业务逻辑层*/
	private ZxymjService zxymjService = new ZxymjService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-添加警员季度积分");
		// 设置当前Activity界面布局
		setContentView(R.layout.zxymj_add); 
		ET_bid = (EditText) findViewById(R.id.ET_bid);
		ET_bname = (EditText) findViewById(R.id.ET_bname);
		ET_sid = (EditText) findViewById(R.id.ET_sid);
		ET_sname = (EditText) findViewById(R.id.ET_sname);
		ET_btypes = (EditText) findViewById(R.id.ET_btypes);
		ET_jid = (EditText) findViewById(R.id.ET_jid);
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		dp_jdsdate = (DatePicker)this.findViewById(R.id.dp_jdsdate);
		dp_jdedate = (DatePicker)this.findViewById(R.id.dp_jdedate);
		ET_xsfajf = (EditText) findViewById(R.id.ET_xsfajf);
		ET_hmzfjf = (EditText) findViewById(R.id.ET_hmzfjf);
		ET_cpfkjf = (EditText) findViewById(R.id.ET_cpfkjf);
		ET_dwzsjf = (EditText) findViewById(R.id.ET_dwzsjf);
		ET_hjf = (EditText) findViewById(R.id.ET_hjf);
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*单击添加警员季度积分按钮*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*验证获取部门id*/ 
					if(ET_bid.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "部门id输入不能为空!", Toast.LENGTH_LONG).show();
						ET_bid.setFocusable(true);
						ET_bid.requestFocus();
						return;	
					}
					zxymj.setBid(Integer.parseInt(ET_bid.getText().toString()));
					/*验证获取部门名称*/ 
					if(ET_bname.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "部门名称输入不能为空!", Toast.LENGTH_LONG).show();
						ET_bname.setFocusable(true);
						ET_bname.requestFocus();
						return;	
					}
					zxymj.setBname(ET_bname.getText().toString());
					/*验证获取警员id*/ 
					if(ET_sid.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "警员id输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sid.setFocusable(true);
						ET_sid.requestFocus();
						return;	
					}
					zxymj.setSid(Integer.parseInt(ET_sid.getText().toString()));
					/*验证获取警员姓名*/ 
					if(ET_sname.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "警员姓名输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sname.setFocusable(true);
						ET_sname.requestFocus();
						return;	
					}
					zxymj.setSname(ET_sname.getText().toString());
					/*验证获取部门类型*/ 
					if(ET_btypes.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "部门类型输入不能为空!", Toast.LENGTH_LONG).show();
						ET_btypes.setFocusable(true);
						ET_btypes.requestFocus();
						return;	
					}
					zxymj.setBtypes(Integer.parseInt(ET_btypes.getText().toString()));
					/*验证获取积分条件ID*/ 
					if(ET_jid.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "积分条件ID输入不能为空!", Toast.LENGTH_LONG).show();
						ET_jid.setFocusable(true);
						ET_jid.requestFocus();
						return;	
					}
					zxymj.setJid(Integer.parseInt(ET_jid.getText().toString()));
					/*验证获取积分季度*/ 
					if(ET_jfjd.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "积分季度输入不能为空!", Toast.LENGTH_LONG).show();
						ET_jfjd.setFocusable(true);
						ET_jfjd.requestFocus();
						return;	
					}
					zxymj.setJfjd(ET_jfjd.getText().toString());
					/*获取积分季度开始时间*/
					Date jdsdate = new Date(dp_jdsdate.getYear()-1900,dp_jdsdate.getMonth(),dp_jdsdate.getDayOfMonth());
					zxymj.setJdsdate(new Timestamp(jdsdate.getTime()));
					/*获取积分季度结束时间*/
					Date jdedate = new Date(dp_jdedate.getYear()-1900,dp_jdedate.getMonth(),dp_jdedate.getDayOfMonth());
					zxymj.setJdedate(new Timestamp(jdedate.getTime()));
					/*验证获取刑事发案积分*/ 
					if(ET_xsfajf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "刑事发案积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_xsfajf.setFocusable(true);
						ET_xsfajf.requestFocus();
						return;	
					}
					zxymj.setXsfajf(Float.parseFloat(ET_xsfajf.getText().toString()));
					/*验证获取号码走访积分*/ 
					if(ET_hmzfjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "号码走访积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_hmzfjf.setFocusable(true);
						ET_hmzfjf.requestFocus();
						return;	
					}
					zxymj.setHmzfjf(Float.parseFloat(ET_hmzfjf.getText().toString()));
					/*验证获取测评反馈积分*/ 
					if(ET_cpfkjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "测评反馈积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_cpfkjf.setFocusable(true);
						ET_cpfkjf.requestFocus();
						return;	
					}
					zxymj.setCpfkjf(Float.parseFloat(ET_cpfkjf.getText().toString()));
					/*验证获取单位重视积分*/ 
					if(ET_dwzsjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "单位重视积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_dwzsjf.setFocusable(true);
						ET_dwzsjf.requestFocus();
						return;	
					}
					zxymj.setDwzsjf(Float.parseFloat(ET_dwzsjf.getText().toString()));
					/*验证获取合计分*/ 
					if(ET_hjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "合计分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_hjf.setFocusable(true);
						ET_hjf.requestFocus();
						return;	
					}
					zxymj.setHjf(Float.parseFloat(ET_hjf.getText().toString()));
					/*调用业务逻辑层上传警员季度积分信息*/
					ZxymjAddActivity.this.setTitle("正在上传警员季度积分信息，稍等...");
					String result = zxymjService.AddZxymj(zxymj);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*操作完成后返回到警员季度积分管理界面*/ 
					Intent intent = new Intent();
					intent.setClass(ZxymjAddActivity.this, ZxymjListActivity.class);
					startActivity(intent); 
					ZxymjAddActivity.this.finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
