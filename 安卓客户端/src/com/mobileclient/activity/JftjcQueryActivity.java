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
	// 声明查询按钮
	private Button btnQuery;
	// 声明积分条件输入框
	private EditText ET_jftj;
	/*查询过滤条件保存到这个对象中*/
	private Jftjc queryConditionJftjc = new Jftjc();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-设置查询积分条件条件");
		// 设置当前Activity界面布局
		setContentView(R.layout.jftjc_query);
		btnQuery = (Button) findViewById(R.id.btnQuery);
		ET_jftj = (EditText) findViewById(R.id.ET_jftj);
		/*单击查询按钮*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*获取查询参数*/
					queryConditionJftjc.setJftj(ET_jftj.getText().toString());
					/*操作完成后返回到积分条件管理界面*/ 
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
