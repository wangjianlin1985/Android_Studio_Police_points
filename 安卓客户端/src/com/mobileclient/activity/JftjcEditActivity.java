package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import com.mobileclient.domain.Jftjc;
import com.mobileclient.service.JftjcService;
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

public class JftjcEditActivity extends Activity {
	// 声明确定添加按钮
	private Button btnUpdate;
	// 声明积分idTextView
	private TextView TV_id;
	// 声明积分条件输入框
	private EditText ET_jftj;
	// 声明分数输入框
	private EditText ET_fs;
	// 声明积分类型输入框
	private EditText ET_typeid;
	// 声明mtypeid输入框
	private EditText ET_mtypeid;
	// 声明备注输入框
	private EditText ET_bz;
	protected String carmera_path;
	/*要保存的积分条件信息*/
	Jftjc jftjc = new Jftjc();
	/*积分条件管理业务逻辑层*/
	private JftjcService jftjcService = new JftjcService();

	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-修改积分条件");
		// 设置当前Activity界面布局
		setContentView(R.layout.jftjc_edit); 
		TV_id = (TextView) findViewById(R.id.TV_id);
		ET_jftj = (EditText) findViewById(R.id.ET_jftj);
		ET_fs = (EditText) findViewById(R.id.ET_fs);
		ET_typeid = (EditText) findViewById(R.id.ET_typeid);
		ET_mtypeid = (EditText) findViewById(R.id.ET_mtypeid);
		ET_bz = (EditText) findViewById(R.id.ET_bz);
		btnUpdate = (Button) findViewById(R.id.BtnUpdate);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		/*单击修改积分条件按钮*/
		btnUpdate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*验证获取积分条件*/ 
					if(ET_jftj.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "积分条件输入不能为空!", Toast.LENGTH_LONG).show();
						ET_jftj.setFocusable(true);
						ET_jftj.requestFocus();
						return;	
					}
					jftjc.setJftj(ET_jftj.getText().toString());
					/*验证获取分数*/ 
					if(ET_fs.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "分数输入不能为空!", Toast.LENGTH_LONG).show();
						ET_fs.setFocusable(true);
						ET_fs.requestFocus();
						return;	
					}
					jftjc.setFs(Float.parseFloat(ET_fs.getText().toString()));
					/*验证获取积分类型*/ 
					if(ET_typeid.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "积分类型输入不能为空!", Toast.LENGTH_LONG).show();
						ET_typeid.setFocusable(true);
						ET_typeid.requestFocus();
						return;	
					}
					jftjc.setTypeid(Integer.parseInt(ET_typeid.getText().toString()));
					/*验证获取mtypeid*/ 
					if(ET_mtypeid.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "mtypeid输入不能为空!", Toast.LENGTH_LONG).show();
						ET_mtypeid.setFocusable(true);
						ET_mtypeid.requestFocus();
						return;	
					}
					jftjc.setMtypeid(Integer.parseInt(ET_mtypeid.getText().toString()));
					/*验证获取备注*/ 
					if(ET_bz.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "备注输入不能为空!", Toast.LENGTH_LONG).show();
						ET_bz.setFocusable(true);
						ET_bz.requestFocus();
						return;	
					}
					jftjc.setBz(ET_bz.getText().toString());
					/*调用业务逻辑层上传积分条件信息*/
					JftjcEditActivity.this.setTitle("正在更新积分条件信息，稍等...");
					String result = jftjcService.UpdateJftjc(jftjc);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*操作完成后返回到积分条件管理界面*/ 
					Intent intent = new Intent();
					intent.setClass(JftjcEditActivity.this, JftjcListActivity.class);
					startActivity(intent); 
					JftjcEditActivity.this.finish();
				} catch (Exception e) {}
			}
		});
		initViewData();
	}

	/* 初始化显示编辑界面的数据 */
	private void initViewData() {
	    jftjc = jftjcService.GetJftjc(id);
		this.TV_id.setText(id+"");
		this.ET_jftj.setText(jftjc.getJftj());
		this.ET_fs.setText(jftjc.getFs() + "");
		this.ET_typeid.setText(jftjc.getTypeid() + "");
		this.ET_mtypeid.setText(jftjc.getMtypeid() + "");
		this.ET_bz.setText(jftjc.getBz());
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
