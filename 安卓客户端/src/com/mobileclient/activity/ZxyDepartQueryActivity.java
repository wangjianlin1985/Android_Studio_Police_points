package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.BnameDropdown;
import com.mobileclient.domain.JfjdDropdown;
import com.mobileclient.domain.Zxy;
import com.mobileclient.service.ZxyService;
import com.mobileclient.service.ZxyjfService;

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

public class ZxyDepartQueryActivity extends Activity {
	// ������ѯ��ť
	private Button btnQuery;
	 
	private ZxyService zxyService = new ZxyService();
	// �������ּ���������
	private Spinner Spinner_jfjd;
	private List<JfjdDropdown> jfjdList; 
	/*��ѯ�����������浽���������*/
	private Zxy queryConditionZxy = new Zxy();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-���ò�ѯ��λ���Ȼ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxy_depart_query);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		 
		Spinner_jfjd = (Spinner) findViewById(R.id.Spinner_jfjd);
		jfjdList = zxyService.queryAllJfjd();
		int jfjdCount = jfjdList.size();
		String jfjdText[] = new String[jfjdCount];
		for(int i=0;i<jfjdCount;i++) { 
			jfjdText[i] = jfjdList.get(i).getText();
		}
		// ����ѡ������ArrayAdapter��������
		ArrayAdapter<String> jfjd_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, jfjdText);
		// ����ͼ����������б�ķ��
		jfjd_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		Spinner_jfjd.setAdapter(jfjd_adapter);
		// ����¼�Spinner�¼�����
		Spinner_jfjd.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				queryConditionZxy.setJfjd((jfjdList.get(arg2).getValue())); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// ����Ĭ��ֵ
		Spinner_jfjd.setVisibility(View.VISIBLE);
		/*������ѯ��ť*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��ȡ��ѯ����*/
					//queryConditionZxy.setBname(ET_bname.getText().toString());
					//queryConditionZxy.setJfjd(ET_jfjd.getText().toString());
					Declare declare = (Declare) ZxyDepartQueryActivity.this.getApplication();
					queryConditionZxy.setBname(declare.getMc());
					/*������ɺ󷵻ص���λ���Ȼ��ֹ������*/ 
					Intent intent = new Intent();
					intent.setClass(ZxyDepartQueryActivity.this, ZxyListActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("queryConditionZxy", queryConditionZxy);
					intent.putExtras(bundle);
					startActivity(intent);  
					ZxyDepartQueryActivity.this.finish();
				} catch (Exception e) {}
			}
			});
	}
}
