package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.mobileclient.domain.Jftjc;

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

public class JftjcQueryActivity extends Activity {
	// ������ѯ��ť
	private Button btnQuery;
	// �����������������
	private EditText ET_jftj;
	/*��ѯ�����������浽���������*/
	private Jftjc queryConditionJftjc = new Jftjc();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-���ò�ѯ������������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.jftjc_query);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		ET_jftj = (EditText) findViewById(R.id.ET_jftj);
		/*������ѯ��ť*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��ȡ��ѯ����*/
					queryConditionJftjc.setJftj(ET_jftj.getText().toString());
					/*������ɺ󷵻ص����������������*/ 
					Intent intent = new Intent();
					intent.setClass(JftjcQueryActivity.this, JftjcListActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("queryConditionJftjc", queryConditionJftjc);
					intent.putExtras(bundle);
					startActivity(intent);  
					JftjcQueryActivity.this.finish();
				} catch (Exception e) {}
			}
			});
	}
}
