package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import com.mobileclient.domain.Zxyjf;
import com.mobileclient.service.ZxyjfService;
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
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.Toast;

public class ZxyjfEditActivity extends Activity {
	// 声明确定添加按钮
	private Button btnUpdate;
	// 声明idTextView
	private TextView TV_id;
	// 声明部门id输入框
	private EditText ET_bid;
	// 声明部门名称输入框
	private EditText ET_bname;
	// 声明部门类型输入框
	private EditText ET_btypes;
	// 声明警员id输入框
	private EditText ET_sid;
	// 声明警员姓名输入框
	private EditText ET_sname;
	// 声明积分条件ID输入框
	private EditText ET_jid;
	// 声明积分条件输入框
	private EditText ET_jftj;
	// 声明积分季度输入框
	private EditText ET_jfjd;
	// 出版积分日期控件
	private DatePicker dp_jfdate;
	// 出版积分季度开始时间控件
	private DatePicker dp_jdsdate;
	// 声明分数输入框
	private EditText ET_fs;
	// 声明数量输入框
	private EditText ET_sl;
	// 声明刑事发案积分输入框
	private EditText ET_xsfajf;
	// 声明号码走访积分输入框
	private EditText ET_hmzfjf;
	// 声明测评反馈积分输入框
	private EditText ET_cpfkjf;
	// 声明单位重视积分输入框
	private EditText ET_dwzsjf;
	protected String carmera_path;
	/*要保存的单位民警积分信息*/
	Zxyjf zxyjf = new Zxyjf();
	/*单位民警积分管理业务逻辑层*/
	private ZxyjfService zxyjfService = new ZxyjfService();

	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-修改单位民警积分");
		// 设置当前Activity界面布局
		setContentView(R.layout.zxyjf_edit); 
		TV_id = (TextView) findViewById(R.id.TV_id);
		ET_bid = (EditText) findViewById(R.id.ET_bid);
		ET_bname = (EditText) findViewById(R.id.ET_bname);
		ET_btypes = (EditText) findViewById(R.id.ET_btypes);
		ET_sid = (EditText) findViewById(R.id.ET_sid);
		ET_sname = (EditText) findViewById(R.id.ET_sname);
		ET_jid = (EditText) findViewById(R.id.ET_jid);
		ET_jftj = (EditText) findViewById(R.id.ET_jftj);
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		dp_jfdate = (DatePicker)this.findViewById(R.id.dp_jfdate);
		dp_jdsdate = (DatePicker)this.findViewById(R.id.dp_jdsdate);
		ET_fs = (EditText) findViewById(R.id.ET_fs);
		ET_sl = (EditText) findViewById(R.id.ET_sl);
		ET_xsfajf = (EditText) findViewById(R.id.ET_xsfajf);
		ET_hmzfjf = (EditText) findViewById(R.id.ET_hmzfjf);
		ET_cpfkjf = (EditText) findViewById(R.id.ET_cpfkjf);
		ET_dwzsjf = (EditText) findViewById(R.id.ET_dwzsjf);
		btnUpdate = (Button) findViewById(R.id.BtnUpdate);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		/*单击修改单位民警积分按钮*/
		btnUpdate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*验证获取部门id*/ 
					if(ET_bid.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "部门id输入不能为空!", Toast.LENGTH_LONG).show();
						ET_bid.setFocusable(true);
						ET_bid.requestFocus();
						return;	
					}
					zxyjf.setBid(Integer.parseInt(ET_bid.getText().toString()));
					/*验证获取部门名称*/ 
					if(ET_bname.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "部门名称输入不能为空!", Toast.LENGTH_LONG).show();
						ET_bname.setFocusable(true);
						ET_bname.requestFocus();
						return;	
					}
					zxyjf.setBname(ET_bname.getText().toString());
					/*验证获取部门类型*/ 
					if(ET_btypes.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "部门类型输入不能为空!", Toast.LENGTH_LONG).show();
						ET_btypes.setFocusable(true);
						ET_btypes.requestFocus();
						return;	
					}
					zxyjf.setBtypes(Integer.parseInt(ET_btypes.getText().toString()));
					/*验证获取警员id*/ 
					if(ET_sid.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "警员id输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sid.setFocusable(true);
						ET_sid.requestFocus();
						return;	
					}
					zxyjf.setSid(ET_sid.getText().toString());
					/*验证获取警员姓名*/ 
					if(ET_sname.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "警员姓名输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sname.setFocusable(true);
						ET_sname.requestFocus();
						return;	
					}
					zxyjf.setSname(ET_sname.getText().toString());
					/*验证获取积分条件ID*/ 
					if(ET_jid.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "积分条件ID输入不能为空!", Toast.LENGTH_LONG).show();
						ET_jid.setFocusable(true);
						ET_jid.requestFocus();
						return;	
					}
					zxyjf.setJid(Integer.parseInt(ET_jid.getText().toString()));
					/*验证获取积分条件*/ 
					if(ET_jftj.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "积分条件输入不能为空!", Toast.LENGTH_LONG).show();
						ET_jftj.setFocusable(true);
						ET_jftj.requestFocus();
						return;	
					}
					zxyjf.setJftj(ET_jftj.getText().toString());
					/*验证获取积分季度*/ 
					if(ET_jfjd.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "积分季度输入不能为空!", Toast.LENGTH_LONG).show();
						ET_jfjd.setFocusable(true);
						ET_jfjd.requestFocus();
						return;	
					}
					zxyjf.setJfjd(ET_jfjd.getText().toString());
					/*获取出版日期*/
					Date jfdate = new Date(dp_jfdate.getYear()-1900,dp_jfdate.getMonth(),dp_jfdate.getDayOfMonth());
					zxyjf.setJfdate(new Timestamp(jfdate.getTime()));
					/*获取出版日期*/
					Date jdsdate = new Date(dp_jdsdate.getYear()-1900,dp_jdsdate.getMonth(),dp_jdsdate.getDayOfMonth());
					zxyjf.setJdsdate(new Timestamp(jdsdate.getTime()));
					/*验证获取分数*/ 
					if(ET_fs.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "分数输入不能为空!", Toast.LENGTH_LONG).show();
						ET_fs.setFocusable(true);
						ET_fs.requestFocus();
						return;	
					}
					zxyjf.setFs(Float.parseFloat(ET_fs.getText().toString()));
					/*验证获取数量*/ 
					if(ET_sl.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "数量输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sl.setFocusable(true);
						ET_sl.requestFocus();
						return;	
					}
					zxyjf.setSl(Integer.parseInt(ET_sl.getText().toString()));
					/*验证获取刑事发案积分*/ 
					if(ET_xsfajf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "刑事发案积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_xsfajf.setFocusable(true);
						ET_xsfajf.requestFocus();
						return;	
					}
					zxyjf.setXsfajf(Float.parseFloat(ET_xsfajf.getText().toString()));
					/*验证获取号码走访积分*/ 
					if(ET_hmzfjf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "号码走访积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_hmzfjf.setFocusable(true);
						ET_hmzfjf.requestFocus();
						return;	
					}
					zxyjf.setHmzfjf(Float.parseFloat(ET_hmzfjf.getText().toString()));
					/*验证获取测评反馈积分*/ 
					if(ET_cpfkjf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "测评反馈积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_cpfkjf.setFocusable(true);
						ET_cpfkjf.requestFocus();
						return;	
					}
					zxyjf.setCpfkjf(Float.parseFloat(ET_cpfkjf.getText().toString()));
					/*验证获取单位重视积分*/ 
					if(ET_dwzsjf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfEditActivity.this, "单位重视积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_dwzsjf.setFocusable(true);
						ET_dwzsjf.requestFocus();
						return;	
					}
					zxyjf.setDwzsjf(Float.parseFloat(ET_dwzsjf.getText().toString()));
					/*调用业务逻辑层上传单位民警积分信息*/
					ZxyjfEditActivity.this.setTitle("正在更新单位民警积分信息，稍等...");
					String result = zxyjfService.UpdateZxyjf(zxyjf);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*操作完成后返回到单位民警积分管理界面*/ 
					Intent intent = new Intent();
					intent.setClass(ZxyjfEditActivity.this, ZxyjfListActivity.class);
					startActivity(intent); 
					ZxyjfEditActivity.this.finish();
				} catch (Exception e) {}
			}
		});
		initViewData();
	}

	/* 初始化显示编辑界面的数据 */
	private void initViewData() {
	    zxyjf = zxyjfService.GetZxyjf(id);
		this.TV_id.setText(id+"");
		this.ET_bid.setText(zxyjf.getBid() + "");
		this.ET_bname.setText(zxyjf.getBname());
		this.ET_btypes.setText(zxyjf.getBtypes() + "");
		this.ET_sid.setText(zxyjf.getSid());
		this.ET_sname.setText(zxyjf.getSname());
		this.ET_jid.setText(zxyjf.getJid() + "");
		this.ET_jftj.setText(zxyjf.getJftj());
		this.ET_jfjd.setText(zxyjf.getJfjd());
		Date jfdate = new Date(zxyjf.getJfdate().getTime());
		this.dp_jfdate.init(jfdate.getYear() + 1900,jfdate.getMonth(), jfdate.getDate(), null);
		Date jdsdate = new Date(zxyjf.getJdsdate().getTime());
		this.dp_jdsdate.init(jdsdate.getYear() + 1900,jdsdate.getMonth(), jdsdate.getDate(), null);
		this.ET_fs.setText(zxyjf.getFs() + "");
		this.ET_sl.setText(zxyjf.getSl() + "");
		this.ET_xsfajf.setText(zxyjf.getXsfajf() + "");
		this.ET_hmzfjf.setText(zxyjf.getHmzfjf() + "");
		this.ET_cpfkjf.setText(zxyjf.getCpfkjf() + "");
		this.ET_dwzsjf.setText(zxyjf.getDwzsjf() + "");
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
