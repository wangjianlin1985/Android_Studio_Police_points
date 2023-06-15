package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.BnameDropdown;
import com.mobileclient.domain.JfjdDropdown;
import com.mobileclient.domain.Zxymj;
import com.mobileclient.service.ZxyService;
import com.mobileclient.service.ZxymjService;

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

public class ZxymjDepartQueryActivity extends Activity {
	// ������ѯ��ť
	private Button btnQuery;
	 
	private ZxymjService zxymjService = new ZxymjService(); 
	// ������Ա���������
	private EditText ET_sname; 
	// �������ּ���������
	private Spinner Spinner_jfjd;
	private List<JfjdDropdown> jfjdList; 
	/*��ѯ�����������浽���������*/
	private Zxymj queryConditionZxymj = new Zxymj();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-���ò�ѯ��Ա���Ȼ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxymj_depart_query);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		 
		ET_sname = (EditText) findViewById(R.id.ET_sname);
		Spinner_jfjd = (Spinner) findViewById(R.id.Spinner_jfjd);
		jfjdList = zxymjService.queryAllJfjd();
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
				queryConditionZxymj.setJfjd((jfjdList.get(arg2).getValue())); 
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
					//queryConditionZxymj.setBname(ET_bname.getText().toString());
					Declare declare = (Declare) ZxymjDepartQueryActivity.this.getApplication();
					queryConditionZxymj.setBname(declare.getMc());
					queryConditionZxymj.setSname(ET_sname.getText().toString());
					//queryConditionZxymj.setJfjd(ET_jfjd.getText().toString());
					/*������ɺ󷵻ص���Ա���Ȼ��ֹ������*/ 
					Intent intent = new Intent();
					intent.setClass(ZxymjDepartQueryActivity.this, ZxymjListActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("queryConditionZxymj", queryConditionZxymj);
					intent.putExtras(bundle);
					startActivity(intent);  
					ZxymjDepartQueryActivity.this.finish();
				} catch (Exception e) {}
			}
			});
	}
}
