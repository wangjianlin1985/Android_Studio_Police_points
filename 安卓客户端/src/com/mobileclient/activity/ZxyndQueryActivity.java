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
	// ������ѯ��ť
	private Button btnQuery;
	// ������������������
	private Spinner Spinner_bname;
	private List<BnameDropdown> bnameList;
	private ZxyndService zxyndService = new ZxyndService();
	// ����������������
	private EditText ET_jfjd;
	/*��ѯ�����������浽���������*/
	private Zxynd queryConditionZxynd = new Zxynd();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-���ò�ѯ��λ��Ȼ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxynd_query);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		Spinner_bname = (Spinner) findViewById(R.id.Spinner_bname);
		bnameList = zxyndService.queryAllBname();
		int bnameCount = bnameList.size();
		String bnameText[] = new String[bnameCount];
		for(int i=0;i<bnameCount;i++) { 
			bnameText[i] = bnameList.get(i).getText();
		}
		// ����ѡ������ArrayAdapter��������
		ArrayAdapter<String> bname_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, bnameText);
		// ����ͼ����������б�ķ��
		bname_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		Spinner_bname.setAdapter(bname_adapter);
		// ����¼�Spinner�¼�����
		Spinner_bname.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				queryConditionZxynd.setBname((bnameList.get(arg2).getValue())); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		/*������ѯ��ť*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��ȡ��ѯ����*/
					//queryConditionZxynd.setBname(ET_bname.getText().toString());
					queryConditionZxynd.setJfjd(ET_jfjd.getText().toString());
					/*������ɺ󷵻ص���λ��Ȼ��ֹ������*/ 
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
