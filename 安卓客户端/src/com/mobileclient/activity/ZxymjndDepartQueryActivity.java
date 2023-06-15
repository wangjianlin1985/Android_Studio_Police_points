package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.BnameDropdown;
import com.mobileclient.domain.JfjdDropdown;
import com.mobileclient.domain.Zxymjnd;
import com.mobileclient.service.ZxymjndService;
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

public class ZxymjndDepartQueryActivity extends Activity {
	// 声明查询按钮
	private Button btnQuery;
	 
	private ZxymjndService zxymjndService = new ZxymjndService();
	// 声明警员名称输入框
	private EditText ET_sname;
	// 声明积分年度输入框
	private Spinner Spinner_jfjd;
	private List<JfjdDropdown> jfjdList; 
	/*查询过滤条件保存到这个对象中*/
	private Zxymjnd queryConditionZxymjnd = new Zxymjnd();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-设置查询警员年度积分条件");
		// 设置当前Activity界面布局
		setContentView(R.layout.zxymjnd_depart_query);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		 
		ET_sname = (EditText) findViewById(R.id.ET_sname);
		Spinner_jfjd = (Spinner) findViewById(R.id.Spinner_jfjd);
		jfjdList = zxymjndService.queryAllJfjd();
		int jfjdCount = jfjdList.size();
		String jfjdText[] = new String[jfjdCount];
		for(int i=0;i<jfjdCount;i++) { 
			jfjdText[i] = jfjdList.get(i).getText();
		}
		// 将可选内容与ArrayAdapter连接起来
		ArrayAdapter<String> jfjd_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, jfjdText);
		// 设置图书类别下拉列表的风格
		jfjd_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		Spinner_jfjd.setAdapter(jfjd_adapter);
		// 添加事件Spinner事件监听
		Spinner_jfjd.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				queryConditionZxymjnd.setJfjd((jfjdList.get(arg2).getValue())); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		Spinner_jfjd.setVisibility(View.VISIBLE);
		/*单击查询按钮*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*获取查询参数*/
					//queryConditionZxymjnd.setBname(ET_bname.getText().toString());
					Declare declare = (Declare) ZxymjndDepartQueryActivity.this.getApplication();
					queryConditionZxymjnd.setBname(declare.getMc());
					queryConditionZxymjnd.setSname(ET_sname.getText().toString());
					//queryConditionZxymjnd.setJfjd(ET_jfjd.getText().toString());
					/*操作完成后返回到警员年度积分管理界面*/ 
					Intent intent = new Intent();
					intent.setClass(ZxymjndDepartQueryActivity.this, ZxymjndListActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("queryConditionZxymjnd", queryConditionZxymjnd);
					intent.putExtras(bundle);
					startActivity(intent);  
					ZxymjndDepartQueryActivity.this.finish();
				} catch (Exception e) {}
			}
			});
	}
}
