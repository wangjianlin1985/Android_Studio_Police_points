package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import com.mobileclient.domain.Zxymjnd;
import com.mobileclient.service.ZxymjndService;
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

public class ZxymjndEditActivity extends Activity {
	// 声明确定添加按钮
	private Button btnUpdate;
	// 声明idTextView
	private TextView TV_id;
	// 声明部门ID输入框
	private EditText ET_bid;
	// 声明部门名称输入框
	private EditText ET_bname;
	// 声明警员id输入框
	private EditText ET_sid;
	// 声明警员名称输入框
	private EditText ET_sname;
	// 声明部门类型输入框
	private EditText ET_btypes;
	// 声明积分年度输入框
	private EditText ET_jfjd;
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
	/*要保存的警员年度积分信息*/
	Zxymjnd zxymjnd = new Zxymjnd();
	/*警员年度积分管理业务逻辑层*/
	private ZxymjndService zxymjndService = new ZxymjndService();

	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-修改警员年度积分");
		// 设置当前Activity界面布局
		setContentView(R.layout.zxymjnd_edit); 
		TV_id = (TextView) findViewById(R.id.TV_id);
		ET_bid = (EditText) findViewById(R.id.ET_bid);
		ET_bname = (EditText) findViewById(R.id.ET_bname);
		ET_sid = (EditText) findViewById(R.id.ET_sid);
		ET_sname = (EditText) findViewById(R.id.ET_sname);
		ET_btypes = (EditText) findViewById(R.id.ET_btypes);
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		ET_xsfajf = (EditText) findViewById(R.id.ET_xsfajf);
		ET_hmzfjf = (EditText) findViewById(R.id.ET_hmzfjf);
		ET_cpfkjf = (EditText) findViewById(R.id.ET_cpfkjf);
		ET_dwzsjf = (EditText) findViewById(R.id.ET_dwzsjf);
		ET_hjf = (EditText) findViewById(R.id.ET_hjf);
		btnUpdate = (Button) findViewById(R.id.BtnUpdate);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		/*单击修改警员年度积分按钮*/
		btnUpdate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*验证获取部门ID*/ 
					if(ET_bid.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "部门ID输入不能为空!", Toast.LENGTH_LONG).show();
						ET_bid.setFocusable(true);
						ET_bid.requestFocus();
						return;	
					}
					zxymjnd.setBid(Integer.parseInt(ET_bid.getText().toString()));
					/*验证获取部门名称*/ 
					if(ET_bname.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "部门名称输入不能为空!", Toast.LENGTH_LONG).show();
						ET_bname.setFocusable(true);
						ET_bname.requestFocus();
						return;	
					}
					zxymjnd.setBname(ET_bname.getText().toString());
					/*验证获取警员id*/ 
					if(ET_sid.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "警员id输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sid.setFocusable(true);
						ET_sid.requestFocus();
						return;	
					}
					zxymjnd.setSid(Integer.parseInt(ET_sid.getText().toString()));
					/*验证获取警员名称*/ 
					if(ET_sname.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "警员名称输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sname.setFocusable(true);
						ET_sname.requestFocus();
						return;	
					}
					zxymjnd.setSname(ET_sname.getText().toString());
					/*验证获取部门类型*/ 
					if(ET_btypes.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "部门类型输入不能为空!", Toast.LENGTH_LONG).show();
						ET_btypes.setFocusable(true);
						ET_btypes.requestFocus();
						return;	
					}
					zxymjnd.setBtypes(Integer.parseInt(ET_btypes.getText().toString()));
					/*验证获取积分年度*/ 
					if(ET_jfjd.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "积分年度输入不能为空!", Toast.LENGTH_LONG).show();
						ET_jfjd.setFocusable(true);
						ET_jfjd.requestFocus();
						return;	
					}
					zxymjnd.setJfjd(ET_jfjd.getText().toString());
					/*验证获取刑事发案积分*/ 
					if(ET_xsfajf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "刑事发案积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_xsfajf.setFocusable(true);
						ET_xsfajf.requestFocus();
						return;	
					}
					zxymjnd.setXsfajf(Float.parseFloat(ET_xsfajf.getText().toString()));
					/*验证获取号码走访积分*/ 
					if(ET_hmzfjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "号码走访积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_hmzfjf.setFocusable(true);
						ET_hmzfjf.requestFocus();
						return;	
					}
					zxymjnd.setHmzfjf(Float.parseFloat(ET_hmzfjf.getText().toString()));
					/*验证获取测评反馈积分*/ 
					if(ET_cpfkjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "测评反馈积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_cpfkjf.setFocusable(true);
						ET_cpfkjf.requestFocus();
						return;	
					}
					zxymjnd.setCpfkjf(Float.parseFloat(ET_cpfkjf.getText().toString()));
					/*验证获取单位重视积分*/ 
					if(ET_dwzsjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "单位重视积分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_dwzsjf.setFocusable(true);
						ET_dwzsjf.requestFocus();
						return;	
					}
					zxymjnd.setDwzsjf(Float.parseFloat(ET_dwzsjf.getText().toString()));
					/*验证获取合计分*/ 
					if(ET_hjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndEditActivity.this, "合计分输入不能为空!", Toast.LENGTH_LONG).show();
						ET_hjf.setFocusable(true);
						ET_hjf.requestFocus();
						return;	
					}
					zxymjnd.setHjf(Float.parseFloat(ET_hjf.getText().toString()));
					/*调用业务逻辑层上传警员年度积分信息*/
					ZxymjndEditActivity.this.setTitle("正在更新警员年度积分信息，稍等...");
					String result = zxymjndService.UpdateZxymjnd(zxymjnd);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*操作完成后返回到警员年度积分管理界面*/ 
					Intent intent = new Intent();
					intent.setClass(ZxymjndEditActivity.this, ZxymjndListActivity.class);
					startActivity(intent); 
					ZxymjndEditActivity.this.finish();
				} catch (Exception e) {}
			}
		});
		initViewData();
	}

	/* 初始化显示编辑界面的数据 */
	private void initViewData() {
	    zxymjnd = zxymjndService.GetZxymjnd(id);
		this.TV_id.setText(id+"");
		this.ET_bid.setText(zxymjnd.getBid() + "");
		this.ET_bname.setText(zxymjnd.getBname());
		this.ET_sid.setText(zxymjnd.getSid() + "");
		this.ET_sname.setText(zxymjnd.getSname());
		this.ET_btypes.setText(zxymjnd.getBtypes() + "");
		this.ET_jfjd.setText(zxymjnd.getJfjd());
		this.ET_xsfajf.setText(zxymjnd.getXsfajf() + "");
		this.ET_hmzfjf.setText(zxymjnd.getHmzfjf() + "");
		this.ET_cpfkjf.setText(zxymjnd.getCpfkjf() + "");
		this.ET_dwzsjf.setText(zxymjnd.getDwzsjf() + "");
		this.ET_hjf.setText(zxymjnd.getHjf() + "");
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
