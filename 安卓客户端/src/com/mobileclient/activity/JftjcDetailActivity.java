package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.Jftjc;
import com.mobileclient.service.JftjcService;
import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
public class JftjcDetailActivity extends Activity {
	// 声明返回按钮
	private Button btnReturn;
	// 声明积分id控件
	private TextView TV_id;
	// 声明积分条件控件
	private TextView TV_jftj;
	// 声明分数控件
	private TextView TV_fs;
	// 声明积分类型控件
	private TextView TV_typeid;
	// 声明mtypeid控件
	private TextView TV_mtypeid;
	// 声明备注控件
	private TextView TV_bz;
	/* 要保存的积分条件信息 */
	Jftjc jftjc = new Jftjc(); 
	/* 积分条件管理业务逻辑层 */
	private JftjcService jftjcService = new JftjcService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-查看积分条件详情");
		// 设置当前Activity界面布局
		setContentView(R.layout.jftjc_detail);
		// 通过findViewById方法实例化组件
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_id = (TextView) findViewById(R.id.TV_id);
		TV_jftj = (TextView) findViewById(R.id.TV_jftj);
		TV_fs = (TextView) findViewById(R.id.TV_fs);
		TV_typeid = (TextView) findViewById(R.id.TV_typeid);
		TV_mtypeid = (TextView) findViewById(R.id.TV_mtypeid);
		TV_bz = (TextView) findViewById(R.id.TV_bz);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				JftjcDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* 初始化显示详情界面的数据 */
	private void initViewData() {
	    jftjc = jftjcService.GetJftjc(id); 
		this.TV_id.setText(jftjc.getId() + "");
		this.TV_jftj.setText(jftjc.getJftj());
		this.TV_fs.setText(jftjc.getFs() + "");
		this.TV_typeid.setText(jftjc.getTypeid() + "");
		this.TV_mtypeid.setText(jftjc.getMtypeid() + "");
		this.TV_bz.setText(jftjc.getBz());
	} 
}
