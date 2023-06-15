package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.BnameDropdown;
import com.mobileclient.domain.Zxynd;
import com.mobileclient.service.ZxyService;
import com.mobileclient.service.ZxyndService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class ZxyndQueryActivity extends Activity {
	// 声明查询按钮
	private Button btnQuery;
	// 声明部门名称下拉框
	private Spinner Spinner_bname;
	private List<BnameDropdown> bnameList;
	private ZxyndService zxyndService = new ZxyndService();
	// 声明积分年度输入框
	private EditText ET_jfjd;
	/*查询过滤条件保存到这个对象中*/
	private Zxynd queryConditionZxynd = new Zxynd();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-设置查询单位年度积分条件");
		// 设置当前Activity界面布局
		setContentView(R.layout.zxynd_query);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		Spinner_bname = (Spinner) findViewById(R.id.Spinner_bname);
		bnameList = zxyndService.queryAllBname();
		int bnameCount = bnameList.size();
		String bnameText[] = new String[bnameCount];
		for(int i=0;i<bnameCount;i++) { 
			bnameText[i] = bnameList.get(i).getText();
		}
		// 将可选内容与ArrayAdapter连接起来
		ArrayAdapter<String> bname_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, bnameText);
		// 设置图书类别下拉列表的风格
		bname_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		Spinner_bname.setAdapter(bname_adapter);
		// 添加事件Spinner事件监听
		Spinner_bname.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				queryConditionZxynd.setBname((bnameList.get(arg2).getValue())); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		/*单击查询按钮*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*获取查询参数*/
					//queryConditionZxynd.setBname(ET_bname.getText().toString());
					queryConditionZxynd.setJfjd(ET_jfjd.getText().toString());
					/*操作完成后返回到单位年度积分管理界面*/ 
					Intent intent = new Intent();
					intent.setClass(ZxyndQueryActivity.this, ZxyndListActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("queryConditionZxynd", queryConditionZxynd);
					intent.putExtras(bundle);
					startActivity(intent);  
					ZxyndQueryActivity.this.finish();
				} catch (Exception e) {}
			}
			});
	}
}
